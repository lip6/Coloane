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

	/* Ajoute un élément Position de la liste des points intermediaires */
	public void addPI(int x, int y) throws SyntaxErrorException {
		
		//Si une position (x,y) existe, une exception est levée
		for (int i = 0; i < this.listOfPI.size(); i++) {
			if (listOfPI.get(i).getXPosition() == x
					&& listOfPI.get(i).getYPosition() == y) {
				throw new SyntaxErrorException("Un point intermediaire de coordonnées ("
						+ x
						+ ","
						+ y
						+ ") existe déjà pour l'arc d'identifiant "
						+ this.getId());
			}
		}
		
		//Sinon on ajoute la position
		IPosition p = new Position(x, y);
		listOfPI.addElement(p);

	}

	/* Supprime l'element Position de coordonnées (x,y) */
	public void removePI(int x, int y) throws SyntaxErrorException {
		
		//indique la presence d'un point de coordonnees (x,y)
		boolean presence = false;
		
		//Test pour chaque position si ses coordonées sont (x,y)
		for (int i = 0; i < this.listOfPI.size(); i++) {
			if (listOfPI.get(i).getXPosition() == x
					&& listOfPI.get(i).getYPosition() == y) {
				
				//On supprime la position trouvée
				this.listOfPI.remove(i);
				presence=true;
			}
		}
		
		//Si aucune position (x,y) n'est trouvée, on léve une exception
		if (!presence) {
			throw new SyntaxErrorException("Aucun point intermediaire de coordonnées("
					+ x
					+ ","
					+ y
					+ ") existe pour l'arc d'identifiant "
					+ this.getId());

		}
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
