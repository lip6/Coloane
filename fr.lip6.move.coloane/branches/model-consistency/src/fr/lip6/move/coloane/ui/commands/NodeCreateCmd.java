package fr.lip6.move.coloane.ui.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

/**
 * Commande pour ajouter un nouveau noeud 
 */

public class NodeCreateCmd extends Command {

        /** Nouveau noeud a creer */
        private INodeImpl newNode;

        /** Model */
        private final IModelImpl model;

        /** Limite */
        private Rectangle bounds;

        /**
         * Creer une commande qui ajoutera le noeud au modle
         * 
         * @param node Le nouveau noeud ˆ ajouter
         * @param model Le modle qui contiendra le noeud
         * @param bound Les limites du noeud; (la taille peut tre (-1, -1))
         */
        public NodeCreateCmd(INodeImpl node, IModelImpl model, Rectangle bounds) {
        	this.newNode = node;
            this.model = model;
            this.newNode.setModelAdapter(model);
            this.bounds = bounds;
        }

        /**
         * Savoir si on peux executer la commande ?
         * --> Toujours OK
         * @return booleen
         */
        public boolean canExecute() {
        	return true;
        }

        /**
         * Executer la commande
         */
        public void execute() {
        	this.newNode.getGraphicInfo().setLocation(bounds.getLocation());
            Dimension size = bounds.getSize();
            if (size.width > 0 && size.height > 0) {
            	this.newNode.getGraphicInfo().setSize(size);
            }
            this.redo();
        }

        /**
         * Refaire les actions de la methode Execute
         * --> Revient a faire un nouvel ajout d'un noeud
         */
        public void redo() {
        	try {
				model.addNode(newNode);
			} catch (BuildException e) {
				e.printStackTrace();
				System.err.println("Echec ! : "+e.getMessage());
			}
        }

        /**
         * Annuler les modifications faites par la methode Execute
         * --> Revient a supprimer le noeud cree
         */
        public void undo() {
        	try {
				model.removeNode(newNode);
			} catch (BuildException e) {
				e.printStackTrace();
				System.err.println("Echec ! : "+e.getMessage());
			}
        }

}