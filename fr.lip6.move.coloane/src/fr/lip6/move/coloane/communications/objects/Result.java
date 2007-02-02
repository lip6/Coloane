package fr.lip6.move.coloane.communications.objects;

import java.util.Vector;

public class Result {
	private String description;
	private Vector<String> elements;

	
	public Result(String description) {
		this.description = description;
		this.elements = new Vector<String>();
	}
	
	public Result() {
		this.description = null;
		this.elements = new Vector<String>();
	}
	
	public void addElement(String element) {
		this.elements.add(element);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Vector<String> getList() {
		return this.elements;
	}
}
