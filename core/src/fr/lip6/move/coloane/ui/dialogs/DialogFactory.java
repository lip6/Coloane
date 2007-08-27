package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.exceptions.UnknowDialogException;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;

/**
 * Usines a boite de dialogue
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
	 * @throws UnknowDialogException Lorsque la boite de dialogue n'est pas trouvee
	 */
	public static IDialog create(IDialogCom dialog) throws UnknowDialogException {

		switch (dialog.getType()) {
			case IDialogCom.DLG_INTERACTIVE:
				throw new UnknowDialogException();

			case IDialogCom.DLG_STANDARD:
			case IDialogCom.DLG_WARNING:
			case IDialogCom.DLG_ERROR:
				return new SimpleDialog(dialog);

			default:
				throw new UnknowDialogException();
		}
	}
}
