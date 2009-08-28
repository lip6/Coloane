package fr.lip6.move.coloane.tools.stats.results;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allow a tool to send a result which will be displayed in an Eclipse view.<br><br>
 * The result has an tree structure : it can contain {@link SubResult} which will also be able to contain others SubResult.<br><br>
 * 
 * Permet à un outil de renvoyer un résultat qui sera affiché dans une vue d'Eclipse<br>
 * Le résultat a une structure arborescente : il peut contenir des {@link SubResult} qui pourront à leur tour contenir d'autres sous-résultats.
 * 
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public class Result implements IResult {
	/** Result name */
	private String resultName;

	/** Sub-results included in this result. */
	private List<ISubResult> subResults;

	/** Tips list */
	private Map<Integer, List<ITip>> tips;

	/** Liste des commandes de modifications du modele */
	private List<ICommand> commandsList;

	/** La graphe résultat */
	private IGraph outputGraph;
	private IGraph computedGraph;

	/** La liste des commandes pour construire le graphe résultat */
	private List<ICommand> deltaCommands;

	/** List of results in the form of text. */
	private List<List<String>> textualResults;

	/**
	 * Constructor of an empty result.
	 * @param resultName the name of the result (Name of the called tool is preferred).
	 * @param outputGraph Le modèle résultat envoyé par le core... à remplir.
	 */
	public Result(String resultName, IGraph outputGraph) {
		this.resultName = resultName;
		this.outputGraph = outputGraph;
		this.computedGraph = null;
		this.subResults = new ArrayList<ISubResult>();
		this.tips = new HashMap<Integer, List<ITip>>();
		this.commandsList = new ArrayList<ICommand>();
		this.deltaCommands = new ArrayList<ICommand>();
		this.textualResults = new ArrayList<List<String>>();
	}

	/**
	 * Constructor of an empty result.
	 * @param resultName the name of the result (Name of the called tool is preferred).
	 * @param serviceName service which provide results (now unused, replaced by the resultName argument).
	 * @param outputGraph Le modèle résultat envoyé par le core... à remplir
	 * @deprecated use {@link Result#Result(String, IGraph)} instead.
	 */
	public Result(String resultName, String serviceName, IGraph outputGraph) {
		this(resultName, outputGraph);
	}

	/**
	 * Add a sub-result in the sub-result list.
	 * Ajoute un sous-resultat à la liste des sous-résultats.
	 * 
	 * @param subResult The sub-result added to the list.
	 */
	public final void addSubResult(ISubResult subResult) {
		this.subResults.add(subResult);
	}

	/**
	 * Ajoute une information à la liste des informations renvoyées par la plate-forme
	 * @param tip L'information à ajouter à la liste
	 */
	public final void addTip(ITip tip) {
		if (this.tips.containsKey(tip.getIdObject())) {
			(this.tips.get(tip.getIdObject())).add(tip);
		} else {
			List<ITip> list = new ArrayList<ITip>();
			list.add(tip);
			this.tips.put(tip.getIdObject(), list);
		}
	}

	/**
	 * Add a tip to the tip list.
	 * @param object the tip will belong to this object.
	 * @param name the tip name.
	 * @param value the tip value.
	 */
	public final void addTip(IElement object, String name, String value) {
		if (object != null) {
			this.addTip(new Tip(object.getId(), name, value));
		}
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
		this.deltaCommands.addAll(commands);
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getResultName() {
		return this.resultName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getRootName() {
		return this.resultName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return this.resultName;
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
	public final Map<Integer, List<ITip>> getTips() {
		return this.tips;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ICommand> getModificationsOnCurrentGraph() {
		return this.commandsList;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<List<String>> getTextualResults() {
		return textualResults;
	}
	
	/**
	 * Add a result in the form of text in the list.
	 * Ajoute un résultat sous forme de texte dans la liste.
	 * 
	 * @param result Le résultat textuel qui doit être ajouté dans la liste.<br>
	 * Celui-ci est stocké sous forme de tableau pour être affiché dans les colonnes de la vue.
	 * @param result the textual result to be added to the list.<br>
	 * This one is stored in an array for being displayed in the columns of the view.
	 */
	public final void addTextualResult(String... result) {
		// emptyList permet de savoir si le tableau construit est constitué uniquement de chaînes vides
		// emptyList allow us to know is the constructed array is only constituted by empty string
		boolean emptyList = true;
		List<String> array = new ArrayList<String>(result.length);
		for (int i = 0; i < result.length; i++) {
			array.add(result[i]);
			emptyList = emptyList && ("".equals(result[i])); //$NON-NLS-1$
		}

		if (emptyList) {
			// Si toutes chaînes sont vides, on renvoie un tableau vide avec "No Result" pour l'indiquer à l'utilisateur
			// If all strings are empty, we return instead an array with "No result" inside to indicate to the user
			array.clear();
			array.add("No result"); //$NON-NLS-1$
			this.textualResults.add(array);
		} else {
			this.textualResults.add(array);
		}
	}
	
	/**
	 * Construit le modèle résultat à partir des commandes transmises par la plate-forme
	 * @return Le modèle résultat construit
	 * TODO: A ameliorer
	 */
	private IGraph buildOutputGraph() {
		// Si il n'y a aucune commande pour contrsuire le graphe resultat...
		if (this.deltaCommands.size() == 0) { return null; }
		// Sinon on le construit
		try {
			for (ICommand command : this.deltaCommands) {
				if (command == null) { continue; }
				command.execute(this.outputGraph);
			}
		} catch (ModelException me) {
			// TODO
			System.err.println("Aie..."); //$NON-NLS-1$
		}
		return this.outputGraph;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IGraph getNewGraph() {
		if (this.deltaCommands.size() <= 0) {
			return null;
		} else {
			if (computedGraph == null) { computedGraph = buildOutputGraph(); }
			return computedGraph;
		}
	}
}
