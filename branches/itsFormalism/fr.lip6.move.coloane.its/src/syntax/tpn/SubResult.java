package syntax.tpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

public class SubResult implements ISubResult {

	private List<String> textualResults=new ArrayList<String>();
	private Map<Integer, List<String>> attributesOutline = new HashMap<Integer, List<String>>();
	private List<ISubResult> children = new ArrayList<ISubResult>();
	private String name="";
	private List<Integer> objectsDesignation= new ArrayList<Integer>();
	private List<Integer> objectsOutline= new ArrayList<Integer>();
	private int type;

	@Override
	public Map<Integer, List<String>> getAttributesOutline() {
		return attributesOutline ;
	}

	@Override
	public List<ISubResult> getChildren() {
		return children ;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Integer> getObjectsDesignation() {
		return objectsDesignation;
	}

	@Override
	public List<Integer> getObjectsOutline() {
		return objectsOutline;
	}

	@Override
	public List<String> getTextualResults() {
		return textualResults;
	}

	@Override
	public int getType() {
		return type;
	}

	public void addAttributeOutline(int id, String string) {
		if (! attributesOutline.containsKey(id)) {
			attributesOutline.put(id, new ArrayList<String>());
		}
		attributesOutline.get(id).add(string);
	}

	public void addObjectOutline(int id) {
		objectsOutline.add(id);
	}

	public void addTextualResults(String string) {
		textualResults.add(string);
	}

}
