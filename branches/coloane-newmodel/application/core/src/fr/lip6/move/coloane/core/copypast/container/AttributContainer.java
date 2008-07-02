package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.interfaces.model.interfaces.IAttribute;

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
	public AttributContainer(IAttribute attr) {
		name = attr.getName();
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
	 * Définis la position de l'attribut.
	 * @param x
	 * @param y
	 */
	public final void setLocation(int x, int y) {
		this.location = new Point(x, y);
	}
}
