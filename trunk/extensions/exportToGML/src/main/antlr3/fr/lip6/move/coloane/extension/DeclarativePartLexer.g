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

CLASS : 'CLASS' | 'Class' | 'class' ;
CIRCULAR : 'CIRCULAR' | 'Circular' | 'circular' ;
IS : 'IS' | 'Is' | 'is' ;
IN : 'IN' | 'In' | 'in' ;
DOMAIN : 'DOMAIN' | 'Domain' | 'domain' ;
BAG : 'BAG' | 'Bag' | 'bag' ;
EQUIV : 'EQUIVALENCES' | 'Equivalences' | 'equivalences' | 'EQUIV' | 'Equiv' | 'equiv' ;
VAR : 'VAR' | 'Var' | 'var' ;
UNIQUE : 'UNIQUE' | 'Unique' | 'unique' ;

// whitespaces and identifiers
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

fragment LETTER : 'a'..'z' | 'A'..'Z' | '_' ;
IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
