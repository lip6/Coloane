package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.checker.ColoaneMarkerNavigationProvider;
import fr.lip6.move.coloane.core.ui.commands.ArcCompleteCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.ArcReconnectCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.NodeDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.properties.ChangeAttributeCmd;
import fr.lip6.move.coloane.interfaces.formalism.IArcAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.commands.CompoundCommand;

public class ColoaneCommandStackEventListener implements
		CommandStackEventListener {

	ColoaneCommandStackEventListener() { }
	
	public void stackChanged(CommandStackEvent event) {
		System.out.println(event.getCommand().getClass());
		System.out.println(event.getDetail());

		if ((event.getDetail() & CommandStack.POST_MASK) != 0) {
			Command command;
			
			// Une CompoundCommand est parfois renvoyé l'ors d'un undo/redo,
			// il faut alors appelé la méthode unwrap() pour récupérer la commande exécutée. 
			if (event.getCommand() instanceof CompoundCommand) {
				command = ((CompoundCommand)event.getCommand()).unwrap();
			} else {
				command = event.getCommand();
			}

			System.out.println(command.getClass());

			// On récupère le graphe et le formalisme courant.
			IGraph currentGraph = SessionManager.getInstance().getCurrentSession().getGraph();
			IFormalism formalism = currentGraph.getFormalism();

			// Du formalisme, on extrait les checkers.
			List<IArcChecker> arcCheckers = formalism.getArcCheckers();
			List<INodeChecker> nodeCheckers = formalism.getNodeCheckers();
			List<IArcAttributeChecker> arcAttributeCheckers = formalism.getArcAttributeCheckers();
			List<INodeAttributeChecker> nodeAttributeCheckers = formalism.getNodeAttributeCheckers();

			// On différencie les actions entreprises selon la nature de la commande reçue dans l'event.
			
			// Vérification des markers après la création d'un arc.
			if (command instanceof ArcCompleteCmd) {
				System.out.println("ARCCOMPLETECOMMAND");

				// On récupère l'arc créé.
				ArcCompleteCmd currentCmd = ((ArcCompleteCmd)command);
				IArc currentArc = currentCmd.getArc();

				// On supprime ensuite les markers de l'arc et de ses attributs ainsi que ceux des noeuds source et target.
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_MARKER, currentArc);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_ATTRIBUTE_MARKER, currentArc);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, currentArc.getSource());
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, currentArc.getTarget());

				// On revérifie les markers des noeuds sources et target.
				for (INodeChecker nodeChecker : nodeCheckers) {
					nodeChecker.nodeCheck(currentArc.getSource());
					nodeChecker.nodeCheck(currentArc.getTarget());
				}

				// Après que la commande ait été réalisée ou bien lors d'un redo,
				// il faut aussi vérifier les markers de l'arc et de ses attributs.
				if (event.getDetail() == CommandStack.POST_EXECUTE || event.getDetail() == CommandStack.POST_REDO) {
					for (IArcChecker arcChecker : arcCheckers) {
						arcChecker.arcCheck(currentArc);
					}
					for (IAttribute attribute : currentArc.getAttributes()) {
						for(IArcAttributeChecker arcAttributeChecker : arcAttributeCheckers) {
							arcAttributeChecker.arcAttributeCheck(currentArc, attribute);
						}
					}
				}
			}
			
			// Vérification des markers après la suppression d'un arc.
			if (command instanceof ArcDeleteCmd) {
				System.out.println("ARCDELETECOMMAND");
				
				// On récupère l'arc supprimé
				IArc currentArc = ((ArcDeleteCmd)command).getArc();
				
				// On supprime les markers de l'arc et de ses attributs, ainsi que ceux des noeuds source et target.
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_MARKER, currentArc);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_ATTRIBUTE_MARKER, currentArc);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, currentArc.getSource());
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, currentArc.getTarget());

				// On vérifie ensuite les markers des noeuds source et target
				for (INodeChecker nodeChecker : nodeCheckers) {
					nodeChecker.nodeCheck(currentArc.getSource());
					nodeChecker.nodeCheck(currentArc.getTarget());
				}

				// Dans le cas d'un undo, l'arc est rétabli dans le graphe :
				// Il faut donc revérifier les markers de l'arc et de ses attributs comme lors de sa création.
				if (event.getDetail() == CommandStack.POST_UNDO) {
					for (IArcChecker arcChecker : arcCheckers) {
						arcChecker.arcCheck(currentArc);
					}
					for (IAttribute attribute : currentArc.getAttributes()) {
						for(IArcAttributeChecker arcAttributeChecker : arcAttributeCheckers) {
							arcAttributeChecker.arcAttributeCheck(currentArc, attribute);
						}
					}
				}
			}
			
			// Vérification des markers après la reconnection d'un arc.
			if (command instanceof ArcReconnectCmd) {
				System.out.println("ARCRECONNECTCOMMAND");

				// On récupère l'arc reconnecté ainsi que les nouveaux/anciens noeuds sources et targets.
				ArcReconnectCmd currentCmd = ((ArcReconnectCmd)command);
				IArc currentArc = currentCmd.getArc();
				INode newSource = currentCmd.getNewSource();
				INode newTarget = currentCmd.getNewTarget();
				INode oldSource = currentCmd.getOldSource();
				INode oldTarget = currentCmd.getOldTarget();
				
				// On supprime les markers des noeuds sources et targets (anciens et nouveaux) ainsi que ceux de l'arc.
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_MARKER, currentArc);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, newSource);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, newTarget);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, oldSource);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, oldTarget);

				// On vérifie ensuite les attributs de ces 4 noeuds et de l'arc car l'état de chacun a pu être modifié.
				for (INodeChecker nodeChecker : nodeCheckers) {
					nodeChecker.nodeCheck(newSource);
					nodeChecker.nodeCheck(newTarget);
					nodeChecker.nodeCheck(oldSource);
					nodeChecker.nodeCheck(oldTarget);
				}

				for (IArcChecker arcChecker : arcCheckers) {
					arcChecker.arcCheck(currentArc);
				}
			}
			
			// Vérification des markers après la création d'un noeud.
			if (command instanceof NodeCreateCmd) {
				System.out.println("NODECREATECOMMAND");
				
				// On récupère le noeud créé.
				NodeCreateCmd currentCmd = (NodeCreateCmd) command;
				INode currentNode = currentCmd.getNode();
				
				// On supprime les markers liés au noeud et à ses attributs.
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, currentNode);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_ATTRIBUTE_MARKER, currentNode);
				
				// Si la commande est exécutée pour la 1ère fois ou pour un redo,
				// on vérifie les markers du noeud et de ses attributs.
				if (event.getDetail() == CommandStack.POST_EXECUTE || event.getDetail() == CommandStack.POST_REDO) {
					for (INodeChecker nodeChecker : nodeCheckers) {
						nodeChecker.nodeCheck(currentNode);
					}
					for (IAttribute attribute : currentNode.getAttributes()) {
						for(INodeAttributeChecker nodeAttributeChecker : nodeAttributeCheckers) {
							nodeAttributeChecker.nodeAttributeCheck(currentNode, attribute);
						}
					}
				}
			}
			
			// Vérification des markers après la suppression d'un noeud.
			if (command instanceof NodeDeleteCmd) {
				System.out.println("NODEDELETECOMMAND");

				// On crée les listes d'arcs entrants et sortants.
				INode currentNode = ((NodeDeleteCmd)command).getNode();
				List<IArc> inArcs = ((NodeDeleteCmd)command).getIncomingArcs();
				List<IArc> outArcs = ((NodeDeleteCmd)command).getOutgoingArcs();


				// On supprime les markers du noeud supprimé.
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, currentNode);
				ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_ATTRIBUTE_MARKER, currentNode);
				
				// Chaque arc relié au noeud sera supprimé, on enlève également les markers de tous ces arcs et de leurs attributs.
				for (IArc arc : inArcs) {
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_MARKER, arc);
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_ATTRIBUTE_MARKER, arc);
					// Pour les arcs entrants, on supprime les markers du noeud source.
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, arc.getSource());
				}
				for (IArc arc : outArcs) {
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_MARKER, arc);
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_ATTRIBUTE_MARKER, arc);
					// Pour les arcs sortants, on supprime les markers du noeud target.
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_MARKER, arc.getTarget());
				}
				
				
				// On revérifie les markers des noeuds sources des arcs entrants et des noeuds target des arcs sortants
				// car leur état a changé du fait de la suppression d'un arc qui leur été relié.
				for (INodeChecker nodeChecker : nodeCheckers) {
					for (IArc arc : inArcs) {
						nodeChecker.nodeCheck(arc.getSource());
					}
					for (IArc arc : outArcs) {
						nodeChecker.nodeCheck(arc.getTarget());
					}
				}
				
				// Cas où on annule la suppression du noeud supprimé.
				// Les arcs et le noeud supprimés sont rétablis : on revérifie donc leurs marqueurs.
				if (event.getDetail() == CommandStack.POST_UNDO) {
					System.out.println("UNDO");
					// Vérification pour le noeud supprimé et ses attributs.
					for (INodeChecker nodeChecker : nodeCheckers) {
						nodeChecker.nodeCheck(currentNode);
					}
					for (IAttribute attribute : currentNode.getAttributes()) {
						 for (INodeAttributeChecker nodeAttributeChecker : nodeAttributeCheckers) {
							nodeAttributeChecker.nodeAttributeCheck(currentNode, attribute);
						}
					}
					
					// Ensuite pour les arcs entrants et sortants et leurs attributs.
					for (IArc arc : inArcs) {
						for (IArcChecker arcChecker : arcCheckers) {
							arcChecker.arcCheck(arc);
						}
						for (IAttribute attribute : arc.getAttributes()) {
							for(IArcAttributeChecker arcAttributeChecker : arcAttributeCheckers) {
								arcAttributeChecker.arcAttributeCheck(arc, attribute);
							}
						}
					}
					for (IArc arc : outArcs) {
						for (IArcChecker arcChecker : arcCheckers) {
							arcChecker.arcCheck(arc);
						}
						for (IAttribute attribute : arc.getAttributes()) {
							for(IArcAttributeChecker arcAttributeChecker : arcAttributeCheckers) {
								arcAttributeChecker.arcAttributeCheck(arc, attribute);
							}
						}
					}
				}
			}
			
			// Vérification des markers après un changement de valeur d'un attribut.
			if (command instanceof ChangeAttributeCmd) {
				System.out.println("CHANGEATTRIBUTECOMMAND");
				
				// On récupère l'attribut modifié ainsi que l'élément associé.
				ChangeAttributeCmd currentCmd = (ChangeAttributeCmd) command;
				IAttribute currentAttribute = currentCmd.getAttribute();
				IElement currentElement = currentAttribute.getReference();

				// On différencie les INode des IArc car ce ne sont pas les mêmes checkers qui sont appelés.
				if (currentElement instanceof INode) {
					// Si c'est un INode, on supprime les markers de ses attributs et on revérifie ces mêmes markers.
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.NODE_ATTRIBUTE_MARKER, currentElement);
					for (INodeAttributeChecker nodeAttributeChecker : nodeAttributeCheckers) {
						nodeAttributeChecker.nodeAttributeCheck((INode) currentAttribute.getReference(), currentAttribute);
					}
				} else if (currentElement instanceof INode) {
					// Si c'est un IArc, on supprime les markers de ses attributs et on revérifie si ces markers mêmes markers.
					ColoaneMarkerNavigationProvider.deleteElementMarkers(ColoaneMarkerNavigationProvider.ARC_ATTRIBUTE_MARKER, currentElement);
					for (IArcAttributeChecker arcAttributeChecker : arcAttributeCheckers) {
						arcAttributeChecker.arcAttributeCheck((IArc) currentAttribute.getReference(), currentAttribute);
					}
				}
			}
		} //if ((event.getDetail() & CommandStack.POST_MASK) != 0)
		
		
	}
}
