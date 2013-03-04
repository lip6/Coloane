grammar ExpressionParserCosmos;


options {
  language = Java;
  output = template;
}

@lexer::header {
  package fr.lip6.move.coloane.extensions.exporttogrml.antlr;
}

@header {
  package fr.lip6.move.coloane.extensions.exporttogrml.antlr;
  
  import java.util.Map;
  import java.util.HashMap;
}

@members {
  Map<String,String> symbols;
    //private boolean is_constante(String id) { return ("intconst".equals(symbols.get(id)) | "realconst".equals(symbols.get(id))); } 
    //private boolean is_int_constante(String id) { return "intconst".equals(symbols.get(id)); } 
    private boolean is_variable(String id) { return "variable".equals(symbols.get(id)); } 
    
  }

  
distribution[Map<String,String> st]
@init {
  symbols = st;
} 
  : s=IDENTIFIER '(' a=arg_list ')'
  {
    StringTemplate tmp = templateLib.getInstanceOf("balise");
    tmp.setAttribute("name", "type");
    tmp.setAttribute("content", $s.getText());
    List<StringTemplate> listtmp = new ArrayList<StringTemplate>();
    listtmp.add(tmp);
    listtmp.add($a.st);
  } -> balise(name={"distribution"}, content={ listtmp })
  ;
  
arg_list
  : -> { %{""} }
  | a+=realExpr (',' a+=realExpr)*
  {
    List<StringTemplate> tmp = new ArrayList();
    int compteur = 0;
    for (Object x : $a) {
      /*StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "number");
      tmp1.setAttribute("content", compteur);
     // StringTemplate tmp2 = templateLib.getInstanceOf("balise");
     // tmp2.setAttribute("name", "expr");
     // tmp2.setAttribute("content", x);
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add((StringTemplate)x);*/
      
      StringTemplate tmp0 = templateLib.getInstanceOf("balise");
      tmp0.setAttribute("name", "param");
      tmp0.setAttribute("content", (StringTemplate)x);
      tmp.add(tmp0);
      ++compteur;
    }
  } -> delist(arg={tmp})
  ;
  
  
realExprW[Map<String,String> s]
@init {
  symbols = s;
} :
  e=realExpr EOF -> {$e.st};

realExpr :
   e=multRealExpr 
   (                 -> {$e.st}
   | ('+' e2=realExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> funbalise(name={"+"}, content={ tmplist })
   | '-' e2=realExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> funbalise(name={"-"}, content={ tmplist })
   )) 
   ;

multRealExpr
  : e=realAtom 
  (
                  -> {$e.st}
  | ('*' e2=realAtom {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> funbalise(name={"*"}, content={ tmplist })
  | '/' e2=realAtom {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> funbalise(name={"/"}, content={ tmplist })
  )+)
  ;

realAtom: 
   i=IDENTIFIER ->  exprbalise(name={"name"}, content={ $i.getText() })
  | i=INTEGER -> exprbalise(name={"numValue"}, content={ $i.getText() })
  | '(' e=realExpr ')' -> {$e.st}
  | MAX '(' a=realExpr ',' b=realExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> funbalise(name={"max"}, content={ tmplist })
  | MIN '(' a=realExpr ',' b=realExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> funbalise(name={"min"}, content={ tmplist })
  | POWER '(' a=realExpr ',' b=realExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> funbalise(name={"power"}, content={ tmplist })
  ;

boolExprW[Map<String,String> s]
@init {
  symbols = s;
} :
  e=boolExpr EOF -> { $e.st };

boolExpr:
   e=atomBoolExpr
   (                 -> {$e.st}
   | ('&' e2=boolExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> boolbalise(name={"and"}, content={ tmplist })
   | '|' e2=boolExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> boolbalise(name={"or"}, content={ tmplist })
   )) 
   ;
   
atomBoolExpr:
 '(' e=boolExpr ')' -> {$e.st}
  | '['a=realExpr (o='<>' | o='<' | o='>' | o='<=' | o='>=' | o='=') b=realExpr']' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);
    String op = "";
    if($o.getText().equals("<>"))op="notEqual";
    if($o.getText().equals("="))op="equal";
    if($o.getText().equals("<"))op="less";
    if($o.getText().equals(">"))op="greater";
    if($o.getText().equals("<="))op="lessEqual";
    if($o.getText().equals(">="))op="greaterEqual";
  } -> boolbalise(name={op}, content={ tmplist })
  | TRUE -> boolbalise(name={"boolValue"}, content={"true"})
  | FALSE -> boolbalise(name={"boolValue"}, content={"false"});
  
   
update[Map<String,String> s]
@init {
  symbols = s;
} :
  EOF -> balise(name={"updates"}, content={ "" })
  | a+=updatevar (';' a+=updatevar)* (';')? EOF
  {
    List<StringTemplate> tmp = new ArrayList();
    for (Object x : $a) {
      tmp.add((StringTemplate)x);
    }
    
  } -> balise(name={"updates"}, content={ tmp })
  ;
  
  updatevar
  : a=IDENTIFIER '=' e=realExpr {
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "name");
      tmp.setAttribute("content", $a.getText());
  
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp);
      tmplist.add($e.st);
  
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "update");
      tmp2.setAttribute("content", tmplist);
      } -> delist(arg={tmp2})
      ;

flow[Map<String,String> s]
@init {
  symbols = s;
} :
  EOF -> balise(name={"flows"}, content={ "" })
  | a+=flowvar (';' a+=flowvar)* (';')? EOF
  {
    List<StringTemplate> tmp = new ArrayList();
    for (Object x : $a) {
      tmp.add((StringTemplate)x);
    }
    
  } -> balise(name={"flows"}, content={ tmp })
  ;
  
  flowvar
  : a=IDENTIFIER  '\'' '=' e=realExpr {
      StringTemplate tmp = templateLib.getInstanceOf("balise");
      tmp.setAttribute("name", "name");
      tmp.setAttribute("content", $a.getText());
  
  
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp);
      tmplist.add($e.st);
  
  
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "flow");
      tmp2.setAttribute("content", tmplist);
      } -> delist(arg={tmp2})
      ;
   
TRUE: 'true' | 'TRUE' | 'True';
FALSE: 'false' | 'FALSE' | 'False';
POWER: 'power' | 'POWER';
MAX: 'max' | 'MAX';
MIN: 'min' | 'MIN';

fragment LETTER : 'a'..'z' | 'A'..'Z' | '_' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
