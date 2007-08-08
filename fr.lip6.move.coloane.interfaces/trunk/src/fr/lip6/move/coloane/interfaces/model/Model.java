package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Cette classe represente un modele generique.<br>
 * Elle permet la manipulation des elements du modele et le parcours de ceux-ci.<br>
 * Un modele se compose de :
 * <ul>
 * <li> Une liste de noeuds</li>
 * <li> Une liste d'attributs</li>
 * <li> Une liste d'arcs</li>
 * </ul>
 * A noter qu'un modele a toujours un identifiant egal a 1<br>
 * Le modele generique porte l'information sur son formalisme en tant que chaine
 * de caracteres.
 */
public abstract class Model implements IModel {

	/** Type du modele. */
	private String formalism = "";

	/**
	 * Position absolue horizontale depuis le bord gauche de la fenetre
	 * d'affichage du modele.
	 */
	private int xPosition;

	/**
	 * Position absolue verticale depuis le bord haut de la fenetre d'affichage
	 * du modele.
	 */
	private int yPosition;

	/** Liste de l'ensemble des elements noeuds du modele. */
	private Vector<INode> listOfNode;

	/** Liste de l'ensemble des attributs du modele. */
	private Vector<IAttribute> listOfAttr;

	/** Liste de l'ensemble des arcs du modele. */
	private Vector<IArc> listOfArc;

	/** ID maximum du modele */
	private int maxId;

	/** Liste des id presents dans le modele. */
	private Vector<Integer> listOfId;

	/**
	 * Constructeur
	 */
	public Model() {
		this.xPosition = INITIAL_X;
		this.yPosition = INITIAL_Y;
		this.listOfArc = new Vector<IArc>();
		this.listOfAttr = new Vector<IAttribute>();
		this.listOfNode = new Vector<INode>();
		this.listOfId = new Vector<Integer>();
		this.maxId = 1;
		this.listOfId.addElement(Integer.valueOf(maxId));
	}

	/**
	 * Constructeur
	 * @param commands Une suite de commandes qui permettent de construire le modele.
	 */
	public Model(Vector<String> commands) throws SyntaxErrorException {
		this();

		// Construction du modele a partir des commandes recues
		try {
			this.buildModel(commands);
		} catch (SyntaxErrorException e) {
			throw new SyntaxErrorException("Cannot build the model : " + e.getMessage());
		}
	}

	/**
	 * Constructeur
	 * @param commandsFile Un fichier contenant des commandes pour construire le modele
	 * @throws IOException Erreur de lecture dans le fichier
	 */
	public Model(File commandsFile) throws SyntaxErrorException {
		this();

		// Un vecteur de commandes de construction
		Vector<String> commands = new Vector<String>();

		try {
			// Lecture du fichier
			BufferedReader buffer = new BufferedReader(new FileReader(commandsFile));
			while (buffer.ready()) {
				commands.add(buffer.readLine());
			}
			buffer.close();

			this.buildModel(commands);

		} catch (NoSuchElementException e) {
			throw new SyntaxErrorException("Cannot build the model : " + e.getMessage());

		// Erreur de localisation du fichier
		} catch (FileNotFoundException e) {
			throw new SyntaxErrorException("Cannot build the model (file not found)");

		// Erreur de lecture du fichier
		} catch (IOException e) {
			throw new SyntaxErrorException("Cannot build the model (cannot read the file)");

		// Erreur de syntaxe
		} catch (SyntaxErrorException e) {
			throw new SyntaxErrorException("Cannot build the model : " + e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getANode(int)
	 */
	public final INode getANode(int uniqueId) {
		for (INode node : this.listOfNode) {
			if (node.getId() == uniqueId) {
				return node;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getAnArc(int)
	 */
	public final IArc getAnArc(int uniqueId) {
		for (IArc arc : this.listOfArc) {
			if (arc.getId() == uniqueId) {
				return arc;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#addNode(fr.lip6.move.coloane.interfaces.model.INode)
	 */
	public final void addNode(INode node) throws ModelException {

		// On ajoute le noeud seulement s'il n'est pas deja dans le modele
		if (!this.listOfNode.contains(node)) {

			// On leve une exception si l'id de node est deja present dans le modele
			if (!(getAnArc(node.getId()) == null) || !(getANode(node.getId()) == null) || node.getId() == 1) {
				throw new ModelException("A node with the same ID already exists");
			}

			this.listOfNode.addElement(node);

			// Gestion de l'identifiant du noeud
			if (node.getId() == 0) {
				node.setId(this.getMaxId() + 1);
				setMaxId(this.getMaxId() + 1);
			}

			this.listOfId.addElement(Integer.valueOf(node.getId()));
		}

	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#addArc(fr.lip6.move.coloane.interfaces.model.Arc)
	 */
	public final void addArc(IArc arc) throws ModelException {
		INode start = arc.getStartingNode();
		INode end = arc.getEndingNode();

		// Si l'arc est deja dans le modele...
		if (this.listOfArc.contains(arc)) {
			return;
		}

		if ((start != null) && (end != null)) {
			// Les noeuds cible et source de l'arc doivent etre present dans le modele
			if ((listOfNode.contains(start)) && (listOfNode.contains(end))) {

				// On leve une exception si l'id de arc est deja present dans le modele
				if (!(getAnArc(arc.getId()) == null) || !(getANode(arc.getId()) == null) || arc.getId() == 1) {
					throw new ModelException("An arc with the same ID already exists");
				}

				// On indique aux noeuds cible et source la presence de l'arc
				start.addOutputArc(arc);
				end.addInputArc(arc);

				this.listOfArc.addElement(arc);

				// Gestion de l'identifiant du noeud
				if (arc.getId() == 0) {
					arc.setId(this.getMaxId() + 1);
					setMaxId(this.getMaxId() + 1);
				}
				this.listOfId.addElement(Integer.valueOf(arc.getId()));
			} else {
				throw new ModelException("Cannot add the arc : A parent node is missing");
			}
		} else {
			throw new ModelException("Cannot add the arc : A parent node is missing");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)
	 */
	public final void addAttribute(IAttribute attribute) {
		if (!(attribute.getValue() == "")) {
			attribute.setRefId(1);
			this.listOfAttr.addElement(attribute);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#removeNode(fr.lip6.move.coloane.interfaces.model.INode)
	 */
	public final void removeNode(INode node) throws ModelException {

		if (this.listOfNode.contains(node)) {
			int nbIn = node.getListOfInputArcSize();
			int nbOut = node.getListOfOutputArcSize();

			try {
				// On supprime les arcs entrants
				for (int i = 0; i < nbIn; i++) {
					IArc in = node.getNthInputArc(0);
					this.removeArc(in);
				}

				// On supprime les arcs sortants
				for (int i = 0; i < nbOut; i++) {
					IArc out = node.getNthOutputArc(0);
					this.removeArc(out);
				}

				this.listOfId.remove(Integer.valueOf(node.getId()));
				this.listOfNode.remove(node);
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new ModelException("An error has occured during node deletion");
			}
		} else {
			throw new ModelException("The node does not exist");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#removeArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
	public final void removeArc(IArc arc) throws ModelException {

		if (this.listOfArc.contains(arc)) {

			// Suppression de la liste des arcs
			this.listOfArc.remove(arc);

			// Les noeuds source et cible ne doivent plus pointer vers l'arc
			INode in = arc.getStartingNode();
			INode out = arc.getEndingNode();

			if (in != null) {
				in.removeOutputArc(arc);
			}
			if (out != null) {
				out.removeInputArc(arc);
			}

			this.listOfId.remove(Integer.valueOf(arc.getId()));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getListOfAttrSize()
	 */
	public final int getListOfAttrSize() {
		return this.listOfAttr.size();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getListOfArcSize()
	 */
	public final int getListOfArcSize() {
		return this.listOfArc.size();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getListOfNodeSize()
	 */
	public final int getListOfNodeSize() {
		return this.listOfNode.size();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getNthAttr(int)
	 */
	public final IAttribute getNthAttr(int index) {
		try {
			return (IAttribute) this.listOfAttr.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getNthArc(int)
	 */
	public final IArc getNthArc(int index) {
		try {
			return (IArc) this.listOfArc.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getNthNode(int)
	 */
	public final INode getNthNode(int index) {
		try {
			return (INode) this.listOfNode.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getXPosition()
	 */
	public final int getXPosition() {
		return this.xPosition;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getYPosition()
	 */
	public final int getYPosition() {
		return this.yPosition;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#setPosition(int, int)
	 */
	public final void setPosition(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#setFormalism(java.lang.String)
	 */
	public final void setFormalism(String form) {
		this.formalism = form;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getFormalism()
	 */
	public final String getFormalism() {
		return this.formalism;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getMaxId()
	 */
	public final int getMaxId() {
		int max = 1;
		INode nodes;
		IArc arcs;

		// Parcours des noeuds
		for (int i = 0; i < this.getListOfNodeSize(); i++) {
			nodes = getNthNode(i);

			if (max < nodes.getId()) {
				max = nodes.getId();
			}
		}

		// Parcours des arcs
		for (int i = 0; i < this.getListOfArcSize(); i++) {
			arcs = getNthArc(i);

			if (max < arcs.getId()) {
				max = arcs.getId();
			}
		}

		return max;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#setMaxId(int)
	 */
	public final int setMaxId(int max) {
		this.maxId = max;
		return max;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#translate()
	 */
	public abstract String[] translate();

	/**
	 * Renvoie la liste des ID des elements du modele
	 * @return Vector<Integer> La liste
	 */
	public final Vector<Integer> getListOfId() {
		return listOfId;
	}
}
