package fr.lip6.move.coloane.core.results_new;

import java.util.ArrayList;
import java.util.List;

/**
 * Arbre de résultat, à voir comme un tableau avec :<ul>
 * <li>l'arborescence décris les lignes</li>
 * <li>une liste d'éléments pour les colonnes</li>
 * </ul>
 */
public class ResultTreeImpl implements IResultTree {
	private IResultTree parent;
	private ArrayList<IResultTree> children;
	
	private ArrayList<Object> elements;
	
	public ResultTreeImpl(String... elements) {
		children = new ArrayList<IResultTree>();
		this.elements = new ArrayList<Object>();
		for(String element:elements)
			this.elements.add(element);
	}

	public IResultTree getParent() {
		return parent;
	}

	public void setParent(IResultTree parent) {
		this.parent = parent;
	}

	public List<IResultTree> getChildren() {
		return children;
	}
	
	public void addChild(IResultTree child) {
		children.add(child);
		child.setParent(this);
	}

	public List<Object> getElement() {
		return elements;
	}
}
