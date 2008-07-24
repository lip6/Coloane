package fr.lip6.move.coloane.test.observers;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.test.apiws.Activator;

public class ReceptDialogObserver implements IReceptDialogObserver {
	
	public void update(IDialog dialog) {
		
		ShowMyDialog myDialog = new ShowMyDialog();
		myDialog.start();

		try {
			Activator.getSessionController().getActiveSession().sendDialogAnswer(
					new DialogAnswer(
							dialog.getId(),
							IDialog.DLG_NO_BUTTON,
							false,
							"",
							null));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}