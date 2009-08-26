grammar SGRomeo;

@lexer::header {
package fr.lip6.move.coloane.extension.importFromSGRomeo.parser;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

}

@parser::header {
package fr.lip6.move.coloane.extension.importFromSGRomeo.parser;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.extensions.IImportFrom;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;


import java.util.HashMap;
import java.util.Map;
}

@parser::members {
       private IGraph graph = new GraphModelFactory().createGraph("Reachability Graph");
       private Map<String,INode> nodes = new HashMap<String, INode>();
       
       private INode getNode (String nodeId) {
            INode node = nodes.get(nodeId);
            if (node == null) {
              try {
                 node = graph.createNode("state");
                 nodes.put(nodeId, node);
              } catch (ModelException e) {
                 e.printStackTrace();
              }
            }
            return node;
       }
}


romeoSGModel returns [IGraph graph] : (sep|arc)* state* 
{
   graph = this.graph;
};

arc : idSrc=VARIABLE PREARROW trans=label POSTARROW idDest=VARIABLE 
{
  INode src = getNode($idSrc.getText()); 
  INode dest = getNode($idDest.getText()); 
        try {
        IArc arc = graph.createArc("event",src,dest);
                arc.getAttribute("label").setValue(trans);  
        } catch (ModelException e) {
        e.printStackTrace();
          }
};

sep : LINESEPARATOR  ;

state scope { StringBuffer sb; } : 
{ 
  $state::sb = new StringBuffer();
}
(
sep id=VARIABLE sep 
'Marking'  { $state::sb.append("Marking \n");}
placeMarking* 
'Firing Domain' { $state::sb.append("Firing Domain \n");}
clockDomain* clockConstraint* 
sep
)
{
  INode node = getNode($id.getText());
  node.getAttribute("value").setValue($state::sb.toString());
  $state::sb=null;
}
;

placeMarking : mark=ENTIER '   ' pname=label 
{
  if ( ! "0".equals(mark.getText() )) {
      ($state::sb).append($mark.getText() + "  " + pname + "\n");
  }
}; 

clockDomain : label ' [' ENTIER ',' (ENTIER ']' | 'inf[') { ($state::sb).append($text+"\n"); };

clockConstraint : label MINUS label LESSTHAN ENTIER { ($state::sb).append($text+"\n"); };


label returns [String s=""] : 
  //{ s = ""; } 
  ( 
    (
      var=VARIABLE 
      { s+= $var.getText(); }
     ) 
    |
    ( 
      '-' 
      { s+="_";} 
    )
    |
    ( 
      ' ' 
      { s+="";} 
    )
    |
    (
     val=ENTIER
     {s += $val.getText();}
     )
   )*;


LINESEPARATOR: ('-')+ ('\n' | '\r') ;
PREARROW: ' -- ';
POSTARROW: ' --> ';
MINUS : ' - ';
LESSTHAN : ' <= ';
/****** Basics */
fragment LETTER : 'a'..'z' | 'A'..'Z' | '_'
  ;
fragment DIGIT  : '0'..'9'
  ;
fragment STRING : '"'.*'"'
  ;
// ignore whitespace
WS  : ( '\t' | '\n' | '\r') { $channel = HIDDEN; }
  ;
ENTIER : ('-'?) DIGIT (DIGIT)*
  ;
VARIABLE  : STRING | (LETTER (LETTER | DIGIT)*)
  ;
// LABEL : ' ' ( options{greedy=false;}: .* ) ' ';

// LETTER (LETTER | DIGIT | ' ' | '-')* );
  


