package fr.lip6.move.coloane.extensions.importExportGSPN.exportToGSPN;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;
import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class ExportToImplTest extends TestCase {

	private ExportToImpl export;
	private IGraph graph, graph1, graph2, graph_tinf, graph_timm, graph_tserv, graph_tmd;
	//private INode node;
	//private IAttribute attribute;
		
	protected void setUp() throws Exception {
		super.setUp();

		export=new ExportToImpl();
		graph=new GraphModelFactory().createGraph("GSPN");
		graph1=new GraphModelFactory().createGraph("GSPN");
		graph2=new GraphModelFactory().createGraph("GSPN");
		graph_tinf=new GraphModelFactory().createGraph("GSPN");
		graph_timm=new GraphModelFactory().createGraph("GSPN");
		graph_tserv=new GraphModelFactory().createGraph("GSPN");
		graph_tmd=new GraphModelFactory().createGraph("GSPN");
		
		graph1.createNode("place");
		
		graph2.createNode("place", 1);
		graph2.createNode("place", 2);
		graph2.createNode("place", 3);
		
		graph_tinf.createNode("transition (Infinite)");
		graph_timm.createNode("immediate transition");
		graph_tserv.createNode("transition (Server)");
		graph_tmd.createNode("transition (Marking Dependent)");
	}
	
	public void testDefinitionMD() {
		fail("Not yet implemented");
	}

	public void testMarkingOfPlaces() {
		fail("Not yet implemented");
	}

	public void testColorDef() {
		fail("Not yet implemented");
	}

	public void testNoobjs() {
		fail("Not yet implemented");
	}

	public void testPlaces() {
		List<String> toResult=new ArrayList<String>();
		Collection<String> resultat=export.places(graph, null);
		assertTrue(resultat.size()==0);
		assertEquals(toResult, ExportToImpl.places(graph1, null));
		assertEquals(toResult, ExportToImpl.places(graph2, null));
	}

	public void testGroups() {
		fail("Not yet implemented");
	}

	public void testTransitionsAndArcs() {
		fail("Not yet implemented");
	}

	public void testGetNodeXCoordinate() {
		//assertEquals();
	}

	public void testGetNodeYCoordinate() {
		fail("Not yet implemented");
	}

	public void testGetAttributeXCoordinate() {
		fail("Not yet implemented");
	}

	public void testGetAttributeYCoordinate() {
		fail("Not yet implemented");
	}

}
