grammar DeclarativeParserCosmos;

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

const_list
  : -> { %{""} }
  | a+=constdef (SEMICOLON a+=constdef)* (SEMICOLON)?
  {
    List<StringTemplate> tmp = new ArrayList();
    for (Object x : $a) {
      tmp.add((StringTemplate)x);
    }
    
  } -> balise(name={"constDeclaration"}, content={ tmp })
  ;
  
  constdef
  : INT c=STRING EQUAL s=STRING  {
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "constName");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "intFormula");
      tmp2.setAttribute("content", $s.getText());
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add(tmp2);
      
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "intConstDeclaration");
      tmp.setAttribute("content", tmplist);
      
      
      } -> delist(arg={tmp})
      
      //balise(name={"intConstDeclaration"}, content={ tmplist })
      
  | REAL c=STRING EQUAL s=STRING  {
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "constName");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "realFormula");
      tmp2.setAttribute("content", $s.getText());
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add(tmp2);
      
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "realConstDeclaration");
      tmp.setAttribute("content", tmplist);
      
      } -> delist(arg={tmp})
      
      //balise(name={"realConstDeclaration"}, content={ tmplist })
  ;
  
  EQUAL : '=';
  SEMICOLON : ';' ;
  INT : 'int';
  REAL : 'real';
  
  STRING : (LETTER | DIGIT) (LETTER | DIGIT | '.' | '_')* ;
  fragment LETTER : 'a'..'z' | 'A'..'Z' ;
  fragment DIGIT : '0'..'9' ;
  
  WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
  