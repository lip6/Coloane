package fr.lip6.move.coloane.projects.its.checks.ui;

import fr.lip6.move.coloane.projects.its.checks.AbstractCheckService;
import fr.lip6.move.coloane.projects.its.checks.CheckList;
import fr.lip6.move.coloane.projects.its.checks.ServiceResult;
import fr.lip6.move.coloane.projects.its.order.Ordering;
import fr.lip6.move.coloane.projects.its.order.Orders;
import fr.lip6.move.coloane.projects.its.ui.forms.TypeListTreeProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;

public class CheckListTreeProvider extends TypeListTreeProvider implements
		IContentProvider {

	
	/**
	 * Root elements are the type declarations.
	 * @param inputElement a type list
	 * @return the types in the list
	 * 
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		CheckList cl = (CheckList) inputElement;
		List<Object> al = new ArrayList<Object>();
		al.add(cl.getType());
		al.add(cl.getOrders());
		for (AbstractCheckService cs : cl) {
			al.add(cs);
		}		
		return al.toArray();
	}

	protected void addChildren(Object element, List<Object> children) {
		if (element instanceof AbstractCheckService) {
			AbstractCheckService cs = (AbstractCheckService) element;
			for (ServiceResult sr : cs) {
				children.add(sr);
			}
		} else if (element instanceof Orders) {
			Orders os = (Orders) element;
			for (Ordering o : os) {
				children.add(o);
			}
		} else if (element instanceof Ordering) {
			Ordering o = (Ordering) element;
			for (Ordering o2 : o) {
				children.add(o2);
			}
		}
		// TODO
		super.addChildren(element, children);
	}
	
	/**
	 * Implemented where possible per the contract of TreeProvider
	 * {@inheritDoc}
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof ServiceResult) {
			ServiceResult sr = (ServiceResult) element;
			return sr.getParent();
		}
		return super.getParent(element);
	}
	
	

}
