parser grammar DeclarativePartParserSN;

options {
  language = Java;
  output = template;
  tokenVocab = DeclarativePartLexer;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
  
  import java.util.Map;
	import java.util.HashMap;
	import java.util.ArrayList;
	import java.util.Arrays;
}

@members {
	Map<String, String> symbols = new HashMap<String, String>();
	public Map<String, String> getSymbols() { return symbols; }
	
	private String formalism;
	
	private boolean is_class(String id) { return "class".equals(symbols.get(id)); }
  private boolean is_domain(String id) { return "domain".equals(symbols.get(id)) || "domain_bag".equals(symbols.get(id)); }
  private boolean is_domain_bag(String id) { return "domain_bag".equals(symbols.get(id)); }
  private boolean is_variable(String id) { return "variable".equals(symbols.get(id)) || "variable_bag".equals(symbols.get(id)); }
}

@rulecatch {
  catch (RecognitionException re) {
    throw re;
  }
}

// the starting rule
declaration[String netFormalism]
@init {
  formalism = netFormalism;
}
  : c+=classSection (c+=equivalenceSection)? (c+=domainSection)? c+=variableSection
  -> balise(name={"declaration"}, content={ $c })
  ;

// the class declaration section
classSection
  : CLASS (e+=classDeclaration)+ -> delist(arg={$e})
  ;

classDeclaration
@init {
  boolean circular=false;
}
  : id=IDENTIFIER IS (CIRCULAR { circular=true; })? d=classDescription[$id.getText()] SEMICOLON { symbols.get($id.getText()) == null }?
  { symbols.put($id.getText(),"class"); }
  {
    StringTemplate[] tmp = { %balise(name={"name"}, content={$id.getText()}), %balise(name={"classType"}, content={$d.st}), %balise(name={"circular"}, content={circular?"true":"false"}) };
  }
  -> balise(name={"classDeclaration"}, content={ Arrays.asList(tmp) })
  ;

classDescription[String class_id]
  : e=interval -> { $e.st }
  | c=classEnum[$class_id] -> { $c.st }
  ;
   
classEnum[String class_id] 
  : LHOOK e=listIdentifier[$class_id] RHOOK
  -> balise(name={"classEnum"}, content={$e.st})
  ;

classEnum2
  : LHOOK e=listIdentifier2 RHOOK
  -> balise(name={"classEnum"}, content={ $e.st })
  ;

listIdentifier2
  : ids+=IDENTIFIER (COMA ids+=IDENTIFIER)* // il faut tester que les identifiants sont uniques
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    for (Object x : $ids) {
      tmp.add( %balise(name={"enumValue"}, content={((Token)x).getText()}) );
    }
  }
  -> delist(arg={tmp})
  ;

interval
  : e=INTEGER DOUBLEDOT f=INTEGER
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    tmp.add( %balise(name={"lowerBound"}, content={$e.getText()}) );
    tmp.add( %balise(name={"higherBound"}, content={$f.getText()}) );
  }
  -> balise(name={"classIntInterval"}, content={ tmp })
  ;

listIdentifier[String class_id]
  : ids+=IDENTIFIER (COMA ids+=IDENTIFIER)* // il faut tester que les identifiants sont uniques 
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    for (Object x : $ids) {
      String xText = ((Token)x).getText();
      if (symbols.get(xText) == null) {
        symbols.put(xText, $class_id);
      }
      tmp.add( %balise(name={"enumValue"}, content={xText}) );
    }
  }
  -> delist(arg={tmp})
  ;

// the equivalence declaration section
equivalenceSection
  : EQUIV (e+=equivalenceDeclaration)+ -> delist(arg={$e})
  ;
  
equivalenceDeclaration
  : IN id=IDENTIFIER COLON d=equivalenceDescription SEMICOLON { is_class($id.getText()) }?
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    tmp.add( %balise(name={"type"}, content={$id.getText()}) );
    tmp.add($d.st);
  }
  -> balise(name={"scsDeclaration"}, content={ tmp })
  ;

equivalenceDescription
  : e+=namedIntervalDefinition (COMA e+=namedIntervalDefinition)* -> delist(arg={$e})
  ;

namedIntervalDefinition
@init {
  List<StringTemplate> tmp = new ArrayList<StringTemplate>();
}
  : (id=IDENTIFIER IS { symbols.get($id.getText()) == null }?
      { symbols.put($id.getText(), "scs"); }
      { tmp.add( %balise(name={"name"}, content={$id.getText()}) ); }
    )? i=intervalDefinition
  {
    tmp.add( %balise(name={"scsType"}, content={$i.st}) );
  }
  -> balise(name={"scs"}, content={ tmp })
  ;

intervalDefinition 
  : i=interval -> { $i.st }
  | e=INTEGER
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    tmp.add(templateLib.getInstanceOf("balise", new STAttrMap().put("name", "lowerBound").put("content", $e.getText())));
    tmp.add(templateLib.getInstanceOf("balise", new STAttrMap().put("name", "higherBound").put("content", $e.getText())));
  }
  -> balise(name={"classIntInterval"}, content={ tmp })
  | c=classEnum2 -> { $c.st }
  | f=IDENTIFIER
  -> balise(
    name={"classEnum"},
    content={ %balise(name={"enumValue"}, content={$f.getText()}) }
  )
  ;

// the domain declaration section
domainSection
  : DOMAIN (d+=domainDeclaration)+ -> delist(arg={$d})
  ;

domainDeclaration
  : id=IDENTIFIER IS { symbols.get($id.getText())==null }? d=domainDefinition[$id.getText()]
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    tmp.add( %balise(name={"name"}, content={$id.getText()}) );
    tmp.add( %balise(name={"domainType"}, content={ $d.st }) );
  }
  -> balise(name={"domainDeclaration"}, content={ tmp })
  ;
  
domainDefinition[String id]
  : b=bagDefinition SEMICOLON { symbols.put($id,"domain_bag"); } -> { $b.st }
  | s=singleDomain SEMICOLON { symbols.put($id,"domain"); }
  -> balise(
    name={"cartesianProduct"},
    content = { %balise(name={"type"}, content={$s.st}) }
  )
  | p=productDefinition SEMICOLON { symbols.put($id,"domain"); } -> { $p.st }
  ;
  
singleDomain
  : id=IDENTIFIER { is_domain($id.getText()) || is_class($id.getText()) }?
  -> { %{$id.getText()} }
  ;

bagDefinition
  : BAG LPAREN id=IDENTIFIER RPAREN { is_class($id.getText()) }?
  -> balise(
    name={"domainBag"},
    content={ %balise(name={"type"}, content={$id.getText()}) }
  )
  ;

productDefinition
  : LT p=productElementList GT
  -> balise(name={"cartesianProduct"}, content={$p.st})
  ;
  
productElementList
  : ids+=IDENTIFIER (COMA ids+=IDENTIFIER)*
  -> cartesianProduct(arg={$ids})
  ;

// the variable declaration section
variableSection
  : VAR (v+=variableDeclaration)+ -> delist(arg={$v})
  ;

variableDeclaration
@init {
  boolean unique = false;
}
  : lid=listVarIdentifier IN (UNIQUE { unique=true; })? idd=IDENTIFIER SEMICOLON { is_domain($idd.getText()) || is_class($idd.getText()) }?  
  {
    List<StringTemplate> tmp = new ArrayList<StringTemplate>();
    boolean isForm = (formalism == "SNB");
    for ( String id : $lid.listId ) {
      if (is_domain_bag($idd.getText()))
        symbols.put(id,"variable_bag");
      else
        symbols.put(id,"variable");
      tmp.add( %variable_decl(id={id}, idd={$idd.getText()}, form={isForm}, unique={unique?"true":"false"}) );
    }
  }
  -> delist(arg={tmp})
  ;

listVarIdentifier returns [List<String> listId]
@init { retval.listId = new ArrayList<String>(); }
  : id=IDENTIFIER { symbols.get($id.getText()) == null }? (COMA l=listVarIdentifier { retval.listId = $l.listId; })? { retval.listId.add($id.getText()); }
  ;
