lexer grammar ValuationLexerSNB;

options {
  language = Java;
}

@header {
  package fr.lip6.move.coloane.extensions.exporttogrml.antlr;
}

LPAREN : '(' ;
RPAREN : ')' ;
LT : '<' ;
GT : '>' ;
LBRACE : '{' ;
RBRACE : '}' ;
PLUSPLUS : '++' ;
MINUSMINUS : '--' ;
PLUS : '+' ;
MINUS : '-' ;
TIMES : '*' ;
COMA : ',' ;
DOT : '.' ;
TILDE : '~' ;
ALL : 'ALL' | 'All' | 'all' ;
ORD : 'ORD' | 'Ord' | 'ord' ;
UNION : 'UNION' | 'Union' | 'union' ;
INTER : 'INTER' | 'Inter' | 'inter' ;
DIFF : 'DIFF' | 'Diff' | 'diff' ;

// whitespaces and identifiers
// whitespaces and identifiers
fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
