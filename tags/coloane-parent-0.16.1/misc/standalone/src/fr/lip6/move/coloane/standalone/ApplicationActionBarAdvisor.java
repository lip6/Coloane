package fr.lip6.move.coloane.standalone;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.jface.action.IContributionItem;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	// Declaration des actions du menu File
	private IWorkbenchAction newDerAction;
	private IContributionItem wizardList;
	private IWorkbenchAction saveAction;
	private IWorkbenchAction saveAsAction;
	private IWorkbenchAction saveAllAction;
	private IWorkbenchAction closeAction;
	private IWorkbenchAction closeAllAction;
	private IWorkbenchAction quitAction;
	
	// Declaration des actions du menu Edit
	private IWorkbenchAction undoAction;
	private IWorkbenchAction redoAction;
	private IWorkbenchAction copyAction;
	private IWorkbenchAction pasteAction;
	private IWorkbenchAction cutAction;
	private IWorkbenchAction deleteAction;
	private IWorkbenchAction selectAllAction;
	
	// Declaration des actions du menu Window
	private IContributionItem viewList;
	
	// Declaration des actions du menu Help
	private IWorkbenchAction aboutAction;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
	    super(configurer);
	}
	
	protected void makeActions(IWorkbenchWindow window) {
		
		// Creation des actions et enregistrements
		
		// Actions du menu File
		newDerAction = ActionFactory.NEW_WIZARD_DROP_DOWN.create(window);
		register(newDerAction);
		wizardList = ContributionItemFactory.NEW_WIZARD_SHORTLIST.create(window);
		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);
		saveAsAction = ActionFactory.SAVE_AS.create(window);
		register(saveAsAction);
		saveAllAction = ActionFactory.SAVE_ALL.create(window);
		register(saveAllAction);
		closeAction = ActionFactory.CLOSE.create(window);
		register(closeAction);
		closeAllAction = ActionFactory.CLOSE_ALL.create(window);
		register(closeAllAction);
		quitAction = ActionFactory.QUIT.create(window);
		register(quitAction);
		
		// Actions du menu Edit
		undoAction = ActionFactory.UNDO.create(window);
		register(undoAction);
		redoAction = ActionFactory.REDO.create(window);
		register(redoAction);
		copyAction = ActionFactory.COPY.create(window);
		register(copyAction);
		cutAction = ActionFactory.CUT.create(window);
		register(cutAction);
		deleteAction = ActionFactory.DELETE.create(window);
		register(deleteAction);
		pasteAction = ActionFactory.PASTE.create(window);
		register(pasteAction);
		selectAllAction = ActionFactory.SELECT_ALL.create(window);
		register(selectAllAction);
		
		// Action du menu Window
		viewList = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
	
		// Action du menu Help
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);		
		
	}
	
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
		MenuManager editMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
		MenuManager windowMenu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);
		MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(windowMenu);
		
		// Le marqueur MB_ADDITIONS indique l'emplacement reserve aux menus dynamiques( ajout d'extensions de actionSets
		menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
	    menuBar.add(helpMenu);
	  
		// Menu File
	    MenuManager newMenu = new MenuManager("&New");
	    fileMenu.add(newMenu);
	    newMenu.add(wizardList);
	    
	    // NewAction.setText("&New");
	    fileMenu.add(new Separator());
	    fileMenu.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
	    fileMenu.add(new Separator());
	    fileMenu.add(new GroupMarker(IWorkbenchActionConstants.IMPORT_EXT));
	    fileMenu.add(new Separator());
	    fileMenu.add(closeAction);
	    fileMenu.add(closeAllAction);
	    fileMenu.add(new Separator());
	    fileMenu.add(saveAction);
	    fileMenu.add(saveAsAction);
	    fileMenu.add(saveAllAction);
	  
	    fileMenu.add(new Separator());
	    fileMenu.add(quitAction);
	    
	    // Menu Edit
	    editMenu.add(undoAction);
	    editMenu.add(redoAction);
	    editMenu.add(new Separator());
	    editMenu.add(cutAction);
	    editMenu.add(copyAction);
	    editMenu.add(pasteAction);
	    editMenu.add(new Separator());
	    editMenu.add(deleteAction);
	    editMenu.add(selectAllAction);
	    
	    // Menu Window
	    MenuManager viewMenu = new MenuManager("Show &view"); 
		viewMenu.add(viewList);
		windowMenu.add(viewMenu);
	    
	    // Menu Help
	    helpMenu.add(aboutAction);
		
	}
	
}
