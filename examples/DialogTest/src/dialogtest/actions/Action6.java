package dialogtest.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import fr.lip6.move.coloane.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.ui.dialogs.IDialog;
import fr.lip6.move.coloane.ui.dialogs.SimpleDialog;
import fr.lip6.move.coloane.ui.dialogs.TextArea;
import fr.lip6.move.coloane.ui.dialogs.UnknowDialogException;

public class Action6 implements IWorkbenchWindowActionDelegate {

	public void dispose() {}

	public void init(IWorkbenchWindow window) {}

	public void run(IAction action) {
		// Liste avec une seule saisie possible
		try {
			IDialog d = DialogFactory.create(1, SimpleDialog.DLG_WARNING,
					SimpleDialog.DLG_OK_CANCEL,
					"Une seule sélection",
					"Veuillez sélectionner un élément",
					"Sélection",
					TextArea.INPUT_FORBIDDEN, TextArea.MULTI_LINE_WITH_SINGLE_SELECTION, "");
			d.addChoice("a");
			d.addChoice("b");
			d.addChoice("c");
			d.open();
			
			AffichageResultat.affiche(d.getDialogResult());
			
		} catch (UnknowDialogException e) {}
		catch (Exception e) {
			System.err.println("Oooosp");
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {}
}
