package fr.lip6.move.coloane.tools.stats;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.core.extensions.IColoaneAction;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

public class StatAction implements IColoaneAction {

	public List<IResult> run(IGraph model) {
		//System.out.println("Nombre de noeuds : "+model.getNodes().size());
		//System.out.println("Nombre d'arcs : "+model.getArcs().size());
	
		
		MyResultMenu rm1 = new MyResultMenu("Comptage de noeuds et d'arcs",null);
		MyResultMenu rm2 = new MyResultMenu("Nombres d'arcs",null);
		MyResultMenu rm3 = new MyResultMenu("Nombres de noeuds",null);
		
		MyResult resNodes = new MyResult(String.valueOf(model.getNodes().size()),1);
		MyResult resArcs = new MyResult(String.valueOf(model.getArcs().size()),1);
		
		rm2.addMyResult(resNodes);
		rm3.addMyResult(resArcs);
		
		rm1.addMyResultMenu(rm2);
		rm1.addMyResultMenu(rm3);
		
		
		/*
		MyResultMenu rm1 = new MyResultMenu("menu 1",null);
		MyResultMenu rm2 = new MyResultMenu("menu 2",null);
		MyResultMenu rm3 = new MyResultMenu("menu 3",null);
		
		
		MyResult m1 = new MyResult("resultat 1",1);
		MyResult m2 = new MyResult("resultat 2",2);
		MyResult m3 = new MyResult("resultat 3",3);
		MyResult m4 = new MyResult("resultat 4",4);
		
		rm1.addMyResult(m1);
		rm1.addMyResult(m2);
		rm3.addMyResult(m3);
		rm3.addMyResult(m4);
		
		rm1.addMyResultMenu(rm2);
		rm2.addMyResultMenu(rm3);
		*/
		
		rm1.myToString();
		ArrayList<IResult> al = new ArrayList<IResult>();
		al.add(rm1);
		return al;
	}
}
