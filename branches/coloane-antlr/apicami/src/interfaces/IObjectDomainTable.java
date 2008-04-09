package interfaces;

import java.util.ArrayList;



/**
 * cette interface decrit la table des domaine.
 * @author KAHOO & UU
 *
 */
public interface IObjectDomainTable {

	 /**
	    * Retourne la classe de l'objet.
	    * 1 : Noeud, 2 : Connecteur
	    * @return String
	    */
	String getObjectClass();
	
	 /**
	    * Retourne l'identifiant de la table.
	    * @return int
	    */
	int getId();
	
	 /**
	    * Retourne le type de la table.
	    * @return String
	    */
	String getType();
	
	 /**
	    * Retourne la liste des attributs de notre table de domaine.
	    * @return tableau des attributs(IObjectAttribute).
	    */
   ArrayList<IObjectAttribute> getDomainObjectAttributes();
}
