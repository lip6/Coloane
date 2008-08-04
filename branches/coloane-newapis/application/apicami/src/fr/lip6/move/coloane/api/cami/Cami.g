grammar Cami;

options {language=Java;}

@lexer::header {
package fr.lip6.move.coloane.api.cami;
}

@parser::header {
package fr.lip6.move.coloane.api.cami;
	
import fr.lip6.move.coloane.api.camiObject.ConnectionInfo;
import fr.lip6.move.coloane.api.interfaces.ISessionController;
import fr.lip6.move.coloane.api.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.api.observables.DisconnectObservable;
import fr.lip6.move.coloane.api.observables.ConnectionObservable;
import fr.lip6.move.coloane.api.session.SessionController;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.api.observables.ReceptMenuObservable;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
	
import java.util.Map;
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
	|	receive_services
	|	state_service
	/* Messages spéciaux */
	|	message_to_user
	/* Pour les résultats */
	|	receive_results
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
	'OS(' session_name=CAMI_STRING ')'
	'TD()'
	'FA()'
	interlocutor_table {
		ISessionInfo sessionInfo = CamiObjectBuilder.buildSessionInfo($open_session::sessionArgs);
		sessionController.notifyReceptSessionInfo(sessionInfo);
	}
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
	scope { 
		List<ISubMenu> roots;
		List<IUpdateMenu> updates;
		List<IService> services;
	}
	@init { 
		$receive_services::roots = new ArrayList<ISubMenu>();
		$receive_services::updates = new ArrayList<IUpdateMenu>();
		$receive_services::services = new ArrayList<IService>();
	}
	:
	receive_services_group+
	state_service*
	'QQ(3)' {
		LOGGER.finest("Fin de la transmission des services");
		((ReceptMenuObservable) hash.get("ISession")).notifyObservers($receive_services::roots, $receive_services::updates, $receive_services::services);
	}
	;

receive_services_group
	scope { ISubMenu root; }
	:	
	'DQ()'
	root_description
	service_description*
	'FQ()' {
		LOGGER.finest("Fin de la reception du groupe de services");
		$receive_services::roots.add($receive_services_group::root);
	}
	(
	'VQ(' root_name=CAMI_STRING ')' {}
	)?
	;

/* Description du menu root */
root_description
	:
	'CQ(' name=CAMI_STRING ',' question_type=NUMBER ',' question_behavior=NUMBER')' {
		$receive_services_group::root = CamiObjectBuilder.buildRootMenu($name.text, $question_type.text, $question_behavior.text);
	}
	;

/* Description d'un service (question) */
service_description
	@init { List<String> aq = new ArrayList<String>(); }
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ','
	question_type=NUMBER? ',' question_behavior=NUMBER? ','
	set_item=NUMBER? ',' dialog=NUMBER? ','
	stop_authorized=NUMBER? ',' output_formalism=CAMI_STRING ',' active_state=NUMBER? ')' {
	
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
		IQuestion question = CamiObjectBuilder.buildQuestion(aq);

		// Ajout au menu root existant
		((SubMenu) $receive_services_group::root).addQuestion(question);

		// Ajout à la liste des services
		IService service = CamiObjectBuilder.buildService($receive_services_group::root, question);
		$receive_services::services.add(service);
	}
	;

/* Description de l'état d'un services */
state_service
	@init { List<String> tq = new ArrayList<String>(); }
	:
	'TQ(' root_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=NUMBER ',' message=CAMI_STRING? ')' {
		if($state.text.equals("7") || $state.text.equals("8")) {
			LOGGER.finest("Reception d'un etat de service");
			tq.add($root_name.text);
			tq.add($question_name.text);
			tq.add($state.text);
			tq.add($message.text);
			
			// Ajout à la liste des updates
			IUpdateMenu update = CamiObjectBuilder.buildUpdate(tq);
			$receive_services::updates.add(update);
		} else {
			// TODO: Lever une exception ?
			LOGGER.warning("Reception d'un etat de service non conforme -> " + $state.text);
		}
	}
	;

/* --------------------------------------- */
/* Message aux utilisateurs                */
/* --------------------------------------- */

ko_message
	:
	'KO(1' mess=CAMI_STRING ',' severity=NUMBER ')' {
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
			((BrutalInterruptObservable) hash.get("IBrutalInterrupt")).notifyObservers($message.text);  
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

/* Réception des résultats */
receive_results
	:	
	'DR()'
	'RQ(' root_name=CAMI_STRING ',' service_name=CAMI_STRING ',' deprecated=NUMBER ')' {}
	(	state_service {}
	|	special_message {}
	|	warning_message {}
	|	result {}
	)*
	;

/* Description d'un résultats */
result	
	:	
	'DE(' set_name=CAMI_STRING ',' set_type=NUMBER ')'
	( result_body {} )+
	'FE()' {}
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
	'RT(' text=CAMI_STRING ')' {}
	;

/* Changement d'un attribut */
attribute_change
	:	
	'WE(' id=NUMBER ',' attribute_name=CAMI_STRING ',' new_value=CAMI_STRING ')' {}
	;

/* Mise en valeur d'un attribut */
attribute_outline
	:	
	'MT(' id=NUMBER ',' attribute_name=CAMI_STRING ',' begin=NUMBER? ',' end=NUMBER? ')' {}
	;

/* Désignation d'un objet */
object_designation
	:
	'RO(' id=NUMBER ')' {}		
	;

/* Mise en valeur d'un objet */
object_outline
	:
	'ME(' id=NUMBER ')' {}
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
	scope { List<IDialog> dialogs; }
	@init { List<IDialog> dialogs = new ArrayList<IDialog>(); }
	:	
	'DC()'
	dialog_creation
	(
	next_dialog { } 
	)+
	'FF()' {}
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
		$dialog_definition::dialogs.add(dialog);
	}
	;

/* ??? */
next_dialog
	:
	'DS(' dialog_id=NUMBER ',' line=CAMI_STRING ')' {}
	;

/* Affiche la boite de dialogue */
display_dialog
	:
	'AD(' dialog_id=NUMBER ')' {}
	;

/* Cache la boite de dialogue */
hide_dialog
	:
	'HD(' dialog_id=NUMBER ')' {}
	;

/* Destruction de la boite de dialogue */	
destroy_dialog
	:
	'DG(' dialog_id=NUMBER ')' {}
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
	
NUMBER
	:
	'0'..'9'+
	;

fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;


