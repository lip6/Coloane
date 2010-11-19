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

transitionGuard[HashMap<String,String> s,String gap] returns [String value]
@init {
  symbols = s;
  $value = gap + "<attribute name=\"guard\">\n";
}
@after {
  $value = $value + gap + "</attribute>\n";
} :
  TRUE
{ $value = $value + gap + "<attribute name=\"boolExpr\">\n";
  $value = $value + gap + "\t<attribute name=\"boolValue\">true</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  FALSE
{ $value = $value + gap + "<attribute name=\"boolExpr\">\n";
  $value = $value + gap + "\t<attribute name=\"boolValue\">false</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  LHOOK g=guard[$gap] RHOOK { $value = $value.concat($g.value); } ;

guard[String gap] returns [String value]
@init { $value = gap + "<attribute name=\"boolExpr\">\n"; }
@after { $value = $value + gap + "</attribute>\n"; } :
  NOT g=guard[gap+"\t"]
{ $value = $value + gap + "<attribute name=\"not\">\n";
  $value = $value.concat($g.value);
  $value = $value + gap + "</attribute>\n";
} |
  LPAREN h=guard[gap] RPAREN
{ $value = $value.concat($h.value);
} |
  d=disjunctiveNormalForm[$gap+"\t"]
{ $value = $value + gap + "<attribute name=\"or\">\n";
  $value = $value + $d.value;
  $value = $value + gap + "</attribute>\n";
} ;

disjunctiveNormalForm[String gap] returns [String value]
@init { $value = ""; } :
  o=orOperator[$gap+"\t"]
{ $value = $value + gap + "<attribute name=\"or\">\n";
  $value = $value + $o.value;
  $value = $value + gap + "</attribute>\n";
}
  (OR d=disjunctiveNormalForm[$gap] { $value = $value + $d.value; })? ;
  
orOperator[String gap] returns [String value]
@init { $value = ""; } :
  a=atom[$gap+"\t"]
{ $value = $value + gap + "<attribute name=\"and\">\n";
  $value = $value + $a.value;
  $value = $value + gap + "</attribute>\n";
}
  (AND o=orOperator[$gap] { $value = $value + $o.value; })? ;

atom[String gap] returns [String value]
@init { $value = ""; } :
  TRUE
{ $value = $value + gap + "<attribute name=\"boolExpr\">\n";
  $value = $value + gap + "\t<attribute name=\"boolValue\">true</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  FALSE
{ $value = $value + gap + "<attribute name=\"boolExpr\">\n";
  $value = $value + gap + "\t<attribute name=\"boolValue\">false</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  g1=guardOperator[$gap+"\t\t"] op=relOperator[false] g2=guardOperator[$gap+"\t\t"]
{ $value = $value + gap + "<attribute name\"" + $op.value + "\">\n";
  $value = $value + gap + "\t<attribute name=\"boolExpr\">\n";
  $value = $value + $g1.value;
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"boolExpr\">\n";
  $value = $value + gap + $g2.value;
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  UNIQUE LPAREN id=IDENTIFIER RPAREN { is_variable($id.getText()) }?
{ $value = $value + gap + "<attribute name=\"unique\">\n";
  $value = $value + gap + "\t<attribute name=\"name\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  CARD LPAREN g=IDENTIFIER RPAREN op=relOperator[true] i=INTEGER { is_variable($g.getText()) }?
{ $value = $value + gap + "<attribute name=\"cardinalExpression\">\n";
  $value = $value + gap + "\t<attribute name=\"cardinal" + $op.value + "\">\n";
  $value = $value + gap + "\t<attribute name=\"name\">" + $g.getText() + "</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"intValue\">" + $i.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} ;

relOperator[boolean incard] returns [String value] :
  EQ { if ($incard) $value="Equal"; else $value="equal"; } |
  NEQ { if ($incard) $value="NotEqual"; else $value="notEqual"; } |
  LEQ { if ($incard) $value="LessEqual"; else $value="lessEqual"; } |
  GEQ { if ($incard) $value="GreaterEqual"; else $value="greaterEqual"; } |
  LT { if ($incard) $value="Less"; else $value="less"; } |
  GT { if ($incard) $value="Greater"; else $value="greater"; } ;

guardOperator[String gap] returns [String value] : v=varClassElement[$gap] { $value = $v.value; } | s=simpleBagOperator[$gap] { $value=$s.value; } ;

varClassElement[String gap] returns [String value]
@init { $value=""; } :
  id=IDENTIFIER { is_variable($id.getText()) }? { $value = $value.concat("<attribute name=\"name\">" + $id.getText() + "</attribute>\n"); } | // variableIdentifier
  id=IDENTIFIER DOT ALL { is_class($id.getText()) }? // classIdentifier DOT ALL
{ $value = $value.concat("<attribute name=\"function\">\n");
  $value = $value.concat("<attribute name=\"all\">\n");
  $value = $value.concat("<attribute name=\"type\">");
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} |
  idc=IDENTIFIER DOT i=INTEGER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value.concat("<attribute name=\"intConst\">\n");
  $value = $value.concat("<attribute name=\"type\">" + $id.getText() + "</attribute>\n");
  $value = $value.concat("<attribute name=\"intValue\">" + $i.getText() + "</attribute>\n");
  $value = $value.concat("</attribute>\n");
} |
  idc=IDENTIFIER DOT i=IDENTIFIER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value.concat("<attribute name=\"enumConst\">\n");
  $value = $value.concat("<attribute name=\"type\">" + $id.getText() + "</attribute>\n");
  $value = $value.concat("<attribute name=\"enumValue\">" + $i.getText() + "</attribute>\n");
  $value = $value.concat("</attribute>\n");
} |
  id=IDENTIFIER PLUSPLUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier ++ n
  { Integer.parseInt($n.getText()) > 0 }?
{ $value = $value.concat("<attribute name=\"function\">\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value.concat("<attribute name=\"++\">\n");
  }
  $value = $value.concat("<attribute name=\"name\">" + $id.getText() + "</attribute>\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value.concat("</attribute>\n");
  }
  $value = $value.concat("</attribute>\n");
} |
  id=IDENTIFIER MINUSMINUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier -- n
  { Integer.parseInt($n.getText()) > 0 }?
{ $value = $value.concat("<attribute name=\"function\">\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value.concat("<attribute name=\"--\">\n");
  }
  $value = $value.concat("<attribute name=\"name\">" + $id.getText() + "</attribute>\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value.concat("</attribute>\n");
  }
  $value = $value.concat("</attribute>\n");
} ;
  
simpleBagOperator[String gap] returns [String value] : LBRACE id=IDENTIFIER RBRACE { is_variable($id.getText()) }? ;
  