package fr.lip6.move.coloane.models;

import java.util.Arrays;
import java.util.Vector;
import java.io.Serializable;

/**
 * Le noeud d'un modele
 */
public class Node extends Base implements Serializable {

    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Type du noeud */
    private String nodeType;
    
    /** Identificateur du noeud */
    private int id;
    
    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    public int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    public int yPosition;

    /** Liste des arc entrants */
    private Vector<Arc> listOfInputArc;

    /** Liste des arcs sortants */
    private Vector<Arc> listOfOutputArc;

    /** Liste des attributs du noeud*/
    private Vector<Attribute> listOfAttr;

    /**
     * Constructeur
     * 
     * @param NodeType	Type du noeud
     * @param UniqueId	Identifiant du noeud
     */
    public Node(String nodeType) {
    	this.nodeType = nodeType;
        this.id = Base.uniqueId++;
        xPosition = 0;
        yPosition = 0;
        this.listOfAttr = new Vector<Attribute>(0);
        this.listOfInputArc = new Vector<Arc>(0);
        this.listOfOutputArc = new Vector<Arc>(0);
        
        System.out.println("1. Creation dun noeud avec id:"+this.id);
    }
    
    /**
     * Constructeur
     * 
     * @param uniqueId	Identifiant du noeud
     * @param nodeType	Type du noeud
     * @param x			Position x du noeud
     * @param y			Position y du noeud
     */
    public Node(String nodeType, int x, int y) {
        this.nodeType = nodeType;
        this.id = Base.uniqueId++;
        xPosition = x;
        yPosition = y;
        this.listOfAttr = new Vector<Attribute>(0);
        this.listOfInputArc = new Vector<Arc>(0);
        this.listOfOutputArc = new Vector<Arc>(0);
        
        System.out.println("2. Creation dun noeud avec id:"+this.id);
    }
    
    /**
     * Constructeur
     * 
     * @param uniqueId	Identifiant du noeud
     * @param nodeType	Type du noeud
     * @param x			Position x du noeud
     * @param y			Position y du noeud
     */
    public Node(String nodeType, int x, int y, int id) {
        this.nodeType = nodeType;
        this.id = id;
        xPosition = x;
        yPosition = y;
        this.listOfAttr = new Vector<Attribute>(0);
        this.listOfInputArc = new Vector<Arc>(0);
        this.listOfOutputArc = new Vector<Arc>(0);
        
        System.out.println("3. Creation dun noeud avec id:"+this.id);
    }

    /**
     * Retourne l'identifiant du noeud 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le type du noeud
     * @return String
     */
    public String getNodeType() {
        return this.nodeType;
    }

    /**
     * This method sets the x and y position of the node
     * 
     * @param x	Position x
     * @param y Position y
     * 
     */
    public void setPosition(int x, int y) {
    	xPosition = x;
    	yPosition = y;
    }

    /**
     * Retourne la position x du noeud
     * @return int
     */
    public int getXPosition() {
        return this.xPosition;
    }

    /**
     * Retourne la position y noeud
     * @return int
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Ajout d'un arc entrant 
     * @param arc Arc a ajouter
     */
    public void addInputArc(Arc arc) {
    	this.listOfInputArc.addElement(arc);
    }

    /**
     * Supprime un arc entrant
     * @param arc Arc a supprimer
     */
    public void removeInputArc(Arc arc) {
        try {
            this.listOfInputArc.remove(arc);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Supprime un arc entrant
     * @param index	Index de l'arc a supprimer
     */
    public void removeInputArc(int index) {
        try {
            this.listOfInputArc.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Retourne le nombre d'arc entrants
     * @return int
     */
    public int getListOfInputArcSize() {
        return this.listOfInputArc.size();
    }

    /**
     * Retourne l'arc designe par son index dans la liste des arcs entrants
     * @param index Index de l'arc entrant a supprimer
     * @return Arc
     */
    public Arc getNthInputArc(int index) {
        try {
            return (Arc) this.listOfInputArc.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Ajoute un arc sortant
     * @param arc Arc a ajouter
     */
    public void addOutputArc(Arc arc) {
    	this.listOfOutputArc.addElement(arc);
    }

    /**
     * Supprime un arc sortant
     * @param arc Arc a supprimer
     */
    public void removeOutputArc(Arc arc) {
        try {
            this.listOfOutputArc.remove(arc);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Supprime un arc sortant
     * @param index Index de l'arc a supprimer
     */
    public void removeOutputArc(int index) {
        try {
            this.listOfOutputArc.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Retourne le nombre d'arcs sortants
     * @return int
     */
    public int getListOfOutputArcSize() {
        return this.listOfOutputArc.size();
    }

    /**
     * Retourne l'arc designe par son idex dans la liste des arcs sortants
     * @param index Index de l'arc dans la liste
     * @return Arc
     */
    public Arc getNthOutputArc(int index) {
        try {
            return (Arc) this.listOfOutputArc.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	return null;
        }

    }

    /**
     * Ajoute un attribut au noeud 
     * @param attribute Attribut a ajouter
     */
    public void addAttribute(Attribute attribute) {
        if (id == attribute.getRefId()) {
            this.listOfAttr.addElement(attribute);
        } 
    }

    /**
     * Suppression d'unattribut
     * @param attribute	Attribut
     */
    public void removeAttribute(Attribute attribute) {
        try {
            this.listOfAttr.remove(attribute);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Suppression d'un attribut en fonction de son index dans la liste des attributs
     * @param index Index de l'attribut dans la liste
     */
    public void removeAttribute(int index) {
        try {
            this.listOfAttr.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /**
     * Retourne le nombre d'attributs du noeud
     * @return int
     */
    public int getListOfAttrSize() {
        return this.listOfAttr.size();
    }
    
    /**
     * Retourne la liste des attributs du noeud
     * @return Vector
     */
    public Vector getListOfAttr() {
        return this.listOfAttr;
    }

    /**
     * Retourne le nieme attribut de la liste
     * @param index Index de l'attribut
     * @return Attribute
     */
    public Attribute getNthAttr(int index) {
        try {
            return (Attribute) this.listOfAttr.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	return null;
        }
    }

    /**
     * Traduction du noeud noeud en chaine de commandes CAMI
     * @return String[]
     */
    public String[] translateToCAMI() {
        String[] stringToReturn;
        StringBuffer s;
        Vector<String> vectorStringToReturn = new Vector<String>(0);
        
        s = new StringBuffer();
        s.append("CN(");
        s.append(this.nodeType.length() + ":" + this.nodeType);
        s.append(",");
        s.append(id);
        s.append(")");
        vectorStringToReturn.addElement(s.toString());
        
        s = new StringBuffer();
        s.append("PO(");
        s.append(id);
        s.append(",");
        s.append(xPosition);
        s.append(",");
        s.append(yPosition);
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

