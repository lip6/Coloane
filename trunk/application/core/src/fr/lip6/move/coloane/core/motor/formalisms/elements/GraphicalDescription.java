package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;

import org.eclipse.draw2d.IFigure;

/**
 * Cette classe regroupe toutes les informations grapgique relative à un élément de formalisme
 */
public class GraphicalDescription implements IGraphicalDescription {
	/** Le nom de l'élément de formalisme qui sera affiché sur la palette */
	private String paletteName;

	/** La description associée à l'élément de formalisme */
	private String description;

	/** Est-ce que l'élément de formalisme doit se trouver dans la palette ? */
	private boolean palettable;

	/** Est-ce que l'élément de formalisme doit pouvoir être affiché ? */
	private boolean drawable;

	/** Figure (JAVA) associée à l'élément de formalisme */
	private Class< ? > associatedFigureClass;

	/** Est-ce que l'élément graphique est plein ? */
	private boolean filled = false;

	/** Icone (16pixels) associée à l'élément de formalisme */
	private String icon16px;

	/** Icone (24pixels) associée à l'élément de formalisme */
	private String icon24px;

	/** Hauteur de l'élément graphique */
	private int height = 15;

	/** Largeur de l'élément graphique */
	private int width = 15;

	/**
	 * Constructeur d'une description graphique
	 * @param palettable Est-ce que l'outil doit être affiché dans la palette ?
	 * @param drawable Est-ce que l'outil doit être affiché sur le modèle
	 */
	public GraphicalDescription(boolean palettable, boolean drawable) {
		this.palettable = palettable;
		this.drawable = drawable;
	}


	/** {@inheritDoc} */
	public final String getPaletteName() {
		return paletteName;
	}


	/**
	 * Positionne le nom de l'élément de formalisme affiché dans la palette
	 * @param paletteName Nom à afficher dans la palette
	 */
	public final void setPaletteName(String paletteName) {
		this.paletteName = paletteName;
	}


	/** {@inheritDoc} */
	public final String getDescription() {
		return description;
	}


	/**
	 * Positionne la description de l'élément de formalisme affiché dans la palette
	 * @param description Description à afficher dans la palette
	 */
	public final void setDescription(String description) {
		this.description = description;
	}


	/** {@inheritDoc} */
	public final boolean isPalettable() {
		return palettable;
	}


	/** {@inheritDoc} */
	public final boolean isDrawable() {
		return drawable;
	}


	/** {@inheritDoc} */
	public final IFigure getAssociatedFigure() {
		try {
			return (IFigure) associatedFigureClass.getConstructor(new Class< ? >[0]).newInstance(new Object[0]);
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * Positionne lea figure associée à l'élément de formalisme
	 * @param c Nom de la classe figure associée à l'élément de formalisme
	 */
	public final void setAssociatedFigure(Class< ? > c) {
		this.associatedFigureClass = c;
	}


	/** {@inheritDoc} */
	public final String getIcon16px() {
		return "/" + icon16px; //$NON-NLS-1$
	}


	/**
	 * Positionne l'icone (16pixels) assicée à l'élément de formalisme
	 * @param icon16px Le chemin de l'icône 16 pixels
	 */
	public final void setIcon16px(String icon16px) {
		this.icon16px = icon16px;
	}


	/** {@inheritDoc} */
	public final String getIcon24px() {
		return "/" + icon24px; //$NON-NLS-1$
	}


	/**
	 * Positionne l'icone (24pixels) assicée à l'élément de formalisme
	 * @param icon24px Le chemin de l'icône 24 pixels
	 */
	public final void setIcon24px(String icon24px) {
		this.icon24px = icon24px;
	}

	/** {@inheritDoc} */
	public final boolean isFilled() {
		return filled;
	}


	/**
	 * Indique si la figure doit être remplie (graphiquement parlant)
	 * @param filled <code>true</code> si la figure doit être pleine. <code>false</code> sinon.
	 */
	public final void setFilled(boolean filled) {
		this.filled = filled;
	}


	/** {@inheritDoc} */
	public final Integer getHeight() {
		return this.height;
	}


	/**
	 * Positionne la hauteur de l'élément graphique associé à l'élément de formalisme
	 * @param height la hauteur de l'objet graphique
	 */
	public final void setHeight(String height) {
		this.height = Integer.valueOf(height);
	}


	/** {@inheritDoc} */
	public final Integer getWidth() {
		return this.width;
	}


	/**
	 * Positionne la largeur de l'élément graphique associé à l'élément de formalisme
	 * @param width la largeur de l'objet graphique
	 */
	public final void setWidth(String width) {
		this.width = Integer.valueOf(width);
	}
}
