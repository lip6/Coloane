grammar DeclarativeParserCosmos;

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

@members {
    Map<String, String> symbols = new HashMap<String, String>();
    public Map<String, String> getSymbols() { return symbols; }
  }

const_list
  : -> { %{""} }
  | a+=constdef (SEMICOLON a+=constdef)* (SEMICOLON)?
  {
    List<StringTemplate> tmp = new ArrayList();
    for (Object x : $a) {
      tmp.add((StringTemplate)x);
    }
    
  } -> balise(name={"declarations"}, content={ tmp })
  ;
  
  constdef
  : INT c=STRING EQUAL s=STRING  {
      symbols.put($c.getText(),"intconst");
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "constName");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "intFormula");
      StringTemplate tmp3 = templateLib.getInstanceOf("balise");
      tmp3.setAttribute("name", "value");
      tmp3.setAttribute("content", $s.getText());
      
      tmp2.setAttribute("content", tmp3);
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add(tmp2);
      
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "intConstDeclaration");
      tmp.setAttribute("content", tmplist);
      
      
      } -> delist(arg={tmp})
      
      //balise(name={"intConstDeclaration"}, content={ tmplist })
      
  | REAL c=STRING EQUAL s=STRING  {
      symbols.put($c.getText(),"realconst");
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "constName");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "realFormula");
      
      StringTemplate tmp3 = templateLib.getInstanceOf("balise");
      tmp3.setAttribute("name", "value");
      tmp3.setAttribute("content", $s.getText());
      
      tmp2.setAttribute("content", tmp3);
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add(tmp2);
      
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "realConstDeclaration");
      tmp.setAttribute("content", tmplist);
      
      } -> delist(arg={tmp})
      
   | VARIABLE c=STRING  {
      symbols.put($c.getText(),"variable");
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "varName");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "variable");
      tmp2.setAttribute("content", tmp1);
      
      } -> delist(arg={tmp2})
      //balise(name={"realConstDeclaration"}, content={ tmplist })
  ;
  

  VARIABLE : 'var';
  EQUAL : '=';
  SEMICOLON : ';' ;
  COMMA : ',' ;
  INT : 'int';
  REAL : 'real';
  
  STRING : (LETTER | DIGIT) (LETTER | DIGIT | '.' | '_')* ;
  fragment LETTER : 'a'..'z' | 'A'..'Z' ;
  fragment DIGIT : '0'..'9' ;
  
  WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
  