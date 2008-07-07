package fr.lip6.move.coloane.testapiws.observers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;

public class OpenSessionObserver implements IOpenSessionObserver {

	private Shell shell;
	private Menu menu;
	
	public OpenSessionObserver(Shell s){
		this.shell = s;
		this.menu = shell.getMenuBar();
	}
	
	public void update(IAnswerOpenSession s) {
		System.out.println("OPEN SESSION : idSession -> "+s.getIdSession()+" formalism -> "+s.getFormalism());
		System.out.println("menus null:"+(s.getMenus()==null));
		for (IRootMenu m : s.getMenus().getRootsMenus()){
			
			MenuItem rootMenuHeader = new MenuItem(menu,SWT.CASCADE);
			rootMenuHeader.setText(m.getName());
			Menu rootMenu = new Menu(shell,SWT.DROP_DOWN);
			rootMenuHeader.setMenu(rootMenu);
			
			printMenu(m.getRoot(),"",rootMenu);
		}
		MessageDialog.openInformation(shell, "Apiws", "Session ouvert");
		
	}
	
	public void printMenu(ISubMenu sm,String dec, Menu m1){
		System.out.println(dec+"["+(sm.isVisibility()?"+":"-")+"SUB  ] "+sm.getName()+" : validation="+sm.isValidation());
		if (sm.getItems()!=null){
			for (IItemMenu smenu : sm.getItems()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"Items]"+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.PUSH);
				item.setText(smenu.getName());
			}
		}
		if (sm.getOption()!=null){
			for (IOptionMenu smenu : sm.getOption()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"OP   ] "+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.CHECK);
				item.setText(smenu.getName());
			}
		}
		if (sm.getSubMenus()!=null){
			for (ISubMenu smenu : sm.getSubMenus()){
				MenuItem subMenuHeader = new MenuItem(m1,SWT.CASCADE);
				subMenuHeader.setText(smenu.getName());
				
				Menu subMenu = new Menu(shell,SWT.DROP_DOWN);
				subMenuHeader.setMenu(subMenu);
				
				printMenu(smenu,"",subMenu);
			}
		}
	}

}
