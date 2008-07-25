package fr.lip6.move.coloane.core.ui.menus;

import org.eclipse.jface.action.Action;


public class ServiceAction extends Action {
	private String serviceName;

	public ServiceAction(String serviceName) {
		super(serviceName);
		this.serviceName = serviceName;
	}

	@Override
	public final void run() {
	}
}
