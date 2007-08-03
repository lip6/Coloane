/**
 * Cette Classe permet de definir un report
 * pour le Syntax Checker
 * 
 */
package fr.lip6.move.coloane.factory;

import java.util.Vector;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SousResultsCom;
import fr.lip6.move.coloane.results.Result;
import fr.lip6.move.coloane.results.ResultsList;

/**
 * @author cdcharles
 *
 */
public class SyntaxCheckerReport implements IReport {
	
	/**Liste des resultats envoyes par FK*/
	private IResultsCom resultCom;
	
	private final String label = "Petri Net Syntax Checker";
	
	/**La liste des resultats*/
	private ResultsList result;
	
	/**
	 *Le constructeur
	 *@param resultCom la liste de resultat envoyes par FK
	 */
	public SyntaxCheckerReport(IResultsCom resultCom){
		this.resultCom = resultCom;
	}
	
	/**Cette methode renvoie la liste des resultats recuperes par FK
	 * et les transmet a Coloane
	 * @return ResultsList la liste des resultats
	 */
	public ResultsList getResultList(){
		result = new ResultsList(label);
		String descr = null;
		
		if(resultCom.getSous_resultats().get(0).equals("List of unamed places")){
			descr = "Unamed Place";
		} else if(resultCom.getSous_resultats().get(0).equals("List of unnamed transition")){
			descr = "Unamed Transition";
		}
		
		SousResultsCom sousResult = new SousResultsCom();
		Vector<String> roList = sousResult.getCmdRO();
		
		for(int i=0; i <roList.size(); i++){
			result.add(new Result(roList.get(i),descr));
		}
		return result;
	}
}
