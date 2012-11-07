parser grammar GuardParser;

options {
  language = Java;
  output = template;
  tokenVocab = GuardLexer;
}

@header {
  package fr.lip6.move.coloane.extensions.exporttogrml.antlr;
  
  import java.util.Map;
}

@members {
  Map<String,String> symbols;
  
  private boolean is_class(String id) { return "class".equals(symbols.get(id)); }
  private boolean is_domain(String id) { return "domain".equals(symbols.get(id)) || "domain_bag".equals(symbols.get(id)); }
  private boolean is_variable(String id) { return "variable".equals(symbols.get(id)) || "variable_bag".equals(symbols.get(id)); }
  private boolean is_simple_variable(String id) { return "variable".equals(symbols.get(id)); }
  private boolean is_scs(String id) { return "scs".equals(symbols.get(id)); }
}

@rulecatch {
  catch (RecognitionException re) {
    throw re;
  }
}

transitionGuard[Map<String,String> s]
@init {
  symbols = $s;
  StringTemplate tmp = null;
}
  : (TRUE)?
  {
    StringTemplate tmp0 = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolValue").put("content", "true"));
    tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolExpr").put("content", tmp0));
  }
  -> balise(name={"guard"}, content={ tmp })
  | FALSE {
    StringTemplate tmp0 = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolValue").put("content", "false"));
    tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolExpr").put("content", tmp0));
  }
  -> balise(name={"guard"}, content={ tmp })
  | LHOOK g=guard RHOOK -> balise(name={"guard"}, content={$g.st})
  ;

guard
  : c+=clause (AND c+=clause)*
  {
    if ($c.size() == 1) {
      StringTemplate tmp = $c.get(0);
      if (tmp.getAttributes().get("name").equals("boolExpr")) {
        retval.st = tmp;
      } else {
        retval.st = %balise( name={"boolExpr"}, content={$c} );
      }
    } else {
      StringTemplate tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "and").put("content", $c));
      retval.st = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolExpr").put("content", tmp));
    }
  }
  ;
  
clause
  : t+=term (OR t+=term)*
  {
    if ($t.size() == 1) {
      retval.st = %balise( name={"boolExpr"}, content={$t} );
    } else {
      StringTemplate tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "or").put("content", $t));
      retval.st = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolExpr").put("content", tmp));
    }
  }
  ;
 
 term
  : a=atom -> { $a.st }
  | NOT LPAREN g=guard RPAREN
  -> balise(name={"boolExpr"}, content={
        %balise(name={"not"}, content={$g.st})
     } )
  | LPAREN g=guard RPAREN -> { $g.st }
  ;

gOp
  : r=relOperator[false] -> { $r.st }
  | i=inclusion -> { $i.st }
  ;

atom
@init {
  StringTemplate tmp = null;
  List<StringTemplate> tmpl = new ArrayList<StringTemplate>();
}
  : TRUE
  {
    StringTemplate tmp0 = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolValue").put("content", "true"));
    tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolExpr").put("content", tmp0));
  }
  -> balise(name={"guard"}, content={ tmp })
  | FALSE
  {
    StringTemplate tmp0 = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolValue").put("content", "false"));
    tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolExpr").put("content", tmp0));
  }
  -> balise(name={"guard"}, content={ tmp })
  | g1=guardOperator oper=gOp g2=guardOperator
  {
    tmpl.add( %balise(name={"expr"}, content={$g1.st}) );
    tmpl.add( %balise(name={"expr"}, content={$g2.st}) );
  }
  -> balise(name={"boolExpr"}, content={ %balise(name={$oper.st}, content={tmpl}) })
  | (IDENTIFIER IN IDENTIFIER)=> id=IDENTIFIER IN id_c=IDENTIFIER { is_simple_variable($id.getText()) }? { is_scs($id_c.getText()) }?
  {
    //List<StringTemplate> tmpl = new ArrayList<StringTemplate>();
    tmpl.add( %balise(name={"name"}, content={$id.getText()}) );
    tmpl.add( %balise(name={"type"}, content={$id_c.getText()}) );
  }
  -> balise(name={"boolExpr"}, content={tmpl})
  | UNIQUE LPAREN id=IDENTIFIER RPAREN { is_variable($id.getText()) }?
  {
    StringTemplate tmp0 = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "name").put("content", $id.getText()));
    StringTemplate tmp1 = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "uniqueGuard").put("content", tmp0));
    retval.st = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "boolExpr").put("content", tmp1));
  }
  | CARD LPAREN g=IDENTIFIER RPAREN op=relOperator[true] i=INTEGER { is_variable($g.getText()) }?
  {
    //List<StringTemplate> tmpl = new ArrayList<StringTemplate>();
    tmpl.add( %balise(name={"name"}, content={$g.getText()}) );
    tmpl.add( %balise(name={"intValue"}, content={$i.getText()}) );
    
    tmp = templateLib.getInstanceOf("balise", new STAttrMap().put("name", "cardinal" + $op.st.toString()).put("content", tmpl));
  }
  -> balise(name={"boolExpr"}, content={
        %balise(name={"cardinalExpression"}, content={ tmp })
  })
  ;

inclusion
  : INCLUDED -> { %{"included"} }
  | STRICTINCLUDED -> { %{"strictlyIncluded"} }
  ;

relOperator[boolean incard]
@init {
  String val = "";
}
  : ( EQ { if ($incard) val="Equal"; else val="equal"; }
  | NEQ { if ($incard) val="NotEqual"; else val="notEqual"; }
  | LEQ { if ($incard) val="LessEqual"; else val="lessEqual"; }
  | GEQ { if ($incard) val="GreaterEqual"; else val="greaterEqual"; }
  | LT { if ($incard) val="Less"; else val="less"; }
  | GT { if ($incard) val="Greater"; else val="greater"; }
  ) -> { %{val} }
  ;

guardOperator
  : v=varClassElement -> { $v.st }
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
  
