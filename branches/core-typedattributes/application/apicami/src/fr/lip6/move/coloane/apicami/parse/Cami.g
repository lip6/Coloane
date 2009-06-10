grammar Cami;

options {language=Java;}

@lexer::header {
package fr.lip6.move.coloane.apicami.parse;
}

@parser::header {
package fr.lip6.move.coloane.apicami.parse;
	
import fr.lip6.move.coloane.apicami.interfaces.ISessionController;
import fr.lip6.move.coloane.apicami.objects.ConnectionInfo;
import fr.lip6.move.coloane.apicami.objects.Dialog;
import fr.lip6.move.coloane.apicami.objects.ReceptMessage;
import fr.lip6.move.coloane.apicami.objects.ReceptServiceState;
import fr.lip6.move.coloane.apicami.objects.menu.IQuestion;
import fr.lip6.move.coloane.apicami.objects.menu.SubMenu;
import fr.lip6.move.coloane.apicami.objects.result.Result;
import fr.lip6.move.coloane.apicami.objects.result.SubResult;
import fr.lip6.move.coloane.apicami.objects.result.Tip;
import fr.lip6.move.coloane.apicami.observables.BrutalInterruptObservable;
import fr.lip6.move.coloane.apicami.observables.ConnectionObservable;
import fr.lip6.move.coloane.apicami.observables.DisconnectObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptDialogObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptMenuObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptMessageObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptResultObservable;
import fr.lip6.move.coloane.apicami.observables.ReceptServiceStateObservable;
import fr.lip6.move.coloane.apicami.session.SessionController;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptMessage;
import fr.lip6.move.coloane.interfaces.api.evenements.IReceptServiceState;
import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.model.command.AttributePositionCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateArcCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateAttributeCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateInflexPointCommand;
import fr.lip6.move.coloane.interfaces.model.command.CreateNodeCommand;
import fr.lip6.move.coloane.interfaces.model.command.DeleteInflexPointsCommand;
import fr.lip6.move.coloane.interfaces.model.command.DeleteObjectCommand;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.model.command.ObjectPositionCommand;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.coloane.interfaces.objects.menu.IUpdateMenu;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
	
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

/** Un indicateur d'etat */
private static int state = ICamiParserState.DEFAULT_STATE;

/** Permet de consulter l'etat en cours du parser */
public int getState() {
	return state;
}

/** L'objet resultat : STATIQUE pour pouvoir le remplir en plusieurs passes */
private static IResult result;

private static List<IUpdateMenu> updates;
private static Map<Integer, IDialog> dialogs;

}
/* --------------------------------------- */
/* Selecteur principal                     */
/* --------------------------------------- */

main
	@init { state = ICamiParserState.DEFAULT_STATE; }
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
	/* Messages speciaux */
	|	message_to_user
	/* Pour les resultats */
	|	ask_for_model
	|	receive_results
	/* Les messages KO */
	|	ko_message
	;

/* --------------------------------------- */
/* Gestion de la connexion                 */
/* --------------------------------------- */

/* Ouverture de la communication avec la plate-forme */
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

/* Reception des services */
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
	message_to_user*	
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

/* Description de l'etat d'un services */
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

/* Reception d'un message utilisateur */
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
		IReceptMessage msg = (IReceptMessage) new ReceptMessage(IReceptMessage.TRACE_MESSAGE, $message.text);
		((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg);
	}
	;

/* Message d'avertissement */
warning_message
	:
	'WN(' message=CAMI_STRING ')' {
		LOGGER.finest("Reception d'un message de trace");
		IReceptMessage msg = (IReceptMessage) new ReceptMessage(IReceptMessage.WARNING_MESSAGE, $message.text);
		((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg);
	}
	;

/* Message special */
special_message
	:
	'MO(' type=NUMBER ',' message=CAMI_STRING ')' {
		if($type.text.equals("1")) { 
			LOGGER.finest("Reception d'un message de l'administrateur"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(IReceptMessage.ADMINISTRATOR_MESSAGE,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}
    
		if($type.text.equals("2")) {
			//TODO :Verifier qu'il faut traiter ce message comme un KO
			LOGGER.finest("Reception d'un message court et urgent");
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(IReceptMessage.ERROR_MESSAGE,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}

		if($type.text.equals("3")) { 
			LOGGER.finest("Reception d'un message copyright"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(IReceptMessage.COPYRIGHT_MESSAGE,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}
    
		if($type.text.equals("4")) { 
			LOGGER.finest("Reception d'un message a propos des statistiques d'execution"); 
			IReceptMessage msg =(IReceptMessage) new ReceptMessage(IReceptMessage.TRACE_MESSAGE,$message.text); 
			((ReceptMessageObservable) hash.get("IReceptMessage")).notifyObservers(msg); 
		}
	}
	;

/* --------------------------------------- */
/* Gestion des resultats                   */
/* --------------------------------------- */

ask_for_model
	:
	state_service*
	'DF(-' NUMBER ',' NUMBER ',' NUMBER ')' {
		sessionController.notifyWaitingForModel();
	}
	;

/* Reception des resultats */
receive_results
	@init { 
		state = ICamiParserState.RESULT_STATE;
		updates = new ArrayList<IUpdateMenu>();
		dialogs = new HashMap<Integer, IDialog>();
	}
	:	
	'DR()'
	'RQ(' root_name=CAMI_STRING ',' service_name=CAMI_STRING ',' deprecated=NUMBER ')' {
		result = CamiObjectBuilder.buildResult($root_name.text, $service_name.text, sessionController.getActiveSession().getOutputModel());
	}
	;

results
	:	
	(	message_to_user
	|	state_service 		{ updates.add($state_service.builtUpdate); }
	|	dialog_definition 	{ dialogs.put($dialog_definition.dialog.getId(), $dialog_definition.dialog); }
	|	dialog_display
	|	dialog_destroy
	| 	tip_description 	{ ((Result) result).addTip($tip_description.tip); }
	|	one_result 		{ ((Result) result).addSubResult($one_result.builtResult); }
	|	formalism_change
	|	model_definition 	{ ((Result) result).addOutputGraph($model_definition.commandsList); }
	)*
	(
	'FR(' NUMBER ')' {
		LOGGER.finest("Transmission des resultats");
		((ReceptResultObservable)hash.get("IReceptResult")).notifyObservers(result);
		LOGGER.finest("Transmission des mises a jour des services");
		((ReceptMenuObservable) hash.get("ISession")).notifyObservers(null, updates); 
		sessionController.notifyEndResult();
		
		// Remise a zero de l'etat du parser
		state = ICamiParserState.DEFAULT_STATE;
	}
	)?
	;

/* Description d'un resultats */
one_result
	returns[ISubResult builtResult]
	scope { ISubResult current; }
	:
	(	'DE()' { $one_result::current = CamiObjectBuilder.buildSubResult("","0"); }
	|	'DE(' set_name=CAMI_STRING ',' set_type=NUMBER ')' { $one_result::current = CamiObjectBuilder.buildSubResult($set_name.text, $set_type.text); }
	)
	result_body*
	'FE()' { builtResult = $one_result::current; }
	;

/* Corps d'un resultat */
result_body
	:	one_result
	|	textual_result
	|	attribute_change
	|	object_designation
	|	object_outline
	|	attribute_outline
	|	object_creation { ((Result) result).addCommand($object_creation.command); }
	|	object_deletion { ((Result) result).addCommand($object_deletion.command); }
	| 	tip_description { ((Result) result).addTip($tip_description.tip); }
	;

/* Resultat textuel */
textual_result
	:	
	'RT(' text=CAMI_STRING ')' { ((SubResult) $one_result::current).addTextualResult($text.text); }
	;

/* Changement d'un attribut */
attribute_change
	:	
	'WE(' id=NUMBER ',' attribute_name=CAMI_STRING ',' new_value=CAMI_STRING ')'
	;

/* Mise en valeur d'un attribut */
attribute_outline
	:	
	'MT(' id=NUMBER ',' attribute_name=CAMI_STRING ',' begin=NUMBER? ',' end=NUMBER? ')' 
	{ ((SubResult) $one_result::current).addAttributeOutline(Integer.parseInt($id.text), $attribute_name.text); }
	;

/* Designation d'un objet */
object_designation
	:
	'RO(' id=NUMBER ')' { ((SubResult) $one_result::current).addObjectDesignation(Integer.parseInt($id.text)); }		
	;

/* Mise en valeur d'un objet */
object_outline
	:
	'ME(' id=NUMBER ')' { ((SubResult) $one_result::current).addObjectOutline(Integer.parseInt($id.text)); }
	;

/* Creation d'un objet */
object_creation returns [ICommand command]
	:	syntactic { command = $syntactic.command; }
	|	aestetic  { command = $aestetic.command;  }
	;

/* Suppression d'un objet */
object_deletion returns [ICommand command]
	:	'SU(' id=NUMBER ')' { command = new DeleteObjectCommand(Integer.parseInt($id.text)); }
 	|	'SI(' page_id=NUMBER ',' id=NUMBER ')' {
 			if (($id.text).equals("-1")) { command = new DeleteInflexPointsCommand(); } 
 			else { command = new DeleteObjectCommand(Integer.parseInt($id.text)); }
 		}
 	;
 
 /* Description des changements a apporter au modele pour ces resultats */
 formalism_change
 	:	
 	attribute_table
 	( 'ZA('NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ',' NUMBER ')' )?
 	;
 
 /* Une information sur un attribut particulier */
 tip_description returns [ITip tip]
 	:
 	'XA(' id_object=NUMBER ',' attribute_name=CAMI_STRING ',' attribute_value=CAMI_STRING ')' 
 	{ tip = new Tip(Integer.parseInt($id_object.text), $attribute_name.text, $attribute_value.text); }
 	;
 
 /* Table des attributs (non prise en compte par le core) */
 attribute_table
 	:	
 	'TD(' CAMI_STRING ')'
 	( 'OB(' NUMBER ',' NUMBER ',' CAMI_STRING ')' | 'AT(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')' )*
	'FA()'
 	;
 
/* --------------------------------------- */
/* Definition d'un modele		   */
/* --------------------------------------- */

/* En-tete d'un modele */
model_definition returns [List<ICommand> commandsList]
	@init { commandsList = new ArrayList<ICommand>(); }
	:	
	'DB()'
	( 	syntactic { commandsList.add($syntactic.command); } 
	| 	aestetic  { commandsList.add($aestetic.command); }
	)*
	'FB()'
	;

/* Definition des composants */
syntactic returns [ICommand command]
	:	node 		{ command = $node.command; }
	|	box 
	| 	arc 		{ command = $arc.command; }
	| 	attribute	{ command = $attribute.command; }
	;

/* Description d'un noeud */
node returns [ICommand command]
	:	
	'CN(' type=CAMI_STRING ',' id=NUMBER ')' { command = new CreateNodeCommand(Integer.parseInt($id.text), $type.text); }
	;

/* Description d'une boite */
box	
	:
	'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')' { LOGGER.warning("BOX support is deprecated"); }
	;

/* Description d'un arc */
arc returns [ICommand command]
	:
	'CA(' type=CAMI_STRING ',' id=NUMBER ',' source=NUMBER ',' target=NUMBER ')' 
	{ command = new CreateArcCommand(Integer.parseInt($id.text), $type.text, Integer.parseInt($source.text), Integer.parseInt($target.text)); }
	;

/* Description d'un attribut */
attribute returns [ICommand command]
	:	'CT(' name=CAMI_STRING ',' id=NUMBER ','value=CAMI_STRING ')' { command = new CreateAttributeCommand($name.text, Integer.parseInt($id.text), $value.text, false); }
	|	'CM(' name=CAMI_STRING ',' id=NUMBER ',' line=NUMBER ',' deprecated=NUMBER ',' value=CAMI_STRING ')'
		{ command = new CreateAttributeCommand($name.text, Integer.parseInt($id.text), $value.text, (Integer.parseInt($line.text) != 1)); }
	;

/* Description esthetique */
aestetic returns [ICommand command]
	:	object_position	{ command = $object_position.command; }
	| 	text_position	{ command = $text_position.command; }
	| 	inflex_point	{ command = $inflex_point.command; }
	;

/* Position d'un objet */
object_position returns [ICommand command]
	:	
	(	'PO(' id=NUMBER ',' x=NUMBER ',' y=NUMBER ')'
	|	'pO(' id=NUMBER ',' x=NUMBER ',' y=NUMBER ')'
	|	'PO(-1,' id=NUMBER ',' x=NUMBER ',' y=NUMBER ',' top=NUMBER ',' bottom=NUMBER')'
	) 
	{ command = new ObjectPositionCommand(Integer.parseInt($id.text), Integer.parseInt($x.text), Integer.parseInt($y.text)); }
	;

/* Position d'un attribut */
text_position returns [ICommand command]
	:	
	'PT(' id=NUMBER ',' attribute_name=CAMI_STRING ',' x=NUMBER ',' y=NUMBER ')'
	{ command = new AttributePositionCommand(Integer.parseInt($id.text), $attribute_name.text, Integer.parseInt($x.text), Integer.parseInt($y.text)); }
	;

/* Position des points d'inflexion */
inflex_point returns [ICommand command]
	:	
	(	'PI(' id=NUMBER ',' x=NUMBER ',' y=NUMBER ')' 
	|	'pI(' id=NUMBER ',' x=NUMBER ',' y=NUMBER ')'
	)
	{ command = new CreateInflexPointCommand(Integer.parseInt($id.text), Integer.parseInt($x.text), Integer.parseInt($y.text)); }
	;
	
/* --------------------------------------- */
/* Definition d'une boite de dialogue      */
/* --------------------------------------- */

/* Description d'une boite de dialogue */
dialog_definition
	returns [IDialog dialog]
	:
	'DC()' { LOGGER.finest("Reception d'une definition d'une boite de dialogue"); }
	dialog_creation { dialog = $dialog_creation.dialog; }
	dialog_next[dialog]*
	'FF()' { LOGGER.finest("Fin de reception des boites de dialogue"); }
	;

/* Corps de la boite de dialogue */
dialog_creation
	returns [IDialog dialog]
	@init { List<String> ce = new ArrayList<String>();}
	:
	'CE(' dialog_id=NUMBER ',' dialog_type=NUMBER ',' buttons_type=NUMBER ','  window_title=CAMI_STRING ',' 
	help=CAMI_STRING ',' title_or_message=CAMI_STRING ',' input_type=NUMBER ',' line_type=NUMBER ',' 
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
		dialog = CamiObjectBuilder.buildDialog(ce);
	}
	;

/* ??? */
dialog_next[IDialog dialog]
	:
	'DS(' dialog_id=NUMBER ',' line=CAMI_STRING ')' { ((Dialog) dialog).addLine($line.text); }
	;

/* Affiche la boite de dialogue */
dialog_display
	:
	'AD(' dialog_id=NUMBER ')'
	{	// Demande l'affichage au core
		((ReceptDialogObservable) hash.get("IReceptDialog")).notifyObservers(dialogs.get(Integer.parseInt($dialog_id.text)));
		// Permet de stocker la boite de dialogue dans l'API pour pouvoir y repondre
		sessionController.notifyReceptDialog(dialogs.get(Integer.parseInt($dialog_id.text)));
	}
	;

/* Cache la boite de dialogue */
dialog_hide
	:
	'HD(' dialog_id=NUMBER ')'
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
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);} ':' value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
NUMBER	:	('0'..'9')+ ;
NEWLINE :	( '\r'?'\n' )+ {skip();} ;

fragment
FIXED_LENGTH_STRING [int len] :	({len > 0}?=> .{len--;})* ;
