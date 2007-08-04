package fr.lip6.move.coloane.api.utils;

import java.util.Vector;

import fr.lip6.move.coloane.api.log.LogsUtils;
import fr.lip6.move.coloane.api.main.Api;
import fr.lip6.move.coloane.api.model.Model;
import fr.lip6.move.coloane.api.objects.ResultsCom;
import fr.lip6.move.coloane.api.objects.UpdateMenuCom;

import fr.lip6.move.coloane.interfaces.objects.IDialogCom;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.IRootMenuCom;
import fr.lip6.move.coloane.interfaces.objects.IUpdateMenuCom;
import fr.lip6.move.coloane.interfaces.model.IModel;

/**
 * Classe implementant le comportement de l'ecouteur principal de Coloane
 */
public class FramekitThreadListener extends Thread {

	/** Reference vers l'api */
	private Api api;

	/** Reference vers l'objet ComLowLevel */
	private ComLowLevel lowCom;

	/** Le verrou pour bloque la thread */
	private Lock verrou;

	/** Permet de mettre a jour le menu */
	private CamiTranslator translater;

	/** Liste des menus */
	private Vector<IRootMenuCom> menuList;

	/** Liste des menus a mettre a jour */
	private Vector<IUpdateMenuCom> menuUpdates;

	/** Indicateur de fraicheur des mises a jour */
	private boolean resetUpdates;

	/** Indicateur de fraicheur des resultats */
	private boolean resetResults;

	/** Indicateur de fraicheur du modele */
	private boolean modelState;

	/** Liste des dialogues */
	private Vector<IDialogCom> dialogList;

	/** Liste des resultats */
	private Vector<IResultsCom> resultList;

	private LogsUtils logsutils;

	/**
	 * Constructeur
	 * @param api point d'entre vers l'api
	 * @param lowCom point d'entree vers la com
	 * @param verrou le verrou du speaker
	 */
	public FramekitThreadListener(Api api, ComLowLevel lowCom, Lock verrou) {

		this.api = api;
		this.lowCom = lowCom;
		this.verrou = verrou;
		this.translater = new CamiTranslator();
		this.menuList = new Vector<IRootMenuCom>();
		this.menuUpdates = new Vector<IUpdateMenuCom>();
		this.dialogList = new Vector<IDialogCom>();
		this.resultList = new Vector<IResultsCom>();
		this.resetUpdates = false;
		this.resetResults = false;
		this.modelState = false;
		this.logsutils = new LogsUtils();
	}

	/**
	 * Le corps du thread
	 */
	public void run() {
		Api.apiLogger.entering("FrameKitThreadListener", "run");
		// Le menu en cours de construction
		IRootMenuCom menu = null;

		// Le dialogue en cours de construction
		IDialogCom dialog = null;

		// Le resultat en cours de construction
		IResultsCom result = null;

		// La commande en cours de traitement
		Commande cmd = new Commande();  // la commande recu

		// La liste des arguments d'une commande en cours de traitement */
		Vector listeArgs;

		// Les instructions CAMI recues decrivant un menu
		Vector<Vector> currentMenu = null;

		// Le modele recu
		Vector<String> modelReceive = new Vector<String>();

		// Indications concernant la description d'une boite de dialogue
		Vector<Vector> vectorDialog = new Vector<Vector>();

		// Boucle d'ecoute
		while (true) {

			// En mode turboboost, plusieurs commande peuvent �tre fournies
			Vector commandeRecue;

			try {
				// Lecture des commandes sur le flux d'entree
				commandeRecue = this.lowCom.readCommande();

				// Si on recoit un mesage fin de service
				if (commandeRecue.elementAt(0).equals("EOS")) {
					api.closeConnexion(1, "Disconnected from Framekit", 1);
					Api.apiLogger.finer("Connexion Closed");
					//System.out.println("Connexion closed");
					break;
				}	

				// En cas d'erreur, on se deconnecte de FrameKit	
			} catch (Exception e) {
				Api.apiLogger.throwing("FrameKitThreadListener", "run", e);
				Api.apiLogger.warning(logsutils.StackToString(e));
				api.closeConnexion(1, "Deconnexion de FrameKit", 1);
				Api.apiLogger.finer("Connexion fermee");
				//System.out.println("Connexion fermee");
				break;
			}


			// Analyse des commandes recues
			try {

				// Parcours de toutes les commandes re�ues
				for (int numCommande = 0; numCommande<commandeRecue.size(); numCommande++) {

					// Si la commande recue est vide : on passe a la suivante
					if (((String) commandeRecue.elementAt(numCommande)).length() == 0) {
						continue;
					}

					// Decoupage des arguments
					listeArgs = cmd.getArgs((String) commandeRecue.elementAt(numCommande));

					// Si la commande recue ne conteient aucun argument
					if (listeArgs == null) {
						continue;
					}

					// Message TQ
					// Transmission d'un etat du service en cours de realisation
					if (listeArgs.firstElement().equals("TQ")) {
						int type = Integer.parseInt((String) listeArgs.elementAt(3));
						//String message  = (String) listeArgs.elementAt(4);

						switch(type) {

							// Etat du service : inactif
							case 1 : {
								//this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
								break;
							}
		
							// Etat du service : actif
							case 2 : {
								//this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
								break;
							}						
		
							// Etat du service : termine
							case 3 : {
								//this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
								break;
							}
		
							// Etat du service : non documente!
							case 5 : {
								//this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
								break;
							}
		
							// Etat du service : termine de facon errone
							case 6 : {
								//this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
								break;
							}
		
							// Modification de l'arbre des services : active
							case 7 : {
								try {
									if (resetUpdates) {
										menuUpdates.removeAllElements();
										resetUpdates = false;
									}
									String root = (String) listeArgs.get(1);
									String toUpdate = (String) listeArgs.get(2);
		
									// On essaye de recuperer l'indicateur du menu de syntaxe
									// pour savoir si le modele est propre ou sale
									if (toUpdate.equals("Petri net syntax checker")) {
										this.modelState = true;// Le modele est sale 
									}
		
									IUpdateMenuCom update = new UpdateMenuCom(root,toUpdate,true);
									menuUpdates.add(update);
								} catch (Exception e) {
									Api.apiLogger.throwing("FrameKitThread", "run",e);
									Api.apiLogger.warning("Erreur reception TQ type= 7");
									Api.apiLogger.warning(logsutils.StackToString(e));
									//System.err.println("Erreur reception TQ type = 7");
								}
								break;
							}
		
							// Modification de l'arbre des services : desactive
							case 8 : {
								try {
									if (resetUpdates) {
										menuUpdates.removeAllElements();
										resetUpdates = false;
									}
									String root = (String) listeArgs.get(1);
									String toUpdate = (String) listeArgs.get(2);
		
									// On essaye de recuperer l'indicateur du menu de syntaxe
									// pour savoir si le modele est propre ou sale
									if (toUpdate.equals("Petri net syntax checker")) {
										this.modelState = false;// Le modele est propre 
									}
		
									IUpdateMenuCom update = new UpdateMenuCom(root,toUpdate,false);
									menuUpdates.add(update);
								} catch (Exception e) {
									Api.apiLogger.throwing("FrameKitThread", "run",e);
									Api.apiLogger.warning("Erreur reception TQ type = 8");
									Api.apiLogger.warning(logsutils.StackToString(e));
									//System.err.println("Erreur reception TQ type = 8");
								}
								break;
							}
		
							// Depracated 
							case 9 : {
								Api.apiLogger.finer("TQ = 9 => Deprecated");
								break;
							}
		
							default :
								Api.apiLogger.warning("Commande inconnue" + type);
								Api.apiLogger.warning("Commande inconnue" + type);
								break;
						}
						continue;
					} 

					// Message OS
					// Ouverture d'une session
					if (listeArgs.firstElement().equals("OS")) {
						this.verrou.unlock();
						continue;
					} 

					// Message TD
					// TODO : Documenter ?
					if (listeArgs.firstElement().equals("TD")) {
						this.verrou.unlock();
						continue;
					}

					// Message FA
					// TODO : Documenter ?
					if (listeArgs.firstElement().equals("FA")) {
						this.verrou.unlock();
						continue;
					} 

					// Message TL
					// TODO : Documenter ?
					if (listeArgs.firstElement().equals("TL")) {
						this.verrou.unlock();
						continue;
					} 


					// Message VI
					// TODO : Documenter
					if (listeArgs.firstElement().equals("VI")) {
						String serviceName = (String) listeArgs.elementAt(1);
						this.verrou.unlock(serviceName);
						continue;
					} 

					// Message FL
					// TODO : Documenter
					if (listeArgs.firstElement().equals("FL")) {
						this.verrou.unlock();
						continue;
					} 

					// Message DQ
					// Debut de la transmission d'un arbre de services
					if (listeArgs.firstElement().equals("DQ")) {
						continue;
					}

					// Message CQ
					// Creation du noeud racine de l'arbre des services
					if (listeArgs.firstElement().equals("CQ")) {
						currentMenu = new Vector<Vector>();
						currentMenu.add(listeArgs);
						continue;
					}

					// Message AQ
					// Ajout d'un service dans l'arbre des services
					if (listeArgs.firstElement().equals("AQ")) {
						currentMenu.add(listeArgs);
						continue;
					}

					// Message HQ
					// Aide associee a une entree du menu de services
					if (listeArgs.firstElement().equals("HQ")) {
						continue;
					}

					// Message VQ
					// TODO : A verifier ? Affichage d'un menu ?
					if (listeArgs.firstElement().equals("VQ")) {
						String toDraw = (String) listeArgs.elementAt(1);

						// On recupere le menu a afficher
						for (IRootMenuCom rootMenu : menuList) {
							if (rootMenu.getRootMenuName().equals(toDraw)) {
								api.drawMenu(rootMenu);
								break;
							}
						}
						continue; 
					}

					// Message EQ
					// Cacher un menu de services
					if (listeArgs.firstElement().equals("EQ")) {
						continue;
					}

					// Message SQ
					// Desctruction d'un menu de services
					if (listeArgs.firstElement().equals("SQ")) {
						continue;
					}

					// Message FQ
					// Fin de la transmission d'un menu de services
					if (listeArgs.firstElement().equals("FQ")) {

						// Construction du menu
						try {
							menu = translater.getMenu(currentMenu);
							menuList.add(menu);
						} catch (Exception e) {
							Api.apiLogger.throwing("FrameKitThreadListener", "run", e);
							Api.apiLogger.warning("Erreur dans FQ = Impossible de construire le menu");
							Api.apiLogger.warning("Erreur dans FQ = Impossible d'ajouter le menu construit � la plateforme");
							Api.apiLogger.warning(logsutils.StackToString(e));
							//System.err.println("Erreur dans FQ = Impossible de construire le menu");
							//System.out.println("Erreur dans FQ = Impossible d'ajouter le menu construit � la plateforme");
						}
					} 

					// Message QQ
					// Le menu est mis a jour !
					if ((listeArgs.firstElement().equals("QQ")) && ((listeArgs.elementAt(1).equals("2")) || (listeArgs.elementAt(1).equals("3")))) {

						// Si la liste des menus est marquee comme invalide, on la vide nous meme
						// Ce code est utilisee dans le cas ou aucun changement n'a ete effectue sur les menus
						if (resetUpdates) {
							menuUpdates.removeAllElements();
							resetUpdates = false;
						}

						// Mise a jour des menus
						api.updateMenu(menuUpdates);
						resetUpdates = true;						

						// Indique l'etat de fraicheur du modele
						// Important au retour de la connexion par exemple
						this.api.setModelDirty(this.modelState);

						// TODO : A verifier
						this.verrou.unlock();
						continue;
					}

					// Message FR
					// Fin de la transmission d'une reponse a un service
					if (listeArgs.firstElement().equals("FR")) {

						// Si la liste des menus est marquee comme invalide, on la vide nous meme
						// Ce code est utilisee dans le cas ou aucun changement n'a ete effectue sur les menus
						if (resetUpdates) {
							menuUpdates.removeAllElements();
							resetUpdates = false;
						}

						// On recherche les menus qui ont ete mis a jour
						api.updateMenu(menuUpdates);
						resetUpdates = true;

						// Le retour d'un service indique que le modele est a jour sur la plate-forme
						this.api.setModelDirty(false);

						// Si la liste des resultats est toujours marquee comme invalide...
						// On la vide nous meme
						// Ce code est utilisee dans le cas ou aucun changement n'a ete effectue sur les resultats
						if (resetResults) {
							resultList.removeAllElements();
							resetResults = false;
						}

						// On envoie la liste des resultats
						this.api.setResults(resultList);
						resetResults = true;

						continue;
					}

					// Message KO
					// Fermeture brutale de la connexion
					if (listeArgs.firstElement().equals("KO")) {
						Integer i = new Integer(listeArgs.elementAt(2).toString());
						api.closeConnexion(1, (String) listeArgs.elementAt(1), i.intValue());
						continue;
					}

					// Message FC
					// Fin de connexion
					if (listeArgs.firstElement().equals("FC")) {
						api.closeConnexion(1, "Fermeture standard de FrameKit", 1);
						continue;
					} 

					// Message TR
					// TODO : A documenter
					if (listeArgs.firstElement().equals("TR")) {
						//this.api.sendMessageUI(FramekitMessage.TRACE, (String) listeArgs.elementAt(1), 1);
						continue;
					}

					// Message WN
					// TODO : A documenter
					if (listeArgs.firstElement().equals("WN")) {
						this.api.printHistory((String) listeArgs.elementAt(1));
						continue;
					}


					// Message MO
					// TODO : A documenter
					if (listeArgs.firstElement().equals("MO")) {
						this.api.printHistory((String) listeArgs.elementAt(2));
						continue;
					}

					// Message DF
					// Demande du contenu d'un modele
					if (listeArgs.firstElement().equals("DF")) {
						FramekitThreadSpeaker speaker = this.api.getCurrentSpeaker();
						speaker.sendModel();
					} 

					// Message DR
					// Debut de la transmission d'une reponse d'un outil
					if (listeArgs.firstElement().equals("DR")) {
						continue;
					}

					// Message RQ
					// Designation de la question a laquelle on repond
					if (listeArgs.firstElement().equals("RQ")) {
						continue;
					}

					// Message DE
					// Debut d'un ensemble de resultats ou d'objets transmis par la plate-forme a Coloane 
					if (listeArgs.firstElement().equals("DE")) {
						result = new ResultsCom();
						continue;
					}

					// Message RT
					// Transmission d'un resultat textuel mono-ligne
					if (listeArgs.firstElement().equals("RT")) {
						if (!listeArgs.elementAt(1).equals("") && !listeArgs.elementAt(1).equals("0")) {
							result.addDescription((String) listeArgs.elementAt(1));
						}
						continue;
					}

					// Message RO
					// Designation d'un objet associe au resultat dans un ensemble
					if (listeArgs.firstElement().equals("RO")) {
						result.addElement((String) listeArgs.elementAt(1));
						continue;
					}

					// Message ME
					// Mise en evidence d'un obet Coloane
					if (listeArgs.firstElement().equals("ME")) {
						continue;
					}

					// Message FE
					// Fin d'ensemble de resultats ou d'objets transmis
					if (listeArgs.firstElement().equals("FE")) {
						if (resetResults) {
							resultList.removeAllElements();
							resetResults = false;
						}
						resultList.add(result);
						continue;
					}

					// Message SS
					// Suspension d'un session Coloane
					if (listeArgs.firstElement().equals("SS")) {
						Api.apiLogger.finer("Unlock SS");
						//System.out.println("Unlock SS");
						this.verrou.unlock();
						continue;
					}

					// Message RS
					// Reprise d'une session Coloane
					if (listeArgs.firstElement().equals("RS")) {
						//this.api.setResumedSession(true);
						continue;
					}

					// Message FS
					// Fin d'une session Coloane
					if (listeArgs.firstElement().equals("FS")) {
						Api.apiLogger.finer("Unlock FS");
						//System.out.println("Unlock FS");
						this.verrou.unlock();
						continue;
					}

					// Message DC
					// Debut de transmission de la definition d'une boite de dialogue
					if (listeArgs.firstElement().equals("DC")) {
						vectorDialog = new Vector<Vector>();
						vectorDialog.add(listeArgs);
					}					

					// Message CE
					// Creation d'un dialogue
					if (listeArgs.firstElement().equals("CE")) {
						vectorDialog.add(listeArgs);
						continue;
					}

					// Message DS
					// Suite de la construction d'une boite de dialogue
					if (listeArgs.firstElement().equals("DS")) {
						vectorDialog.add(listeArgs);
					}

					// Message DB
					// Debut de transmission d'un modele
					if (listeArgs.firstElement().equals("DB")) {
						modelReceive = new Vector<String>();		
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
							|| (listeArgs.firstElement()).equals("PI")) {

						modelReceive.add((String) commandeRecue.get(numCommande));
					}

					// Message FB
					// Fin de la transmission d'un modele
					if (listeArgs.firstElement().equals("FB")) {
						IModel model = new Model(modelReceive);
						this.api.setNewModel(model);
						continue;
					}

					// Message FF
					// Fin de la transmission d'une boite de dialogue
					if (listeArgs.firstElement().equals("FF")) {
						vectorDialog.add(listeArgs);

						// Construction du menu
						try {
							dialog = translater.getDialog(vectorDialog);
							dialogList.add(dialog);
						} catch (Exception e) {
							Api.apiLogger.throwing("FrameKitThreadListener", "run", e);
							Api.apiLogger.warning("Erreur dans FF = Impossible de construire la boite de dialogue");
							Api.apiLogger.warning(logsutils.StackToString(e));
							//System.err.println("Erreur dans FF = Impossible de construire la boite de dialogue");
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
						for (int index = 0; index < dialogList.size(); index++) {
							if (identity == dialogList.get(index).getId()) {
								api.drawDialog(dialogList.get(index));
								indic = true;
								break;
							}
						}

						// On s'assure que la boite de dialogue a bien ete trouvee
						if (!indic) {
							Api.apiLogger.warning("Impossible de trouver la boite de dialogue (" +identity+") a afficher");
							//System.err.println("Impossible de trouver la boite de dialogue ("+identity+") a afficher");

						}

						continue;
					}

					// Message HD
					// Cacher une fen�tre de dialogue
					if (listeArgs.firstElement().equals("HD")) {
						continue;
					}

					// Message DG
					// Destruction d'un dialogue
					if (listeArgs.firstElement().equals("DG")) {
						continue;
					}
				}
			} catch (Exception e) {
				Api.apiLogger.throwing("FrameKitThreadListener", "run", e);
				Api.apiLogger.warning(logsutils.StackToString(e));
				//e.printStackTrace();
			}
		}
	}	
}
