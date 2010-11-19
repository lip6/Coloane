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
  HashMap<String,String> symbols;
  
  private boolean is_class(String id) { return symbols.get(id) == "class"; }
  private boolean is_domain(String id) { return symbols.get(id) == "domain"; }
  private boolean is_variable(String id) { return symbols.get(id) == "variable"; }
}

arcLabel[HashMap<String,String> s] returns [String value] 
@init {
  symbols = s;
  $value = "<attribute name=\"valuation\">\n";
}
@after { $value = $value.concat("</attribute>\n"); } :
  l=listElementaryExpr { $value = $value.concat($l.value); } |
  i=positiveInteger
{ $value = $value.concat("<attribute name=\"token\">\n");
  $value = $value.concat("<attribute name=\"occurs\">");
  $value = $value.concat($i.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} ;

positiveInteger returns [String value] : i=INTEGER { Integer.parseInt($i.getText()) > 0 }? { $value=$i.getText(); } ;

listElementaryExpr returns [String value]
@init { $value = ""; } :
  e=elementaryExpression[false] { $value = $value.concat($e.value); } (PLUS l=listElementaryExpr { $value = $value.concat($l.value); } )? |
  id=IDENTIFIER { is_class($id.getText()) }? DOT ALL MINUS e=elementaryExpression[false]
{ $value = $value.concat("<attribute name=\"token\">\n<attribute name=\"tokenProfile\">\n<attribute name=\"setDiff\">");
  $value = $value.concat("<attribute name=\"all\">\n<attribute name=\"type\">" + $id.getText() + "</attribute>\n</attribute>");
  $value = $value.concat($e.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} ;

elementaryExpression[boolean nested] returns [String value]
@init { if (!nested) $value="<attribute name=\"token\">\n"; else $value=""; }
@after { if (!nested) $value = $value.concat("</attribute>\n"); } :
  e=elementaryProduct
{ if (!nested) {
    $value = $value.concat("<attribute name=\"occurs\">\n");
    $value = $value.concat("<attribute name=\"intValue\">1</attribute>\n");
    $value = $value.concat("</attribute>\n");
    $value = $value.concat("<attribute name=\"tokenProfile\">");
  }
  $value = $value.concat($e.value);
  if (!nested)
    $value = $value.concat("</attribute>\n");
} |
  { !(nested) }? c=coefficient TIMES p=elementaryProduct // no coefficient allowed inside a cartesian product (it would be better to use a gated predicate here)
{ $value = $value.concat("<attribute name=\"occurs\">\n");
  $value = $value.concat($c.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"tokenProfile\">");
  $value = $value.concat($p.value);
  $value = $value.concat("</attribute>\n");
} ;

coefficient returns [String value]
@init { $value=""; } :
  i=positiveInteger
{ $value = $value.concat("<attribute name=\"intValue\">");
  $value = $value.concat($i.value);
  $value = $value.concat("</attribute>");
} |
  ORD LPAREN id=IDENTIFIER RPAREN { is_variable($id.getText()) }? // variableIdentifier
{ $value = $value.concat("<attribute name=\"ord\">\n");
  $value = $value.concat("<attribute name=\"name\">");
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} |
  ORD LPAREN idc=IDENTIFIER DOT ide=INTEGER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value.concat("<attribute name=\"ord\">\n");
  $value = $value.concat("<attribute name=\"intConst\">");
  $value = $value.concat("<attribute name=\"\type\">");
  $value = $value.concat($idc.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"intValue\">\n");
  $value = $value.concat($ide.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} |
  ORD LPAREN idc=IDENTIFIER DOT ide=IDENTIFIER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value = $value.concat("<attribute name=\"ord\">\n");
  $value = $value.concat("<attribute name=\"enumConst\">");
  $value = $value.concat("<attribute name=\"\type\">");
  $value = $value.concat($idc.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"enumValue\">\n");
  $value = $value.concat($ide.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} ;
  
elementaryProduct returns [String value]
@init { $value=""; } :
  LT l=listProdElement GT { $value = $value.concat($l.value); } ;

listProdElement returns [String value]
@init { $value="<attribute name=\"expr\">\n"; } :
  e=prodElement { $value = $value.concat($e.value + "</attribute>\n"); } (COMA l=listProdElement { $value = $value.concat($l.value); })? ;
  
varClassElement returns [String value]
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
/* end of the copy */

/* Marking part */

initMarking[HashMap<String,String> s] returns [String value]
@init { symbols = s; $value="<attribute name=\"marking\">\n"; }
@after { $value = $value.concat("</attribute>\n"); } :
  l=listMarking { $value = $value.concat($l.value); } |
  i=positiveInteger
{ $value = $value.concat("<attribute name=\"token\">\n");
  $value = $value.concat("<attribute name=\"occurs\">");
  $value = $value.concat($i.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} ;
  
listMarking returns [String value]
@init { $value = ""; } :
  m=marking { $value = $value.concat($m.value); } (PLUS l=listMarking { $value = $value.concat($l.value); })? ;

marking returns [String value]
@init { $value="<attribute name=\"token\">\n"; }
@after { $value = $value.concat("</attribute>\n"); } :
  e=elementaryProduct
{ $value = $value.concat("<attribute name=\"occurs\">\n");
  $value = $value.concat("<attribute name=\"intValue\">1</attribute>\n");
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"tokenProfile\">");
  $value = $value.concat($e.value);
  $value = $value.concat("</attribute>\n");
} |
  i=positiveInteger TIMES e=elementaryProduct
{ $value = $value.concat("<attribute name=\"occurs\">\n");
  $value = $value.concat("<attribute name=\"intValue\">");
  $value = $value.concat($i.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"tokenProfile\">");
  $value = $value.concat($e.value);
  $value = $value.concat("</attribute>\n");
} ;

/* end of Marking part */

prodElement returns [String value]
@init { $value=""; } :
  e=elementaryExpression[true] { $value = $value.concat($e.value); } |
  (IDENTIFIER)=> v=varClassElement
{ $value = $value.concat("<attribute name=\"expr\">\n");
  $value = $value.concat($v.value);
  $value = $value.concat("</attribute>\n");
} |
  recursiveBagOperators ;
  
simpleBagOperators : LBRACE IDENTIFIER RBRACE ;

recursiveBagOperators : interTerm (INTER interTerm)* ;

interTerm : LPAREN interTerm2 RPAREN | interTerm2 ;

interTerm2 : atomBag (UNION atomBag)* ;

atomBag : simpleBagOperators |
  id=IDENTIFIER | // variable-bag identifier
  TILDE atomBag ;
