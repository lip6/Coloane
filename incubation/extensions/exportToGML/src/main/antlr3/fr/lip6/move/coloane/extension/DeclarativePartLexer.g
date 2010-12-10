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

CLASS : 'CLASS' | 'Class' ;
CIRCULAR : 'CIRCULAR' | 'circular' ;
IS : 'IS' | 'is' ;
IN : 'IN' | 'in' ;
DOMAIN : 'DOMAIN' | 'Domain' ;
BAG : 'BAG' | 'Bag' ;
EQUIV : 'EQUIVALENCES' | 'Equivalences';
VAR : 'VAR' | 'Var' ;
UNIQUE : 'UNIQUE' | 'unique' ;

// whitespaces and identifiers
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

fragment LETTER : 'a'..'z' | 'A'..'Z' ;
IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
