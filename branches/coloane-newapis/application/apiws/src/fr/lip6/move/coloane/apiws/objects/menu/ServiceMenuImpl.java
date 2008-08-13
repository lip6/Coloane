package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Question;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;

import java.util.logging.Logger;

/**
 * Cette classe définie un service dans un menu.
 *
 * @author Monir CHAOUKI
 */
public class ServiceMenuImpl extends ItemMenuImpl implements IServiceMenu {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private String associatedService;

	/**
	 * Constructeur
	 * @param service La question correspondant a un service réçu de la part du wrapper
	 * @param root Le menu principal où est contenu le service.
	 */
	public ServiceMenuImpl(Question service, String root) {
		super(service);

		this.associatedService = root + "_" + service.getName();
		LOGGER.finest("Construction d'un ServiceMenu: " + service.getName() + "(Visibility = " + this.isVisible() + "; associatedService = " + this.getAssociatedService() + ")");
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getAssociatedService() {
		return associatedService;
	}

}
