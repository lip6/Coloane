package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.motor.formalisms.constraints.CheckArcValuation;
import fr.lip6.move.coloane.core.motor.formalisms.constraints.CheckPlaceIncomingArcs;
import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint;
import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintLink;
import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraintNode;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IArcAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.IArcChecker;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.INodeChecker;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition d'un formalisme.<br>
 * L'instanciation d'un tel formalisme provoque systématiquement la création d'un objet {@link GraphFormalism}.<br>
 * Sans aucune autre précision, les éléments de formalisme créés par la suite seront associé à cet objet {@link GraphFormalism}.
 */
public class Formalism implements IFormalism {

	/** Id du formalisme. */
	private String id;

	/** Nom du formalisme. */
	private String name;

	/** Parent du formalisme (identifiant historique). */
	private String fkname;

	/** Liste des regles du formalisme concernant les liens entre objets. */
	private List<IConstraintLink> linkconstraints;

	/** Liste des regles du formalisme concernant les actions sur les noeuds. */
	private List<IConstraintNode> nodeconstraints;

	/** Le graphe défnini par le formalisme */
	private IGraphFormalism master = null;
	
	private List<INodeChecker> nodeCheckList;
	private List<IArcChecker> arcCheckList;
	private List<IArcAttributeChecker> arcAttributeCheckList;
	private List<INodeAttributeChecker> nodeAttributeCheckList;

	/** Nom du fichier de l'image avec extension ex: icon.gif */
	private String image;

	/**
	 * Création d'un formalisme
	 *
	 * @param id Id du formalisme
	 * @param name Nom du formalisme.
	 * @param fkname Le nom du formalisme à utiliser avec FrameKit
	 * @param image L'image associé à toutes les instances de ce formalisme
	 */
	Formalism(String id, String name, String fkname, String image) {
		this.id = id;
		this.name = name;
		this.fkname = fkname;
		this.image = image;

		this.linkconstraints = new ArrayList<IConstraintLink>();
		this.nodeconstraints = new ArrayList<IConstraintNode>();
		
		this.nodeCheckList = new ArrayList<INodeChecker>();
		this.arcCheckList = new ArrayList<IArcChecker>();
		this.arcAttributeCheckList = new ArrayList<IArcAttributeChecker>();
		this.nodeAttributeCheckList = new ArrayList<INodeAttributeChecker>();

		this.nodeCheckList.add(new CheckPlaceIncomingArcs());
		this.arcAttributeCheckList.add(new CheckArcValuation(0,2));
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isLinkAllowed(INode source, INode target, IArcFormalism arcFormalism) {
		// Parcours de toutes les contraintes définies dans le formalisme
		for (IConstraintLink constraint : linkconstraints) {
			if (!constraint.isSatisfied(source, target, arcFormalism)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
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

	/** {@inheritDoc} */
	public final String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getFKName() {
		return this.fkname;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getImageName() {
		return "/" + image; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return getName();
	}

	public List<IArcChecker> getArcCheckers() {
		return arcCheckList;
	}

	public List<INodeChecker> getNodeCheckers() {
		return nodeCheckList;
	}
	
	public List<IArcAttributeChecker> getArcAttributeCheckers() {
		return arcAttributeCheckList;
	}
	
	public List<INodeAttributeChecker> getNodeAttributeCheckers() {
		return nodeAttributeCheckList;
	}
}
