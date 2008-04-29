package fr.lip6.move.coloane.core.results_new;

import java.util.List;

public interface IResultTree {
	public IResultTree getParent();

	public void setParent(IResultTree parent);

	public List<IResultTree> getChildren();
	
	public void addChild(IResultTree child);

	public List<Object> getElement();
}
