package fr.lip6.move.coloane.ui.actions;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.main.Translate;
import fr.lip6.move.coloane.ui.Editor;
import fr.lip6.move.coloane.ui.MainPerspectiveFactory;
import fr.lip6.move.coloane.ui.menus.UpdatePlatformMenu;
import fr.lip6.move.coloane.ui.panels.HistoryView;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

public class ConnectModelAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	public final void init(IWorkbenchWindow w) {
		this.window = w;
	}

	public final void run(IAction action) {

		try {
			// If we don't call this method here, the view is not initialized and HistoryView.instance is null (and it is bad).
			window.getActivePage().showView(MainPerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(),
					Translate.getString("ui.actions.ConnectModelAction.0"), //$NON-NLS-1$
					Translate.getString("ui.actions.ConnectModelAction.1")); //$NON-NLS-1$
		}

		HistoryView.getInstance().addLine(Translate.getString("ui.actions.ConnectModelAction.3")); //$NON-NLS-1$

		if (window.getActivePage().getActiveEditor() == null) {
			HistoryView.getInstance().addLine(Translate.getString("ui.actions.ConnectModelAction.4")); //$NON-NLS-1$
		} else {
			Editor editor = (Editor) window.getActivePage().getActiveEditor();

			try {
				if (editor.getModel() != null) {
					// Le modele existe... On peut essayer de le connecter
					HistoryView.getInstance().addText(Translate.getString("ui.actions.ConnectModelAction.5")); //$NON-NLS-1$

					// Transformation du modeles en modeles implementant l'interface de la Com
					String eclipseSessionName;

					if (editor instanceof Editor) {
						System.out.println("Session particuliere"); //$NON-NLS-1$
						IFile file = ((IFileEditorInput) editor.getEditorInput()).getFile();
						eclipseSessionName = file.getProjectRelativePath().toString();
					} else {
						System.out.println("Session par defaut"); //$NON-NLS-1$
						eclipseSessionName = "SessionDefault"; //$NON-NLS-1$
					}

					System.out.println("Nom de session : " + eclipseSessionName); //$NON-NLS-1$

					if (Coloane.getDefault().getMotor().openSession(editor.getModel(), eclipseSessionName)) {
						HistoryView.getInstance().addLine(Translate.getString("ui.actions.ConnectModelAction.14")); //$NON-NLS-1$
						Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), false));
						Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), true));
					} else {
						HistoryView.getInstance().addLine(Translate.getString("ui.actions.ConnectModelAction.15")); //$NON-NLS-1$
					}

				} else {
					HistoryView.getInstance().addText(Translate.getString("ui.actions.ConnectModelAction.16")); //$NON-NLS-1$
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public final void dispose() {
		return;
	}

}
