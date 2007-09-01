grammar Cami;

@parser::header{
package fr.lip6.move.coloane.api.camiParser;

import fr.lip6.move.coloane.api.session.states.*;
import fr.lip6.move.coloane.api.session.states.authentication.*;
import fr.lip6.move.coloane.api.camiCommands.*;
import fr.lip6.move.coloane.api.camiCommands.types.*;
}

@lexer::header{
package fr.lip6.move.coloane.api.camiParser;
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
	:	
	'DC()'
	dialog_creation
	next_dialog+
	'FF()'
	;

dialog_creation
	:
	'CE(' number ',' number ',' number ','  CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' 
		number ',' number ',' CAMI_STRING? ')'
	;

next_dialog
	:
	'DS(' number ',' CAMI_STRING ')'
	;

display_dialog
	:
	'AD(' number ')'
	;
	
hide_dialog
	:
	'HD(' number ')'
	;
	
destroy_dialog
	:
	'DG(' number ')'
	;

interactive_response
	:
	'MI(' number ',' number ')'
	;


/*------------------------------------------------------------------
 * CAMI-LDPM - GUI Protocole description
 *------------------------------------------------------------------*/

// Traces and messages

message_to_user
	:
	trace_message | warning_message | special_message
	;

trace_message
	:
	'TR(' CAMI_STRING ')'
	;

warning_message
	:
	'WN(' CAMI_STRING ')'
	;

special_message
	returns [SpecialMessages camiContent]
	:	
	'MO(' number ',' CAMI_STRING ')'
	{
		SpecialMessageType messageType = null;
		switch ($number.value) {
			case 1:
				messageType = SpecialMessageType.motd;	
			break;
		case 2:
				messageType = SpecialMessageType.quickAndUrgentWarning;
			break;
		case 3:
				messageType = SpecialMessageType.copyrightMessage;
			break;
		case 4:
				messageType = SpecialMessageType.executionStatistics;
			break;
		default:
			break;
		}
		
		camiContent = new SpecialMessages(messageType,$CAMI_STRING.text);
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
	  		throw new MessageFormatFailure($special_message.camiContent);
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
	  		throw new MessageFormatFailure($special_message.camiContent);
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
	'OC(' n1=number ',' n2=number ')'
	{
		camiContent = new AckOpenConnection($n1.value,$n2.value);
	}
	;

close_connection_normal
	:
	'FC()'
	;
	
close_connection_panic
	returns [CloseConnectionPanic camiContent]
	:
	'KO(1,' CAMI_STRING ',' number ')'
	{
		camiContent = new CloseConnectionPanic($CAMI_STRING.text,$number.value);
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
	'VI(' service_name=CAMI_STRING ',' about_service=CAMI_STRING ',' '3' ',' new_model=number ')'
;

// Invokation of a service

pre_result_reception
	:
	question_state
	ask_hierarchic
	;

result_reception
	:
	'DR()'
	question_reply
	( question_state | special_message | warning_message | result )* 
	'FR(' number ')'
	;

question_reply
	:
	'RQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' number ')'
	;

question_state	
	:
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=number ',' mess=CAMI_STRING? ')'
	;

result	:	
	'DE(' ensemble_name=CAMI_STRING ',' ensemble_type=number ')'
	result_body+
	'FE()'
	;
 
result_body
 	:
 	  result
 	| textual_result
 	| attribute_change
 	| object_designation
 	| object_outline
 	| attribute_outline
 	| object_creation
 	| object_deletion
 	;
 
 textual_result
 	:
 	'RT(' CAMI_STRING ')'
 	;
 
 attribute_change
 	:
 	'WE(' id=number ',' attr_name=CAMI_STRING ',' new_value=CAMI_STRING ')'
 	;
 
 object_designation
 	:
 	'RO(' id=number ')'
 	;
 
 object_outline
 	:
 	'ME(' id=number ')'
 	;
 
 attribute_outline
 	:
 	'MT(' id=number ',' attr_name=CAMI_STRING ',' begin=number? ',' end=number? ')'
 	;
 
 object_creation
 	:
	  'CN(' CAMI_STRING ',' number ')'
	| 'CB(' CAMI_STRING ',' number ',' number ')'
	| 'CA(' CAMI_STRING ',' number ',' number ',' number ')'
	| 'CT(' CAMI_STRING ',' number ',' CAMI_STRING ')'
	| 'CM(' CAMI_STRING ',' number ',' number ',' number ',' CAMI_STRING ')'
 	;
 
object_deletion
	:
 	  'SU(' id=number ')'
 	| 'SI(' page_id=number ',' id=number ')'
 	;
 
// Session handler

ack_open_session
	returns [AckOpenSession camiContent]
	:
	'OS(' CAMI_STRING ')'
	'TD()'
	'FA()'
	interlocutor_table
	{
		camiContent = new AckOpenSession($CAMI_STRING.text);
	}
	;

ack_close_current_session 
	:	
	'FS()'
	;

ack_suspend_current_session 
	:	 
	'SS()'
	;

ack_resume_session
	returns [AckResumeSession camiContent]
	:
	'RS(' CAMI_STRING ')'
	{
		camiContent = new AckResumeSession($CAMI_STRING.text);
	}
	;

// Model asking

ask_and_get_model
	:
	  ask_for_a_model
	| model_definition
	| change_date
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

// Modification of a model's date

change_date
	:
	'MS(' number ')'
	;

// Service menu reception

service_menu_reception
	:
	'DQ()'
	menu_name
	question_add*
	'FQ()'
	;

menu_name
	:
	'CQ(' name=CAMI_STRING ',' number ',' number ')'
	;

question_add
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
		question_type=number? ',' question_behavior=number? ',' 
		set_item=number? ','  historic=number? ',' stop_authorized=number? ',' 
		ouput_formalism=CAMI_STRING? ',' active=number? ')'
	;

service_menu_modification
	:
	enable_main_question
	question_state*
	end_menu_transmission
	;

enable_main_question
	:
	'VQ(' main_question_name=CAMI_STRING ')'
	;

disable_main_question
	:
	'EQ(' main_question_name=CAMI_STRING ')'
	;

end_menu_transmission
	:
	'QQ(' number ')'
	;

help_question
	:
	'HQ(' question_name=CAMI_STRING ',' help_message=CAMI_STRING ')'
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
