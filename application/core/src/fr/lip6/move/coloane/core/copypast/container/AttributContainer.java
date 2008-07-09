package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;

import org.eclipse.draw2d.geometry.Point;

/**
 * Classe permettant la reconstruction d'un attribut
 */
public class AttributContainer {
	private int id;
	private String name;
	private String value;
	private Point location;

	/**
	 * @param attr
	 */
	public AttributContainer(IAttributeImpl attr) {
		id = attr.getId();
		name = attr.getDisplayName();
		value = attr.getValue();
		location = attr.getGraphicInfo().getLocation();
	}

	/**
	 * @return id de l'attribut à passer au setPropertyValue d'un objet du model
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return valeur de l'attribut
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @return la position de l'attribut
	 */
	public final Point getLocation() {
		return location;
	}

	/**
	 * @return le nom de l'attribut
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Indique la position d'un attribut
	 * @param x En abcisse
	 * @param y En ordonnée
	 */
	public final void setLocation(int x, int y) {
		this.location = new Point(x, y);
	}
}
