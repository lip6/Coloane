package fr.lip6.move.coloane.core.motor.formalisms.elements;

public class GraphicalDescription {
	
	private String paletteName;
	
	private String description;
	
	private boolean palettable;
	
	private boolean drawable;
	
	private String associatedFigure;
	
	private boolean filled;
	
	private String icon16px;
	
	private String icon24px;
	
	private String height;
	
	private String width;
	
	
	public GraphicalDescription(boolean palettable, boolean drawable) {
		this.palettable = palettable;
		this.drawable = drawable;
	}
	

	public String getPaletteName() {
		return paletteName;
	}


	public void setPaletteName(String paletteName) {
		this.paletteName = paletteName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isPalettable() {
		return palettable;
	}


	public boolean isDrawable() {
		return drawable;
	}


	public String getAssociatedFigure() {
		return associatedFigure;
	}


	public void setAssociatedFigure(String associatedFigure) {
		this.associatedFigure = associatedFigure;
	}


	public String getIcon16px() {
		return icon16px;
	}


	public void setIcon16px(String icon16px) {
		this.icon16px = icon16px;
	}


	public String getIcon24px() {
		return icon24px;
	}


	public void setIcon24px(String icon24px) {
		this.icon24px = icon24px;
	}

	public boolean isFilled() {
		return filled;
	}


	public void setFilled(boolean filled) {
		this.filled = filled;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getWidth() {
		return width;
	}


	public void setWidth(String width) {
		this.width = width;
	}
}
