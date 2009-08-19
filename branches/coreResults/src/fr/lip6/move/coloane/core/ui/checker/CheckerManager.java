package fr.lip6.move.coloane.core.ui.checker;

import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.IGraphChecker;
import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class CheckerManager {
	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** The name of the extension point that contains formalisms definition */
	private static final String EXTENSION_ID = "fr.lip6.move.coloane.core.formalisms"; //$NON-NLS-1$
	
	private static CheckerManager instance;
	/**
	 * Checkers originels définis dans le formalisme  
	 */
	private Map<String,Checker> formalismsCheckers = new HashMap<String,Checker>();
	
	/**
	 * Checkers attachés à une session particulière
	 */
	private Map<String,Checker> sessionCheckers = new HashMap<String,Checker>();
	
	private CheckerManager() {
		IConfigurationElement[] checkers = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
		for (int i = 0; i < checkers.length; i++) {
			formalismsCheckers.put(checkers[i].getAttribute("name"), buildChecker(checkers[i])); //$NON-NLS-1$
		}
	}
	
	/**
	 * @return The CheckerManager instance
	 */
	public static synchronized CheckerManager getInstance() {
		if (instance == null) { instance = new CheckerManager(); }
		return instance;
	}
	
	private Checker buildChecker(IConfigurationElement description) {
		LOGGER.finer("Définition d'un checker pour le formalisme : "+description.getAttribute("name")); //$NON-NLS-1$ //$NON-NLS-2$
		Checker checker = new Checker(description.getAttribute("name")); //$NON-NLS-1$
		
		IConfigurationElement[] xmlDescription = description.getChildren("XmlDescription"); //$NON-NLS-1$
		
		IConfigurationElement[] graphes = xmlDescription[0].getChildren("Graph"); //$NON-NLS-1$
		for (IConfigurationElement graph : graphes){
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
					checker.addGraphChecker(graph.getAttribute("name"), graphChecker); //$NON-NLS-1$
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
					//new NodeChecker
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
						LOGGER.finer("Ajout d'un ArcChecker sur le noeud '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		}
		
		return checker;
	}

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
						checker.addArcAttributeChecker(description.getAttribute("name"), attribute.getAttribute("name"), attributeChecker);  //$NON-NLS-1$//$NON-NLS-2$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '"+attribute.getAttribute("name")+"' de l'arc '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					} else if (description.getName().equals("Node")) { //$NON-NLS-1$
						checker.addNodeAttributeChecker(description.getAttribute("name"), attribute.getName(), attributeChecker); //$NON-NLS-1$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '"+attribute.getAttribute("name")+"' du noeud '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					} else if (description.getName().equals("Graph")) { //$NON-NLS-1$
						checker.addGraphAttributeChecker(description.getAttribute("name"), attribute.getName(), attributeChecker); //$NON-NLS-1$
						LOGGER.finer("Ajout d'un AttributeChecker sur l'attribut '"+attribute.getAttribute("name")+"' du graphe '"+description.getAttribute("name")+"'");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
					}
				} catch (CoreException core) {
					LOGGER.warning("Erreur dans la définition d'un AttributeChecker ! Une condition a été mal définie: "+core.getMessage()); //$NON-NLS-1$
				}
			}
		}
	}

	public void setChecker(ISession session) {
		String formalismName = session.getGraph().getFormalism().getId();
		
		IConfigurationElement[] checkers = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);
		for (int i = 0; i < checkers.length; i++) {
			if (checkers[i].getAttribute("id").equals(formalismName)) { //$NON-NLS-1$
				Checker checker = buildChecker(checkers[i]);
				session.setChecker(checker);
				sessionCheckers.put(formalismName, checker);
				return;
			}
		}
	}
	
	
}
