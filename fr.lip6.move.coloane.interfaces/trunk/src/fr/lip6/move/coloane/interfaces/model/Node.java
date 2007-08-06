package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;

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
public abstract class Node implements INode {

	/** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
	private static final long serialVersionUID = 1L;

	/** Type du noeud */
	protected String type;

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
		this.type = nodeType;
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
		this.type = nodeType;
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
	public Node(String nodeType, int x, int y, int nodeId) {

		this.type = nodeType;
		this.id = nodeId;
		xPosition = x;
		yPosition = y;
		this.listOfAttr = new Vector<IAttribute>();
		this.listOfInputArc = new Vector<IArc>();
		this.listOfOutputArc = new Vector<IArc>();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getId()
	 */
	public final int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#setId(int)
	 */
	public final void setId(int nodeId) {
		this.id = nodeId;

		// Le changement d'idientifiant implique obligatoirement
		// Le referencement des attributs
		for (IAttribute att : this.listOfAttr) {
			att.setRefId(id);
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNodeType()
	 */
	public final String getNodeType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#setPosition(int, int)
	 */
	public final void setPosition(int x, int y) {
		xPosition = x;
		yPosition = y;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getXPosition()
	 */
	public final int getXPosition() {
		return this.xPosition;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getYPosition()
	 */
	public final int getYPosition() {
		return yPosition;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#addInputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
	public final void addInputArc(IArc arc) {
		this.listOfInputArc.addElement(arc);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeInputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
	public final void removeInputArc(IArc arc) {
		try {
			this.listOfInputArc.remove(arc);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeInputArc(int)
	 */
	public final void removeInputArc(int index) {
		try {
			this.listOfInputArc.remove(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfInputArcSize()
	 */
	public final int getListOfInputArcSize() {
		return this.listOfInputArc.size();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNthInputArc(int)
	 */
	public final IArc getNthInputArc(int index) {
		try {
			return (IArc) this.listOfInputArc.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#addOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
	public final void addOutputArc(IArc arc) {
		this.listOfOutputArc.addElement(arc);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeOutputArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
	public final void removeOutputArc(IArc arc) {
		try {
			this.listOfOutputArc.remove(arc);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeOutputArc(int)
	 */
	public final void removeOutputArc(int index) {
		try {
			this.listOfOutputArc.remove(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfOutputArcSize()
	 */
	public final int getListOfOutputArcSize() {
		return this.listOfOutputArc.size();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNthOutputArc(int)
	 */
	public final IArc getNthOutputArc(int index) {
		try {
			return (IArc) this.listOfOutputArc.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}

	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)
	 */
	public final void addAttribute(IAttribute attribute) {
		if (!(attribute.getValue() == "")) {
			attribute.setRefId(this.getId());
			this.listOfAttr.addElement(attribute);
		}

	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)
	 */
	public final void removeAttribute(IAttribute attribute) {
		try {
			this.listOfAttr.remove(attribute);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#removeAttribute(int)
	 */
	public final void removeAttribute(int index) {
		try {
			this.listOfAttr.remove(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfAttrSize()
	 */
	public final int getListOfAttrSize() {
		return this.listOfAttr.size();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getListOfAttr()
	 */
	public final Vector<IAttribute> getListOfAttr() {
		return this.listOfAttr;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.INode#getNthAttr(int)
	 */
	public final IAttribute getNthAttr(int index) {
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

	public final Vector<IArc> getListOfInputArc() {
		return this.listOfInputArc;
	}

	public final Vector<IArc> getListOfOutputArc() {
		return this.listOfOutputArc;
	}
	/*** FIN DES AJOUTS ***/
}
