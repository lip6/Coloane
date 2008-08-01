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
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.api.camiObject.menu.IQuestion;
import fr.lip6.move.coloane.api.camiObject.menu.SubMenu;
import fr.lip6.move.coloane.api.camiObject.ReceptMessage;
import fr.lip6.move.coloane.api.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
	
import java.util.Map;
import java.util.logging.Logger;
}

@members {

/* L'objet pour la synchronisation */
Map<String, Object> hash;
	
/* Le gestionnaire de session */
ISessionController sessionController;

/* Constructeur du parser */
/* Ce cosntructeur est n�cessaire pour r�cup�rer l'objet de synchronisation */
public CamiParser(TokenStream input, Map<String, Object> hash) {
	this(input);
	this.hash = hash;
	this.sessionController = SessionController.getInstance();
}

/** Le logger des evenements */
private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

/* Divers objets utiles pour les notifications */
private IConnectionInfo connectionInfo;

}
/* --------------------------------------- */
/* Selecteur principal                     */
/* --------------------------------------- */

main 
	/* Pour la connexion */
	:	open_communication
	|	open_connection
	|	close_connection
	/* Pour les sessions */
	|	open_session
	|	close_session
	|	suspend_session
	|	resume_session
	/* Pour les services */
	|	receive_services
	|	state_service
	/* Messages sp�ciaux */
	|	message_to_user
	/* Pour les r�sultats */
	|	receive_results
	/* Les messages KO */
	|	ko_message
	;

/* --------------------------------------- */
/* Gestion de la connexion                 */
/* --------------------------------------- */

/* Ouverture de la communication avec la plte-forme */
open_communication
	:
	'SC(' fkName=CAMI_STRING ')' {
		LOGGER.finest("Creation de l'objet de connexion");
		connectionInfo = new ConnectionInfo($fkName.text);
		synchronized (hash) {
			hash.notify();
		}
	}
	;

/* Ouverture de la connexion */
open_connection
	:
	'OC(' major=NUMBER ',' minor=NUMBER ')' {
		((ConnectionInfo) connectionInfo).setFkMajor($major.text);
		((ConnectionInfo) connectionInfo).setFkMinor($minor.text);
		((ConnectionObservable) hash.get("IConnection")).notifyObservers(connectionInfo);
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
	@init { List<String> sessionArgs = new ArrayList<String>(); }
	:
	'OS(' session_name=CAMI_STRING ')'
	'TD()'
	'FA()'
	interlocutor_table {
		ISessionInfo sessionInfo = CamiObjectBuilder.buildSessionInfo(sessionArgs);
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

/* R�ception des services */
receive_services
	scope { 
		List<ISubMenu> roots;
		List<IUpdateMenu> updates;
		List<IService> services;
	}
	@init { 
		List<ISubMenu> roots = new ArrayList<ISubMenu>();
		List<IUpdateMenu> updates = new ArrayList<IUpdateMenu>();
		List<IService> services = new ArrayList<IService>();
	}
	:
	receive_services_group+
	state_service*
	'QQ(3)' {
		LOGGER.finest("Fin de la transmission des services");
		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(roots, updates, services);
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

		// Ajout � la liste des services
		IService service = CamiObjectBuilder.buildService($receive_services_group::root, question);
		$receive_services::services.add(service);
	}
	;

/* Description de l'�tat d'un services */
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
			
			// Ajout � la liste des updates
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
	'KO(1' mess=CAMI_STRING ',' severity=number ')' {
		// TODO: Differencier les KOs (1 2 ou 3)
		// TODO: Traiter le dernier argument du KO
		LOGGER.warning("Reception d'un message asynchrone");
		((BrutalInterruptObservable) hash.get("IBrutalInterrupt")).notifyObservers($mess.text);
	}
	;

/* R�ception d'un message utilisateur */
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

/* Message sp�cial */
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
/* Gestion des r�sultats                   */
/* --------------------------------------- */

/* R�ception des r�sultats */
receive_results
	:	
	'DR()'
	'RQ(' root_name=CAMI_STRING ',' service_name=CAMI_STRING ',' deprecated=number ')' {}
	(	state_service {}
	|	special_message {}
	|	warning_message {}
	|	result {}
	)*
	;

/* Description d'un r�sultats */
result	
	:	
	'DE(' set_name=CAMI_STRING ',' set_type=number ')'
	( result_body {} )+
	'FE()' {}
	;

/* Corps d'un r�sultat */
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

/* R�sultat textuel */
textual_result
	:	
	'RT(' text=CAMI_STRING ')' {}
	;

/* Changement d'un attribut */
attribute_change
	:	
	'WE(' id=number ',' attribute_name=CAMI_STRING ',' new_value=CAMI_STRING ')' {}
	;

/* Mise en valeur d'un attribut */
attribute_outline
	:	
	'MT(' id=number ',' attribute_name=CAMI_STRING ',' begin=number? ',' end=number? ')' {}
	;

/* D�signation d'un objet */
object_designation
	:
	'RO(' id=number ')' {}		
	;

/* Mise en valeur d'un objet */
object_outline
	:
	'ME(' id=number ')' {}
	;

/* Cr�ation d'un objet */
object_creation
	:	node
	|	box
	|	arc
	|	attribute
	;

/* Suppression d'un objet */
object_deletion
	:	'SU(' id=number ')' {}
 	|	'SI(' page_id=number ',' id=number ')' {}
 	;
 	
/* --------------------------------------- */
/* D�finition d'un mod�le		   */
/* --------------------------------------- */

/* En-t�te d'un mod�le */
model_definition
	:	
	'DB()'
	( syntactic | aestetic )
	'FB()'
	;

/* D�finition des composants */
syntactic
	:
	node | box | arc | attribute
	;

/* Description d'un noeud */
node
	:	
	'CN(' CAMI_STRING ',' number ')'
	;

/* Description d'une boite */
box	:
	'CB(' CAMI_STRING ',' number ',' number ')'
	;

/* Description d'un arc */
arc	:
	'CA(' CAMI_STRING ',' number ',' number ',' number ')'
	;

/* Description d'un attribut */
attribute
	:	'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')'
	|	'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')'
	;

/* Description esth�tique */
aestetic
	:
	object_position | text_position | intermediary_point
	;

/* Position d'un objet */
object_position
	:	'PO(' id=number ',' h_distance=number ',' v_distance=number ')'
	|	'pO(' id=number ',' h_distance=number ',' v_distance=number ')'
	|	'PO(-1,' id=number ',' left=number ',' right=number ',' top=number ',' bottom=number')'
	;

/* Position d'un attribut */
text_position
	:	
	'PT(' id=number ',' name_attr=CAMI_STRING ',' h_distance=number ',' v_distance=number ')'
	;

/* Position des points d'inflexion */
intermediary_point
	:	
	'PI(' number ',' number ',' number ')'
	;
	
/* --------------------------------------- */
/* D�finition d'une boite de dialogue      */
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
	'CE(' dialog_id=number ',' dialog_type=number ',' 
	buttons_type=number ','  window_title=CAMI_STRING ',' 
	help=CAMI_STRING ',' title_or_message=CAMI_STRING ',' 
	input_type=number ',' line_type=number ',' 
	default_value=CAMI_STRING? ')' {
	
		ce.add($dialog_id.text);
		ce.add($dialog_type.text);
		ce.add($buttons_type.text);
		ce.add($window_title.text);
		ce.add($help.text);
		ce.add($title_or_message.text);
		ce.add($input_type.text);
		ce.add($line_type.text);
		if ($default_value != null) { ce.add($default_value.text); } else { ce.Add(null); }
		
		// Construction de l'objet boite de dialogue
		IDialog dialog = CamiObjectBuilder.buildDialog(ce);
		// Ajout de la boite de dialogue � la liste
		$dialog_definition::dialogs.add(dialog};
	}
	;

/* ??? */
next_dialog
	:
	'DS(' dialog_id=number ',' line=CAMI_STRING ')' {}
	;

/* Affiche la boite de dialogue */
display_dialog
	:
	'AD(' dialog_id=number ')' {}
	;

/* Cache la boite de dialogue */
hide_dialog
	:
	'HD(' dialog_id=number ')' {}
	;

/* Destruction de la boite de dialogue */	
destroy_dialog
	:
	'DG(' dialog_id=number ')' {}
	;

// Deprecated
interactive_response
	:
	'MI(' number ',' number ')'
	;

/* --------------------------------------- */
/* Divers objets                           */
/* --------------------------------------- */

number
	returns [int value]
	:
	NUMBER {value = Integer.parseInt($NUMBER.text);}
	;

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

EOF	
	:
	{skip();}
	;

