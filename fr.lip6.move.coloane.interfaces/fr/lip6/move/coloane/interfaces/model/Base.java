package fr.lip6.move.coloane.interfaces.model;

/**
 * Cette classe est utilise pour gerer les identifiants des composants du modele
 * @author Croc
 *
 */

public class Base {
	
	 /** Identifiant unique de depart */
    private static int uniqueId = 1;
    
    /**
     * Renvoie un nouvelle identifiant unique
     * @return un identifiant unique dans le modele
     */
    protected int getUniqueId() {
    	uniqueId++;
    	return uniqueId;
    }
    
    /**
     * Met a jour le compteur d'identifiant unique.
     * @param id L'identifiant unique qui a etre insere dnas le modele
     * @return un idnetifiant unique egal ou different de l'id propose en fonction de son unicite
     */
    protected int setUniqueId(int id) {
    	if (id > uniqueId) {
    		uniqueId = id;
    	} else {
    		uniqueId++;
    	}
		return uniqueId;
    }
}
