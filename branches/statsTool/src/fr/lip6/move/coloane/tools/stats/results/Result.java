package fr.lip6.move.coloane.tools.stats.results;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
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

	/** Liste des informations */
	private List<ITip> tipsList;

	/** Liste des commandes de modifications du modele */
	private List<ICommand> commandsList;

	/** La graphe résultat */
	private IGraph outputGraph;
	private IGraph computedGraph;

	/** La liste des commandes pour construire le graphe résultat */
	private List<ICommand> outputCommandsList;

	/**
	 * Constructeur
	 * @param rootName Le nom du menu racine qui contient le service qui a été invoqué
	 * @param serviceName Le service qui fournit les résultats
	 * @param outputGraph Le modèle résultat envoyé par le core... à remplir
	 */
	public Result(String rootName, String serviceName, IGraph outputGraph) {
		this.rootName = rootName;
		this.serviceName = serviceName;
		this.outputGraph = outputGraph;
		this.computedGraph = null;
		this.subResults = new ArrayList<ISubResult>();
		this.tipsList = new ArrayList<ITip>();
		this.commandsList = new ArrayList<ICommand>();
		this.outputCommandsList = new ArrayList<ICommand>();
	}

	/**
	 * Ajoute un sous-resultat à la liste des sous-résultats
	 * @param subResult Le sous-résultat qui doit être attaché à la liste
	 */
	public final void addSubResult(ISubResult subResult) {
		this.subResults.add(subResult);
	}

	/**
	 * Ajoute une information à la liste des informations renvoyées par la plate-forme
	 * @param tip L'information à ajouter à la liste
	 */
	public final void addTip(ITip tip) {
		this.tipsList.add(tip);
	}

	/**
	 * Ajouter une commande de modification du modèle à la liste
	 * @param command La commande qui doit être ajoutée
	 */
	public final void addCommand(ICommand command) {
		this.commandsList.add(command);
	}

	/**
	 * Demande la création d'un nouveau graphe
	 * @param commands La liste de commandes décrivant le nouveau noeud
	 */
	public final void addOutputGraph(List<ICommand> commands) {
		this.outputCommandsList.addAll(commands);
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
	public final List<ITip> getTipsList() {
		return this.tipsList;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ICommand> getModificationsOnCurrentGraph() {
		return this.commandsList;
	}

	/**
	 * Construit le modèle résultat à partir des commandes transmises par la plate-forme
	 * @return Le modèle résultat construit
	 * TODO: A ameliorer
	 */
	private IGraph buildOutputGraph() {
		// Si il n'y a aucune commande pour contrsuire le graphe resultat...
		if (this.outputCommandsList.size() == 0) { return null; }
		// Sinon on le construit
		try {
			for (ICommand command : this.outputCommandsList) {
				if (command == null) { continue; }
				command.execute(this.outputGraph);
			}
		} catch (ModelException me) {
			// TODO
			System.err.println("Aie...");
		}
		return this.outputGraph;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IGraph getNewGraph() {
		if (this.outputCommandsList.size() <= 0) {
			return null;
		} else {
			if (computedGraph == null) { computedGraph = buildOutputGraph(); }
			return computedGraph;
		}
	}
}
