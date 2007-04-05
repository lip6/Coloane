package fr.lip6.move.coloane.motor.formalism;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Definition d'un formalisme
 */

public class Formalism implements Serializable {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
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
    private String  imageName;
	
    
    
	/**
	 * Construteur de la classe Formalism
	 * 
	 * @param name Nom du formalisme.
	 */
	public Formalism(String name) {
		this.name = name;
		this.listOfElementBase        = new ArrayList<ElementBase>();
		this.listOfRules              = new ArrayList<Rule>();
		this.listOfAttributeFormalism = new ArrayList<AttributeFormalism>();
	}

    /**
     * Construteur de la classe Formalism (avec icone associee)
     * 
     * @param name Nom du formalisme.
     * @param img Nom du fichier de l'image
     */
    public Formalism(String name, String img) {
        this.imageName = img;
        this.name = name;
        this.listOfElementBase        = new ArrayList<ElementBase>();
        this.listOfRules              = new ArrayList<Rule>();
        this.listOfAttributeFormalism = new ArrayList<AttributeFormalism>();
    }
       
    /**
     * Indique si la liaison entre deux element est possible
     * @param elemIn  Element de base en entre de l'arc
     * @param elemOut Element de base en sortie de l'arc
     * @return boolean
     */
    public boolean isLinkAllowed(ElementBase elemIn, ElementBase elemOut) {

    	if (elemIn instanceof ArcFormalism) {
			return false;
		}
    	if (elemOut instanceof ArcFormalism) {
			return false;
		}
    	if (!(elemIn instanceof NodeFormalism)) {
    		return false;
    	}
    	if (!(elemOut instanceof NodeFormalism)) {
    		return false;
    	}
    	
    	Iterator it;
    	Rule rule;
    	
    	for (it = listOfRules.iterator(); it.hasNext();) {
    		rule = (Rule) it.next();
    		/** TODO: Revoir le resultat de la condition suivante */
    		if (rule.getElementIn().equals(elemIn)) {
    			if (rule.getElementOut().equals(elemOut)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
	/**
	 * Methode renvoyant un Objet Node a partir de son nom.
	 * 
	 * @param name du Node que l'on cherche.
	 * @return Le NodeFormalism ayant comme nom celui donne en entree. 
	 */
    public NodeFormalism string2Node(String name){
    	ElementBase node;
    	Iterator it = listOfElementBase.iterator();
    	for (node = null; it.hasNext();) {
    		node = ((ElementBase) it.next());
    		if (name.equals(node.getName())) {
    			return ((NodeFormalism) node);
    		}
    	}
    	return null;
    }
    
    /**
     * Methode renvoyant un Objet Node a partir de son nom.
     * 
     * @param name du Node que l'on cherche.
     * @return Le NodeFormalism ayant comme nom celui donne en entree. 
     */
   public ElementBase string2Arc(String name){
    ElementBase arc;
    Iterator it = listOfElementBase.iterator();
    for (arc = null; it.hasNext();) {
        arc = ((ElementBase) it.next());
        if (name.equals(arc.getName())) {
            return arc;
        }
    }
    return null;
   }
    
    
    
    
  /**
   * Methode renvoyant un Objet Node a partir de son ID.
   * @param id Identifiant du NodeFormalism recherhce.
   * @return NodeFormalism
   */
    public NodeFormalism int2Node(int id) {
    	if (id >= listOfElementBase.size()) {
    		return (NodeFormalism) listOfElementBase.get(id);
    	} else {
    		return null;
    	}
	}
	
	/**
	 * Ajout d'un element de base a l'interieur du formalisme.
	 * 
	 * @param elemB Element de base a ajouter.
	 */
	public void addElementBase(ElementBase elemB) {
		if (elemB == null) { return; }
        try {
            listOfElementBase.add(elemB);
        } catch (Exception e) {
        	System.err.println("Erreur lors de l'ajout d'un element de base de le formalisme");
        }
	}

	/**
     * Ajoute une attribut (AttributeFormalism) a l'element de base.
     * @param attrForm attribut à ajouter
     */
    public void addAttributeFormalism(AttributeFormalism attrForm) {
    	try {
    	    listOfAttributeFormalism.add(attrForm);
        } catch (Exception e) {
            System.out.println("Erreur addAttributeFormalism");
        }
    }
	
    /**
     * Ajouter une regle
     * @param rule La regle a ajouter un formalisme
     */
    public void addRule(Rule rule) {
        try {
            listOfRules.add(rule);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout d'une regle au formalisme");
        }
    }
    
	/**
	 * Retourne la liste des elements de base attache au formalisme
	 * @return ArrayList
	 */
	public ArrayList getListOfElementBase() {
		return listOfElementBase;
	}

	/**
	 * Retourne la liste des regles associees au formalisme
	 * @return ArrayList
	 */
	public ArrayList getListOfRules() {
		return listOfRules;
	}

	/**
	 * Retourne le nom du formalisme
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom du formalisme
	 * @param name Le nom du formalisme
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retourne la liste des atttributs du formalisme.
	 * @return ArrayList
	 */
	public ArrayList getListOfAttribute() {
		return listOfAttributeFormalism;
	}

	/**
	 * Retourne le nom de l'image associee au formalime
	 * @return String
	 */
    public String getImageName() {
        return imageName;
    }

    /**
     * Retourne la chaine de caracteres a utiliser pour l'extension du fichier
     * @return String
     */
	public String getExtension() {
		return extension;
	}
	
	/**
	 * Modifie l'extension attachee au formalisme
	 * @param extension L'extension a utiliser pour l'enregistrement.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
}
