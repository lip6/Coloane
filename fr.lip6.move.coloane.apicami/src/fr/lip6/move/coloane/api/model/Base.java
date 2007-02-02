package fr.lip6.move.coloane.api.model;

public class Base {
	
	 /** Identifiant unique de l'arc */
    private static int uniqueId = 2;
    
    public int getMaxId() {
    	return uniqueId;
    }
    
    public int attributeId () {
    	return uniqueId++;
    }
    
    public void setMaxId(int id) {
    	 uniqueId = id+1;
    }

}
