package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.MessageType;
import fr.lip6.move.coloane.core.session.Session;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.core.ui.dialogs.IDialogUI;
import fr.lip6.move.coloane.core.ui.menus.ColoaneRootMenu;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Manage user interface elements such as :
 * <ul>
 * 	<li>menu and submenus</li>
 * </ul>
 * 
 * @author Jean-Baptiste Voron
 */
public final class UserInterfaceManager {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/** 
	 * Coloane very-root menu cache instance.<br>
	 * A cache is needed because finding the Coloane menu is a bit long...
	 */
	private static Menu coloaneMenu = null;

	/** An UserInterfaceManager instance */
	private static UserInterfaceManager instance;

	/**
	 * @return a UserInterfaceManager instance
	 */
	public static synchronized UserInterfaceManager getInstance() {
		if (instance == null) {
			instance = new UserInterfaceManager();
		}
		return instance;
	}

	/**
	 * Draw session associated menus
	 * @param session The session for which menus have to be displayed
	 */
	public void drawMenus(final ISession session) {
		if (session == null) {
			return;
		}
		
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				
				// Otherwise, we are looking for it (among all displayed menus)
				Menu menu = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getMenuBar();
				for (MenuItem item : menu.getItems()) {
					if (item.getText().replace("&", "").equals(Coloane.getParam("MENUBAR_LABEL").replace("&", ""))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
						coloaneMenu = item.getMenu();
					}
				}
				
				// If no Coloane menu has been discovered... There will be a problem
				if (coloaneMenu == null)
					return;
				
				if (!coloaneMenu.isDisposed()) {
					LOGGER.finer("Cleaning up Coloane menus"); //$NON-NLS-1$
					for (MenuItem item : coloaneMenu.getItems()) {
						if (!item.getText().equals(Coloane.getParam("LOCAL_MENU"))) { //$NON-NLS-1$
							item.dispose();
						}
					}
				}
				
				// For each menu, attach the root menu to the very-root one, and update all children
				for (ColoaneRootMenu rootMenu : session.getRootServiceMenus()) {
					LOGGER.finer("Add Coloane menu [" + rootMenu.getMenuText() + "] to the end of menu list"); //$NON-NLS-1$ //$NON-NLS-2$
					rootMenu.fill(coloaneMenu, -1);
					rootMenu.updateAll(true);
				}
			}
		});
	}


	/**
	 * Affichage des resultats dans la vue resultats
	 */
	public void displayResults() {
		LOGGER.fine("Affichage des resultats du service"); //$NON-NLS-1$
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ColoanePerspectiveFactory.RESULTS_VIEW);
				} catch (PartInitException e) {
					LOGGER.warning("Erreur lors de l'affichage des resultats"); //$NON-NLS-1$
				}
			}
		});
	}

	/**
	 * Affiche sur la console de la session courante le message
	 * @param message message
	 * @param type type du message
	 */
	public void printConsoleMessage(final String message, final MessageType type) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				ISession session = SessionManager.getInstance().getCurrentSession();
				if (session != null) {
					((Session) session).printConsoleMessage(message, type);
				}
			}
		});
	}

	/**
	 * Affiche sur toutes les consoles existantes le message
	 * @param message message
	 * @param type type du message
	 */
	public void printAllConsoleMessage(final String message, final MessageType type) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				((SessionManager) SessionManager.getInstance()).printConsoleMessage(message, type);
			}
		});
	}

	/**
	 * Affichage d'une boite de dialogue
	 * @param dialog L'objet contenant toutes les informations sur la boite de dialogue a afficher
	 */
	public void drawDialog(final IDialog dialog) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// Factory de boite de dialogue
				IDialogUI dialogUI = DialogFactory.create(dialog);

				// Ouverture de la boite de dialogue
				dialogUI.open();

				// Capture des resultats
				//Motor.getInstance().sendDialogAnswer(dialogUI.getDialogResult());
			}
		});
	}
}
