lexer grammar Cami;
@header {
package fr.lip6.move.coloane.api.cami;
            
        }

T8 : 'SC(' ;
T9 : ')' ;
T10 : 'OC(' ;
T11 : ',' ;
T12 : 'FC()' ;
T13 : 'OS(' ;
T14 : 'TD()' ;
T15 : 'FA()' ;
T16 : 'SS()' ;
T17 : 'RS(' ;
T18 : 'FS(' ;
T19 : 'TL()' ;
T20 : 'VI(' ;
T21 : 'FL()' ;
T22 : 'DQ()' ;
T23 : 'FQ()' ;
T24 : 'VQ(' ;
T25 : 'CQ(' ;
T26 : 'AQ(' ;
T27 : 'TQ(' ;
T28 : '7' ;
T29 : '8' ;
T30 : 'QQ(' ;
T31 : 'TR(' ;
T32 : 'WN(' ;
T33 : 'MO(' ;
T34 : 'KO(1,' ;
T35 : 'DF(-2,' ;
T36 : 'DR()' ;

// $ANTLR src "Cami.g" 528
CAMI_STRING
	@init{int nbToRead = 0;}
    	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':' 
	fs=FIXED_LENGTH_STRING[nbToRead]{setText($fs.text);}
	;

// $ANTLR src "Cami.g" 536
fragment
FIXED_LENGTH_STRING
	[int len]
	:   
	( { len > 0 }?=> .{len--;})* // Gated predicate : deactivate the '.' when len chars have been read
	;

// $ANTLR src "Cami.g" 543
NUMBER	: 	
	'0'..'9'+
	;


// $ANTLR src "Cami.g" 548
NEWLINE
 : 	
	( '\r'?'\n' )+ {skip();}
	;
