package fr.lip6.move.coloane.interfaces.formalism;

/**
 * Cette classe regroupe toutes les informations grapgique relative à un élément de formalisme
 */
public interface IGraphicalDescription {

	/**
	 * @return Le nom qui doit être affiché dans la palette pour cet élément de formalisme
	 */
	public abstract String getPaletteName();

	/**
	 * @return La description de l'élément de formalisme
	 */
	public abstract String getDescription();

	/**
	 * @return Est-ce que l'élément de formalisme doit être affiché dans la palette ? 
	 */
	public abstract boolean isPalettable();

	/**
	 * @return Est-ce que l'élément de formalisme doit être visible sur le modèle
	 */
	public abstract boolean isDrawable();

	/**
	 * @return La figure associée à l'élément de formalisme
	 */
	public abstract String getAssociatedFigure();

	/**
	 * @return L'icône (16 pixels) associée à l'élément de formalisme
	 */
	public abstract String getIcon16px();

	/**
	 * @return L'icône (24 pixels) associée à l'élément de formalisme
	 */
	public abstract String getIcon24px();

	/**
	 * @return L'indicateur de remplissage de ma figure
	 */
	public abstract boolean isFilled();

	/**
	 * @return La hauteur par défaut de la figure
	 */
	public abstract String getHeight();

	/**
	 * @return La largeur par défaut de la figure
	 */
	public abstract String getWidth();

}