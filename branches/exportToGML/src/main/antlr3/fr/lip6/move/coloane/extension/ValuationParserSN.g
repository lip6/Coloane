parser grammar ValuationParserSN;

options {
  language = Java;
  tokenVocab = ValuationLexerSNB;
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

arcLabel[HashMap<String,String> s] returns [String value] 
@init {
  symbols = s;
  $value = "<attribute name=\"valuation\">\n";
}
@after { $value.concat("</attribute>\n"); } :
  l=listElementaryExpr { $value.concat($l.value); } |
  i=positiveInteger
{ $value.concat("<attribute name=\"token\">\n");
  $value.concat("<attribute name=\"occurs\">");
  $value.concat($i.value);
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} ;

positiveInteger returns [String value] : i=INTEGER { Integer.parseInt($i.getText()) > 0 }? { $value=$i.getText(); } ;

listElementaryExpr returns [String value]
@init { $value = ""; } :
  e=elementaryExpression[false] { $value.concat($e.value); } (PLUS l=listElementaryExpr { $value.concat($l.value); } )? |
  id=IDENTIFIER { is_class($id.getText()) }? DOT ALL MINUS e=elementaryExpression[false]
{ $value.concat("<attribute name=\"token\">\n<attribute name=\"tokenProfile\">\n<attribute name=\"setDiff\">");
  $value.concat("<attribute name=\"all\">\n<attribute name=\"type\">" + $id.getText() + "</attribute>\n</attribute>");
  $value.concat($e.value);
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} ;

elementaryExpression[boolean nested] returns [String value]
@init { if (!nested) $value="<attribute name=\"token\">\n"; else $value=""; }
@after { if (!nested) $value.concat("</attribute>\n"); } :
  e=elementaryProduct
{ if (!nested) {
    $value.concat("<attribute name=\"occurs\">\n");
    $value.concat("<attribute name=\"intValue\">1</attribute>\n");
    $value.concat("</attribute>\n");
    $value.concat("<attribute name=\"tokenProfile\">");
  }
  $value.concat($e.value);
  if (!nested)
    $value.concat("</attribute>\n");
} |
  { !(nested) }? c=coefficient TIMES p=elementaryProduct // on n'autorise pas de coefficients au sein d'un produit cartésien (on aurait préféré un gated predicate ici)
{ $value.concat("<attribute name=\"occurs\">\n");
  $value.concat($c.value);
  $value.concat("</attribute>\n");
  $value.concat("<attribute name=\"tokenProfile\">");
  $value.concat($p.value);
  $value.concat("</attribute>\n");
} ;

coefficient returns [String value]
@init { $value=""; } :
  i=positiveInteger
{ $value.concat("<attribute name=\"intValue\">");
  $value.concat($i.value);
  $value.concat("</attribute>");
} |
  ORD LPAREN id=IDENTIFIER RPAREN { is_variable($id.getText()) }? // variableIdentifier
{ $value.concat("<attribute name=\"ord\">\n");
  $value.concat("<attribute name=\"name\">");
  $value.concat($id.getText());
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} |
  ORD LPAREN idc=IDENTIFIER DOT ide=INTEGER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value.concat("<attribute name=\"ord\">\n");
  $value.concat("<attribute name=\"intConst\">");
  $value.concat("<attribute name=\"\type\">");
  $value.concat($idc.getText());
  $value.concat("</attribute>\n");
  $value.concat("<attribute name=\"intValue\">\n");
  $value.concat($ide.getText());
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} |
  ORD LPAREN idc=IDENTIFIER DOT ide=IDENTIFIER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value.concat("<attribute name=\"ord\">\n");
  $value.concat("<attribute name=\"enumConst\">");
  $value.concat("<attribute name=\"\type\">");
  $value.concat($idc.getText());
  $value.concat("</attribute>\n");
  $value.concat("<attribute name=\"enumValue\">\n");
  $value.concat($ide.getText());
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} ;
  
elementaryProduct returns [String value]
@init { $value=""; } :
  LT l=listProdElement GT { $value.concat($l.value); } ;

listProdElement returns [String value]
@init { $value="<attribute name=\"expr\">\n"; } :
  e=prodElement { $value.concat($e.value + "</attribute>\n"); } (COMA l=listProdElement { $value.concat($l.value); })? ;

prodElement returns [String value]
@init { $value=""; } :
  e=elementaryExpression[true] { $value.concat($e.value); } |
  v=varClassElement
{ $value.concat("<attribute name=\"expr\">\n");
  $value.concat($v.value);
  $value.concat("</attribute>\n");
} ;
  
varClassElement returns [String value]
@init { $value=""; } :
  id=IDENTIFIER { is_variable($id.getText()) }? { $value.concat("<attribute name=\"name\">" + $id.getText() + "</attribute>\n"); } | // variableIdentifier
  id=IDENTIFIER DOT ALL { is_class($id.getText()) }? // classIdentifier DOT ALL
{ $value.concat("<attribute name=\"function\">\n");
  $value.concat("<attribute name=\"all\">\n");
  $value.concat("<attribute name=\"type\">");
  $value.concat($id.getText());
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} |
  idc=IDENTIFIER DOT i=INTEGER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value.concat("<attribute name=\"intConst\">\n");
  $value.concat("<attribute name=\"type\">" + $id.getText() + "</attribute>\n");
  $value.concat("<attribute name=\"intValue\">" + $i.getText() + "</attribute>\n");
  $value.concat("</attribute>\n");
} |
  idc=IDENTIFIER DOT i=IDENTIFIER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
{ $value.concat("<attribute name=\"enumConst\">\n");
  $value.concat("<attribute name=\"type\">" + $id.getText() + "</attribute>\n");
  $value.concat("<attribute name=\"enumValue\">" + $i.getText() + "</attribute>\n");
  $value.concat("</attribute>\n");
} |
  id=IDENTIFIER PLUSPLUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier ++ n
  { Integer.parseInt($n.getText()) > 0 }?
{ $value.concat("<attribute name=\"function\">\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value.concat("<attribute name=\"++\">\n");
  }
  $value.concat("<attribute name=\"name\">" + $id.getText() + "</attribute>\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value.concat("</attribute>\n");
  }
  $value.concat("</attribute>\n");
} |
  id=IDENTIFIER MINUSMINUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier -- n
  { Integer.parseInt($n.getText()) > 0 }?
{ $value.concat("<attribute name=\"function\">\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value.concat("<attribute name=\"--\">\n");
  }
  $value.concat("<attribute name=\"name\">" + $id.getText() + "</attribute>\n");
  for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) { 
    $value.concat("</attribute>\n");
  }
  $value.concat("</attribute>\n");
} ;
