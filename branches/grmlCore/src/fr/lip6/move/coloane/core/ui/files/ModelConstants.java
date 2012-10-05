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
package fr.lip6.move.coloane.core.ui.files;

/**
 * Set of constants useful when dealing with XML files that describe models
 *
 * @author Jean-Baptiste Voron
 * @author Clément Démoulins
 */
public final class ModelConstants {
	
	/** identifier for nodes description */
	public static final String NODES_LIST_MARKUP = "nodes"; //$NON-NLS-1$
	/** identifier for arcs description */
	public static final String ARCS_LIST_MARKUP = "arcs"; //$NON-NLS-1$
	/** identifier for nodes description */
	public static final String STICKYS_LIST_MARKUP = "stickys"; //$NON-NLS-1$
	/** identifier for attribute name description */
	public static final String ATTRIBUTES_LIST_MARKUP = "attributes"; //$NON-NLS-1$

	/** identifier for attribute name description */
	public static final String MODEL_FORMALISM_MARKUP = "formalismUrl"; //$NON-NLS-1$

	/** identifier for model description */
	public static final String MODEL_MARKUP = "model"; //$NON-NLS-1$
	/** identifier for attribute description */
	public static final String ATTRIBUTE_MARKUP = "attribute"; //$NON-NLS-1$
	/** identifier for node description */
	public static final String NODE_MARKUP = "node"; //$NON-NLS-1$
	/** identifier for arc description */
	public static final String ARC_MARKUP = "arc"; //$NON-NLS-1$
	/** identifier for inflex point (bendpoint) description */
	public static final String PI_MARKUP = "pi"; //$NON-NLS-1$
	/** identifier for link description */
	public static final String LINK_MARKUP = "link"; //$NON-NLS-1$
	/** identifier for sticky note description */
	public static final String STICKY_MARKUP = "sticky"; //$NON-NLS-1$

	/** identifier for attribute name description */
	public static final String ATTRIBUTE_NAME_MARKUP = "name"; //$NON-NLS-1$
	/** identifier for attribute value description */
	public static final String ATTRIBUTE_VALUE_MARKUP = "value"; //$NON-NLS-1$
	/** identifier for attribute x position description */
	public static final String ATTRIBUTE_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for attribute y position description */
	public static final String ATTRIBUTE_Y_MARKUP = "yposition"; //$NON-NLS-1$

	/** identifier for node id description */
	public static final String NODE_ID_MARKUP = "id"; //$NON-NLS-1$
	/** identifier for node type description */
	public static final String NODE_TYPE_MARKUP = "nodeType"; //$NON-NLS-1$
	/** identifier for node x position description */
	public static final String NODE_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for node y position description */
	public static final String NODE_Y_MARKUP = "yposition"; //$NON-NLS-1$
	/** identifier for node scale description */
	public static final String NODE_SCALE_MARKUP = "scale"; //$NON-NLS-1$
	/** identifier for node alternate figures description */
	public static final String NODE_ALTERNATE_MARKUP = "alt"; //$NON-NLS-1$
	/** identifier for node foreground color description */
	public static final String NODE_FOREGROUND_MARKUP = "foreground"; //$NON-NLS-1$
	/** identifier for node background color description */
	public static final String NODE_BACKGROUND_MARKUP = "background"; //$NON-NLS-1$
	/** identifier for node interface description */
	public static final String NODE_INTERFACE_MARKUP = "interface"; //$NON-NLS-1$
	/** identifier for node link description */
	public static final String NODE_LINK_MARKUP = "link"; //$NON-NLS-1$

	/** identifier for sticky x position description */
	public static final String STICKY_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for sticky y position description */
	public static final String STICKY_Y_MARKUP = "yposition"; //$NON-NLS-1$
	/** identifier for sticky width description */
	public static final String STICKY_WIDTH_MARKUP = "width"; //$NON-NLS-1$
	/** identifier for sticky height description */
	public static final String STICKY_HEIGHT_MARKUP = "height"; //$NON-NLS-1$
	/** identifier for sticky height description */
	public static final String STICKY_VALUE_MARKUP = "value"; //$NON-NLS-1$

	/** identifier for link reference description */
	public static final String LINK_REFERENCE_MARKUP = "linkId"; //$NON-NLS-1$

	/** identifier for arc id description */
	public static final String ARC_ID_MARKUP = "id"; //$NON-NLS-1$
	/** identifier for arc type description */
	public static final String ARC_TYPE_MARKUP = "arcType"; //$NON-NLS-1$
	/** identifier for arc start element id description */
	public static final String ARC_STARTID_MARKUP = "source"; //$NON-NLS-1$
	/** identifier for arc end element id description */
	public static final String ARC_ENDID_MARKUP = "target"; //$NON-NLS-1$
	/** identifier for arc curved status description */
	public static final String ARC_CURVED_MARKUP = "curved"; //$NON-NLS-1$
	/** identifier for arc color description */
	public static final String ARC_COLOR_MARKUP = "color"; //$NON-NLS-1$

	/** identifier for inflex point x position description */
	public static final String PI_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for inflex point y position description */
	public static final String PI_Y_MARKUP = "yposition"; //$NON-NLS-1$

	/**
	 * Private constructor
	 */
	private ModelConstants() { }

}
