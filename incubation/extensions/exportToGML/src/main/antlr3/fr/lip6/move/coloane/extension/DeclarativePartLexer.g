lexer grammar DeclarativePartLexer;

options {
  language = Java;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
}

LPAREN : '(' ;
RPAREN : ')' ;
LHOOK : '[' ;
RHOOK : ']' ;
COMA : ',' ;
COLON : ':' ;
SEMICOLON : ';' ;
DOUBLEDOT : DOT DOT ;
DOT : '.' ;
LT : '<' ;
GT : '>' ;

CLASS : 'CLASS' ;
CIRCULAR : 'CIRCULAR' ;
IS : 'IS' ;
IN : 'IN' ;
DOMAIN : 'DOMAIN' ;
BAG : 'BAG' ;
EQUIV : 'EQUIVALENCES' ;
VAR : 'VAR' ;
UNIQUE : 'UNIQUE' ;

// whitespaces and identifiers
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

fragment LETTER : 'a'..'z' | 'A'..'Z' ;
IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
