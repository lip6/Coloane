grammar ActionCosmosParser;

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

fragment LETTER : 'a'..'z' | 'A'..'Z' | '_' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ; 
SHARP: '#';