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
 */
public interface IModelHandler {

	/** identifier for nodes description */
	String NODES_LIST_MARKUP = "nodes"; //$NON-NLS-1$
	/** identifier for arcs description */
	String ARCS_LIST_MARKUP = "arcs"; //$NON-NLS-1$
	/** identifier for nodes description */
	String STICKYS_LIST_MARKUP = "stickys"; //$NON-NLS-1$
	/** identifier for attribute name description */
	String ATTRIBUTES_LIST_MARKUP = "attributes"; //$NON-NLS-1$

	/** identifier for attribute name description */
	String MODEL_FORMALISM_MARKUP = "formalism"; //$NON-NLS-1$

	/** identifier for model description */
	String MODEL_MARKUP = "model"; //$NON-NLS-1$
	/** identifier for attribute description */
	String ATTRIBUTE_MARKUP = "attribute"; //$NON-NLS-1$
	/** identifier for node description */
	String NODE_MARKUP = "node"; //$NON-NLS-1$
	/** identifier for arc description */
	String ARC_MARKUP = "arc"; //$NON-NLS-1$
	/** identifier for inflex point (bendpoint) description */
	String PI_MARKUP = "pi"; //$NON-NLS-1$
	/** identifier for link description */
	String LINK_MARKUP = "link"; //$NON-NLS-1$
	/** identifier for sticky note description */
	String STICKY_MARKUP = "sticky"; //$NON-NLS-1$

	/** identifier for attribute name description */
	String ATTRIBUTE_NAME_MARKUP = "name"; //$NON-NLS-1$
	/** identifier for attribute value description */
	String ATTRIBUTE_VALUE_MARKUP = "value"; //$NON-NLS-1$
	/** identifier for attribute x position description */
	String ATTRIBUTE_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for attribute y position description */
	String ATTRIBUTE_Y_MARKUP = "yposition"; //$NON-NLS-1$

	/** identifier for node id description */
	String NODE_ID_MARKUP = "id"; //$NON-NLS-1$
	/** identifier for node type description */
	String NODE_TYPE_MARKUP = "nodetype"; //$NON-NLS-1$
	/** identifier for node x position description */
	String NODE_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for node y position description */
	String NODE_Y_MARKUP = "yposition"; //$NON-NLS-1$
	/** identifier for node scale description */
	String NODE_SCALE_MARKUP = "scale"; //$NON-NLS-1$
	/** identifier for node alternate figures description */
	String NODE_ALTERNATE_MARKUP = "alt"; //$NON-NLS-1$
	/** identifier for node foreground color description */
	String NODE_FOREGROUND_MARKUP = "foreground"; //$NON-NLS-1$
	/** identifier for node background color description */
	String NODE_BACKGROUND_MARKUP = "background"; //$NON-NLS-1$
	/** identifier for node interface description */
	String NODE_INTERFACE_MARKUP = "interface"; //$NON-NLS-1$
	/** identifier for node link description */
	String NODE_LINK_MARKUP = "link"; //$NON-NLS-1$

	/** identifier for sticky x position description */
	String STICKY_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for sticky y position description */
	String STICKY_Y_MARKUP = "yposition"; //$NON-NLS-1$
	/** identifier for sticky width description */
	String STICKY_WIDTH_MARKUP = "width"; //$NON-NLS-1$
	/** identifier for sticky height description */
	String STICKY_HEIGHT_MARKUP = "height"; //$NON-NLS-1$
	/** identifier for sticky height description */
	String STICKY_VALUE_MARKUP = "value"; //$NON-NLS-1$

	/** identifier for link reference description */
	String LINK_REFERENCE_MARKUP = "linkId"; //$NON-NLS-1$

	/** identifier for arc id description */
	String ARC_ID_MARKUP = "id"; //$NON-NLS-1$
	/** identifier for arc type description */
	String ARC_TYPE_MARKUP = "arctype"; //$NON-NLS-1$
	/** identifier for arc start element id description */
	String ARC_STARTID_MARKUP = "startid"; //$NON-NLS-1$
	/** identifier for arc end element id description */
	String ARC_ENDID_MARKUP = "endid"; //$NON-NLS-1$
	/** identifier for arc curved status description */
	String ARC_CURVED_MARKUP = "curved"; //$NON-NLS-1$
	/** identifier for arc color description */
	String ARC_COLOR_MARKUP = "color"; //$NON-NLS-1$

	/** identifier for inflex point x position description */
	String PI_X_MARKUP = "xposition"; //$NON-NLS-1$
	/** identifier for inflex point y position description */
	String PI_Y_MARKUP = "yposition"; //$NON-NLS-1$
}
