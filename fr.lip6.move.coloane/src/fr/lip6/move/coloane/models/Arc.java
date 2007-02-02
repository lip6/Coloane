package fr.lip6.move.coloane.models;

import java.util.Arrays;
import java.util.Vector;
import java.io.Serializable;


/**
 * Classe representant un arc d'un modele dans les differents formalismes
 * implantes. Un arc possede obligatoire un noeud en entree et en sortie.
 * Un liste d'attribut peut etre en plus attache a un arc.
 * 
 * @author Olivier Rouquette, Christophe Janton
 */
public class Arc extends Base implements Serializable { 
    
    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Type de l'arc */
    private String arcType;
    
    /** Identifiant de l'arc */
    private int id;
    
    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    public int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    public int yPosition;

    /** Vecteur contenant l'ensemble des objets de type Attribut de l'arc.
     * @see Atribute
     */
    private Vector<Attribute> listOfAttr;

    /** Noeud d'entree de l'arc. */
    private Node startingNode;

    /** Noeud de sortie de l'arc. */
    private Node endingNode;

    /**
     * Constructeur de la classe Arc. 
     * @param arcType Type de l'arc
     * @param uniqueId Identifant unique de l'arc
     */
    public Arc(String arcType, int id) {
        this.arcType = arcType;
        this.id = id;
        this.xPosition = 0;
        this.yPosition = 0;
        this.listOfAttr = new Vector<Attribute>(0);
        this.startingNode = null;
        this.endingNode = null;
        
        System.out.println("1. Creation d'un arc avec id:"+this.id);
    }
    
    /**
     * Constructeur de la classe Arc. 
     * @param arcType Type de l'arc
     */
    public Arc(String arcType) {
        this.arcType = arcType;
        this.id = Base.uniqueId++;
        this.xPosition = 0;
        this.yPosition = 0;
        this.listOfAttr = new Vector<Attribute>(0);
        this.startingNode = null;
        this.endingNode = null;
        
        System.out.println("2. Creation d'un arc avec id:"+this.id);
    }

    /**
     * Retourne le type de l'arc.
     * @return String
     */
    public String getArcType() {
        return this.arcType;
    }
    
    /**
     * Cette methode permet de fixer les coordonnees x et y de l'arc.
     * @param x Coordonnee x
     * @param y Coordonnee y
     */
    public void setPosition(int x, int y) {
        if (x > 0 && y > 0) {
            this.xPosition = x;
            this.yPosition = y;
        }
    }

    /**
	 * Retourne l'identifiant unique de l'arc.
	 * @return int
	 */
	public int getUniqueId() {
	    return this.id;
	}

	/**
     * Retourne l'abscisse de l'arc.
     * @return int
     */
    public int getXPosition() {
        return this.xPosition;
    }

    /**
     * Retourne l'ordonnee de l'arc.
     * @return int
     */
    public int getYPosition() {
        return this.yPosition;
    }

    /**
     * Permet de fixer le noeud d'entree de l'arc.
     * @param node Noeud d'entree
     */
    public void setStartingNode(Node node) {
        if (this.startingNode == null) {
            this.startingNode = node;
        }
    }

    /**
     * Permet de fixer le noeud de sortie de l'arc.
     * @param node Noeud de sortie
     */
    public void setEndingNode(Node node) {
        if (this.endingNode == null) {
            this.endingNode = node;
        }
    }

    /**
     * Retourne le noeud d'entree de l'arc.
     * @return Node
     * @see Node
     */
    public Node getStartingNode() {
        return this.startingNode;
    }

    /**
     * Retourne le noeud de sortie de l'arc.
     * @return Node
     * @see Node
     */
    public Node getEndingNode() {
        return this.endingNode;
    }

    /**
     * Ajout d'un attribut a l'arc
     * @param attribute Attribut a ajouter
     * @see Attribute
     */
    public void addAttribute(Attribute attribute) {
    	this.listOfAttr.addElement(attribute);
    }

    /**
     * Supprime un attribut
     * @param attribute Attribut a supprimer
     * @see Attribute
     */
    public void removeAttribute(Attribute attribute) {
        try {
            this.listOfAttr.remove(attribute);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Spprime l'attribut en fonction de con index.
     * @param index Index de l'attribut a supprimer
     */
    public void removeAttribute(int index) {
        try {
            this.listOfAttr.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Cette methode retourne le nombre d'attributs de l'arc.
     * @return int
     */
    public int getListOfAttrSize() {
        return this.listOfAttr.size();
    }
    
    /**
     * Retourne le vecteur contenant les attributs du modele.
     * @return Vector
     * @see Attribute
     */
    public Vector getListOfAttr() {
        return this.listOfAttr;
    }
    
    /**
     * Retourne le nieme attribut de l'arc 
     * @param index Index de l'attribut recherche
     * @return Attribute
     */
    public Attribute getNthAttr(int index) {
        Attribute attr = null;
        try {
            attr = (Attribute) this.listOfAttr.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return attr;
    }

    /**
     * Traduit un objet Arc en chaine de caracteres CAMI correspondante
     * @return String[]
     */
    public String[] translateToCAMI() {
        if (this.startingNode == null || this.endingNode == null) {
            return null;
        } else {
            String[] stringToReturn;
            StringBuffer s;
            Vector<String> vectorStringToReturn = new Vector<String>(0);

            s = new StringBuffer();
            s.append("CA(");
            s.append(this.arcType.length() + ":" + this.arcType);
            s.append(",");
            s.append(id);
            s.append(",");
            s.append(this.startingNode.getId());
            s.append(",");
            s.append(this.endingNode.getId());
            s.append(")");
            vectorStringToReturn.addElement(s.toString());

            for (int i = 0; i < this.getListOfAttrSize(); i++) {
                vectorStringToReturn.addAll(Arrays.asList(this.getNthAttr(i).translateToCAMI()));
            }

            stringToReturn = new String[vectorStringToReturn.size()];
            vectorStringToReturn.toArray(stringToReturn);
            return stringToReturn;
        }
    }
}

