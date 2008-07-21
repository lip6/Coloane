package fr.lip6.move.coloane.interfaces.model;

import org.eclipse.swt.graphics.Color;

/**
 * Interface des information graphique concernant un noeud
 * @see fr.lip6.move.coloane.interfaces.model.impl.interfaces.NodeGraphicInfo
 */

public interface INodeGraphicInfo extends ILocationInfo {

	/**
	 * Retourne un booleen indiquant si la figure est remplie ou non.
	 * @return boolean
	 */
	boolean isFilled();

	/**
	 * @return heure du dernier deplacement de l'arc passé en paramètre ou 0 si l'arc n'a jamais été déplacé.
	 */
	Long getLastMove(IArc key);

	/**
	 * @return couleur de fond
	 */
	Color getBackground();

	/**
	 * @param background
	 */
	void setBackground(Color background);

	/**
	 * @return couleur du noeud
	 */
	Color getForeground();

	/**
	 * @param foreground
	 */
	void setForeground(Color foreground);

	/**
	 * Permet de définir la taille du noeud
	 * @param zoom pourcentage
	 */
	void setScale(int scale);

	/**
	 * @return pourcentage
	 */
	int getScale();
}
