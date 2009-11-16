grammar Prod;

@lexer::header {
package fr.lip6.move.coloane.extension.importFromPROD.parser;

}

@parser::header {
package fr.lip6.move.coloane.extension.importFromPROD.parser;

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
       private IGraph graph = new GraphModelFactory().createGraph("Time Petri Net");
       private Map<String,INode> nodes = new HashMap<String, INode>();
}


prodModel returns [IGraph graph] : node*
{
   graph = this.graph;
};

node : place|transition;


place : lvl=placeLevel pname=VARIABLE mk=initMarquage 
      {
             try {
                INode node = graph.createNode("place");
                node.getAttribute("name").setValue($pname.getText());
                // add for later reference by name
                nodes.put($pname.getText(), node);
                if (mk != 0)
                  node.getAttribute("marking").setValue(Integer.toString(mk));
                // TODO : handle the place level
              } catch (ModelException e) {                
                e.printStackTrace();
              }       
       }
      ;

placeLevel returns [int lvl]: 
  PLACE '(' level=ENTIER ')' 
  { lvl = Integer.parseInt($level.text); }
  | PLACE 
  { lvl= 0; };

nomtransition  returns [String name] : TRANS n=VARIABLE { name = $n.getText() ;};

transition 
scope { INode idTrans; } 
: tname=nomtransition 
{
       try {
           INode node = graph.createNode("transition");
           node.getAttribute("name").setValue(tname);
           $transition::idTrans = node ;
       } catch (ModelException e) {                
                e.printStackTrace();
       }         
} 
entree sortie ENDTR

;

entree    : UGLYPREFIX_IN '{' arcin* '}'
    |
    ;

sortie    : UGLYPREFIX_OUT '{' arcout* '}'
  |
  ;

arcin :  
pname=VARIABLE ':' ntok=marquage ';'
{
  // A simple input arc
  INode place = nodes.get($pname.getText());
  INode tr = $transition::idTrans;
  try {
            IArc a = graph.createArc("arc",place,tr);
            a.getAttribute("valuation").setValue(Integer.toString(ntok));
  } catch (ModelException e) {
            e.printStackTrace();
  }

}
| pname=VARIABLE ':' UGLYPREFIX_RESET ';'
{
  // A reset arc
    
  INode place = nodes.get($pname.getText());
  INode tr = $transition::idTrans;
  try {
            IArc a = graph.createArc("reset",place,tr);
  } catch (ModelException e) {
            e.printStackTrace();
  }

}
    | pname=VARIABLE UGLYPREFIX_INHIBITOR ntok2=ENTIER ';'
{
 // A inhibitor arc
  INode place = nodes.get($pname.getText());
  INode tr = $transition::idTrans;
  try {
            IArc a = graph.createArc("inhibitor",place,tr);
            a.getAttribute("valuation").setValue($ntok2.getText());
  } catch (ModelException e) {
            e.printStackTrace();
  }


};

arcout : 
pname=VARIABLE ':' ntok=marquage ';'
{
  // A simple input arc
  INode place = nodes.get($pname.getText());
  INode tr = $transition::idTrans;
  try {
            IArc a = graph.createArc("arc",tr,place);
            a.getAttribute("valuation").setValue(Integer.toString(ntok));
  } catch (ModelException e) {
            e.printStackTrace();
  }

};

initMarquage returns [int mark]:
  MK '(' n=marquage ')'
  {
    mark = n;
  }
  |
  {
    mark = 0;
  };

marquage  returns [int mark]: 
     TOKEN  
    { mark = 1; }    
    | n=ENTIER TOKEN 
    { mark = Integer.parseInt($n.getText()); }    
    ;

TOKEN : '<..>'  ;

UGLYPREFIX_INHIBITOR : '<'  ;
TRANS : '#trans';
PLACE : '#place';
ENDTR : '#endtr';
UGLYPREFIX_RESET : 'RESET';
UGLYPREFIX_IN : 'in';
UGLYPREFIX_OUT: 'out';
MK:'mk';
/****** Basics */
fragment LETTER : 'a'..'z' | 'A'..'Z' | '_'
  ;
// ignore comments
COMMENT : '//'.*'\n' {$channel = HIDDEN; }
  ; 
// ignore whitespace
WS  : (' ' | '\t' | '\n' | '\r') { $channel = HIDDEN; }
  ;
fragment DIGIT  : '0'..'9'
  ;
ENTIER : DIGIT (DIGIT)*
  ;
fragment STRING : '"'.*'"'
  ;
VARIABLE  : STRING | (LETTER (LETTER | DIGIT)*)
  ;
  


