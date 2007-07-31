package fr.lip6.move.coloane.ui.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.ui.model.ModelImplAdapter;
import fr.lip6.move.coloane.ui.model.NodeImplAdapter;

/**
 * Commande pour ajouter un nouveau noeud 
 */

public class NodeCreateCmd extends Command {

        /** Nouveau noeud a creer */
        private NodeImplAdapter newNode;

        /** Model */
        private final ModelImplAdapter model;

        /** Limite */
        private Rectangle bounds;

        /**
         * Creer une commande qui ajoutera le noeud au modle
         * 
         * @param node Le nouveau noeud ˆ ajouter
         * @param model Le modle qui contiendra le noeud
         * @param bound Les limites du noeud; (la taille peut tre (-1, -1))
         */
        public NodeCreateCmd(NodeImplAdapter node, ModelImplAdapter model, Rectangle bound) {
        	this.newNode = node;
            this.model = model;
            newNode.setModelAdapter(model);
            bounds = bound;
        }

        /**
         * Savoir si on peux executer la commande ?
         * @return booleen
         */
        public boolean canExecute() {
        	return true;
        }

        /**
         * Executer la commande
         */
        public void execute() {
        	newNode.getGraphicInfo().setLocation(bounds.getLocation());
            Dimension size = bounds.getSize();
            if (size.width > 0 && size.height > 0) {
            	newNode.getGraphicInfo().setSize(size);
            }
            redo();
        }

        /**
         * Refaire les actions de la methode Execute
         */
        public void redo() {
        	model.addChild(newNode);
        }

        /**
         * Annuler les modifications faites par la methode Execute
         */
        public void undo() {
        	model.removeChild(newNode);
        }

}