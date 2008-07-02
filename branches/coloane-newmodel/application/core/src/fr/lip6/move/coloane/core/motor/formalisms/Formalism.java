package fr.lip6.move.coloane.core.motor.formalisms;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint;
import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphFormalism;

/**
 * Definition d'un formalisme.<br>
 * L'instanciation d'un tel formalisme provoque systématiquement la création d'un objet {@link GraphFormalism}.<br>
 * Sans aucune autre précision, les éléments de formalisme créés par la suite seront associé à cet objet {@link GraphFormalism}.
 */
public class Formalism {

	/** Nom du formalisme. */
	private String name;

	/** Nom de l'extension du fichier dans lequel on enregistrera le formalisme */
	private String extension;

	/** Adresse du XSchema pour l'ecriture et la lecture des modeles enregistres */
	private String xschema;

	/** Liste des élément de base du formalisme. */
	private List<ElementFormalism> elements;

	/** Liste des regles du formalisme. */
	private List<IConstraint> constraints;

	/** Nom du fichier de l'image avec extension ex: icon.gif */
	private String image;
	
	/** Graphe principal du formalisme */
	private ElementFormalism master;

	/**
	 * Création d'un formalisme
	 *
	 * @param name Nom du formalisme.
	 * @param extension Extension associée au formalisme
	 * @param xshema Le XSchema nécessaire à la lecture des instances de ce formalisme
	 * @param image L'image associé à toutes les instances de ce formalisme
	 */
	public Formalism(String name, String extension, String xshema, String image) {
		this.name = name;
		this.extension = extension;
		this.image = image;
		this.xschema = xshema;
		
		this.elements = new ArrayList<ElementFormalism>();
		this.constraints = new ArrayList<IConstraint>();
		
		// Creation et Ajout du graphe principal lié à l'instance du formalisme
		this.master = new GraphFormalism(name);
		this.elements.add(master);
	}

	/**
	 * Indique si la liaison entre deux élément est possible
	 * @param source Element source de l'arc
	 * @param target Element cible de l'arc
	 * @return <code>true</code> si la liaison est possible
	 */
	public final boolean isLinkAllowed(ElementFormalism source, ElementFormalism target) {
		// Parcours de toutes les contraintes définies dans le formalisme
		for (IConstraint constraint : constraints) {
			if (!constraint.isSatisfied(source,target)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Ajout d'un element de base au formalisme
	 * @param element {@link ElementFormalism} de base a ajouter.
	 */
	public final void addElement(ElementFormalism element) {
		if (element == null) { return; }
		this.elements.add(element);
	}

	/**
	 * Ajouter une contrainte au formalisme
	 * @param constraint La contrainte à ajouter au formalisme
	 * @see {@link IConstraint}
	 */
	public final void addConstraint(IConstraint constraint) {
		if (constraint == null) { return; }
		this.constraints.add(constraint);
	}

	/**
	 * Retourne la liste des éléments attachés au formalisme
	 * @return Une liste d'éléments {@FormalismElement}
	 */
	public final List<ElementFormalism> getListOfElementBase() {
		return this.elements;
	}

	/**
	 * @return Le nom du formalisme
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * @return L'image associée à toutes les instances de ce formalisme
	 */
	public final String getImageName() {
		return this.image;
	}

	/**
	 * @return La chaine de caractères à utiliser pour l'extension du fichier
	 */
	public final String getExtension() {
		return this.extension;
	}

	/**
	 * @return L'adresse du xschema a utliser pour la validation
	 */
	public final String getSchema() {
		return this.xschema;
	}
	
	/**
	 * @return Le graphe principal du formalisme
	 */
	public final ElementFormalism getMasterGraph() {
		return this.master;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return getName();
	}
}
