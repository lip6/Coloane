package fr.lip6.move.coloane.model;

import junit.framework.TestCase;
import java.io.*;
import java.lang.reflect.Array;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
public class TestModel_translate extends TestCase {
	
	Model model= new Model();
	int id_node, id_node2, id_arc;
	String [] translate; //resultat de la traduction coloane->cami
	
	/*
	public void affichage(){
		String res=new String("Model:\n");
		if (model.getListOfAttrSize()>0){
			for(int i=0;i<model.getListOfAttrSize();i++){
			res+=" "+model.getNthAttr(i).getName()+":"+model.getNthAttr(i).getValue()+" refId:"+model.getNthAttr(i).getRefId()+"\n";
			}
		}
		if (model.getListOfNodeSize()>0){
			for(int i=0;i<model.getListOfNodeSize();i++){
				INode node=model.getNthNode(i);
				res+="Node:"+node.getId()+"\n";
				if (node.getListOfAttrSize()>0){
					for(int j=0;j<node.getListOfAttrSize();j++){
					res+=" "+node.getNthAttr(j).getName()+":"+node.getNthAttr(j).getValue()+" refId:"+node.getNthAttr(j).getRefId()+"\n";
					}
				}
			}
		}
		if (model.getListOfArcSize()>0){
			for(int i=0;i<model.getListOfArcSize();i++){
				IArc arc=model.getNthArc(i);
				res+="Arc:"+arc.getId()+"\n";
				res+=" start:"+arc.getStartingNode().getId()+" end:"+arc.getEndingNode().getId()+"\n";
				if (arc.getListOfAttrSize()>0){
					for(int j=0;j<arc.getListOfAttrSize();j++){
					res+=" "+arc.getNthAttr(j).getName()+":"+arc.getNthAttr(j).getValue()+" refId:"+arc.getNthAttr(j).getRefId()+"\n";
					}
				}
			}
		}
		System.out.println(res);
	}
	*/
	
	public String lireFichier(String fichier){
		BufferedReader lecteurAvecBuffer = null;
	    String ligne,contenu;
	    contenu=new String("");
	
	    try
	      {
	    	File sourceF = new File(fichier);
	        lecteurAvecBuffer = new BufferedReader
	          (new FileReader(sourceF));
	      }
	    catch(FileNotFoundException exc)
	      {
	        System.out.println("Erreur d'ouverture");
	      }
	    try
	      {
	    	while ((ligne = lecteurAvecBuffer.readLine()) != null)
	    	contenu=contenu+ligne;
	    	lecteurAvecBuffer.close();
	    	
	      }
	    catch(IOException e)
	      {
	    	System.out.println("Erreur de lecture:"+e.toString());
	      }
	    return contenu;
    }
	
	
	public void affiche_translate(String [] t){
		for(int i=0;i<Array.getLength(t);i++){
			System.out.println(t[i]);
		}
	}
	
	
	public void testTranslate(){
		
		/*1er modele*/
		System.out.println("Modele 1\n");
		
		//Ajout des 4 attributs du model
		String [] attributs ={"declaration","author(s)","version","project"}; 
		for(int i=0;i<4;i++){
			String [] value=new String[1];
			value[0]="aM"+i;
			IAttribute attr=new Attribute(attributs[i], value, 1);
			model.addAttribute(attr);
			
		}
		
		
		//Ajout des 6 noeuds
		int ascii=65;
		for(int i=0;i<6;i++){
			INode node;
			if(i%2==0)
				node=new Node("place");
			else
				node=new Node("transition");
				
			//Ajout d'1 attribut au noeud
			String [] value=new String[1];
			value[0]=""+(char)ascii;
			IAttribute attr=new Attribute("name", value, 0 );
			node.addAttribute(attr);
			ascii++;
			
			
			model.addNode(node);
			
		}
		
		//Ajout des 6 arcs
		for(int i=0;i<6;i++){
			IArc arc = new Arc("Arc"+(model.getMaxId()+1));
		
			String [] value=new String[1];
				value[0]="???";
				IAttribute attr=new Attribute("name", value, 0 );
				arc.addAttribute(attr);
		
		
			INode start=model.getNthNode(i);
			INode end=model.getNthNode((i+1)%6);
			arc.setStartingNode(start);
			arc.setEndingNode(end);
			
			model.addArc(arc);
			
		}
		
		//Traduction
		translate=model.translate();
		affiche_translate(translate);		
		
		
		
		//2ème modele
		System.out.println("Modele 2\n");
		
		//Suppression de l'arc 7
		IArc arc_to_remove = model.getAnArc(7);
		model.removeArc(arc_to_remove);
		
		//Suppression de l'arc 9
		arc_to_remove = model.getAnArc(9);
		model.removeArc(arc_to_remove);
		
		//Ajout de l'arc entre le 1er noeud et le 3eme noeud
		IArc arc_to_add = new Arc("Arc"+(model.getMaxId()+1));
		arc_to_add.setStartingNode(model.getANode(2));
		arc_to_add.setEndingNode(model.getANode(4));
		
		model.addArc(arc_to_add);
		
//		Ajout de l'arc entre le 2eme noeud et le 4eme noeud
		arc_to_add = new Arc("Arc"+(model.getMaxId()+1));
		arc_to_add.setStartingNode(model.getANode(3));
		arc_to_add.setEndingNode(model.getANode(5));
		
		model.addArc(arc_to_add);
		
		translate=model.translate();
		
		
		//3ème modele
		System.out.println("Modele 3\n");
		model.removeNode(model.getANode(3));
		model.removeNode(model.getANode(6));
		
		INode node = new Node("Node"+(model.getMaxId()+1));
		
		model.addNode(node);
		
		translate=model.translate();
		
		
		
		//4ème modele
		System.out.println("Modele 4\n");	
		String [] value=new String[1];
		value[0]="New";
		IAttribute attr=new Attribute("attr", value, 1 );
		
		model.addAttribute(attr);
		
		
		for(int i=0;i<model.getListOfNodeSize();i++){
			IAttribute attrs=new Attribute("attr", value, model.getNthNode(i).getId() );
			model.getNthNode(i).addAttribute(attrs);
		}
		
		for(int i=0;i<model.getListOfArcSize();i++){
			model.getNthArc(i).addAttribute(attr);
		}
		
		translate=model.translate();
	}
}