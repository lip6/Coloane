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
   (                 -> {$e}
   | '+' e2=intExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e);
    tmplist.add($e2);   
   } -> balise(name={"iplus"}, content={ tmplist })
   | '-' e2=intExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e);
    tmplist.add($e2);   
   } -> balise(name={"iminus"}, content={ tmplist })
   )
   ;

multIntExpr
  : e=intAtom 
  (
                  -> {$e}
  | '*' e2=multIntExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e);
    tmplist.add($e2);   
   } -> balise(name={"imult"}, content={ tmplist })
  )
  ;

intAtom: 
   i=IDENTIFIER -> balise(name={"intConst"}, content={ $i.getText() }) 
  | i=INTEGER -> balise(name={"value"}, content={ $i.getText() }) 
  | '(' e=intExpr ')' -> {$e}
  ;



fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
