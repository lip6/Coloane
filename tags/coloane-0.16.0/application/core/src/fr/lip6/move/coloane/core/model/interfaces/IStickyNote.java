package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * Interface définissant une note, ses comportements publics et ses propriétés
 */
public interface IStickyNote extends ILocationInfo, ILinkableElement {

	/** ID pour la propriete lorsqu'un changement de la valeur */
	String VALUE_PROP = "Sticky.ValueUpdate"; //$NON-NLS-1$

	/** ID pour la propriété de changement de taille */
	String RESIZE_PROP = "Sticky.Resize"; //$NON-NLS-1$

	/**
	 * @return Le contenu de la note
	 */
	String getLabelContents();

	/**
	 * Indique un nouveau contenu pour la note
	 * @param newText Le nouveau contenu
	 */
	void setLabelContents(String newText);

	/**
	 * @return Taille de la note
	 */
	Dimension getSize();

	/**
	 * @param size nouvelle taille de la note
	 */
	void setSize(Dimension size);
}
