package fr.lip6.move.coloane.ui.commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.ui.model.NodeImplAdapter;

/**
 * Connexion effective d'un arc
 */

public class ArcCompleteCmd extends Command {

	/** Noeud source */
    private final NodeImplAdapter source;
    
    /** Noeud cible */
    private final NodeImplAdapter target;

    /** Element de base du formalisme (arc) */
    private final ElementBase arcFormalism;

    /** Connection */
    private ArcImplAdapter connection;

    /**
     * Connexion de l'arc
     * @param source noeud source
     * @param target noeud cible
     * @param base elementBase
     */
    public ArcCompleteCmd(NodeImplAdapter source, NodeImplAdapter target, ElementBase base) {
    	this.source = source;
        this.target = target;
        this.arcFormalism = base;
    }

    /**
     * Savoir si l'action est executable
     * @return booleen
     */
    public boolean canExecute() {

    	Formalism form = arcFormalism.getFormalism();
    	
    	// La connexion est-elle autorisee par le formalisme ?
    	if (!form.isLinkAllowed(source.getElementBase(), target.getElementBase())) {
    		return false;
        } 
    	
    	// Evite les doublons en renvoyant faux si le lien existe deja   	
    	for (Iterator iter = source.getSourceArcs().iterator(); iter.hasNext();) {
    		ArcImplAdapter arc = (ArcImplAdapter) iter.next();
            if (arc.getTarget().equals(target)) {
            	return false;
            }
        }
        
    	return true;
    }

    /**
     * Creation de la connexion
     */
    public void execute() {
    	connection = new ArcImplAdapter(source, target, arcFormalism);
    	connection.setModelAdapter(source.getModelAdapter());
    }

    /**
     * Refaire
     */
    public void redo() {
    	connection.reconnect();
    }

    /**
     * Defaire
     */
    public void undo() {
    	connection.disconnect();
    }
}