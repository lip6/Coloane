package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Arbre de résultat, à voir comme un tableau avec :<ul>
 * <li>l'arborescence décris les lignes</li>
 * <li>une liste d'éléments pour les colonnes</li>
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
		this.addObserver(MANAGER.getCurrentServiceResult());
	}

	public ResultTreeImpl(String... elements) {
		this(-1, elements);
	}

	public final IResultTree getParent() {
		return parent;
	}

	public final void setParent(IResultTree parent) {
		this.parent = parent;
	}

	public final List<IResultTree> getChildren() {
		return children;
	}

	public final void addChild(IResultTree child) {
		children.add(child);
		child.setParent(this);
	}

	public final List<Object> getElement() {
		return elements;
	}

	public final int getId() {
		return id;
	}

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
