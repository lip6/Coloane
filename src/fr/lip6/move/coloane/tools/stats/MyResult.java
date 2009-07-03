package fr.lip6.move.coloane.tools.stats;

public class MyResult {
	/** Nom du sous-résultat */
	private String name;

	/** Type du sous-résultat */
	private int type;

	public MyResult(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	void myToString() {
		System.out.println(this.name);
	}

}
