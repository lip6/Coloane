package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Arbre de résultat, à voir comme un tableau avec :
 * <ul>
 *   <li>l'arborescence décris les lignes</li>
 *   <li>une liste d'éléments pour les colonnes</li>
 * </ul>
 */
public class ResultTreeImpl extends Observable implements IResultTree {
	private static final SessionManager MANAGER = Coloane.getDefault().getMotor().getSessionManager();

	private IResultTree parent;
	private ArrayList<IResultTree> children;
	private ArrayList<Object> elements;
	private final ArrayList<Integer> highlights;


	/**
	 * Constructeur d'un sous-arbre de resultats avec une liste d'objets à mettre en valeur
	 * @param toHighlight La liste d'objets à mettre en valeur
	 * @param elements Les elements composant le resultat
	 */
	public ResultTreeImpl(List<Integer> toHighlight, String...elements) {
		// Element a mettre en valeur lors de la selection de cet arbre (feuille) de resultat
		highlights = new ArrayList<Integer>();

		if (toHighlight != null) {
			this.highlights.addAll(toHighlight);
		}

		// Description des enfants du sous arbre
		children = new ArrayList<IResultTree>();
		this.elements = new ArrayList<Object>();
		for (String element : elements) {
			this.elements.add(element);
		}
		try {
			this.addObserver(MANAGER.getCurrentServiceResult());
		} catch (NullPointerException e) {
			Coloane.getLogger().warning("Erreur dans l'attachement de la liste de resultats a la session");
		}
	}

	/**
	 * Constructeur d'un sous-arbre de resultats avec un objet a mettre en valeur
	 * @param toHighlight L'identifiant de l'objet a mettre en valeur
	 * @param elements Les elements composant le resultat
	 */
	public ResultTreeImpl(int toHighlight, String... elements) {
		this(null, elements);
		this.addHighlighted(toHighlight);
	}

	/**
	 * Constructeur d'un sous-arbre de resultats sans mise en valeur particulière
	 * @param elements Les elements (colonnes) composant le resultat
	 */
	public ResultTreeImpl(String... elements) {
		this(null, elements);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getParent()
	 */
	public final IResultTree getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#setParent(fr.lip6.move.coloane.core.results.IResultTree)
	 */
	public final void setParent(IResultTree parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getChildren()
	 */
	public final List<IResultTree> getChildren() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#addChild(fr.lip6.move.coloane.core.results.IResultTree)
	 */
	public final void addChild(IResultTree child) {
		children.add(child);
		child.setParent(this);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getElement()
	 */
	public final List<Object> getElement() {
		return elements;
	}


	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#setHighlighted(int[])
	 */
	public final void addHighlighted(int... toHighlight) {
		for (Integer id : toHighlight) {
			this.highlights.add(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getHighlight()
	 */
	public final List<Integer> getHighlighted() {
		return this.highlights;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#remove()
	 */
	public final void remove() {
		if (parent != null) {
			parent.getChildren().remove(this);
			this.parent = null;
		} else {
			MANAGER.getCurrentServiceResult().getChildren().remove(this);
		}
		setChanged();
		notifyObservers();
	}
}
