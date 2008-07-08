package fr.lip6.move.coloane.testapiws.observers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenSession;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IOptionItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IRootMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.service.IServiceSimple;
import fr.lip6.move.coloane.apiws.interfaces.observers.IOpenSessionObserver;
import fr.lip6.move.coloane.apiws.interfaces.session.IApiSession;
import fr.lip6.move.coloane.testapiws.Activator;

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

		if (sm.getOptions()!=null){
			for (IOptionItem smenu : sm.getOptions()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"OP  ] "+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.CHECK);
				item.setText(smenu.getName());
				item.setEnabled(smenu.isVisibility());
			}
		}

		if (sm.getServiceSimpleItems()!=null){
			for (final IItemMenu smenu : sm.getServiceSimpleItems()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"ss   ]"+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.PUSH);
				item.setText(smenu.getName());
				item.setEnabled(smenu.isVisibility());
				item.addSelectionListener(
						new SelectionListener(){

							public void widgetDefaultSelected(SelectionEvent e) {
								// TODO Auto-generated method stub

							}

							public void widgetSelected(SelectionEvent e) {
								// TODO Auto-generated method stub
								IApiSession session = Activator.getDefault().getSession();
								IServiceSimple ss = (IServiceSimple) session.getServicesAvailable().getServices().get(smenu.getName());
								String msg = 
									"Name\t\t: "+ss.getName()+"\n"+
									"Domaine\t\t: "+ss.getDomain()+"\n"+
									"Cardinality\t: "+ss.getCardinality()+"\n"+
									"Interaction\t: "+ss.isInteraction()+"\n"+
									"Stop\t\t\t: "+ss.isStop()+"\n"+
									"Validation\t\t: "+ss.isValidation()+"\n"+
									"Visibility\t\t: "+ss.isVisibility()+"\n"+
									"Help\t\t\t: "+"\n";
								for (String help : ss.getHelps()){
									msg= msg+help+"\n";
								}
								MessageDialog.openInformation(shell, "APIWS", msg );
							}

						});
			}
		}

		if (sm.getServiceWithOneObjectItems()!=null){
			for (IItemMenu smenu : sm.getServiceWithOneObjectItems()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"soo  ]"+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.PUSH);
				item.setText(smenu.getName());
				item.setEnabled(smenu.isVisibility());
			}
		}
		
		if (sm.getServiceWithObjectsItems()!=null){
			for (IItemMenu smenu : sm.getServiceWithObjectsItems()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"sos  ]"+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.PUSH);
				item.setText(smenu.getName());
				item.setEnabled(smenu.isVisibility());
			}
		}
		
		if (sm.getServiceWithOneTextItems()!=null){
			for (IItemMenu smenu : sm.getServiceWithOneTextItems()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"sot  ]"+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.PUSH);
				item.setText(smenu.getName());
				item.setEnabled(smenu.isVisibility());
			}
		}
		
		if (sm.getServiceWithTextsItems()!=null){
			for (IItemMenu smenu : sm.getServiceWithTextsItems()){
				System.out.println(dec+"   ["+(sm.isVisibility()?"+":"-")+"sts  ]"+smenu.getName()+" : validation="+sm.isValidation());
				MenuItem item =  new MenuItem(m1,SWT.PUSH);
				item.setText(smenu.getName());
				item.setEnabled(smenu.isVisibility());
			}
		}
		
		
		if (sm.getSubMenus()!=null){
			for (ISubMenu smenu : sm.getSubMenus()){
				MenuItem subMenuHeader = new MenuItem(m1,SWT.CASCADE);
				subMenuHeader.setText(smenu.getName());
				
				Menu subMenu = new Menu(shell,SWT.DROP_DOWN);
				subMenuHeader.setMenu(subMenu);
				
				printMenu(smenu,"  "+dec,subMenu);
			}
		}
	}

}
