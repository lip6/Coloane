package fr.lip6.move.coloane.its.syntax.tpn;

import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An adapter for a syntax error.
 * @author Yann
 *
 */
public final class SubResult implements ISubResult {

	private List<String> textualResults = new ArrayList<String>();
	private Map<Integer, List<String>> attributesOutline = new HashMap<Integer, List<String>>();
	private List<ISubResult> children = new ArrayList<ISubResult>();
	private String name = "";
	private List<Integer> objectsDesignation = new ArrayList<Integer>();
	private List<Integer> objectsOutline = new ArrayList<Integer>();
	private int type;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Integer, List<String>> getAttributesOutline() {
		return attributesOutline;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ISubResult> getChildren() {
		return children;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Integer> getObjectsDesignation() {
		return objectsDesignation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Integer> getObjectsOutline() {
		return objectsOutline;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getTextualResults() {
		return textualResults;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getType() {
		return type;
	}

	/**
	 * add an object attribute to the list of target objects
	 * @param id id of the owning element
	 * @param string attribute that is targeted
	 */
	public void addAttributeOutline(int id, String string) {
		if (!attributesOutline.containsKey(id)) {
			attributesOutline.put(id, new ArrayList<String>());
		}
		attributesOutline.get(id).add(string);
	}

	/**
	 * add an object to the list of target objects
	 * @param id id of the owning element
	 */
	public void addObjectOutline(int id) {
		objectsOutline.add(id);
	}

	/**
	 * Add a textual result.
	 * @param string the text to add
	 */
	public void addTextualResults(String string) {
		textualResults.add(string);
	}

}
