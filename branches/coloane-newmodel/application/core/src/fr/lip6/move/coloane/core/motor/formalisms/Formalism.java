package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.motor.formalisms.constraints.IConstraint;
import fr.lip6.move.coloane.core.motor.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.core.motor.formalisms.elements.GraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

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

	/** Nom de l'extension du fichier dans lequel on enregistrera le formalisme */
	private String extension;

	/** Adresse du XSchema pour l'ecriture et la lecture des modeles enregistres */
	private String xschema;

	/** Liste des élément de base du formalisme. */
	private List<IElementFormalism> elements;

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

		this.elements = new ArrayList<IElementFormalism>();
		this.constraints = new ArrayList<IConstraint>();

		// Creation et Ajout du graphe principal lié à l'instance du formalisme
		this.master = new GraphFormalism(name,this);
		this.elements.add(master);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#isLinkAllowed(fr.lip6.move.coloane.interfaces.formalism.IElementFormalism, fr.lip6.move.coloane.interfaces.formalism.IElementFormalism)
	 */
	public final boolean isLinkAllowed(IElementFormalism source, IElementFormalism target) {
		// Parcours de toutes les contraintes définies dans le formalisme
		for (IConstraint constraint : constraints) {
			if (!constraint.isSatisfied(source, target)) {
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

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getListOfElementBase()
	 */
	public final List<IElementFormalism> getListOfElementBase() {
		return this.elements;
	}

	public final IElementFormalism getFormalismElement(String name) {
		for (IElementFormalism elementFormalism : elements) {
			if (elementFormalism.getName().equals(name)) {
				return elementFormalism;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getName()
	 */
	public final String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getImageName()
	 */
	public final String getImageName() {
		return this.image;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#getExtension()
	 */
	public final String getExtension() {
		return this.extension;
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
	public final IElementFormalism getMasterGraph() {
		return this.master;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.IFormalism#toString()
	 */
	@Override
	public final String toString() {
		return getName();
	}

	public IElementFormalism getElementFormalism(String name) {
		for (IElementFormalism element : this.elements) {
			if (element.getName().equals(name)) {
				return element;
			}
		}
		return null;
	}
}
