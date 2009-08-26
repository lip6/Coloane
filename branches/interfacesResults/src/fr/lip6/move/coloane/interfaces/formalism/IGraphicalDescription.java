package fr.lip6.move.coloane.interfaces.formalism;

import org.eclipse.draw2d.IFigure;

/**
 * Cette classe regroupe toutes les informations grapgique relative à un élément de formalisme
 */
public interface IGraphicalDescription {

	/**
	 * @return Le nom qui doit être affiché dans la palette pour cet élément de formalisme
	 */
	String getPaletteName();

	/**
	 * @return La description de l'élément de formalisme
	 */
	String getDescription();

	/**
	 * @return Est-ce que l'élément de formalisme doit être affiché dans la palette ?
	 */
	boolean isPalettable();

	/**
	 * @return Est-ce que l'élément de formalisme doit être visible sur le modèle
	 */
	boolean isDrawable();

	/**
	 * @return La figure associée à l'élément de formalisme
	 */
	IFigure getAssociatedFigure();

	/**
	 * @return L'icône (16 pixels) associée à l'élément de formalisme
	 */
	String getIcon16px();

	/**
	 * @return L'icône (24 pixels) associée à l'élément de formalisme
	 */
	String getIcon24px();

	/**
	 * @return L'indicateur de remplissage de ma figure
	 */
	boolean isFilled();

	/**
	 * @return La hauteur par défaut de la figure
	 */
	Integer getHeight();

	/**
	 * @return La largeur par défaut de la figure
	 */
	Integer getWidth();
}
