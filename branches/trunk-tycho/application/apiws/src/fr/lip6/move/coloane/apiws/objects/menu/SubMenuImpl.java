package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Question;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.SubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe définie un sous-menu.
 *
 * @author Monir CHAOUKI
 */
public class SubMenuImpl extends ItemMenuImpl implements ISubMenu {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private List<IServiceMenu> services;

	private List<IOptionMenu> options;

	private List<ISubMenu> subMenus;

	/**
	 * Constructeur
	 * @param subMenu le sous-menu reçu de la part du wrapper.
	 * @param root le menu principal où est contenu le sous-menu.
	 */
	public SubMenuImpl(Question subMenu, String root) {
		super(subMenu);

		LOGGER.finest("Construction d'un sous-menu: " + subMenu.getName() + "(Visibility = " + subMenu.getVisibility() + ")");

		this.services = new ArrayList<IServiceMenu>();
		this.options = new ArrayList<IOptionMenu>();
		this.subMenus = new ArrayList<ISubMenu>();

		SubMenu sub = (SubMenu) subMenu;

		if (sub.getServices() != null) {
			for (int i = 0; i < sub.getServices().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServices()[i], root));
			}
		}

		if (sub.getServicesWithObjects() != null) {
			for (int i = 0; i < sub.getServicesWithObjects().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServicesWithObjects()[i], root));
			}
		}

		if (sub.getServicesWithOneObject() != null) {
			for (int i = 0; i < sub.getServicesWithOneObject().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServicesWithOneObject()[i], root));
			}
		}

		if (sub.getServiceWithOneText() != null) {
			for (int i = 0; i < sub.getServiceWithOneText().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServiceWithOneText()[i], root));
			}
		}

		if (sub.getServiceWithTexts() != null) {
			for (int i = 0; i < sub.getServiceWithTexts().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServiceWithTexts()[i], root));
			}
		}

		if (sub.getOption() != null) {
			for (int i = 0; i < sub.getOption().length; i++) {
				options.add(new OptionMenuImpl(sub.getOption()[i]));
			}
		}

		if (sub.getSubMenus() != null) {
			for (int i = 0; i < sub.getSubMenus().length; i++) {
				subMenus.add(new SubMenuImpl(sub.getSubMenus()[i], root));
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IServiceMenu> getServiceMenus() {
		return services;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IOptionMenu> getOptions() {
		return options;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubMenu> getSubMenus() {
		return subMenus;
	}

}
