grammar HASLformulaParser;

options {
  language = Java;
  output = template;
}

@lexer::header {
  package fr.lip6.move.coloane.extensions.exporttogml.antlr;
}

@header {
  package fr.lip6.move.coloane.extensions.exporttogml.antlr;
}


haslFormW:
a=haslForm EOF -> balise(name={"HASL Formula"}, content={ $a.st });

haslForm: AVG '(' e=algExpr ')' (';')?
  -> balise(name={"AVG"}, content={ $e.st });


algExpr: a=lhaFunc -> balise(name={"YHF"}, content = {$a.st});
  
lhaFunc:  
  LAST '(' a=linForm ')' 
  -> balise(name={"last"}, content={ $a.st });

linForm:
  a=IDENTIFIER -> exprbalise(name={"name"}, content={ $a.getText() });


AVG: 'AVG';
LAST: 'last';

  
fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;