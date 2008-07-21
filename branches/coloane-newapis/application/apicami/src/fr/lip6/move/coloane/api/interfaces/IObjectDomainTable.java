package fr.lip6.move.coloane.api.interfaces;

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
	public String getObjectClass();

	 /**
	    * Retourne l'identifiant de la table.
	    * @return int
	    */
	public int getId();

	 /**
	    * Retourne le type de la table.
	    * @return String
	    */
	public String getType();

	 /**
	    * Retourne la liste des attributs de notre table de domaine.
	    * @return tableau des attributs(IObjectAttribute).
	    */
	public ArrayList<IObjectAttribute> getDomainObjectAttributes();
}
