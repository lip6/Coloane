lexer grammar Cami;
options {
  language=Java;

}
@header {
package fr.lip6.move.coloane.api.cami;
}

T7 : 'SC(' ;
T8 : ')' ;
T9 : 'OC(' ;
T10 : ',' ;
T11 : 'FC()' ;
T12 : 'OS(' ;
T13 : 'TD()' ;
T14 : 'FA()' ;
T15 : 'TL()' ;
T16 : 'FL()' ;
T17 : 'VI(' ;
T18 : 'SS()' ;
T19 : 'RS(' ;
T20 : 'FS()' ;
T21 : 'QQ(3)' ;
T22 : 'DQ()' ;
T23 : 'FQ()' ;
T24 : 'VQ(' ;
T25 : 'CQ(' ;
T26 : 'AQ(' ;
T27 : 'TQ(' ;
T28 : 'KO(1' ;
T29 : 'TR(' ;
T30 : 'WN(' ;
T31 : 'MO(' ;
T32 : 'DR()' ;
T33 : 'RQ(' ;
T34 : 'DE(' ;
T35 : 'FE()' ;
T36 : 'RT(' ;
T37 : 'WE(' ;
T38 : 'MT(' ;
T39 : 'RO(' ;
T40 : 'ME(' ;
T41 : 'SU(' ;
T42 : 'SI(' ;
T43 : 'DB()' ;
T44 : 'FB()' ;
T45 : 'CN(' ;
T46 : 'CB(' ;
T47 : 'CA(' ;
T48 : 'CT(' ;
T49 : 'CM(' ;
T50 : 'PO(' ;
T51 : 'pO(' ;
T52 : 'PO(-1,' ;
T53 : 'PT(' ;
T54 : 'PI(' ;
T55 : 'DC()' ;
T56 : 'FF()' ;
T57 : 'CE(' ;
T58 : 'DS(' ;
T59 : 'AD(' ;
T60 : 'HD(' ;
T61 : 'DG(' ;
T62 : 'MI(' ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 578
CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':'
	value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 586
NUMBER
	:
	'0'..'9'+
	;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 591
fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 598
EOF	
	:
	{skip();}
	;

