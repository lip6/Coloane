package fr.lip6.move.coloane.apiws.objects.result;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.CreateArcCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateAttributeCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateInflexPointCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateNodeCommand;
import fr.lip6.move.coloane.interfaces.model.command.DeleteObjectCommand;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
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

		this.subResult = new ArrayList<ISubResult>();
		/*if (result.getEnsemble().getEnsembles != null) {
			for (int i = 0; i < result.getEnsemble().getEnsembles.length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (result.getEnsemble().getEnsembles[i] == null) {
					break;
				}
				this.subResult.add(new SubResultImpl(result.getEnsemble().getEnsembles[i]));
			}
		}*/
		this.subResult.add(new SubResultImpl(result));

		if (result.getNewModels() != null) {
			if (result.getNewModels()[0] != null) {
				this.newGraph = createGraph(result.getNewModels()[0], newGraph);
			}
		}

		this.modificationsOnCurrentGraph = new ArrayList<ICommand>();
		if (result.getModelModification()) {
			Model theCurrentModel = result.getCurrentModel();
			LModificationModel theModificationModel = theCurrentModel.getModification();
			if (theModificationModel != null) {
				if (theModificationModel.getModifications() != null) {
					for (int i = 0; i < theModificationModel.getModifications().length; i++) {
						this.modificationsOnCurrentGraph.add(createCommand(theModificationModel.getModifications()[i], theCurrentModel));
					}
				}
			}
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
				break;
			// Modification d'un element
			case 2 :
				for (int i = 0; i < model.getArcs().length; i++) {
					if (model.getArcs()[i].getId() == modification.getId()) {
						//return new CreateAttributeCommand(model.getArcs()[i].getNameAtt(), modification.getId(), model.getArcs()[i].getValue());
					}
				}
				for (int i = 0; i < model.getNodes().length; i++) {
					if (model.getNodes()[i].getId() == modification.getId()) {
						//return new CreateAttributeCommand(model.getNodes()[i].getNameAtt(), modification.getId(), model.getNodes()[i].getValue());
					}
				}
				break;

			// Modification de la position d'un objet
			case 3 :

			// Modification de la taille d'un objet
			case 4 :

			// Modification de la position d'un attribut
			case 5 :

			// Ajout d'un point d'inflexion
			case 6 :

			// Supprimer tous les points d'inflexions
			case 7 :

			default : return null;
		}

		return null;
	}

	/**
	 * Crée un nouveau graph
	 * @param model le model reçu
	 * @param newGraph le graph vide pour crééer le nouveau resultat
	 * @return le nouveau graph
	 */
	private IGraph createGraph(Model model, IGraph newGraph) {
		List<ICommand> commandForNewGraph = new ArrayList<ICommand>();

		if (model.getNodes() != null) {
			for (int i = 0; i < model.getNodes().length; i++) {
				ICommand addNodeCommand = new CreateNodeCommand(model.getNodes()[i].getId(), model.getNodes()[i].getType());
				commandForNewGraph.add(addNodeCommand);
			}
			for (int i = 0; i < model.getNodes().length; i++) {
				for (int j = 0; j < model.getNodes()[i].getAtts().length; j++) {
					ICommand addNodeAttCommand = new CreateAttributeCommand(
							model.getNodes()[i].getAtts()[j].getAttName(),
							model.getNodes()[i].getId(),
							myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
					commandForNewGraph.add(addNodeAttCommand);

				}
			}

		}

		if (model.getArcs() != null) {
			for (int i = 0; i < model.getArcs().length; i++) {
				ICommand addArcCommand = new CreateArcCommand(
						model.getArcs()[i].getId(),
						model.getArcs()[i].getType(),
						model.getArcs()[i].getSource(),
						model.getArcs()[i].getDestination());
				commandForNewGraph.add(addArcCommand);
			}
			for (int i = 0; i < model.getArcs().length; i++) {
				for (int j = 0; j < model.getArcs()[i].getAtts().length; j++) {
					ICommand addArcAttCommand = new CreateAttributeCommand(
							model.getArcs()[i].getAtts()[j].getAttName(),
							model.getArcs()[i].getId(),
							myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
					commandForNewGraph.add(addArcAttCommand);

				}
				for (int j = 0; j < model.getArcs()[i].getPoints().length; j++) {
					ICommand addArcInflexPtCommand = new CreateInflexPointCommand(
							model.getArcs()[i].getId(),
							model.getArcs()[i].getPoints()[j].getXx(),
							model.getArcs()[i].getPoints()[j].getYy());
					commandForNewGraph.add(addArcInflexPtCommand);

				}
			}
		}

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

}
