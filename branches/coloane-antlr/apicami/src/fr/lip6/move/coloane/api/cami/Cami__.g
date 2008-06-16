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
T37 : 'RQ(' ;
T38 : 'ZA(' ;
T39 : 'DE(' ;
T40 : 'FE()' ;
T41 : 'DE()' ;
T42 : 'RT(' ;
T43 : 'RO(' ;
T44 : 'ME(' ;
T45 : 'MT(' ;
T46 : 'CN(' ;
T47 : 'CB(' ;
T48 : 'CA(' ;
T49 : 'CT(' ;
T50 : 'CM(' ;
T51 : 'SU(' ;
T52 : 'SI(' ;
T53 : 'TD(' ;
T54 : 'OB(' ;
T55 : 'AT(' ;
T56 : 'DB()' ;
T57 : 'FB()' ;
T58 : 'PO(' ;
T59 : 'pO(' ;
T60 : 'DC()' ;
T61 : 'AD(' ;
T62 : 'DS(' ;
T63 : 'CE(' ;
T64 : 'FF(' ;

// $ANTLR src "Cami.g" 715
CAMI_STRING
	@init{int nbToRead = 0;}
    	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':' 
	fs=FIXED_LENGTH_STRING[nbToRead]{setText($fs.text);}
	;

// $ANTLR src "Cami.g" 723
fragment
FIXED_LENGTH_STRING
	[int len]
	:   
	( { len > 0 }?=> .{len--;})* // Gated predicate : deactivate the '.' when len chars have been read
	;

// $ANTLR src "Cami.g" 730
NUMBER	: 	
	'0'..'9'+
	;


// $ANTLR src "Cami.g" 735
NEWLINE
 : 	
	( '\r'?'\n' )+ {skip();}
	;

// $ANTLR src "Cami.g" 740
EOF     :
	        {
         System.out.println("je parse EOOOFFFFF"); 
skip();}
	        ;

