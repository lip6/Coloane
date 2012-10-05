package fr.lip6.move.coloane.extensions.importfromgrml;


/**
 * Manipulate two associated String values
 *
 * @author Elodie Banel
 */
public class Attribute {
	private String key;
	private String value;
	
	public Attribute(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey(){
		return key;
	}
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
}
