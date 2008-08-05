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
T29 : 'QQ(2)' ;
T30 : 'KO(' ;
T31 : 'TR(' ;
T32 : 'WN(' ;
T33 : 'MO(' ;
T34 : 'DF(-' ;
T35 : 'DR()' ;
T36 : 'RQ(' ;
T37 : 'DE(' ;
T38 : 'FE()' ;
T39 : 'RT(' ;
T40 : 'WE(' ;
T41 : 'MT(' ;
T42 : 'RO(' ;
T43 : 'ME(' ;
T44 : 'SU(' ;
T45 : 'SI(' ;
T46 : 'DB()' ;
T47 : 'FB()' ;
T48 : 'CN(' ;
T49 : 'CB(' ;
T50 : 'CA(' ;
T51 : 'CT(' ;
T52 : 'CM(' ;
T53 : 'PO(' ;
T54 : 'pO(' ;
T55 : 'PO(-1,' ;
T56 : 'PT(' ;
T57 : 'PI(' ;
T58 : 'DC()' ;
T59 : 'FF()' ;
T60 : 'CE(' ;
T61 : 'DS(' ;
T62 : 'AD(' ;
T63 : 'HD(' ;
T64 : 'DG(' ;
T65 : 'FR(' ;
T66 : 'MI(' ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 691
CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':'
	value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 699
NUMBER	:	('0'..'9')+;
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 700
NEWLINE :	( '\r'?'\n' )+ {skip();};

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 702
fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;
