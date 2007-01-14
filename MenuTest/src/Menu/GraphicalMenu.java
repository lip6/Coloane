package Menu;


import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * This class takes a RootMenu and builds a graphic
 * menu for the Eclipse menubar, inserting it
 * just after the "Platform" menu.
 * 
 * @author Alexandre ORTIZ
 *
 */
public class GraphicalMenu {
	private RootMenu root;
	private Shell shell;
	
	/**
	 * @param root
	 * @param window The window repersenting the workbench.<br/>
	 *  Can be obtained with
	 *  	PlatformUI.getWorkbench().getActiveWorkbenchWindow() 
	 */
	public GraphicalMenu(RootMenu root, 
			IWorkbenchWindow window) {
		shell  = window.getShell();
		this.root = root;
	}
	
	/**
	 * Builds recursively a MenuManager from, using a RootMenu
	 * given in the constructor.
	 */
	public void build() {
		MenuManager rootMenuManager = new MenuManager(root.getName());
		
		/*
		 * We recursively build the mnu.
		 */
		for(ChildMenu aChild : root.getChildren()) {
			if (aChild.getChildrenNumber() == 0)
				rootMenuManager.add(new CAction(aChild.getName()));
			else
				buildChildMenu(aChild, rootMenuManager);
		}
		
		Menu menuBar = shell.getMenuBar();
		MenuItem[] mi = menuBar.getItems();
		
		int place = 0;
		
		/*
		 * We search the Platform menu's position to add this
		 * menu just after it.
		 */
		for (int i = 0; i < mi.length; i++)
			if (mi[i].getText().equals("&Platform")) {
				place = i;
				break;
			}
		
		rootMenuManager.fill(menuBar, place + 1);
		
		rootMenuManager.getParent().remove(rootMenuManager);
	}
	
	/**
	 * Updates a menu (i.e; modifies it in the menubar).
	 *
	 */
	public void update() {
		remove();
		build();
	}
	
	/**
	 * Removes a menu from the menubar.
	 *
	 */
	public void remove() {
		for (MenuItem mi : shell.getMenuBar().getItems())
			if (mi.getText().equals(root.getName())) {
				mi.dispose();
				return;
			}
	}
	
	/**
	 * Builds recursively a MenuManager from a Childmenu.
	 * @param child
	 * @param parentMenuManager
	 */
	public void buildChildMenu(ChildMenu child,
			MenuManager parentMenuManager) {
		MenuManager childMenuManager = new MenuManager(child.getName());
		parentMenuManager.add(childMenuManager);
		
		for(ChildMenu littleChild : child.getChildren())
			/*
			 * If we are on a leaf, we don't add a MenuManager
			 * but a Action.
			 */
			if (littleChild.isLeaf()) {
				CAction exitAction = new CAction(littleChild.getName());
				exitAction.setEnabled(littleChild.getEnabled());
				childMenuManager.add(exitAction);
			} else
				buildChildMenu(littleChild, childMenuManager);
	}
}