package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.communications.Com;
import fr.lip6.move.coloane.core.exceptions.UIException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.menus.RootMenu;
import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.core.ui.dialogs.IDialog;
import fr.lip6.move.coloane.core.ui.menus.GraphicalMenu;
import fr.lip6.move.coloane.core.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.core.ui.menus.UpdatePlatformMenu;
import fr.lip6.move.coloane.core.ui.panels.HistoryView;

import java.util.Vector;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Composite;
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

	/** Le module de communication */
	private static Com com = null;

	/** Le module de moteur */
	private Motor motor = null;

	/** L'instance du singlaton : UserInterface */
	private static UserInterface instance;

	/**
	 * Constructeur de la classe
	 */
	private UserInterface() {
		fenetreTravail = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * Retourne le module moteur
	 * @return Motor Le module moteur
	 */
	public static synchronized UserInterface getInstance() {
		if (instance == null) {
			instance = new UserInterface();
			Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), false)); //$NON-NLS-1$
			Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), false)); //$NON-NLS-1$
			Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("BREAK_ITEM"), false)); //$NON-NLS-1$
		}
		return instance;
	}

	/**
	 * Demande d'un service
	 * @param rootMenuName Le nom du menu racine
	 * @param parentName Le nom du pere de la feuille cliquee
	 * @param serviceName Le nom du service demande
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		motor.askForService(rootMenuName, parentName, serviceName);
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
	 * @param menu La racine du menu a afficher
	 */
	public void drawMenu(RootMenu menu) {
		// Supprime tous les menus sauf PLATFORM
		MenuManipulation.clean();

		ISession currentSession = motor.getSessionManager().getCurrentSession();
		if (currentSession == null) {
			LOGGER.warning("Aucune session courante"); //$NON-NLS-1$
			Coloane.showWarningMsg("Impossible d'afficher le menu"); //$NON-NLS-1$
			return;
		}
		currentSession.setServicesMenu(menu);
		GraphicalMenu gmenu = new GraphicalMenu(menu, fenetreTravail, this);
		gmenu.build();
	}

	/**
	 * Demande la mise a jour du menu
	 * @param updates La liste des mises a jour a faire sur les menus
	 */
	public void updateMenu(ISession concernedSession, Vector<IUpdateMenuCom> updates) {
		if (concernedSession == null) {
			LOGGER.warning("Aucune session concernee par ces resultats"); //$NON-NLS-1$
			Coloane.showWarningMsg("Impossible de mettre a jour le menu"); //$NON-NLS-1$
			return;
		}
		// Recuperation du menu de service de la session
		RootMenu service = concernedSession.getServicesMenu();
		if (service == null) {
			return;
		}
		for (IUpdateMenuCom up : updates) {
			if (up.getRoot().equals(service.getName())) {
				service.setEnabled(up.getService(), up.getState());
			}
		}
		GraphicalMenu gmenu = new GraphicalMenu(concernedSession.getServicesMenu(), fenetreTravail, this);
		gmenu.update();
	}

	/**
	 * Reaffiche les menus (clean + reconstruction)
	 */
	public void redrawMenus() {
		// Supprime tous les menus sauf PLATFORM
		MenuManipulation.clean();

		ISession currentSession = motor.getSessionManager().getCurrentSession();
		if (currentSession == null) {
			LOGGER.warning("Aucune session courante"); //$NON-NLS-1$
			return;
		}
		GraphicalMenu gmenu = new GraphicalMenu(currentSession.getServicesMenu(), fenetreTravail, this);
		gmenu.build();
	}

	/**
	 * Demande la suppression d'un menu designee par son nom
	 * @param menuName Le nom du menu a supprimer
	 */
	public void removeMenu(String menuName) {
		MenuManipulation.remove(menuName);
	}

	/**
	 * Desactivation du rootMenu
	 * @param rootMenu menu Root a griser (ainsi que tous ses fils)
	 */
	public void changeRootMenuStatus(String rootName, boolean status) {
		MenuManipulation.setEnabled(rootName, rootName, status);
	}

	/**
	 * Affichage des resultats d'un appel de service
	 * @param serviceName Le nom du service qui produit ses resultats
	 * @param result L'objet contenant les resultats pour ce service
	 */
	public void setResults(String serviceName, IResultsCom result) {
		motor.getSessionManager().getCurrentSession().getServiceResults().add(serviceName, result);
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
	 * @param Dialog L'objet contenant toutes les informations sur la boite de dialogue a afficher
	 * @throws UIException
	 */
	public static void drawDialog(IDialogCom dialogCom) throws UIException {
		// Factory de boite de dialogue
		IDialog dialog = DialogFactory.create(dialogCom);

		// Ouverture de la boite de dialogue
		dialog.open();

		// Capture des resultats
		com.sendDialogAnswers(dialog.getDialogResult());
	}

	/**
	 * Modifie l'etat d'un item du menu Platform
	 * @param menuItem Le nom de l'item a modifier
	 * @param newState Le nouvel etat pour l'item
	 */
	public void platformState(boolean authentication, int session) {
		LOGGER.fine("Mise a jour de l'etat de la session (AUTH,SESSION) : (" + authentication + "," + session + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Composite parent = (Composite) Coloane.getParent();

		// Prise en compte de l'authentification
		if (!authentication) {
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("AUTHENTICATION_ITEM"), true)); //$NON-NLS-1$
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), false)); //$NON-NLS-1$
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), false)); //$NON-NLS-1$
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("BREAK_ITEM"), false)); //$NON-NLS-1$
			return;
		} else {
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("AUTHENTICATION_ITEM"), false)); //$NON-NLS-1$
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("BREAK_ITEM"), true)); //$NON-NLS-1$
		}

		// Prise en compte de l'etat de la session
		switch (session) {
		case ISession.CLOSED:
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), true)); //$NON-NLS-1$
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), false)); //$NON-NLS-1$
			break;

		case ISession.ERROR:
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), false)); //$NON-NLS-1$
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), false)); //$NON-NLS-1$
			break;

		default:
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), false)); //$NON-NLS-1$
			parent.getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), true)); //$NON-NLS-1$
			break;
		}
	}

	/**
	 * On attache le module de communication a l' l'interface utilisateur
	 * @param Le module {@link Com}
	 */
	public void setCom(Com c) {
		com = c;
	}

	/**
	 * On attache le module du moteur a l' l'interface utilisateur
	 * @param Motor Le module {@link Motor}
	 */
	public void setMotor(Motor mot) {
		this.motor = mot;
	}
}
