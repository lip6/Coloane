package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.List;

/**
 * Cette classe représent une options.
 */
public class OptionMenu extends ItemMenu implements IOptionMenu {

	private boolean validated;

	/**
	 * Constructeur d'un item
	 * @param type le type de l'item :
	 * 				- item simple (i.e. un service)
	 * 				- option
	 * 				- sous-menu
	 * @param name le nom de l'item
	 * @param visible la visibilité de l'item
	 * @param associatedService l'identifiant du service auquel l'item est associé
	 * @param helps l'aide sur l'item
	 * @param validated definie si l'option est coché ou non.
	 */
	protected OptionMenu(int type, String name, boolean visible, String associatedService, List<String> helps, boolean validated) {
		super(type, name, visible, associatedService, helps);
		this.validated = validated;
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
