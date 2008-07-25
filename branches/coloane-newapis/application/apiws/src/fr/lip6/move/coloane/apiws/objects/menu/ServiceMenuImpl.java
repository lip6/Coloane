package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;

/**
 * Cette classe définie un service dans un menu.
 */
public class ServiceMenuImpl extends ItemMenuImpl implements IServiceMenu {

	private String associatedService;

	/**
	 * Constructeur
	 * @param service La question correspondant a un service réçu de la part du wrapper
	 */
	public ServiceMenuImpl(Question service) {
		super(service);
		// TODO Auto-generated constructor stub
		this.associatedService = null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getAssociatedService() {
		return associatedService;
	}

}
