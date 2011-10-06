parser grammar ValuationParserSNB;
//import ValuationParserSN;

options {
  language = Java;
  output = template;
  tokenVocab = ValuationLexerSNB;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
  import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
}

/* copy of ValuationParserSN.g, while the import-header bug is not fixed */
@members {
  Map<String,String> symbols;
  boolean nested = false;
  
  private boolean is_class(String id) { return "class".equals(symbols.get(id)); }
  private boolean is_domain(String id) { return "domain".equals(symbols.get(id)) || "domain_bag".equals(symbols.get(id)); }
  private boolean is_variable(String id) { return "variable".equals(symbols.get(id)) || "variable_bag".equals(symbols.get(id)); }
  private boolean is_variable_bag(String id) { return "variable_bag".equals(symbols.get(id)); }
  private boolean is_scs(String id) { return "scs".equals(symbols.get(id)); }
}

@rulecatch {
  catch (RecognitionException re) {
    throw re;
  }
}

arcLabel[Map<String,String> s]
@init {
  symbols = s;
  StringTemplate tmp = null;
}
  : l=listElementaryExpr -> balise(name={"valuation"}, content={$l.st})
  | i=positiveInteger
  {
    StringTemplate tmp0 = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "intValue").put("content", $i.st));
    tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "occurs").put("content", tmp0));
  }
  -> balise(name={"valuation"}, content={
        %balise(name={"token"}, content={ tmp })
  })
  ;

positiveInteger
  : i=INTEGER { Integer.parseInt($i.getText()) > 0 }? -> { %{$i.getText()} }
  ;

listElementaryExpr
@init {
  StringTemplate tmp0 = null;
}
  : e+=elementaryExpression[false] (PLUS e+=elementaryExpression[false])* -> delist(arg={$e})
  | id=IDENTIFIER { is_class($id.getText()) || is_scs($id.getText()) }? DOT ALL MINUS f=elementaryExpression[false]
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    StringTemplate tmpp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "type").put("content", $id.getText()));
    tmp.add( %balise(name={"all"}, content={ tmpp }) );
    tmp.add( $f.st );
    
    tmp0 = %balise(name={"setDiff"}, content={tmp});
  }
  -> balise(name={"token"}, content={
        %balise(name={"tokenProfile"}, content={ tmp0 })
  })
  ;

elementaryExpression[boolean n]
@init {
  nested = n;
  List<StringTemplate> tmp = new ArrayList<StringTemplate>();
}
@after {
  nested = false;
}
  : e=elementaryProduct
  {
    if (nested) {
      retval.st = $e.st;
    } else {
      List<StringTemplate> tmp0 = new ArrayList<StringTemplate>();
      tmp0.add( %balise(name={"occurs"}, content={ templateLib.getInstanceOf("balise", new STAttrMap().put("name", "intValue").put("content", "1")) }) );
      tmp0.add( %balise(name={"tokenProfile"}, content={$e.st}) );
      retval.st = %balise(name={"token"}, content={tmp0});
    }
  }
  | { !nested }?=> c=coefficient TIMES p=elementaryProduct // no coefficient allowed inside a cartesian product (it would be better to use a gated predicate here)
  {
    tmp.add( %balise(name={"occurs"}, content={$c.st}) );
    tmp.add( %balise(name={"tokenProfile"}, content={$p.st}) );
  }
  -> balise(name={"token"}, content={tmp})
  ;

coefficient
@init {
  List<StringTemplate> tmp = new ArrayList<StringTemplate>();
}
  : i=positiveInteger
  -> balise(name={"intValue"}, content={$i.st})
  | ORD LPAREN id=IDENTIFIER RPAREN { is_variable($id.getText()) }? // variableIdentifier
  -> balise(name={"ord"}, content={ %balise(name={"name"}, content={$id.getText()}) })
  | ORD LPAREN idc=IDENTIFIER DOT ide=INTEGER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
  {
    tmp.add( %balise(name={"type"}, content={$idc.getText()}) );
    tmp.add( %balise(name={"intValue"}, content={$ide.getText()}) );
  }
  -> balise(name={"ord"}, content={
        %balise(name={"intConst"}, content={ tmp })
  })
  | ORD LPAREN idc=IDENTIFIER DOT ide=IDENTIFIER RPAREN { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
  {
    tmp.add( %balise(name={"type"}, content={$idc.getText()}) );
    tmp.add( %balise(name={"enumValue"}, content={$ide.getText()}) );
  }
  -> balise(name={"ord"}, content={
        %balise(name={"enumConst"}, content={ tmp })
  })
  ;
  
elementaryProduct
  : LT l=listProdElement GT -> { $l.st }
  ;

listProdElement
  : e+=prodElement (COMA e+=prodElement)* -> delist(arg={$e})
  ;
  
varClassElement
@init {
  List<StringTemplate> tmpl = new ArrayList<StringTemplate>();
  StringTemplate tmp = null;
}
  : { is_variable(input.LT(1).getText()) }?=> id=IDENTIFIER // variableIdentifier
  -> balise(name={"name"}, content={$id.getText()})
  | id=IDENTIFIER { is_class(symbols.get($id.getText())) }? // elementIdentifier
  {
    tmpl.add( %balise(name={"type"}, content={symbols.get($id.getText())}) );
    tmpl.add( %balise(name={"enumValue"}, content={$id.getText()}) );
  }
  -> balise(name={"enumConst"}, content={tmpl})
  | id=IDENTIFIER DOT ALL { is_class($id.getText()) }? // classIdentifier DOT ALL
  {
    tmp = %balise(name={"type"}, content={$id.getText()});
  }
  -> balise(name={"function"}, content={
        %balise(name={"all"}, content={ tmp })
  })
  | idc=IDENTIFIER DOT i=INTEGER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
  {
    tmpl.add( %balise(name={"type"}, content={$idc.getText()}) );
    tmpl.add( %balise(name={"intValue"}, content={$i.getText()}) );
  }
  -> balise(name={"intConst"}, content={tmpl})
  | idc=IDENTIFIER DOT i=IDENTIFIER { is_class($idc.getText()) }? // classIdentifier DOT elementIdentifier
  {
    tmpl.add( %balise(name={"type"}, content={$idc.getText()}) );
    tmpl.add( %balise(name={"enumValue"}, content={$i.getText()}) );
  }
  -> balise(name={"enumConst"}, content={tmpl})
  | id=IDENTIFIER PLUSPLUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier ++ n
  { Integer.parseInt($n.getText()) > 0 }?
  {
    tmp = %balise(name={"name"}, content={$id.getText()});
    for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) {
      tmp = %balise(name={"++"}, content={tmp});
    }
  }
  -> balise(name={"function"}, content={tmp})
  | id=IDENTIFIER MINUSMINUS n=INTEGER { is_variable($id.getText()) }? // variableIdentifier -- n
  { Integer.parseInt($n.getText()) > 0 }?
  {
    tmp = %balise(name={"name"}, content={$id.getText()});
    for (int j=0 ; j<Integer.parseInt($n.getText()) ; ++j) {
      tmp = %balise(name={"--"}, content={tmp});
    }
  } -> balise(name={"function"}, content={tmp})
  ;
/* end of the copy */

/* Marking part */

initMarking[Map<String,String> s]
@init {
  symbols = s;
  StringTemplate tmp = null;
}
  : l=listMarking -> balise(name={"marking"}, content={$l.st})
  | i=INTEGER
  {
    tmp = %balise(name={"intValue"}, content={$i.getText()});
    tmp = %balise(name={"occurs"}, content={tmp});
  }
  -> balise(name={"marking"}, content={
        %balise(name={"token"}, content={ tmp })
  })
  | -> balise(name={"marking"}, content={""})
  ;
  
listMarking
  : m+=marking (PLUS m+=marking) -> delist(arg={$m})
  ;

marking
@init {
  List<StringTemplate> tmp = new ArrayList<StringTemplate>();
}
  : e=elementaryProduct
  {
    StringTemplate tmp0 = %balise(name={"intValue"}, content={"1"});
    tmp.add( %balise(name={"occurs"}, content={ tmp0 }) );
    tmp.add( %balise(name={"tokenProfile"}, content={$e.st}) );
  }
  -> balise(name={"token"}, content={ tmp })
  | i=positiveInteger TIMES e=elementaryProduct
  {
    StringTemplate tmp0 = %balise(name={"intValue"}, content={$i.st});
    tmp.add( %balise(name={"occurs"}, content={ tmp0 }) );
    tmp.add( %balise(name={"tokenProfile"}, content={$e.st}) );
  }
  -> balise(name={"token"}, content={tmp})
  ;

/* end of Marking part */

prodElement
  : (IDENTIFIER)=> v=varClassElement -> balise(name={"expr"}, content={$v.st})
  | e=elementaryExpression[true] -> balise(name={"expr"}, content={$e.st})
  | r=recursiveBagOperators -> balise(name={"expr"}, content={$r.st})
  ;
  
simpleBagOperators
  : LBRACE id=varClassElement RBRACE -> balise(name={"wrap"}, content={$id.st})
  ;

atomBag
  : v=varClassElement -> balise(name={"bagOperator"}, content={$v.st})
  | LPAREN r=recursiveBagOperators RPAREN -> { $r.st }
  | s=simpleBagOperators -> balise(name={"bagOperator"}, content={$s.st})
  | TILDE r=recursiveBagOperators
  -> balise(name={"bagOperator"}, content={
        %balise(name={"bagComplementary"}, content={$r.st})
  })
  ;

recursiveBagOperators
@init {
  List<StringTemplate> tmp = new ArrayList();
}
  : (atomBag)=> a=atomBag -> { $a.st }
  | a=atomBag op=bagOp b=recursiveBagOperators
  {
    tmp.add($a.st);
    tmp.add($b.st);
  }
  -> balise(name={"bagOperator"}, content={
        %balise(name={$op.st}, content={tmp})
  })
  ;

bagOp
  : INTER -> { %{"bagIntersection"} }
  | UNION -> { %{"bagUnion"} }
  | DIFF -> { %{"bagDifference"} }
  ;
