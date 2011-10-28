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
  
  import java.util.Map;
  import java.util.HashMap;
}

@members {
  Map<String,String> symbols;
    private boolean is_constante(String id) { return ("intconst".equals(symbols.get(id)) | "realconst".equals(symbols.get(id))); } 
    private boolean is_int_constante(String id) { return "intconst".equals(symbols.get(id)); } 
    
  }

intExprW[Map<String,String> s]
@init {
  symbols = s;
} :
  e=intExpr EOF -> balise(name={"intFormula"}, content={ $e.st });

intExpr :
   e=multIntExpr 
   (                 -> {$e.st}
   | ('+' e2=multIntExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"iplus"}, content={ tmplist })
   | '-' e2=multIntExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"iminus"}, content={ tmplist })
   )+) 
   ;

multIntExpr
  : e=intAtom 
  (
                  -> {$e.st}
  | ('*' e2=intAtom {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"imult"}, content={ tmplist })
  )+)
  ;

intAtom: 
   i=IDENTIFIER {
    StringTemplate tmp = templateLib.getInstanceOf("balise");
      if(is_constante($i.getText())){
        tmp.setAttribute("name", "intConst");
      }else{
          tmp.setAttribute("name", "placeName");
      }  
      tmp.setAttribute("content", $i.getText());
   } -> delist(arg={tmp})
  | i=INTEGER -> balise(name={"value"}, content={ $i.getText() })
  | '(' e=intExpr ')' -> {$e.st}
  | MAX '(' a=intExpr ',' b=intExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> balise(name={"imax"}, content={ tmplist })
  | MIN '(' a=intExpr ',' b=intExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> balise(name={"imin"}, content={ tmplist })
  | POWER '(' a=intExpr ',' b=intExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> balise(name={"ipower"}, content={ tmplist })
  ;
  
  
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
      StringTemplate tmp1 = templateLib.getInstanceOf("balise");
      tmp1.setAttribute("name", "number");
      tmp1.setAttribute("content", compteur);
      StringTemplate tmp2 = templateLib.getInstanceOf("balise");
      tmp2.setAttribute("name", "realFormula");
      tmp2.setAttribute("content", x);
      
      List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
      tmplist.add(tmp1);
      tmplist.add(tmp2);
      
      StringTemplate tmp0 = templateLib.getInstanceOf("balise");
      tmp0.setAttribute("name", "param");
      tmp0.setAttribute("content", tmplist);
      tmp.add(tmp0);
      ++compteur;
    }
  } -> delist(arg={tmp})
  ;
  
  
realExprW[Map<String,String> s]
@init {
  symbols = s;
} :
  e=realExpr EOF -> balise(name={"realFormula"}, content={ $e.st });

realExpr :
   e=multRealExpr 
   (                 -> {$e.st}
   | ('+' e2=multRealExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"plus"}, content={ tmplist })
   | '-' e2=multRealExpr {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"minus"}, content={ tmplist })
   )+) 
   ;

multRealExpr
  : e=realAtom 
  (
                  -> {$e.st}
  | ('*' e2=realAtom {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"mult"}, content={ tmplist })
  | '/' e2=realAtom {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($e.st);
    tmplist.add($e2.st);   
   } -> balise(name={"div"}, content={ tmplist })
  )+)
  ;

realAtom: 
   i=IDENTIFIER {
    StringTemplate tmp = templateLib.getInstanceOf("balise");
      if(is_int_constante($i.getText())){
        tmp.setAttribute("name", "intConst");
      }else if(is_constante($i.getText())){
        tmp.setAttribute("name", "realConst");
      }else{
        tmp.setAttribute("name", "marking");
      }  
      tmp.setAttribute("content", $i.getText());
   } -> delist(arg={tmp})
  | i=INTEGER -> balise(name={"value"}, content={ $i.getText() })
  | '(' e=realExpr ')' -> {$e.st}
  | MAX '(' a=realExpr ',' b=realExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> balise(name={"max"}, content={ tmplist })
  | MIN '(' a=realExpr ',' b=realExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> balise(name={"min"}, content={ tmplist })
  | POWER '(' a=realExpr ',' b=realExpr ')' {
    List<StringTemplate> tmplist = new ArrayList<StringTemplate>();
    tmplist.add($a.st);
    tmplist.add($b.st);   
  } -> balise(name={"power"}, content={ tmplist })
  ;

POWER: 'power' | 'POWER';
MAX: 'max' | 'MAX';
MIN: 'min' | 'MIN';

fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : DIGIT+ ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
