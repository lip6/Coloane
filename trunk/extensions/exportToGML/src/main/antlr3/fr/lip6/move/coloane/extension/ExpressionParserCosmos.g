grammar ExpressionParserCosmos;

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


intExpr:
   e=multIntExpr 
   (                 -> {$e.st}
   | '+' e2=intExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"iplus"}, content={ tmplist })
   | '-' e2=intExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"iminus"}, content={ tmplist })
   ) EOF
   ;

multIntExpr
  : e=intAtom 
  (
                  -> {$e.st}
  | '*' e2=multIntExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"imult"}, content={ tmplist })
  )
  ;

intAtom: 
   i=IDENTIFIER -> balise(name={"intConst"}, content={ $i.getText() }) 
  | i=INTEGER -> balise(name={"value"}, content={ $i.getText() }) 
  | '(' e=intExpr ')' -> {$e.st}
  ;



fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
