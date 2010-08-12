package fr.lip6.move.coloane.interfaces.objects.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Define a sub-menu.<br>
 * 
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public class SubMenu extends ItemMenu implements ISubMenu {
	/** A list of sub-menus */
	private List<ISubMenu> submenus;

	/** A list of service items (associated with this sub-menu) */
	private List<IServiceMenu> services;

	/** A list of option items (associated with this sub-menu) */
	private List<IOptionMenu> options;

	/**
	 * Constructor
	 * @param name The name of the sub-menu
	 * @param visible The visible state of the sub-menu (and all of its children)
	 * @param path Path
	 */
	public SubMenu(String name, boolean visible) {
		super(name, visible, null);
		this.services = new ArrayList<IServiceMenu>();
		this.options = new ArrayList<IOptionMenu>();
		this.submenus = new ArrayList<ISubMenu>();
	}

	/**
	 * {@inheritDoc}
	 */
	public void addOptionMenu(IOptionMenu option) {
		this.options.add(option);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addSubMenu(ISubMenu submenu) {
		this.submenus.add(submenu);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addServiceMenu(IServiceMenu service) {
		this.services.add(service);
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IServiceMenu> getServiceMenus() {
		return this.services;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<IOptionMenu> getOptions() {
		return this.options;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubMenu> getSubMenus() {
		return this.submenus;
	}
}
