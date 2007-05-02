package fr.lip6.move.coloane.ui.commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.ui.model.IArcImpl;
import fr.lip6.move.coloane.ui.model.INodeImpl;

/**
 * Deuxieme etape de la creation d'un lien entre deux noeuds !
 * Cette commande est creee lors du second clic (donc sur l'element d'arrivee).
 */

public class ArcCompleteCmd extends Command {

	/** Noeud source */
    private final INodeImpl source;
    
    /** Noeud cible */
    private final INodeImpl target;

    /** Element de base du formalisme (arc) */
    private final ElementBase arcFormalism;

    /** L'arc */
    private IArcImpl arc;

    /**
     * Connexion de l'arc
     * @param source noeud source
     * @param target noeud cible
     * @param base elementBase
     */
    public ArcCompleteCmd(INodeImpl source, INodeImpl target, ElementBase base) {
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
    		IArcImpl arc = (IArcImpl) iter.next();
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
    	try {
    		// Le constructeur se charge de la connexion
    		arc = new ArcImplAdapter(source, target, arcFormalism);
    		arc.setModelAdapter(source.getModelAdapter());
    		this.redo();
    	} catch (BuildException e) {
    		System.err.println("Echec ! : "+e.getMessage());
    	}
    }

    /**
     * Refaire la methode Execute
     */
    public void redo() {
    	source.getModelAdapter().addArc(arc);
    }

    /**
     * Defaire la methode Execute
     */
    public void undo() {
    	source.getModelAdapter().removeArc(arc);
    }
}