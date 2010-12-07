parser grammar DeclarativePartParserSN;

options {
  language = Java;
  //output = template;
  tokenVocab = DeclarativePartLexer;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;
  
  import java.util.Map;
	import java.util.HashMap;
	import java.util.ArrayList;
}

@members {
	Map<String,String> symbols = new HashMap<String,String>();
	public Map<String,String> getSymbols() { return symbols; }
	
	private boolean is_class(String id) { return "class".equals(symbols.get(id)); }
  private boolean is_domain(String id) { return "domain".equals(symbols.get(id)); }
  private boolean is_variable(String id) { return "variable".equals(symbols.get(id)); }
}

// the starting rule
declaration[String gap] returns [String value]
@init { $value = gap + "<attribute name=\"declaration\">\n"; }
@after { $value = $value + gap + "</attribute>\n"; } :
   c1=classSection[$gap+"\t"] { $value = $value + $c1.value; }
  (c2=equivalenceSection[$gap+"\t"] { $value = $value + $c2.value; })?
  (c3=domainSection[$gap+"\t"] { $value = $value + $c3.value; })?
  c4=variableSection[$gap+"\t"] { $value = $value + $c4.value; } ;

// the class declaration section
classSection[String gap] returns [String value] : CLASS l=classDeclarationList[$gap]
{ $value = $l.value;
} ;

classDeclarationList[String gap] returns [String value] : 
  e=classDeclaration[$gap] { $value = $e.value; } |
  e=classDeclaration[$gap] l=classDeclarationList[$gap] { $value = $e.value + $l.value; } ;

classDeclaration[String gap] returns [String value]
@init {
  boolean circular=false;
  $value = ""; } :
  id=IDENTIFIER IS (CIRCULAR { circular=true; })? d=classDescription[$gap+"\t\t"] SEMICOLON { symbols.get($id.getText()) == null }?
{ symbols.put($id.getText(),"class");
  
  $value = $value + gap + "<attribute name=\"classeDeclaration\">\n";
  // balise name
  $value = $value + gap + "\t<attribute name=\"name\">" + $id.getText() + "</attribute>\n"; // fermeture de la balise name
  // balise classType
  $value = $value + gap + "\t<attribute name=\"classType\">\n";
  $value = $value.concat($d.value);
  $value = $value + gap + "\t</attribute>\n"; // fermeture de la balise classType
  // balise circular
  $value = $value + gap + "\t<attribute name=\"circular\">";
  if (circular) $value = $value.concat("true"); else $value = $value.concat("false");
  $value = $value.concat("</attribute>\n"); // fermeture de la balise circular
  $value = $value + gap + "</attribute>\n"; // fermeture de la balise classeDeclaration
  
  System.out.println("blabla");
} ;

classDescription[String gap] returns [String value]
@init { $value=""; } : 
  e=interval[$gap]
{ $value = $value + $e.value;
} |
  c=classEnum[$gap] { $value = $value + $c.value; } ;
   
classEnum[String gap] returns [String value] 
@init { $value=""; } :
  LHOOK e=listIdentifier[$gap+"\t"] RHOOK 
{ $value = $value + gap + "<attribute name=\"classEnum\">\n" + $e.value + gap + "</attribute>\n";
} ;

interval[String gap] returns [String value]
@init { $value = ""; } : e=INTEGER DOUBLEDOT f=INTEGER
{ $value = $value + gap + "<attribute name=\"classIntInterval\">\n";
  $value = $value + gap + "\t<attribute name=\"lowerBound\">" + $e.text + "</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"higherBound\">" + $f.text + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} ;

listIdentifier[String gap] returns [String value]
@init { $value = ""; } : ids+=IDENTIFIER (COMA ids+=IDENTIFIER)* // il faut tester que les identifiants sont uniques 
{ for (Object x : $ids) {
    $value = $value + gap + "<attribute name=\"enumValue\">";
    $value = $value + ((Token)x).getText();
    $value = $value + "</attribute>\n";
  }
};

// the equivalence declaration section
equivalenceSection[String gap] returns [String value] : EQUIV e=equivalenceDeclarationList[$gap] { $value = $e.value; } ;

equivalenceDeclarationList[String gap] returns [String value] :
  e=equivalenceDeclaration[$gap] { $value = $e.value; } (l=equivalenceDeclarationList[$gap] { $value = $value + $l.value ; })? ;
  
equivalenceDeclaration[String gap] returns [String value]
@init { $value = ""; } :
  IN id=IDENTIFIER COLON d=equivalenceDescription[$gap+"\t\t"] SEMICOLON { is_class($id.getText()) }? 
{ 
  $value = $value + gap + "<attribute name=\"scsDeclaration\">\n";
  $value = $value + gap + "\t<attribute name=\"type\">";
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value + gap + "\t<attribute name=\"scsType\">\n";
  $value = $value.concat($d.value);
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "</attribute>\n";
};

equivalenceDescription[String gap] returns [String value] 
@init { $value=""; } :
  e=intervalDefinition[$gap] { $value = $value.concat($e.value); } (COMA l=equivalenceDescription[$gap] { $value = $value.concat($l.value); })? ;

intervalDefinition[String gap] returns [String value] 
@init { $value=""; } :
  i=interval[$gap]
{
  $value = $value.concat($i.value); 
} | 
  e=INTEGER 
{ $value = $value + gap + "<attribute name=\"classIntInterval\">\n";
  $value = $value + gap + "\t<attribute name=\"lowerBound\">" + $e.getText() + "</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"higherBound\">" + $e.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  c=classEnum[$gap]
{ $value = $value.concat($c.value);
} |
  f=IDENTIFIER
{ $value = $value + gap + "<attribute name=\"classEnum\">\n";
  $value = $value + gap + "\t<attribute name=\"enumValue\">" + $f.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} ;

// the domain declaration section
domainSection[String gap] returns [String value] : DOMAIN d=domainDeclarationList[$gap] { $value = $d.value; } ;

domainDeclarationList[String gap] returns [String value] 
@init { $value = ""; } :
  d=domainDeclaration[$gap] { $value = $value.concat($d.value); } (l=domainDeclarationList[$gap] { $value = $value.concat($l.value); })? ;

domainDeclaration[String gap] returns [String value]
@init { $value=""; } :
  id=IDENTIFIER IS d=domainDescription[$gap+"\t\t"] SEMICOLON { symbols.get($id.getText())==null }?
{ symbols.put($id.getText(),"domain");
  $value = $value + gap + "<attribute name=\"domainDeclaration\">\n";
  $value = $value + gap + "\t<attribute name=\"name\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "\t<attribute name=\"domainType\">\n";
  $value = $value.concat($d.value);
  $value = $value + gap + "\t</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} ;

domainDescription[String gap] returns [String value] 
@init { $value = ""; } :
  b=bagDefinition[$gap] { $value = $value.concat($b.value); } |
  s=singleDomain[$gap] 
{ $value = $value + gap + "<attribute name=\"cartesianProduct\">\n";
  $value = $value + gap + "\t<attribute name=\"type\">" + $s.value + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} |
  p=productDefinition[$gap] { $value = $value.concat($p.value); } ;

singleDomain[String gap] returns [String value] : id=IDENTIFIER { is_domain($id.getText()) || is_class($id.getText()) }?
{ $value = $id.getText(); } ;

bagDefinition[String gap] returns [String value] : BAG LPAREN id=IDENTIFIER RPAREN { is_class($id.getText()) }?
{ $value = "";
  $value = $value + gap + "<attribute name=\"domainBag\">\n";
  $value = $value + gap + "\t<attribute name=\"type\">" + $id.getText() + "</attribute>\n";
  $value = $value + gap + "</attribute>\n";
} ;

productDefinition[String gap] returns [String value]
@init { $value = gap + "<attribute name=\"cartesianProduct\">\n"; } :
  LT p=productElementList[$gap+"\t"] GT
{ $value = $value.concat($p.value);
  $value = $value + gap + "</attribute>\n";
} ;
  
productElementList[String gap] returns [String value]
@init { $value = ""; } :
  id=IDENTIFIER
{ $value = $value + gap + "<attribute name=\"type\">" + $id.getText() + "</attribute>\n";
} (COMA p=productElementList[$gap] { $value = $value.concat($p.value); })? ;

// the variable declaration section
variableSection[String gap] returns [String value] : VAR l=variableDeclarationList[$gap] { $value = $l.value; } ;

variableDeclarationList[String gap] returns [String value]
@init { $value=""; } :
  v=variableDeclaration[$gap] { $value = $value.concat($v.value); } (l=variableDeclarationList[$gap] { $value = $value.concat($l.value); })? ;

variableDeclaration[String gap] returns [String value]
@init {
  $value = "";
  boolean unique = false;
} :
  lid=listVarIdentifier IN (UNIQUE { unique=true; })? idd=IDENTIFIER SEMICOLON { is_domain($idd.getText()) || is_class($idd.getText()) }?  
{ for ( String id : $lid.listId ) {
    symbols.put(id,"variable");
    $value = $value + gap + "<attribute name=\"variableDeclaration\">\n";
    $value = $value + gap + "\t<attribute name=\"name\">" + id + "</attribute>\n";
    $value = $value + gap + "\t<attribute name=\"type\">" + $idd.getText() + "</attribute>\n";
    $value = $value + gap + "\t<attribute name=\"unique\">";
    if (unique) $value = $value + "true"; else $value = $value + "false";
    $value = $value + "</attribute>\n";
    $value = $value + gap + "</attribute>\n";
  }
} ;

listVarIdentifier returns [List<String> listId]
@init { listId = new ArrayList<String>(); } :
  id=IDENTIFIER { symbols.get($id.getText()) == null }? (COMA l=listVarIdentifier { listId = $l.listId; })? { listId.add($id.getText()); } ;

