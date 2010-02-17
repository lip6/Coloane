grammar TinaNDR;

@lexer::header {
package fr.lip6.move.coloane.extension.importExportTINA.importFromTINA.parser;

}

@parser::header {
package fr.lip6.move.coloane.extension.importExportTINA.importFromTINA.parser;


import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.IAttribute;


import java.util.HashMap;
import java.util.Map;
}

@parser::members {
       private IGraph graph = new GraphModelFactory().createGraph("Time Petri Net");
       private Map<String,INode> nodes = new HashMap<String, INode>();
       private INode source;
       private INode destination;
}



tinaGraphicalModel returns [IGraph graph] : (trdesc|pldesc)* (edgedesc|prdesc)* netdesc
{
  graph = this.graph;
};


netdesc : 'h' NAME (nodesize (bgcolor)?)? ;

nodesize :  'small' | 'normal' | 'large' ;

bgcolor : NAME ;




prdesc  : 'pr' 
  (node=tname 
  {}
  )+ ('<'|'>') 
  (node=tname
  {}
  )+ ;


trdesc  
scope { INode idTrans; } 
: 't' xpos=FLOAT ypos=FLOAT node=tname 
{
  $trdesc::idTrans = node;
}
( eft lft anchor )
|
( anchor eft lft anchor label=NAME anchor )
{
  $trdesc::idTrans.getAttribute("label").setValue($label.getText());
}
;

eft : ('-'
{
  // TODO : complain if open interval
}
)? ENTIER
{
 $trdesc::idTrans.getAttribute("earliestFiringTime").setValue($eft.getText());
};
lft : ('-'
{
  // TODO : complain if open interval
}
)? (ENTIER|'w')
{
  $trdesc::idTrans.getAttribute("latestFiringTime").setValue($lft.getText());
  $trdesc::idTrans.getAttribute("latestFiringTime").setValue("inf");
};
anchor : 'n' | 'nw' | 'w' | 'sw' | 's' | 'se' | 'e' | 'ne' | 'c' ;


pldesc 
scope { INode idPlace; } 
: 'p' xpos=FLOAT ypos=FLOAT node=pname 
{
       $pldesc::idPlace = node;
}
mk=integer anchor
{  
  // marking
  if (mk != 0)
         $pldesc::idPlace.getAttribute("marking").setValue(Integer.toString(mk));
  
}
(label=NAME anchor)? 
{
  // labels on places not supported currently.
}                
;

edgedesc : 'e' srcname=NAME target=NAME arc anchor
          | 'e' srcname=NAME ang=FLOAT rad=FLOAT target=NAME angt=FLOAT radt=FLOAT arc anchor ;



lbdesc    : 'lb' nodeName=NAME label=NAME
{
  INode node = nodes.get(nodeName.getText());
  if (node != null) {
    IAttribute lab = node.getAttribute("label");
    if (lab != null) {
      lab.setValue(label.getText());
    }
  }
};

pname returns [INode node] : name=NAME
{
    node = nodes.get($name.getText());
    if (node == null) {
        try {
                node = graph.createNode("place");
                node.getAttribute("name").setValue(name.getText());
                // add for later reference by name
                nodes.put(name.getText(), node);                
       }catch (ModelException e) {                
                e.printStackTrace();
       }        
    }
};

tname returns [INode node] : name=NAME
{
    node = nodes.get($name.getText());
    if (node == null) {
        try {
                node = graph.createNode("transition");
                node.getAttribute("label").setValue(name.getText());
                // add for later reference by name
                nodes.put(name.getText(), node);                
       }catch (ModelException e) {                
                e.printStackTrace();
       }        
    }
};


tinput : (node=pname 
  {
    this.destination = node;
    this.source = $trdesc::idTrans;
  }
  arc)*;

toutput : (node=pname 
  {
    this.destination = $trdesc::idTrans;
    this.source = node;    
  }
  arc)*;

poutput: (node=tname 
  {
    this.destination = $pldesc::idPlace;
    this.source = node;
  }
  arc)*;

pinput: (node=tname 
  {
    this.destination = node;
    this.source = $pldesc::idPlace;
  }
  arc)*;

arc : type=('*'|'?'|'?-'|'!'|'!-') value=integer  
{
  IArc a = null;
  try {
  if ("*".equals(type.getText())) {
      // normal arc
      a = graph.createArc("arc",source,destination);
  } else if ("?".equals(type.getText())) {
      // test arc
     a = graph.createArc("read",source,destination);
  } else if ("?-".equals(type.getText())) {
      // inhibitor arc
     a = graph.createArc("inhibitor",source,destination);  
  } else if ("!".equals(type.getText())) {
      // stopwatch arc
    // TODO !!
  } else if ("!-".equals(type.getText())) {
      // stopwatch inhibitor arc
    // TODO !!
  }
  }catch (ModelException e) {                
                e.printStackTrace();
  }  
  if (a != null) {
    a.getAttribute("valuation").setValue(Integer.toString(value));
  }
}
| 
{
 try {
  IArc a = graph.createArc("arc",source,destination);
  a.getAttribute("valuation").setValue("1");
  }catch (ModelException e) {                
                e.printStackTrace();
  }  
}
;

integer  returns [int value]:  
    n=ENTIER 
    { value = Integer.parseInt($n.getText()); }
    ('K'
    { value = 1000 * value; }
    |'M'
    { value = 1000000 * value; }
    )?
    ;

/****** Basics */
fragment LETTER : 'a'..'z' | 'A'..'Z' | '_' | '\''
  ;
// ignore comments
COMMENT : '#'.*'\n' {$channel = HIDDEN; }
  ; 
// ignore whitespace
WS  : (' ' | '\t' | '\n' | '\r') { $channel = HIDDEN; }
  ;
fragment DIGIT  : '0'..'9'
  ;
ENTIER : DIGIT (DIGIT)*
  ;
FLOAT : DIGIT (DIGIT)* ('.' DIGIT (DIGIT)*)? ;
fragment STRING : '{'.*'}'
  ;
NAME  : STRING | (LETTER (LETTER | DIGIT)*)
  ;
  
