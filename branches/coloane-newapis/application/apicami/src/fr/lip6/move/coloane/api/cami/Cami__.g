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
T38 : 'DE()' ;
T39 : 'DE(' ;
T40 : 'FE()' ;
T41 : 'RT(' ;
T42 : 'WE(' ;
T43 : 'MT(' ;
T44 : 'RO(' ;
T45 : 'ME(' ;
T46 : 'SU(' ;
T47 : 'SI(' ;
T48 : 'ZA(' ;
T49 : 'XA(' ;
T50 : 'TD(' ;
T51 : 'OB(' ;
T52 : 'AT(' ;
T53 : 'DB()' ;
T54 : 'FB()' ;
T55 : 'CN(' ;
T56 : 'CB(' ;
T57 : 'CA(' ;
T58 : 'CT(' ;
T59 : 'CM(' ;
T60 : 'PO(' ;
T61 : 'pO(' ;
T62 : 'PO(-1,' ;
T63 : 'PT(' ;
T64 : 'PI(' ;
T65 : 'pI(' ;
T66 : 'DC()' ;
T67 : 'FF()' ;
T68 : 'CE(' ;
T69 : 'DS(' ;
T70 : 'AD(' ;
T71 : 'HD(' ;
T72 : 'DG(' ;
T73 : 'MI(' ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 746
CAMI_STRING
	@init{int nbToRead = 0;}
	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);} ':' value=FIXED_LENGTH_STRING[nbToRead]{setText($value.text);}
	;
	
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 752
NUMBER	:	('0'..'9')+ ;
// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 753
NEWLINE :	( '\r'?'\n' )+ {skip();} ;

// $ANTLR src "/Users/jbvoron/Projets/coloane-newapis/fr.lip6.move.coloane.apicami/src/fr/lip6/move/coloane/api/cami/Cami.g" 755
fragment
FIXED_LENGTH_STRING [int len] :	({len > 0}?=> .{len--;})* ;
