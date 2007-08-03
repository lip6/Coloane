/**
 * Classe definissant la Factory pour fabriquer les
 * differents reports de chaque service
 * 
 */
package fr.lip6.move.coloane.factory;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

/**
 * @author cdcharles
 *
 */
public class FactoryReport {

	private IResultsCom resultCom;
	
	FactoryReport(IResultsCom resultCom){
		this.resultCom = resultCom;
	}
	
	public IReport createReport(){
		if(resultCom.getCmdRQ().equals("Petri Net Syntax Checker")){
			 return new SyntaxCheckerReport(resultCom);			
		}
		return null;
	}
}
