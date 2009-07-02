package fr.lip6.move.coloane.interfaces.model;

import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;

import java.util.List;

/**
 * This class defines a node of a model<br>
 * This class alos defines several properties that are sent to edits parts<br>
 * @see IElement
 *
 * @author Jean-Baptiste Voron
 * @author Clément Demoulins
 */
public interface INode extends IElement {

	/** When the list of outgoing arcs is modified */
	String OUTGOING_ARCS_PROP = "Node.OutputArc"; //$NON-NLS-1$

	/** When the list of incoming arcs is modified */
	String INCOMING_ARCS_PROP = "Node.InputArc"; //$NON-NLS-1$

	/** When the value of the node is modified */
	String VALUE_PROP = "Node.ValueUpdate"; //$NON-NLS-1$

	/** When the node is selected */
	String SELECT_PROP = "Node.SelectUpdate"; //$NON-NLS-1$

	/** When the node is unselected */
	String UNSELECT_PROP = "Node.UnSelectUpdate"; //$NON-NLS-1$

	/** When the node is highlighted by a tool */
	String SPECIAL_PROP = "Node.SpecialUpdate"; //$NON-NLS-1$

	/** When the node is no more highlighted by a tool */
	String UNSPECIAL_PROP = "Node.UnSpecialUpdate"; //$NON-NLS-1$

	/** When to background color is updated */
	String BACKGROUND_COLOR_PROP = "Node.Color.Background"; //$NON-NLS-1$

	/** When the foreground color is update */
	String FOREGROUND_COLOR_PROP = "Node.Color.Foreground"; //$NON-NLS-1$

	/** When the scale factor is modified */
	String RESIZE_PROP = "Node.Zoom"; //$NON-NLS-1$

	/** When an alternate figure must be loaded */
	String ALTERNATE_PROP = "Node.Alternate"; //$NON-NLS-1$

	/** When public state of an node change */
	String PUBLIC_PROP = "Node.Public";

	/**
	 * @return The formalism associated to the node
	 */
	INodeFormalism getNodeFormalism();

	/**
	 * @return The default graphical information associated to the node
	 */
	INodeGraphicInfo getGraphicInfo();

	/**
	 * @return A list of outgoing arcs. The list is <b>unmodifiable</b>.
	 */
	List<IArc> getOutgoingArcs();

	/**
	 * @return A list of incoming arcs. The list is <b>unmodifiable</b>.
	 */
	List<IArc> getIncomingArcs();

	/**
	 * Ask for updating tips associated to the node
	 */
	void updateTips();

	/**
	 * @return <code>true</code> if the node is public, it's for link nodes.
	 */
	boolean isInterface();

	/**
	 * Change the state of the node
	 * @param state new state
	 */
	void setInterface(boolean state);

	/**
	 * @return reference to a public node or <code>null</code>.
	 */
	String getNodeLink();

	/**
	 * @param link new link to a public node or <code>null</code> to delete link.
	 */
	void setNodeLink(String link);
}
