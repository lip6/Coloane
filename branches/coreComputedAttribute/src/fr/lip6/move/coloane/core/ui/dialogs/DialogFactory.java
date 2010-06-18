package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

/**
 * Usines a boites de dialogue
 */
public class DialogFactory {

	/**
	 * Constructeur protege
	 */
	protected DialogFactory() { }

	/**
	 * Creation d'une boite de dialogue suivant le type de IDialogCom
	 * @param dialog La boite de dialogue
	 * @return Une boite de dialogue Eclipse
	 */
	public static IDialogUI create(IDialog dialog) {

		switch (dialog.getType()) {
			case IDialog.DLG_INTERACTIVE:
				throw new UnsupportedOperationException(Messages.DialogFactory_0);

			case IDialog.DLG_STANDARD:
			case IDialog.DLG_WARNING:
			case IDialog.DLG_ERROR:
				return new SimpleDialog(dialog);

			default:
				throw new UnsupportedOperationException(Messages.DialogFactory_1);
		}
	}
}
