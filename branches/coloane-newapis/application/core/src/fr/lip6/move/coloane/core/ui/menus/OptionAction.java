package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * Action permettant d'ajouter une option au menu.<br>
 * La liste et l'état des options est maintenu dans la session courrante.
 */
public class OptionAction extends Action {

	/**
	 * @param option Objet décrivant l'option
	 */
	public OptionAction(IOptionMenu option) {
		super(option.getName(), convertStyle(option.getType()));
		setId(option.getName());
		setChecked(option.isValidated());
	}

	/**
	 * @param style style donnée par l'api
	 * @return style compréhensible par une Action
	 */
	private static int convertStyle(int style) {
		if (style == IOptionMenu.TYPE_CHECKBOX) {
			return IAction.AS_CHECK_BOX;
		} else if (style == IOptionMenu.TYPE_RADIO) {
			return IAction.AS_RADIO_BUTTON;
		} else {
			return IAction.AS_UNSPECIFIED;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void run() {
	}

}
