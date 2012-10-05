/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
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
	 * @return The control (Text, Combo or Canvas) used to anchor the position of the next element.
	 */
	Control getControl();
	
	/**
	 * @return The control (Text or Combo) used to identify the element.
	 */
	Control getControlText();

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
