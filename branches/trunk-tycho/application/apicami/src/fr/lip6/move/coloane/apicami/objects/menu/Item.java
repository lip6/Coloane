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

	/**
	 * {@inheritDoc}
	 */
	public final String getPath() {
		return this.path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (path == null) {
			if (other.path != null) {
				return false;
			}
		} else if (!path.equals(other.path)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return name;
	}
}
