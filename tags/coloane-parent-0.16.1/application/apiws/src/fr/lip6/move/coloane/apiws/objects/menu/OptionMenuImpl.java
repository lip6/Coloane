package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Question;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;

import java.util.logging.Logger;

/**
 * Cette classe définie une option.
 *
 * @author Monir CHAOUKI
 */
public class OptionMenuImpl extends ItemMenuImpl implements IOptionMenu {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private boolean validated;

	private int type;

	/**
	 * Constructeur
	 * @param option La question correspondant à une option réçu de la part du wrapper
	 */
	public OptionMenuImpl(Question option) {
		super(option);

		this.validated = option.getValidation();
		this.type = IOptionMenu.TYPE_CHECKBOX;
		LOGGER.finest("Construction d'un option: " + option.getName() + "(Visibility = " + this.isVisible() + "Checked = " +  this.isValidated() + ")");
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isValidated() {
		return validated;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setValidated(boolean validated) {
		this.validated = validated;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return type;
	}

}
