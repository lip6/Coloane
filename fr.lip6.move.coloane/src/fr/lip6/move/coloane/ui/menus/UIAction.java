package fr.lip6.move.coloane.ui.menus;

import org.eclipse.jface.action.Action;

import fr.lip6.move.coloane.ui.UserInterface;

public class UIAction extends Action {
	private UserInterface ui;
	private String rootMenuName;
	private String parentName;
	private String serviceName;
	
	public UIAction(UserInterface ui, String rootMenuName,
			String parentName, String serviceName) {
		super(serviceName);
		this.ui = ui;
		this.rootMenuName = rootMenuName;
		this.parentName = parentName;
		this.serviceName = serviceName;
	}
	
	public void run() {
		ui.askForService(rootMenuName, parentName, serviceName);
		System.err.println("Service : " + serviceName + "called");
	}
}
