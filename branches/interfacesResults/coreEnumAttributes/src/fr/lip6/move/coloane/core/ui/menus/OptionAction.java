package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import java.util.logging.Logger;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * Action permettant d'ajouter une option au menu.<br>
 * La liste et l'état des options est maintenu dans la session courrante.
 */
public class OptionAction extends Action {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private ISession session;

	/**
	 * @param option Objet décrivant l'option
	 * @param session session attachée à cette option
	 */
	public OptionAction(IOptionMenu option, ISession session) {
		super(option.getName(), convertStyle(option.getType()));
		setId(option.getName());
		setChecked(option.isValidated());
		session.setOption(option.getName(), option.isValidated());
		this.session = session;
	}

	/**
	 * Converti un type d'option en style compréhensible pour une IAction
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
	public final void run() {
		LOGGER.fine("Changement d'une option : " + getId() + " = " + isChecked()); //$NON-NLS-1$ //$NON-NLS-2$
		session.setOption(getId(), isChecked());
	}

}
