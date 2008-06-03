package fr.lip6.move.coloane.api.camiObject.tests;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.Arc;
import fr.lip6.move.coloane.api.camiObject.Attribute;
import fr.lip6.move.coloane.api.camiObject.Box;
import fr.lip6.move.coloane.api.camiObject.Model;
import fr.lip6.move.coloane.api.camiObject.Node;
import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.IAttribute;
import fr.lip6.move.coloane.api.interfaces.IBox;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.INode;
import junit.framework.TestCase;

public class ModelTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testModel(){

		ArrayList<IArc> arcs = new ArrayList<IArc>();
		ArrayList<IBox> boxes = new ArrayList<IBox>();
		ArrayList<INode> nodes = new ArrayList<INode>();

		// creation dun arc
		 String arcType= "arc";

	     ArrayList<IAttribute> attribute = new ArrayList<IAttribute>();
	/*     attribute.add((IAttribute)(new Attribute()));
	     int endingNode = 2;
	     int startingNode = 8;
	     int idArc = 13;
          IArc arc = new Arc (arcType,idArc,endingNode,startingNode,attribute);
          arcs.add(arc);


        //creation d'un boxx
        ArrayList<IAttribute> attributeb = new ArrayList<IAttribute>();
  	     attributeb.add((IAttribute)new Attribute());
  	    int boxID = 4;
  	    int boxPage = 5;
  	    String boxType = "evaluation";

      	IBox box = new Box(attributeb,boxID,boxPage,boxType);
        boxes.add(box);

  	   //creation dun noeud
  	   ArrayList<IAttribute> attributen = new ArrayList<IAttribute>();
	   attributen.add((IAttribute)new Attribute());
	   int nodeID = 4;
	   String nodeType = "evaluation";

	   INode node = new Node( attributen,nodeID, nodeType);
	   nodes.add(node);

	   IModel model = new Model (arcs, boxes, nodes);

	   this.assertEquals(arcs, model.getArcs());
	   this.assertEquals(boxes, model.getBoxes());
	   this.assertEquals(nodes, model.getNodes());*/
	}
}
