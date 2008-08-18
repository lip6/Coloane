package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.core.ui.dialogs.IDialogUI;
import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.core.ui.panels.HistoryView;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Interface Utilisateur
 */
public final class UserInterface {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** La fenetre de travail */
	private static IWorkbenchWindow fenetreTravail;

	/** L'instance du singlaton : UserInterface */
	private static UserInterface instance;

	/**
	 * Constructeur de la classe
	 */
	private UserInterface() {
		fenetreTravail = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
//		service.activateHandler("", new AuthenticationHandler());
	}

	/**
	 * Retourne le module moteur
	 * @return Motor Le module moteur
	 */
	public static synchronized UserInterface getInstance() {
		if (instance == null) {
			instance = new UserInterface();
		}
		return instance;
	}

	/**
	 * Affichage d'un message sur la vue historique
	 * @param message a afficher
	 */
	public void printHistoryMessage(String message) {
		final String msg = message;

		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				if (HistoryView.getInstance() != null) {
					HistoryView.getInstance().addLine(msg);
				}
			}
		});
	}

	/**
	 * Afficher un menu
	 * @param menus La racine du menu a afficher
	 */
	public void drawMenus(final List<ISubMenu> menus) {
		final ISession session = SessionManager.getInstance().getCurrentSession();
		if (session == null) {
			return;
		}
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// Supprime tous les menus sauf PLATFORM
				MenuManipulation.clean();
				session.clearServicesMenu();

				for (ISubMenu menu : menus) {
					MenuManager menuManager = MenuManipulation.build(menu, session);
					MenuManipulation.add(menuManager);
					session.addServicesMenu(menuManager);
				}
			}
		});
	}

	/**
	 * Vide le menu
	 */
	public void cleanMenu() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MenuManipulation.clean();
			}
		});
	}

	/**
	 * Demande la mise a jour du menu
	 * @param mapUpdateMenu La liste des mises a jour a faire sur les menus
	 */
	public void updateMenu(final Map<String, IUpdateMenu> mapUpdateMenu) {
		// Recuperation du menu de service de la session
		final ISession session = SessionManager.getInstance().getCurrentSession();
		if (session == null) {
			return;
		}
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				for (MenuManager menuManager : session.getServicesMenu()) {
					MenuManipulation.update(menuManager, mapUpdateMenu);
				}
			}
		});
	}

	/**
	 * Reaffiche les menus (clean + reconstruction)
	 */
	public void redrawMenus() {
		// Supprime tous les menus sauf PLATFORM
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MenuManipulation.clean();

				ISession currentSession = SessionManager.getInstance().getCurrentSession();
				if (currentSession == null) {
					LOGGER.warning("Aucune session courante"); //$NON-NLS-1$
					return;
				}

				for (MenuManager menu : currentSession.getServicesMenu()) {
					MenuManipulation.add(menu);
				}
			}
		});
	}

	/**
	 * Affichage des resultats dans la vue resultats
	 */
	public void printResults() {
		LOGGER.fine("Affichage des resultats du service"); //$NON-NLS-1$
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					fenetreTravail.getActivePage().showView(ColoanePerspectiveFactory.RESULTS_VIEW);
				} catch (PartInitException e) {
					LOGGER.warning("Erreur lors de l'affichage des resultats"); //$NON-NLS-1$
				}
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
				Motor.getInstance().sendDialogAnswer(dialogUI.getDialogResult());
			}
		});
	}
}
