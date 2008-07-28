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
	 * @param root Le menu principal où est contenu le service.
	 */
	public ServiceMenuImpl(Question service, String root) {
		super(service);
		this.associatedService = root + "_" + service.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getAssociatedService() {
		return associatedService;
	}

}
