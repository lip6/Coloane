lexer grammar Cami;
options {
  language=Java;

}
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
T16 : 'TL()' ;
T17 : 'FL()' ;
T18 : 'VI(' ;
T19 : 'SS()' ;
T20 : 'RS(' ;
T21 : 'FS()' ;
T22 : 'QQ(3)' ;
T23 : 'DQ()' ;
T24 : 'FQ()' ;
T25 : 'VQ(' ;
T26 : 'CQ(' ;
T27 : 'AQ(' ;
T28 : 'TQ(' ;
T29 : 'KO(1' ;
T30 : 'TR(' ;
T31 : 'WN(' ;
T32 : 'MO(' ;
T33 : 'DF(-2,' ;
T34 : 'DR()' ;
T35 : 'RQ(' ;
T36 : 'DE(' ;
T37 : 'FE()' ;
T38 : 'RT(' ;
T39 : 'WE(' ;
T40 : 'MT(' ;
T41 : 'RO(' ;
T42 : 'ME(' ;
T43 : 'SU(' ;
T44 : 'SI(' ;
T45 : 'DB()' ;
T46 : 'FB()' ;
T47 : 'CN(' ;
T48 : 'CB(' ;
T49 : 'CA(' ;
T50 : 'CT(' ;
T51 : 'CM(' ;
T52 : 'PO(' ;
T53 : 'pO(' ;
T54 : 'PO(-1,' ;
T55 : 'PT(' ;
T56 : 'PI(' ;
T57 : 'DC()' ;
T58 : 'FF()' ;
T59 : 'CE(' ;
T60 : 'DS(' ;
T61 : 'AD(' ;
T62 : 'HD(' ;
T63 : 'DG(' ;
T64 : 'FR(' ;
T65 : 'MI(' ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 658
CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':'
	value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 666
NUMBER	:	'0'..'9'+;
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 667
NEWLINE :	( '\r'?'\n' )+ {skip();};

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 669
fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;


