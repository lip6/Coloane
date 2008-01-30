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
	//*************************//
	//     CONSTANTE DOT       //
	// ************************//
	
	// fontname "transportable":
	
	private static final String TIMES ="Times-Roman"; 
	private static final String TIMES_BOLD ="Times-Bold";
	private static final String TIMES_ITALIC ="Times-Italic";
	
	private static final String HELVETICA="Helvetica";
	private static final String HELVETICA_BOLD="Helvetica-Bold";
	private static final String HELVETICA_ITALIC="Helvetica-Italic";

	private static final String COURIER = "Courier";
	private static final String COURIER_BOLD = "Courier-Bold";
	private static final String COURIER_ITALIC = "Courier-Italic";
	
	private static final String SYMBOL = "Symbol";
	private static final String SYMBOL_BOLD = "Symbol-Bold";
	private static final String SYMBOL_ITALIC = "Symbol-Italic";
	
	// color:
	private static final String BLACK = "black";
	private static final String WHITE = "white";
	private static final String RED = "red";
	private static final String ORANGE = "orange";
	private static final String YELLOW = "yellow";
	private static final String GREEN = "green";
	private static final String CYAN = "cyan";
	private static final String BLUE = "blue";
	private static final String GOLD = "gold";
	
	// Style edge:
	private static final String STYLE_FILLED = "filled";
	private static final String STYLE_DOTTED = "dotted";
	private static final String STYLE_BOLD = "bold";
	
	
	//*************************//
	//     PARAMETRES          //
	// ************************//
	private String RATIO ="0.5";
	
	private String FONTNAME_NAME_NODE = COURIER_ITALIC;
	private String FONTSIZE_NAME_NODE = "56";
	private String FONTCOLOR_NAME_NODE = GREEN;
	
	private String FONTNAME_ATTRIBUTS = SYMBOL_BOLD;
	private String FONTSIZE_ATTRIBUTS = "20";
	private String FONTCOLOR_ATTRIBUTE = RED;
	private String STYLE_EDGE_ATTRIBUTE = STYLE_DOTTED;
	private String COLOR_EDGE_ATTRIBUTE = BLUE;
	
	
	
	public final Vector<String> translateModel(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		
		// contenu du debut du fichier dot
		toReturn.add("digraph G {");
		toReturn.add("ratio="+RATIO+";");
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
		
		// contenu de la fin du fichier dot
		toReturn.add("}");
		return toReturn;
	}
	
	
	private Vector<String> getPlaces(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("place")){
				// Creation d'une zone pour le graph contenant la place
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				// Contoure de la zone de couleur blanche (i.e. transparant ;-)) 
				toReturn.add("color=white;");
				
				// Parcour tous les attributs de la place
				for ( IAttribute att : noeud.getListOfAttr()){
					// Si c'est le nom, on le met on valeur
					if (att.getName().equals("name")){
						if (! att.getValue().equals("") ){
							// Creation d'un noeud unique pour definir le nom de la place
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [label=\""+att.getValue()+"\", fontsize="+FONTSIZE_NAME_NODE+", fontname=\""+FONTNAME_NAME_NODE+"\", fontcolor="+FONTCOLOR_NAME_NODE+", shape=plaintext] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
					//sinon on affiche les atributs avec leur valeur
					else{
						// Si les valeurs de l'attrinuts est non null
						if (! att.getValue().equals("") ){
							// Creation d'un noeud unique pour definir l'attibut de la place
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [ label="+"\""+att.getName()+"="+att.getValue()+"\""+", fontname=\""+FONTNAME_ATTRIBUTS+"\", fontsize="+FONTSIZE_ATTRIBUTS+", "+"fontcolor="+FONTCOLOR_ATTRIBUTE+", shape=plaintext ] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
				}
				// Creation du cercle repersentant la place
				toReturn.add(noeud.getId()+" [shape=circle, fontcolor=white, fixedsize=true];");
				toReturn.add("}");
			}
		}
		return toReturn;
	}
	
	

	// Commentaire identique getPlaces
	private Vector<String> getTransitions(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("transition")){
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				toReturn.add("color=white;");
				for ( IAttribute att : noeud.getListOfAttr()){
					if (att.getName().equals("name")){
						if (! att.getValue().equals("") ){
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [label=\""+att.getValue()+"\", fontsize="+FONTSIZE_NAME_NODE+", fontname=\""+FONTNAME_NAME_NODE+"\", fontcolor="+FONTCOLOR_NAME_NODE+", shape=plaintext] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
					else{
						if (! att.getValue().equals("") ){
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [ label="+"\""+att.getName()+"="+att.getValue()+"\""+", fontname=\""+FONTNAME_ATTRIBUTS+"\", fontsize="+FONTSIZE_ATTRIBUTS+", "+"fontcolor="+FONTCOLOR_ATTRIBUTE+", shape=plaintext ] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
				}
				toReturn.add(noeud.getId()+" [shape=box, fontcolor=white, fixedsize=true];");
				toReturn.add("}");
			}
		}
		return toReturn;		
	}

	// Commentaire identique getPlaces
	private Vector<String> getImTransitions(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("immediate transition")){
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				toReturn.add("color=white;");
				for ( IAttribute att : noeud.getListOfAttr()){
					if (att.getName().equals("name")){
						if (! att.getValue().equals("") ){
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [label=\""+att.getValue()+"\", fontsize="+FONTSIZE_NAME_NODE+", fontname=\""+FONTNAME_NAME_NODE+"\", fontcolor="+FONTCOLOR_NAME_NODE+", shape=plaintext] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
					else{
						if (! att.getValue().equals("") ){
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [ label="+"\""+att.getName()+"="+att.getValue()+"\""+", fontname=\""+FONTNAME_ATTRIBUTS+"\", fontsize="+FONTSIZE_ATTRIBUTS+", "+"fontcolor="+FONTCOLOR_ATTRIBUTE+", shape=plaintext ] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
				}
				toReturn.add(noeud.getId()+" [shape=box, style=filled, color=black, fixedsize=true];");
				toReturn.add("}");
			}
		}
		return toReturn;		
	}
	
	// Commentaire identique getPlaces
	private Vector<String> getQueues(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("queue")){
				toReturn.add("subgraph cluster"+noeud.getId()+" {");
				toReturn.add("color=white;");
				for ( IAttribute att : noeud.getListOfAttr()){
					if (att.getName().equals("name")){
						if (! att.getValue().equals("") ){
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [label=\""+att.getValue()+"\", fontsize="+FONTSIZE_NAME_NODE+", fontname=\""+FONTNAME_NAME_NODE+"\", fontcolor="+FONTCOLOR_NAME_NODE+", shape=plaintext] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
					else{
						if (! att.getValue().equals("") ){
							String etiquette = ""+att.getName()+noeud.getId();
							toReturn.add(etiquette+" [ label="+"\""+att.getName()+"="+att.getValue()+"\""+", fontname=\""+FONTNAME_ATTRIBUTS+"\", fontsize="+FONTSIZE_ATTRIBUTS+", "+"fontcolor="+FONTCOLOR_ATTRIBUTE+", shape=plaintext ] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
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
		
		// Booleen permettant de savoir s'il y a au moins un arc
		boolean oneArc = false;
		// Noeud de depart
		String startingNode = "";
		// Noeud d'arriver
		String endingNode = "";
		
		
		for ( IArc arc : listeArcs){
			if (arc.getArcType().equals("arc")){
				oneArc = true;
				// Recupere le noeud de depart
				for ( IAttribute att : arc.getStartingNode().getListOfAttr()){
					if (att.getName().equals("name")){
						startingNode = ""+arc.getStartingNode().getId();
					}
				}
				// Recupere le noeud d'arriver
				for ( IAttribute att : arc.getEndingNode().getListOfAttr()){
					if (att.getName().equals("name")){
						endingNode = ""+arc.getEndingNode().getId();
					}
				}
				// Recupere la valuation du noeud
				String valuation ="";
				for ( IAttribute att : arc.getListOfAttr()){
					if (att.getName().equals("valuation")){
						valuation = att.getValue();
					}
				}
				// Creation d'un arc entre deux noeuds
				toReturn.add(startingNode+" -> "+endingNode+" [label=\""+valuation+"\"] ;");
			}
			
		}
		
		if ( oneArc == false){
			return new Vector<String>();
		}
		
		return toReturn;		
	}
	
	// Commentaire identique getArcs
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
				String valuation ="";
				for ( IAttribute att : arc.getListOfAttr()){
					if (att.getName().equals("valuation")){
						valuation = att.getValue();
					}
				}
				toReturn.add(startingNode+" -> "+endingNode+" [label=\""+valuation+"\""+" arrowtail=none, arrowhead=odot] ;");
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

		// Ecriture du modele entier
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