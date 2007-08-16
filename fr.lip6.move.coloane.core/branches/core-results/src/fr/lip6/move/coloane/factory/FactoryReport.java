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

	public FactoryReport(IResultsCom resultCom) {
		this.resultCom = resultCom;
	}

	public IReport createReport() {
		if (resultCom.getCmdRQ().equals("Petri net syntax checker")
				|| resultCom.getCmdRQ().equals("Compute structural bounds")
				|| resultCom.getCmdRQ().equals("Is the net structuraly safe?")
				|| resultCom.getCmdRQ().equals("Is the net structurally bounded?")
				|| resultCom.getCmdRQ().equals("P-invariants")
				|| resultCom.getCmdRQ().equals("P-positive invariants")
				|| resultCom.getCmdRQ().equals("T-positive invariants")){
			return new GenericServicesReport(resultCom);
		}
		return null;
		 //return new DefaultReport();
	}
}
