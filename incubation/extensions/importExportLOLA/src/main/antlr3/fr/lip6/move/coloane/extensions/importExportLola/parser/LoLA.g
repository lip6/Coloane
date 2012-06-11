grammar LoLA;

@lexer::header {
package main.antlr3.fr.lip6.move.coloane.extensions.importExportLola.parser;
  
}

@parser::header {
package main.antlr3.fr.lip6.move.coloane.extensions.importExportLola.parser;


import fr.lip6.move.coloane.core.model.factory.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;

import java.util.HashMap;
import java.util.Map;
}


@parser::members {
       private IFormalism formalism;
       private INodeFormalism placeFormalism;
       private INodeFormalism transitionFormalism;
       private IArcFormalism arcFormalism;

       private IGraph graph;
       private Map<String,INode> nodes = new HashMap<String, INode>();
       private INode source;
       private INode destination;
       
       public void setFormalism(IFormalism formalism) {
           this.formalism = formalism;
           this.placeFormalism = (INodeFormalism) formalism.getRootGraph().getElementFormalism("place");
           this.transitionFormalism = (INodeFormalism) formalism.getRootGraph().getElementFormalism("transition");
           this.arcFormalism = (IArcFormalism) formalism.getRootGraph().getElementFormalism("arc");
           
           graph = new GraphModelFactory().createGraph(formalism);
       }
}

//Resulting graph
lolaModel returns [IGraph graph] : placeDesc markDesc transDesc*
{
   graph = this.graph;
};

//Place description
placeDesc : 'PLACE' firstNode=pname (',' nextNode=pname)* (';');

//Marking description
markDesc : 'MARKING' marking (',' marking )* (';');

//Sets the correct marking value to a node
marking : pl=NAME ':' value=INT
{ 
  INode node = nodes.get($pl.getText());
  if (node != null) {
    IAttribute mark = node.getAttribute("marking");
    if (mark != null) {
      int newValue = Integer.parseInt(mark.getValue()) + Integer.parseInt(value.getText());
      mark.setValue(Integer.toString(newValue));
    }
  }
};

//Transition description
transDesc  
scope { INode idTrans; } 
: 'TRANSITION' node=tname 
{
  $transDesc::idTrans = node;
}
( 'CONSUME' )
( fromPlace ) (',' fromPlace)* (';')
( 'PRODUCE' )
( toPlace ) (',' toPlace)* (';')
;

//Returns the matching place node or creates a new node with the right values
pname returns [INode node] : name=NAME
{
    node = nodes.get($name.getText());
    if (node == null || !node.getNodeFormalism().getName().equals("place")) {
         try {
                  node = graph.createNode(placeFormalism);
                  node.getAttribute("name").setValue(name.getText());
                  nodes.put(name.getText(), node);
         } catch (ModelException e) { e.printStackTrace(); }
    }
};

//Returns the matching transition node or creates a new node with the right values
tname returns [INode node] : name=NAME
{
    node = nodes.get($name.getText());
    if (node == null || !node.getNodeFormalism().getName().equals("transition")) { 
        try 
        {
           node = this.graph.createNode(transitionFormalism);
           node.getAttribute("label").setValue(name.getText());
           nodes.put(name.getText(), node);                
        } catch (ModelException e) { e.printStackTrace(); }        
    }
};

//Gets the incoming arcs to a place node
toPlace : (node=pname 
  {
    this.destination = node;
    this.source = $transDesc::idTrans;
  }
  arc)*;

//Gets the outgoing arcs from a place node
fromPlace : (node=pname 
  {
    this.destination = $transDesc::idTrans;
    this.source = node;    
  }
  arc)+;

//Creates a new arc with the right values
arc : ':' value=INT
{
  IArc a = null;
  try {
    a = graph.createArc(arcFormalism,source,destination);
  } catch (ModelException e) {                
                e.printStackTrace();
  }  
  if (a != null) {
    a.getAttribute("valuation").setValue(value.getText());
  }
}

;

//Net description
net : placeDecl markDecl transDecl* ;

//Places declaration
placeDecl : 'PLACE' NAME (',' NAME )*  ';' ;

//Markings declaration
markDecl : 'MARKING' NAME':' INT (',' NAME ':' INT)*  ';';

//Transitions declaration
transDecl : 'TRANSITION'  NAME 
  'CONSUME' pvalue (','pvalue)* ';'
  'PRODUCE' (pvalue (','pvalue)*)? ';'
  ;

//Place value
pvalue : NAME ':' INT ;


//String to Integer
integer  returns [int value]:  
    n=INT 
    { value = Integer.parseInt($n.getText()); }
;    

/****** Basics ********/
//Letters representation
fragment LETTER : 'a'..'z' | 'A'..'Z' | '_' | '\''
  ;
//Ignore comments
COMMENT : '{'.*'}' {$channel = HIDDEN; }
  ; 

//Digits representation
fragment DIGIT  : '0'..'9'
  ;
INT : DIGIT (DIGIT)*
  ;  

//Ignore white spaces
WS  : (' ' | '\t' | '\n' | '\r'| ',' ) { $channel = HIDDEN; }
  ;

//LoLA identifiers forbidden characters 
EXCLUDE : ('(' | ')' | '{' | '}' | ',' | ';' | ':' | ' ' | '\t' | '\n' | '\r' );  
OPERATOR : ('<->' | '<>' | '->' | '=' | '[]' | '.' | '+' | '-' | '*' | '/' | '|' | '>' | '<' | '#' | '>=' | '<=' ) {$channel = HIDDEN;};
KEYWORD : ('RECORD' | 'END' | 'SORT' | 'FUNCTION' | 'DO' | 'ARRAY' | 'ENUMERATE' | 'CONSTANT' | 'BOOLEAN' | 'OF' | 'BEGIN' | 'WHILE' | 'IF' | 'THEN' | 'ELSE' 
         | 'SWITCH' | 'CASE' | 'NEXTSTEP' | 'REPEAT' | 'FOR' | 'TO' | 'ALL' | 'EXIT' | 'EXISTS' | 'RETURN' | 'TRUE' | 'FALSE' | 'MOD' | 'VAR' | 'GUARD' | 'STATE' 
         | 'PATH' | 'GENERATOR' | 'ANALYSE' | 'PLACE' | 'TRANSITION' | 'MARKING' | 'CONSUME' | 'PRODUCE' | 'FORMULA' | 'EXPATH' | 'ALLPATH' | 'ALWAYS' | 'UNTIL' 
         | 'EVENTUALLY' | 'AND' | 'OR' | 'NOT') {$channel = HIDDEN; };

//Identifier representation  
NAME  :  (DIGIT | LETTER |~EXCLUDE)*;