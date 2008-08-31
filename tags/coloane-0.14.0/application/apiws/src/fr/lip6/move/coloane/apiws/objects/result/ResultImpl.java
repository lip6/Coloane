package fr.lip6.move.coloane.apiws.objects.result;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub.AttributeValue;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.LModificationModel;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Model;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ModelModification;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.RService;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Cette classe définie un résultat pour le core de Coloane.
 *
 * @author Monir CHAOUKI
 */
public class ResultImpl implements IResult {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

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
		LOGGER.finer("Construction du résultat pour le service: " + result.getAnswerToquestion().getName());

		this.rootName = service.getRoot();
		this.serviceName = result.getAnswerToquestion().getName();

		// Création de la liste des sous-resultats
		LOGGER.finer("Création de la liste des sous-résultats");
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
		LOGGER.finer("Création de la liste des commandes ignorée");
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
				LOGGER.finer("Création d'un nouveau graph");
				this.newGraph = createGraph(result.getNewModels()[0], newGraph);
			}
		}

		// Création de la liste des modifications à apporter sur le graph courrant
		LOGGER.finer("Création de la liste des commandes pour modifié le graph courrant");
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

						createCommand(theModificationModel.getModifications()[i], theCurrentModel, this.modificationsOnCurrentGraph);
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
	 * Remplie une liste de commande pour modifier l'ancien model
	 * @param modification la modification a apporter sur le model
	 * @param model le model courrant modifié
	 * @param listeCommand la liste de commande à remplire
	 */
	private void createCommand(ModelModification modification, Model model, List<ICommand> listeCommand) {
		switch (modification.getType()) {
			// Suppresion d'un element
			case 0:
				LOGGER.finest("Suppression de l'objet: id:" + modification.getId());
				listeCommand.add(new DeleteObjectCommand(modification.getId()));
				return;
			// Ajout d'un element
			case 1:
				if (model.getNodes() != null) {
					for (int i = 0; i < model.getNodes().length; i++) {
						if (model.getNodes()[i] == null) {
							break;
						}
						if (model.getNodes()[i].getId() == modification.getId()) {
							LOGGER.finest("Ajout d'un noeud: id:" + modification.getId() + " type:" + model.getNodes()[i].getType());
							listeCommand.add(new CreateNodeCommand(modification.getId(), model.getNodes()[i].getType()));

							LOGGER.finest(
									"Ajout la position d'un noeud: id:" + modification.getId()
									+ " x:" + model.getNodes()[i].getPosition().getXx()
									+ " y:" + model.getNodes()[i].getPosition().getYy());
							listeCommand.add(new ObjectPositionCommand(modification.getId(), model.getNodes()[i].getPosition().getXx(), model.getNodes()[i].getPosition().getYy()));

							for (int j = 0; j < model.getNodes()[i].getAtts().length; j++) {
								if (model.getNodes()[i].getAtts()[j] == null) {
									break;
								}
								LOGGER.finest(
										"Ajout l'attribut d'un noeud: id:" + modification.getId()
										+ " nameAtt:" + model.getNodes()[i].getAtts()[j].getAttName()
										+ " valueAtt:" + myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
								listeCommand.add(new CreateAttributeCommand(
										model.getNodes()[i].getAtts()[j].getAttName(),
										model.getNodes()[i].getId(),
										myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n")));
							}
						}
					}
				}
				if (model.getArcs() != null) {
					for (int i = 0; i < model.getArcs().length; i++) {
						if (model.getArcs()[i] == null) {
							break;
						}
						if (model.getArcs()[i].getId() == modification.getId()) {
							LOGGER.finest(
									"Ajout d'un arc: id:" + modification.getId()
									+ " type:" + model.getArcs()[i].getType()
									+ " idSource:" + model.getArcs()[i].getSource()
									+ " idTarget:" + model.getArcs()[i].getDestination());
							listeCommand.add(new CreateArcCommand(
									modification.getId(),
									model.getArcs()[i].getType(),
									model.getArcs()[i].getSource(),
									model.getArcs()[i].getDestination()));

							for (int j = 0; j < model.getArcs()[i].getAtts().length; j++) {
								if (model.getArcs()[i].getAtts()[j] == null) {
									break;
								}
								LOGGER.finest(
										"Ajout l'attribut d'un arc: id:" + modification.getId()
										+ " nameAtt:" + model.getArcs()[i].getAtts()[j].getAttName()
										+ " valueAtt:" + myJoin(model.getArcs()[i].getAtts()[j].getValues(), "\n"));
								listeCommand.add(new CreateAttributeCommand(
										model.getArcs()[i].getAtts()[j].getAttName(),
										model.getArcs()[i].getId(),
										myJoin(model.getArcs()[i].getAtts()[j].getValues(), "\n")));
							}

							for (int j = 0; j < model.getArcs()[i].getPoints().length; j++) {
								if (model.getArcs()[i].getPoints()[j] == null) {
									break;
								}
								LOGGER.finest(
										"Ajout un point d'inflexion pour l'arc: id:" + modification.getId()
										+ " x:" + model.getArcs()[i].getPoints()[j].getXx()
										+ " y:" + model.getArcs()[i].getPoints()[j].getYy());
								listeCommand.add(new CreateInflexPointCommand(
										model.getArcs()[i].getId(),
										model.getArcs()[i].getPoints()[j].getXx(),
										model.getArcs()[i].getPoints()[j].getYy()));
							}
						}
					}
				}
				return;
			// Modification d'un element
			case 2:
				LOGGER.finest(
						"Ajout d'un attribut: idReferenced:" + modification.getId()
						+ " nameAtt:" + modification.getNameAtt()
						+ " valueAtt:" + modification.getValue());
				listeCommand.add(new CreateAttributeCommand(modification.getNameAtt(), modification.getId(), modification.getValue()));
				return;
			// Modification de la position d'un objet
			case 3:
				LOGGER.finest(
						"Modification de la position d'un objet: id:" + modification.getId()
						+ " x:" + modification.getPos().getXx()
						+ " y:" + modification.getPos().getYy());
				listeCommand.add(new ObjectPositionCommand(modification.getId(), modification.getPos().getXx(), modification.getPos().getYy()));
				return;
			// Modification de la taille d'un objet
			case 4 :
				LOGGER.warning("Modification de la taille d'un objet non pris en compte: id:" + modification.getId());
				return;
			// Modification de la position d'un attribut
			case 5 :
				LOGGER.finest(
						"Modification de la position d'un attribut: idReferenced:" + modification.getId()
						+ "nameAtt:" + modification.getNameAtt()
						+ " x:" + modification.getPos().getXx()
						+ " y:" + modification.getPos().getYy());
				listeCommand.add(new AttributePositionCommand(
						modification.getId(),
						modification.getNameAtt(),
						modification.getPos().getXx(),
						modification.getPos().getYy()));
				return;
			// Ajout d'un point d'inflexion
			case 6 :
				LOGGER.finest(
						"Création d'un points d'inflexion: id:" + modification.getId()
						+ " x:" + modification.getPos().getXx()
						+ " y:" + modification.getPos().getYy());
				listeCommand.add(new CreateInflexPointCommand(
						modification.getId(),
						modification.getPos().getXx(),
						modification.getPos().getYy()));
				return;
			// Supprimer tous les points d'inflexions
			case 7 :
				LOGGER.finest("Suppression de tous les points d'inflexions");
				listeCommand.add(new DeleteInflexPointsCommand());
				return;
			// Sinon
			default:
				LOGGER.warning("Modification inconnu sur l'objet: id:" + modification.getId());
				return;
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

				// On trait tous les noeuds dont l'identifiant est différent de 1
				if (model.getNodes()[i].getId() != 1) {

					//Ajout une commande pour créer le noeud
					LOGGER.finest(
							"Création d'un noeud: id:" + model.getNodes()[i].getId()
							+ " type:" + model.getNodes()[i].getType());
					ICommand addNodeCommand = new CreateNodeCommand(model.getNodes()[i].getId(), model.getNodes()[i].getType());
					commandForNewGraph.add(addNodeCommand);

					// Parcours les attributs du noeud
					for (int j = 0; j < model.getNodes()[i].getAtts().length; j++) {
						// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
						if (model.getNodes()[i].getAtts()[j] == null) {
							break;
						}

						// Ajout une commande pour créer un attribut du noeud
						LOGGER.finest(
								"Creation d'un attribut pour un noeud: idReferenced:" + model.getNodes()[i].getId()
								+ " nameAtt:" + model.getNodes()[i].getAtts()[j].getAttName()
								+ " valueAtt:" +  myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
						ICommand addNodeAttCommand = new CreateAttributeCommand(
								model.getNodes()[i].getAtts()[j].getAttName(),
								model.getNodes()[i].getId(),
								myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
						commandForNewGraph.add(addNodeAttCommand);

					}
				} else { // Si c'est un noeud de type 1
					// Parcours les attributs du noeud
					for (int j = 0; j < model.getNodes()[i].getAtts().length; j++) {
						// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
						if (model.getNodes()[i].getAtts()[j] == null) {
							break;
						}

						// Ajout une commande pour créer un attribut du noeud
						LOGGER.finest(
								"Creation d'un attribut pour le noeud special: idReferenced:" + model.getNodes()[i].getId()
								+ " nameAtt:" + model.getNodes()[i].getAtts()[j].getAttName()
								+ " valueAtt:" +  myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
						ICommand addNodeAttCommand = new CreateAttributeCommand(
								model.getNodes()[i].getAtts()[j].getAttName(),
								model.getNodes()[i].getId(),
								myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
						commandForNewGraph.add(addNodeAttCommand);

					}
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
				LOGGER.finest(
						"Creation d'un arc: id:" + model.getArcs()[i].getId()
						+ " type:" + model.getArcs()[i].getType()
						+ " idSource:" + model.getArcs()[i].getSource()
						+ " idTarget:" + model.getArcs()[i].getDestination());
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
					LOGGER.finest(
							"Création d'un attribut pour un arc: idReferenced:" + model.getArcs()[i].getId()
							+ " nameAtt:" + model.getArcs()[i].getAtts()[j].getAttName()
							+ " valueAtt:" + myJoin(model.getNodes()[i].getAtts()[j].getValues(), "\n"));
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
					LOGGER.finest(
							"Création d'un point d'un point d'inflexion: id:" + model.getArcs()[i].getId()
							+ " x:" + model.getArcs()[i].getPoints()[j].getXx()
							+ " y:" + model.getArcs()[i].getPoints()[j].getYy());
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
			System.out.println("\t" + command.toString());
		}


		// Parcours la liste des commandes à exécuter pour créer le nouveau graph
		for (ICommand command : commandForNewGraph) {
			try {
				command.execute(newGraph);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				LOGGER.warning("La creéation du nouveau graph à echouer: " + e.getMessage());
				e.printStackTrace();
			}
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
