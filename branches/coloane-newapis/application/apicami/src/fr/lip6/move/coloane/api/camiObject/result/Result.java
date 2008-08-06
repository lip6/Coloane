package fr.lip6.move.coloane.api.camiObject.result;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.List;

/**
 * Description d'un résultat d'invocation de service.<br>
 * En plus du rappel des informations de base sur le service, une liste de sous-résultats est fournie
 *
 * @author Jean-Baptiste Voron
 */
public class Result implements IResult {
	/** Le nom du menu racine qui contient le service invoqué */
	private String rootName;

	/** Le nom du service qui a été invoqué */
	private String serviceName;

	/** La liste des sous-résultats */
	private List<ISubResult> subResults;

	/**
	 * Constructeur
	 * @param rootName Le nom du menu racine qui contient le service qui a été invoqué
	 * @param serviceName Le service qui fournit les résultats
	 */
	public Result(String rootName, String serviceName) {
		this.rootName = rootName;
		this.serviceName = serviceName;
		this.subResults = new ArrayList<ISubResult>();
	}

	/**
	 * Ajoute un sous-resultat à la liste des sous-résultats
	 * @param subResult Le sous-résultat qui doit être attaché à la liste
	 */
	public final void addSubResult(ISubResult subResult) {
		this.subResults.add(subResult);
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getRootName() {
		return this.rootName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return this.serviceName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getSubResults() {
		return this.subResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IGraph getCurrentGraph() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IGraph getNewGraph() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ITip> getTipsList() {
		return null;
	}
}
