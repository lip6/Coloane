package fr.lip6.move.coloane.core.results_new;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.lip6.move.coloane.core.results_new.reports.GenericReport;
import fr.lip6.move.coloane.core.results_new.reports.IReport;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

/**
 * Liste de ResultTree. Cette liste étends Observable.<br>
 * Cette classe est thread-safe.
 */
public class ResultTreeList extends Observable implements IResultTree {
	private final CopyOnWriteArrayList<IResultTree> list;
	private final HashMap<String, IReport> services;
	private final IReport generic;
	
	public ResultTreeList() {
		list = new CopyOnWriteArrayList<IResultTree>();
		services = new HashMap<String, IReport>();
		generic = new GenericReport();
	}
	
	public void add(String serviceName, IResultsCom result) {
		IReport report = services.get(serviceName);
		IResultTree newResult;
		if(report != null)
			newResult = report.build(result);
		else
			newResult = generic.build(result);
		list.add(newResult);
		setChanged();
		notifyObservers(getWidth(newResult));
	}
	
	private int getWidth(IResultTree node) {
		int max = node.getElement().size();
		for(IResultTree child:node.getChildren())
			max = Math.max(max, getWidth(child));
		return max;
	}

	@Override
	public void addChild(IResultTree child) {
		list.add(child);
		setChanged();
		notifyObservers();
	}

	@Override
	public List<IResultTree> getChildren() {
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getElement() {
		return list;
	}

	@Override
	public IResultTree getParent() {
		return null;
	}

	@Override
	public void setParent(IResultTree parent) {
	}
}
