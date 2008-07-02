package fr.lip6.move.coloane.core.motor.formalisms.elements;

import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;

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
	private String associatedFigure;
	
	/** Est-ce que l'élément graphique est plein ? */
	private boolean filled;
	
	/** Icone (16pixels) associée à l'élément de formalisme */
	private String icon16px;
	
	/** Icone (24pixels) associée à l'élément de formalisme */ 
	private String icon24px;
	
	/** Hauteur de l'élément graphique */
	private String height;
	
	/** Largeur de l'élément graphique */
	private String width;
	
	
	/**
	 * Constructeur d'une description graphique
	 * @param palettable Est-ce que l'outil doit être affiché dans la palette ?
	 * @param drawable Est-ce que l'outil doit être affiché sur le modèle
	 */
	public GraphicalDescription(boolean palettable, boolean drawable) {
		this.palettable = palettable;
		this.drawable = drawable;
	}
	

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#getPaletteName()
	 */
	public String getPaletteName() {
		return paletteName;
	}


	/**
	 * Positionne le nom de l'élément de formalisme affiché dans la palette
	 * @param paletteName Nom à afficher dans la palette
	 */
	public void setPaletteName(String paletteName) {
		this.paletteName = paletteName;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#getDescription()
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Positionne la description de l'élément de formalisme affiché dans la palette
	 * @param description Description à afficher dans la palette
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#isPalettable()
	 */
	public boolean isPalettable() {
		return palettable;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#isDrawable()
	 */
	public boolean isDrawable() {
		return drawable;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#getAssociatedFigure()
	 */
	public String getAssociatedFigure() {
		return associatedFigure;
	}


	/**
	 * Positionne lea figure associée à l'élément de formalisme
	 * @param associatedFigure Nom de la classe figure associée à l'élément de formalisme
	 */
	public void setAssociatedFigure(String associatedFigure) {
		this.associatedFigure = associatedFigure;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#getIcon16px()
	 */
	public String getIcon16px() {
		return icon16px;
	}


	/**
	 * Positionne l'icone (16pixels) assicée à l'élément de formalisme
	 * @param icon16px Le chemin de l'icône 16 pixels
	 */
	public void setIcon16px(String icon16px) {
		this.icon16px = icon16px;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#getIcon24px()
	 */
	public String getIcon24px() {
		return icon24px;
	}


	/**
	 * Positionne l'icone (24pixels) assicée à l'élément de formalisme
	 * @param icon24px Le chemin de l'icône 24 pixels
	 */
	public void setIcon24px(String icon24px) {
		this.icon24px = icon24px;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#isFilled()
	 */
	public boolean isFilled() {
		return filled;
	}


	/**
	 * Indique si la figure doit être remplie (graphiquement parlant)
	 * @param filled <code>true</code> si la figure doit être pleine. <code>false</code> sinon.
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#getHeight()
	 */
	public String getHeight() {
		return height;
	}


	/**
	 * Positionne la hauteur de l'élément graphique associé à l'élément de formalisme
	 * @param height la hauteur de l'objet graphique
	 */
	public void setHeight(String height) {
		this.height = height;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.motor.formalisms.elements.IGraphicalDescription#getWidth()
	 */
	public String getWidth() {
		return width;
	}


	/**
	 * Positionne la largeur de l'élément graphique associé à l'élément de formalisme
	 * @param width la largeur de l'objet graphique
	 */
	public void setWidth(String width) {
		this.width = width;
	}
}
