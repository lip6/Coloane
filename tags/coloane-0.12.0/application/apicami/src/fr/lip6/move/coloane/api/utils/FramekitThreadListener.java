package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.api.exceptions.CommunicationCloseException;
import fr.lip6.move.coloane.api.exceptions.UnexpectedCamiCommand;
import fr.lip6.move.coloane.api.main.Api;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.Model;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;
import fr.lip6.move.coloane.interfaces.objects.ResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.interfaces.objects.UpdateMenuCom;
import fr.lip6.move.coloane.interfaces.translators.CamiTranslator;

import java.util.Vector;

/**
 * Classe implementant le comportement de l'ecouteur principal de Coloane
 */
public class FramekitThreadListener extends Thread {

	/** Reference vers l'api */
	private Api api;

	/** Reference vers l'objet ComLowLevel */
	private ComLowLevel lowCom;

	/** Liste des menus */
	private Vector<IRootMenuCom> menuList;

	/** Liste des menus a mettre a jour */
	private Vector<IUpdateMenuCom> menuUpdates;

	/** Liste des dialogues */
	private Vector<IDialogCom> dialogsList;

	/**
	 * Constructeur
	 * @param api point d'entre vers l'api
	 * @param lowCom point d'entree vers la com
	 * @param verrou le verrou du speaker
	 */
	public FramekitThreadListener(Api activeAPI, ComLowLevel com) {
		this.api = activeAPI;
		this.lowCom = com;
		this.menuList = new Vector<IRootMenuCom>();
		this.menuUpdates = new Vector<IUpdateMenuCom>();
		this.dialogsList = new Vector<IDialogCom>();
	}

	/**
	 * Le corps du thread
	 */
	public final void run() {

		/* Le menu en cours de construction */
		Vector<Vector<String>> menu = null;

		/* La boite de dialogue en cours de construction */
		Vector<Vector<String>> dialog = null;

		/* Le modele en cours de construction */
		Vector<String> model = null;

		/* L'ensemble de resultats en cours de construction */
		IResultsCom results = null;

		/* Un sous resultat */
		SubResultsCom subresult = null;

		// Boucle d'ecoute
		while (true) {

			// En mode turboboost, plusieurs commande peuvent etre fournies
			Vector<String> receivedCommands = null;

			// Lecture des commandes sur le flux d'entree
			try {
				receivedCommands = this.lowCom.readCommande();
			} catch (CommunicationCloseException e) {
				Api.getLogger().warning("Connexion Failed");
				api.closeConnexion(1, "Disconnected from Framekit", 1);
				return;
			}

			// Si on recoit un mesage fin de service
			if (receivedCommands.elementAt(0).equals("EOS")) {
				api.closeConnexion(1, "Disconnected from Framekit", 1);
				Api.getLogger().warning("Connexion Closed");
				break;
			}

			// Parcours de toutes les commandes recues
			for (String cmd : receivedCommands) {

				// Si la commande recue est vide : on passe a la suivante
				if (cmd.length() == 0) { continue; }

				// Decoupage des arguments
				Vector<String> listeArgs;
				try {
					listeArgs = FKCommand.getArgs(cmd);
				} catch (SyntaxErrorException e) {
					Api.getLogger().warning("Erreur de parse de la commande " + cmd);
					continue;
				}

				// Si la commande recue ne conteient aucun argument
				if (listeArgs == null) { continue; }

				// Message TQ
				// Transmission d'un etat du service en cours de realisation
				if (listeArgs.firstElement().equals("TQ")) {
					int type = Integer.parseInt((String) listeArgs.elementAt(3));

					switch(type) {

					case 2 :
						String service = (String) listeArgs.elementAt(2);
						String description = (String) listeArgs.elementAt(4);
						api.setTaskDescription(service, description);
						break;

					case 3 :
						break;

					case 6 :
						break;

						// Modification de l'arbre des services : active
					case 7 :
						try {
							String root = (String) listeArgs.get(1);
							String toUpdate = (String) listeArgs.get(2);
							IUpdateMenuCom update = new UpdateMenuCom(root, toUpdate, true);
							menuUpdates.add(update);
						} catch (Exception e) {
							Api.getLogger().warning("Erreur reception TQ type= 7");
						}
						break;


						// Modification de l'arbre des services : desactive
					case 8 :
						try {
							String root = (String) listeArgs.get(1);
							String toUpdate = (String) listeArgs.get(2);
							IUpdateMenuCom update = new UpdateMenuCom(root, toUpdate, false);
							menuUpdates.add(update);
						} catch (Exception e) {
							Api.getLogger().warning("Erreur reception TQ type = 8");
						}
						break;


						// Depracated
					case 9 :
						Api.getLogger().warning("TQ = 9 => Deprecated");
						break;

					default :
						Api.getLogger().warning("Commande TQ inconnue " + type);
					}
					continue;
				}

				// Message CQ
				// Creation du noeud racine de l'arbre des services
				if (listeArgs.firstElement().equals("CQ")) {
					menu = new Vector<Vector<String>>();
					menu.add(listeArgs);
					continue;
				}

				// Message AQ
				// Ajout d'un service dans l'arbre des services
				if (listeArgs.firstElement().equals("AQ")) {
					menu.add(listeArgs);
					continue;
				}

				// Message FQ
				// Fin de la transmission d'un menu de services
				if (listeArgs.firstElement().equals("FQ")) {
					try {
						menuList.add(CamiBuilder.buildMenu(menu));
					} catch (UnexpectedCamiCommand e) {
						Api.getLogger().warning("Erreur lors de la construction du menu");
					}
					continue;
				}

				// Message VQ
				// Affichage d'un menu (premiere construction a l'aide de AQ)
				if (listeArgs.firstElement().equals("VQ")) {
					String menuToDraw = (String) listeArgs.elementAt(1);

					// On recupere le menu a afficher
					for (IRootMenuCom rootMenu : menuList) {
						if (rootMenu.getRootMenuName().equals(menuToDraw)) {
							api.drawMenu(rootMenu);
							break;
						}
					}
					continue;
				}

				// Message QQ
				// Le menu est mis a jour !
				if (listeArgs.firstElement().equals("QQ")) {

					// Mise a jour des menus
					api.updateMenu(menuUpdates);

					 int type = Integer.parseInt((String) listeArgs.elementAt(1));
					 if (type == 3) {
						 api.setEndOpenSession();
					 }

					 api.setEndService();

					continue;
				}

				// Message KO
				// Fermeture brutale de la connexion
				if (listeArgs.firstElement().equals("KO")) {
					Integer i = new Integer(listeArgs.elementAt(3).toString());
					api.closeConnexion(1, (String) listeArgs.elementAt(2), i.intValue());
					api.setEndService();
					continue;
				}

				// Message FC
				// Fin de connexion
				if (listeArgs.firstElement().equals("FC")) {
					api.closeConnexion(1, "Fermeture standard de FrameKit", 1);
					api.setEndService();
					continue;
				}

				// Message WN
				// Message de Warning
				if (listeArgs.firstElement().equals("WN")) {
					this.api.printHistory((String) listeArgs.elementAt(1));
					continue;
				}

				// Message TR
				// Message de Trace
				if (listeArgs.firstElement().equals("TR")) {
					this.api.printHistory((String) listeArgs.elementAt(1));
					continue;
				}

				// Message MO
				// Message d'Information
				if (listeArgs.firstElement().equals("MO")) {
					int urgent = Integer.parseInt(listeArgs.elementAt(1));
					if (urgent == 2) { this.api.setEndService(); }
					this.api.printHistory((String) listeArgs.elementAt(2));
					continue;
				}

				// Message DF
				// Demande du contenu d'un modele
				if (listeArgs.firstElement().equals("DF")) {
					this.api.getCurrentSpeaker().sendModel();
				}

				// Message DR
				// Debut de la transmission d'une reponse d'un outil
				if (listeArgs.firstElement().equals("DR")) {
					Api.getLogger().finest("Debut de resultats");
					results = new ResultsCom();
					continue;
				}

				// Message FR
				// Fin de la transmission d'une reponse a un service
				if (listeArgs.firstElement().equals("FR")) {
					Api.getLogger().finest("Fin de resultats");

					// On recherche les menus qui ont ete mis a jour
					api.updateMenu(menuUpdates);

					// On envoie la liste des resultats
					this.api.setResults(results);

					// Gestion du modele recu pendant les resultats
					if (model != null) {
						IModel builtModel;
						try {
							builtModel = new Model(model, new CamiTranslator());
							builtModel.setFormalism("AMI-NET");
						} catch (SyntaxErrorException e) {
							Api.getLogger().warning("Echec de la construction du modele recu : " + e.getMessage());
							return;
						} finally {
							model = null;
						}

						this.api.setNewModel(builtModel);

						continue;
					}

					// Indication de la fin de service
					this.api.setEndService();

					// Remise a zero des boites de dialogues
					dialogsList.clear();

					continue;
				}

				// Message RQ
				// Designation de la question a laquelle on repond
				if (listeArgs.firstElement().equals("RQ")) {
					results.setcmdRQ((String) listeArgs.elementAt(2));
					continue;
				}

				// Message DE
				// Debut d'un ensemble de resultats ou d'objets transmis par la plate-forme a Coloane
				if (listeArgs.firstElement().equals("DE")) {
					Api.getLogger().finest("Debut de reception du sous-resultat");
					if (listeArgs.size() > 1) {
						subresult = new SubResultsCom(listeArgs.get(1));
					} else {
						subresult = new SubResultsCom("");
					}
					continue;
				}

				// Message RT
				// Transmission d'un resultat textuel mono-ligne
				if (listeArgs.firstElement().equals("RT")) {
					if (!listeArgs.elementAt(1).equals("") && !listeArgs.elementAt(1).equals("0")) {
						subresult.addCmdRT((String) listeArgs.elementAt(1));
					}
					continue;
				}

				// Message RO
				// Designation d'un objet associe au resultat dans un ensemble
				if (listeArgs.firstElement().equals("RO")) {
					subresult.addCmdRO((String) listeArgs.elementAt(1));
					continue;
				}

				// Message ME
				// Mise en evidence d'un obet Coloane
				if (listeArgs.firstElement().equals("ME")) {
					subresult.addCmdME((String) listeArgs.elementAt(1));
					continue;
				}

				// Message XA
				// Modification d'un attribut du modele
				if (listeArgs.firstElement().equals("XA")) {
					if (subresult == null) { subresult = new SubResultsCom("");	}
					subresult.addCmdXA(listeArgs.elementAt(1), listeArgs.elementAt(2), listeArgs.elementAt(3));
					continue;
				}

				// Message FE
				// Fin d'ensemble de resultats ou d'objets transmis
				if (listeArgs.firstElement().equals("FE")) {
					Api.getLogger().finest("Fin de reception du sous-resultat");
					results.addResultats(subresult);
					subresult = null;
					continue;
				}


				// Message DC
				// Debut de transmission de la definition d'une boite de dialogue
				if (listeArgs.firstElement().equals("DC")) {
					dialog = new Vector<Vector<String>>();
					dialog.add(listeArgs);
				}

				// Message CE
				// Creation d'un dialogue
				if (listeArgs.firstElement().equals("CE")) {
					dialog.add(listeArgs);
					continue;
				}

				// Message DS
				// Suite de la construction d'une boite de dialogue
				if (listeArgs.firstElement().equals("DS")) {
					dialog.add(listeArgs);
				}

				// Message FS
				// Suite de la construction d'une boite de dialogue
				if (listeArgs.firstElement().equals("FS")) {
					api.setEndCloseCurrentSession();
				}

				// Message DB
				// Debut de transmission d'un modele
				if (listeArgs.firstElement().equals("DB")) {
					model = new Vector<String>();
					continue;
				}

				// Messages CN CB CA CT CM PO PT PI
				// Element de construction d'un modele
				if ((listeArgs.firstElement()).equals("CN")
						|| (listeArgs.firstElement()).equals("CB")
						|| (listeArgs.firstElement()).equals("CA")
						|| (listeArgs.firstElement()).equals("CT")
						|| (listeArgs.firstElement()).equals("CM")
						|| (listeArgs.firstElement()).equals("PO")
						|| (listeArgs.firstElement()).equals("pO")
						|| (listeArgs.firstElement()).equals("PT")
						|| (listeArgs.firstElement()).equals("PI")
						|| (listeArgs.firstElement()).equals("SU")) {

					if (model == null) {
						model = new Vector<String>();
						model = this.api.getModel().translate();
					}

					model.add(cmd);
				}

				// Message FB
				// Fin de la transmission d'un modele
				if (listeArgs.firstElement().equals("FB")) {
					IModel builtModel;
					try {
						builtModel = new Model(model, new CamiTranslator());
						builtModel.setFormalism("ReachabilityGraph");
					} catch (SyntaxErrorException e) {
						e.printStackTrace();
						return;
					} finally {
						model = null;
					}
					this.api.setNewModel(builtModel);

					continue;
				}

				// Message FF
				// Fin de la transmission d'une boite de dialogue
				if (listeArgs.firstElement().equals("FF")) {
					dialog.add(listeArgs);

					// Construction du menu
					try {
						dialogsList.add(CamiBuilder.buildDialog(dialog));
					} catch (Exception e) {
						Api.getLogger().warning("Erreur dans FF = Impossible de construire la boite de dialogue");
					}

					continue;
				}

				// Message AD
				// Affichage dune boite de dialogue referencee par un identifiant unique
				if (listeArgs.firstElement().equals("AD")) {

					// Extraction de l'identite de la boite de dialogue a afficher
					int identity = Integer.parseInt((String) listeArgs.elementAt(1));

					// Recherche de la boite de dialogue
					boolean indic = false;
					for (IDialogCom d : dialogsList) {
						if (identity == d.getId()) {
							api.drawDialog(d);
							indic = true;
							break;
						}
					}

					// On s'assure que la boite de dialogue a bien ete trouvee
					if (!indic) {
						Api.getLogger().warning("Impossible de trouver la boite de dialogue (" + identity + ") a afficher");
					}

					continue;
				}

			}
		}
	}
}
