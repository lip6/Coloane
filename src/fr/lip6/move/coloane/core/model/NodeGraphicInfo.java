package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.interfaces.formalism.IGraphicalDescription;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * This class defines all graphical properties of a node representation.<br>
 * Note that a node can declare several graphical representation.
 *
 *  @author Jean-Baptiste Voron
 */
public class NodeGraphicInfo implements INodeGraphicInfo {
	/** Core Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** The node (parent) */
	private final NodeModel node;

	/** All the graphical descriptions specified by the formalism */
	private final List<IGraphicalDescription> nodeFormalismGraphicalDescriptions = new ArrayList<IGraphicalDescription>();

	/** The index of the current graphical representation */
	private int gdIndex;

	/** The location of the node */
	private int x;
	private int y;

	/** Scaling factor */
	private int scale = 100;

	/** Node colors (foreground / background) */
	private Color foreground = ColorConstants.black;
	private Color background = ColorConstants.white;

	/**
	 * Constructor.<br>
	 * All information is extracted from the formalism definition.
	 * More especially from the node formalism graphical description
	 * @see IGraphicalDescription
	 * @param node The node to which this graphicInfo is associated to
	 */
	public NodeGraphicInfo(INode node) {
		this.node = (NodeModel) node;
		this.nodeFormalismGraphicalDescriptions.addAll(node.getNodeFormalism().getAllGraphicalDescription());
		this.gdIndex = 0;

		// Determine the background color
		if (this.isFilled()) {
			background = ColorConstants.black;
		}
	}

	/** {@inheritDoc} */
	public final Point getLocation() {
		return new Point(this.x, this.y);
	}

	/** {@inheritDoc} */
	public final void setLocation(Point location) {
		LOGGER.finest("setLocation(" + location.x + ", " + location.y + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		Point oldLocation = new Point(this.x, this.y);
		this.x = location.x;
		this.y = location.y;

		// Fire an event for the node (and thus for its EditPart)
		node.firePropertyChange(LOCATION_PROP, oldLocation, location.getCopy());
	}
	
	/** {@inheritDoc} */
	public final void resetLocation() {
		setLocation(new Point(0, 0));
	}

	/**
	 * Returns the width of the node according to the scale factor currently set
	 * @return The width of the node
	 */
	private int getWidth() {
		return (this.getCurrentGraphicalDescription().getWidth() * scale) / 100;
	}

	/**
	 * Returns the height of the node according to the scale factor currently set
	 * @return The height of the node
	 */
	private int getHeight() {
		return (this.getCurrentGraphicalDescription().getHeight() * scale) / 100;
	}

	/** {@inheritDoc} */
	public final Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}

	/** {@inheritDoc} */
	public final boolean isFilled() {
		return this.getCurrentGraphicalDescription().isFilled();
	}

	/** {@inheritDoc} */
	public final Color getBackground() {
		return background;
	}

	/** {@inheritDoc} */
	public final void setBackground(Color background) {
		Color oldValue = this.background;
		this.background = background;
		node.firePropertyChange(INode.BACKGROUND_COLOR_PROP, oldValue, background);
	}

	/** {@inheritDoc} */
	public final Color getForeground() {
		return foreground;
	}

	/** {@inheritDoc} */
	public final void setForeground(Color foreground) {
		Color oldValue = this.foreground;
		this.foreground = foreground;
		node.firePropertyChange(INode.FOREGROUND_COLOR_PROP, oldValue, foreground);
	}

	/** {@inheritDoc} */
	public final void setScale(int scale) {
		Dimension oldSize = new Dimension();
		oldSize.height = getHeight();
		oldSize.width = getWidth();

		this.scale = scale;
		Dimension newSize = new Dimension();
		newSize.height = getHeight();
		newSize.width = getWidth();
		node.firePropertyChange(INode.RESIZE_PROP, oldSize, newSize);
	}

	/** {@inheritDoc} */
	public final int getScale() {
		return scale;
	}

	/** {@inheritDoc} */
	public final List<IGraphicalDescription> getAllNodeFormalismGraphicalDescriptions() {
		return nodeFormalismGraphicalDescriptions;
	}

	/** {@inheritDoc} */
	public final int switchGraphicalDescription() {
		int newGdIndex = this.gdIndex;
		int oldGdIndex = this.gdIndex;

		newGdIndex++;
		if (newGdIndex >= this.nodeFormalismGraphicalDescriptions.size()) {
			newGdIndex = 0;
		}

		node.firePropertyChange(INode.ALTERNATE_PROP, oldGdIndex, newGdIndex);
		this.gdIndex = newGdIndex;

		// Determine the background color
		Color oldValue = this.background;
		if (this.isFilled()) {
			background = ColorConstants.black;
		} else {
			background = ColorConstants.white;
		}
		node.firePropertyChange(INode.BACKGROUND_COLOR_PROP, oldValue, background);

		// Determine the new size
		Dimension oldSize = new Dimension();
		oldSize.height = nodeFormalismGraphicalDescriptions.get(oldGdIndex).getHeight();
		oldSize.width = nodeFormalismGraphicalDescriptions.get(oldGdIndex).getWidth();
		Dimension newSize = new Dimension();
		newSize.height = nodeFormalismGraphicalDescriptions.get(newGdIndex).getHeight();
		newSize.width = nodeFormalismGraphicalDescriptions.get(newGdIndex).getWidth();
		node.firePropertyChange(INode.RESIZE_PROP, oldSize, newSize);

		return oldGdIndex;
	}

	/** {@inheritDoc} */
	public final int switchGraphicalDescription(int selectedIndex) {
		int oldGdIndex = this.gdIndex;
		if ((selectedIndex >= 0) && (selectedIndex < this.nodeFormalismGraphicalDescriptions.size())) {
			this.gdIndex = selectedIndex;
		} else {
			LOGGER.warning("Invalid index for alternate figure !"); //$NON-NLS-1$
			this.gdIndex = oldGdIndex;
		}
		node.firePropertyChange(INode.ALTERNATE_PROP, oldGdIndex, this.gdIndex);

		// Determine the background color
		Color oldValue = this.background;
		if (this.isFilled()) {
			background = ColorConstants.black;
		} else {
			background = ColorConstants.white;
		}
		node.firePropertyChange(INode.BACKGROUND_COLOR_PROP, oldValue, background);

		// Determine the new size
		Dimension oldSize = new Dimension();
		oldSize.height = nodeFormalismGraphicalDescriptions.get(oldGdIndex).getHeight();
		oldSize.width = nodeFormalismGraphicalDescriptions.get(oldGdIndex).getWidth();
		Dimension newSize = new Dimension();
		newSize.height = nodeFormalismGraphicalDescriptions.get(selectedIndex).getHeight();
		newSize.width = nodeFormalismGraphicalDescriptions.get(selectedIndex).getWidth();
		node.firePropertyChange(INode.RESIZE_PROP, oldSize, newSize);

		return oldGdIndex;
	}

	/**
	 * @return The current graphical description (according to the index)
	 */
	private IGraphicalDescription getCurrentGraphicalDescription() {
		return this.nodeFormalismGraphicalDescriptions.get(this.gdIndex);
	}

	/** {@inheritDoc} */
	public final int getGdIndex() {
		return this.gdIndex;
	}
}
