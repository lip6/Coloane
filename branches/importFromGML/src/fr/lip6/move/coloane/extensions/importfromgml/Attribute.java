package fr.lip6.move.coloane.extensions.importfromgml;

public class Attribute {
	private String type;
	private String value;
	
	public Attribute(String type, String value){
		this.type = type;
		this.value = value;
	}
	
	public String getType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		this.value = value;
	}
}
