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
T37 : 'FR(' ;
T38 : 'DE(' ;
T39 : 'FE()' ;
T40 : 'RT(' ;
T41 : 'WE(' ;
T42 : 'MT(' ;
T43 : 'RO(' ;
T44 : 'ME(' ;
T45 : 'SU(' ;
T46 : 'SI(' ;
T47 : 'DB()' ;
T48 : 'FB()' ;
T49 : 'CN(' ;
T50 : 'CB(' ;
T51 : 'CA(' ;
T52 : 'CT(' ;
T53 : 'CM(' ;
T54 : 'PO(' ;
T55 : 'pO(' ;
T56 : 'PO(-1,' ;
T57 : 'PT(' ;
T58 : 'PI(' ;
T59 : 'DC()' ;
T60 : 'FF()' ;
T61 : 'CE(' ;
T62 : 'DS(' ;
T63 : 'AD(' ;
T64 : 'HD(' ;
T65 : 'DG(' ;
T66 : 'MI(' ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 697
CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':'
	value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 705
NUMBER	:	('0'..'9')+;
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 706
NEWLINE :	( '\r'?'\n' )+ {skip();};

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 708
fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;
