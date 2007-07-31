package fr.lip6.move.coloane.model;

import junit.framework.TestCase;
import java.util.Vector;

import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;


/*Scenario de test:
 * Ajout d'un arc de facon correcte ou non aléatoirement
 * Tous les 8 tours, un ou plusieurs arcs sont ajoutés à la liste des arcs à retirer 
 * les arcs présents dans la liste sont alors supprimés
 * Tous les 15 tours 1 noeud choisi aléatoirement est supprimé.
 * Le nombre de tour effectué est spécifié par la variable "max_tour"
 * Le nombre d'arc à supprimer par est spécifié par la variable "nb_remove"
 * */

public class TestModel_ID extends TestCase {
	int max_tour=5000;
	int nb_remove=2;
	
	
	int tour=1;
	
	Model model= new Model();
	int id_node1, id_node2, id_arc;

	
	
	public void switchArcOK(IArc arc){
	
		int action_ok;
		
		action_ok=(int)(Math.random()*4);
		
		switch(action_ok){
			//Ajout de 2 noeuds reliés par un arc
			case 0:	{			
				INode node1=new Node("Node");	
				INode node2=new Node("Node");
				
				arc.setStartingNode(node1);
				arc.setEndingNode(node2);
				
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));	
				model.addNode(node1);
				id_node1 = node1.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_node1)));
				assertEquals(id_node1,model.getMaxId());
				
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));
				model.addNode(node2);
				id_node2 = node2.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_node2)));
				assertEquals(id_node2,model.getMaxId());
				
				assertEquals(node1, model.getANode(id_node1));
				assertEquals(node2, model.getANode(id_node2));
				
				
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));
				model.addArc(arc);
				id_arc=arc.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_arc)));
				assertEquals(id_arc,model.getMaxId());
				
				System.out.println("Ajout de l'arc "+arc.getId()+" et des noeuds "+id_node1+" et "+id_node2+"\n");
				break;
			}
			//Ajout d'un noeud source relié à un noeud déjà existant
			case 1:{
				if(model.getListOfNodeSize()==0){break;}
				
				INode node1=new Node("Node");
				
				int i=(int)(Math.random()*model.getListOfNodeSize());
			
				arc.setStartingNode(node1);
				arc.setEndingNode(model.getNthNode(i));
				
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));			
				model.addNode(node1);
				id_node1 = node1.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_node1)));
				assertEquals(id_node1,model.getMaxId());
				
				
				assertEquals(node1, model.getANode(id_node1));
				
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));			
				model.addArc(arc);
				id_arc=arc.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_arc)));
				assertEquals(id_arc,model.getMaxId());
				
				System.out.println("Ajout de l'arc "+arc.getId()+" et du noeud source "+id_node1+" relié au noeud "+id_node2+"\n");
				break;
			}
			
			//Ajout d'un noeud cible relié à un noeud déjà présent dans le modèle
			case 2:{
				
				if(model.getListOfNodeSize()==0){break;}
				
				INode node2=new Node("Node");
				
				int i=(int)(Math.random()*model.getListOfNodeSize());
			
				arc.setStartingNode(model.getNthNode(i));
				arc.setEndingNode(node2);
				
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));			
				model.addNode(node2);
				id_node2 = node2.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_node2)));
				assertEquals(id_node2,model.getMaxId());
				
				assertEquals(node2, model.getANode(id_node2));
				
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));				
				model.addArc(arc);
				id_arc=arc.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_arc)));
				assertEquals(id_arc,model.getMaxId());
				
				System.out.println("Ajout de l'arc "+arc.getId()+" et du noeud cible "+id_node2+" relié au noeud "+id_node2+"\n");
				break;
			}
			
			case 3:{
				
				if(model.getListOfNodeSize()==0){break;}
				
				int i=(int)(Math.random()*model.getListOfNodeSize());
				
				arc.setStartingNode(model.getNthNode(i));
				i=(int)(Math.random()*model.getListOfNodeSize());
				arc.setEndingNode(model.getNthNode(i));
										
				assertFalse(model.getListOfId().contains(Integer.valueOf(model.getMaxId()+1)));
				model.addArc(arc);
				id_arc=arc.getId();
				assertTrue(model.getListOfId().contains(Integer.valueOf(id_arc)));
				assertEquals(id_arc,model.getMaxId());
				
				System.out.println("Ajout de l'arc "+arc.getId()+" relié aux noeuds "+arc.getStartingNode().getId()+" et "+arc.getEndingNode().getId()+" du modele\n");
				break;
				
			}
		}
		
	}
	
	
	
	/*Cas où l'ajout de l'arc est impossible*/
	public void switchArcNull(IArc arc){
		
		int action_null;
		
		action_null=(int)(Math.random()*4);
		
		switch(action_null){
			//Ajout d'un arc dont les noeuds ne sont présent dans le modèle
			case 0:	{	
				INode node1=new Node("Node");	
				INode node2=new Node("Node");
				
				arc.setStartingNode(node1);
				arc.setEndingNode(node2);
								
				id_node1 = node1.getId();
				id_node2 = node2.getId();
					
				assertTrue(node1!=model.getANode(id_node1));
				assertTrue(node2!=model.getANode(id_node2));
								
				model.addArc(arc);
				break;
			}
			
			//Ajout d'un arc dont le noeud source n'est pas présent dans le modèle
			case 1:{
				if(model.getListOfNodeSize()==0){break;}
				
				INode node1=new Node("Node");
				
				int i=(int)(Math.random()*model.getListOfNodeSize());
			
				arc.setStartingNode(node1);
				arc.setEndingNode(model.getNthNode(i));
									
				id_node1 = node1.getId();
				assertTrue(model.getANode(id_node1)==null);
							
				model.addArc(arc);
				break;
			}
			//Ajout d'un arc dont le noeud cible n'est pas présent dans le modèle
			case 2:{
				if(model.getListOfNodeSize()==0){break;}
				
				INode node2=new Node("Node");
				
				int i=(int)(Math.random()*model.getListOfNodeSize());
			
				arc.setStartingNode(model.getNthNode(i));
				arc.setEndingNode(node2);
								
				id_node2 = node2.getId();
				assertTrue(model.getANode(id_node2)==null);
								
				model.addArc(arc);
				break;
			}
			
			case 3:{	
				model.addArc(arc);
				break;
			}
		}
	}
	
	
	
	public void testScenarioID(){
		
		int action_alea;
		
		while (tour<=max_tour){
			action_alea=(int)(Math.random()*2);
			IArc arc = new Arc("Arc");
			System.out.println("Tour:"+tour);
			
			if((tour%8)==0){
				int ind=0;
				int i=0;
			
			
				while(i<nb_remove){
					if(model.getListOfArcSize()==0){
						System.out.println("Aucun Arc a retirer\n");
						break;
						}
					
					ind=(int)(Math.random()*model.getListOfArcSize());
					arc=model.getNthArc(ind);
					if(!(arc==null)){
						
						id_arc=arc.getId();
						System.out.println("arc_id to remove="+id_arc+"\n");
						INode node1=arc.getStartingNode();
						INode node2=arc.getEndingNode();
						id_node1 = node1.getId();
						id_node2 = node2.getId();
						
						assertTrue(model.getListOfId().contains(Integer.valueOf(id_arc)));
						model.removeArc(arc);
						
						/*arc plus présent dans la liste des arcs du modèle*/
						assertTrue(model.getAnArc(id_arc)==null);
							
						/*arc plus présent dans la liste des noeuds de sa cible et de sa source*/
						assertFalse(node1.getListOfOutputArc().contains(arc));		
						assertFalse(node2.getListOfInputArc().contains(arc));	
						
						/*arc plus présent dans la liste des identifiants*/
						assertFalse(model.getListOfId().contains(Integer.valueOf(id_arc)));
						
						System.out.println("Retrait d'arcs\narc_id:"+id_arc+"\nid_node1:"+id_node1+" id_node2:"+id_node2+"\nMaxId:"+model.getMaxId()+"\n");
						i=i+1;
					}
					}		
				}
				
			 
				else{
					if((tour%15)==0 && !(model.getListOfNodeSize()==0)){
						int id_node;
						int ind=0;
						INode node_remove;
						ind=(int)(Math.random()*model.getListOfNodeSize());
					    Vector<IArc> outArc;
					    Vector<IArc> inArc;
					    
					    
						node_remove=model.getNthNode(ind);
						id_node=node_remove.getId();
						
						outArc = new Vector<IArc>(node_remove.getListOfOutputArc());
					    inArc = new Vector<IArc>(node_remove.getListOfInputArc());
					    
					    model.removeNode(node_remove);
					    
					    assertTrue(model.getANode(id_node)==null);
					    
					    for(int i=0;i<outArc.size();i++){
					    	IArc a=outArc.get(i);
					    	INode n=a.getEndingNode();
					    	int a_id=a.getId();
					    	assertTrue(model.getAnArc(a_id)==null);
					    	assertFalse(n.getListOfInputArc().contains(a));
					    }
					    
					    for(int i=0;i<inArc.size();i++){
					    	IArc a=inArc.get(i);
					    	INode n=a.getStartingNode();
					    	int a_id=a.getId();
					    	assertTrue(model.getAnArc(a_id)==null);
					    	assertFalse(n.getListOfOutputArc().contains(a));
					    }	
					    System.out.println("Retrait de noeud\nid_node:"+id_node+"\n");
					}
						
						
								
						
					
					else{
						//Ajout autorisé
						if(action_alea==0){
							System.out.println("Cas : Ajout autorisé");
							switchArcOK(arc);
							if(!(model.getListOfNodeSize()==0)){
					
								INode node1=arc.getStartingNode();
								INode node2=arc.getEndingNode();
								id_node1 = arc.getStartingNode().getId();
								id_node2 = arc.getEndingNode().getId();
								id_arc=arc.getId();
									
								assertTrue(model.getAnArc(id_arc)!=null);
								assertTrue(node1.getListOfOutputArc().contains(arc));
								assertTrue(node2.getListOfInputArc().contains(arc));
								//System.out.println("ADD tour:"+tour+"\narc_id:"+id_arc+"\nid_node1:"+id_node1+" id_node2:"+id_node2+"\nMaxId:"+model.getMaxId()+"\n");
								}
							else{System.out.println("ListOfNode vide: l'ajout d'un arc ne peut s'effectuer\n");}
						}
						else{
							//Action non autorisée
							System.out.println("Cas : Ajout non conforme");
							switchArcNull(arc);
							if(!(model.getListOfNodeSize()==0)){
								id_arc=arc.getId();
								assertTrue(model.getAnArc(id_arc)==null);
							}
							else{System.out.println("ListOfNode vide\n");}
							}
						}
					}
			tour ++;
		}
		System.out.println("FIN DES TESTS!");
	}
}

