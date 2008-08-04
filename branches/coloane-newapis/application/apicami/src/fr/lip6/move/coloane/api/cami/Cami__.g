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
T33 : 'DR()' ;
T34 : 'RQ(' ;
T35 : 'DE(' ;
T36 : 'FE()' ;
T37 : 'RT(' ;
T38 : 'WE(' ;
T39 : 'MT(' ;
T40 : 'RO(' ;
T41 : 'ME(' ;
T42 : 'SU(' ;
T43 : 'SI(' ;
T44 : 'DB()' ;
T45 : 'FB()' ;
T46 : 'CN(' ;
T47 : 'CB(' ;
T48 : 'CA(' ;
T49 : 'CT(' ;
T50 : 'CM(' ;
T51 : 'PO(' ;
T52 : 'pO(' ;
T53 : 'PO(-1,' ;
T54 : 'PT(' ;
T55 : 'PI(' ;
T56 : 'DC()' ;
T57 : 'FF()' ;
T58 : 'CE(' ;
T59 : 'DS(' ;
T60 : 'AD(' ;
T61 : 'HD(' ;
T62 : 'DG(' ;
T63 : 'MI(' ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 574
CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':'
	value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 582
NUMBER	:	'0'..'9'+;
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 583
NEWLINE :	( '\r'?'\n' )+ {skip();};

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 585
fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;


