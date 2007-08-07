package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

/**
 * Interface fournie par l'interface a Coloane 
 * pour la transmissions de resultats.
 */
public interface IResultsCom {

	/**
	 * Retourne la liste des elements de resultats
	 * @return La liste des elements de resultats
	 */
	public Vector<String> getListOfElement();
	
	/**
	 * Retourne la liste des descriptions de resultats
	 * @return La liste des descriptions de resutlats
	 */
	public Vector<String> getListOfDescription();
	
	/**
	 * Ajout d'un element de resultats
	 * @param element Une chaine de caractere indiquant le resultat
	 */
	public void addElement(String element);
	
	/**
	 * Ajout d'une description d'un resultat
	 * @param description Une chaine de caractere decrivant un resultat
	 */
	public void addDescription(String description);
	
	/**
	 * Retourne une sous-liste des resultats
	 * @param start Indice de depart des resultats desires
	 * @return La sous liste des resutlats depuis l'indice de debut jusqu'a la fin
	 */
	public Vector<String> getSublistOfDescription(int start);
	
	/**
	 * Retourne la premiere description
	 * @return La chaine de caractere correspondant a la premiere description
	 */
	public String getHeadDescription();

}
