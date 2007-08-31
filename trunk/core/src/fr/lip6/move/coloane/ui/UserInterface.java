package fr.lip6.move.coloane.ui;

import fr.lip6.move.coloane.exceptions.UIException;
import fr.lip6.move.coloane.interfaces.IComUi;
import fr.lip6.move.coloane.interfaces.IMotorUi;
import fr.lip6.move.coloane.interfaces.IUiCom;
import fr.lip6.move.coloane.interfaces.IUiMotor;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.motor.session.Session;
import fr.lip6.move.coloane.results.ActionsList;
import fr.lip6.move.coloane.results.Result;
import fr.lip6.move.coloane.results.ResultsList;
import fr.lip6.move.coloane.results.reports.FactoryReport;
import fr.lip6.move.coloane.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.ui.dialogs.IDialog;
import fr.lip6.move.coloane.ui.menus.GraphicalMenu;
import fr.lip6.move.coloane.ui.menus.MenuManipulation;
import fr.lip6.move.coloane.ui.panels.HistoryView;

import java.util.Vector;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Interface Utilisateur
 */

public final class UserInterface implements IUiCom, IUiMotor {

	/** La fenetre de travail */
	private static IWorkbenchWindow fenetreTravail;

	/** Le module de communication */
	private static IComUi com = null;

	/** Le module de moteur */
	private IMotorUi motor = null;

	/** La gestion des resultats */
	private ActionsList serviceResultList = null;

	/** L'instance du singlaton : UserInterface */
	private static UserInterface instance;


	/**
	 * Constructeur de la classe
	 */
	private UserInterface() {
		fenetreTravail = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		// Le gestionnaire de resultats de services
		serviceResultList = new ActionsList();
	}

	/**
	 * Retourne le module moteur
	 * @return Motor Le module moteur
	 */
	public static synchronized UserInterface getInstance() {
		if (instance == null) { instance = new UserInterface(); }
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
	 * @param menu La racine du menu a afficher
	 */
	public void drawMenu(RootMenu menu) {
		Session currentSession = motor.getSessionManager().getCurrentSession();
		currentSession.setServicesMenu(menu);
		GraphicalMenu gmenu = new GraphicalMenu(menu, fenetreTravail, this);
		gmenu.build();
	}


	/**
	 * Demande la mise a jour du menu
	 * @param updates La liste des mises a jour a faire sur les menus
	 */
	public void updateMenu(Vector<IUpdateMenuCom> updates) {
		Session currentSession = motor.getSessionManager().getCurrentSession();
		if (currentSession != null) {

			// Recuperation du menu de service de la session
			RootMenu service = currentSession.getServicesMenu();

			for (IUpdateMenuCom up : updates) {
				if (up.getRoot().equals(service.getName())) {
					service.setEnabled(up.getService(), up.getState());
				}
			}

			GraphicalMenu gmenu = new GraphicalMenu(currentSession.getServicesMenu(), fenetreTravail, this);
			gmenu.update();
		}
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
	public void changeMenuStatus(String rootName, boolean status) {
		MenuManipulation.setEnabled(rootName, rootName, status);
	}

	/**
	 * Affichage des resultats d'un appel de service
	 * @param serviceName Le nom du service qui produit ses resultats
	 * @param result L'objet contenant les resultats pour ce service
	 */
	public void setResults(String serviceName, IResultsCom result) {
		if (serviceResultList != null) {
			String labelService;
			ResultsList r = null;


			if ((serviceName == "") || (result == null)) { //$NON-NLS-1$
				labelService = Messages.UserInterface_1;
				r = new ResultsList(labelService);
				String  description = ""; //$NON-NLS-1$
				String object = ""; //$NON-NLS-1$
				r.add(new Result(object, description));
			}

			FactoryReport factory = new FactoryReport(result);
			r = (factory.createReport()).getResultList();
			serviceResultList.setResultsList(r);
		}
	}


	/**
	 * Affichage des resultats dans la vue resultats
	 */
	public void printResults() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {
					serviceResultList.addResultsList();
					fenetreTravail.getActivePage().showView(MainPerspectiveFactory.RESULTS_VIEW);
					serviceResultList.display(MainPerspectiveFactory.RESULTS_VIEW, fenetreTravail);
				} catch (PartInitException e) {
					Coloane.getLogger().warning("Erreur lors de l'affichage des resultats"); //$NON-NLS-1$
				}
			}
		});
	}


	/**
	 * Demande d'un service
	 * @param rootMenuName Le nom du menu racine
	 * @param parentName Le nom du pere de la feuille cliquee
	 * @param serviceName LE nom du service demande
	 */
	public void askForService(String rootMenuName, String parentName, String serviceName) {
		serviceResultList.removeAll();
		com.askForService(rootMenuName, parentName, serviceName);
	}


	/** Affichage d'une boite de dialogue
	 * @param Dialog L'objet contenant toutes les informations sur la boite de dialogue a afficher
	 */
	public static void drawDialog(IDialogCom dialogCom) {
		// Factory de boite de dialogue
		IDialog dialog = null;
		try {
			dialog = DialogFactory.create(dialogCom);
		} catch (UIException e) {
			Coloane.getLogger().warning("Erreur lors de la creation de la boite de dialogue : " + e.getMessage()); //$NON-NLS-1$
			Coloane.showErrorMsg(e.getMessage());
		}

		// Ouverture de la boite de dialogue
		dialog.open();

		// Capture des resultats
		com.getDialogAnswers(dialog.getDialogResult());
	}

	/**
	 * On attache le module de communication a l' l'interface utilisateur
	 * @param IComUi L'interface
	 */
	public void setCom(IComUi c) {
		com = c;
	}


	/**
	 * On attache le module du moteur a l' l'interface utilisateur
	 * @param IMotorUi L'interface
	 */
	public void setMotor(IMotorUi mot) {
		this.motor = mot;
	}
}
