package fr.lip6.move.coloane.extensions.importExportGSPN.exportToGSPN;

//import static org.easymock.EasyMock.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.interfaces.model.IGraph;

public class ExportToImplTest extends TestCase{
	
		
	//private IGraph mock;
	private ExportToImpl export;
	
	private IGraph graph;
	private IGraph graph1, graph2, graph_tinf, graph_timm, graph_tserv, graph_tmd, graph_tmd2, graph_cc;
	
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		//mock = createMock(IGraph.class);
		
		export=new ExportToImpl();	
		
		/*
		// mock
		mock=new GraphModelFactory().createGraph("GSPN");
		mock.createNode("place", 1);
		*/
		
		
		// empty graph
		graph=new GraphModelFactory().createGraph("GSPN");
		
		
		// 1 place "P1", marking "toto", color classes "C=<x>"
		graph1=new GraphModelFactory().createGraph("GSPN");
		graph1.createNode("place", 1);
		graph1.getNode(1).getAttribute("tag").setValue("P1");
		graph1.getNode(1).getAttribute("marking").setValue("toto");
		graph1.getAttribute("color classes").setValue("C=<x>");
		//graph1.getAttribute("static subclasses").setValue("c1=<x1>");
		//graph1.getAttribute("static subclasses").setValue("c2=<x2>");
		
		
		// 3 places P1, P2, P3
		graph2=new GraphModelFactory().createGraph("GSPN");
		graph2.createNode("place", 1).getAttribute("tag").setValue("P1");
		graph2.createNode("place", 2).getAttribute("tag").setValue("P2");
		graph2.createNode("place", 3).getAttribute("tag").setValue("P3");
		
		// 1 transition (Infinite) "T1"
		graph_tinf=new GraphModelFactory().createGraph("GSPN");
		graph_tinf.createNode("transition (Infinite)", 1);
		graph_tinf.getNode(1).getAttribute("tag").setValue("T1");
		
		// 1 immediate transition
		graph_timm=new GraphModelFactory().createGraph("GSPN");
		graph_timm.createNode("immediate transition");
		
		// 1 transition (Server)
		graph_tserv=new GraphModelFactory().createGraph("GSPN");
		graph_tserv.createNode("transition (Server)");
		
		// 1 transition (Marking Dependent) without and with value of "definition" attribute
		graph_tmd=new GraphModelFactory().createGraph("GSPN");
		graph_tmd.createNode("transition (Marking Dependent)");
		graph_tmd2=new GraphModelFactory().createGraph("GSPN");
		graph_tmd2.createNode("transition (Marking Dependent)").getAttribute("definition").setValue("message");
		
		// petri nets
		graph_cc=new GraphModelFactory().createGraph("GSPN");
		
		}
	
	
	
	/*
	@Test
	public void testExportToImpl() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testExport() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testTranslateGraph() {
		fail("Not yet implemented");
	}
*/
	
	@Test
	public void testDefinitionMD() {
		Collection<String> resultat_graph=export.places(graph, null);
		//replay(graph);
		Collection<String> resultat_graph_tmd=export.places(graph_tmd, null);
		assertNotNull(resultat_graph);
		assertNotNull(resultat_graph_tmd);
		assertTrue(resultat_graph.size()==0);
		assertTrue(resultat_graph_tmd.size()==2);
		//verify(graph);
		//assertEquals()
	}
	

	@Test
	public void testHeaderDef() {
		Collection<String> toResult = new ArrayList<String>();
		toResult.add("|256");
		toResult.add("%");
		toResult.add("|");
		Collection<String> resultat=export.headerDef();
		assertTrue(resultat.size()==3);
		assertEquals(toResult, resultat);
	}
	
	
	@Test
	public void testMarkingOfPlaces() {
		Collection<String> toResult = new ArrayList<String>();
		toResult.add("(m1 m 0 0 (@m");
		toResult.add("toto");
		toResult.add("))");
		Collection<String> resultat_graph=export.markingOfPlaces(graph, null);
		Collection<String> resultat_graph1=export.markingOfPlaces(graph1, null);
		assertNotNull(resultat_graph);
		assertNotNull(resultat_graph1);
		assertTrue(resultat_graph.size()==0);
		assertTrue(resultat_graph1.size()==3);
		assertEquals(toResult, resultat_graph1);
	}

	@Test
	public void testColorDef() {
		Collection<String> toResult = new ArrayList<String>();
		toResult.add("(C c 0 0 (@c");
		toResult.add("<x>");
		toResult.add("))");
		Collection<String> resultat_graph=export.colorDef(graph, null);
		Collection<String> resultat_graph1=export.colorDef(graph1, null);
		assertNotNull(resultat_graph);
		assertNotNull(resultat_graph1);
		assertTrue(resultat_graph.size()==0);
		assertTrue(resultat_graph1.size()==3);
		assertEquals(toResult, resultat_graph1);
	}

	
	@Test
	public void testHeaderNet() {
		Collection<String> toResult = new ArrayList<String>();
		toResult.add("|0|");
		toResult.add("|");
		Collection<String> resultat=export.headerNet();
		assertTrue(resultat.size()==2);
		assertEquals(toResult, resultat);
	}
	
	

	@Test
	public void testNoobjs() {
		Collection<String> resultat_graph=export.noobjs(graph, null);
		Collection<String> resultat_graph1=export.noobjs(graph1, null);
		Collection<String> resultat_graph_tinf=export.noobjs(graph_tinf, null);
		assertNotNull(resultat_graph);
		assertNotNull(resultat_graph1);
		assertNotNull(resultat_graph_tinf);
		assertTrue(resultat_graph.size()==1);
		assertEquals("f   0   0   0   0   0   0   0", resultat_graph);
		assertTrue(resultat_graph1.size()==1);
		assertEquals("f   0   1   0   0   0   0   0", resultat_graph1);
		assertTrue(resultat_graph_tinf.size()==1);
		assertEquals("f   0   0   0   1   0   0   0", resultat_graph_tinf);
	}


	@Test
	public void testPlaces() {
		/*
		replay(mock);
		assertNotNull(export.places(mock, null));
		assertTrue(export.places(mock, null).size()==1);
		assertEquals("P1    0 0 0 0 0 0", export.places(mock, null));
		verify(mock);
		*/
		
		
		 Collection<String> resultat_graph=export.places(graph, null);
		//replay(graph);
		Collection<String> resultat_graph1=export.places(graph1, null);
		Collection<String> resultat_graph2=export.places(graph2, null);
		assertNotNull(resultat_graph);
		assertNotNull(resultat_graph1);
		assertNotNull(resultat_graph2);
		assertTrue(resultat_graph.size()==0);
		//verify(graph);
		assertTrue(resultat_graph1.size()==1);
		assertEquals("P1    0 0 0 0 0 0", resultat_graph1);
		assertTrue(resultat_graph2.size()==3);
		 
	}
		
	
	@Test
	public void testGroups() {
		Collection<String> resultat_graph=export.groups(graph, null);
		Collection<String> resultat_graph_tmd=export.groups(graph_tmd, null);
		assertNotNull(resultat_graph);
		assertNotNull(resultat_graph_tmd);
		assertTrue(resultat_graph.size()==0);
		assertTrue(resultat_graph_tmd.size()==1);
		assertEquals("G1 0 0 1", resultat_graph_tmd);
		
	}

	@Test
	public void testTransitionsAndArcs() {
		Collection<String> toResult = new ArrayList<String>();
		toResult.add("T1  1  0  0  0 0 0 0 0 0 0 0 0");
		toResult.add("0");
		toResult.add("0");
		Collection<String> resultat_graph=export.transitionsAndArcs(graph, null);
		Collection<String> resultat_graph1=export.transitionsAndArcs(graph1, null);
		Collection<String> resultat_graph_tinf=export.transitionsAndArcs(graph_tinf, null);
		assertNotNull(resultat_graph);
		assertNotNull(resultat_graph1);
		assertNotNull(resultat_graph_tinf);
		assertTrue(resultat_graph.size()==0);
		assertTrue(resultat_graph1.size()==0);
		assertTrue(resultat_graph_tinf.size()==3);
		assertEquals(toResult, resultat_graph_tinf);
	}

	@Test
	public void testGetNodeXCoordinate() {
		double resultat_graph1=export.getNodeXCoordinate(graph1.getNode(1));
		assertNotNull(resultat_graph1);
		assertEquals(0, resultat_graph1);
	}

	@Test
	public void testGetNodeYCoordinate() {
		double resultat_graph1=export.getNodeYCoordinate(graph1.getNode(1));
		assertNotNull(resultat_graph1);
		assertEquals(0, resultat_graph1);
	}

	
	@Test
	public void testGetAttributeXCoordinate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAttributeYCoordinate() {
		fail("Not yet implemented");
	}


}

