package fr.lip6.move.coloane.core.ui.properties;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Interface that captures commonality of the various elements in a Property view.
 * @author Yann TM
 *
 */
public interface IAttributeLabel {

	/** Largeur du label */
	int LABEL_WIDTH = 100;

	/**
	 * Redessine le LabelText.
	 */
	void redraw();

	/**
	 * @return <code>true</code> si le LabelText est visible
	 */
	boolean isVisible();

	/**
	 * Change la visibilité du LabelText
	 * @param visible nouvelle état
	 */
	void setVisible(boolean visible);

	/**
	 * @return la valeur du LabelText
	 */
	String getText();

	/**
	 * @param string nouvelle valeur du LabelText
	 */
	void setText(String string);

	/**
	 * @return le parent de ce pseudo-widget
	 */
	Composite getParent();

	/**
	 * @return Le controle (Text ou Combo) qui ancre la position de l'élement suivant.
	 */
	Control getControl();

	void addModifyListener(ModifyListener listener);

	/** 
	 * @return The label of this configuration element.
	 */
	String getLabel();

}