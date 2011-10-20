grammar DistributionParser;

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

distribution
  : s=STRING LPAR a=arg_list RPAR
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
  | a+=arg (COMMA a+=arg)*
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
  
arg
  : s=STRING -> {%{$s.getText()}}
  ;
  
LPAR : '(' ;
RPAR : ')' ;
COMMA : ',' ;

STRING : (LETTER | DIGIT) (LETTER | DIGIT | '.' | '_')* ;
fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;