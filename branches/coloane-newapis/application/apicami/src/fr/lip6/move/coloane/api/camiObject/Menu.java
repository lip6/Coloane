package fr.lip6.move.coloane.api.camiObject;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IMenu;

/**
 * cette classe implemente l'interface IMenu, qui represente un Menu.
 *
 * @author kahoo && uu
 *
 */
public class Menu implements IMenu {

	/** activé ou pas */
	private boolean activate;

	/** peut-on l'arreter ou pas */
	private boolean stopAuthorized;

	/** les sous-menus */
	private ArrayList<IMenu> children;

	/** le nom du menu */
	private String name;

	/** son menu parent */
	private IMenu parent;

	/** la question precedente */
	private int questionBehavior;

	/** le type de question */
	private int questionType;

	/** dialogue permis ou pas */
	private boolean dialogAllowed;

	/** menu valide ou pas set-item */
	private boolean valid;

	/** le formalisme(le domaine resultat) */
	private String outputFormalism;

	/**
	 * le constructeur par default.
	 */
	public Menu() {
		this.activate = false;
		this.children = null;
		this.dialogAllowed = false;
		this.name = null;
		this.outputFormalism = null;
		this.parent = null;
		this.questionBehavior = -1;
		this.questionType = -1;
		this.stopAuthorized = false;
		this.valid = false;

	}

	/**
	 * le constructeur de notre classe.
	 *
	 * @param parent
	 * @param name
	 * @param questionType
	 * @param questionBehavior
	 * @param valid
	 * @param dialogAllowed
	 * @param stopAuthorized
	 * @param outputFormalism
	 * @param activate
	 * @param children
	 */
	public Menu(IMenu parent, String name, int questionType,
			int questionBehavior, boolean valid, boolean dialogAllowed,
			boolean stopAuthorized, String outputFormalism, boolean activate,
			ArrayList<IMenu> children) {
		this.activate = activate;
		this.children = children;
		this.dialogAllowed = dialogAllowed;
		this.name = name;
		this.outputFormalism = outputFormalism;
		this.parent = parent;
		this.questionBehavior = questionBehavior;
		this.questionType = questionType;
		this.stopAuthorized = stopAuthorized;
		this.valid = valid;

	}

	/**
	 * nous retourne true si menu active , false sinon.
	 *
	 * @return boolean
	 */
	public boolean isActivate() {

		return this.activate;
	}

	/**
	 * nous dit si on peut l'arreter ou pas .
	 *
	 * @return boolean.
	 */
	public boolean stopAuthorized() {

		return this.stopAuthorized;
	}

	/**
	 * nous retourne les sous-menus.
	 *
	 * @return les sous-menus.
	 */
	public ArrayList<IMenu> getChildren() {

		return this.children;
	}

	/**
	 * nous retourne le nom du menu.
	 *
	 * @return son nom.
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * nous retourne le menu parent.
	 *
	 * @return le parent.
	 */
	public IMenu getParent() {

		return this.parent;
	}

	/**
	 * nous retourne le numero de la question precedente.
	 *
	 * @return son numero.
	 */
	public int getQuestionBehavior() {

		return this.questionBehavior;
	}

	/**
	 * le type de la question.
	 *
	 * @return le numero.
	 */
	public int getQuestionType() {

		return this.questionType;
	}

	/**
	 * nous dit si on le dialogue est permis.
	 *
	 * @return boolean.
	 */
	public boolean isDialogAllowed() {

		return this.dialogAllowed;
	}

	/**
	 * nous dit si c'est valide ou pas.
	 *
	 * @return boolean.
	 */
	public boolean isValid() {

		return this.valid;
	}

	/**
	 * le formalisme de sortie.
	 *
	 * @return boolean.
	 */
	public String outputFormalism() {

		return this.outputFormalism;
	}

	/**
	 * nous permet de chainer un IMenu dans ma racine
	 */
	public final boolean addMenu(String parentName, IMenu menu) {
		if (parentName.equals(this.name)) {

			this.children.add(menu);
			menu.setParent(this);

			return true;
		} else {

			ArrayList<IMenu> tmp = this.getChildren();
			for (IMenu child : tmp) {
				((IMenu) child).addMenu(parentName, menu);

			}

			return true;

		}

	}

	public void setParent(IMenu parent) {
		this.parent = parent;
	}

}
