package fr.lip6.move.coloane.extensions.importgrml;

import fr.lip6.move.coloane.core.model.factory.FormalismManager;
import fr.lip6.move.coloane.core.model.factory.GraphModelFactory;
import fr.lip6.move.coloane.core.model.interfaces.ICoreGraph;
import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CancellationException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;

import org.cosyverif.model.Arc;
import org.cosyverif.model.Attribute;
import org.cosyverif.model.Model;
import org.cosyverif.model.Node;
import org.cosyverif.model.Note;
import org.cosyverif.model.Note.Ref;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;

/**
 * Semi-automatic converter from GrML to Coloane that uses configuration to establish the mapping between the two representations.
 */
public final class HelpedHandler implements ModelHandler {

	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.extensions.importgrml"); //$NON-NLS-1$

	private IConfigurationElement configuration;
	
	/**
	 * Representation of a GrML attribute type..
	 */
	private class AttributeEntry {
		private String type;
		private String container;
		/**
		 * 
		 * @param type The type (name) of the attribute
		 * @param container The container of the attribute (node or arc type), or empty string if the attribute belongs to the model.
		 */
		public AttributeEntry(String type, String container) {
			this.type = type;
			if (container == null) {
				this.container = "";
			} else {
				this.container = container;
			}
		}

		@Override
		public int hashCode() {
			return type.hashCode() ^ container.hashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			AttributeEntry that = (AttributeEntry) obj;
			return (that != null) && (this.type.equals(that.type)) && (this.container.equals(that.container));
		}
	}
	
	private final Properties formalismEquivalence;
	private final Properties nodeEquivalence;
	private final Properties arcEquivalence;
	private final Map<AttributeEntry, String> attributeEquivalence;
	private final Map<AttributeEntry, AttributeHandler> attributeConverter;
	private final List<PostProcessingHandler> postProcessing;
	
	private final AttributeHandler defaultAttributeConverter = new TextAttributeHandler();
	
	/**
	 * Build a handler from the plug-in configuration.
	 * @param configuration The plug-in configuration
	 */
	public HelpedHandler(IConfigurationElement configuration) {
		this.configuration = configuration;
		this.formalismEquivalence = new Properties();
		this.nodeEquivalence  = new Properties();
		this.arcEquivalence  = new Properties();
		this.attributeEquivalence  = new HashMap<AttributeEntry, String>();
		this.attributeConverter  = new HashMap<AttributeEntry, AttributeHandler>();
		this.postProcessing = new ArrayList<PostProcessingHandler>();
	}
	
	/**
	 * Populate configuration fields from the plug-in configuration.
	 * @throws Exception if something goes wrong
	 */
	private void populateConfiguration() throws Exception {
		// Fill tables with configuration:
		LOGGER.fine("Loading configuration...");
		for (IConfigurationElement conversion: Arrays.asList(configuration.getChildren())) {
			if (conversion.getName().equals("formalism-conversion")) {
				String fml = conversion.getAttribute("fml-url");
				String coloane = conversion.getAttribute("coloane-formalism-id");
				LOGGER.fine("Add formalism equivalence: " + fml + " <-> " + coloane + ".");
				formalismEquivalence.put(fml, coloane);
			} else if (conversion.getName().equals("node-conversion")) {
				String fml = conversion.getAttribute("fml-node-type");
				String coloane = conversion.getAttribute("coloane-node-type");
				LOGGER.fine("Add node equivalence: " + fml + " <-> " + coloane + ".");
				nodeEquivalence.put(fml, coloane);
			} else if (conversion.getName().equals("arc-conversion")) {
				String fml = conversion.getAttribute("fml-arc-type");
				String coloane = conversion.getAttribute("coloane-arc-type");
				LOGGER.fine("Add arc equivalence: " + fml + " <-> " + coloane + ".");
				arcEquivalence.put(fml, coloane);
			} else if (conversion.getName().equals("attribute-conversion")) {
				String fml = conversion.getAttribute("fml-attribute-type");
				String coloane = conversion.getAttribute("coloane-attribute-type");
				if (coloane == null) {
					coloane = fml;
				}
				String container = conversion.getAttribute("container");
				AttributeEntry entry = new AttributeEntry(fml, container);
				LOGGER.fine("Add attribute equivalence: " + entry.type + " in " + entry.container + " <-> " + coloane + ".");
				attributeEquivalence.put(entry, coloane);
				AttributeHandler handler = (AttributeHandler) conversion.createExecutableExtension("handler");
				if (handler == null) {
					handler = defaultAttributeConverter;
				}
				LOGGER.fine("Add attribute converter: " + entry.type + " in " + entry.container + " <-> " + conversion.getAttribute("handler") + ".");
				attributeConverter.put(entry, handler);
			} else if (conversion.getName().equals("post-processing")) {
				postProcessing.add((PostProcessingHandler) conversion.createExecutableExtension("handler"));
			} else {
				LOGGER.warning("Contribution element '" + conversion.getName() + "' is not taken into account.");
			}
		}
	}
	
	/**
	 * Load the GrML model.
	 * @param reader The XML stream to load
	 * @return The loaded model
	 * @throws Exception if something goes wrong
	 */
	private Model loadModel(XMLStreamReader reader) throws Exception {
		// Load model:
		LOGGER.fine("Loading model...");
		JAXBContext context = JAXBContext.newInstance(Model.class);
		Unmarshaller u = context.createUnmarshaller();
		Model model = (Model) u.unmarshal(reader);
		return model;
	}
	
	/**
	 * Transform the GrML model to a Coloane model.
	 * @param model The GrML model
	 * @param monitor The progress monitor
	 * @return The Coloane model
	 * @throws ExtensionException if something goes wrong
	 */
	private IGraph transformModel(Model model, IProgressMonitor monitor) throws ExtensionException {
		// Get number of nodes, arcs, notes and attributes for the monitor:
		int totalWork = 0;
		totalWork += model.getAttribute().size()
				+ model.getNode().size()
				+ model.getArc().size()
				+ model.getNote().size();
		for (Node node: model.getNode()) {
			totalWork += node.getNodeContent().size();
		}
		for (Arc arc: model.getArc()) {
			totalWork += arc.getArcContent().size();
		}
		try {
			monitor.beginTask("Importing GrML model", totalWork);
			// Perform conversion:
			boolean hasErrors = false;
			String formalismName = formalismEquivalence.getProperty(model.getFormalismUrl());
			IFormalism formalism = FormalismManager.getInstance().getFormalismById(formalismName);
			GraphModelFactory factory = new GraphModelFactory();
			ICoreGraph result = (ICoreGraph) factory.createGraph(formalism);
			// Model attributes:
			for (Attribute attribute: model.getAttribute()) {
				hasErrors = hasErrors || importAttribute(result, attribute, "", monitor);
			}
			// Nodes:
			for (Node node: model.getNode()) {
				hasErrors = hasErrors || importNode(result, node, monitor);
			}
			// Arcs:
			for (Arc arc: model.getArc()) {
				hasErrors = hasErrors || importArc(result, arc, monitor);
			}
			// Notes:
			for (Note note: model.getNote()) {
				hasErrors = hasErrors || importNote(result, note, monitor);
			}
			if (hasErrors) {
				throw new ExtensionException("Model conversion is incomplete because of conversion errors.");
			}
			return result;
		} catch (CancellationException e) {
			throw e;
		} finally {
			monitor.done();
		}
	}
	
	/**
	 * Transform a GrML attribute to a Coloane attribute.
	 * @param result The Coloane attribute container
	 * @param attribute The GrML attribute
	 * @param container The name of parent container (node or arc type)
	 * @param monitor The progress monitor
	 * @return true if an error occured
	 */
	private boolean importAttribute(IElement result, Attribute attribute, String container, IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			throw new CancellationException("Import canceled.");
		}
		try {
			AttributeEntry entry = new AttributeEntry(attribute.getName(), container);
			String name = attributeEquivalence.get(entry);
			if (name == null) {
				name = attribute.getName();
			}
			LOGGER.fine("Converting attribute '" + attribute.getName() + "' to '" + name + "'.");
			AttributeHandler converter = attributeConverter.get(entry);
			if (converter == null) {
				converter = defaultAttributeConverter;
			}
			String converted = converter.importFrom(attribute);
			LOGGER.fine("Converted to value '" + converted + "'.");
			if (result.getAttribute(name) == null) {
				throw new ExtensionException("Attribute fml:'" + attribute.getName() + "' is translated to attribute coloane:'" + name + "' that does not exist.");
			}
			result.getAttribute(name).setValue(converted);
			Point point = new Point();
			if (attribute.getX() != null) {
				point.setX(attribute.getX().intValue());
				LOGGER.fine("Position 'x' is '" + point.x + "'.");
			} else {
				LOGGER.fine("Attribute '" + attribute.getName() + "' does not define an 'x' position.");
			}
			if (attribute.getY() != null) {
				point.setY(attribute.getX().intValue());
				LOGGER.fine("Position 'y' is '" + point.y + "'.");
			} else {
				LOGGER.fine("Attribute '" + attribute.getName() + "' does not define an 'y' position.");
			}
			result.getAttribute(name).getGraphicInfo().setLocation(point);
			monitor.worked(1);
			return false;
		} catch (Exception e) {
			LOGGER.warning("Something wrong happened during conversion of attribute '" + attribute.getName() + "' (" + e.getMessage() + ").");
			return true;
		}
	}
	
	/**
	 * Transform a GrML node to a Coloane node.
	 * @param result The Coloane model
	 * @param node The GrML node
	 * @param monitor The progress monitor
	 * @return true if an error occured
	 */
	private boolean importNode(ICoreGraph result, Node node, IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			throw new CancellationException("Import canceled.");
		}
		boolean hasErrors = false;
		try {
			LOGGER.fine("Converting fml node '" + node.getId() + "'.");
			String name = nodeEquivalence.getProperty(node.getNodeType(), node.getNodeType());
			INodeFormalism formalism = (INodeFormalism) result.getFormalism().getRootGraph().getElementFormalism(name);
			if (formalism == null) {
				throw new ExtensionException("Node type '" + node.getNodeType() + "' is translated to coloane node type '" + name + "' that does not exist or is not a node.");
			} else {
				LOGGER.fine("Found equivalence of fml node type '" + node.getNodeType() + "' with node type '" + name + "' in coloane.");
			}
			INode created = result.createNode(formalism, node.getId().intValue());
			Point point = new Point();
			if (node.getX() != null) {
				point.setX(node.getX().intValue());
				LOGGER.fine("Position 'x' is '" + point.x + "'.");
			} else {
				LOGGER.fine("Node '" + node.getId() + "' does not define an 'x' position.");
			}
			if (node.getY() != null) {
				point.setY(node.getY().intValue());
				LOGGER.fine("Position 'y' is '" + point.y + "'.");
			} else {
				LOGGER.fine("Node '" + node.getId() + "' does not define an 'y' position.");
			}
			created.getGraphicInfo().setLocation(point);
			for (Object content: node.getNodeContent()) {
				if (content instanceof Attribute) {
					hasErrors = hasErrors || importAttribute(created, (Attribute) content, name, monitor);
				} else {
					LOGGER.warning("Content '" + content + "' is not an attribute.");
				}
			}
			monitor.worked(1);
			return hasErrors;
		} catch (CancellationException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.warning("Something wrong happened during conversion of node '" + node.getId() + "' (" + e.getMessage() + ").");
			return true;
		}
	}
	
	/**
	 * Transform a GrML arc to a Coloane arc.
	 * @param result The Coloane model
	 * @param arc The GrML arc
	 * @param monitor The progress monitor
	 * @return true if an error occured
	 */
	private boolean importArc(ICoreGraph result, Arc arc, IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			throw new CancellationException("Import canceled.");
		}
		boolean hasErrors = false;
		try {
			LOGGER.fine("Converting fml arc '" + arc.getId() + "'.");
			String name = arcEquivalence.getProperty(arc.getArcType(), arc.getArcType());
			IArcFormalism formalism = (IArcFormalism) result.getFormalism().getRootGraph().getElementFormalism(name);
			if (formalism == null) {
				throw new ExtensionException("Arc type '" + arc.getArcType() + "' is translated to coloane arc type '" + name + "' that does not exist or is not ar arc.");
			} else {
				LOGGER.fine("Found equivalence of fml arc type '" + arc.getArcType() + "' with arc type '" + name + "' in coloane.");
			}
			INode source = result.getNode(arc.getSource().intValue());
			if (source == null) {
				throw new ExtensionException("Arc source '" + arc.getSource() + "' does not exist.");
			}
			INode target = result.getNode(arc.getTarget().intValue());
			if (target == null) {
				throw new ExtensionException("Arc target '" + arc.getTarget() + "' does not exist.");
			}
			IArc created = result.createArc(formalism, source, target, arc.getId().intValue());
			for (org.cosyverif.model.Point point: arc.getCoordinatePoints()) {
				created.addInflexPoint(new Point(point.getX().intValue(), point.getY().intValue()));
			}
			for (Object content: arc.getArcContent()) {
				if (content instanceof Attribute) {
					hasErrors = hasErrors || importAttribute(created, (Attribute) content, name, monitor);
				} else {
					LOGGER.warning("Content '" + content + "' is not an attribute.");
				}
			}
			monitor.worked(1);
			return hasErrors;
		} catch (CancellationException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.warning("Something wrong happened during conversion of node '" + arc.getId() + "' (" + e.getMessage() + ").");
			return true;
		}
	}
	
	/**
	 * Transform a GrML note to a Coloane note.
	 * @param result The Coloane model
	 * @param note The GrML note
	 * @param monitor The progress monitor
	 * @return true if an error occured
	 */
	private boolean importNote(ICoreGraph result, Note note, IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			throw new CancellationException("Import canceled.");
		}
		try {
			IStickyNote created = result.createStickyNote();
			created.setLabelContents(note.getContent());
			for (Ref link: note.getRef()) {
				INode node = result.getNode(link.getId().intValue());
				IArc arc   = result.getArc(link.getId().intValue());
				if (node != null) {
					result.createLink(created, node);
				} else if (arc != null) {
					result.createLink(created, arc);
				} else {
					LOGGER.warning("Id '" + link.getId() + "' does not refer to a node or an arc.");
				}
			}
			Point point = new Point();
			if (note.getX() != null) {
				point.setX(note.getX().intValue());
				LOGGER.fine("Position 'x' is '" + point.x + "'.");
			} else {
				LOGGER.fine("Note does not define an 'x' position.");
			}
			if (note.getY() != null) {
				point.setY(note.getY().intValue());
				LOGGER.fine("Position 'y' is '" + point.y + "'.");
			} else {
				LOGGER.fine("Note does not define an 'y' position.");
			}
			created.setLocation(point);
			monitor.worked(1);
			return false;
		} catch (CancellationException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.warning("Something wrong happened during conversion of note (" + e.getMessage() + ").");
			return true;
		}
	}
	
	@Override
	public IGraph importFrom(XMLStreamReader reader, IProgressMonitor monitor) throws ExtensionException {
		try {
			LOGGER.setLevel(Level.INFO);
			populateConfiguration();
			return transformModel(loadModel(reader), monitor);
		} catch (CancellationException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.warning("Could not import model because: " + e.getMessage());
			throw new ExtensionException("Could not import model.");
		}
	}

}
