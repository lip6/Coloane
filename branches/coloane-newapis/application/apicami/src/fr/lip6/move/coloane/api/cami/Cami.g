grammar Cami;

options {language=Java;}

@lexer::header {
package fr.lip6.move.coloane.api.cami;
}

@parser::header {
package fr.lip6.move.coloane.api.cami;
	
import fr.lip6.move.coloane.api.camiObject.ConnectionInfo;
import fr.lip6.move.coloane.api.observables.ReceptDialogObservable;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.api.observables.ReceptServiceStateObservable;
import fr.lip6.move.coloane.api.camiObject.ReceptServiceState;
import fr.lip6.move.coloane.api.observables.DisconnectObservable;
import fr.lip6.move.coloane.api.observables.ConnectionObservable;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.api.session.SessionController;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.coloane.api.camiObject.Result;
import fr.lip6.move.coloane.api.camiObject.SubResult;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.api.observables.ReceptResultObservable;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.camiObject.Dialog;
import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
	
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;
}

@members {

/* L'objet pour la synchronisation */
Map<String, Object> hash;
	
/* Le gestionnaire de session */
ISessionController sessionController;

/* Indique tous les observers disponibles */
public void setObservers(Map<String, Object> hash) {
	this.hash = hash;
	this.sessionController = SessionController.getInstance();
}

/** Le logger des evenements */
private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

/** L'objet résultat : STATIQUE pour pouvoir le remplir en plusieurs passes */
private static IResult result;

}
/* --------------------------------------- */
/* Selecteur principal                     */
/* --------------------------------------- */

main 
	/* Pour la connexion */
	:	open_communication
	|	close_connection
	/* Pour les sessions */
	|	open_session
	|	close_session
	|	suspend_session
	|	resume_session
	/* Pour les services */
	|	invalid_model
	/* Messages spéciaux */
	|	message_to_user
	/* Pour les résultats */
	|	ask_for_model
	|	receive_results
	|	inside_result
	/* Les messages KO */
	|	ko_message
	;

/* --------------------------------------- */
/* Gestion de la connexion                 */
/* --------------------------------------- */

/* Ouverture de la communication avec la plte-forme */
open_communication
	scope { IConnectionInfo connectionInfo;	}
	:
	'SC('fkName=CAMI_STRING ')' {
		LOGGER.finest("Creation de l'objet de connexion");
		$open_communication::connectionInfo = new ConnectionInfo($fkName.text);
	}
	open_connection {}
	;

/* Ouverture de la connexion */
open_connection
	:
	'OC(' major=NUMBER ',' minor=NUMBER ')' {
		((ConnectionInfo) $open_communication::connectionInfo).setFkMajor($major.text);
		((ConnectionInfo) $open_communication::connectionInfo).setFkMinor($minor.text);
		((ConnectionObservable) hash.get("IConnection")).notifyObservers($open_communication::connectionInfo);
	}
	;

/* Fermeture de la connexion */
close_connection
	:
	'FC()' {
		((DisconnectObservable) hash.get("IDisconnect")).notifyObservers();
	}
	;

/* --------------------------------------- */
/* Gestion des sessions                    */
/* --------------------------------------- */

/* Ouverture de session */
open_session
	scope { List<String> sessionArgs; }
	@init { $open_session::sessionArgs = new ArrayList<String>(); }
	:
	message_to_user*
	'OS(' session_name=CAMI_STRING ')'
	'TD()'
	'FA()'
	interlocutor_table {
		ISessionInfo sessionInfo = CamiObjectBuilder.buildSessionInfo($open_session::sessionArgs);
		sessionController.notifyReceptSessionInfo(sessionInfo);
	}
	message_to_user*
	receive_services
	;

/* @deprecated */
interlocutor_table
	:	
	'TL()'
	body_table+
	'FL()'
	;

/* @deprecated */	
body_table
	:
	'VI(' service_name=CAMI_STRING ',' about_service=CAMI_STRING ',' deprecated=NUMBER ',' new_model=NUMBER ')' {
		$open_session::sessionArgs.add($service_name.text);
		$open_session::sessionArgs.add($about_service.text);
		$open_session::sessionArgs.add($deprecated.text);
		$open_session::sessionArgs.add($new_model.text);
	}
	;

/* Suspension de la session */	
suspend_session
	:
	'SS()' {
		LOGGER.finest("Reception d'un acquittement de suspension de session");
		sessionController.notifyEndSuspendSession();
	}
	;

/* Reprise de la session */
resume_session
	:
	'RS(' session_name=CAMI_STRING ')' {
		LOGGER.finest("Reception d'un acquittement de reprise de session");
		sessionController.notifyEndResumeSession($session_name.text);
	}
	;
	
/* Fermeture de la session */
close_session
	:
	'FS()' {
		LOGGER.finest("Reception d'un acquittement de fermeture de session");
		sessionController.notifyEndCloseSession();
	}
	;

/* --------------------------------------- */
/* Gestion des services                    */
/* --------------------------------------- */

/* Réception des services */
receive_services
	scope { List<IService> services; }
	@init { 
		List<ISubMenu> roots = new ArrayList<ISubMenu>();
		List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>();
	}
	:
	( receive_services_group { roots.add($receive_services_group.builtRoot); } )+
	( state_service { updates.add($state_service.builtUpdate); } )+
	'QQ(3)' {
		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(roots, updates);
		sessionController.notifyEndOpenSession();
	}
	;

receive_services_group
	returns [ISubMenu builtRoot]
	@init { LOGGER.finest("Reception d'une liste de services (Groupe)"); }
	:	
	'DQ()'
	root_description { builtRoot = $root_description.root; } 
	( service_description { ((SubMenu) builtRoot).addQuestion($service_description.question); } )+
	'FQ()' { LOGGER.finest("Fin de la reception du groupe de services"); }
	( 'VQ(' root_name=CAMI_STRING ')' )?
	;

/* Description du menu root */
root_description
	returns [ISubMenu root]
	:
	'CQ(' name=CAMI_STRING ',' question_type=NUMBER ',' question_behavior=NUMBER')' {
		root = CamiObjectBuilder.buildRootMenu($name.text, $question_type.text, $question_behavior.text);
	}
	;

/* Description d'un service (question) */
service_description
	returns [IQuestion question]
	@init { List<String> aq = new ArrayList<String>(); }
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ','
	question_type=NUMBER? ',' question_behavior=NUMBER? ','
	set_item=NUMBER? ',' dialog=NUMBER? ','
	stop_authorized=NUMBER? ',' output_formalism=CAMI_STRING? ',' active_state=NUMBER? ')' {
	
		LOGGER.finest("Reception d'une question " + $entry_name.text);

		aq.add($parent_menu.text);
		aq.add($entry_name.text);
		if ($question_type != null) { aq.add($question_type.text); } else { aq.add(null); }
		if ($question_behavior != null) { aq.add($question_behavior.text); } else { aq.add(null); }
		if ($set_item != null) { aq.add($set_item.text); } else { aq.add(null); }
		if ($dialog != null) { aq.add($dialog.text); } else { aq.add(null); }
		if ($stop_authorized != null) { aq.add($stop_authorized.text); } else { aq.add(null); }
		if ($output_formalism != null) { aq.add($output_formalism.text); } else { aq.add(null); }
       		if ($active_state != null) { aq.add($active_state.text); } else { aq.add(null); }

		// Construction de la question
		question = CamiObjectBuilder.buildQuestion(aq);
	}
	;

/* Description de l'état d'un services */
state_service
	returns [IUpdateMenu builtUpdate]
	@init { List<String> tq = new ArrayList<String>(); }
	:
	'TQ(' root_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=NUMBER ',' message=CAMI_STRING? ')' {
		if($state.text.equals("7") || $state.text.equals("8")) {
			LOGGER.finest("Reception d'un etat de service");
			tq.add($root_name.text);
			tq.add($question_name.text);
			tq.add($state.text);
			if ($message != null) { tq.add($message.text); } else { tq.add(""); }
			
			// Construction de la mise a jour
			builtUpdate = CamiObjectBuilder.buildUpdate(tq);
		} else if($state.text.equals("2")) { 
			if($message != null) { 
				LOGGER.finest("Reception d'un TQ 2"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,2,$message.text); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 2"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,2,null); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 

		if($state.text.equals("3")) { 
			if($message != null) { 
				LOGGER.finest("Reception d'un TQ 3"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,3,$message.text); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 3"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,3,null); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 
    
		if($state.text.equals("4")) { 
			if($message != null) { 
				LOGGER.finest("Reception d'un TQ 4"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,4,$message.text); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 4"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,4,null); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 
    
		if($state.text.equals("5")) { 
			if($message != null) { 
				LOGGER.finest("Reception d'un TQ 5"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,5,$message.text); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 5"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,5,null); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		} 

		if($state.text.equals("6")) {
			if($message != null) { 
				LOGGER.finest("Reception d'un TQ 6"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,6,$message.text); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} else { 
				LOGGER.finest("Reception d'un TQ 6"); 
				IReceptServiceState msg = (IReceptServiceState)new ReceptServiceState($question_name.text,6,null); 
				((ReceptServiceStateObservable)hash.get("IReceptServiceState")).notifyObservers(msg); 
			} 
		}
	}
	;

invalid_model
	@init { List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>(); }
	:	
	(state_service { updates.add($state_service.builtUpdate); } )*
	'QQ(2)' {
		LOGGER.finest("Fin de la mise a jour (status=2)");
		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(null, updates); 
		sessionController.notifyEndInvalidModel();
	}
	;

/* --------------------------------------- */
/* Message aux utilisateurs                */
/* --------------------------------------- */

ko_message
	:
	'KO(' NUMBER ',' mess=CAMI_STRING ',' severity=NUMBER ')' {
		// TODO: Differencier les KOs (1 2 ou 3)
		// TODO: Traiter le dernier argument du KO
		LOGGER.warning("Reception d'un message asynchrone");
		((BrutalInterruptObservable) hash.get("IBrutalInterrupt")).notifyObservers($mess.text);
	}
	;

/* Réception d'un message utilisateur */
message_to_user
	:	trace_message
	|	warning_message
	|	special_message
	;

/* Message de trace */
trace_message
	:
	'TR(' message=CAMI_STRING ')' {
		LOGGER.finest("Reception d'un message de trace");
		IReceptMessage msg = (IReceptMessage) new ReceptMessage(4, $message.text);
		((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg);
	}
	;

/* Message d'avertissement */
warning_message
	:
	'WN(' message=CAMI_STRING ')' {
		LOGGER.finest("Reception d'un message de trace");
		IReceptMessage msg = (IReceptMessage) new ReceptMessage(2, $message.text);
		((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg);
	}
	;

/* Message spécial */
special_message
	:
	'MO(' type=NUMBER ',' message=CAMI_STRING ')' {
		if($type.text.equals("1")) { 
			LOGGER.finest("Reception d'un message de l'administrateur"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(1,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}
    
		if($type.text.equals("2")) {
			//TODO :Verifier qu'il faut traiter ce message comme un KO
			LOGGER.finest("Reception d'un message court et urgent");
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(2,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}

		if($type.text.equals("3")) { 
			LOGGER.finest("Reception d'un message copyright"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(3,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}
    
		if($type.text.equals("4")) { 
			LOGGER.finest("Reception d'un message a propos des statistiques d'execution"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(4,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}
	}
	;

/* --------------------------------------- */
/* Gestion des résultats                   */
/* --------------------------------------- */

ask_for_model
	:
	state_service*
	'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' {
		sessionController.notifyWaitingForModel();
	}
	;

/* Réception des résultats */
receive_results
	:	
	'DR()'
	'RQ(' root_name=CAMI_STRING ',' service_name=CAMI_STRING ',' deprecated=NUMBER ')' {
		result = CamiObjectBuilder.buildResult($root_name.text, $service_name.text);
	}
	inside_result
	;

inside_result
	scope { Map<Integer, IDialog> dialogs; }
	@init { 
		List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>(); 
		LOGGER.finest("Reception d'un ensemble d'elements de resultats");
	}
	:	
	(	state_service { updates.add($state_service.builtUpdate); }
	|	special_message
	|	warning_message
	|	dialog_definition
	|	dialog_display
	|	dialog_destroy
	|	result { ((Result) result).addSubResult($result.builtResult); }
	)+ 
	(
	'FR(' NUMBER ')' {
		LOGGER.finest("Transmission des resultats");
		((ReceptResultObservable)hash.get("IReceptResult")).notifyObservers(result);
		LOGGER.finest("Transmission des mises a jour des services");
		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(null, updates); 
		sessionController.notifyEndResult();
	}
	)?
	;

/* Description d'un résultats */
result
	returns[ISubResult builtResult]
	scope { ISubResult current; }
	:	
	'DE(' set_name=CAMI_STRING ',' set_type=NUMBER ')' {
		LOGGER.finest("Debut du parcours de l'ensemble de resultats");
		$result::current = CamiObjectBuilder.buildSubResult($set_name.text, $set_type.text);
	}
	( result_body {} )+
	'FE()' {
		LOGGER.finest("Fin du parcours de l'ensemble de resultats");
		builtResult = $result::current;
	}
	;

/* Corps d'un résultat */
result_body
	:	result {}
	|	textual_result {}
	|	attribute_change {}
	|	object_designation {}
	|	object_outline {}
	|	attribute_outline {}
	|	object_creation {}
	|	object_deletion {}
	;

/* Résultat textuel */
textual_result
	:	
	'RT(' text=CAMI_STRING ')' { ((SubResult) $result::current).addTextualResult($text.text); }
	;

/* Changement d'un attribut */
attribute_change
	:	
	'WE(' id=NUMBER ',' attribute_name=CAMI_STRING ',' new_value=CAMI_STRING ')' {}
	;

/* Mise en valeur d'un attribut */
attribute_outline
	:	
	'MT(' id=NUMBER ',' attribute_name=CAMI_STRING ',' begin=NUMBER? ',' end=NUMBER? ')' {
		((SubResult) $result::current).addAttributeOutline(Integer.parseInt($id.text), $attribute_name.text);
	}
	;

/* Désignation d'un objet */
object_designation
	:
	'RO(' id=NUMBER ')' { ((SubResult) $result::current).addObjectDesignation(Integer.parseInt($id.text)); }		
	;

/* Mise en valeur d'un objet */
object_outline
	:
	'ME(' id=NUMBER ')' { ((SubResult) $result::current).addObjectOutline(Integer.parseInt($id.text)); }
	;

/* Création d'un objet */
object_creation
	:	node
	|	box
	|	arc
	|	attribute
	;

/* Suppression d'un objet */
object_deletion
	:	'SU(' id=NUMBER ')' {}
 	|	'SI(' page_id=NUMBER ',' id=NUMBER ')' {}
 	;
 	
/* --------------------------------------- */
/* Définition d'un modèle		   */
/* --------------------------------------- */

/* En-tête d'un modèle */
model_definition
	scope { IGraph model; }
	:	
	'DB()'
	( syntactic | aestetic )
	'FB()'
	;

/* Définition des composants */
syntactic
	:
	node | box | arc | attribute
	;

/* Description d'un noeud */
node
	:	
	'CN(' CAMI_STRING ',' NUMBER ')'
	;

/* Description d'une boite */
box	:
	'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
	;

/* Description d'un arc */
arc	:
	'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
	;

/* Description d'un attribut */
attribute
	:	'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
	|	'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
	;

/* Description esthétique */
aestetic
	:
	object_position | text_position | intermediary_point
	;

/* Position d'un objet */
object_position
	:	'PO(' id=NUMBER ',' h_distance=NUMBER ',' v_distance=NUMBER ')'
	|	'pO(' id=NUMBER ',' h_distance=NUMBER ',' v_distance=NUMBER ')'
	|	'PO(-1,' id=NUMBER ',' left=NUMBER ',' right=NUMBER ',' top=NUMBER ',' bottom=NUMBER')'
	;

/* Position d'un attribut */
text_position
	:	
	'PT(' id=NUMBER ',' name_attr=CAMI_STRING ',' h_distance=NUMBER ',' v_distance=NUMBER ')'
	;

/* Position des points d'inflexion */
intermediary_point
	:	
	'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
	;
	
/* --------------------------------------- */
/* Définition d'une boite de dialogue      */
/* --------------------------------------- */

/* Description d'une boite de dialogue */
dialog_definition
	@init { $inside_result::dialogs = new HashMap<Integer, IDialog>(); }
	:
	'DC()' { LOGGER.finest("Reception d'une definition d'une boite de dialogue"); }
	dialog_creation
	( next_dialog )*
	'FF()' { LOGGER.finest("Fin de reception des boites de dialogue"); }
	;

/* Corps de la boite de dialogue */
dialog_creation
	@init { List<String> ce = new ArrayList<String>();}
	:
	'CE(' dialog_id=NUMBER ',' dialog_type=NUMBER ',' 
	buttons_type=NUMBER ','  window_title=CAMI_STRING ',' 
	help=CAMI_STRING ',' title_or_message=CAMI_STRING ',' 
	input_type=NUMBER ',' line_type=NUMBER ',' 
	default_value=CAMI_STRING? ')' {
	
		ce.add($dialog_id.text);
		ce.add($dialog_type.text);
		ce.add($buttons_type.text);
		ce.add($window_title.text);
		ce.add($help.text);
		ce.add($title_or_message.text);
		ce.add($input_type.text);
		ce.add($line_type.text);
		if ($default_value != null) { ce.add($default_value.text); } else { ce.add(null); }
		
		// Construction de l'objet boite de dialogue
		IDialog dialog = CamiObjectBuilder.buildDialog(ce);
		// Ajout de la boite de dialogue à la liste
		$inside_result::dialogs.put(Integer.parseInt($dialog_id.text), dialog);
	}
	;

/* ??? */
next_dialog
	:
	'DS(' dialog_id=NUMBER ',' line=CAMI_STRING ')' {
		LOGGER.finest("Ajout d'une ligne a la boite de dialogue : " + $dialog_id.text);
		((Dialog) $inside_result::dialogs.get(Integer.parseInt($dialog_id.text))).addLine($line.text);
	}
	;

/* Affiche la boite de dialogue */
dialog_display
	:
	'AD(' dialog_id=NUMBER ')' {
		LOGGER.finest("Affichage de la boite de dialogue " + $dialog_id.text);
		((ReceptDialogObservable) hash.get("IReceptDialog")).notifyObservers($inside_result::dialogs.get(Integer.parseInt($dialog_id.text)));
		sessionController.notifyReceptDialog($inside_result::dialogs.get(Integer.parseInt($dialog_id.text)));
	}
	;

/* Cache la boite de dialogue */
hide_dialog
	:
	'HD(' dialog_id=NUMBER ')' {}
	;

/* Destruction de la boite de dialogue */	
dialog_destroy
	:
	'DG(' dialog_id=NUMBER ')' { LOGGER.finest("Destruction de la boite de dialogue " + $dialog_id.text); }
	;

// Deprecated
interactive_response
	:
	'MI(' NUMBER ',' NUMBER ')'
	;

/* --------------------------------------- */
/* Divers objets                           */
/* --------------------------------------- */

CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':'
	value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
NUMBER	:	('0'..'9')+;
NEWLINE :	( '\r'?'\n' )+ {skip();};

fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;
