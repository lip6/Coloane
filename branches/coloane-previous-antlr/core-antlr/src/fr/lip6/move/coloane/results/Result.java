package fr.lip6.move.coloane.results;

/**
 * Description d'un resultat atomique.<br>
 * Lors de l'affichage, le nom du resultat correspond a la deuxieme colonne de la vue Results<br>
 * La description du resultat correspond a la troisieme colonne de la vue Results.
 */
public class Result {

	/** Le nom du resultat */
	private String name;

	/** La description du resultat */
	private String description;

	/**
	 * Resultat atomique
	 * @param resultName Nom du resultat (objet ou groupe d'objet)
	 * @param resultDesc Description associee
	 */
	public Result(String resultName, String resultDesc) {
		this.name = resultName;
		this.description = resultDesc;
	}

	/**
	 * Retourne le nom du resultat atomique
	 * @return Le nom du resultat
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Retourne la description du resultat atomique
	 * @return La description du resultat
	 */
	public final String getDescription() {
		return description;
	}
}
