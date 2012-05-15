grammar DeclarativeParserPTA;

options {
  language = Java;
  output = template;
}

@lexer::header {
  package fr.lip6.move.coloane.extensions.exporttogml.antlr;
}


@header {
  package fr.lip6.move.coloane.extensions.exporttogml.antlr;
  
  import java.util.Map;
  import java.util.HashMap;
}


name_list[String typename1,String typename2]
  : -> { %{""} }
  | a+=STRING (COMMA a+=STRING)*
  {
    List<StringTemplate> tmp = new ArrayList();
      
    for (Object x : $a) {
      StringTemplate tmpConst2 = templateLib.getInstanceOf("balise");
      tmpConst2.setAttribute("name", typename2);
      tmpConst2.setAttribute("content", (StringTemplate)x);
      tmp.add(tmpConst2); 
    }
    
  } -> balise(name={typename1}, content={ tmp })
  ;
  
  
  COMMA : ',' ;
  
  STRING : (LETTER | DIGIT) (LETTER | DIGIT | '.' | '_')* ;
  fragment LETTER : 'a'..'z' | 'A'..'Z' ;
  fragment DIGIT : '0'..'9' ;
  
  WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;