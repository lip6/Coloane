package fr.lip6.move.coloane.communications.utils;

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.communications.Api;
import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.communications.objects.Dialogue;
import fr.lip6.move.coloane.communications.objects.FramekitMessage;
import fr.lip6.move.coloane.communications.objects.WindowedDialogue;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.menus.Menu;
import fr.lip6.move.coloane.menus.RootMenu;
import fr.lip6.move.coloane.ui.menus.GraphicalMenu;


/**
 * @author DQS equipe 2 (Styx)
 */
public class FramekitThreadListener extends Thread {
	
	/** Reference vers l'api */
	private Api api;
	
	/** Reference vers l'objet ComLowLevel */
	private ComLowLevel comm;
	
	/** Le verrou pour bloque la thread */
	private Lock verrou;
	
	/** Permet de mettre a jour le menu */
	private CamiTranslator translater;
	
	
	private Composite parent;
	
	
	/**
	 * liste des menus
	 */
	private Vector<Menu> menuList;
	
	/**
	 * Constructeur
	 * @param apiFK point d'entre vers l'api
	 * @param com point d'entree vers la com
	 * @param aVerrou le verrou du speaker
	 */
	public FramekitThreadListener(Api apiFK, ComLowLevel com, Lock aVerrou) {
		this.api = apiFK;
		this.comm = com;
		this.verrou = aVerrou;
		this.translater = new CamiTranslator();
		this.menuList = new Vector<Menu>();
		this.parent = (Composite) Coloane.getParent();
	}
	
	/**
	 * Le corps du thread
	 */
	public void run() {
		
		// Le menu de formalisme
		Menu formalismMenu = null;
		
		Vector listeArgs;
		Commande cmd = new Commande();  // la commande recu
		Vector majMenu = new Vector();  // les mises a jours du menu
		Vector currentCAMIMenuReceive = new Vector(); // les instructions CAMI pour un menu recues
		Vector cmdCAMIMenuMajReceive = new Vector();  // les instructions CAMI pour maj les menus
		Vector leModel = new Vector();  // le modele recu
		Vector vectorDialog = new Vector();
		
		String errorSyntaxCheck = "";
		String errorId = "";
		int nbNodeError = 0;
		
		while (true) {
			
			// TODO: Etre exhaustif !
			
			// En mode turboboost, plusieurs commande peuvent être fournies
			Vector commandeRecue;
		
			try {
				// Boucle de réception
				commandeRecue = this.comm.readCommande();

				// Si on recoit un mesage fin de service
				if (commandeRecue.elementAt(0).equals("EOS")) {
					api.cnxClosed(1, "Disconnected from Framekit", 1);
					System.out.println("Connexion closed");
					break;
				}	
				
			// En cas d'erreur, on se deconnecte de FrameKit	
			} catch (Exception e) {
				e.printStackTrace();
				api.cnxClosed(1, "Deconnexion de FrameKit", 1);
				System.out.println("Connexion fermee");
				break;
			}
			
			
			// Analyse des commandes recues
			try {
				int numCommande;
				
				// Parcours de toutes les commandes reçues
				for ( numCommande=0; numCommande<commandeRecue.size(); numCommande++) {
					
					if (((String) commandeRecue.elementAt(numCommande)).length() == 0) {
						continue;
					}
					
					System.out.println("Commande en cours d'analyse : "+(String) commandeRecue.elementAt(numCommande));
					
					listeArgs = cmd.getComdRecuAndArg((String) commandeRecue.elementAt(numCommande));
					
					if (listeArgs == null) {
						System.out.println("Rien dans la commande recue");
						continue;
					}
					
					// Message TQ
					if ((listeArgs.firstElement().equals("TQ"))) {
						int type = Integer.parseInt((String) listeArgs.elementAt(3));
						String message  = (String) listeArgs.elementAt(4);
					
						switch(type) {
					
						// Etat du service : inactif
						case 1 : {
							this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
							break;
						}
						
						// Etat du service : actif
						case 2 : {
							this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
							break;
						}						
						
						// Etat du service : termine
						case 3 : {
							this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
							this.api.endService();
							break;
						}
						
						// Etat du service : non documente!
						case 5 : {
							this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
							break;
						}
						
						// Etat du service : termine de facon errone
						case 6 : {
							this.api.sendMessageUI(FramekitMessage.STATE, message, 1);
							break;
						}
						
						// Modification de l'arbre des services : active
						case 7 : {
							try {
								formalismMenu.setEnabled((String) listeArgs.get(2),true);
							} catch (Exception e) {
								System.err.println("Erreur reception TQ type = 7");
							}
							break;
						}
						
						// Modification de l'arbre des services : desactive
						case 8 : {
							try {
								for (int index = 0; index < menuList.size(); index++) {
									if (((Menu) menuList.get(index)).getName().equals(listeArgs.get(1))) {
										((Vector) cmdCAMIMenuMajReceive.get(index)).add(listeArgs);
									}
								}
							} catch (Exception e) {
								System.out.println("erreur reception TQ type = 8");
								e.printStackTrace();
							}
							break;
						}
						
						// Depracated 
						case 9 : {
							System.out.println("TQ=9 => Deprecated");
							break;
						}
						
						default :
							System.err.println("Commande inconue" + type);
						break;
						}
					} // Fin du IF TQ
				
				
					if ((listeArgs.firstElement().equals("OS"))) {
						this.verrou.unlock();
					} 
					
					if ((listeArgs.firstElement().equals("TD"))) {
						this.verrou.unlock();
					}
					
					if ((listeArgs.firstElement().equals("FA"))) {
						this.verrou.unlock();
					} 
					
					if ((listeArgs.firstElement().equals("TL"))) {
						this.verrou.unlock();
					} 
					
					if ((listeArgs.firstElement().equals("VI"))) {
						String serviceName = (String) listeArgs.elementAt(1);
						this.verrou.unlock(serviceName);
					} 
					
					if ((listeArgs.firstElement().equals("FL"))) {
						this.verrou.unlock();
					} 
					
					// Commande de debut d'envoi de menu
					if ((listeArgs.firstElement().equals("DQ"))) {
						System.out.println("Debut de reception du menu");
					}
					
					// Debut de menu
					// 1 vecteur pour les elements
					// 1 vecteur pour les modifications
					if (listeArgs.firstElement().equals("CQ")) {
						currentCAMIMenuReceive = new Vector();
						currentCAMIMenuReceive.add(listeArgs); // Ajout de CQ
						cmdCAMIMenuMajReceive.add(new Vector());
					}
					
					// Element du menu
					if (listeArgs.firstElement().equals("AQ")) {
						currentCAMIMenuReceive.add(listeArgs); // Ajout du AQ
					}
					
					if (listeArgs.firstElement().equals("HQ")) {
					}
					
					if (listeArgs.firstElement().equals("VQ")) {
					}
					
					if (listeArgs.firstElement().equals("EQ")) {
					}
					
					if (listeArgs.firstElement().equals("SQ")) {
					}
					
					// fin du menu de services
					if (listeArgs.firstElement().equals("FQ")) {
						
						// Construction du menu
						try {
							formalismMenu = translater.getMenu(currentCAMIMenuReceive);
						} catch (Exception e) {
							System.err.println("Erreur dans FQ = Impossible de construire le menu");
						}
	
						// Ajout du menu construit a la plateforme
						try {
							menuList.add(formalismMenu);
						} catch (Exception e) {
							System.out.println("Erreur dans FQ = Impossible d'ajouter le menu construit à la plateforme");
						}
						
						System.out.println("Fin du menu");
					} 
					
					
					// Le menu est mid a jour !
					if ((listeArgs.firstElement().equals("QQ")) && ((listeArgs.elementAt(1).equals("2")) || (listeArgs.elementAt(1).equals("3")))) {
											
							parent.getDisplay().asyncExec(new Runnable(){
								public void run(){
									for (int index = 0; index < menuList.size(); index++) {
										api.drawMenu((RootMenu) menuList.get(index));
									}
								}
							});

//							Menu myMajMenu = null;
//							
//							// Mise a jour du menu
//							try {
//								myMajMenu = translater.updateMenu((Vector) cmdCAMIMenuMajReceive.get(index), (Menu) menuList.get(index));
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//							
//							// On remplace le menu
//							try {
//								menuList.set(index, myMajMenu);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//							
//							// On notifie l'API qu'il faut mettre a jour le menu
//							try {
//								api.setMenu((Menu) menuList.get(index));
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//						for (int index = 0; index < cmdCAMIMenuMajReceive.size(); index++) {
//							cmdCAMIMenuMajReceive.set(index, new Vector());
//						}
							
						this.verrou.unlock();
						majMenu = new Vector();
					}
					
					//le menu est mis a jour, on le renvoie au speaker
					if ((listeArgs.firstElement().equals("FR"))) {
						System.out.println("THREAD LISTENER FR recu : NOMBRE DE MISE A JOURS => " + majMenu.size());
//						for (int index = 0; index < menuList.size(); index++) {
//							menuList.set(index, translater.updateMenu((Vector) cmdCAMIMenuMajReceive.get(index), (Menu) menuList.get(index)));
//							api.setMenu((Menu) menuList.get(index));
//						}
//						for (int index = 0; index < cmdCAMIMenuMajReceive.size(); index++) {
//							cmdCAMIMenuMajReceive.set(index, new Vector());
//						}					
					}
					
					if ((listeArgs.firstElement().equals("KO"))) {
						System.out.println("THREAD LISTENER KO recu");
						Integer i = new Integer(listeArgs.elementAt(2).toString());
						api.cnxClosed(1, (String) listeArgs.elementAt(1), i.intValue());
					}
					
					if ((listeArgs.firstElement().equals("FC"))) {
						System.out.println("THREAD LISTENER FC recu");
						api.cnxClosed(1, "fermeture normal", 1);
					} 
					
								
					if ((listeArgs.firstElement().equals("TR"))) {
						System.out.println("THREAD LISTENER TR recu");
						
						this.api.sendMessageUI(FramekitMessage.TRACE, (String) listeArgs.elementAt(1), 1);
					}
					
					if ((listeArgs.firstElement().equals("WN"))) {
						System.out.println("THREAD LISTENER WN recu");
						this.api.sendMessageUI(FramekitMessage.WARNING, (String) listeArgs.elementAt(1), 1);
					}
					
					
					// TODO: Affichage d'un message systeme ?
					if ((listeArgs.firstElement().equals("MO"))) {
						System.out.println("Message MO");
						int un = 0;
						try {
							un = Integer.parseInt((String) listeArgs.elementAt(1));
						} catch (Exception e) {
							System.err.println("Message MO incorrect");
							
						}
						//this.api.sendMessageUI(FramekitMessage.SPECIAL, (String) listeArgs.elementAt(2), un);
						this.api.printHistory((String) listeArgs.elementAt(2));
					}
					
					
					if ((listeArgs.firstElement().equals("DF"))) {
						System.out.println("THREAD LISTENER DF recu");
						
						FramekitThreadSpeaker speaker = this.api.getCurrentSpeaker();
						System.out.println("THREAD LISTENER: debut envoi model");
						speaker.sendModel();
						System.out.println("THREAD LISTENER: fin envoi model");	
					} 
					
					if ((listeArgs.firstElement().equals("DR"))) {
						System.out.println("THREAD LISTENER DR recu");
					}
					
					if ((listeArgs.firstElement().equals("RQ"))) {
						System.out.println("THREAD LISTENER RQ recu");
					}
					
					if ((listeArgs.firstElement().equals("DE"))) {
						System.out.println("THREAD LISTENER DE recu");
						errorSyntaxCheck += listeArgs.get(1) + " ";
						System.out.println("##" + errorSyntaxCheck);
					}
	
					if ((listeArgs.firstElement().equals("RT"))) {
						System.out.println("THREAD LISTENER RT recu");
						errorSyntaxCheck += listeArgs.get(1) + " ";
						System.out.println("##" + errorSyntaxCheck);
					}
	
					if ((listeArgs.firstElement().equals("RO"))) { //ou ME ???
						System.out.println("THREAD LISTENER RO recu");
						nbNodeError++;
						errorId += listeArgs.get(1) + ",";
						System.out.println("##" + errorSyntaxCheck);
						System.out.println("##" + errorId);
					}
	
					if ((listeArgs.firstElement().equals("ME"))) {
						System.out.println("THREAD LISTENER ME recu");
					}
					
					
					if ((listeArgs.firstElement().equals("FE"))) {
						System.out.println("THREAD LISTENER FE recu");
						
						String listeId = String.valueOf(nbNodeError) + "," + errorId;
											
						System.out.println("------------------- ENVOI DE LA LISTE DES ID : " + listeId );
						
						this.api.sendMessageUI(FramekitMessage.ERRORSYNTAXCHECK, errorSyntaxCheck, 1);
						this.api.sendMessageUI(FramekitMessage.LISTEID, listeId, 1);
						
						errorId = "";
						errorSyntaxCheck = "";
						nbNodeError = 0;
					}
					
					
					if ((listeArgs.firstElement().equals("SS"))) {
						this.api.setSuspendCurrentSession(true);
						System.out.println("THREAD LISTENER SS recu");
					}
					
					if ((listeArgs.firstElement().equals("RS"))) {
						this.api.setResumedSession(true);
						System.out.println("THREAD LISTENER RS recu");
					}
					
					if ((listeArgs.firstElement().equals("FS"))) {
						this.api.setClosedSession(true);
						System.out.println("THREAD LISTENER FS recu");
					}
					
					if ((listeArgs.firstElement().equals("DC"))) {
						vectorDialog = new Vector();
						vectorDialog.add(listeArgs);
						System.out.println("THREAD LISTENER DC recu = \'" + listeArgs + "\'");
					}					
					if ((listeArgs.firstElement().equals("CE"))) {
						System.out.println("THREAD LISTENER CE recu = \'" + listeArgs + "\'");
						vectorDialog.add(listeArgs);
					}
					if (listeArgs.firstElement().equals("DS")) {
						System.out.println("THREAD LISTENER DS recu = \'" + listeArgs + "\'");
						vectorDialog.add(listeArgs);
					}
					
					if ((listeArgs.firstElement().equals("DB"))) {
						System.out.println("THREAD LISTENER DB recu");
						leModel = new Vector();		
					}
					
					if ((listeArgs.firstElement()).equals("CN") 
							|| (listeArgs.firstElement()).equals("CB") 
							|| (listeArgs.firstElement()).equals("CA") 
							|| (listeArgs.firstElement()).equals("CT") 
							|| (listeArgs.firstElement()).equals("CM") 
							|| (listeArgs.firstElement()).equals("PO") 
							|| (listeArgs.firstElement()).equals("PT") 
							|| (listeArgs.firstElement()).equals("PI")) {
						
						leModel.add(commandeRecue);
					}
					
					// trouver comment recupere le formalisme du model transmit
					if ((listeArgs.firstElement().equals("FB"))) {
						System.out.println("THREAD LISTENER FB recu");
						Model m = new Model("ReachabilityGraph", leModel);
						this.api.setModel(m);
					}
					
					if (listeArgs.firstElement().equals("FF")) {
						System.out.println("THREAD LISTENER FF recu");
						vectorDialog.add(listeArgs);
						WindowedDialogue wDialog = this.translater.getWindowedDialogue(vectorDialog);
						vectorDialog = new Vector();
						this.api.setDialogUI((Dialogue) wDialog);
						System.out.println("THREAD LISTENER Boite de dialogue creer et envoyer a l'UI");
					}						
					
					if ((listeArgs.firstElement().equals("AD"))) {
						int un = 0;
						try {
							un = Integer.parseInt((String) listeArgs.elementAt(1));
						} catch (Exception e) {
							System.out.println("exception parseInt");
							
						}
						this.api.displayDialogUI(un);
						System.out.println("THREAD LISTENER AD recu");
					}
					
					if ((listeArgs.firstElement().equals("HD"))) {
						int un = 0;
						try {
							un = Integer.parseInt((String) listeArgs.elementAt(1));
						} catch (Exception e) {
							System.out.println("exception parseInt");
							
						}
						this.api.hideDialogUI(un);
						System.out.println("THREAD LISTENER HD recu");
					}
					
					if ((listeArgs.firstElement().equals("DG"))) {
						int un = 0;
						try {
							un = Integer.parseInt((String) listeArgs.elementAt(1));
						} catch (Exception e) {
							System.out.println("exception parseInt");
							
						}
						this.api.destroyDialogUI(un);
						System.out.println("THREAD LISTENER DG recu");
					}
				}
				
//				System.out.println("loop THREAD LISTENER : " + commandeRecue);	
				
			} catch (Exception e) {
				e.printStackTrace();
				//	api.criticalClose();
			}
			}
		System.out.println("WHILE TERMINATED #############");
		this.api.cnxClosed(1, "Deconnexion ...", 1);
	}	
}
