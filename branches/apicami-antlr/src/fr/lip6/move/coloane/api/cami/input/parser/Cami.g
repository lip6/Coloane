grammar Cami;

@parser::header{
package fr.lip6.move.coloane.api.cami.input.parser;

import java.util.Collection;
import java.util.Vector;	

import fr.lip6.move.coloane.api.cami.ICommand;
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
	returns [DialogDefinition camiContent]
	@init
	{
		Collection<NextDialog> nextDialogs = new Vector<NextDialog>();
	}
	:	
	'DC()'
	dialog_creation
	( next_dialog
	{
		nextDialogs.add($next_dialog.camiContent);
	} )+
	'FF()'
	{
		return new DialogDefinition($dialog_creation.camiContent, nextDialogs);
	}
	;

dialog_creation
	returns [DialogCreation camiContent]
	:
	'CE(' dialog_id=number ',' dialog_type=number ',' buttons_type=number ','  window_title=CAMI_STRING ',' help=CAMI_STRING ',' title_or_message=CAMI_STRING ',' 
		input_type=number ',' line_type=number ',' default_value=CAMI_STRING? ')'
	{
		String defaultValue = "";
		camiContent = new DialogCreation( 	$dialog_id.value,
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
	returns [NextDialog camiContent]
	:
	'DS(' dialog_id=number ',' line=CAMI_STRING ')'
	{
		camiContent = new NextDialog($dialog_id.value,$line.text);
	}
	;

display_dialog
	returns [DisplayDialog camiContent]
	:
	'AD(' dialog_id=number ')'
	{
		camiContent = new DisplayDialog($dialog_id.value);
	}
	;
	
hide_dialog
	returns [HideDialog camiContent]
	:
	'HD(' dialog_id=number ')'
	{
		camiContent = new HideDialog($dialog_id.value);
	}
	;
	
destroy_dialog
	returns [DestroyDialog camiContent]
	:
	'DG(' dialog_id=number ')'
	{
		camiContent = new DestroyDialog($dialog_id.value);
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
	  	camiContent = $trace_message.camiContent;
	  }
	| warning_message 
	  {
	  	camiContent = $warning_message.camiContent;
	  }

	| special_message
	  {
	  	camiContent = $special_message.camiContent;
	  }
	;

trace_message
	returns [TraceMessage camiContent]
	:
	'TR(' CAMI_STRING ')'
	{
		camiContent = new TraceMessage($CAMI_STRING.text);
	}
	;

warning_message
	returns [WarningMessage camiContent]
	:
	'WN(' CAMI_STRING ')'
	{
		camiContent = new WarningMessage($CAMI_STRING.text);
	}
	;

special_message
	returns [SpecialMessages camiContent]
	:	
	'MO(' message_type=number ',' message_content=CAMI_STRING ')'
	{
		camiContent = new SpecialMessages(SpecialMessages.SpecialMessageType($message_type.value),$message_content.text);
	}
	;
 

// Connection handler

open_communication
	returns [ICommand camiContent]
	:
	  ack_open_communication 
	  {
		camiContent = $ack_open_communication.camiContent;
	  }
	| close_connection_panic
	  {
		camiContent = $close_connection_panic.camiContent;
	  }
  	| special_message
	  {
		camiContent = $special_message.camiContent;
	  }
	;

check_version
	returns [ICommand camiContent]
	:
	  ack_open_connection
	  {
	  	camiContent = $ack_open_connection.camiContent;
	  }
	| special_message
	  {
	  	camiContent = $special_message.camiContent;
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
	returns [Results camiContent]
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
		questionAnswer = $reply_to_question.camiContent;
	}
	( 
	  question_state
	  {
	  	questionStates.add($question_state.camiContent);
	  }
	| special_message
	  {
	  	messages.add($special_message.camiContent);
	  }
	| warning_message
	  {
	 	messages.add($warning_message.camiContent);
	  }
	| result
	  {
	  	resultSets.add($result.camiContent);
	  }
	)*
	'FR(' answer_type=number ')'
	{
		camiContent = new Results(	questionAnswer,
					messages,
					questionStates,
					resultSets,
					Results.ResultType($answer_type.value));
	}
	;

reply_to_question
	returns [QuestionAnswer camiContent]
	:
	'RQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' deprecated=number ')'
	{
		camiContent = new QuestionAnswer($service_name.text,$question_name.text);
	}
	;

question_state
	returns [QuestionState camiContent]
	:
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=number ',' mess=CAMI_STRING? ')'
	{
		String message = null;
		if( $mess != null ) 
			message = $mess.text;
		camiContent = new QuestionState($service_name.text,$question_name.text,$state.value,message);
	}
	;
	question_add
 	returns [QuestionAdd camiContent]
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
		    question_type=number? ',' question_behavior=number? ',' 
		    set_item=number? ','  historic=number? ',' stop_authorized=number? ',' 
		    ouput_formalism=CAMI_STRING? ',' active=number? ')'
	{
		camiContent = new QuestionAdd(); // TODO
	}
	;


result	
	returns [ResultSet camiContent]
	@init
	{
		Collection<IResult> results = new Vector<IResult>();
	}
	:
	'DE(' ensemble_name=CAMI_STRING ',' ensemble_type=number ')'
	(
	result_body
	{
		results.add($result_body.camiContent);
	}
	)+
	'FE()'
	{
		camiContent = new ResultSet(	$ensemble_name.text,
					ResultSet.ResultSetType($ensemble_type.value),
					results );
	}
	;
 
result_body
 	returns [IResult camiContent]
 	:
 	  result
 	  {
 		camiContent = $result.camiContent;
 	  }
 	| textual_result
 	  {
 		camiContent = $textual_result.camiContent;
 	  }
 	| attribute_change
 	  {
 		camiContent = $attribute_change.camiContent;
 	  }
 	| object_designation
 	  {
 		camiContent = $object_designation.camiContent;
 	  }
 	| object_outline
 	  {
 		camiContent = $object_outline.camiContent;
 	  }
 	| attribute_outline
 	  {
 		camiContent = $attribute_outline.camiContent;
 	  }
 	| object_creation
 	  {
 		camiContent = $object_creation.camiContent;
 	  }
 	| object_deletion
 	  {
 		camiContent = $object_deletion.camiContent;
 	  }
 	;
 
 textual_result
 	returns [IResult camiContent]
 	:
 	'RT(' CAMI_STRING ')'
 	{
 		camiContent = new TextualResult($CAMI_STRING.text);
 	}
 	;
 
 attribute_change
 	returns [IResult camiContent]
 	:
 	'WE(' id=number ',' attr_name=CAMI_STRING ',' new_value=CAMI_STRING ')'
 	{
 		camiContent = new AttributeChange($id.value,$attr_name.text,$new_value.text);
 	}
 	;
 
 object_designation
 	returns [IResult camiContent]
 	:
 	'RO(' id=number ')'
 	{
 		camiContent = new ObjectDesignation($id.value);
 	}
 	;
 
 object_outline
 	returns [IResult camiContent]
 	:
 	'ME(' id=number ')'
 	{
 		camiContent = new ObjectOutline($id.value);
 	}
 	;
 
 attribute_outline
 	returns [IResult camiContent]
 	:
 	'MT(' id=number ',' attr_name=CAMI_STRING ',' begin=number? ',' end=number? ')'
 	{
		camiContent = new AttributeOutline($id.value,$attr_name.text);
 	}
 	;
 
 object_creation
 	returns [IResult camiContent]
 	:
	  'CN(' node_box_type=CAMI_STRING ',' id=number ')'
	  {
	  	camiContent = new CreateNode($node_box_type.text,$id.value);
	  }
	| 'CB(' node_box_type=CAMI_STRING ',' id=number ',' page_id=number ')'
	  {
	  	camiContent = new CreateBox($node_box_type.text,$id.value,$page_id.value);
	  }
	| 'CA(' arc_type=CAMI_STRING ',' id=number ',' start_node=number ',' end_node=number ')'
	  {
	  	camiContent = new CreateArc($arc_type.text,$id.value,$start_node.value,$end_node.value);
	  }
	| 'CT(' attribute_name=CAMI_STRING ',' associated_node=number ',' value=CAMI_STRING ')'
	  {
	  	camiContent = new CreateMonolineAttribute($attribute_name.text,$associated_node.value,$value.text);
	  }
	| 'CM(' attribute_name=CAMI_STRING ',' associated_node=number ',' line_number=number ',' deprecated=number ',' value=CAMI_STRING ')'
	  {
	  	camiContent = new CreateMultilineAttribute($attribute_name.text,$associated_node.value,$line_number.value,$value.text);
	  }
 	;
 
object_deletion
 	returns [IResult camiContent]
	:
 	  'SU(' id=number ')'
 	  {
 	  	camiContent = new ObjectDeletion($id.value);
 	  }
 	| 'SI(' page_id=number ',' id=number ')'
 	  {
 	  	camiContent = new MultipleObjectDeletion($page_id.value,$id.value);
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
  	returns [ServiceMenuReception camiContent]
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
		questions.add($question_add.camiContent); 
	}
	)*
	'FQ()'
	{
		camiContent = new ServiceMenuReception($menu_name.camiContent, questions);
	}
	;

menu_name
	returns [MenuName camiContent]
	:
	'CQ(' name=CAMI_STRING ',' always_three=number ',' always_three=number ')'
	{
		camiContent = new MenuName($name.text);
	}
	;


service_menu_modification
 	returns [ServiceMenuModification camiContent]
 	@init
 	{
 		Collection<QuestionState> questionStates = new Vector<QuestionState>();
 	}
	:
	enable_main_question
	(
	question_state 
	{ 
		questionStates.add($question_state.camiContent); 
	}
	)*
	end_menu_transmission
	{
		camiContent = new ServiceMenuModification(	$enable_main_question.camiContent,
	                                                       	questionStates,
	                                                        $end_menu_transmission.camiContent);
	}
	;

enable_main_question
  	returns [EnableMainQuestion camiContent]
	:
	'VQ(' main_question_name=CAMI_STRING ')'
	{
		camiContent = new EnableMainQuestion($main_question_name.text);
	}
	;

disable_main_question
 	returns [DisableMainQuestion camiContent]
	:
	'EQ(' main_question_name=CAMI_STRING ')'
	{
		camiContent = new DisableMainQuestion($main_question_name.text);
	}
	;

end_menu_transmission
	returns [EndMenuTransmission camiContent]
	:
	'QQ(' number ')'
	{
		camiContent = new EndMenuTransmission(EndMenuTransmission.AckType($number.value));
	}
	;

help_question
 	returns [HelpQuestion camiContent]
	:
	'HQ(' question_name=CAMI_STRING ',' help_message=CAMI_STRING ')'
 	{
		camiContent = new HelpQuestion($question_name.text, $help_message.text);
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
