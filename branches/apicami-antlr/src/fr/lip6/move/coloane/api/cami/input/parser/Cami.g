grammar Cami;

@parser::header{
package fr.lip6.move.coloane.api.cami.input.parser;

import java.util.Collection;
import java.util.Vector;	

import fr.lip6.move.coloane.api.cami.input.connection.AckOpenCommunication;
import fr.lip6.move.coloane.api.cami.input.connection.AckOpenConnection;
import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionNormal;
import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionPanic;
import fr.lip6.move.coloane.api.cami.input.dialog.DestroyDialog;
import fr.lip6.move.coloane.api.cami.input.dialog.DialogCreation;
import fr.lip6.move.coloane.api.cami.input.dialog.DialogDefinition;
import fr.lip6.move.coloane.api.cami.input.dialog.DisplayDialog;
import fr.lip6.move.coloane.api.cami.input.dialog.HideDialog;
import fr.lip6.move.coloane.api.cami.input.dialog.NextDialog;
import fr.lip6.move.coloane.api.cami.input.menus.DisableMainQuestion;
import fr.lip6.move.coloane.api.cami.input.menus.EnableMainQuestion;
import fr.lip6.move.coloane.api.cami.input.menus.EndMenuTransmission;
import fr.lip6.move.coloane.api.cami.input.menus.HelpQuestion;
import fr.lip6.move.coloane.api.cami.input.menus.MenuName;
import fr.lip6.move.coloane.api.cami.input.menus.QuestionAdd;
import fr.lip6.move.coloane.api.cami.input.menus.ServiceMenuModification;
import fr.lip6.move.coloane.api.cami.input.menus.ServiceMenuReception;
import fr.lip6.move.coloane.api.cami.input.messages.IMessage;
import fr.lip6.move.coloane.api.cami.input.messages.SpecialMessages;
import fr.lip6.move.coloane.api.cami.input.messages.TraceMessage;
import fr.lip6.move.coloane.api.cami.input.messages.WarningMessage;
import fr.lip6.move.coloane.api.cami.input.results.AttributeChange;
import fr.lip6.move.coloane.api.cami.input.results.AttributeOutline;
import fr.lip6.move.coloane.api.cami.input.results.CreateArc;
import fr.lip6.move.coloane.api.cami.input.results.CreateBox;
import fr.lip6.move.coloane.api.cami.input.results.CreateMonolineAttribute;
import fr.lip6.move.coloane.api.cami.input.results.CreateMultilineAttribute;
import fr.lip6.move.coloane.api.cami.input.results.CreateNode;
import fr.lip6.move.coloane.api.cami.input.results.IResult;
import fr.lip6.move.coloane.api.cami.input.results.MultipleObjectDeletion;
import fr.lip6.move.coloane.api.cami.input.results.ObjectDeletion;
import fr.lip6.move.coloane.api.cami.input.results.ObjectDesignation;
import fr.lip6.move.coloane.api.cami.input.results.ObjectOutline;
import fr.lip6.move.coloane.api.cami.input.results.QuestionAnswer;
import fr.lip6.move.coloane.api.cami.input.results.QuestionState;
import fr.lip6.move.coloane.api.cami.input.results.ResultSet;
import fr.lip6.move.coloane.api.cami.input.results.Results;
import fr.lip6.move.coloane.api.cami.input.results.TextualResult;
import fr.lip6.move.coloane.api.cami.input.session.AckCloseCurrentSession;
import fr.lip6.move.coloane.api.cami.input.session.AckOpenSession;
import fr.lip6.move.coloane.api.cami.input.session.AckResumeSession;
import fr.lip6.move.coloane.api.cami.input.session.AckSuspendCurrentSession;
import fr.lip6.move.coloane.api.session.states.MessageFormatFailure;
import fr.lip6.move.coloane.api.session.states.authentication.AuthenticationCommunicationAck;
import fr.lip6.move.coloane.api.session.states.authentication.AuthenticationFailure;
import fr.lip6.move.coloane.api.session.states.authentication.AuthenticationVersionAck;

}

@lexer::header{
package fr.lip6.move.coloane.api.cami.input.parser;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

/*------------------------------------------------------------------
 * CAMI-LDM - Models description
 *------------------------------------------------------------------*/

model_definition
	:	
	'DB()'
	( syntactic | aestetic )
	'FB()'
	;

syntactic
	:
	node | box | arc | textual_attribute
	;

node	:	
	'CN(' CAMI_STRING ',' number ')'
	;
	
box	:
	'CB(' CAMI_STRING ',' number ',' number ')'
	;
	
arc	:
	'CA(' CAMI_STRING ',' number ',' number ',' number ')'
	;

textual_attribute
	:
	  'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')'
	| 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')'
	;


aestetic
	:
	object_position | text_position | intermediary_point
	;

object_position
	:
	  'PO(' id=number ',' h_distance=number ',' v_distance=number ')'
	| 'pO(' id=number ',' h_distance=number ',' v_distance=number ')'
	| 'PO(-1,' id=number ',' left=number ',' right=number ',' top=number ',' bottom=number')'
	;

text_position
	:	
	'PT(' id=number ',' name_attr=CAMI_STRING ',' h_distance=number ',' v_distance=number ')'
	;

intermediary_point
	:	
	'PI(' number ',' number ',' number ')'
	;

/*------------------------------------------------------------------
 * CAMI-LDDS - Simple dialogs description
 *------------------------------------------------------------------*/

dialog_definition
	returns [DialogDefinition dialogDefinition]
	@init
	{
		Collection<NextDialog> nextDialogs = new Vector<NextDialog>();
	}
	:	
	'DC()'
	dialog_creation
	( next_dialog
	{
		nextDialogs.add($next_dialog.nextDialog);
	} )+
	'FF()'
	{
		return new DialogDefinition($dialog_creation.dialogCreation, nextDialogs);
	}
	;

dialog_creation
	returns [DialogCreation dialogCreation]
	:
	'CE(' dialog_id=number ',' dialog_type=number ',' buttons_type=number ','  window_title=CAMI_STRING ',' help=CAMI_STRING ',' title_or_message=CAMI_STRING ',' 
		input_type=number ',' line_type=number ',' default_value=CAMI_STRING? ')'
	{
		String defaultValue = "";
		dialogCreation = new DialogCreation( 	$dialog_id.value,
							DialogCreation.DialogType($dialog_type.value),
							DialogCreation.ButtonsType($buttons_type.value),
							$window_title.text,
							$help.text,
							$title_or_message.text,
							DialogCreation.InputType($input_type.value),
							DialogCreation.LineType($line_type.value),
							defaultValue);
	}
	;

next_dialog
	returns [NextDialog nextDialog]
	:
	'DS(' dialog_id=number ',' line=CAMI_STRING ')'
	{
		nextDialog = new NextDialog($dialog_id.value,$line.text);
	}
	;

display_dialog
	returns [DisplayDialog displayDialog]
	:
	'AD(' dialog_id=number ')'
	{
		displayDialog = new DisplayDialog($dialog_id.value);
	}
	;
	
hide_dialog
	returns [HideDialog hideDialog]
	:
	'HD(' dialog_id=number ')'
	{
		hideDialog = new HideDialog($dialog_id.value);
	}
	;
	
destroy_dialog
	returns [DestroyDialog destroyDialog]
	:
	'DG(' dialog_id=number ')'
	{
		destroyDialog = new DestroyDialog($dialog_id.value);
	}
	;

// Deprecated
interactive_response
	:
	'MI(' number ',' number ')'
	;


/*------------------------------------------------------------------
 * CAMI-LDPM - GUI Protocole description
 *------------------------------------------------------------------*/

// Traces and messages

message_to_user
	returns [IMessage camiContent]
	:
	  trace_message 
	  {
	  	camiContent = $trace_message.message;
	  }
	| warning_message 
	  {
	  	camiContent = $warning_message.message;
	  }

	| special_message
	  {
	  	camiContent = $special_message.message;
	  }
	;

trace_message
	returns [TraceMessage message]
	:
	'TR(' CAMI_STRING ')'
	{
		message = new TraceMessage($CAMI_STRING.text);
	}
	;

warning_message
	returns [WarningMessage message]
	:
	'WN(' CAMI_STRING ')'
	{
		message = new WarningMessage($CAMI_STRING.text);
	}
	;

special_message
	returns [SpecialMessages message]
	:	
	'MO(' message_type=number ',' message_content=CAMI_STRING ')'
	{
		message = new SpecialMessages(SpecialMessages.SpecialMessageType($message_type.value),$message_content.text);
	}
	;
 

// Connection handler

open_communication
	returns [AuthenticationCommunicationAck message]
	:
	  ack_open_communication 
	  {
	  	message = new AuthenticationCommunicationAck($ack_open_communication.camiContent);
	  }
	| close_connection_panic
	  {
	  	if( true ) // to avoid an error in the generated code
		  	throw new AuthenticationFailure($close_connection_panic.camiContent);
	  }
  	| special_message
	  {
	  	if(true) // to avoid an error in the generated code
	  		throw new MessageFormatFailure($special_message.message);
	  }

	;

check_version
	returns [AuthenticationVersionAck message]
	:
	  ack_open_connection
	  {
	  	message = new AuthenticationVersionAck($ack_open_connection.camiContent);  
	  }
	| special_message
	  {
	  	if(true) // to avoid an error in the generated code
	  		throw new MessageFormatFailure($special_message.message);
	  }
	;

ack_open_communication
	returns [AckOpenCommunication camiContent]
	:
	'SC(' CAMI_STRING ')'
	{
		camiContent = new AckOpenCommunication($CAMI_STRING.text);
	}
	;
	
ack_open_connection
	returns [AckOpenConnection camiContent]
	:
	'OC(' major=number ',' minor=number ')'
	{
		camiContent = new AckOpenConnection($major.value,$minor.value);
	}
	;

close_connection_normal
	returns [CloseConnectionNormal camiContent]
	:
	'FC()'
	{
		camiContent = new CloseConnectionNormal();
	}
	;
	
close_connection_panic
	returns [CloseConnectionPanic camiContent]
	:
	'KO(1,' message=CAMI_STRING ',' severity=number ')'
	{
		camiContent = new CloseConnectionPanic($message.text,
							CloseConnectionPanic.Severity($severity.value));
	}
	;

// Formalism table

interlocutor_table
	:
	'TL()' 
	body_table+ 
	'FL()' 
	;

body_table
	:
	'VI(' service_name=CAMI_STRING ',' about_service=CAMI_STRING ',' deprecated=number ',' new_model=number ')'
;

// Invokation of a service

pre_result_reception
	:
	question_state
	ask_hierarchic
	;

result_reception
	returns [Results results]
	@init
	{
		Collection<QuestionState> questionStates = new Vector<QuestionState>();
		Collection<IMessage> messages = new Vector<IMessage>();
		Collection<ResultSet> resultSets = new Vector<ResultSet>();
		QuestionAnswer questionAnswer = null;
	}
	:
	'DR()'
	reply_to_question
	{
		questionAnswer = $reply_to_question.questionAnswer;
	}
	( 
	  question_state
	  {
	  	questionStates.add($question_state.questionState);
	  }
	| special_message
	  {
	  	messages.add($special_message.message);
	  }
	| warning_message
	  {
	 	messages.add($warning_message.message);
	  }
	| result
	  {
	  	resultSets.add($result.res);
	  }
	)*
	'FR(' answer_type=number ')'
	{
		results = new Results(	questionAnswer,
					messages,
					questionStates,
					resultSets,
					Results.ResultType($answer_type.value));
	}
	;

reply_to_question
	returns [QuestionAnswer questionAnswer]
	:
	'RQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' deprecated=number ')'
	{
		questionAnswer = new QuestionAnswer($service_name.text,$question_name.text);
	}
	;

question_state
	returns [QuestionState questionState]
	:
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=number ',' mess=CAMI_STRING? ')'
	{
		String message = null;
		if( $mess != null ) 
			message = $mess.text;
		questionState = new QuestionState($service_name.text,$question_name.text,$state.value,message);
	}
	;
	question_add
 	returns [QuestionAdd questionAdd]
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
		    question_type=number? ',' question_behavior=number? ',' 
		    set_item=number? ','  historic=number? ',' stop_authorized=number? ',' 
		    ouput_formalism=CAMI_STRING? ',' active=number? ')'
	{
		questionAdd = new QuestionAdd(); // TODO
	}
	;


result	
	returns [ResultSet res]
	@init
	{
		Collection<IResult> results = new Vector<IResult>();
	}
	:
	'DE(' ensemble_name=CAMI_STRING ',' ensemble_type=number ')'
	(
	result_body
	{
		results.add($result_body.res);
	}
	)+
	'FE()'
	{
		res = new ResultSet(	$ensemble_name.text,
					ResultSet.ResultSetType($ensemble_type.value),
					results );
	}
	;
 
result_body
 	returns [IResult res]
 	:
 	  result
 	  {
 		res = $result.res;
 	  }
 	| textual_result
 	  {
 		res = $textual_result.res;
 	  }
 	| attribute_change
 	  {
 		res = $attribute_change.res;
 	  }
 	| object_designation
 	  {
 		res = $object_designation.res;
 	  }
 	| object_outline
 	  {
 		res = $object_outline.res;
 	  }
 	| attribute_outline
 	  {
 		res = $attribute_outline.res;
 	  }
 	| object_creation
 	  {
 		res = $object_creation.res;
 	  }
 	| object_deletion
 	  {
 		res = $object_deletion.res;
 	  }
 	;
 
 textual_result
 	returns [IResult res]
 	:
 	'RT(' CAMI_STRING ')'
 	{
 		res = new TextualResult($CAMI_STRING.text);
 	}
 	;
 
 attribute_change
 	returns [IResult res]
 	:
 	'WE(' id=number ',' attr_name=CAMI_STRING ',' new_value=CAMI_STRING ')'
 	{
 		res = new AttributeChange($id.value,$attr_name.text,$new_value.text);
 	}
 	;
 
 object_designation
 	returns [IResult res]
 	:
 	'RO(' id=number ')'
 	{
 		res = new ObjectDesignation($id.value);
 	}
 	;
 
 object_outline
 	returns [IResult res]
 	:
 	'ME(' id=number ')'
 	{
 		res = new ObjectOutline($id.value);
 	}
 	;
 
 attribute_outline
 	returns [IResult res]
 	:
 	'MT(' id=number ',' attr_name=CAMI_STRING ',' begin=number? ',' end=number? ')'
 	{
		res = new AttributeOutline($id.value,$attr_name.text);
 	}
 	;
 
 object_creation
 	returns [IResult res]
 	:
	  'CN(' node_box_type=CAMI_STRING ',' id=number ')'
	  {
	  	res = new CreateNode($node_box_type.text,$id.value);
	  }
	| 'CB(' node_box_type=CAMI_STRING ',' id=number ',' page_id=number ')'
	  {
	  	res = new CreateBox($node_box_type.text,$id.value,$page_id.value);
	  }
	| 'CA(' arc_type=CAMI_STRING ',' id=number ',' start_node=number ',' end_node=number ')'
	  {
	  	res = new CreateArc($arc_type.text,$id.value,$start_node.value,$end_node.value);
	  }
	| 'CT(' attribute_name=CAMI_STRING ',' associated_node=number ',' value=CAMI_STRING ')'
	  {
	  	res = new CreateMonolineAttribute($attribute_name.text,$associated_node.value,$value.text);
	  }
	| 'CM(' attribute_name=CAMI_STRING ',' associated_node=number ',' line_number=number ',' deprecated=number ',' value=CAMI_STRING ')'
	  {
	  	res = new CreateMultilineAttribute($attribute_name.text,$associated_node.value,$line_number.value,$value.text);
	  }
 	;
 
object_deletion
 	returns [IResult res]
	:
 	  'SU(' id=number ')'
 	  {
 	  	res = new ObjectDeletion($id.value);
 	  }
 	| 'SI(' page_id=number ',' id=number ')'
 	  {
 	  	res = new MultipleObjectDeletion($page_id.value,$id.value);
 	  }
 	;
 
// Session handler

ack_open_session
	returns [AckOpenSession camiContent]
	:
	'OS(' session_name=CAMI_STRING ')'
	'TD()'
	'FA()'
	interlocutor_table
	{
		camiContent = new AckOpenSession($session_name.text);
	}
	;

ack_close_current_session 
	returns [AckCloseCurrentSession camiContent]
	:
	'FS()'
	{
		camiContent = new AckCloseCurrentSession();
	}
	;

ack_suspend_current_session
	returns [AckSuspendCurrentSession camiContent]
	:	 
	'SS()'
	{
		camiContent = new AckSuspendCurrentSession();
	}
	;

ack_resume_session
	returns [AckResumeSession camiContent]
	:
	'RS(' session_name=CAMI_STRING ')'
	{
		camiContent = new AckResumeSession($session_name.text);
	}
	;

// Model asking

ask_and_get_model
	:
	  ask_for_a_model
	| model_definition
	;

ask_for_a_model
	:
	ask_simple | ask_hierarchic
	;

ask_simple
	:
	'DF()'
	;

ask_hierarchic
	:
	'DF(-2,' number ',' number ')'
	;

// Service menu reception

service_menu_reception
  	returns [ServiceMenuReception serviceMenuReception]
 	@init
	{
		Collection<QuestionAdd> questions = new Vector<QuestionAdd>();
	}
	:
	'DQ()'
	menu_name
	(
	question_add 
	{ 
		questions.add($question_add.questionAdd); 
	}
	)*
	'FQ()'
	{
		serviceMenuReception = new ServiceMenuReception($menu_name.menuName, questions);
	}
	;

menu_name
	returns [MenuName menuName]
	:
	'CQ(' name=CAMI_STRING ',' always_three=number ',' always_three=number ')'
	{
		menuName = new MenuName($name.text);
	}
	;


service_menu_modification
 	returns [ServiceMenuModification serviceMenuModification]
 	@init
 	{
 		Collection<QuestionState> questionStates = new Vector<QuestionState>();
 	}
	:
	enable_main_question
	(
	question_state 
	{ 
		questionStates.add($question_state.questionState); 
	}
	)*
	end_menu_transmission
	{
		serviceMenuModification = new ServiceMenuModification(	$enable_main_question.enableMainQuestion,
	                                                       		questionStates,
	                                                        	$end_menu_transmission.endMenuTransmission);
	}
	;

enable_main_question
  	returns [EnableMainQuestion enableMainQuestion]
	:
	'VQ(' main_question_name=CAMI_STRING ')'
	{
		enableMainQuestion = new EnableMainQuestion($main_question_name.text);
	}
	;

disable_main_question
 	returns [DisableMainQuestion disableMainQuestion]
	:
	'EQ(' main_question_name=CAMI_STRING ')'
	{
		disableMainQuestion = new DisableMainQuestion($main_question_name.text);
	}
	;

end_menu_transmission
	returns [EndMenuTransmission endMenuTransmission]
	:
	'QQ(' number ')'
	{
		endMenuTransmission = new EndMenuTransmission(EndMenuTransmission.AckType($number.value));
	}
	;

help_question
 	returns [HelpQuestion helpQuestion]
	:
	'HQ(' question_name=CAMI_STRING ',' help_message=CAMI_STRING ')'
 	{
		helpQuestion = new HelpQuestion($question_name.text, $help_message.text);
 	}
  	;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

number	
	returns [int value]
	:
	NUMBER {value = Integer.parseInt($NUMBER.text);}
	;

// A CAMI string -> NUMBER:STRING where NUMBER is the size of the STRING to be read
CAMI_STRING
	@init{int nbToRead = 0;}
    	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':' 
	fs=FIXED_LENGTH_STRING[nbToRead]{setText($fs.text);}
	;

fragment
FIXED_LENGTH_STRING
	[int len]
	:   
	( { len > 0 }?=> .{len--;})* // Gated predicate : deactivate the '.' when len chars have been read
	;
	
NUMBER	
	:
	'0'..'9'+
	;

NEWLINE : 	
	( '\r'?'\n' )+ {skip();}
	;

EOF	:
	{skip();}
	;
