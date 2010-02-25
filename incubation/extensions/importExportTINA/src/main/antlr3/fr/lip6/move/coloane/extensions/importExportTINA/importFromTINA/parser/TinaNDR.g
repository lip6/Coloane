grammar TinaNDR;

@lexer::header {
package fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA.parser;

}

@parser::header {
package fr.lip6.move.coloane.extensions.importExportTINA.importFromTINA.parser;


import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.draw2d.geometry.Point;

import java.util.HashMap;
import java.util.Map;
}

@parser::members {
       private IGraph graph = new GraphModelFactory().createGraph("Time Petri Net");
       private Map<String,INode> nodes = new HashMap<String, INode>();
       private INode source;
       private INode destination;
}



tinaGraphicalModel returns [IGraph graph] : ((trdesc|pldesc)+ (edgedesc|prdesc)* netdesc)|'\n'
{
  graph = this.graph;
};


netdesc : 'h' NAME (nodesize (bgcolor)?)? '\n';

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
: 't' xpos=afloat ypos=afloat name=NAME 
{
   INode node = null;
   try {
            node = graph.createNode("transition");
            node.getAttribute("label").setValue(name.getText());
            // add for later reference by name
            nodes.put(name.getText(), node);  
            
            // handle pos
            node.getGraphicInfo().setLocation(new Point(xpos,ypos));
  
  }catch (ModelException e) {                
            e.printStackTrace();
  }
  $trdesc::idTrans = node;
}
(namePos=anchor 
  { node.getAttribute("label").getGraphicInfo().setLocation(namePos.translate($trdesc::idTrans.getGraphicInfo().getLocation())) ; }
)?
 eft lft anchor
( label=NAME anchor 
{
  IAttribute  a = node.getAttribute("label");
  a.setValue($label.getText());
}
)?
 '\n'
;

eft : ('-'
{
  // TODO : complain if open interval
}
)? ft=ENTIER
{
 $trdesc::idTrans.getAttribute("earliestFiringTime").setValue($ft.getText());
};
lft : ('-'
{
  // TODO : complain if open interval
}
)? (ft=ENTIER
{
  $trdesc::idTrans.getAttribute("latestFiringTime").setValue($ft.getText());
}
|'w'
{
  $trdesc::idTrans.getAttribute("latestFiringTime").setValue("inf");
}
)
;

anchor returns [Point pos]: 
'n'  { pos = new Point(0,-30); } 
| 'nw' { pos = new Point(30,-30); } 
| 'w' { pos = new Point(30,0); } 
| 'sw' { pos = new Point(30,30); } 
| 's' { pos = new Point(0,30); } 
| 'se' { pos = new Point(-30,30); } 
| 'e' { pos = new Point(-30,0); } 
| 'ne' { pos = new Point(-30,-30); } 
| 'c' { pos = new Point(0,0); } 
;


pldesc 
scope { INode idPlace; } 
: 'p' xpos=afloat ypos=afloat name=NAME 
{
   INode node = null;
   try {
            node = graph.createNode("place");
            node.getAttribute("name").setValue(name.getText());
            // add for later reference by name
            nodes.put(name.getText(), node);  
            
            // handle pos
            node.getGraphicInfo().setLocation(new Point(xpos,ypos));
  
  }catch (ModelException e) {                
            e.printStackTrace();
  }
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
 '\n'                
;

edgedesc : 'e' srcname=NAME 
 {
    this.source = nodes.get($srcname.getText());
 }
(ang=afloat rad=afloat)? 
target=NAME
 {
    this.destination = nodes.get($target.getText());
 }
(angt=afloat radt=afloat)? 
arc 
anchor '\n';



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

arc : type=('?'|'?-'|'!'|'!-'|) value=integer  
{
  IArc a = null;
  try {
  if (type==null) {
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
    
afloat returns [float value]:
  n=(FLOAT|ENTIER)
  { value = Float.parseFloat($n.getText()); }
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
FLOAT : ENTIER ('.' ENTIER)? ;
fragment STRING : '{'.*'}'
  ;
NAME  : STRING | (LETTER (LETTER | DIGIT)*)
  ;
  
