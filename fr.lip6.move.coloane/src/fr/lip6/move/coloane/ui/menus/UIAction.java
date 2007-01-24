package fr.lip6.move.coloane.ui.menus;

import org.eclipse.jface.action.Action;

import fr.lip6.move.coloane.ui.UserInterface;

public class UIAction extends Action {
	private UserInterface ui;
	private String rootMenuName;
	private String referenceName;
	private String serviceName;
	
	public UIAction(UserInterface ui, String rootMenuName,
			String referenceName, String serviceName) {
		super(serviceName);
		this.ui = ui;
		this.rootMenuName = rootMenuName;
		this.referenceName = referenceName;
		this.serviceName = serviceName;
	}
	
	public void run() {
		ui.askForService(rootMenuName, referenceName, serviceName);
	}
}
