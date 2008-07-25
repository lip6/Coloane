package fr.lip6.move.coloane.apiws.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe définie un sous-menu.
 */
public class SubMenuImpl extends ItemMenuImpl implements ISubMenu {

	private List<IServiceMenu> services;

	private List<IOptionMenu> options;

	private List<ISubMenu> subMenus;

	/**
	 * Constructeur
	 * @param subMenu le sous-menu reçu de la part du wrapper.
	 */
	public SubMenuImpl(Question subMenu) {
		super(subMenu);

		this.services = new ArrayList<IServiceMenu>();
		this.options = new ArrayList<IOptionMenu>();
		this.subMenus = new ArrayList<ISubMenu>();

		SubMenu sub = (SubMenu) subMenu;

		if (sub.getServices() != null) {
			for (int i = 0; i < sub.getServices().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServices()[i]));
			}
		}

		if (sub.getServicesWithObjects() != null) {
			for (int i = 0; i < sub.getServicesWithObjects().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServicesWithObjects()[i]));
			}
		}

		if (sub.getServicesWithOneObject() != null) {
			for (int i = 0; i < sub.getServicesWithOneObject().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServicesWithOneObject()[i]));
			}
		}

		if (sub.getServiceWithOneText() != null) {
			for (int i = 0; i < sub.getServiceWithOneText().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServiceWithOneText()[i]));
			}
		}

		if (sub.getServiceWithTexts() != null) {
			for (int i = 0; i < sub.getServiceWithTexts().length; i++) {
				services.add(new ServiceMenuImpl(sub.getServiceWithTexts()[i]));
			}
		}

		if (sub.getOption() != null) {
			for (int i = 0; i < sub.getOption().length; i++) {
				options.add(new OptionMenuImpl(sub.getOption()[i]));
			}
		}

		if (sub.getSubMenus() != null) {
			for (int i = 0; i < sub.getSubMenus().length; i++) {
				subMenus.add(new SubMenuImpl(sub.getSubMenus()[i]));
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
