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
package fr.lip6.move.coloane.interfaces.objects.result;

import fr.lip6.move.coloane.interfaces.model.IElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class define a sub-result.<br>
 * A sub-result is always contained in a result or a sub-result.<br>
 * Other sub-results can be added to ensure a tree structure.<br>
 * 
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public class SubResult implements ISubResult {
	/** Sub-result name. */
	private String subResultName;

	/** Information about the sub-result. */
	private String information;

	/** Sub-results attached to this sub-result. */
	private List<ISubResult> subResults;

	/** Tips list */
	private Map<Integer, List<ITip>> tips;

	/** 
	 * List of objects that will be highlighted in the model if the subresult checkbox is checked.<br>
	 * Note: These objects will <b>not</b> be displayed in the result tree.
	 */
	private List<Integer> objectsDesignation;

	/** 
	 * List of objects that will be highlighted in the model if the sub-result checkbox is checked or if their checkbox is checked.<br>
	 * Note: These objects will be displayed in the result tree.
	 */
	private List<Integer> objectsOutline;

	/** Attributes that could be highlighted in the model */
	private Map<Integer, List<String>> attributesOutline;

	/** List of results in the form of text. */
	private List<List<String>> textualResults;

	/**
	 * Constructs an empty sub-result.
	 * @param subResultName Name of the sub-result.
	 */
	public SubResult(String subResultName) {
		this(subResultName, ""); //$NON-NLS-1$
	}

	/**
	 * Constructs an empty sub-result with an information about it.
	 * @param subResultName Name of the sub-result.
	 * @param information Information about the sub-result.
	 */
	public SubResult(String subResultName, String information) {
		this.subResultName = subResultName;
		this.information = information;
		this.subResults = new ArrayList<ISubResult>();
		this.objectsDesignation = new ArrayList<Integer>();
		this.objectsOutline = new ArrayList<Integer>();
		this.attributesOutline = new HashMap<Integer, List<String>>();
		this.textualResults = new ArrayList<List<String>>();
		this.tips = new HashMap<Integer, List<ITip>>();
	}


	/**
	 * {@inheritDoc}
	 */
	public final void addTip(ITip tip) {
		// Several tips can be stored for the same object
		if (this.tips.containsKey(tip.getIdObject())) {
			(this.tips.get(tip.getIdObject())).add(tip);
		// First tip for this object
		} else {
			List<ITip> list = new ArrayList<ITip>();
			list.add(tip);
			this.tips.put(tip.getIdObject(), list);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addTip(IElement object, String name, String value) {
		if (object != null) {
			this.addTip(new Tip(object.getId(), name, value));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final Map<Integer, List<ITip>> getTips() {
		return this.tips;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addTextualResult(String... result) {
		// boolean variable emptyList tells us whether the constructed array is constituted of empty strings
		boolean emptyList = true;
		List<String> array = new ArrayList<String>(result.length);
		for (int i = 0; i < result.length; i++) {
			array.add(result[i]);
			emptyList = emptyList && ("".equals(result[i])); //$NON-NLS-1$
		}
		this.textualResults.add(array);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void addAttributeOutline(Integer objectId, String attributeName) {
		if (this.attributesOutline.containsKey(objectId)) {
			this.attributesOutline.get(objectId).add(attributeName);
		} else {
			List<String> first = new ArrayList<String>();
			first.add(attributeName);
			this.attributesOutline.put(objectId, first);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addAttributeOutline(IElement object, String attributeName) {
		if (object != null) {
			addAttributeOutline(object.getId(), attributeName);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void addObjectDesignation(Integer objectId) {
		this.objectsDesignation.add(objectId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final void addObjectDesignation(IElement object) {
		if (object != null) {
			this.objectsDesignation.add(object.getId());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObjectOutline(Integer objectId) {
		this.objectsOutline.add(objectId);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addObjectOutline(IElement object) {
		if (object != null) {
			this.objectsOutline.add(object.getId());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addSubResult(ISubResult subResult) {
		this.subResults.add(subResult);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getSubResults() {
		return this.subResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<Integer> getObjectsDesignation() {
		return objectsDesignation;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<Integer> getObjectsOutline() {
		return objectsOutline;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<List<String>> getTextualResults() {
		return textualResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Map<Integer, List<String>> getAttributesOutline() {
		return attributesOutline;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getSubResultName() {
		return this.subResultName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getInformation() {
		return information;
	}
}
