package fr.lip6.move.coloane.extension.exportToDOT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.motor.formalism.Messages;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.interfaces.model.INode;

public class DotTranslator {

	public final Vector<String> translateModel(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		
		toReturn.add("digraph G {");
		toReturn.add("compound=false;");
		
		Vector<String> places = getPlaces(model);
		toReturn.addAll(places);
		
		Vector<String> transitions = getTransitions(model);
		toReturn.addAll(transitions);
		
		Vector<String> imTransitions = getImTransitions(model);
		toReturn.addAll(imTransitions);
		
		Vector<String> queues = getQueues(model);
		toReturn.addAll(queues);
		
		Vector<String> arcs = getArcs(model);
		toReturn.addAll(arcs);
		
		Vector<String> inhibitorArcs = getInhibitorArcs(model);
		toReturn.addAll(inhibitorArcs);
				
		toReturn.add("}");
		return toReturn;
	}
	
	
	private Vector<String> getPlaces(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("place")){
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				toReturn.add("color=white");
				for ( IAttribute att : noeud.getListOfAttr()){
					if (! att.getValue().equals("") ){
						String etiquette = "\""+att.getName()+noeud.getId()+"="+att.getValue()+"\"";
						toReturn.add(etiquette+" [shape=plaintext ] ;");
						toReturn.add( etiquette+" -> "+noeud.getId()+" [color=gold];");
					}
				}
				toReturn.add(noeud.getId()+" [shape=circle, fontcolor=white, fixedsize=true];");
				toReturn.add("}");
			}
		}
		return toReturn;
	}
	
	
	
	private Vector<String> getTransitions(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("transition")){
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				toReturn.add("color=white");
				for ( IAttribute att : noeud.getListOfAttr()){
					if (! att.getValue().equals("") ){
						String etiquette = "\""+att.getName()+noeud.getId()+"="+att.getValue()+"\"";
						toReturn.add(etiquette+" [shape=plaintext ] ;");
						toReturn.add( etiquette+" -> "+noeud.getId()+" [color=gold];");
					}
				}
				toReturn.add(noeud.getId()+" [shape=box, fontcolor=white, fixedsize=true];");
				toReturn.add("}");
			}
		}
		return toReturn;		
	}

	private Vector<String> getImTransitions(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("immediate transition")){
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				toReturn.add("color=white");
				for ( IAttribute att : noeud.getListOfAttr()){
					if (! att.getValue().equals("") ){
						String etiquette = "\""+att.getName()+noeud.getId()+"="+att.getValue()+"\"";
						toReturn.add(etiquette+" [shape=plaintext ] ;");
						toReturn.add( etiquette+" -> "+noeud.getId()+" [color=gold];");
					}
				}
				toReturn.add(noeud.getId()+" [shape=box, style=filled, color=black, fixedsize=true];");
				toReturn.add("}");
			}
		}
		return toReturn;		
	}
	
	private Vector<String> getQueues(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("queue")){
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				toReturn.add("color=white");
				for ( IAttribute att : noeud.getListOfAttr()){
					if (! att.getValue().equals("") ){
						String etiquette = "\""+att.getName()+noeud.getId()+"="+att.getValue()+"\"";
						toReturn.add(etiquette+" [shape=plaintext ] ;");
						toReturn.add( etiquette+" -> "+noeud.getId()+" [color=gold];");
					}
				}
				toReturn.add(noeud.getId()+" [shape=box, fontcolor=white, style=rounded, fixedsize=true];");
				toReturn.add("}");
			}
		}
		return toReturn;		
	}
	
	private Vector<String> getArcs(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<IArc> listeArcs = model.getListOfArcs();
		
		boolean oneArc = false;
		String startingNode = "";
		String endingNode = "";
		
		
		for ( IArc arc : listeArcs){
			if (arc.getArcType().equals("arc")){
				oneArc = true;
				for ( IAttribute att : arc.getStartingNode().getListOfAttr()){
					if (att.getName().equals("name")){
						startingNode = ""+arc.getStartingNode().getId();
					}
				}
				for ( IAttribute att : arc.getEndingNode().getListOfAttr()){
					if (att.getName().equals("name")){
						endingNode = ""+arc.getEndingNode().getId();
					}
				}
				toReturn.add(startingNode+" -> "+endingNode+" ;");
			}
			
		}
		
		if ( oneArc == false){
			return new Vector<String>();
		}
		
		return toReturn;		
	}
	
	private Vector<String> getInhibitorArcs(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<IArc> listeArcs = model.getListOfArcs();
		
		boolean oneArc = false;
		String startingNode = "";
		String endingNode = "";
		
		for ( IArc arc : listeArcs){
			if (arc.getArcType().equals("inhibitor arc")){
				oneArc = true;
				for ( IAttribute att : arc.getStartingNode().getListOfAttr()){
					if (att.getName().equals("name")){
						startingNode = ""+arc.getStartingNode().getId();
					}
				}
				for ( IAttribute att : arc.getEndingNode().getListOfAttr()){
					if (att.getName().equals("name")){
						endingNode = ""+arc.getEndingNode().getId();
					}
				}
				toReturn.add(startingNode+" -> "+endingNode+" [arrowtail=none, arrowhead=odot] ;");
			}
			
		}
		
		if ( oneArc == false){
			return new Vector<String>();
		}
		
		return toReturn;		
	}

	
	
	
	public final void saveModel(Vector<String> m, String fileName)throws ColoaneException {
		// Creation du fichier
		String ext = "dot";
		FileOutputStream wr;
		try {
			wr = new FileOutputStream(new File(fileName + "." + ext)); //$NON-NLS-1$
		} catch (FileNotFoundException e1) {
			throw new ColoaneException(Messages.FormalismManager_7 + fileName + "." + ext); //$NON-NLS-2$
		}
		BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(wr));

		// Traduction du modele entier
		try {
			Vector<String> dot = m;
			for (String line : dot) {
				buff.write(line);
				buff.newLine();
			}
		} catch (Exception e) {
			throw new ColoaneException(Messages.FormalismManager_9);
		}

		try {
			buff.flush();
			wr.flush();
			buff.close();
			wr.close();
		} catch (IOException e) {
			throw new ColoaneException(Messages.FormalismManager_10);
		}
	}
}