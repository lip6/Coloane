parser grammar ValuationParserSNB;
//import ValuationParserSN;

options {
  language = Java;
  tokenVocab = ValuationLexerSNB;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
}

/* copy of ValuationParserSN.g, while the import-header bug is not fixed */
@members {
  Map<String,String> symbols;
  
  private boolean is_class(String id) { return "class".equals(symbols.get(id)); }
  private boolean is_domain(String id) { return "domain".equals(symbols.get(id)); }
  private boolean is_variable(String id) { return "variable".equals(symbols.get(id)); }
}

arcLabel[Map<String,String> s,String gap] returns [String value] 
@init {
  symbols = s;
  $value = gap + "<attribute name=\"valuation\">\n";
}
@after { $value = $value + gap + "</attribute>\n"; } :
  l=listElementaryExpr[$gap+"\t"] { $value = $value.concat($l.value); } |
  i=positiveInteger
{ $value = $value + gap + "\t<attribute name=\"token\">\n";
  $value = $value + gap + "\t\t<attribute name=\"occurs\">" + $i.value + "</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
} ;

positiveInteger returns [String value] : i=INTEGER { Integer.parseInt($i.getText()) > 0 }? { $value=$i.getText(); } ;

listElementaryExpr[String gap] returns [String value]
@init { $value = ""; } :
  e=elementaryExpression[false,gap] { $value = $value.concat($e.value); } (PLUS l=listElementaryExpr[$gap] { $value = $value.concat($l.value); } )? |
  id=IDENTIFIER { is_class($id.getText()) }? DOT ALL MINUS e=elementaryExpression[false,gap+"\t\t\t"]
{ $value = $value + gap + "<attribute name=\"token\">\n";
  $value = $value + gap + "\t<attribute name=\"tokenProfile\">\n";
  $value = $value + gap + "\t\t<attribute name=\"setDiff\">";
  $value = $value + gap + "\t\t\t<attribute name=\"all\">";
  $value = $value + gap + "\t\t\t\t<attribute name=\"type\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "\t\t\t</attribute>\n";
  $value = $value.concat($e.value);
  $value = $value + gap + "\t\t</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} ;

elementaryExpression[boolean nested,String gap] returns [String value]
@init { if (!nested) $value= gap + "<attribute name=\"token\">\n"; else $value="";
  String gap2;
  if (!nested) gap2 = gap + "\t\t"; else gap2 = gap;
}
@after { if (!nested) $value = $value + gap + "</attribute>\n"; } :
  e=elementaryProduct[gap2]
{ if (nested) { 
    $value = $value + $e.value;
  }
  else {
    $value = $value + gap + "\t<attribute name=\"occurs\">\n";
    $value = $value + gap + "\t\t<attribute name=\"intValue\">1</attribute>\n";
    $value = $value + gap + "\t</attribute>\n";
    $value = $value + gap + "\t<attribute name=\"tokenProfile\">\n";
    $value = $value + $e.value;
    $value = $value + gap + "\t</attribute>\n";
  }
} |
  { !(nested) }? c=coefficient[$gap + "\t\t"] TIMES p=elementaryProduct[$gap + "\t\t"] // no coefficient allowed inside a cartesian product (it would be better to use a gated predicate here)
{ $value = $value + gap + "\t<attribute name=\"occurs\">\n";
  $value = $value + $c.value;
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"tokenProfile\">";
  $value = $value + $p.value;
  $value = $value + "\t</attribute>\n";
} ;

coefficient[String gap] returns [String value]
@init { $value=""; } :
  i=positiveInteger
{ $value = $value + gap + "<attribute name=\"intValue\">" + $i.value + "</attribute>\n";
} |
  ORD LPAREN id=IDENTIFIER RPAREN { is_variable($id.getText()) }? // variableIdentifier
{ $value = $value + gap + "<attribute name=\"ord\">\n";
  $value = $value + gap + "\t<attribute name=\"name\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  ORD LPAREN idc=IDENTIFIER DOT ide=INTEGER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value + gap + "<attribute name=\"ord\">\n";
  $value = $value + gap + "\t<attribute name=\"intConst\">\n";
  $value = $value + gap + "\t\t<attribute name=\"\type\">" + $idc.getText() + "</attribute>\n";
  $value = $value + gap + "\t\t<attribute name=\"intValue\">" + $ide.getText() + "</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  ORD LPAREN idc=IDENTIFIER DOT ide=IDENTIFIER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value + gap + "<attribute name=\"ord\">\n";
  $value = $value + gap + "\t<attribute name=\"enumConst\">\n";
  $value = $value + gap + "\t\t<attribute name=\"\type\">" + $idc.getText() + "</attribute>\n";
  $value = $value + gap + "\t\t<attribute name=\"enumValue\">\n" + $ide.getText() + "</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} ;
  
elementaryProduct[String gap] returns [String value]
@init { $value=""; } :
  LT l=listProdElement[$gap] GT { $value = $value.concat($l.value); } ;

listProdElement[String gap] returns [String value]
@init { $value = ""; } :
  e=prodElement[$gap+""]
{ $value = $value + $e.value;
}
  (COMA l=listProdElement[$gap+"\t"] { $value = $value.concat($l.value); })? ;
  
varClassElement[String gap] returns [String value]
@init { $value=""; } :
  id=IDENTIFIER { is_variable($id.getText()) }? // variableIdentifier
{ $value = $value + gap + "<attribute name=\"name\">" + $id.getText() + "</attribute>\n";
} |
  id=IDENTIFIER DOT ALL { is_class($id.getText()) }? // classIdentifier DOT ALL
{ $value = $value + gap + "<attribute name=\"function\">\n";
  $value = $value + gap + "\t<attribute name=\"all\">\n";
  $value = $value + gap + "\t\t<attribute name=\"type\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  idc=IDENTIFIER DOT i=INTEGER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value + gap + "<attribute name=\"intConst\">\n";
  $value = $value + gap + "\t<attribute name=\"type\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"intValue\">" + $i.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  idc=IDENTIFIER DOT i=IDENTIFIER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value + gap + "<attribute name=\"enumConst\">\n";
  $value = $value + gap + "\t<attribute name=\"type\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"enumValue\">" + $i.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  id=IDENTIFIER PLUSPLUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier ++ n
  { Integer.parseInt($n.getText()) > 0 }?
{ $value = $value + gap + "<attribute name=\"function\">\n";
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value + gap + "\t<attribute name=\"++\">\n";
  }
  $value = $value + gap + "\t\t<attribute name=\"name\">" + $id.getText() + "</attribute>\n";
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value + gap + "\t</attribute>\n";
  }
  $value = $value + gap + "</attribute>\n";
} |
  id=IDENTIFIER MINUSMINUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier -- n
  { Integer.parseInt($n.getText()) > 0 }?
{ $value = $value + gap + "<attribute name=\"function\">\n";
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value + gap + "\t<attribute name=\"--\">\n";
  }
  $value = $value + gap + "\t\t<attribute name=\"name\">" + $id.getText() + "</attribute>\n";
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value = $value + gap + "\t</attribute>\n";
  }
  $value = $value + gap + "</attribute>\n";
} ;
/* end of the copy */

/* Marking part */

initMarking[Map<String,String> s,String gap] returns [String value]
@init { symbols = s; $value = gap+"<attribute name=\"marking\">\n"; }
@after { $value = $value + gap + "</attribute>\n"; } :
  l=listMarking[$gap+"\t"] { $value = $value + $l.value; } |
  i=positiveInteger
{ $value = $value + gap + "\t<attribute name=\"token\">\n";
  $value = $value + gap + "\t\t<attribute name=\"occurs\">" + $i.value + "</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
} | ;
  
listMarking[String gap] returns [String value]
@init { $value = ""; } :
  m=marking[$gap] { $value = $value.concat($m.value); } (PLUS l=listMarking[$gap] { $value = $value.concat($l.value); })? ;

marking[String gap] returns [String value]
@init { $value = gap + "<attribute name=\"token\">\n"; }
@after { $value = $value + gap + "</attribute>\n"; } :
  e=elementaryProduct[$gap+"\t\t"]
{ $value = $value + gap + "\t<attribute name=\"occurs\">\n";
  $value = $value + gap + "\t\t<attribute name=\"intValue\">1</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"tokenProfile\">\n";
  $value = $value + $e.value;
  $value = $value + gap + "\t</attribute>\n";
} |
  i=positiveInteger TIMES e=elementaryProduct[gap+"\t\t"]
{ $value = $value + gap + "\t<attribute name=\"occurs\">\n";
  $value = $value + gap + "\t\t<attribute name=\"intValue\">" + $i.value + "</attribute>\n";
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"tokenProfile\">";
  $value = $value + $e.value;
  $value = $value + gap + "\t</attribute>\n";
} ;

/* end of Marking part */

prodElement[String gap] returns [String value]
@init { $value = ""; } :
  e=elementaryExpression[true,$gap] { $value = $value + $e.value; } |
  (IDENTIFIER)=> v=varClassElement[$gap+"\t"]
{ $value = $value + gap + "<attribute name=\"expr\">\n";
  $value = $value + $v.value;
  $value = $value + gap + "</attribute>\n";
} |
  r=recursiveBagOperators[$gap+"\t\t"]
{ $value = $value + gap + "<attribute name=\"bagOperator\">\n";
  $value = $value + gap + "\t<attribute name=\"bagIntersection\">\n";
  $value = $value + $r.value;
  $value = $value + gap + "\t</attribute>\n"; 
  $value = $value + gap + "</attribute>\n";
} ;
  
simpleBagOperators[String gap] returns [String value] : LBRACE id=varClassElement[$gap+"\t"] RBRACE
{ $value = $value + gap + "<attribute name=\"wrap\">\n";
  $value = $value + $id.value;
  $value = $value + gap + "</attribute>\n";
} ;

recursiveBagOperators[String gap] returns [String value]
@init { $value = gap + "<attribute name=\"bagOperator\">\n";
   }
@after { $value = $value + gap + "</attribute>\n"; } :
  i=interTerm[$gap+"\t"]
{ $value = $value + $i.value; 
}
  (INTER r=recursiveBagOperators[$gap] { $value = $value + $r.value; })? ;

interTerm[String gap] returns [String value]
@init { $value = gap + "<attribute name=\"bagUnion\">\n"; }
@after { $value = $value + gap + "</attribute>\n"; } :
  LPAREN i=interTerm2[$gap+"\t"] { $value = $value + $i.value; } RPAREN |
  i=interTerm2[$gap+"\t"] { $value = $value + $i.value; } ;

interTerm2[String gap] returns [String value]
@init { $value = ""; } :
  a=atomBag[$gap] { $value = $value + $a.value; }
  (UNION i=interTerm2[$gap] { $value = $value + $i.value; })? ;

atomBag[String gap] returns [String value]
@init { $value = gap + "<attribute name=\"bagOperator\">\n"; }
@after { $value = $value + gap + "</attribute>\n"; } :
  s=simpleBagOperators[$gap+"\t"] { $value = $value + $s.value; } |
  id=IDENTIFIER // variable-bag identifier
{ $value = $value + gap + "\t<attribute name=\"name\">" + $id.getText() + "</attribute>\n";
} |
  TILDE a=atomBag[$gap+"\t\t"]
{ $value = $value + gap + "\t<attribute name=\"bagComplementary\">\n";
  $value = $value + $a.value;
  $value = $value + gap + "\t</attribute>\n";  
} ;
