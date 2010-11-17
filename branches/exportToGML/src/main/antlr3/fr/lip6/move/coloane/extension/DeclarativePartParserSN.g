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
declaration returns [String value] :
   c1=classSection { $value.concat($c1.value); }
  (c2=equivalenceSection { $value.concat($c2.value); })?
  (c3=domainSection { $value.concat($c3.value); })?
  c4=variableSection { $value.concat($c4.value); } ;

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
  $value.concat("<attribute name=\"classeDeclaration\">\n");
  // balise name
  $value.concat("<attribute name=\"name\">");
  $value.concat($id.getText());
  $value.concat("</attribute>\n"); // fermeture de la balise name
  // balise classType
  $value.concat("<attribute name=\"classType\">\n");
  $value.concat($d.value);
  $value.concat("</attribute>\n"); // fermeture de la balise classType
  // balise circular
  $value.concat("<attribute name=\"circular\">");
  if (circular) $value.concat("true"); else $value.concat("false");
  $value.concat("</attribute>\n"); // fermeture de la balise circular
  $value.concat("</attribute>\n"); // fermeture de la balise classeDeclaration
} ;

classDescription returns [String value]
@init { $value=""; } : 
  e=interval
  {
    $value.concat($e.value);
  } |
  c=classEnum { $value.concat($c.value); } ;
   
classEnum returns [String value] 
@init { $value=""; } :
  LHOOK e=listIdentifier RHOOK 
{
  $value.concat("<attribute name=\"classEnum\">");
  $value.concat($e.value);
  $value.concat("</attribute>\n");
} ;

interval returns [String value] : e=INTEGER DOUBLEDOT f=INTEGER
{
  $value="";
  $value.concat("<attribute name=\"classIntInterval\">\n");
  $value.concat("<attribute name=\"lowerBound\">");
  $value.concat($e.text);
  $value.concat("</attribute>");
  $value.concat("<attribute name=\"higherBound\">");
  $value.concat($f.text);
  $value.concat("</attribute>");
  $value.concat("</attribute>\n");
} ;

listIdentifier returns [String value] : ids+=IDENTIFIER (COMA ids+=IDENTIFIER)* // il faut tester que les identifiants sont uniques 
{
  $value="";
  for (Object x : $ids) {
    $value.concat("<attribute name=\"enumValue\">");
    $value.concat(((Token)x).getText());
    $value.concat("</attribute>\n");
  }
};

// the equivalence declaration section
equivalenceSection returns [String value] : EQUIV e=equivalenceDeclarationList { $value = $e.value; } ;

equivalenceDeclarationList returns [String value] :
  e=equivalenceDeclaration { $value = $e.value; } (l=equivalenceDeclarationList { $value.concat($l.value); })? ;
  
equivalenceDeclaration returns [String value]
@init { $value = ""; } :
  IN id=IDENTIFIER COLON d=equivalenceDescription SEMICOLON { is_class($id.getText()) }? 
{ 
  $value.concat("<attribute name=\"scsDeclaration\">\n");
  $value.concat("<attribute name=\"type\">");
  $value.concat($id.getText());
  $value.concat("</attribute>\n");
  $value.concat("<attribute name=\"scsType\">");
  $value.concat($d.value);
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
};

equivalenceDescription returns [String value] 
@init { $value=""; } :
  e=intervalDefinition { $value.concat($e.value); } (COMA l=equivalenceDescription { $value.concat($l.value); })? ;

intervalDefinition returns [String value] 
@init { $value=""; } :
  i=interval
{
  $value.concat($i.value); 
} | 
  e=INTEGER 
{ $value.concat("<attribute name=\"classIntInterval\">\n");
  $value.concat("<attribute name=\"lowerBound\">");
  $value.concat($e.text);
  $value.concat("</attribute>");
  $value.concat("<attribute name=\"higherBound\">");
  $value.concat($e.text);
  $value.concat("</attribute>");
  $value.concat("</attribute>\n");
} |
  c=classEnum
{ $value.concat($c.value);
} |
  f=IDENTIFIER
{ $value.concat("<attribute name=\"classEnum\">");
  $value.concat("<attribute name=\"enumValue\">");
  $value.concat($f.getText());
  $value.concat("</attribute>");
  $value.concat("</attribute>\n");
} ;

// the domain declaration section
domainSection returns [String value] : DOMAIN d=domainDeclarationList { $value = $d.value; } ;

domainDeclarationList returns [String value] 
@init { $value = ""; } :
  d=domainDeclaration { $value.concat($d.value); } (l=domainDeclarationList { $value.concat($l.value); })? ;

domainDeclaration returns [String value]
@init { $value=""; } :
  id=IDENTIFIER IS d=domainDescription SEMICOLON { symbols.get($id.getText())==null }?
{ symbols.put($id.getText(),"domain");
  $value.concat("<attribute name=\"domainDeclaration\">\n");
  $value.concat("<attribute name=\"name\">");
  $value.concat($id.getText());
  $value.concat("</attribute>\n");
  $value.concat("<attribute name=\"domainType\">");
  $value.concat($d.value);
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} ;

domainDescription returns [String value] 
@init { $value = ""; } :
  b=bagDefinition { $value.concat($b.value); } |
  s=singleDomain 
{ $value.concat("<attribute name=\"cartesianProduct\">\n");
  $value.concat("<attribute name=\"type\">");
  $value.concat($s.value);
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} |
  p=productDefinition { $value.concat($p.value); } ;

singleDomain returns [String value] : id=IDENTIFIER { is_domain($id.getText()) || is_class($id.getText()) }?
{ $value = $id.getText(); } ;

bagDefinition returns [String value] : BAG LPAREN id=IDENTIFIER RPAREN { is_class($id.getText()) }?
{ $value = "";
  $value.concat("<attribute name=\"domainBag\">");
  $value.concat("<attribute name=\"type\">");
  $value.concat($id.getText());
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} ;

productDefinition returns [String value]
@init { $value="<attribute name=\"cartesianProduct\">"; } :
  LT p=productElementList GT { $value.concat($p.value); $value.concat("</attribute>\n"); } ;
  
productElementList returns [String value]
@init { $value = ""; } :
  id=IDENTIFIER
{ $value.concat("<attribute name\"type\">");
  $value.concat($id.getText());
  $value.concat("</attribute>\n");
} (COMA p=productElementList { $value.concat($p.value); })? ;

// the variable declaration section
variableSection returns [String value] : VAR l=variableDeclarationList { $value = $l.value; } ;

variableDeclarationList returns [String value]
@init { $value=""; } :
  v=variableDeclaration { $value.concat($v.value); } (l=variableDeclarationList { $value.concat($l.value); })? ;

variableDeclaration returns [String value]
@init {
  $value="";
  boolean unique = false;
} :
  idv=IDENTIFIER { symbols.get($idv.getText()) == null }? IN (UNIQUE { unique=true; })? idd=IDENTIFIER SEMICOLON { is_domain($idd.getText()) || is_class($idd.getText()) }?
{
  $value.concat("<attribute name=\"variableDeclaration\">\n");
  $value.concat("<attribute name=\"name\">");
  $value.concat($idv.getText());
  $value.concat("</attribute>\n");
  $value.concat("<attribute name=\"type\">");
  $value.concat($idd.getText());
  $value.concat("</attribute>\n");
  $value.concat("<attribute name=\"unique\">");
  if (unique) $value.concat("true"); else $value.concat("false");
  $value.concat("</attribute>\n");
  $value.concat("</attribute>\n");
} ;
