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


const_list[boolean hasConstants, boolean hasVariables]
  : -> { %{""} }
  | a+=constdef (SEMICOLON a+=constdef)* (SEMICOLON)?
  {
    List<StringTemplate> tmp = new ArrayList();
    
    if(hasConstants) {
      StringTemplate tmpConst = templateLib.getInstanceOf("balise");
      tmpConst.setAttribute("name", "constants");
      List<StringTemplate> tmpConsts = new ArrayList();
      tmpConsts.add(getDef("intconst","intConst"));
      tmpConsts.add(getDef("realconst","realConst"));
      tmpConst.setAttribute("content", tmpConsts) ;
      tmp.add(tmpConst);
    }
    
    if(hasVariables) {
      StringTemplate tmpConst = templateLib.getInstanceOf("balise");
      tmpConst.setAttribute("name", "variables");
      List<StringTemplate> tmpConsts = new ArrayList();
      tmpConsts.add(getDef("variable","real"));
      tmpConsts.add(getDef("discvariable","discrete"));
      tmpConst.setAttribute("content", tmpConsts) ;
      tmp.add(tmpConst);
    }
    
    
  } -> balise(name={"declaration"}, content={ tmp })
  ;
  
  
  constdef
  : INT c=STRING EQUAL s=STRING  {
      symbols.put($c.getText(),"intconst");
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "name");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "expr");
      StringTemplate tmp3 = templateLib.getInstanceOf("balise");
      tmp3.setAttribute("name", "numValue");
      tmp3.setAttribute("content", $s.getText());
      
      tmp2.setAttribute("content", tmp3);
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add(tmp2);
      
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "intConst");
      tmp.setAttribute("content", tmplist);
      initValues.put($c.getText(),tmp);
      
      } -> delist(arg={tmp})
      
      //balise(name={"intConstDeclaration"}, content={ tmplist })
      
  | REAL c=STRING EQUAL s=STRING  {
      symbols.put($c.getText(),"realconst");
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "name");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "expr");
      
      StringTemplate tmp3 = templateLib.getInstanceOf("balise");
      tmp3.setAttribute("name", "numValue");
      tmp3.setAttribute("content", $s.getText());
      
      tmp2.setAttribute("content", tmp3);
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add(tmp2);
      
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "realConst");
      tmp.setAttribute("content", tmplist);
      initValues.put($c.getText(),tmp);
      
      } -> delist(arg={tmp})
      
   | VARIABLE c=STRING  {
      symbols.put($c.getText(),"variable");
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "name");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "real");
      tmp2.setAttribute("content", tmp1);
      
      initValues.put($c.getText(),tmp2);
      } -> delist(arg={tmp2})
      //balise(name={"realConstDeclaration"}, content={ tmplist })
      
    | DISCRETEVARIABLE c=STRING  {
      symbols.put($c.getText(),"discvariable");
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "name");
      tmp1.setAttribute("content", $c.getText());
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "discrete");
      tmp2.setAttribute("content", tmp1);
      
      initValues.put($c.getText(),tmp2);
      } -> delist(arg={tmp2})
  ;
  

  VARIABLE : 'var';
  
  DISCRETEVAR : 'discvar';
  EQUAL : '=';
  SEMICOLON : ';' ;
  COMMA : ',' ;
  INT : 'int';
  REAL : 'real';
  
  STRING : (LETTER | DIGIT) (LETTER | DIGIT | '.' | '_')* ;
  fragment LETTER : 'a'..'z' | 'A'..'Z' ;
  fragment DIGIT : '0'..'9' ;
  
  WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;