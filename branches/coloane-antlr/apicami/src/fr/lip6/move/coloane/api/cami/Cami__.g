lexer grammar Cami;
@header {
package fr.lip6.move.coloane.api.cami;
            
        }

T8 : 'SC(' ;
T9 : ')' ;
T10 : 'OC(' ;
T11 : ',' ;
T12 : 'OS(' ;
T13 : 'TD()' ;
T14 : 'FA()' ;
T15 : 'TL()' ;
T16 : 'VI(' ;
T17 : 'FL()' ;
T18 : 'DQ()' ;
T19 : 'FQ()' ;
T20 : 'VQ(' ;
T21 : 'CQ(' ;
T22 : 'AQ(' ;
T23 : 'TQ(' ;
T24 : '7' ;
T25 : '8' ;
T26 : 'QQ(' ;
T27 : 'MO(' ;

// $ANTLR src "Cami.g" 340
CAMI_STRING
	@init{int nbToRead = 0;}
    	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':' 
	fs=FIXED_LENGTH_STRING[nbToRead]{setText($fs.text);}
	;

// $ANTLR src "Cami.g" 348
fragment
FIXED_LENGTH_STRING
	[int len]
	:   
	( { len > 0 }?=> .{len--;})* // Gated predicate : deactivate the '.' when len chars have been read
	;

// $ANTLR src "Cami.g" 355
NUMBER	: 	
	'0'..'9'+
	;


// $ANTLR src "Cami.g" 360
NEWLINE
 : 	
	( '\r'?'\n' )+ {skip();}
	;
