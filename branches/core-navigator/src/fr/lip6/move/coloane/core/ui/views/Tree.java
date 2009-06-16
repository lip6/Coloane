package fr.lip6.move.coloane.core.ui.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Tree structure for the model of the Models Navigator.
 * @param <T> Type of element contains in nodes of the tree.
 *
 * @author Clément Démoulins
 */
public class Tree<T> {

	private Tree<T> parent = null;
	private List<Tree<T>> children = new ArrayList<Tree<T>>();
	
	private T element;
	private ImageDescriptor icon;

	/**
	 * @param element element contains in this node
	 */
	public Tree(T element) {
		this.element = element;
	}

	/**
	 * @return the element
	 */
	public final T getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public final void setElement(T element) {
		this.element = element;
	}

	/**
	 * @return the icon
	 */
	public final ImageDescriptor getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public final void setIcon(ImageDescriptor icon) {
		this.icon = icon;
	}

	/**
	 * @return the parent
	 */
	public final Tree<T> getParent() {
		return parent;
	}

	/**
	 * @return a readonly list of the children
	 */
	public final List<Tree<T>> getChildren() {
		return Collections.unmodifiableList(children);
	}

	/**
	 * Add a child to this node, if the child was attached to another node it's previously removed.
	 * @param child child to add
	 */
	public final void addChild(Tree<T> child) {
		if (child.getParent() != null) {
			child.getParent().removeChild(child);
		}
		children.add(child);
		child.setParent(this);
	}
	
	/**
	 * @param child child to remove
	 * @return <code>true</code> if the child was removed
	 */
	public final boolean removeChild(Tree<T> child) {
		boolean r = children.remove(child);
		if (r) {
			child.setParent(null);
		}
		return r;
	}

	/**
	 * @param parent the parent to set
	 */
	final void setParent(Tree<T> parent) {
		this.parent = parent;
	}
}
