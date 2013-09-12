lexer grammar DeclarativePartLexer;

options {
  language = Java;
}

@header {
  package fr.lip6.move.coloane.extensions.exporttogrml.antlr;
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
EQUAL : '=';

CLASS : 'CLASS' | 'Class' | 'class' ;
CIRCULAR : 'CIRCULAR' | 'Circular' | 'circular' ;
IS : 'IS' | 'Is' | 'is' ;
IN : 'IN' | 'In' | 'in' ;
DOMAIN : 'DOMAIN' | 'Domain' | 'domain' ;
BAG : 'BAG' | 'Bag' | 'bag' ;
EQUIV : 'EQUIVALENCES' | 'Equivalences' | 'equivalences' ;
VAR : 'VAR' | 'Var' | 'var' ;
UNIQUE : 'UNIQUE' | 'Unique' | 'unique' ;
INT : 'int';
REAL : 'real';
CONST : 'CONST' | 'Const' | 'const';

// whitespaces and identifiers
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

fragment LETTER : 'a'..'z' | 'A'..'Z' | '_' ;
IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;

STRING : (LETTER | DIGIT) (LETTER | DIGIT | '.' | '_')* ;
  
  
  
  