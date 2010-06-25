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

	/** Width of the Label field. */
	int LABEL_WIDTH = 100;

	/**
	 * Redraw and refresh the display.
	 */
	void redraw();

	/**
	 * @return <code>true</code> if the label is currently visible.
	 */
	boolean isVisible();

	/**
	 * Change visibility of the embedded graphical elements.
	 * @param visible the new visibility.
	 */
	void setVisible(boolean visible);

	/**
	 * @return The current value of this attribute.
	 */
	String getText();

	/**
	 * @param string Update the current value of this attribute.
	 */
	void setText(String string);

	/**
	 * @return the parent of this (pseudo) widget.
	 */
	Composite getParent();

	/**
	 * @return The control (Text or Combo) used to anchor the position of the next element.
	 */
	Control getControl();

	/**
	 * Allows to register a listener for modifications.
	 * @param listener the listener.
	 */
	void addModifyListener(ModifyListener listener);

	/** 
	 * @return The label of this configuration element.
	 */
	String getLabel();

}
