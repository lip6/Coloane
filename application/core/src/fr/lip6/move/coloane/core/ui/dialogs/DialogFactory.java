package fr.lip6.move.coloane.core.ui.dialogs;

import fr.lip6.move.coloane.core.exceptions.UIException;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;

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
	 * @throws UIException Lorsque la boite de dialogue n'est pas trouvee
	 */
	public static IDialog create(IDialogCom dialog) throws UIException {

		switch (dialog.getType()) {
			case IDialogCom.DLG_INTERACTIVE:
				throw new UIException(Messages.DialogFactory_0);

			case IDialogCom.DLG_STANDARD:
			case IDialogCom.DLG_WARNING:
			case IDialogCom.DLG_ERROR:
				return new SimpleDialog(dialog);

			default:
				throw new UIException(Messages.DialogFactory_1);
		}
	}
}
