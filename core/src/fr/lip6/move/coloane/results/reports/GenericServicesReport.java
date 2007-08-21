/**
 * Cette Classe permet de definir un report
 * pour le Syntax Checker
 * 
 */
package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.results.ResultsList;

/**
 * @author cdcharles
 *
 */
public class GenericServicesReport implements IReport {

	/**Liste des resultats envoyes par FK*/
	private IResultsCom resultCom;

	/**Le nom du service*/
	private final String label = "Petri Net Syntax Checker";

	/**La liste des resultats*/
	private ResultsList resultList;

	/**
	 *Le constructeur
	 *@param resultCom la liste de resultat envoyes par FK
	 */
	public GenericServicesReport(IResultsCom result) {
		this.resultCom = result;
	}

	/**Cette methode renvoie la liste des resultats recuperes par FK et les transmet a Coloane
	 * @return ResultsList la liste des resultats
	 */
	public final ResultsList getResultList() {
		return new ResultsList(resultCom.getQuestion());
	}

	public final String getLabel() {
		return label;
	}
}
