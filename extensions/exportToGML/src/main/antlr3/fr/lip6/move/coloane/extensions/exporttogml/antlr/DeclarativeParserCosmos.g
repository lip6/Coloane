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
    Map<String, StringTemplate> initValues = new HashMap<String, StringTemplate>();
    public Map<String, String> getSymbols() { return symbols; }
    
    private StringTemplate getDef(String symbolsType, String gmlType){
      List<StringTemplate> tmpConst = new ArrayList();
      for (String x : symbols.keySet()){
        if(symbolsType.equals(symbols.get(x))){
          tmpConst.add(initValues.get(x));
        }
      }
      StringTemplate tmpConsts = templateLib.getInstanceOf("balise");
      tmpConsts.setAttribute("name", gmlType+"s");
      tmpConsts.setAttribute("content", tmpConst);
      return tmpConsts;
    }
    
  }

const_list
  : -> { %{""} }
  | a+=constdef (SEMICOLON a+=constdef)* (SEMICOLON)?
  {
    List<StringTemplate> tmp = new ArrayList();
    
    StringTemplate tmpConst = templateLib.getInstanceOf("balise");
    tmpConst.setAttribute("name", "constants");
    List<StringTemplate> tmpConsts = new ArrayList();
    tmpConsts.add(getDef("intconst","intConst"));
    tmpConsts.add(getDef("realconst","realConst"));
    tmpConst.setAttribute("content", tmpConsts) ;
    
    tmp.add(tmpConst);
    
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
      tmp2.setAttribute("name", "variable");
      tmp2.setAttribute("content", tmp1);
      
      initValues.put($c.getText(),tmp2);
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
  