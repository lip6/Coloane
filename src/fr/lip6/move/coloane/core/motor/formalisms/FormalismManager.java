package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintLink;
import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintNode;
import fr.lip6.move.coloane.core.motor.formalisms.elements.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.AttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.ComputedAttributeFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphicalDescription;
import fr.lip6.move.coloane.core.motor.formalisms.elements.NodeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Formalism Manager.<br>
 * This class is called in order to:
 * <ul>
 * 	<li>Load and Build all formalisms at runtime (when the editor is launched)</li>
 * 	<li>Give a way to access the formalism by its name, its identifier...</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 */
public final class FormalismManager {

	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The name of the extension point that contains formalisms definition */
	private static final String EXTENSION_ID = "fr.lip6.move.coloane.core.formalisms"; //$NON-NLS-1$

	/** The FormalismManager singleton */
	private static FormalismManager instance = null;

	/** Available formalisms list */
	private List<IFormalism> formalisms = new ArrayList<IFormalism>();

	/**
	 * Constructor.<br>
	 * All available (connected as extension) formalisms are built.
	 * @see {@link #getInstance()}
	 */
	private FormalismManager() {
		IConfigurationElement[] formalisms = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
		for (int i = 0; i < formalisms.length; i++) {
			buildFormalism(formalisms[i]);
		}
	}

	/**
	 * @return The FormalismManager instance
	 */
	public static synchronized FormalismManager getInstance() {
		if (instance == null) { instance = new FormalismManager(); }
		return instance;
	}

	/**
	 * Build a formalism thanks to its XML definition
	 * @param description Description loads from the extension point
	 */
	private void buildFormalism(IConfigurationElement description) {
		String id, name, fkname, xschema, image;
		id = description.getAttribute("id"); //$NON-NLS-1$
		name = description.getAttribute("name");  //$NON-NLS-1$
		fkname = description.getAttribute("fkname");  //$NON-NLS-1$
		xschema = description.getAttribute("xschema"); //$NON-NLS-1$
		image = description.getAttribute("image"); //$NON-NLS-1$

		LOGGER.fine("Build a formalism " + name + "(FK ID: " + fkname + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		LOGGER.finer("XSchema : " + xschema + " - Image : " + image); //$NON-NLS-1$ //$NON-NLS-2$

		Formalism form = new Formalism(id, name, fkname, image);
		IConfigurationElement[] xmlDescription = description.getChildren("XmlDescription"); //$NON-NLS-1$

		// Graphs definition from XML description
		IConfigurationElement[] graphes = xmlDescription[0].getChildren("Graph"); //$NON-NLS-1$
		for (IConfigurationElement graph : graphes) {
			GraphFormalism g = new GraphFormalism(graph.getAttribute("name"), form); //$NON-NLS-1$
			LOGGER.finer("Build a graph element : " + g.getName()); //$NON-NLS-1$
			this.buildAttributes(g, graph);
			this.buildComputedAttributes(g, graph);
			form.setRootGraph(g);

			// Nodes definition from XML description
			IConfigurationElement[] nodes = graph.getChildren("Node"); //$NON-NLS-1$
			for (IConfigurationElement node : nodes) {
				NodeFormalism n = new NodeFormalism(node.getAttribute("name"), form); //$NON-NLS-1$
				LOGGER.finer("Build a node element : " + n.getName()); //$NON-NLS-1$
				this.buildAttributes(n, node);
				this.buildComputedAttributes(n, node);
				this.buildGraphicalDescription(n, node);
				g.addElement(n);
			}

			// Arcs definition from XML description
			IConfigurationElement[] arcs = graph.getChildren("Arc"); //$NON-NLS-1$
			for (IConfigurationElement arc : arcs) {
				ArcFormalism a = new ArcFormalism(arc.getAttribute("name"), form); //$NON-NLS-1$
				LOGGER.finer("Build an arc element : " + a.getName()); //$NON-NLS-1$
				this.buildAttributes(a, arc);
				this.buildComputedAttributes(a, arc);
				this.buildGraphicalDescription(a, arc);
				g.addElement(a);
			}
		}

		// Build constraints
		// Two kinds of constraint exist: Links or Nodes (beware of their type)
		try {
			IConfigurationElement[] constraints = xmlDescription[0].getChildren("Constraint"); //$NON-NLS-1$
			for (IConfigurationElement constraint : constraints) {
				// Link constraint
				if (Boolean.parseBoolean(constraint.getAttribute("link"))) { //$NON-NLS-1$
					form.addConstraintLink((IConstraintLink) constraint.createExecutableExtension("type")); //$NON-NLS-1$
				// Node constraint
				} else {
					form.addConstraintNode((IConstraintNode) constraint.createExecutableExtension("type")); //$NON-NLS-1$
				}
			}
		} catch (CoreException core) {
			LOGGER.warning("Formalism definition error! A constraint has not been correctly defined: " + core.getMessage()); //$NON-NLS-1$
			return;
		}

		// Add the formalism to the FormalismManager's list
		this.formalisms.add(form);
	}

	/**
	 * Extract all elements attributes and add them to the parent description
	 * @param element Formalism element that is currently parsed. (considered as the parent)
	 * @param description Element description. This description may contains attributes
	 */
	private void buildAttributes(ElementFormalism element, IConfigurationElement description) {
		// Browse all attributes from the element description
		IConfigurationElement[] attributes = description.getChildren("Attribute"); //$NON-NLS-1$
		for (IConfigurationElement attribute : attributes) {

			// Test whether this attribute is limited to an enumerated range of values.
			boolean isEnum = Boolean.parseBoolean(attribute.getAttribute("enumerated")); //$NON-NLS-1$
			List<String> enumValues = null;
			if (isEnum) {
				enumValues = new ArrayList<String>();
				for (IConfigurationElement enumVal : attribute.getChildren("EnumerationValue")) { //$NON-NLS-1$
					enumValues.add(enumVal.getAttribute("name")); //$NON-NLS-1$
				}
			}
			// Now either !isEnum, or enumValues is not null.

			AttributeFormalism a = new AttributeFormalism(attribute.getAttribute("name"), Boolean.parseBoolean(attribute.getAttribute("drawable")), Boolean.parseBoolean(attribute.getAttribute("multiline")), isEnum, enumValues);  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
			LOGGER.finer("Build the attribute " + a.getName() + " for the element : " + element.getName()); //$NON-NLS-1$ //$NON-NLS-2$

			// Parse the default value
			if (attribute.getAttribute("default") != null) { //$NON-NLS-1$
				a.setDefaultValue(attribute.getAttribute("default")); //$NON-NLS-1$
				LOGGER.finer("Add default value " + a.getDefaultValue() + " for the attribute : " + a.getName()); //$NON-NLS-1$ //$NON-NLS-2$
				if (attribute.getAttribute("displayed_default") != null) { //$NON-NLS-1$
					a.setDefaultValueDrawable(Boolean.parseBoolean(attribute.getAttribute("displayed_default"))); //$NON-NLS-1$
				}
			}
			
			// Set default location (delta_x and delta_y)
			if (attribute.getAttribute("delta_x") != null) { //$NON-NLS-1$
				a.setXDelta(Integer.parseInt(attribute.getAttribute("delta_x"))); //$NON-NLS-1$
				LOGGER.finer("Add relative location (X) for the attribute : " + a.getName()); //$NON-NLS-1$
			}
			
			if (attribute.getAttribute("delta_y") != null) { //$NON-NLS-1$
				a.setYDelta(Integer.parseInt(attribute.getAttribute("delta_y"))); //$NON-NLS-1$
				LOGGER.finer("Add relative location (Y) for the attribute : " + a.getName()); //$NON-NLS-1$
			}

			// Parse graphical considerations
			if (attribute.getAttribute("bold") != null) { //$NON-NLS-1$
				a.setBold(Boolean.parseBoolean(attribute.getAttribute("bold"))); //$NON-NLS-1$
				LOGGER.finer("Ajout de l'indicateur de gras pour l'attribut : " + a.getName()); //$NON-NLS-1$
			}
			if (attribute.getAttribute("italic") != null) { //$NON-NLS-1$
				a.setItalic(Boolean.parseBoolean(attribute.getAttribute("italic"))); //$NON-NLS-1$
				LOGGER.finer("Add italic state for the attribute : " + a.getName()); //$NON-NLS-1$
			}
			if (attribute.getAttribute("size") != null) { //$NON-NLS-1$
				a.setSize(attribute.getAttribute("size")); //$NON-NLS-1$
				LOGGER.finer("Add bold state for the attribute : " + a.getName()); //$NON-NLS-1$
			}

			// Add the attribute to the parent's list
			element.addAttribute(a);
		}
	}

	/**
	 * Extract all elements attributes and add them to the parent description
	 * @param element Formalism element that is currently parsed. (considered as the parent)
	 * @param description Element description. This description may contains attributes
	 */
	private void buildComputedAttributes(ElementFormalism element, IConfigurationElement description) {
		// Browse all attributes from the element description
		IConfigurationElement[] computedAttributes = description.getChildren("ComputedAttribute"); //$NON-NLS-1$
		for (IConfigurationElement computedAttribute : computedAttributes) {

			String attributeName = computedAttribute.getAttribute("name"); //$NON-NLS-1$
			String defaultValue = computedAttribute.getAttribute("default"); //$NON-NLS-1$
			boolean defaultValueDrawable = true;
			
			// Parse the default value
			if (defaultValue != null) {	
				if (computedAttribute.getAttribute("displayed_default") != null) { //$NON-NLS-1$
					defaultValueDrawable = Boolean.parseBoolean(computedAttribute.getAttribute("displayed_default")); //$NON-NLS-1$
				}
			}
			
			// Attribute Formatter
			Object attributeFormatter = null;
			ComputedAttributeFormalism ca;
			try {
				attributeFormatter = computedAttribute.createExecutableExtension("formatter"); //$NON-NLS-1$
				ca = new ComputedAttributeFormalism(attributeName, defaultValue, defaultValueDrawable, attributeFormatter.getClass());
			} catch (CoreException e) {
				e.printStackTrace();
				LOGGER.warning("Something went wrong when we tried to add the figure to the element : " + element.getName() + " ( " + e.getMessage() + " )"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				ca = new ComputedAttributeFormalism(attributeName, defaultValue, defaultValueDrawable);
			}
			
			// Build the Computed Attribute Object
			LOGGER.finer("Build the computed attribute " + computedAttribute.getName() + " for the element : " + element.getName()); //$NON-NLS-1$ //$NON-NLS-2$

			// Set default location (delta_x and delta_y)
			if (computedAttribute.getAttribute("delta_x") != null) { //$NON-NLS-1$
				ca.setXDelta(Integer.parseInt(computedAttribute.getAttribute("delta_x"))); //$NON-NLS-1$
				LOGGER.finer("Add relative location (X) for the computed attribute : " + computedAttribute.getName()); //$NON-NLS-1$
			}
			
			if (computedAttribute.getAttribute("delta_y") != null) { //$NON-NLS-1$
				ca.setYDelta(Integer.parseInt(computedAttribute.getAttribute("delta_y"))); //$NON-NLS-1$
				LOGGER.finer("Add relative location (Y) for the computed attribute : " + computedAttribute.getName()); //$NON-NLS-1$
			}

			// Parse graphical considerations
			if (computedAttribute.getAttribute("bold") != null) { //$NON-NLS-1$
				ca.setBold(Boolean.parseBoolean(computedAttribute.getAttribute("bold"))); //$NON-NLS-1$
				LOGGER.finer("Add bold state for the attribute : " + computedAttribute.getName()); //$NON-NLS-1$
			}
			if (computedAttribute.getAttribute("italic") != null) { //$NON-NLS-1$
				ca.setItalic(Boolean.parseBoolean(computedAttribute.getAttribute("italic"))); //$NON-NLS-1$
				LOGGER.finer("Add italic state for the attribute : " + ca.getName()); //$NON-NLS-1$
			}
			if (computedAttribute.getAttribute("size") != null) { //$NON-NLS-1$
				ca.setSize(computedAttribute.getAttribute("size")); //$NON-NLS-1$
				LOGGER.finer("Add font size for the attribute : " + ca.getName()); //$NON-NLS-1$
			}

			// Add the attribute to the parent's list
			element.addComputedAttribute(ca);
		}
	}

	/**
	 * Extract graphical descriptions for elements.<br>
	 * <b>The first available graphical description is the default one.</b></br>
	 * Others are considered as alternatives.
	 * @param element Formalism element that is currently parsed. (considered as the parent)
	 * @param description Element description. This description may contains attributes
	 */
	private void buildGraphicalDescription(ElementFormalism element, IConfigurationElement description) {
		// Browse graphical description for the element
		IConfigurationElement[] graphicalDescriptions = description.getChildren("GraphicInfo"); //$NON-NLS-1$

		for (IConfigurationElement graphicalDescription : graphicalDescriptions) {

			// Build a GraphicalDescription object
			GraphicalDescription gd = new GraphicalDescription(Boolean.parseBoolean(graphicalDescription.getAttribute("palettable")), Boolean.parseBoolean(graphicalDescription.getAttribute("drawable"))); //$NON-NLS-1$ //$NON-NLS-2$
			LOGGER.finer("Build the graphical decription for the element : " + element.getName()); //$NON-NLS-1$

			if (graphicalDescription.getAttribute("paletteName") != null) { //$NON-NLS-1$
				gd.setPaletteName(graphicalDescription.getAttribute("paletteName")); //$NON-NLS-1$
				LOGGER.finest("Add a palette name for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("description") != null) { //$NON-NLS-1$
				gd.setDescription(graphicalDescription.getAttribute("description")); //$NON-NLS-1$
				LOGGER.finest("Add a description for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("fill") != null) { //$NON-NLS-1$
				gd.setFilled(Boolean.parseBoolean(graphicalDescription.getAttribute("fill"))); //$NON-NLS-1$
				LOGGER.finest("Add a fill state for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("height") != null) { //$NON-NLS-1$
				gd.setHeight(graphicalDescription.getAttribute("height")); //$NON-NLS-1$
				LOGGER.finest("Add the height of the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("width") != null) { //$NON-NLS-1$
				gd.setWidth(graphicalDescription.getAttribute("width")); //$NON-NLS-1$
				LOGGER.finest("Add the width of the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("icon16px") != null) { //$NON-NLS-1$
				gd.setIcon16px(graphicalDescription.getAttribute("icon16px")); //$NON-NLS-1$
				LOGGER.finest("Add the 16px icon for the element : " + element.getName()); //$NON-NLS-1$
			}
			if (graphicalDescription.getAttribute("icon24px") != null) { //$NON-NLS-1$
				gd.setIcon24px(graphicalDescription.getAttribute("icon24px")); //$NON-NLS-1$
				LOGGER.finest("Add the 24px icon for the element : " + element.getName()); //$NON-NLS-1$
			}

			// Associate a graphical figure description (class) to the element
			if (graphicalDescription.getAttribute("associatedFigure") != null) { //$NON-NLS-1$
				try {
					Object associatedFigure = graphicalDescription.createExecutableExtension("associatedFigure"); //$NON-NLS-1$
					gd.setAssociatedFigure(associatedFigure.getClass());
					LOGGER.finest("Add the associated figure for the element : " + element.getName()); //$NON-NLS-1$
				} catch (CoreException e) {
					LOGGER.warning("Something went wrong when we tried to add the figure to the element : " + element.getName() + " ( " + e.getMessage() + " )"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}

			// Add the graphical description to the parent's list
			element.addGraphicalDescription(gd);
		}
	}

	/**
	 * Returns a formalism from its name or throws an {@link IllegalArgumentException} is no formalism is found.
	 * @param name The name of the formalism
	 * @return The formalism {@link IFormalism}
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 */
	public IFormalism getFormalismByName(String name) throws IllegalArgumentException {
		for (IFormalism form : formalisms) {
			if (name.toLowerCase().equals(form.getName().toLowerCase())) {
				return form;
			}
		}
		LOGGER.warning("This formalism is not known : '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("This formalism is not known : '" + name + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Returns a formalism from its id or throws an {@link IllegalArgumentException} is no formalism is found.
	 * @param id The id of the formalism
	 * @return The formalism {@link IFormalism}
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 */
	public IFormalism getFormalismById(String id) throws IllegalArgumentException {
		for (IFormalism form : formalisms) {
			if (form.getId().equals(id)) {
				return form;
			}
		}
		LOGGER.warning("This formalism is not known : '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("This formalism is not known : '" + id + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Returns a formalism from its FKName or throws an {@link IllegalArgumentException} is no formalism is found.
	 * @see IFormalism
	 * @param fkName The FKName of the formalism
	 * @return The formalism {@link IFormalism}
	 * @throws IllegalArgumentException If no such formalism exists in FormalismManager list.
	 * TODO: This method is not clear...
	 * @deprecated
	 */
	public IFormalism getFormalismByFkName(String fkName) throws IllegalArgumentException {
		if (fkName.toLowerCase().equals("ami-net")) { //$NON-NLS-1$
			for (IFormalism form : formalisms) {
				if ("Colored Petri Net".toLowerCase().equals(form.getName().toLowerCase())) { //$NON-NLS-1$
					return form;
				}
			}
		}
		for (IFormalism form : formalisms) {
			if (fkName.toLowerCase().equals(form.getFKName().toLowerCase())) {
				return form;
			}
		}
		LOGGER.warning("This formalism is not known : '" + fkName + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		throw new IllegalArgumentException("This formalism is not known : '" + fkName + "'"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @return The list of available formalisms
	 */
	public List<IFormalism> getListOfFormalisms() {
		return formalisms;
	}
}
