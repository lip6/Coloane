package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.results.Result;

public class LinerCharacterizationReport extends Report {

	public LinerCharacterizationReport(String label, IResultsCom results) {
		super(label, results);
	}

	@Override
	protected final void buildReport() {
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			for (String object : sr.getCmdRO()) {
				if (object.equals("1")) { object = "Model"; }
				getResultList().add(new Result(object, sr.getCmdRT().get(0)));
				return;
			}

			String res = "";
			for (String object : sr.getCmdRT()) {
				res += object + "\n";
			}
			getResultList().add(new Result("", res));
		}
	}

}
