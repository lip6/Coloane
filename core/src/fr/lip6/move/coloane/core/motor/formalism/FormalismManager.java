package fr.lip6.move.coloane.core.motor.formalism;

import fr.lip6.move.coloane.core.motor.formalism.defs.PetriNets;
import fr.lip6.move.coloane.core.motor.formalism.defs.PrefixNets;
import fr.lip6.move.coloane.core.motor.formalism.defs.ReachabilityGraph;

import java.util.ArrayList;

/**
 * Classe du gestionnaire de formalismes.
 * C'est ici que sont definis les formalismes.
 */
public class FormalismManager {

	/** Liste des formalismes disponibles. */
	private static ArrayList<Formalism> listOfFormalisms;

	/**
	 * Constructeur de la classe FormalismsManager
	 * Constitution de la liste des formalismes
	 */
	public FormalismManager() {
		listOfFormalisms = new ArrayList<Formalism>();
		listOfFormalisms.add(new PetriNets());
		listOfFormalisms.add(new PrefixNets());
		listOfFormalisms.add(new ReachabilityGraph());
	}
	
	/**
	 * Cette methode retourne un formalisme a partir de son nom
	 * @param name Le nom du formalism qu'on cherche
	 * @return Formalism
	 */
	public final Formalism getFormalismByName(String name) {
		for (Formalism form : listOfFormalisms) {
			if (name.toLowerCase().equals(form.getName().toLowerCase())) {
				return form;
			}
		}
		return null;
	}

	/**
	 * Retourne la liste des formalismes disponibles
	 * @return listOfFormalism
	 */
	public final ArrayList<Formalism> getListOfFormalisms() {
		return listOfFormalisms;
	}

	
	
}
