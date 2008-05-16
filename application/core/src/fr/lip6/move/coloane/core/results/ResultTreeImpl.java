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
	private int id;

	public ResultTreeImpl(int id, String... elements) {
		this.id = id;
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

	public ResultTreeImpl(String... elements) {
		this(-1, elements);
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
	 * @see fr.lip6.move.coloane.core.results.IResultTree#getId()
	 */
	public final int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.results.IResultTree#remove()
	 */
	public final void remove() {
		if (parent != null) {
			parent.getChildren().remove(this);
		} else {
			MANAGER.getCurrentServiceResult().getChildren().remove(this);
		}
		setChanged();
		notifyObservers();
	}
}
