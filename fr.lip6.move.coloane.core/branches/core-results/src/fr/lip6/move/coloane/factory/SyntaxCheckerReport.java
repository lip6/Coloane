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
	
	/**Le nom du service*/
	private final String label = "Petri Net Syntax Checker";
	
	/**La liste des resultats*/
	private ResultsList resultList;
	
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
		resultList = new ResultsList(resultCom.getCmdRQ());
		int i=0;
		String descr = "";
		Vector<SousResultsCom> sousR = resultCom.getSous_resultats();
		
		while(i < sousR.size()){
			descr = sousR.get(i).getCmdRT().get(0);
			Vector<String> cmdRO = sousR.get(i).getCmdRO();
			if(cmdRO.size() == 0){
				resultList.add(new Result("", descr));
			} else {
				for(int j = 0; j< cmdRO.size();j++){
					resultList.add(new Result(cmdRO.get(j),descr));
				}
			}
			i++;
		}
		
		return resultList;
	}

	public String getLabel() {
		return label;
	}
}
