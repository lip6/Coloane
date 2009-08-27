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
 * Checkers manager.
 * 
 * @author Florian David
 */
public final class CheckerManager {
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
		LOGGER.finer("Création d'un checker défini par le formalisme " + description.getAttribute("name")); //$NON-NLS-1$ //$NON-NLS-2$
		// Checker creation
		Checker checker = new Checker();

		IConfigurationElement[] xmlDescription = description.getChildren("XmlDescription"); //$NON-NLS-1$
		IConfigurationElement[] graphes = xmlDescription[0].getChildren("Graph"); //$NON-NLS-1$
		IConfigurationElement graph = graphes[0];

		// Getting graph checkers description
		IConfigurationElement[] graphConditions = graph.getChildren("GraphChecker"); //$NON-NLS-1$
		for (IConfigurationElement condition : graphConditions) {
			// For each graph checker,
			try {
				// we instantiate the IGraphChecker class from the "class_condition" attribute defined in the extension point.
				IGraphChecker graphCondition = (IGraphChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
				String message = condition.getAttribute("message"); //$NON-NLS-1$
				Integer severity;

				// then we retrieve the marker severity
				if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
					severity = IMarker.SEVERITY_ERROR;
				} else {
					severity = IMarker.SEVERITY_WARNING;
				}

				// and finally constructs the GraphChecker and add it to the Checker
				GraphChecker graphChecker = new GraphChecker(graphCondition, message, severity);
				checker.addGraphChecker(graphChecker);
				LOGGER.finer("Ajout d'un GraphChecker sur l'arc '" + description.getAttribute("name") + "'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			} catch (CoreException core) {
				LOGGER.warning("Erreur dans la définition d'un GraphChecker ! Une condition a été mal définie: " + core.getMessage()); //$NON-NLS-1$
			}
		}
		// Building AttributeCheckers on graph attributes
		this.buildAttributesCheckers(graph, checker);


		// Getting nodes description
		IConfigurationElement[] nodes = graph.getChildren("Node"); //$NON-NLS-1$
		for (IConfigurationElement node : nodes) {
			// For each node description, we get all node checkers description
			IConfigurationElement[] nodeConditions = node.getChildren("NodeChecker"); //$NON-NLS-1$
			for (IConfigurationElement condition : nodeConditions) {
				// For each node checker,
				try {
					// we instantiate the INodeChecker class from the "class_condition" attribute defined in the extension point.
					INodeChecker nodeCondition = (INodeChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
					String message = condition.getAttribute("message"); //$NON-NLS-1$
					Integer severity;

					// then we retrieve the marker severity
					if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
						severity = IMarker.SEVERITY_ERROR;
					} else {
						severity = IMarker.SEVERITY_WARNING;
					}

					// and finally constructs the NodeChecker and add it to the Checker
					NodeChecker nodeChecker = new NodeChecker(nodeCondition, message, severity);
					checker.addNodeChecker(node.getAttribute("name"), nodeChecker); //$NON-NLS-1$
					LOGGER.finer("Ajout d'un NodeChecker sur le noeud '" + description.getAttribute("name") + "'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				} catch (CoreException core) {
					LOGGER.warning("Erreur dans la définition d'un NodeChecker ! Une condition a été mal définie: " + core.getMessage()); //$NON-NLS-1$
				}
			}
			// Building AttributeCheckers on node attributes
			this.buildAttributesCheckers(node, checker);
		}


		// Getting arcs description
		IConfigurationElement[] arcs = graph.getChildren("Arc"); //$NON-NLS-1$
		for (IConfigurationElement arc : arcs) {
			// For each arc description, we get all arc checkers description
			IConfigurationElement[] arcConditions = arc.getChildren("ArcChecker"); //$NON-NLS-1$
			for (IConfigurationElement condition : arcConditions) {
				// For each arc checker,
				try {
					// we instantiate the IArcChecker class from the "class_condition" attribute defined in the extension point.
					IArcChecker arcCondition = (IArcChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
					String message = condition.getAttribute("message"); //$NON-NLS-1$
					Integer severity;

					// then we retrieve the marker severity
					if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
						severity = IMarker.SEVERITY_ERROR;
					} else {
						severity = IMarker.SEVERITY_WARNING;
					}

					// and finally constructs the ArcChecker and add it to the Checker
					ArcChecker arcChecker = new ArcChecker(arcCondition, message, severity);
					checker.addArcChecker(arc.getAttribute("name"), arcChecker); //$NON-NLS-1$
					LOGGER.finer("Ajout d'un ArcChecker sur l'arc '" + description.getAttribute("name") + "'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				} catch (CoreException core) {
					LOGGER.warning("Erreur dans la définition d'un ArcChecker ! Une condition a été mal définie: " + core.getMessage()); //$NON-NLS-1$
				}
			}
			// Building AttributeCheckers on arc attributes
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
		// First we get all attributes.
		IConfigurationElement[] attributes = description.getChildren("Attribute"); //$NON-NLS-1$
		for (IConfigurationElement attribute : attributes) {
			// For each attribute description, we get all attribute checkers description
			IConfigurationElement[] conditions = attribute.getChildren("AttributeChecker"); //$NON-NLS-1$
			for (IConfigurationElement condition : conditions) {
				// For each attribute checker,
				try {
					// we instantiate the IAttributeChecker class from the "class_condition" attribute defined in the extension point.
					IAttributeChecker attributeCondition = (IAttributeChecker) condition.createExecutableExtension("class_condition"); //$NON-NLS-1$
					String message = condition.getAttribute("message"); //$NON-NLS-1$
					Integer severity;

					// then we retrieve the marker severity
					if (condition.getAttribute("severity").equals("Error")) { //$NON-NLS-1$ //$NON-NLS-2$
						severity = IMarker.SEVERITY_ERROR;
					} else {
						severity = IMarker.SEVERITY_WARNING;
					}

					// and finally constructs the ArcChecker and add it to the correct attribute checkers list in the checker.
					AttributeChecker attributeChecker = new AttributeChecker(attributeCondition, message, severity);

					if (description.getName().equals("Arc")) { //$NON-NLS-1$
						// Adding the attributeChecker to arcAttributeChecker
						checker.addArcAttributeChecker(description.getAttribute("name"), attribute.getAttribute("name"), attributeChecker); //$NON-NLS-1$ //$NON-NLS-2$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '" + attribute.getAttribute("name") + "' de l'arc '" + description.getAttribute("name") + "'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					} else if (description.getName().equals("Node")) { //$NON-NLS-1$
						// Adding the attributeChecker to nodeAttributeChecker
						checker.addNodeAttributeChecker(description.getAttribute("name"), attribute.getAttribute("name"), attributeChecker); //$NON-NLS-1$ //$NON-NLS-2$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '" + attribute.getAttribute("name") + "' du noeud '" + description.getAttribute("name") + "'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					} else if (description.getName().equals("Graph")) { //$NON-NLS-1$
						// Adding the attributeChecker to graphAttributeChecker
						checker.addGraphAttributeChecker(attribute.getAttribute("name"), attributeChecker); //$NON-NLS-1$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '" + attribute.getAttribute("name") + "' du graphe '" + description.getAttribute("name") + "'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					}
				} catch (CoreException core) {
					LOGGER.warning("Erreur dans la définition d'un AttributeChecker ! Une condition a été mal définie: " + core.getMessage()); //$NON-NLS-1$
				}
			}
		}
	}
	
	/**
	 * Method call by {@link CheckableCmd} when an arc need to be checked.<br>
	 * It checks severals others elements of the graph because when an arc need to be check, we don't know the reason.<br>
	 * <u>Example :</u><br>
	 * If an arc is added, we need to check the arc itself and the source and target of the arc.
	 * @param checker the checker used to check.
	 * @param resource the resource file where markers are created.
	 * @param graph the graph which contains the arc.
	 * @param arc the arc to check.
	 */
	public void checkArc(Checker checker, IResource resource, IGraph graph, IArc arc) {
		// First we check the arc itself.
		// Deleting its markers.
		MarkerManager.deleteArcMarkers(resource, arc);
		MarkerManager.deleteArcAttributeMarkers(resource, arc);

		// If the arc still belongs to the graph, we check it.
		if (graph.getArc(arc.getId()) != null) {
			checkIArc(checker, arc, resource);
			checkIElementAttributes(checker, arc, resource);
		}

		// The same thing for source and target nodes.
		List<INode> arcNodes = new ArrayList<INode>(2);
		arcNodes.add(arc.getSource());
		arcNodes.add(arc.getTarget());

		MarkerManager.deleteNodeMarkers(resource, arc.getSource());
		MarkerManager.deleteNodeMarkers(resource, arc.getTarget());

		for (INode node : arcNodes) {
			if (graph.getNode(node.getId()) != null) {
				checkINode(checker, node, resource);
			}
		}
	}

	/**
	 * Method call by {@link CheckableCmd} when a node need to be checked.<br>
	 * It checks severals others elements of the graph because when a node need to be check, we don't know the reason.<br>
	 * <u>Example :</u><br>
	 * If a node is deleted, we need to check incoming arcs and it source and outgoing arcs and it target.
	 * @param checker the checker used to check.
	 * @param resource the resource file where markers are created.
	 * @param graph the graph which contains the node.
	 * @param node the node to check.
	 */
	public void checkNode(Checker checker, IResource resource, IGraph graph, INode node) {
		List<IArc> inArcs = node.getIncomingArcs();
		List<IArc> outArcs = node.getOutgoingArcs();

		// We delete the node markers and its attributes markers,
		MarkerManager.deleteNodeMarkers(resource, node);
		MarkerManager.deleteNodeAttributeMarkers(resource, node);

		for (IArc arc : inArcs) {
			// all attributes markers and arcs markers of its incoming arcs
			MarkerManager.deleteArcMarkers(resource, arc);
			MarkerManager.deleteArcAttributeMarkers(resource, arc);
			// and node markers of incoming arc node source
			MarkerManager.deleteNodeMarkers(resource, arc.getSource());
		}
		for (IArc arc : outArcs) {
			// all attributes markers and arcs markers of its outgoing arcs
			MarkerManager.deleteArcMarkers(resource, arc);
			MarkerManager.deleteArcAttributeMarkers(resource, arc);
			// and node markers of outgoing arc node target.
			MarkerManager.deleteNodeMarkers(resource, arc.getSource());
		}

		// If the node still belongs to the graph,
		if (graph.getNode(node.getId()) != null) {
			// We check its markers and those of its attributes
			checkINode(checker, node, resource);
			checkIElementAttributes(checker, node, resource);

			// Then we check markers and attributes markers of its incoming arcs.
			for (IArc arc : inArcs) {
				if (graph.getArc(arc.getId()) != null) {
					checkIArc(checker, arc, resource);
					checkIElementAttributes(checker, arc, resource);
				}
			}

			// Then we check markers and attributes markers of its outgoing arcs.
			for (IArc arc : outArcs) {
				if (graph.getArc(arc.getId()) != null) {
					checkIArc(checker, arc, resource);
					checkIElementAttributes(checker, arc, resource);
				}
			}
		}

		// At last, we check node markers of incoming arc node source
		for (IArc arc : inArcs) {
			INode nodeSource = arc.getSource();
			if (graph.getNode(nodeSource.getId()) != null) {
				checkINode(checker, nodeSource, resource);
			}
		}
		// and node markers of outgoing arc node target.
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
		MarkerManager.deleteGraphMarkers(resource);
		MarkerManager.deleteGraphAttributeMarkers(resource);

		for (GraphChecker graphChecker : checker.getGraphCheckers()) {
			if (!graphChecker.check(graph)) {
				MarkerManager.createGraphMarker(resource, graphChecker.getMessage(), graph, graphChecker.getSeverity());
			}
		}
		checkIElementAttributes(checker, graph, resource);
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
			if (!nodeChecker.check(node)) {
				MarkerManager.createNodeMarker(resource, nodeChecker.getMessage(), node, nodeChecker.getSeverity());
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
			if (!arcChecker.check(arc)) {
				MarkerManager.createArcMarker(resource, arcChecker.getMessage(), arc, arcChecker.getSeverity());
			}
		}
	}


	/**
	 * Apply the attributes checkers to the attributes of the specified element.
	 * @param checker the checker associate to the session which attributes belong.
	 * @param element the element from where attributes come.
	 * @param resource the resource file where markers are created.
	 */
	private void checkIElementAttributes(Checker checker, IElement element, IResource resource) {
		for (IAttribute attribute : element.getAttributes()) {
			if (element instanceof INode) {
				INode node = (INode) element;
				for (AttributeChecker attributeChecker : checker.getNodeAttributeCheckers(node.getNodeFormalism().getName(), attribute.getName())) {
					if (!attributeChecker.check(attribute.getValue())) {
						MarkerManager.createNodeAttributeMarker(resource, attributeChecker.getMessage(), element, attributeChecker.getSeverity());
					}
				}
			} else if (element instanceof IArc) {
				IArc arc = (IArc) element;
				for (AttributeChecker attributeChecker : checker.getArcAttributeCheckers(arc.getArcFormalism().getName(), attribute.getName())) {
					if (!attributeChecker.check(attribute.getValue())) {
						MarkerManager.createArcAttributeMarker(resource, attributeChecker.getMessage(), element, attributeChecker.getSeverity());
					}
				}
			} else if (element instanceof IGraph) {
				for (AttributeChecker attributeChecker : checker.getGraphAttributeCheckers(attribute.getName())) {
					if (!attributeChecker.check(attribute.getValue())) {
						MarkerManager.createGraphAttributeMarker(resource, attributeChecker.getMessage(), element, attributeChecker.getSeverity());
					}
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
		LOGGER.finer("Check intégrale du graph de la session " + resource.getFullPath().toString()); //$NON-NLS-1$

		// Deleting all the markers.
		MarkerManager.deleteAllMarkers(resource);

		// Arcs checking.
		for (IArc arc : graph.getArcs()) {
			checkIArc(checker, arc, resource);
			checkIElementAttributes(checker, arc, resource);
		}

		// Node checking.
		for (INode node : graph.getNodes()) {
			checkINode(checker, node, resource);
			checkIElementAttributes(checker, node, resource);
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
			// If the current formalism name from the extension point matches the specified one,
			// the checker is built and linked to the session.
			if (checkers[i].getAttribute("id").equals(formalismId)) { //$NON-NLS-1$
				Checker checker = buildChecker(checkers[i]);
				LOGGER.finer("Attachement du checker à la session " + session.getName()); //$NON-NLS-1$
				session.setChecker(checker);
				return checker;
			}
		}
		return null;
	}
}
