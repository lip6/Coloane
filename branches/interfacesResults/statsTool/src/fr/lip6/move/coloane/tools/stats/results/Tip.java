package fr.lip6.move.coloane.tools.stats.results;

import fr.lip6.move.coloane.interfaces.objects.result.ITip;

public class Tip implements ITip {
	private int idObject;
	private String name;
	private String value;


	public Tip(int idObject, String name, String value) {
		this.idObject = idObject;
		this.name = name;
		this.value = value;
	}

	public int getIdObject() {
		return idObject;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
