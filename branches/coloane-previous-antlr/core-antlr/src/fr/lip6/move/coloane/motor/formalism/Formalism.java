package fr.lip6.move.coloane.motor.formalism;

import java.util.ArrayList;

/**
 * Definition d'un formalisme
 */
public class Formalism {

	/** Nom du formalisme. */
	private String name;

	/** Nom de l'extension du fichier dans lequel on enregistrera le formalisme */
	private String extension;

	/** Liste des elements de base du formalisme. */
	private ArrayList<ElementBase> listOfElementBase;

	/** Liste des attributs propres a un formalisme. */
	private ArrayList<AttributeFormalism> listOfAttributeFormalism;

	/** Liste des regles du formalisme. */
	private ArrayList<Rule> listOfRules;

	/** Nom du fichier de l'image avec extension ex: icon.gif */
	private String imageName;

	/**
	 * Construteur de la classe Formalism (avec icone associee)
	 *
	 * @param formalismName Nom du formalisme.
	 * @param formalismImg Nom du fichier de l'image
	 * @param formalismExtension Extension associee au formalisme
	 */
	public Formalism(String formalismName, String formalismImg, String formalismExtension) {
		this.imageName = formalismImg;
		this.name = formalismName;
		this.extension = formalismExtension;
		this.listOfElementBase = new ArrayList<ElementBase>();
		this.listOfRules = new ArrayList<Rule>();
		this.listOfAttributeFormalism = new ArrayList<AttributeFormalism>();
	}

	/**
	 * Indique si la liaison entre deux element est possible
	 * @param elemIn  Element de base en entre de l'arc
	 * @param elemOut Element de base en sortie de l'arc
	 * @return boolean
	 */
	public final boolean isLinkAllowed(ElementBase elemIn, ElementBase elemOut) {
		for (Rule r : listOfRules) {
			if (r.getElementIn().equals(elemIn) && r.getElementOut().equals(elemOut)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Methode renvoyant les informations sur un noeud du formalisme.
	 * @param formalismName du Node que l'on cherche.
	 * @return Le NodeFormalism ayant comme nom celui donne en entree.
	 */
	public final NodeFormalism getNodeFormalism(String typeElt) {
		for (ElementBase elt : listOfElementBase) {
			if (typeElt.equals(elt.getName())) {
				return (NodeFormalism) elt;
			}
		}
		return null;
	}

	/**
	 * Methode renvoyant les informations sur un arc du formalisme
	 * @param name du Node que l'on cherche.
	 * @return Le NodeFormalism ayant comme nom celui donne en entree.
	 */
	public final ElementBase getArcFormalism(String typeElt) {
		for (ElementBase elt : listOfElementBase) {
			if (typeElt.equals(elt.getName())) {
				return (ArcFormalism) elt;
			}
		}
		return null;
	}


	/**
	 * Ajout d'un element de base a l'interieur du formalisme.
	 * @param baseElement Element de base a ajouter.
	 */
	public final void addElementBase(ElementBase baseElement) {
		if (baseElement == null) {
			return;
		}
		listOfElementBase.add(baseElement);
	}

	/**
	 * Ajoute une attribut (AttributeFormalism) a l'element de base.
	 * @param attFormalism attribut a ajouter
	 */
	public final void addAttributeFormalism(AttributeFormalism attFormalism) {
		if (attFormalism == null) {
			return;
		}
		listOfAttributeFormalism.add(attFormalism);
	}

	/**
	 * Ajouter une regle
	 * @param rule La regle a ajouter un formalisme
	 */
	public final void addRule(Rule rule) {
		if (rule == null) {
			return;
		}
		listOfRules.add(rule);
	}

	/**
	 * Retourne la liste des elements de base attache au formalisme
	 * @return ArrayList
	 */
	public final ArrayList<ElementBase> getListOfElementBase() {
		return listOfElementBase;
	}

	/**
	 * Retourne le nom du formalisme
	 * @return String
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Retourne la liste des atttributs du formalisme.
	 * @return ArrayList
	 */
	public final ArrayList<AttributeFormalism> getListOfAttribute() {
		return listOfAttributeFormalism;
	}

	/**
	 * Retourne le nom de l'image associee au formalime
	 * @return String
	 */
	public final String getImageName() {
		return imageName;
	}

	/**
	 * Retourne la chaine de caracteres a utiliser pour l'extension du fichier
	 * @return String
	 */
	public final String getExtension() {
		return extension;
	}
}
