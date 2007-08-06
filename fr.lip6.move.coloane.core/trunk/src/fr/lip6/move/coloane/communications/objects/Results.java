package fr.lip6.move.coloane.communications.objects;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

import java.util.Vector;

public class Results {
	private Vector<String> description;
	private Vector<String> elements;


	public Results() {
		this.description = new Vector<String>();
		this.elements = new Vector<String>();
	}

	public Results(IResultsCom resultsCom) {
		this.description = resultsCom.getListOfDescription();
		this.elements = resultsCom.getListOfElement();
	}

	public final void addElement(String element) {
		this.elements.add(element);
	}

	public final void addDescription(String desc) {
		this.description.add(desc);
	}

	public final Vector<String> getListOfElement() {
		return this.elements;
	}

	public final Vector<String> getListOfDescription() {
		return this.description;
	}

	public final Vector<String> getSublistOfDescription(int start) {
		Vector<String> tmp = new Vector<String>();

		for (int i = start; i < this.description.size(); i++) {
			tmp.add(this.description.elementAt(i));
		}

		return tmp;
	}

	public final String getHeadDescription() {
		return this.description.elementAt(0);
	}
}
