package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette interfaces définie un items.
 */
public abstract class ItemMenuImpl implements IItemMenu {

	private String name;

	private boolean visible;

	private List<String> helps;

	/**
	 * Constructeur
	 * @param question La question réçu de la part du wrapper
	 */
	protected ItemMenuImpl(Question question) {
		this.name = question.getName();
		this.visible = question.getVisibility();

		this.helps = new  ArrayList<String>();
		if (question.getHelps() != null) {
			for (int i = 0; i < question.getHelps().length; i++) {
				this.helps.add(question.getHelps()[i]);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<String> getHelps() {
		return helps;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isVisible() {
		return visible;
	}


}
