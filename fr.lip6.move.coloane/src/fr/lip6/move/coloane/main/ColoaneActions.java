package fr.lip6.move.coloane.main;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import fr.lip6.move.coloane.ui.Editor;
import fr.lip6.move.coloane.ui.MainPerspectiveFactory;
import fr.lip6.move.coloane.ui.dialogs.AuthenticationDialog;
import fr.lip6.move.coloane.ui.panels.HistoryView;

public class ColoaneActions implements IWorkbenchWindowActionDelegate {

	/** Fenetre de travail */
	private IWorkbenchWindow window; 

	/** ID pour l'action "authentification" */
	private static final String ACTION_AUTH = "authentication";

	/** ID pour l'action "connexion" */
	private static final String ACTION_CONNECT_MODEL = "connect";

	/** ID pour l'action "deconnexion" */
	private static final String ACTION_DISCONNECT_MODEL = "disconnect";


	public void dispose() {
		// TODO Auto-generated method stub
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
		/*
		 * When the two following lines are executed, the menu
		 * doesn't exist and then these lines are useless
		 */
		// TODO find a point after the menu's creation to call these methods
		//MenuManipulation.setEnabled("Platform", "Connect model", false);
		//MenuManipulation.setEnabled("Platform", "Disconnect model", false);
	}

	public void run(IAction action) {
		/*
		 * If we don't call this method here, the view is not
		 * initialized and HistoryView.instance
		 * is null (and it is bad).
		 */
		try {
			window.getActivePage().
			showView(MainPerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(),
					"Error during view initialization",
					"The Historic view cannot be opened");
		}
		
		// Authentification
		if (ACTION_AUTH.equalsIgnoreCase(action.getId())) {
			
			System.out.println("Demande d'authentification");
			
			HistoryView.instance.addText("[?] Authentification -> ");

			// Affichage de la boite de dialogue d'authentification
			AuthenticationDialog authDialog = new AuthenticationDialog(window.getShell());

			if (authDialog.open() == Dialog.OK) {
				HistoryView.instance.addLine("OK");
			} else {
				HistoryView.instance.addLine("KO");
			}

			// Connexion d'un modele
		} else if (ACTION_CONNECT_MODEL.equalsIgnoreCase(action.getId())) {
			System.out.println("Connexion d'un mod�le");
			HistoryView.instance.addLine("[?] Connexion d'un mod�le");

			if(window.getActivePage().getActiveEditor() == null) {
				HistoryView.instance.addLine("[!] Echec: Aucun modele ouvert !");
			} else {
				Editor editor = (Editor) window.getActivePage().getActiveEditor();

				try {
					if (editor.getModel() != null) {
						// Le mod�le existe... On peut essayer de le connecter
						HistoryView.instance.addText("Connexion en cours... ");

						// Transformation du mod�les en mod�les impl�mentant l'interface de la Com
						String sessionName;

						if (editor instanceof Editor) {
							System.out.println("Session particuli�re");
							IFile file = ((IFileEditorInput)editor.getEditorInput()).getFile();
							sessionName=file.getProjectRelativePath().toString();
						} else {
							System.out.println("Session par d�faut");
							sessionName="SessionDefault";
						}

						System.out.println("Nom de session : "+sessionName);

						if (Coloane.getDefault().getMotor().openConnexion(editor.getModel(), sessionName)) {
							// TODO : Griser les menues adequats
							HistoryView.instance.addLine("SUCCESS");
							//MenuManipulation.setEnabled("Platform", "Connect model", false);
							//MenuManipulation.setEnabled("Platform", "Disconnect model", true);
						} else {
							// TODO : GRiser les menus adequats
							HistoryView.instance.addLine("FAILED");
						}

					} else {
						HistoryView.instance.addText("[!] Echec: Le mod�le n'est pas valide");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (ACTION_DISCONNECT_MODEL.equalsIgnoreCase(action.getId())) {
			System.out.println("Deconnexion");
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {}

}
