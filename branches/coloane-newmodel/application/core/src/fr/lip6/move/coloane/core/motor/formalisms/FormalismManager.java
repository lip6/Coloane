package fr.lip6.move.coloane.core.motor.formalisms;

import fr.lip6.move.coloane.core.motor.formalisms.definitions.PetriNets;
import fr.lip6.move.coloane.core.motor.formalisms.definitions.ReachabilityGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe du gestionnaire de formalismes.
 * C'est ici que sont definis les formalismes.
 */
public final class FormalismManager {

	/** Liste des formalismes disponibles. */
	private List<Formalism> formalisms;

	/** L'instance du singleton : FormalismManager */
	private static FormalismManager instance = null;

	/**
	 * Constructeur de la classe FormalismsManager
	 * Constitution de la liste des formalismes
	 * Pour eviter les doublonson utilise le pattern <b>singleton</b>
	 * @see {@link #getInstance()}
	 */
	private FormalismManager() {
		formalisms = new ArrayList<Formalism>();
		formalisms.add(new PetriNets());
		formalisms.add(new ReachabilityGraph());
	}

	/**
	 * Retourne le gestionnaire de formalismes
	 * @return FormalismManager Une instance du gestionnaire de formalismes
	 */
	public static synchronized FormalismManager getInstance() {
		if (instance == null) { instance = new FormalismManager(); }
		return instance;
	}

	/**
	 * Cette methode retourne un formalisme à partir de son nom
	 * @param name Le nom du formalism qu'on cherche
	 * @return Le formalisme attendu ou <code>null</code> sinon
	 */
	public Formalism getFormalismByName(String name) {
		for (Formalism form : formalisms) {
			if (name.toLowerCase().equals(form.getName().toLowerCase())) {
				return form;
			}
		}
		return null;
	}

	/**
	 * @return La liste des formalismes disponibles
	 */
	public List<Formalism> getListOfFormalisms() {
		return formalisms;
	}
}
