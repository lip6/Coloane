package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;


/**
 * cette interface représente une table de domaines.
 * @author KAHOO & UU
 *
 */
public interface IDomainTable {

	/**
	 * Donne les objets contenus dans ma table des domaines.
	 * @return le tableau de ces objets.
	 */
	public ArrayList<IObjectDomainTable>  getObjectsDomainTable();
}
