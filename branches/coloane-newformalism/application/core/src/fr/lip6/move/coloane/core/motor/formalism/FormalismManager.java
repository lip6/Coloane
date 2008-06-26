package fr.lip6.move.coloane.core.motor.formalism;

import fr.lip6.move.coloane.core.motor.formalism.defs.BinaryTree;
import fr.lip6.move.coloane.core.motor.formalism.defs.Geograph;
import fr.lip6.move.coloane.core.motor.formalism.defs.PetriNets;
import fr.lip6.move.coloane.core.motor.formalism.defs.PrefixNets;
import fr.lip6.move.coloane.core.motor.formalism.defs.ReachabilityGraph;

import java.util.ArrayList;

/**
 * Classe du gestionnaire de formalismes.
 * C'est ici que sont definis les formalismes.
 */
public final class FormalismManager {

	/** Liste des formalismes disponibles. */
	private ArrayList<Formalism> listOfFormalisms;

	/** L'instance du singleton : FormalismManager */
	private static FormalismManager instance = null;

	/**
	 * Constructeur de la classe FormalismsManager
	 * Constitution de la liste des formalismes
	 * Pour eviter les doublonson utilise le pattern <b>Singleton</b>
	 * @see #getInstance()
	 */
	private FormalismManager() {
		listOfFormalisms = new ArrayList<Formalism>();
		listOfFormalisms.add(new PetriNets());
		listOfFormalisms.add(new PrefixNets());
		listOfFormalisms.add(new ReachabilityGraph());
		listOfFormalisms.add(new Geograph());
		listOfFormalisms.add(new BinaryTree());
	}

	/**
	 * Retourne le gestionnaire de formalismes
	 * @return FormalismManager Une isntance du gestionnaire de formalismes
	 */
	public static synchronized FormalismManager getInstance() {
		if (instance == null) { instance = new FormalismManager(); }
		return instance;
	}

	/**
	 * Cette methode retourne un formalisme a partir de son nom
	 * @param name Le nom du formalism qu'on cherche
	 * @return Formalism
	 */
	public Formalism getFormalismByName(String name) {
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
	public ArrayList<Formalism> getListOfFormalisms() {
		return listOfFormalisms;
	}
}
