package fr.lip6.move.coloane.api.camiObject.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IServiceMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Définition de la racine d'un menu de services
 */
public class SubMenu extends Item implements ISubMenu {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/** La liste des sous-menus */
	private List<ISubMenu> submenus;

	/** La liste des services simples attachés au sous-menu */
	private List<IServiceMenu> services;

	/** La liste des options attachées au sous-menu */
	private List<IOptionMenu> options;

	/** Type du sous-menu */
	private int type;

	/** Comportement du sous-menu */
	private int behavior;

	/**
	 *
	 * @param name Le nom du sous-menu
	 * @param type Le type du sous-menu
	 * @param behavior Le comportement du sous-menu
	 * @param visibility Visibilité du sous-menu (et de ses enfants)
	 */
	public SubMenu(String name, int type, int behavior, boolean visibility) {
		super(name, visibility, null);
		this.type = type;
		this.behavior = behavior;
		this.services = new ArrayList<IServiceMenu>();
		this.options = new ArrayList<IOptionMenu>();
		this.submenus = new ArrayList<ISubMenu>();
	}

	/**
	 * Recherche un sous-menu à partir de son nom
	 * @param name Le nom du sous-menu recherché
	 * @return Le sous menu désiré ou <code>null</code> si aucun ne correspond
	 */
	private ISubMenu findMenu(String name) {
		// Je commence par vérifier qu'il ne s'agit pas du sous-menu courant
		if (this.getName().equals(name)) {
			return this;
		}

		// Parcours de tous les sous-menu
		for (ISubMenu submenu : this.submenus) {
			if (submenu.getName().equals(name)) {
				return submenu;
			}
			// Appel récursif si nécessaire
			ISubMenu ret = ((SubMenu) submenu).findMenu(name);
			if (ret != null) {
				return ret;
			}
		}
		// Si on ne trouve rien
		return null;
	}

	/**
	 * Ajoute une question (option, item ou sous-menu) au sous-menu courant OU à un de ses fils
	 * @param question La description de la question transmise par la plate-forme
	 */
	public final void addQuestion(IQuestion question) {
		LOGGER.finer("Demande d'ajout de la question: " + question.getName());

		// Recherche du sous-menu
		ISubMenu parentMenu = findMenu(question.getParent());
		if (parentMenu == null) {
			// TODO Exception ou quelque chose dans le style
			LOGGER.warning("Impossible de trouver le parent (" + question.getParent() + ") de la question");
			return;
		}

		// Si la question transmise est la définition d'un sous-menu
		if (question.getType() == IQuestion.TYPE_SUBMENU) {
			((SubMenu) parentMenu).addSubMenu(question);
			LOGGER.finer("La question est ajoutee comme sous-menu de " + parentMenu.getName());
		// Si le père de la question transmise est un conteneur d'options
		} else if (((SubMenu) parentMenu).isContainingOptions()) {
			((SubMenu) parentMenu).addOptionMenu(question);
			LOGGER.finer("La question est ajoutee comme option de " + parentMenu.getName());
		// Dans tous les autres cas, c'est une description de services
		} else {
			((SubMenu) parentMenu).addServiceMenu(question);
			LOGGER.finer("La question est ajoutee comme service de " + parentMenu.getName());
		}
	}

	/**
	 * Ajoute une nouvelle option au menu
	 * @param question La description du menu
	 */
	private void addOptionMenu(IQuestion question) {
		IOptionMenu option = new OptionMenu(question.getName(), question.isVisible(), null, question.isValid());
		this.options.add(option);
	}

	/**
	 * Ajoute un nouveau sous-menu au menu courant
	 * @param question La description du menu à ajouter
	 */
	private void addSubMenu(IQuestion question) {
		SubMenu submenu = new SubMenu(question.getName(), question.getType(), question.getBehavior(), question.isVisible());
		this.submenus.add(submenu);
	}

	/**
	 * Ajoute une nouvelle entrée de service dans le menu
	 * @param question La description de l'entrée de service à ajouter
	 * TODO : Gérer la liste des aides (est-ce possible ? pour le moment = null)
	 * TODO : Gérer la question associée (pour le moment il s'agit du nom)
	 */
	private void addServiceMenu(IQuestion question) {
		IServiceMenu service = new ServiceMenu(question.getName(), question.isVisible(), null, question.getName());
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

	/**
	 * Est-ce que le sous-menu considéré est un conteneur d'option ?
	 * @return <code>true</code> si le sous-menu contient des options
	 */
	private boolean isContainingOptions() {
		return (this.behavior != IQuestion.BEHAVIOR_ONECHOICE);
	}
}
