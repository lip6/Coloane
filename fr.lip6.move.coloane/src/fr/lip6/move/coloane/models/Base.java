package fr.lip6.move.coloane.models;

public class Base {
	
	 /** Identifiant unique de l'arc */
    public static int uniqueId = 2;
    
    public int getMaxId() {
    	return uniqueId;
    }
    
    public void setMaxId(int id) {
    	 uniqueId = id+1;
    }

}
