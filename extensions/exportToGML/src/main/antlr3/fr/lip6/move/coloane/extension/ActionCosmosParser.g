grammar ActionCosmosParser;

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


action:
  ALL EOF -> balise(name={"action"}, content={ "ALL" })
  | SHARP EOF -> balise(name={"action"}, content={ "#" })
  | a+=actionname (',' a+=actionname)* EOF
  {
    List<StringTemplate> tmp = new ArrayList();
    for (Object x : $a) {
      tmp.add((StringTemplate)x);
    }
    
  } -> balise(name={"action"}, content={ tmp })
  ;
  
  actionname
  : a=IDENTIFIER  {
      
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "actionName");
      tmp.setAttribute("content", $a.getText());
      } -> delist(arg={tmp})
      ;
   
ALL: 'all' | 'ALL';

fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ; 
SHARP: '#';