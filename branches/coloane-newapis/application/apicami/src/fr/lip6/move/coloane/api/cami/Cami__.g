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
T47 : 'ZA(' ;
T48 : 'XA(' ;
T49 : 'TD(' ;
T50 : 'OB(' ;
T51 : 'AT(' ;
T52 : 'DB()' ;
T53 : 'FB()' ;
T54 : 'CN(' ;
T55 : 'CB(' ;
T56 : 'CA(' ;
T57 : 'CT(' ;
T58 : 'CM(' ;
T59 : 'PO(' ;
T60 : 'pO(' ;
T61 : 'PO(-1,' ;
T62 : 'PT(' ;
T63 : 'PI(' ;
T64 : 'DC()' ;
T65 : 'FF()' ;
T66 : 'CE(' ;
T67 : 'DS(' ;
T68 : 'AD(' ;
T69 : 'HD(' ;
T70 : 'DG(' ;
T71 : 'MI(' ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 714
CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':'
	value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 722
NUMBER	:	('0'..'9')+;
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 723
NEWLINE :	( '\r'?'\n' )+ {skip();};

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 725
fragment
FIXED_LENGTH_STRING
	[int len]
	:
	({len > 0}?=> .{len--;})*
	;
