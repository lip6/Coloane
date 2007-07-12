package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;
import java.io.Serializable;

import fr.lip6.move.coloane.interfaces.objects.IPosition;
import fr.lip6.move.coloane.interfaces.objects.Position;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

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

	/** Vecteur contenant l'ensemble des points intermediaire de type Position. */
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
		this.listOfPI = new Vector<IPosition>();
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
		this.listOfPI = new Vector<IPosition>();
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
		return this.listOfPI;
	}

	
	/**
	 * Indique si le point de coordonnees x,y appartient a la liste des points d'inflexion
	 * @param x Les abscisses
	 * @param y Les ordonnees
	 * @return -1 si aucun point n'a ete trouve ou l'indice du point si il y en a un.
	 */
	private int checkPI(int x, int y) {
		// Si une position (x,y) existe, une exception est levee
		for (int i = 0; i < this.listOfPI.size(); i++) {
			if (listOfPI.get(i).getXPosition() == x && listOfPI.get(i).getYPosition() == y) {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#addPI(int, int)
	 */
	public void addPI(int x, int y) throws SyntaxErrorException {
		addPI(x, y, this.listOfPI.size());
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#addPI(int, int, int)
	 */
	public void addPI(int x, int y, int index) throws SyntaxErrorException {
		
		// On doit verifier que le point n'existe pas deja
		if (checkPI(x, y) >= 0) {
			throw new SyntaxErrorException("Un point intermediaire de coordonnees identiques existe deja pour l'arc d'identifiant "	+ this.getId());
		} 

		// Si il est unique	
		IPosition p = new Position(x, y);
		listOfPI.add(index, p);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removePI(int, int)
	 */
	public void removePI(int x, int y) throws SyntaxErrorException {
		int i = checkPI(x, y);
		if (i >= 0) {
			this.listOfPI.remove(i);
		} else {
			throw new SyntaxErrorException("Aucun point intermediaire ne correspond pour l'arc d'identifiant "+ this.getId());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removePI(int)
	 */
	public void removePI(int index) throws SyntaxErrorException {
		if (index < this.listOfPI.size()) {
			this.listOfPI.remove(index);
		} else {
			throw new SyntaxErrorException("Impossible de trouver le point d'inflexion a supprimer");
		}
	}
	
	public void modifyPI(int index, int newX, int newY) {
		IPosition p = this.listOfPI.get(index);
		p.setPosition(newX, newY);		
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getNthPI(int)
	 */
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
