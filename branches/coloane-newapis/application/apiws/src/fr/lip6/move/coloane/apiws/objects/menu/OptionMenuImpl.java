package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;

/**
 * Cette classe définie une option.
 */
public class OptionMenuImpl extends ItemMenuImpl implements IOptionMenu {

	private boolean validated;

	/**
	 * Constructeur
	 * @param option La question correspondant à une option réçu de la part du wrapper
	 */
	public OptionMenuImpl(Question option) {
		super(option);
		this.validated = option.getValidation();
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

}
