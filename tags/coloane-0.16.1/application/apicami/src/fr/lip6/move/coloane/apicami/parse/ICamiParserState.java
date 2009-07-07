package fr.lip6.move.coloane.apicami.parse;

/**
 * Définition des états du parser<br>
 * Ces états sont utilisés à des fins heuristiques.
 *
 * @author Jean-Baptiste
 */
public interface ICamiParserState {

	/** Etat par defaut. Aucune optimisation possible */
	int DEFAULT_STATE = 0;

	/** Etat de reception des résultat */
	int RESULT_STATE = 1;

	/** Etat par defaut. Aucune optimisation possible */
	int OPENSESSION_STATE = 2;
}
