package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Question;
import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

/**
 * Cette interfaces définie un items.
 *
 * @author Monir CHAOUKI
 */
public abstract class ItemMenuImpl implements IItemMenu {

	private String name;

	private boolean visible;

	private String helps;

	// FIXME : gestion du path pour pouvoir gérer les options
	private String path = "";

	/**
	 * Constructeur
	 * @param question La question réçu de la part du wrapper
	 */
	protected ItemMenuImpl(Question question) {
		this.name = question.getName();
		this.visible = question.getVisibility();

		this.helps = "";
		if (question.getHelps() != null) {
			for (int i = 0; i < question.getHelps().length; i++) {
				this.helps += (question.getHelps()[i] + "\n");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getHelps() {
		return helps;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	public final String getPath() {
		return path;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isVisible() {
		return visible;
	}

	/** {@inheritDoc} */
	@Override
	public final String toString() {
		return name;
	}


}
