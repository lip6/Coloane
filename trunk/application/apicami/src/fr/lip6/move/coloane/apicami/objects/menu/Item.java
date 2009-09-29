package fr.lip6.move.coloane.apicami.objects.menu;

import fr.lip6.move.coloane.interfaces.objects.menu.IItemMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Définition d'un item
 */
public class Item implements IItemMenu {
	/** Le nom de l'item */
	private String name;

	/** Liste des messages d'aide associés à l'item */
	private List<String> help;

	/** Visibilité de l'item */
	private boolean visibility;

	/** Chemin */
	private String path;

	/**
	 * Constructeur
	 * @param name Le nom de l'item
	 * @param visibility La visibilité de l'item
	 * @param help La liste des messages d'aide associés à l'item
	 * @param path Chemin
	 */
	public Item(String name, boolean visibility, List<String> help, String path) {
		this.name = name;
		if (help == null) {
			this.help = new ArrayList<String>();
		} else {
			this.help = help;
		}
		this.visibility = visibility;
		this.path = path;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getHelps() {
		StringBuilder toReturn = new StringBuilder();
		for (String helpLine : help) {
			toReturn.append(helpLine);
		}
		return toReturn.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isVisible() {
		return this.visibility;
	}

	public final String getPath() {
		return this.path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return name;
	}
}
