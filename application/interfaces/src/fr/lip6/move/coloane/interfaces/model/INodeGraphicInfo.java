package fr.lip6.move.coloane.interfaces.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Color;

import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;

/**
 * All available graphical information for a node.<br>
 * 
 * @author Jean-Baptiste Voron
 */

public interface INodeGraphicInfo extends ILocationInfo {

	/**
	 * @return <code>true</code> if the figure must be filled with the background color; <code>false</code> otherwise.
	 */
	boolean isFilled();

	/**
	 * @return The background color
	 * @see #setBackground(Color)
	 */
	Color getBackground();

	/**
	 * @param background The background color
	 * @see #setForeground(Color)
	 */
	void setBackground(Color background);

	/**
	 * @return The foreground color
	 * @see #getBackground()
	 */
	Color getForeground();

	/**
	 * @param foreground The foreground color
	 * @see #setBackground(Color)
	 */
	void setForeground(Color foreground);

	/**
	 * Set a new scale factor for this node
	 * @param scale The scale applied to the node (100 = normal size)
	 * @see #getScale()
	 */
	void setScale(int scale);

	/**
	 * @return The scale factor currently used for this node
	 * @see #setScale(int)
	 */
	int getScale();

	/**
	 * Returns the dimensions of the node according to the scale factor currently set
	 * @return The dimensions of the node
	 * @see #setScale(int)
	 */
	Dimension getSize();
	
	/**
	 * @return All graphical descriptions associated to this node
	 */
	List<IGraphicalDescription> getAllNodeFormalismGraphicalDescriptions();
	
	/**
	 * Change the current figure used to represent the node<br>
	 * The new figure is the one that follow the current figure in the list of available alternative figures<br>
	 * @return The index of the <b>previous</b> selected figure
	 */
	int switchGraphicalDescription();

	/**
	 * Change the current figure used to represent the node<br>
	 * The new figure is the one designated by the id parameter<br>
	 * 
	 * @param selectedIndex The index that designates the figure to be selected
	 * @return The index of the <b>previous</b> selected figure
	 */
	int switchGraphicalDescription(int selectedIndex);
}
