package menutest;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import Menu.GraphicalMenu;
import Menu.MenuNotFoundException;
import Menu.RootMenu;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class SampleAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	RootMenu rootMenu;
	GraphicalMenu cmb;
	int act = 1;
	
	/**
	 * The constructor.
	 */
	public SampleAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		switch (act) {
		case 1:
			act1();
			break;
		case 2:
			act2();
			break;
		case 3:
			act3();
			break;
		default:
			break;
		}
	}

	public void act1 (){
		rootMenu = new RootMenu("waza");
		act++;
	
		try {
			rootMenu.addMenu("Menu 1");
			rootMenu.addMenu("Feuille 1", "Menu 1");
			rootMenu.addMenu("Menu 4", "Menu 1");
			rootMenu.addMenu("Feuille 2", "Menu 4", false);
			rootMenu.addMenu("Feuille 3", "Menu 4");
			rootMenu.addMenu("Menu 2", false);
			rootMenu.addMenu("Feuille 4", "Menu 2", false);
			rootMenu.addMenu("Feuille 5");
			rootMenu.addMenu("Menu 3");
			rootMenu.addMenu("Feuille 6", "Menu 3");
			System.out.println("Construction graphique du menu");
			cmb = new GraphicalMenu(rootMenu, window);
			cmb.build();
		} catch (MenuNotFoundException me) {
			System.out.println("Problème !!!!");
		}
	}
	
	public void act2 (){
		act++;
		
		try {
			rootMenu.removeMenu("Feuille 6");
			cmb.update();
		} catch (MenuNotFoundException m) {
			System.out.println("OMG !!!");
		}
	}
	
	public void act3 (){
		act = 1;
		
		try {
			rootMenu.addMenu("Feuille 7", "Menu 3");
			cmb.update();
		} catch (MenuNotFoundException m) {
			System.out.println("OMG !!!");
		}
	}
	
	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}