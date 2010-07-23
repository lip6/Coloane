package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

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
	 * Type
	 * @deprecated
	 */
	private Integer type;

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
		this.type = 0;
	}


	/**
	 * Add a special information the existing list
	 * @param tip The special information to add to the model
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
	 * Add a new tip in the subResult.<br>
	 * This is a convenience way to add a tip without explicitly create it.
	 * @param object the model element in which the tip will be linked.
	 * @param name the tip name.
	 * @param value the tip value.
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
	 * Add a textual result in the list.
	 * @param result the textual result to add to the list.<br>
	 * It's stored in an array for being displayed in the columns of the view.
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
	 * Add an attribute (associated with a model object) to the list of attributes to be highlighted.
	 * @param objectId The object ID to whom belongs the attribute.
	 * @param attributeName The name of the attribute to be highlighted.
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
	 * Add an attribute (associated with a model object) to the list of attributes to be highlighted.<br>
	 * This method uses directly an IElement instead of ElementID.
	 * @param object The object to whom belongs the attribute.
	 * @param attributeName The name of the attribute to be highlighted.
	 */
	public final void addAttributeOutline(IElement object, String attributeName) {
		if (object != null) {
			addAttributeOutline(object.getId(), attributeName);
		}
	}
	
	/**
	 * Add an object which to the list of potential highlighted but non-displayed element.
	 * @param objectId The object ID to be added to the list.
	 */
	public final void addObjectDesignation(Integer objectId) {
		this.objectsDesignation.add(objectId);
	}
	
	/**
	 * Add an object which to the list of potential highlighted but non-displayed element.
	 * @param object The object to be added to the list.
	 */
	public final void addObjectDesignation(IElement object) {
		if (object != null) {
			this.objectsDesignation.add(object.getId());
		}
	}

	/**
	 * Add an object which to the list of potential highlighted and displayed element.
	 * @param objectId The object ID to be added to the list.
	 */
	public final void addObjectOutline(Integer objectId) {
		this.objectsOutline.add(objectId);
	}

	/**
	 * Add an object which to the list of potential highlighted and displayed element.
	 * @param object The object to be added to the list.
	 */
	public final void addObjectOutline(IElement object) {
		if (object != null) {
			this.objectsOutline.add(object.getId());
		}
	}

	/**
	 * Add a sub-result in the sub-result list.
	 * @param subResult The sub-result added to the list.
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
	public final List<ISubResult> getChildren() {
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
	public final String getName() {
		return this.subResultName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getInformation() {
		return information;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return this.type;
	}

	/**
	 * Set the sub-result type.
	 * @param type the desired type.
	 * @deprecated unused.
	 */
	public final void setType(Integer type) {
		this.type = type;
	}
}
