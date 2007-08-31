grammar Cami;

@header{
package fr.lip6.move.coloane.api.camiParser;

import fr.lip6.move.coloane.api.session.states.authentication.*;
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
	'CN(' CAMI_STRING ',' NUMBER ')'
	;
	
box	:
	'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
	;
	
arc	:
	'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
	;

textual_attribute
	:
	  'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
	| 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
	;


aestetic
	:
	object_position | text_position | intermediary_point
	;

object_position
	:
	  'PO(' id=NUMBER ',' h_distance=NUMBER ',' v_distance=NUMBER ')'
	| 'pO(' id=NUMBER ',' h_distance=NUMBER ',' v_distance=NUMBER ')'
	| 'PO(-1,' id=NUMBER ',' left=NUMBER ',' right=NUMBER ',' top=NUMBER ',' bottom=NUMBER')'
	;

text_position
	:	
	'PT(' id=NUMBER ',' name_attr=CAMI_STRING ',' h_distance=NUMBER ',' v_distance=NUMBER ')'
	;

intermediary_point
	:	
	'PI(' NUMBER ',' NUMBER ',' NUMBER ')'
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
	'CE(' NUMBER ',' NUMBER ',' NUMBER ','  CAMI_STRING ',' CAMI_STRING ',' CAMI_STRING ',' 
		NUMBER ',' NUMBER ',' CAMI_STRING? ')'
	;

next_dialog
	:
	'DS(' NUMBER ',' CAMI_STRING ')'
	;

display_dialog
	:
	'AD(' NUMBER ')'
	;
	
hide_dialog
	:
	'HD(' NUMBER ')'
	;
	
destroy_dialog
	:
	'DG(' NUMBER ')'
	;

interactive_response
	:
	'RI(' NUMBER ',' NUMBER ')'
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
	:	
	'MO(' NUMBER ',' CAMI_STRING ')'
	;
 

// Connection handler

open_communication
	returns [AuthenticationAck message]
	:
	  ack_open_communication 
	  {
	  	message = new AuthenticationAck();
	  }
	| close_connection_panic
	  {
	  	if( true ) // to avoid an error in the generated code
		  	throw new AuthenticationFailure($close_connection_panic.s);
	  }
	;

check_version
	returns [AuthenticationAck message]
	:
	  ack_open_connection
	  {
	  	message = new AuthenticationAck();  
	  }
	| close_connection_panic
	  {
	  	if(true) // to avoid an error in the generated code
	  		throw new VersionFailure($close_connection_panic.s);
	  }
	;

ack_open_communication
	:
	'SC(' CAMI_STRING ')' 
	;
	
ack_open_connection
	:
	'OC(' NUMBER ',' NUMBER ')'
	;

close_connection_normal
	:
	'FC()'
	;
	
close_connection_panic
	returns [String s]
	:
	'KO(1,' mess=CAMI_STRING ',' level=NUMBER ')'
	{
		s=$mess.text;
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
	'VI(' service_name=CAMI_STRING ',' about_service=CAMI_STRING ',' '3' ',' new_model=NUMBER ')'
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
	'FR(' NUMBER ')'
	;

question_reply
	:
	'RQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' NUMBER ')'
	;

question_state	
	:
	'TQ(' service_name=CAMI_STRING ',' question_name=CAMI_STRING ',' state=NUMBER ',' mess=CAMI_STRING? ')'
	;

result	:	
	'DE(' ensemble_name=CAMI_STRING ',' ensemble_type=NUMBER ')'
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
 	'WE(' id=NUMBER ',' attr_name=CAMI_STRING ',' new_value=CAMI_STRING ')'
 	;
 
 object_designation
 	:
 	'RO(' id=NUMBER ')'
 	;
 
 object_outline
 	:
 	'ME(' id=NUMBER ')'
 	;
 
 attribute_outline
 	:
 	'MT(' id=NUMBER ',' attr_name=CAMI_STRING ',' begin=NUMBER? ',' end=NUMBER? ')'
 	;
 
 object_creation
 	:
	  'CN(' CAMI_STRING ',' NUMBER ')'
	| 'CB(' CAMI_STRING ',' NUMBER ',' NUMBER ')'
	| 'CA(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ')'
	| 'CT(' CAMI_STRING ',' NUMBER ',' CAMI_STRING ')'
	| 'CM(' CAMI_STRING ',' NUMBER ',' NUMBER ',' NUMBER ',' CAMI_STRING ')'
 	;
 
object_deletion
	:
 	  'SU(' id=NUMBER ')'
 	| 'SI(' page_id=NUMBER ',' id=NUMBER ')'
 	;
 
// Session handler

ack_open_session
	:
	'OS(' CAMI_STRING')'
	'TD()'
	'FA()'
	interlocutor_table
	;

ack_close_current_session 
	:	
	'FS()'
	;

ack_suspend_current_session 
	:	 
	'SS()'
	;

ack_resume_suspend_current_session
	:
	'RS(' CAMI_STRING ')'
	;

// Model asking

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
	'DF(-2,' NUMBER ',' NUMBER ')'
	;

// Modification of a model's date

change_date
	:
	'MS(' NUMBER ')'
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
	'CQ(' name=CAMI_STRING ',' NUMBER ',' NUMBER ')'
	;

question_add
	:
	'AQ(' parent_menu=CAMI_STRING ',' entry_name=CAMI_STRING ',' 
		question_type=NUMBER? ',' question_behavior=NUMBER? ',' 
		set_item=NUMBER? ','  historic=NUMBER? ',' stop_authorized=NUMBER? ',' 
		ouput_formalism=CAMI_STRING? ',' active=NUMBER? ')'
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
	'QQ(' NUMBER ')'
	;

help_question
	:
	'HQ(' question_name=CAMI_STRING ',' help_message=CAMI_STRING ')'
	;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

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
	
NUMBER	: 	
	'0'..'9'+
	;

NEWLINE : 	
	( '\r'?'\n' )+ {skip();}
	;

EOF	:
	{skip();}
	;
