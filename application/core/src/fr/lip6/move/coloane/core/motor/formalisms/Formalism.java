package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintLink;
import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintNode;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition d'un formalisme.<br>
 * L'instanciation d'un tel formalisme provoque systématiquement la création d'un objet {@link GraphFormalism}.<br>
 * Sans aucune autre précision, les éléments de formalisme créés par la suite seront associé à cet objet {@link GraphFormalism}.
 */
public class Formalism implements IFormalism {

	/** Nom du formalisme. */
	private String name;

	/** Parent du formalisme (identifiant historique). */
	private String fkname;

	/** Adresse du XSchema pour l'ecriture et la lecture des modeles enregistres */
	private String xschema;

	/** Liste des regles du formalisme concernant les liens entre objets. */
	private List<IConstraintLink> linkconstraints;

	/** Liste des regles du formalisme concernant les actions sur les noeuds. */
	private List<IConstraintNode> nodeconstraints;

	/** Le graphe défnini par le formalisme */
	private IGraphFormalism master = null;

	/** Nom du fichier de l'image avec extension ex: icon.gif */
	private String image;

	/**
	 * Création d'un formalisme
	 *
	 * @param name Nom du formalisme.
	 * @param extension Extension associée au formalisme
	 * @param xshema Le XSchema nécessaire à la lecture des instances de ce formalisme
	 * @param image L'image associé à toutes les instances de ce formalisme
	 */
	Formalism(String name, String parent, String xshema, String image) {
		this.name = name;
		this.fkname = parent;
		this.image = image;
		this.xschema = xshema;

		this.linkconstraints = new ArrayList<IConstraintLink>();
		this.nodeconstraints = new ArrayList<IConstraintNode>();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#isLinkAllowed(fr.lip6.move.coloane.interfaces.formalism.IElementFormalism, fr.lip6.move.coloane.interfaces.formalism.IElementFormalism)
	 */
	public final boolean isLinkAllowed(INode source, INode target) {
		// Parcours de toutes les contraintes définies dans le formalisme
		for (IConstraintLink constraint : linkconstraints) {
			if (!constraint.isSatisfied(source, target)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.formalism.IFormalism#isActionAllowed(fr.lip6.move.coloane.interfaces.model.INode)
	 */
	public final boolean isActionAllowed(INode node) {
		// Parcours de toutes les contraintes définies dans le formalisme
		for (IConstraintNode constraint : nodeconstraints) {
			if (!constraint.isSatisfied(node)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Ajouter une contrainte de lien au formalisme
	 * @param constraint La contrainte de lien à ajouter au formalisme
	 * @see {@link IConstraintLink}
	 * @see {@link IConstraint}
	 */
	public final void addConstraintLink(IConstraintLink constraint) {
		if (constraint == null) { return; }
		this.linkconstraints.add(constraint);
	}

	/**
	 * Ajouter une contrainte de noeud au formalisme
	 * @param constraint La contrainte de noeud à ajouter au formalisme
	 * @see {@link IConstraintNode}
	 * @see {@link IConstraint}
	 */
	public final void addConstraintNode(IConstraintNode constraint) {
		if (constraint == null) { return; }
		this.nodeconstraints.add(constraint);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getName()
	 */
	public final String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.formalism.IFormalism#getParent()
	 */
	public final String getFKName() {
		return this.fkname;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getImageName()
	 */
	public final String getImageName() {
		return "/" + image; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getSchema()
	 */
	public final String getSchema() {
		return this.xschema;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getMasterGraph()
	 */
	public final IGraphFormalism getMasterGraph() {
		return this.master;
	}

	/**
	 * Indique quel est le graphe principal du formalisme (point d'entrée)
	 * @param master Le graphe principal du formalisme
	 */
	public final void setMasterGraph(IGraphFormalism master) {
		this.master = master;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#toString()
	 */
	@Override
	public final String toString() {
		return getName();
	}
}
