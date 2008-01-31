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
	//**************************//
	//     CONSTANTES DOT       //
	// *************************//
	
	// fontname :
	//  /!\ il semblerait que seul TIMES, est compatibles avec tous les formats d'images
	
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
	
	
	//****************************************************//
	//     PARAMETRES POUR LE FICHIER DOT A CREER         //
	// ***************************************************//
	private String RATIO ="0.5";
	
	private String FONTNAME_NAME_NODE = TIMES;
	private String FONTSIZE_NAME_NODE = "42";
	private String FONTCOLOR_NAME_NODE = BLACK;
	
	private String FONTNAME_ATTRIBUTS = TIMES;
	private String FONTSIZE_ATTRIBUTS = "14";
	private String FONTCOLOR_ATTRIBUTE = BLACK;
	private String STYLE_EDGE_ATTRIBUTE = STYLE_FILLED;
	private String COLOR_EDGE_ATTRIBUTE = GOLD;
	
	
	/**
	 * Creer un tableau de string correspondant au contenu du fichier dot, pour
	 * le model passer en parametres.
	 * @param model Model du fichier à exporter. 
	 * @return Le contenu du fichier dot correspondant au model.
	 */
	public final Vector<String> translateModel(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		
		// contenu du debut du fichier dot
		toReturn.add("digraph G {");
		toReturn.add("ratio="+RATIO+";");
		toReturn.add("compound=false;");
		
		
		// Recupere les places du model...
		Vector<String> places = getPlaces(model);
		// ...et l'ajoute au fichier
		toReturn.addAll(places);
		
		// Recupere les tansitions du model...
		Vector<String> transitions = getTransitions(model);
		// ...et l'ajoute au fichier
		toReturn.addAll(transitions);
		
		// Recupere les transitions immediats du model...
		Vector<String> imTransitions = getImTransitions(model);
		// ...et l'ajoute au contenu du fichier
		toReturn.addAll(imTransitions);
		
		// Recupere les queues du model...
		Vector<String> queues = getQueues(model);
		// ...et l'ajoute au fichier
		toReturn.addAll(queues);
		
		// Recupere les arcs du model...
		Vector<String> arcs = getArcs(model);
		// ...et l'ajoute au fichier
		toReturn.addAll(arcs);
		
		// Recupere les arcs inibitateur du model...
		Vector<String> inhibitorArcs = getInhibitorArcs(model);
		// ...et l'ajoute au fichier
		toReturn.addAll(inhibitorArcs);
		
		// contenu de la fin du fichier dot
		toReturn.add("}");
		return toReturn;
	}
	
	/**
	 * Creer un tableau de string correspondant a la declaration des places
	 * qui sera contenu dans le fichier dot.
	 * @param model Model où récuperer les places
	 * @return La partie correspondant a la declaration des places
	 */
	private Vector<String> getPlaces(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		// Parcours tous les noeuds du model
		for ( INode noeud : listeNoeuds){
			// Si c'est un place
			if ( noeud.getNodeType().equals("place")){				
				// Parcour tous les attributs de la place
				for ( IAttribute att : noeud.getListOfAttr()){
					// Si c'est le nom, on le met on valeur
					if (att.getName().equals("name")){
						// Si les valeurs de l'attrinuts est non null
						if (! att.getValue().equals("") ){
							// Creation d'un noeud unique pour definir le nom de la place
							String etiquette = ""+att.getName()+noeud.getId();
							// Affichage du nom
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
							// Affichage de l'attribut et de sa valeurs
							toReturn.add(etiquette+" [ label="+"\""+att.getName()+"="+att.getValue()+"\""+", fontname=\""+FONTNAME_ATTRIBUTS+"\", fontsize="+FONTSIZE_ATTRIBUTS+", "+"fontcolor="+FONTCOLOR_ATTRIBUTE+", shape=plaintext ] ;");
							toReturn.add( etiquette+" -> "+noeud.getId()+" [color="+COLOR_EDGE_ATTRIBUTE+", style="+STYLE_EDGE_ATTRIBUTE+"];");
						}
					}
				}
				// Creation du cercle repersentant la place
				toReturn.add(noeud.getId()+" [shape=circle, fontcolor=white, fixedsize=true];");
			}
		}
		return toReturn;
	}
	
	

	/**
	 * Creer un tableau de string correspondant a la declaration des transitions
	 * qui sera contenu dans le fichier dot.
	 * @param model Model où récuperer les transitions
	 * @return La partie correspondant a la declaration des transitions
	 */
	// Commentaire identique getPlaces
	private Vector<String> getTransitions(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("transition")){
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
				toReturn.add(noeud.getId()+" [shape=box, regular=true, fontcolor=white, fixedsize=true];");
			}
		}
		return toReturn;		
	}

	/**
	 * Creer un tableau de string correspondant a la declaration des transitions immediate
	 * qui sera contenu dans le fichier dot.
	 * @param model Model où récuperer les transitions immediate
	 * @return La partie correspondant a la declaration des transitions immediate
	 */
	// Commentaire identique getPlaces
	private Vector<String> getImTransitions(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("immediate transition")){
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
				toReturn.add(noeud.getId()+" [shape=box, regular=true, style=filled, color=black, fixedsize=true];");
			}
		}
		return toReturn;		
	}
	
	/**
	 * Creer un tableau de string correspondant a la declaration des queues
	 * qui sera contenu dans le fichier dot.
	 * @param model Model où récuperer les queues
	 * @return La partie correspondant a la declaration des queues
	 */
	// Commentaire identique getPlaces
	private Vector<String> getQueues(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<INode> listeNoeuds = model.getListOfNodes();
		
		for ( INode noeud : listeNoeuds){
			if ( noeud.getNodeType().equals("queue")){
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
			}
		}
		return toReturn;		
	}
	

	/**
	 * Creer un tableau de string correspondant a la declaration des arcs
	 * qui sera contenu dans le fichier dot.
	 * @param model Model où récuperer les arcs
	 * @return La partie correspondant a la declaration des arcs
	 */
	private Vector<String> getArcs(IModel model) {
		Vector<String> toReturn = new Vector<String>();
		Vector<IArc> listeArcs = model.getListOfArcs();
		
		// Booleen permettant de savoir s'il y a au moins un arc
		boolean oneArc = false;
		// Noeud de depart
		String startingNode = "";
		// Noeud d'arriver
		String endingNode = "";
		
		// Parcours tous les arcs
		for ( IArc arc : listeArcs){
			// Si c'est un arc "simple"
			if (arc.getArcType().equals("arc")){
				// Il existe un arc
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
		
		// Si pas d'arc...
		if ( oneArc == false){
			//...alors rien
			return new Vector<String>();
		}
		
		return toReturn;		
	}
	
	/**
	 * Creer un tableau de string correspondant a la declaration des arcs inibitateurs
	 * qui sera contenu dans le fichier dot.
	 * @param model Model où récuperer les arcs inibitateurs
	 * @return La partie correspondant a la declaration des arcs inibitateurs
	 */
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

	
	
	/**
	 * Enregistre le model a exporter dans un fichier
	 * @param model Model a exporter/sauvgarder
	 * @param fileName Fichier où exporter/sauvgarder le model
	 * @throws ColoaneException Exception a propager 
	 */
	public final void saveModel(Vector<String> model, String fileName)throws ColoaneException {
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
			Vector<String> dot = model;
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