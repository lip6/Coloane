package fr.lip6.move.coloane.model;

import junit.framework.TestCase;
import java.lang.reflect.Array;
import fr.lip6.move.coloane.interfaces.model.*;

public class TestModel_PI extends TestCase {

	
	public  void affiche_translate(String[] t) {
		for (int i = 0; i < Array.getLength(t); i++) {
			System.out.println(t[i]);
		}
	}
	public void test(){
		IModel model = new Model();
		
		INode node1 = new Node("place");
		INode node2 = new Node("transition");
		IArc arc= new Arc("arc");
		
		
		try{
		model.addNode(node1);
		model.addNode(node2);
		
		arc.setStartingNode(node1);
		arc.setEndingNode(node2);
		
		try{
		System.out.println("ok");
		arc.addPI(10,15);
		}catch(Exception e){
			
			e.printStackTrace();
		}
	
		
		model.addArc(arc);
		assertTrue(model.getAnArc(arc.getId())!=null);
		
		affiche_translate(model.translate());
		
		}catch(Exception e){
			e.printStackTrace();
			assertFalse(model.getAnArc(arc.getId())!=null);
		}
		
	}
	
}
