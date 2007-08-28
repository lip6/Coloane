package fr.lip6.move.coloane.ui.actions;

import fr.lip6.move.coloane.main.Coloane;
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public final void init(IWorkbenchWindow w) {
		this.window = w;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public final void run(IAction action) {

		try {
			// If we don't call this method here, the view is not initialized and HistoryView.instance is null (and it is bad).
			window.getActivePage().showView(MainPerspectiveFactory.HISTORY_VIEW);
		} catch (PartInitException e) {
			MessageDialog.openError(window.getShell(), Messages.ConnectModelAction_0, Messages.ConnectModelAction_1);
		}

		HistoryView.getInstance().addLine(Messages.ConnectModelAction_2);

		if (window.getActivePage().getActiveEditor() == null) {
			HistoryView.getInstance().addLine(Messages.ConnectModelAction_3);
		} else {
			Editor editor = (Editor) window.getActivePage().getActiveEditor();

			try {
				if (editor.getModel() != null) {
					// Le modele existe... On peut essayer de le connecter
					HistoryView.getInstance().addText(Messages.ConnectModelAction_4);

					// Transformation du modeles en modeles implementant l'interface de la Com
					String eclipseSessionName;

					if (editor instanceof Editor) {
						IFile file = ((IFileEditorInput) editor.getEditorInput()).getFile();
						eclipseSessionName = file.getProjectRelativePath().toString();
					} else {
						eclipseSessionName = "SessionDefault"; //$NON-NLS-1$
					}

					Coloane.getLogger().fine("Session courante : " + eclipseSessionName); //$NON-NLS-1$

					if (Coloane.getDefault().getMotor().openSession(editor.getModel(), eclipseSessionName)) {
						HistoryView.getInstance().addLine(Messages.ConnectModelAction_6);
						Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), false)); //$NON-NLS-1$
						Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), true)); //$NON-NLS-1$
					} else {
						HistoryView.getInstance().addLine(Messages.ConnectModelAction_9);
					}

				} else {
					HistoryView.getInstance().addText(Messages.ConnectModelAction_10);
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
