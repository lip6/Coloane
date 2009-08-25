package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.IGraphChecker;
import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Gestionnaire de checkers.
 * 
 * @author Florian David
 */
public class CheckerManager {
	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** The name of the extension point that contains formalisms definition */
	private static final String EXTENSION_ID = "fr.lip6.move.coloane.core.formalisms"; //$NON-NLS-1$
	
	/** The instance */
	private static CheckerManager instance;
	
	/** Constructor */
	private CheckerManager() { }
	
	/** @return The CheckerManager instance */
	public static synchronized CheckerManager getInstance() {
		if (instance == null) { instance = new CheckerManager(); }
		return instance;
	}
	
	/**
	 * Build a specific checker thanks to its XML definition.
	 * 
	 * @param description Description loads from the extension point.
	 * @return the constructed checker.
	 */
	private Checker buildChecker(IConfigurationElement description) {
		LOGGER.finer("Création d'un checker défini par le formalisme "+description.getAttribute("name")); //$NON-NLS-1$ //$NON-NLS-2$
		Checker checker = new Checker();
		
		IConfigurationElement[] xmlDescription = description.getChildren("XmlDescription"); //$NON-NLS-1$
		
		IConfigurationElement[] graphes = xmlDescription[0].getChildren("Graph"); //$NON-NLS-1$
		IConfigurationElement graph = graphes[0];
		
		IConfigurationElement[] graphConditions = graph.getChildren("GraphChecker"); //$NON-NLS-1$
		// On récupère les checkers sur le graphe.
		for (IConfigurationElement condition : graphConditions) {
			try {
				IGraphChecker graphCondition = (IGraphChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
				String message = condition.getAttribute("message"); //$NON-NLS-1$
				Integer severity;
				
				if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
					severity = IMarker.SEVERITY_ERROR;
				} else {
					severity = IMarker.SEVERITY_WARNING;
				}
				
				GraphChecker graphChecker = new GraphChecker(graphCondition, message, severity);
				checker.addGraphChecker(graphChecker);
				LOGGER.finer("Ajout d'un GraphChecker sur l'arc '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			} catch (CoreException core) {
				LOGGER.warning("Erreur dans la définition d'un GraphChecker ! Une condition a été mal définie: "+core.getMessage()); //$NON-NLS-1$
			}
			//new GraphChecker
		}
		this.buildAttributesCheckers(graph, checker);


		IConfigurationElement[] nodes = graph.getChildren("Node"); //$NON-NLS-1$
		// On récupère ensuite les checkers sur les nodes.
		for (IConfigurationElement node : nodes){
			IConfigurationElement[] nodeConditions = node.getChildren("NodeChecker"); //$NON-NLS-1$
			for (IConfigurationElement condition : nodeConditions) {
				try {
					INodeChecker nodeCondition = (INodeChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
					String message = condition.getAttribute("message"); //$NON-NLS-1$
					Integer severity;
					
					if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
						severity = IMarker.SEVERITY_ERROR;
					} else {
						severity = IMarker.SEVERITY_WARNING;
					}
					
					NodeChecker nodeChecker = new NodeChecker(nodeCondition, message, severity);
					checker.addNodeChecker(node.getAttribute("name"), nodeChecker); //$NON-NLS-1$
					LOGGER.finer("Ajout d'un NodeChecker sur le noeud '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				} catch (CoreException core) {
					LOGGER.warning("Erreur dans la définition d'un NodeChecker ! Une condition a été mal définie: "+core.getMessage()); //$NON-NLS-1$
				}
			}
			this.buildAttributesCheckers(node, checker);
		}
		
		
		
		IConfigurationElement[] arcs = graph.getChildren("Arc"); //$NON-NLS-1$
		// Et enfin les checkers sur les arcs.
		for (IConfigurationElement arc : arcs){
			IConfigurationElement[] arcConditions = arc.getChildren("ArcChecker"); //$NON-NLS-1$
			for (IConfigurationElement condition : arcConditions) {
				try {
					IArcChecker arcCondition = (IArcChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
					String message = condition.getAttribute("message"); //$NON-NLS-1$
					Integer severity;
					
					if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
						severity = IMarker.SEVERITY_ERROR;
					} else {
						severity = IMarker.SEVERITY_WARNING;
					}
					
					ArcChecker arcChecker = new ArcChecker(arcCondition, message, severity);
					checker.addArcChecker(arc.getAttribute("name"), arcChecker); //$NON-NLS-1$
					LOGGER.finer("Ajout d'un ArcChecker sur l'arc '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				} catch (CoreException core) {
					LOGGER.warning("Erreur dans la définition d'un ArcChecker ! Une condition a été mal définie: "+core.getMessage()); //$NON-NLS-1$
				}
			}
			this.buildAttributesCheckers(arc, checker);
		}	
		
		
		return checker;
	}

	/**
	 * Build the attribute checkers.
	 * 
	 * @param description Description loads from the extension point.
	 * @param checker the checker under construction.
	 */
	private void buildAttributesCheckers(IConfigurationElement description, Checker checker) {
		IConfigurationElement[] attributes = description.getChildren("Attribute"); //$NON-NLS-1$
		for (IConfigurationElement attribute : attributes) {
			IConfigurationElement[] conditions = attribute.getChildren("AttributeChecker"); //$NON-NLS-1$
			for (IConfigurationElement condition : conditions) {
				try {
					IAttributeChecker attributeCondition = (IAttributeChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
					String message = condition.getAttribute("message"); //$NON-NLS-1$
					Integer severity;
					
					if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
						severity = IMarker.SEVERITY_ERROR;
					} else {
						severity = IMarker.SEVERITY_WARNING;
					}

					AttributeChecker attributeChecker = new AttributeChecker(attributeCondition, message, severity);
					
					if (description.getName().equals("Arc")) { //$NON-NLS-1$
						checker.addArcAttributeChecker(description.getAttribute("name"), attribute.getAttribute("name"), attributeChecker); //$NON-NLS-1$ //$NON-NLS-2$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '"+attribute.getAttribute("name")+"' de l'arc '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					} else if (description.getName().equals("Node")) { //$NON-NLS-1$
						checker.addNodeAttributeChecker(description.getAttribute("name"), attribute.getAttribute("name"), attributeChecker); //$NON-NLS-1$ //$NON-NLS-2$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '"+attribute.getAttribute("name")+"' du noeud '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					} else if (description.getName().equals("Graph")) { //$NON-NLS-1$
						checker.addGraphAttributeChecker(attribute.getAttribute("name"), attributeChecker); //$NON-NLS-1$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '"+attribute.getAttribute("name")+"' du graphe '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					}
				} catch (CoreException core) {
					LOGGER.warning("Erreur dans la définition d'un AttributeChecker ! Une condition a été mal définie: "+core.getMessage()); //$NON-NLS-1$
				}
			}
		}
	}
	
	/**
	 * Method call by {@link CheckableCmd} when an arc need to be checked.<br>
	 * It checks severals others elements of the graph because when an arc need to be check, we don't know the reason.<br>
	 * <u>Example :</ul><br>
	 * If an arc is added, we need to check the arc itself and the source and target of the arc.
	 * @param checker the checker used to check.
	 * @param graph the graph which contains the arc.
	 * @param arc the arc to check.
	 */
	public void checkArc(Checker checker, IResource resource, IGraph graph, IArc arc) {
		// First we check the arc itself.
		// Deleting it markers.
		MarkerManager.deleteElementMarkers(resource, MarkerManager.ARC_MARKER, arc);
		MarkerManager.deleteElementMarkers(resource, MarkerManager.ARC_ATTRIBUTE_MARKER, arc);

		// If the arc still belongs to the graph, we check it.
		if (graph.getArc(arc.getId()) != null) {
			checkIArc(checker, arc, resource);
			checkIElementAttributes(checker, arc, MarkerManager.ARC_ATTRIBUTE_MARKER, resource);
		}

		// The same thing for source and target nodes.
		List<INode> arcNodes = new ArrayList<INode>(2);
		arcNodes.add(arc.getSource());
		arcNodes.add(arc.getTarget());

		MarkerManager.deleteElementMarkers(resource, MarkerManager.NODE_MARKER, arc.getSource());
		MarkerManager.deleteElementMarkers(resource, MarkerManager.NODE_MARKER, arc.getTarget());

		for (INode node : arcNodes) {
			if (graph.getNode(node.getId()) != null) {
				checkINode(checker, node, resource);
			}
		}
	}

	/**
	 * Method call by {@link CheckableCmd} when a node need to be checked.<br>
	 * It checks severals others elements of the graph because when a node need to be check, we don't know the reason.<br>
	 * <u>Example :</ul><br>
	 * If a node is deleted, we need to check incoming arcs and it source and outgoing arcs and it target.
	 * @param checker the checker used to check.
	 * @param resource the resource file where markers are created.
	 * @param graph the graph which contains the node.
	 * @param node the node to check.
	 */
	public void checkNode(Checker checker, IResource resource, IGraph graph, INode node) {
		List<IArc> inArcs = node.getIncomingArcs();
		List<IArc> outArcs = node.getOutgoingArcs();

		// Suppression des markers du noeud et des attributs du noeud.
		MarkerManager.deleteElementMarkers(resource, MarkerManager.NODE_MARKER, node);
		MarkerManager.deleteElementMarkers(resource, MarkerManager.NODE_ATTRIBUTE_MARKER, node);
		// Supression des markers des arcs et des attributs des arcs reliés au noeud ainsi que les markers du noeud situé à l'autre extrémité de chaque arc.
		for (IArc arc : inArcs) {
			MarkerManager.deleteElementMarkers(resource, MarkerManager.ARC_MARKER, arc);
			MarkerManager.deleteElementMarkers(resource, MarkerManager.ARC_ATTRIBUTE_MARKER, arc);
			// Pour les arcs entrants, on supprime les markers du noeud source.
			MarkerManager.deleteElementMarkers(resource, MarkerManager.NODE_MARKER, arc.getSource());
		}
		for (IArc arc : outArcs) {
			MarkerManager.deleteElementMarkers(resource, MarkerManager.ARC_MARKER, arc);
			MarkerManager.deleteElementMarkers(resource, MarkerManager.ARC_ATTRIBUTE_MARKER, arc);
			// Pour les arcs sortants, on supprime les markers du noeud target.
			MarkerManager.deleteElementMarkers(resource, MarkerManager.NODE_MARKER, arc.getTarget());
		}

		// Si le noeud appartient encore au graphe,
		if (graph.getNode(node.getId()) != null) {
			// On vérifie ses markers et ceux de ses attributs.
			checkINode(checker, node, resource);
			checkIElementAttributes(checker, node, MarkerManager.NODE_ATTRIBUTE_MARKER, resource);

			// Ensuite on vérifie les markers des arcs entrants et de leurs attributs. 
			for (IArc arc : inArcs) {
				if (graph.getArc(arc.getId()) != null) {
					checkIArc(checker, arc, resource);
					checkIElementAttributes(checker, arc, MarkerManager.ARC_ATTRIBUTE_MARKER, resource);
				}
			}

			// On vérifie les markers des arcs sortants et les markers des attributs de ces arcs.
			for (IArc arc : outArcs ) {
				if (graph.getArc(arc.getId()) != null) {
					checkIArc(checker, arc, resource);
					checkIElementAttributes(checker, arc, MarkerManager.ARC_ATTRIBUTE_MARKER, resource);
				}
			}
		}

		// Pour finir, on verifie les nodes target des arc sortants et les nodes source des arcs entrants. 
		for (IArc arc : inArcs) {
			INode nodeSource = arc.getSource();
			if (graph.getNode(nodeSource.getId()) != null) {
				checkINode(checker, nodeSource, resource);
			}
		}
		for (IArc arc : outArcs) {
			INode nodeTarget = arc.getTarget();
			if (graph.getNode(nodeTarget.getId()) != null) {
				checkINode(checker, nodeTarget, resource);
			}
		}
	}

	/**
	 * Apply the graph checkers to the specified graph and its attributes.
	 * @param checker the checker used to check.
	 * @param resource the resource file where markers are created.
	 * @param graph the graph to check.
	 */
	public void checkGraph(Checker checker, IResource resource, IGraph graph) {
		MarkerManager.deleteElementMarkers(resource, MarkerManager.GRAPH_MARKER, graph);
		MarkerManager.deleteElementMarkers(resource, MarkerManager.GRAPH_ATTRIBUTE_MARKER, graph);
		
		for (GraphChecker graphChecker : checker.getGraphCheckers()) {
			if (! graphChecker.check(graph)) {
				MarkerManager.createMarker(resource, MarkerManager.GRAPH_MARKER, graphChecker.getMessage(), graph, graphChecker.getSeverity());
			}
		}
		checkIElementAttributes(checker, graph, MarkerManager.GRAPH_ATTRIBUTE_MARKER, resource);
	}


	/**
	 * Apply the node checkers to the specified node.
	 * @param checker the checker associate to the session which the node belong.
	 * @param node the node to check.
	 * @param resource the resource file where markers are created.
	 */
	private void checkINode(Checker checker, INode node, IResource resource) {
		String nodeFormalism = node.getNodeFormalism().getName();

		for (NodeChecker nodeChecker : checker.getNodeCheckers(nodeFormalism)) {
			if (! nodeChecker.check(node)) {
				MarkerManager.createMarker(resource, MarkerManager.NODE_MARKER, nodeChecker.getMessage(), node, nodeChecker.getSeverity());
			}
		}
	}


	/**
	 * Apply the arc checkers to the specified arc.
	 * @param checker the checker associate to the session which the arc belong.
	 * @param arc the arc to check.
	 * @param resource the resource file where markers are created.
	 */
	private void checkIArc(Checker checker, IArc arc, IResource resource) {
		String arcFormalism = arc.getArcFormalism().getName();
		for (ArcChecker arcChecker : checker.getArcCheckers(arcFormalism)) {
			if (! arcChecker.check(arc)) {
				MarkerManager.createMarker(resource, MarkerManager.ARC_MARKER, arcChecker.getMessage(), arc, arcChecker.getSeverity());
			}
		}
	}


	/**
	 * Apply the attributes checkers to the attributes of the specified element.
	 * @param checker the checker associate to the session which attributes belong.
	 * @param element the element from where attributes come.
	 * @param markerType the type of marker to create.
	 * @param resource the resource file where markers are created.
	 */
	private void checkIElementAttributes(Checker checker, IElement element, String markerType, IResource resource) {
		for (IAttribute attribute : element.getAttributes()) {
			for (AttributeChecker attributeChecker : checker.getGraphAttributeCheckers(attribute.getName())) {
				if (! attributeChecker.check(attribute.getValue())) {
					MarkerManager.createMarker(resource, markerType, attributeChecker.getMessage(), element, attributeChecker.getSeverity());
				}
			}
		}
	}


	/**
	 * Check a whole graph.
	 * @param checker the checker used to check.
	 * @param resource the resource file where markers are created.
	 * @param graph the graph to check.
	 */
	public void checkAll(Checker checker, IResource resource, IGraph graph) {
		LOGGER.finer("Check intégrale du graph de la session "+resource.getFullPath().toString()); //$NON-NLS-1$

		// Deleting all the markers.
		MarkerManager.deleteAllMarkers(resource);
		
		// Arcs checking.
		for (IArc arc : graph.getArcs()) {
			checkIArc(checker, arc, resource);
			checkIElementAttributes(checker, arc, MarkerManager.ARC_ATTRIBUTE_MARKER, resource);
		}

		// Node checking.
		for (INode node : graph.getNodes()) {
			checkINode(checker, node, resource);
			checkIElementAttributes(checker, node, MarkerManager.NODE_ATTRIBUTE_MARKER, resource);
		}

		// Graph checking.
		checkGraph(checker, resource, graph);
	}


	/**
	 * Initialize and attach a checker to a session.
	 * @param session the session where the checker is set.
	 * @return the created checker.
	 */
	public Checker setChecker(ISession session) {
		String formalismId = session.getGraph().getFormalism().getId();
		
		IConfigurationElement[] checkers = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
		for (int i = 0; i < checkers.length; i++) {
			// If the current formalism name from the extension point matches the speciefied one,
			// the checker is built and linked to the session.
			if (checkers[i].getAttribute("id").equals(formalismId)) { //$NON-NLS-1$
				Checker checker = buildChecker(checkers[i]);
				LOGGER.finer("Attachement du checker à la session "+session.getName()); //$NON-NLS-1$
				session.setChecker(checker);
				return checker;
			}
		}
		return null;
	}
}
