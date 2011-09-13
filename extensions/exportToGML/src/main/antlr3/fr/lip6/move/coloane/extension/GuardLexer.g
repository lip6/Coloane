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

TRUE : 'TRUE' | 'True' | 'true' ;
FALSE : 'FALSE' | 'False' | 'false' ;
NOT : 'NOT' | 'Not' | 'not' ;
AND : 'AND' | 'And' | 'and' ;
OR : 'OR' | 'Or' | 'or' ;
UNIQUE : 'UNIQUE' | 'Unique' | 'unique' ;
CARD : 'CARD' | 'Card' | 'card' ;
ALL : 'ALL' | 'All' | 'all' ;
INCLUDED : 'INCLUDED' | 'Included' | 'included' ;
STRICTINCLUDED : 'STRICTINCLUDED' | 'Strictincluded' | 'StrictIncluded' | 'strictincluded' ;
IN : 'IN' | 'In' | 'in' ;

// whitespaces and identifiers
// whitespaces and identifiers
fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
