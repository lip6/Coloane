package fr.lip6.move.coloane.apiws.objects.result;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.AttributePositionCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateArcCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateAttributeCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateInflexPointCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateNodeCommand;
import fr.lip6.move.coloane.interfaces.model.command.DeleteInflexPointsCommand;
import fr.lip6.move.coloane.interfaces.model.command.DeleteObjectCommand;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.model.command.ObjectPositionCommand;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.wrapper.ws.WrapperStub.AttributeValue;
import fr.lip6.move.wrapper.ws.WrapperStub.LModificationModel;
import fr.lip6.move.wrapper.ws.WrapperStub.Model;
import fr.lip6.move.wrapper.ws.WrapperStub.ModelModification;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Cette classe définie un résultat pour le core de Coloane.
 */
public class ResultImpl implements IResult {

	private String rootName;

	private String serviceName;

	private List<ISubResult> subResult;

	private List<ITip> tipsList;

	private IGraph newGraph;

	private List<ICommand> modificationsOnCurrentGraph;

	/**
	 * Constructeur
	 * @param result le resultat reçu de la part du wrapper
	 * @param service le service executer
	 * @param newGraph le nouveeau graph
	 */
	public ResultImpl(RService result, IService service, IGraph newGraph) {
		this.rootName = service.getRoot();
		this.serviceName = result.getAnswerToquestion().getName();

		// Création de la liste des sous-resultats
		this.subResult = new ArrayList<ISubResult>();
		if (result.getEnsemble().getEnsembles() != null) {
			for (int i = 0; i < result.getEnsemble().getEnsembles().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (result.getEnsemble().getEnsembles()[i] == null) {
					break;
				}
				this.subResult.add(new SubResultImpl(result.getEnsemble().getEnsembles()[i]));
			}
		}

		// Création de la liste des modification à ignorer
		this.tipsList = new ArrayList<ITip>();
		if (result.getCurrentModel() != null) {
			if (result.getCurrentModel().getModification() != null) {
				if (result.getCurrentModel().getModification().getIgnores() != null) {
					for (int i = 0; i < result.getCurrentModel().getModification().getIgnores().length; i++) {
						// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
						if (result.getCurrentModel().getModification().getIgnores()[i] == null) {
							break;
						}
						this.tipsList.add(new TipImpl(result.getCurrentModel().getModification().getIgnores()[i]));
					}
				}
			}
		}

		// Construit le nouveau graph
		if (result.getNewModels() != null) {
			if (result.getNewModels()[0] != null) {
				this.newGraph = createGraph(result.getNewModels()[0], newGraph);
			}
		}

		// Création de la liste des modifications à apporter sur le graph courrant
		this.modificationsOnCurrentGraph = new ArrayList<ICommand>();
		if (result.getModelModification()) {
			Model theCurrentModel = result.getCurrentModel();
			LModificationModel theModificationModel = theCurrentModel.getModification();
			if (theModificationModel != null) {
				if (theModificationModel.getModifications() != null) {
					for (int i = 0; i < theModificationModel.getModifications().length; i++) {
						// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
						if (theModificationModel.getModifications()[i] == null) {
							break;
						}

						ICommand cmd = createCommand(theModificationModel.getModifications()[i], theCurrentModel);
						if (cmd != null) {
							this.modificationsOnCurrentGraph.add(cmd);
						}
					}
				}
			}
		}

		///////////////////////////// AFFICHAGE
		System.out.println();
		System.out.println("ARBRE DES RESULTATS:");
		for (ISubResult subRes : subResult) {
			printResult(subRes, "");
		}
		System.out.println();
		System.out.println("COMMANDES DE MODIFICATIONS:");
		for (ICommand cmd : modificationsOnCurrentGraph) {
			System.out.println("\t" + cmd.toString());
		}
		System.out.println();
		System.out.println("COMMANDEs IGNIOREES:");
		for (ITip t : tipsList) {
			System.out.println("\t" + "Id:" + t.getIdObject() + " Name:" + t.getName() + " Value:" + t.getValue());
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public final String getRootName() {
		return rootName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return serviceName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getSubResults() {
		return subResult;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ITip> getTipsList() {
		return tipsList;
	}

	/**
	 * {@inheritDoc}
	 */
	public final IGraph getNewGraph() {
		return newGraph;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ICommand> getModificationsOnCurrentGraph() {
		return modificationsOnCurrentGraph;
	}

	/**
	 * Créé une commande pour modifier l'ancien model
	 * @param modification la modification a apporter sur le model
	 * @param model le model courrant modifié
	 * @return la commande pour modifier l'ancien model
	 */
	private ICommand createCommand(ModelModification modification, Model model) {

		switch (modification.getType()) {
			// Suppresion d'un element
			case 0:
				return new DeleteObjectCommand(modification.getId());
			// Ajout d'un element
			case 1 :
				for (int i = 0; i < model.getArcs().length; i++) {
					if (model.getArcs()[i].getId() == modification.getId()) {
						return new CreateArcCommand(
								modification.getId(),
								model.getArcs()[i].getType(),
								model.getArcs()[i].getSource(),
								model.getArcs()[i].getDestination());
					}
				}
				for (int i = 0; i < model.getNodes().length; i++) {
					if (model.getNodes()[i].getId() == modification.getId()) {
						return new CreateNodeCommand(modification.getId(), model.getNodes()[i].getType());
					}
				}
				return null;
			// Modification d'un element
			case 2 :
				return new CreateAttributeCommand(modification.getNameAtt(), modification.getId(), modification.getValue());

			// Modification de la position d'un objet
			case 3 :
				return new ObjectPositionCommand(modification.getId(), modification.getPos().getXx(), modification.getPos().getYy());

			// Modification de la taille d'un objet
			case 4 :
				return null;

			// Modification de la position d'un attribut
			case 5 :
				return new AttributePositionCommand(
						modification.getId(),
						modification.getNameAtt(),
						modification.getPos().getXx(),
						modification.getPos().getYy());

			// Ajout d'un point d'inflexion
			case 6 :
				return new CreateInflexPointCommand(
						modification.getId(),
						modification.getPos().getXx(),
						modification.getPos().getYy());

			// Supprimer tous les points d'inflexions
			case 7 :
				return new DeleteInflexPointsCommand();

			// Sinon
			default : return null;
		}
	}

	/**
	 * Crée un nouveau graph
	 * @param model le model reçu
	 * @param newGraph le graph vide pour crééer le nouveau resultat
	 * @return le nouveau graph
	 */
	private IGraph createGraph(Model model, IGraph newGraph) {
		// La liste des commandes pour construire le nouveau graph
		List<ICommand> commandForNewGraph = new ArrayList<ICommand>();

		if (model.getNodes() != null) {
			// Parcours tous les noeuds
			for (int i = 0; i < model.getNodes().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (model.getNodes()[i] == null) {
					break;
				}

				//Ajout une commande pour créer le noeud
				ICommand addNodeCommand = new CreateNodeCommand(model.getNodes()[i].getId(), model.getNodes()[i].getType());
				commandForNewGraph.add(addNodeCommand);

				// Parcours les attributs du noeud
				for (int j = 0; j < model.getNodes()[i].getAtts().length; j++) {
					// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
					if (model.getNodes()[i].getAtts()[j] == null) {
						break;
					}

					// Ajout une commande pour créer un attribut du noeud
					ICommand addNodeAttCommand = new CreateAttributeCommand(
							model.getNodes()[i].getAtts()[j].getAttName(),
							model.getNodes()[i].getId(),
							myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
					commandForNewGraph.add(addNodeAttCommand);

				}
			}
		}

		if (model.getArcs() != null) {
			// Parcours tous les arcs
			for (int i = 0; i < model.getArcs().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (model.getArcs()[i] == null) {
					break;
				}

				// Ajout une commande pour créer l'arc
				ICommand addArcCommand = new CreateArcCommand(
						model.getArcs()[i].getId(),
						model.getArcs()[i].getType(),
						model.getArcs()[i].getSource(),
						model.getArcs()[i].getDestination());
				commandForNewGraph.add(addArcCommand);

				// Parcours les attributs de l'arc
				for (int j = 0; j < model.getArcs()[i].getAtts().length; j++) {
					// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
					if (model.getArcs()[i].getAtts()[j] == null) {
						break;
					}

					// Ajout une commande pour créer un attribut de l'arc
					ICommand addArcAttCommand = new CreateAttributeCommand(
							model.getArcs()[i].getAtts()[j].getAttName(),
							model.getArcs()[i].getId(),
							myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
					commandForNewGraph.add(addArcAttCommand);

				}

				// Parcours les points d'inflexion de l'arc
				for (int j = 0; j < model.getArcs()[i].getPoints().length; j++) {
					// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
					if (model.getArcs()[i].getPoints()[j] == null) {
						break;
					}

					// Ajout une commande pour créer un point d'inflexion à l'arc
					ICommand addArcInflexPtCommand = new CreateInflexPointCommand(
							model.getArcs()[i].getId(),
							model.getArcs()[i].getPoints()[j].getXx(),
							model.getArcs()[i].getPoints()[j].getYy());
					commandForNewGraph.add(addArcInflexPtCommand);

				}
			}
		}

		System.out.println();
		System.out.println("COMMANDES POUR CREER UN MODEL:");
		for (ICommand command : commandForNewGraph) {
			command.execute(newGraph);
			System.out.println("\t" + command.toString());
		}


		// Parcours la liste des commandes à exécuter pour créer le nouveau graph
		for (ICommand command : commandForNewGraph) {
			command.execute(newGraph);
		}

		return newGraph;
	}

	/**
	 * Transforme le tableau en une chaîne de caractère
	 * @param tab le tableau des valeurs
	 * @param delimiter le delimiteur
	 * @return la chaîne de caractère
	 */
	private String myJoin(AttributeValue[] tab, String delimiter) {
		StringBuilder buffer = new StringBuilder();

		if (tab.length > 0) {
			buffer.append(tab[0].getValue());
		}

		for (int i = 1; i < tab.length; i++) {
			buffer.append(delimiter);
			buffer.append(tab[i].getValue());
		}

		return buffer.toString();
	}

	/**
	 * Affiche l'arbe d'un sous-résultat
	 * @param res le sous-résultat à afficher
	 * @param dec le décalage
	 */
	private void printResult(ISubResult res, String dec) {
			System.out.println(dec + "+ " + res.getName());
			for (String str : res.getTextualResults()) {
				System.out.println("   " + dec + "- " + "TextualResult     : " + str);
			}
			for (Integer o : res.getObjectsOutline()) {
				System.out.println("   " + dec + "- " + "ObjectsOutline    : " + o);
			}
			for (Integer i : res.getObjectsDesignation()) {
				System.out.println("   " + dec + "- " + "ObjectsDesignation: " + i);
			}
			for (Entry<Integer, List<String>> entry : res.getAttributesOutline().entrySet()) {
				System.out.println("   " + dec + "* " + "AttributesOutline : " + entry.getKey());
				for (String s : entry.getValue()) {
					System.out.println("   " + "   " + dec + "- " + "AttributesOutline : " + s);
				}
			}
			for (ISubResult subRes : res.getChildren()) {
				printResult(subRes, "   " + dec);
			}
	}

}
