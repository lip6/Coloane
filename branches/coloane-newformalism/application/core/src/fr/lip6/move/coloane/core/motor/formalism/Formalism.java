package fr.lip6.move.coloane.core.motor.formalism;

import java.util.ArrayList;
import fr.lip6.move.coloane.core.motor.formalism.permissionRule.IRule;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

/**
 * Definition d'un formalisme
 */
public class Formalism {

	/** Nom du formalisme. */
	private String name;

	/** Nom de l'extension du fichier dans lequel on enregistrera le formalisme */
	private String extension;

	/** Adresse du XSchema pour l'ecriture et la lecture des modeles enregistres */
	private String xschema;

	/** Liste des elements de base du formalisme. */
	private ArrayList<ElementFormalism> listOfElementBase;

	/** Liste des attributs propres a un formalisme. */
	private ArrayList<AttributeFormalism> listOfAttributeFormalism;

	/** Liste des regles du formalisme. */
	private ArrayList<IRule> listOfRules;

	/** Nom du fichier de l'image avec extension ex: icon.gif */
	private String imageName;

	
	/**
	 * Constructeur de la classe Formalism (avec icone associee)
	 *
	 * @param formalismName Nom du formalisme.
	 * @param formalismImg Nom du fichier de l'image
	 * @param formalismExtension Extension associee au formalisme
	 */
	public Formalism(String formalismName, String formalismImg, String formalismExtension, String formalismXschema) {
		this.imageName = formalismImg;
		this.name = formalismName;
		this.extension = formalismExtension;
		this.xschema = formalismXschema;
		this.listOfElementBase = new ArrayList<ElementFormalism>();
		this.listOfRules = new ArrayList<IRule>();
		this.listOfAttributeFormalism = new ArrayList<AttributeFormalism>();
	}
	
	/**
	 * Indique si la liaison entre deux elements est possible
	 * @param elemIn  Element de base en entree de l'arc
	 * @param elemOut Element de base en sortie de l'arc
	 * @return boolean
	 */
	public final boolean isLinkAllowed(INodeImpl eltIn, INodeImpl eltOut) {
		for (IRule r : listOfRules) {			
			if (!r.canConnect(eltIn,eltOut)) {
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
		for (ElementFormalism elt : listOfElementBase) {
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
	public final ElementFormalism getArcFormalism(String typeElt) {
		for (ElementFormalism elt : listOfElementBase) {
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
	public final void addElementBase(ElementFormalism baseElement) {
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
	public final void addRule(IRule rule) {
		if (rule == null) {
			return;
		}
		listOfRules.add(rule);
	}
	
	/**
	 * Retourne la liste des elements de base attache au formalisme
	 * @return ArrayList
	 */
	public final ArrayList<ElementFormalism> getListOfElementBase() {
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

	/**
	 * Retourne l'adresse du xschema a utliser pour la validation
	 * @return String
	 */
	public final String getSchema() {
		return xschema;
	}
}
