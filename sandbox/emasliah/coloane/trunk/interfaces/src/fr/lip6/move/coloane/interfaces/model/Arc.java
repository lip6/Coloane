package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.objects.IInflexPoint;
import fr.lip6.move.coloane.interfaces.objects.InflexPoint;

import java.util.Vector;

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
 * @see INode
 * @see IAttribute
 * @see IArc
 */
public abstract class Arc implements IArc {

	/** Type de l'arc */
	protected String type;

	/** Identifiant de l'arc */
	protected int id;

	/**
	 * Vecteur contenant l'ensemble des objets de type Attribut de l'arc.
	 * @see IAttribute
	 */
	protected Vector<IAttribute> listOfAttr;

	/** Vecteur contenant l'ensemble des points intermediaire de type Position. */
	protected Vector<IInflexPoint> listOfPI;

	/** Noeud d'entree de l'arc. */
	protected INode startingNode;

	/** Noeud de sortie de l'arc. */
	protected INode endingNode;

	/**
	 * Constructeur de la classe Arc.
	 * @param arcType Type de l'arc
	 * @param arcId Identifant unique de l'arc
	 */
	public Arc(String arcType, int arcId) {
		this.type = arcType;
		this.id = arcId;
		this.listOfAttr = new Vector<IAttribute>();
		this.listOfPI = new Vector<IInflexPoint>();
		this.startingNode = null;
		this.endingNode = null;
	}

	/**
	 * Constructeur de la classe Arc.
	 * @param arcType Type de l'arc
	 */
	public Arc(String arcType) {
		this (arcType, 0);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getArcType()
	 */
	public final String getArcType() {
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#setType(java.lang.String)
	 */
	public final void setArcType(String arcType) {
		this.type = arcType;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getId()
	 */
	public final int getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#setId(int)
	 */
	public final void setId(int arcId) {
		this.id = arcId;

		// L'attribution d'un identifiant implique obligatoirement
		// le rereferencement des attributs. On parcours donc cette liste
		// En modifiant chacun de ses membres.
		for (IAttribute att : this.listOfAttr) {
			att.setRefId(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#setStartingNode(fr.lip6.move.coloane.interfaces.model.Node)
	 */
	public final void setStartingNode(INode node) {
		this.startingNode = node;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#setEndingNode(fr.lip6.move.coloane.interfaces.model.Node)
	 */
	public final void setEndingNode(INode node) {
		this.endingNode = node;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getStartingNode()
	 */
	public final INode getStartingNode() {
		return this.startingNode;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getEndingNode()
	 */
	public final INode getEndingNode() {
		return this.endingNode;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#addAttribute(fr.lip6.move.coloane.interfaces.model.Attribute)
	 */
	public final void addAttribute(IAttribute attribute) {
		if (!(attribute.getValue() == "")) {
			attribute.setRefId(this.getId());
			this.listOfAttr.addElement(attribute);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removeAttribute(fr.lip6.move.coloane.interfaces.model.Attribute)
	 */
	public final void removeAttribute(IAttribute attribute) throws ModelException {
		try {
			this.listOfAttr.remove(attribute);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ModelException("Cannot delete the attribute. It does not exists !");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removeAttribute(int)
	 */
	public final void removeAttribute(int index) throws ModelException {
		try {
			this.listOfAttr.remove(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ModelException("Cannot delete the attribute. It does not exists !");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getListOfAttrSize()
	 */
	public final int getListOfAttrSize() {
		return this.listOfAttr.size();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getListOfAttr()
	 */
	public final Vector<IAttribute> getListOfAttr() {
		return this.listOfAttr;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getNthAttr(int)
	 */
	public final IAttribute getNthAttr(int index) {
		try {
			return (IAttribute) this.listOfAttr.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Retourne la liste des points intermediaires de l'arc
	 * @return Vector<IPosition>
	 * @see IInflexPoint
	 */
	public final Vector<IInflexPoint> getListOfPI() {
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
	public final void addPI(int x, int y) throws ModelException {
		this.addPI(x, y, this.listOfPI.size());
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#addPI(int, int, int)
	 */
	public final void addPI(int x, int y, int index) throws ModelException {

		// On doit verifier que le point n'existe pas deja
		if (checkPI(x, y) >= 0) {
			throw new ModelException("A bendpoint already exists for these coordinates");
		}

		// Si il est unique
		IInflexPoint p = new InflexPoint(x, y);
		listOfPI.add(index, p);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removePI(int, int)
	 */
	public final void removePI(int x, int y) throws ModelException {
		int i = checkPI(x, y);
		if (i >= 0) {
			this.listOfPI.remove(i);
		} else {
			throw new ModelException("Aucun point intermediaire ne correspond pour l'arc d'identifiant " + this.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#removePI(int)
	 */
	public final void removePI(int index) throws ModelException {
		if (index < this.listOfPI.size()) {
			this.listOfPI.remove(index);
		} else {
			throw new ModelException("Impossible de trouver le point d'inflexion a supprimer");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#modifyPI(int, int, int)
	 */
	public final void modifyPI(int index, int newX, int newY) throws ModelException {
		try {
			IInflexPoint p = this.listOfPI.get(index);
			p.setPosition(newX, newY);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ModelException("The bendpoint does not exist");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#getNthPI(int)
	 */
	public final IInflexPoint getNthPI(int index) {
		try {
			return listOfPI.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IArc#translateToCAMI()
	 */
	public abstract String[] translate();
}
