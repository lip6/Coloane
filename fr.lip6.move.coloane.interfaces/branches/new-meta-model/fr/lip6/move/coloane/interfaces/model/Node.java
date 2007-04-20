package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;
import java.io.Serializable;

/**
 * Cette classe represente un noeud generique dans un modele (tout aussi generique).<br>
 * Un oeud se compose de la maniere suivante :
 * <ul>
 * 	<li> Un type qui peut servir au formalisme. Ce type est une chaine de caractere</li>
 * 	<li> Un identifiant unique le designant au sein d'un modele</li>
 * 	<li> Un positionnement (X,Y)</li>
 * 	<li> Une liste d'arcs entrants</li>
 * 	<li> Une liste d'arcs sortants</li>
 * 	<li> Une liste d'attributs</li>
 * </ul>
 * @see INode
 * @see IAttribute
 * @see IArc
 */
public abstract class Node implements INode, Serializable {

    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Type du noeud */
    protected String nodeType;
    
    /** Identificateur du noeud */
    protected int id;
    
    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    protected int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    protected int yPosition;

    /** Liste des arc entrants */
    private Vector<IArc> listOfInputArc;

    /** Liste des arcs sortants */
    private Vector<IArc> listOfOutputArc;

    /** Liste des attributs du noeud*/
    private Vector<IAttribute> listOfAttr;

    /**
     * Constructeur
     * 
     * @param nodeType Type du noeud
     * @see IAttribute
     * @see IArc
     */
    public Node(String nodeType) {
    	this.nodeType = nodeType;
        this.id = 0;
        xPosition = 0;
        yPosition = 0;
        this.listOfAttr = new Vector<IAttribute>();
        this.listOfInputArc = new Vector<IArc>();
        this.listOfOutputArc = new Vector<IArc>();
    }
    
    /**
     * Constructeur
     * 
     * @param nodeType Type du noeud
     * @param x	Position x du noeud
     * @param y	Position y du noeud
     * @see IAttribute
     * @see IArc
     */
    public Node(String nodeType, int x, int y) {
        this.nodeType = nodeType;
        this.id = 0;
        xPosition = x;
        yPosition = y;
        this.listOfAttr = new Vector<IAttribute>();
        this.listOfInputArc = new Vector<IArc>();
        this.listOfOutputArc = new Vector<IArc>();
    }
    
    /**
     * Constructeur
     * 
     * @param id Identifiant du noeud
     * @param nodeType Type du noeud
     * @param x	Position x du noeud
     * @param y	Position y du noeud
     * @see IAttribute
     * @see IArc
     */
    public Node(String nodeType, int x, int y, int id) {
    	
        this.nodeType = nodeType;
        this.id = id;
        xPosition = x;
        yPosition = y;
        this.listOfAttr = new Vector<IAttribute>();
        this.listOfInputArc = new Vector<IArc>();
        this.listOfOutputArc = new Vector<IArc>();
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getId()
	 */
    public int getId() {
        return id;
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#setId(int)
	 */
    public void setId(int id) {
        this.id = id;
        
        // Le changement d'idientifiant implique obligatoirement
        // Le rer�f�rencement des attributs
        for (IAttribute att: this.listOfAttr) {
        	att.setRefId(id);
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNodeType()
	 */
    public String getNodeType() {
        return this.nodeType;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#setPosition(int, int)
	 */
    public void setPosition(int x, int y) {
    	xPosition = x;
    	yPosition = y;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getXPosition()
	 */
    public int getXPosition() {
        return this.xPosition;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getYPosition()
	 */
    public int getYPosition() {
        return yPosition;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#addInputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
    public void addInputArc(IArc arc) {
    	this.listOfInputArc.addElement(arc);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeInputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
    public void removeInputArc(IArc arc) {
        try {
            this.listOfInputArc.remove(arc);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeInputArc(int)
	 */
    public void removeInputArc(int index) {
        try {
            this.listOfInputArc.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfInputArcSize()
	 */
    public int getListOfInputArcSize() {
        return this.listOfInputArc.size();
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNthInputArc(int)
	 */
    public IArc getNthInputArc(int index) {
        try {
            return (IArc) this.listOfInputArc.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#addOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
    public void addOutputArc(IArc arc) {
    	this.listOfOutputArc.addElement(arc);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
    public void removeOutputArc(IArc arc) {
        try {
            this.listOfOutputArc.remove(arc);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeOutputArc(int)
	 */
    public void removeOutputArc(int index) {
        try {
            this.listOfOutputArc.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfOutputArcSize()
	 */
    public int getListOfOutputArcSize() {
        return this.listOfOutputArc.size();
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNthOutputArc(int)
	 */
    public IArc getNthOutputArc(int index) {
        try {
            return (IArc) this.listOfOutputArc.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	return null;
        }

    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)
	 */
    public void addAttribute(IAttribute attribute) {
        if (this.id == attribute.getRefId()) {
            this.listOfAttr.addElement(attribute);
        } 
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)
	 */
    public void removeAttribute(IAttribute attribute) {
        try {
            this.listOfAttr.remove(attribute);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeAttribute(int)
	 */
    public void removeAttribute(int index) {
        try {
            this.listOfAttr.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	e.printStackTrace();
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfAttrSize()
	 */
    public int getListOfAttrSize() {
        return this.listOfAttr.size();
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfAttr()
	 */
    public Vector<IAttribute> getListOfAttr() {
        return this.listOfAttr;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNthAttr(int)
	 */
    public IAttribute getNthAttr(int index) {
        try {
            return (IAttribute) this.listOfAttr.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	return null;
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#translateToCAMI()
	 */
    public abstract String[] translate();


/****** AJOUTS POUR TESTS UNITAIRES******/

    public Vector<IArc> getListOfInputArc() {
    	return this.listOfInputArc;
    }

    public Vector<IArc> getListOfOutputArc() {
    	return this.listOfOutputArc;
    }
/*** FIN DES AJOUTS ***/
}