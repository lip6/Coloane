package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.results.Result;

public class StructuralBounds extends Report {

	public StructuralBounds(IResultsCom results) {
		super(Messages.StructuralBounds_0, results);
	}

	@Override
	protected final void buildReport() {
		// Parcours de tous les DE-FE
		for (SubResultsCom sr : getResultsCom().getSubResults()) {
			// Parcours de tous les objets mis en valeur
			for (String object : sr.getCmdRO()) {
				getResultList().add(new Result(object, sr.getCmdRT().get(0)));
			}
		}
	}
}
