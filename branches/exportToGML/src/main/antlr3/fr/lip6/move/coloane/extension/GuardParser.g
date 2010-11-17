parser grammar GuardParser;

options {
  language = Java;
  tokenVocab = GuardLexer;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
  
  import java.util.HashMap;
}

@members {
  HashMap<String,String> symbols;
  
  private boolean is_class(String id) { return symbols.get(id) == "class"; }
  private boolean is_domain(String id) { return symbols.get(id) == "domain"; }
  private boolean is_variable(String id) { return symbols.get(id) == "variable"; }
}

transitionGuard[HashMap<String,String> s] returns [String value]
@init {
  symbols = s;
  $value = "<attribute name=\"guard\">\n";
}
@after {
  $value.concat("</attribute>\n");
} :
  TRUE { $value.concat("<attribute name=\"boolExpr\">\n<attribute name=\"boolValue\">true</attribute>\n</attribute>\n"); } |
  FALSE { $value.concat("<attribute name=\"boolExpr\">\n<attribute name=\"boolValue\">false</attribute>\n</attribute>\n"); } |
  LHOOK g=guard RHOOK { $value.concat($g.value); } ;

guard returns [String value]
@init { $value = "<attribute name=\"boolExpr\">\n"; }
@after { $value.concat("</attribute>\n"); } :
  NOT g=guard
{ $value.concat("<attribute name=\"not\">");
  $value.concat($g.value);
  $value.concat("</attribute>\n");
} |
  LPAREN h=guard RPAREN
{ $value.concat($h.value);
} |
  d=disjunctiveNormalForm {  } ;

disjunctiveNormalForm : orOperator (OR disjunctiveNormalForm)? ;
  
orOperator : atom (AND atom)* ;

atom returns [String value]
@init { $value = ""; } :
  TRUE { $value.concat("<attribute name=\"boolExpr\">\n<attribute name=\"boolValue\">true</attribute>\n</attribute>\n"); } |
  FALSE { $value.concat("<attribute name=\"boolExpr\">\n<attribute name=\"boolValue\">false</attribute>\n</attribute>\n"); } |
  guardOperator relOperator guardOperator |
  UNIQUE LPAREN guardOperator RPAREN |
  CARD LPAREN guardOperator RPAREN relOperator INTEGER ;

relOperator : EQ | NEQ | LEQ | GEQ | LT | GT ;

guardOperator : varClassElement | simpleBagOperator ;

varClassElement : IDENTIFIER |
  IDENTIFIER DOT IDENTIFIER |
  IDENTIFIER PLUSPLUS INTEGER |
  IDENTIFIER MINUSMINUS INTEGER |
  IDENTIFIER DOT ALL ;
  
simpleBagOperator : LBRACE IDENTIFIER RBRACE ;
  