lexer grammar GuardLexer;

options {
  language = Java;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
}

LHOOK : '[' ;
RHOOK : ']' ;
LPAREN : '(' ;
RPAREN : ')' ;
LBRACE : '{' ;
RBACE : '}' ;
DOT : '.' ;
PLUSPLUS : '++' ;
MINUSMINUS : '--' ;
EQ : '=' ;
NEQ : '<>' ;
LEQ : '<=' ;
GEQ : '>=' ;
LT : '<' ;
GT : '>' ;

TRUE : 'TRUE' ;
FALSE : 'FALSE' ;
NOT : 'NOT' ;
AND : 'AND' ;
OR : 'OR' ;
UNIQUE : 'UNIQUE' ;
CARD : 'CARD' ;
ALL : 'ALL' ;

// whitespaces and identifiers
fragment LETTER : 'a'..'z' | 'A'..'Z' ;
IDENTIFIER : (LETTER)+ ;

fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
