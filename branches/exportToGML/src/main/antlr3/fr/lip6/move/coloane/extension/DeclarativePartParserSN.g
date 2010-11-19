parser grammar DeclarativePartParserSN;

options {
  language = Java;
  //output = template;
  tokenVocab = DeclarativePartLexer;
}

@header {
  package main.antlr3.fr.lip6.move.coloane.extension;

	import java.util.HashMap;
}

@members {
	HashMap<String,String> symbols = new HashMap<String,String>();
	public HashMap<String,String> getSymbols() { return symbols; }
	
	private boolean is_class(String id) { return symbols.get(id) == "class"; }
	private boolean is_domain(String id) { return symbols.get(id) == "domain"; }
	private boolean is_variable(String id) { return symbols.get(id) == "variable"; }
}

// the starting rule
declaration returns [String value]
@init { $value = ""; } :
   c1=classSection { $value = $value + $c1.value; }
  (c2=equivalenceSection { $value = $value + $c2.value; })?
  (c3=domainSection { $value = $value.concat($c3.value); })?
  c4=variableSection { $value = $value.concat($c4.value); } ;

// the class declaration section
classSection returns [String value] : CLASS l=classDeclarationList
{
  $value = $l.value;
} ;

classDeclarationList returns [String value] : 
  e=classDeclaration { $value = $e.value; } |
  e=classDeclaration l=classDeclarationList { $value = $e.value.concat($l.value); } ;

classDeclaration returns [String value]
@init {
  boolean circular=false;
} 
: id=IDENTIFIER IS (CIRCULAR { circular=true; })? d=classDescription SEMICOLON { symbols.get($id.getText())==null }?
{ symbols.put($id.getText(),"class");
  
  $value = "";
  $value = $value.concat("<attribute name=\"classeDeclaration\">\n");
  // balise name
  $value = $value.concat("<attribute name=\"name\">");
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n"); // fermeture de la balise name
  // balise classType
  $value = $value.concat("<attribute name=\"classType\">\n");
  $value = $value.concat($d.value);
  $value = $value.concat("</attribute>\n"); // fermeture de la balise classType
  // balise circular
  $value = $value.concat("<attribute name=\"circular\">");
  if (circular) $value = $value.concat("true"); else $value = $value.concat("false");
  $value = $value.concat("</attribute>\n"); // fermeture de la balise circular
  $value = $value.concat("</attribute>\n"); // fermeture de la balise classeDeclaration
} ;

classDescription returns [String value]
@init { $value=""; } : 
  e=interval
  {
    $value = $value.concat($e.value);
  } |
  c=classEnum { $value = $value.concat($c.value); } ;
   
classEnum returns [String value] 
@init { $value=""; } :
  LHOOK e=listIdentifier RHOOK 
{
  $value = $value.concat("<attribute name=\"classEnum\">");
  $value = $value.concat($e.value);
  $value = $value.concat("</attribute>\n");
} ;

interval returns [String value] : e=INTEGER DOUBLEDOT f=INTEGER
{
  $value="";
  $value = $value.concat("<attribute name=\"classIntInterval\">\n");
  $value = $value.concat("<attribute name=\"lowerBound\">");
  $value = $value.concat($e.text);
  $value = $value.concat("</attribute>");
  $value = $value.concat("<attribute name=\"higherBound\">");
  $value = $value.concat($f.text);
  $value = $value.concat("</attribute>");
  $value = $value.concat("</attribute>\n");
} ;

listIdentifier returns [String value] : ids+=IDENTIFIER (COMA ids+=IDENTIFIER)* // il faut tester que les identifiants sont uniques 
{
  $value="";
  for (Object x : $ids) {
    $value = $value.concat("<attribute name=\"enumValue\">");
    $value = $value.concat(((Token)x).getText());
    $value = $value.concat("</attribute>\n");
  }
};

// the equivalence declaration section
equivalenceSection returns [String value] : EQUIV e=equivalenceDeclarationList { $value = $e.value; } ;

equivalenceDeclarationList returns [String value] :
  e=equivalenceDeclaration { $value = $e.value; } (l=equivalenceDeclarationList { $value = $value.concat($l.value); })? ;
  
equivalenceDeclaration returns [String value]
@init { $value = ""; } :
  IN id=IDENTIFIER COLON d=equivalenceDescription SEMICOLON { is_class($id.getText()) }? 
{ 
  $value = $value.concat("<attribute name=\"scsDeclaration\">\n");
  $value = $value.concat("<attribute name=\"type\">");
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"scsType\">");
  $value = $value.concat($d.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
};

equivalenceDescription returns [String value] 
@init { $value=""; } :
  e=intervalDefinition { $value = $value.concat($e.value); } (COMA l=equivalenceDescription { $value = $value.concat($l.value); })? ;

intervalDefinition returns [String value] 
@init { $value=""; } :
  i=interval
{
  $value = $value.concat($i.value); 
} | 
  e=INTEGER 
{ $value = $value.concat("<attribute name=\"classIntInterval\">\n");
  $value = $value.concat("<attribute name=\"lowerBound\">");
  $value = $value.concat($e.text);
  $value = $value.concat("</attribute>");
  $value = $value.concat("<attribute name=\"higherBound\">");
  $value = $value.concat($e.text);
  $value = $value.concat("</attribute>");
  $value = $value.concat("</attribute>\n");
} |
  c=classEnum
{ $value = $value.concat($c.value);
} |
  f=IDENTIFIER
{ $value = $value.concat("<attribute name=\"classEnum\">");
  $value = $value.concat("<attribute name=\"enumValue\">");
  $value = $value.concat($f.getText());
  $value = $value.concat("</attribute>");
  $value = $value.concat("</attribute>\n");
} ;

// the domain declaration section
domainSection returns [String value] : DOMAIN d=domainDeclarationList { $value = $d.value; } ;

domainDeclarationList returns [String value] 
@init { $value = ""; } :
  d=domainDeclaration { $value = $value.concat($d.value); } (l=domainDeclarationList { $value = $value.concat($l.value); })? ;

domainDeclaration returns [String value]
@init { $value=""; } :
  id=IDENTIFIER IS d=domainDescription SEMICOLON { symbols.get($id.getText())==null }?
{ symbols.put($id.getText(),"domain");
  $value = $value.concat("<attribute name=\"domainDeclaration\">\n");
  $value = $value.concat("<attribute name=\"name\">");
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"domainType\">");
  $value = $value.concat($d.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} ;

domainDescription returns [String value] 
@init { $value = ""; } :
  b=bagDefinition { $value = $value.concat($b.value); } |
  s=singleDomain 
{ $value = $value.concat("<attribute name=\"cartesianProduct\">\n");
  $value = $value.concat("<attribute name=\"type\">");
  $value = $value.concat($s.value);
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} |
  p=productDefinition { $value = $value.concat($p.value); } ;

singleDomain returns [String value] : id=IDENTIFIER { is_domain($id.getText()) || is_class($id.getText()) }?
{ $value = $id.getText(); } ;

bagDefinition returns [String value] : BAG LPAREN id=IDENTIFIER RPAREN { is_class($id.getText()) }?
{ $value = "";
  $value = $value.concat("<attribute name=\"domainBag\">");
  $value = $value.concat("<attribute name=\"type\">");
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} ;

productDefinition returns [String value]
@init { $value="<attribute name=\"cartesianProduct\">"; } :
  LT p=productElementList GT { $value = $value.concat($p.value); $value = $value.concat("</attribute>\n"); } ;
  
productElementList returns [String value]
@init { $value = ""; } :
  id=IDENTIFIER
{ $value = $value.concat("<attribute name\"type\">");
  $value = $value.concat($id.getText());
  $value = $value.concat("</attribute>\n");
} (COMA p=productElementList { $value = $value.concat($p.value); })? ;

// the variable declaration section
variableSection returns [String value] : VAR l=variableDeclarationList { $value = $l.value; } ;

variableDeclarationList returns [String value]
@init { $value=""; } :
  v=variableDeclaration { $value = $value.concat($v.value); } (l=variableDeclarationList { $value = $value.concat($l.value); })? ;

variableDeclaration returns [String value]
@init {
  $value="";
  boolean unique = false;
} :
  idv=IDENTIFIER { symbols.get($idv.getText()) == null }? IN (UNIQUE { unique=true; })? idd=IDENTIFIER SEMICOLON { is_domain($idd.getText()) || is_class($idd.getText()) }?
{
  $value = $value.concat("<attribute name=\"variableDeclaration\">\n");
  $value = $value.concat("<attribute name=\"name\">");
  $value = $value.concat($idv.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"type\">");
  $value = $value.concat($idd.getText());
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("<attribute name=\"unique\">");
  if (unique) $value = $value.concat("true"); else $value = $value.concat("false");
  $value = $value.concat("</attribute>\n");
  $value = $value.concat("</attribute>\n");
} ;
