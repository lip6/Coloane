package fr.lip6.move.coloane.communications.objects;

import java.util.Vector;

public class Result {
	private Vector<String> description;
	private Vector<String> elements;

	
	public Result() {
		this.description = new Vector<String>();
		this.elements = new Vector<String>();
	}
	
	public void addElement(String element) {
		this.elements.add(element);
	}

	public void addDescription(String description) {
		this.description.add(description);
	}

	public Vector<String> getListOfElement() {
		return this.elements;
	}

	public Vector<String> getListOfDescription() {
		return this.description;
	}
	
	public Vector<String> getSublistOfDescription(int start) {
		Vector<String> tmp = new Vector<String>();
		
		for (int i=start; i < this.description.size(); i++) {
			tmp.add(this.description.elementAt(i));
		}
		
		return tmp;
	}
	
	public String getHeadDescription() {
		return this.description.elementAt(0);
	}
}
