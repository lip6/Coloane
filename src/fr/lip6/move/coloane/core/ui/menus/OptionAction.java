package fr.lip6.move.coloane.core.ui.menus;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import java.util.logging.Logger;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * Define an action that will manage the state of an option.<br>
 */
public class OptionAction extends Action implements IStatedElement {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Visible state */
	private boolean visible = false;

	/**
	 * Constructor
	 * @param optionDescription The description of the option
	 */
	public OptionAction(IOptionMenu optionDescription) {
		super(optionDescription.getName(), convertStyle(optionDescription.getType()));
		setId(optionDescription.getName());
		setChecked(optionDescription.isVisible());
		this.visible = optionDescription.isVisible();
	}

	/**
	 * Convert a IOptionType to a type understandable by an IAction
	 * @param style option style
	 * @return a IAction style 
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
		LOGGER.fine("Option switch : " + getId() + " = " + isChecked()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/** {@inheritDoc} */
	public boolean getState() {
		return visible;
	}

	/** {@inheritDoc} */
	public void setState(boolean state) {
		this.visible = state;
	}
}
