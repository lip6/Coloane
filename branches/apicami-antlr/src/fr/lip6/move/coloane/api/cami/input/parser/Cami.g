grammar Cami;

@parser::header{
package fr.lip6.move.coloane.api.camiParser;

import fr.lip6.move.coloane.api.camiCommands.*;
import fr.lip6.move.coloane.api.camiCommands.results.*;
import fr.lip6.move.coloane.api.session.states.*;
import fr.lip6.move.coloane.api.session.states.authentication.*;
import fr.lip6.move.coloane.api.camiCommands.results.*;
import fr.lip6.move.coloane.api.camiCommands.SpecialMessages.*;
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
	'MO(' message_type=number ',' message=CAMI_STRING ')'
	{
		camiContent = new SpecialMessages(SpecialMessages.SpecialMessageType($message_type.value),$message.text);
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
	returns [Results results]
	:
	'DR()'
	reply_to_question
	r+=( question_state | special_message | warning_message | result )*
	'FR(' number ')'
	{
	}
	;

reply_to_question
	:
	'RQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' number ')'
	;

question_state
	returns [QuestionState questionState]
	:
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=number ',' mess=CAMI_STRING? ')'
	{
		questionState = new QuestionState($service_name.text,$question_name.text,$state.value,$mess.text);
	}
	;

result	
	returns [ResultSet resultSet]
	:
	'DE(' ensemble_name=CAMI_STRING ',' ensemble_type=number ')'
	result_body+
	'FE()'
	;
 
result_body
 	returns [IResult res]
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
	| 'CM(' attribute_name=CAMI_STRING ',' associated_node=number ',' line_number=number ',' number ',' value=CAMI_STRING ')'
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
