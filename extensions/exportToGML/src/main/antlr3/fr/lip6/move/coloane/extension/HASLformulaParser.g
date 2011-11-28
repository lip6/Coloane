grammar HASLformulaParser;

options {
  language = Java;
  output = template;
}

@lexer::header {
  package main.antlr3.fr.lip6.move.coloane.extension;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
}

haslForm: AVG '(' e=algExpr ')' (';')? EOF
  -> balise(name={"HASLFormula"}, content={ $e.st });


algExpr: a=lhaFunc -> {$a.st};
  
  
lhaFunc:  
  LAST '(' a=linForm ')' -> {$a.st};

linForm:
  a=IDENTIFIER -> balise(name={"variable"}, content={ $a.getText() });


AVG: 'AVG';
LAST: 'last';

  
fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;