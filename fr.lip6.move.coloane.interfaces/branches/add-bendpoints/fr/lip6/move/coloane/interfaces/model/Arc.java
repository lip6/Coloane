package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;
import java.io.Serializable;

import fr.lip6.move.coloane.interfaces.objects.IPosition;
import fr.lip6.move.coloane.interfaces.objects.Position;

/**
 * Cette classe decrit un arc generique d'un modele.<br>
 * Un arc se compose des elements suivants :
 * <ul>
 * <li> Un type regroupant des informations destinees au formalisme </li>
 * <li> Un identifiant unique le designant dans un modele</li>
 * <li> Un positionnement (X,Y)</li>
 * <li> Une liste d'attributs </li>
 * <li> Un noeud de depart (source)</li>
 * <li> Un noeud d'arrivee (cible)</li>
 * </ul>
 * Quelques contraintes de construction sont attachees a l'arc :
 * <ul>
 * <li>Un arc possede obligatoirement un noeud source et un noeud cible.</li>
 * </ul>
 * 
 * @see INode
 * @see IAttribute
 * @see IArc
 */
public abstract class Arc implements IArc, Serializable {

	/**
	 * Utilise lors de la deserialization afin de s'assurer que les versions des
	 * classes Java soient concordantes.
	 */
	private static final long serialVersionUID = 1L;

	/** Type de l'arc */
	protected String arcType;

	/** Identifiant de l'arc */
	protected int id;

	/**
	 * Vecteur contenant l'ensemble des objets de type Attribut de l'arc.
	 * 
	 * @see IAttribute
	 */
	private Vector<IAttribute> listOfAttr;

	/** Vecteur contenant l'ensemble des points intermediaire de type Point. */
	private Vector<IPosition> listOfPI;

	/** Noeud d'entree de l'arc. */
	protected INode startingNode;

	/** Noeud de sortie de l'arc. */
	protected INode endingNode;

	/**
	 * Constructeur de la classe Arc.
	 * 
	 * @param arcType
	 *            Type de l'arc
	 * @param id
	 *            Identifant unique de l'arc
	 */
	public Arc(String arcType, int id) {
		this.arcType = arcType;
		this.id = id;
		this.listOfAttr = new Vector<IAttribute>();
		this.startingNode = null;
		this.endingNode = null;
	}

	/**
	 * Constructeur de la classe Arc.
	 * 
	 * @param arcType
	 *            Type de l'arc
	 */
	public Arc(String arcType) {
		this.arcType = arcType;
		this.id = 0;
		this.listOfAttr = new Vector<IAttribute>();
		this.startingNode = null;
		this.endingNode = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getArcType()
	 */
	public String getArcType() {
		return this.arcType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getId()
	 */
	public int getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#setId(int)
	 */
	public void setId(int id) {
		this.id = id;

		// Le changement d'idientifiant implique obligatoirement
		// Le rer�f�rencement des attributs
		for (IAttribute att : this.listOfAttr) {
			att.setRefId(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#setStartingNode(fr.lip6.move.coloane.interfaces.model.Node)
	 */
	public void setStartingNode(INode node) {
		if (this.startingNode == null) {
			this.startingNode = node;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#setEndingNode(fr.lip6.move.coloane.interfaces.model.Node)
	 */
	public void setEndingNode(INode node) {
		if (this.endingNode == null) {
			this.endingNode = node;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getStartingNode()
	 */
	public INode getStartingNode() {
		return this.startingNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getEndingNode()
	 */
	public INode getEndingNode() {
		return this.endingNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#addAttribute(fr.lip6.move.coloane.interfaces.model.Attribute)
	 */
	public void addAttribute(IAttribute attribute) {
		if (!(attribute.getValue() == "")) {
			attribute.setRefId(this.getId());
			this.listOfAttr.addElement(attribute);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removeAttribute(fr.lip6.move.coloane.interfaces.model.Attribute)
	 */
	public void removeAttribute(IAttribute attribute) {
		try {
			this.listOfAttr.remove(attribute);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removeAttribute(int)
	 */
	public void removeAttribute(int index) {
		try {
			this.listOfAttr.remove(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getListOfAttrSize()
	 */
	public int getListOfAttrSize() {
		return this.listOfAttr.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getListOfAttr()
	 */
	public Vector<IAttribute> getListOfAttr() {
		return this.listOfAttr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getNthAttr(int)
	 */
	public IAttribute getNthAttr(int index) {
		IAttribute attr = null;
		try {
			attr = (IAttribute) this.listOfAttr.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return attr;
	}

	/* Retourne la liste des points intermediaires */
	public Vector<IPosition> getListOfPI() {
		return listOfPI;
	}

	/* Ajoute un élément Position de la liste des points intermediaires */
	public void addPI(int x, int y) {
		Position p = null;
		p.setPosition(x, y);
		listOfPI.addElement(p);
	}
	
	/* Supprime l'element Position de coordonnées (x,y) */
	public void removePI(int x, int y) {
	}

	/* Renvoie le index-ieme element de la liste des points intermediaires */
	public IPosition getNthPI(int index) {
		return listOfPI.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#translateToCAMI()
	 */
	public abstract String[] translate();
}
