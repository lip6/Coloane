package fr.lip6.move.coloane.core.results;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.results.reports.GenericReport;
import fr.lip6.move.coloane.core.results.reports.IReport;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

/**
 * Liste de ResultTree. Cette liste étends Observable.<br>
 * Cette classe est thread-safe.
 */
public class ResultTreeList extends Observable implements IResultTree, Observer {
	/**
	 * Attributs du point d'extension 'exports'
	 */
	private static final String EXTENSION_POINT_ID = "fr.lip6.move.coloane.core.reports"; //$NON-NLS-1$
	private static final String SERVICE_EXTENSION = "service_name"; //$NON-NLS-1$
	private static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$

	
	private final CopyOnWriteArrayList<IResultTree> list;
	private final ArrayList<List<Integer>> highlights;
	private final HashMap<String, IReport> services;
	private final IReport generic;
	
	public ResultTreeList() {
		list = new CopyOnWriteArrayList<IResultTree>();
		highlights = new ArrayList<List<Integer>>();
		services = new HashMap<String, IReport>();
		generic = new GenericReport();
		
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for(IConfigurationElement element:reg.getConfigurationElementsFor(EXTENSION_POINT_ID)) {
			String service = element.getAttribute(SERVICE_EXTENSION);
			try {
				IReport report = (IReport)element.createExecutableExtension(CLASS_EXTENSION);
				services.put(service, report);
				Coloane.getLogger().fine("Ajout du service de résultat : "+service);
			} catch (CoreException e) {
				Coloane.getLogger().warning("Problème avec l'extension : "+service);
			}
		}
	}
	
	public void add(String serviceName, IResultsCom result) {
		IResultTree newResult = null;
		List<Integer> newHighlight = null;
		
		IReport report = services.get(serviceName);
		if(report != null) {
			newResult = report.build(result);
			newHighlight = report.highlightNode(result);
		}

		if(newResult == null){
			newResult = generic.build(result);
		}
		
		if(newHighlight == null)
			newHighlight = new ArrayList<Integer>();
		
		list.add(newResult);
		highlights.add(newHighlight);
		update(null, getWidth(newResult));
	}
	
	private int getWidth(IResultTree node) {
		if(node==null)
			return -1;
		int max = node.getElement().size();
		for(IResultTree child:node.getChildren())
			max = Math.max(max, getWidth(child));
		return max;
	}

	public void addChild(IResultTree child) {
		list.add(child);
		update(null, null);
	}

	public List<IResultTree> getChildren() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public List getElement() {
		return list;
	}

	public IResultTree getParent() {
		return null;
	}

	public void setParent(IResultTree parent) {
	}

	public int getId() {
		return -1;
	}

	public List<Integer> getHighlight() {
		return highlights.get(highlights.size()-1);
	}

	public List<Integer> getHighlight(int index) {
		return highlights.get(index);
	}
	
	public List<Integer> getHighlight(IResultTree node) {
		return getHighlight(list.indexOf(node));
	}

	public void remove() {
	}

	public void update(Observable o, Object arg) {
		setChanged();
		Integer width;
		if(arg!=null)
			width = (Integer)arg;
		else
			width = 0;
		
		notifyObservers(width);
	}

	@Override
	public String toString() {
		return list.toString();
	}

	public void removeAll() {
		list.clear();
		update(null, 0);
	}
}
