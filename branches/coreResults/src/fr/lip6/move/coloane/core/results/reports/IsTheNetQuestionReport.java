package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.ResultTreeImpl;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;

/**
 * Générateur de résultat pour le calcul des bornes
 */
public class IsTheNetQuestionReport implements IReport {

	/** {@inheritDoc} */
	public final ResultTreeImpl build(IResult result) {

		// S'il n'y a qu'un sous-resultat... Alors pas d'enfants
		if (result.getSubResults().size() == 1) {
			// La reponse est stockee dans le premier resultat textuel du
			// premier sous-resultat
			String answer = result.getSubResults().get(0).getTextualResults().get(0).get(0);
			ResultTreeImpl root = new ResultTreeImpl(result.getResultName(), answer);
			root.setSessionManager(SessionManager.getInstance());
			return root;
		}

		// Sinon, il faut afficher la reponse plus le detail
		String answer = result.getSubResults().get(0).getTextualResults().get(0).get(0);
		ResultTreeImpl root = new ResultTreeImpl(result.getResultName(), answer);
		root.setSessionManager(SessionManager.getInstance());

		// Sinon on parcours les resultats
		for (List<String> resList : result.getSubResults().get(1).getTextualResults()) {
			String res = resList.get(0);

			// Dans le cas des lignes d'entete ou de presentation
			if (res.indexOf(':') == -1) {
				continue;
			}

			// Pour le details des places et transitions problematiques
			String firstPart = res.substring(0, res.indexOf(':')).trim();
			String secondPart = Messages.IsTheNetQuestionReport_0 + res.substring(res.indexOf(':') + 1).trim();
			ResultTreeImpl node = new ResultTreeImpl(firstPart, secondPart);

			// On ajoute ce noeud au resultat de service
			root.addChild(node);
		}
		return root;
	}
}
