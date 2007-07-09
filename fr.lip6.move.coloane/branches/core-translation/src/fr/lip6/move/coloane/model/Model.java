package fr.lip6.move.coloane.model;

import java.util.Vector;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.Arrays;

import java.io.File;
import java.io.Serializable;

import fr.lip6.move.coloane.api.utils.CamiParser;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.main.Coloane;

/**
 * Cette classe represente un modele generique.<br>
 * Elle permet la manipulation des elements du modele et le parcours de ceux-ci.<br>
 * Un modele se compose de :
 * <ul>
 * 	<li> Une liste de noeuds</li>
 * 	<li> Une liste d'attributs</li>
 * 	<li> Une liste d'arcs</li>
 * </ul>
 * A noter qu'un modele a toujours un identifiant egal a 1<br>
 * Le modele generique porte l'information sur son formalisme en tant que chaine de caracteres.<br>
 * @see fr.lip6.move.coloane.interfaces.model.Model
 */
public class Model extends fr.lip6.move.coloane.interfaces.model.Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Model(Vector<String> commands) {
		super(commands);
	}
	
	public Model() {
		super();
	}
	
	public Model(File commandsFile) throws Exception {
		super(commandsFile);
	}

	/**
     * Construction du modele a partir d'un vecteur de commande CAMI
     * @param Vector camiCommande Le vecteur de commandes CAMI
     * @throws SyntaxErrorException
     */
    public void buildModel(Vector camiCommande) throws SyntaxErrorException {
    	String line = null;
        String type = null;
        
        StringTokenizer st;
        CamiParser ps;
    	
    	try {
    		for (int k = 0; k < camiCommande.size(); k++) {
        		line = (String) camiCommande.get(k);
        		
        		st = new StringTokenizer(line);
                ps = new CamiParser(line.substring(3));

                type = st.nextToken("("); //$NON-NLS-1$
	
                // Decouverte d'un noeud
                if (type.equals("CN")) {  //$NON-NLS-1$
                	String nodeType = ps.parseString(","); //$NON-NLS-1$
                    String nodeId = ps.parseInt(")"); //$NON-NLS-1$

                    // Si le noeud en cours n'est pas le noeud principal du modele
                    if (Integer.parseInt(nodeId) != 1) {
                    	INode node = new Node(nodeType, 0, 0, Integer.parseInt(nodeId));
                        this.addNode(node);
                    }
                    else{
                    	this.setFormalism(nodeType);
                    }
                    	
                    continue; // Prochaine commande
                }
                
                // Decouverte d'un arc
                if (type.equals("CA")) {  //$NON-NLS-1$
                    String arcType = ps.parseString(","); //$NON-NLS-1$
                    String arcId = ps.parseInt(","); //$NON-NLS-1$
                    String from = ps.parseInt(","); //$NON-NLS-1$
                    String to = ps.parseInt(")"); //$NON-NLS-1$

                    // Recherche de la source et de la cible
                    INode nodeBegin = getANode(Integer.parseInt(from));
                    INode nodeEnd = getANode(Integer.parseInt(to));

                    if (nodeBegin == null || nodeEnd == null) {
                        throw new SyntaxErrorException(Coloane.traduction.getString("model.Model.9")); //$NON-NLS-1$
                    }

                    // Creation de l'arc
                    IArc arc = new Arc(arcType, Integer.parseInt(arcId));
                    arc.setStartingNode(nodeBegin);
                    arc.setEndingNode(nodeEnd);
                    
                    nodeBegin.addOutputArc(arc);
                    nodeEnd.addInputArc(arc);

                    this.addArc(arc);
                    continue;// Prochaine commande
                }
                
                // Decouverte d'attribut sur une ligne
                if (type.equals("CT")) {  //$NON-NLS-1$
                    String value = new String();

                    String name = ps.parseString(","); //$NON-NLS-1$
                    String ref = ps.parseInt(","); //$NON-NLS-1$
                    value = ps.parseString(")"); //$NON-NLS-1$

                    // Creation effective de l'attribut
                    IAttribute attr = new Attribute(name, value, Integer.parseInt(ref));

                    // L'attribut peut directement etre attache au modele
                    if (Integer.parseInt(ref) == 1) {
                        this.addAttribute(attr);
                        continue;
                    } 

                    // Est-ce que l'attribut s'attache a un arc ?
                    IArc arc = getAnArc(Integer.parseInt(ref));
                    if (arc != null) {
                    	arc.addAttribute(attr);
                    	continue;
                    }
                    
                    // Est-ce que l'attribut s'attache a un noeud ?
                    INode node = getANode(Integer.parseInt(ref));
                    if (node != null) {
                    	node.addAttribute(attr);
                    	continue;
                    }

                    // Sinon on retourne une erreur
                    throw new SyntaxErrorException(Coloane.traduction.getString("model.Model.14")); //$NON-NLS-1$
                }
                
                // Creation d'une ligne dans un attribut multi-ligne
                if (type.equals("CM")) {  //$NON-NLS-1$
                    String value = new String();;
                    boolean found = false;

                    String name = ps.parseString(","); //$NON-NLS-1$
                    String ref = ps.parseInt(","); //$NON-NLS-1$
                    ps.parseInt(","); //$NON-NLS-1$
                    ps.parseInt(","); //$NON-NLS-1$
                    value = ps.parseString(")"); //$NON-NLS-1$
                    st = new StringTokenizer(line);

                    try {
                        // Si le referent a comme identifiant 1, alors il faut attacher l'attribut au modele
                    	if (Integer.parseInt(ref) == 1) {
                            
                    		// On recherche l'attribut a modifier
                    		for (int i = 0; i < this.getListOfAttrSize(); i++) {
                                IAttribute a = this.getNthAttr(i);
                                if (name.equals((a).getName())) {
                                    
                                    a.setValue(a.getValue()+"\r"+value); //$NON-NLS-1$
                                    found = true;
                                    break; // On sort de cette boucle de recherche
                                }
                            }
                    		
                    		// Si aucun attribut n'a ete trouve... On en cree un!
                            if (!found) {
                                IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
                                this.addAttribute(att);
                            }
                            continue;
                        
                        } 
                    	
                    	// On cherche a savoir si l'attribut s'attache a un arc ? 
                    	IArc arc = getAnArc(Integer.parseInt(ref));
                        if (arc != null) {
                        	for (int i = 0; i < arc.getListOfAttrSize(); i++) {
                        		IAttribute att = arc.getNthAttr(i);
                                if (name.equals(att.getName())) {
                                	att.setValue(att.getValue()+"\r"+value); //$NON-NLS-1$
                                    found = true;
                                    break;
                                }
                            }
                            
                        	// Si aucun attribut de l'arc ne correspond on en cree un
                        	if (!found) {
                        		// On cree un nouvel attribut
                                IAttribute att = new Attribute(name, value, Integer.parseInt(ref));
                                arc.addAttribute(att);
                            }
                        	continue;
                        }
                        
                        // On cherche a savoir si l'attribut s'attache a un noeud ?
                        INode node = getANode(Integer.parseInt(ref));
                        if (node != null) {
                        	for (int i = 0; i < node.getListOfAttrSize(); i++) {
                        		IAttribute att = node.getNthAttr(i);
                                if (name.equals(att.getName())) {
                                	att.setValue(att.getValue()+"\r"+value); //$NON-NLS-1$
                                    found = true;
                                    break;
                                }
                            }
                            
                        	// Si aucun attribut du noeud ne correspond... On en cree un
                        	if (!found) {
                        		IAttribute att = new Attribute(name, value,Integer.parseInt(ref));
                        		node.addAttribute(att);
                            }
                        	
                        }
                        
                    } catch (Exception e) {
                        throw new SyntaxErrorException(Coloane.traduction.getString("model.Model.24")); //$NON-NLS-1$
                    }

                }
                
                // Decouverte d'une position de noeud
                if (type.equals("PO") || type.equals("pO")) {  //$NON-NLS-1$ //$NON-NLS-2$
                    String ref = ps.parseInt(","); //$NON-NLS-1$
                    String x = ""; //$NON-NLS-1$
                    String y = ""; //$NON-NLS-1$
                    
//                  !! Attention bidouille pour prendre en compte le PO de 3e type
                    if (Integer.parseInt(ref) == -1) {
                    	ref = ps.parseInt(","); //$NON-NLS-1$
                    	x = ps.parseInt(","); //$NON-NLS-1$
                    	y = ps.parseInt(","); //$NON-NLS-1$
                    	//x = ps.parseInt(","); 
                    } else {
	                    x = ps.parseInt(","); //$NON-NLS-1$
	                    y = ps.parseInt(")"); //$NON-NLS-1$
                    }
                    
                    
                    
                    
                    
                    if (Integer.parseInt(ref) == 1) {
                    	this.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                    }
                    
                    if (Integer.parseInt(ref) != 1) {
                        INode node = getANode(Integer.parseInt(ref));
                        if (node != null) {
                        	node.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                        } else {
                        	throw new SyntaxErrorException(Coloane.traduction.getString("model.Model.42")); //$NON-NLS-1$
                        }
                    }
                }
                
                // Decouverte d'une position de texte
                if (type.equals("PT")) {  //$NON-NLS-1$
                    String ref = ps.parseInt(","); //$NON-NLS-1$
                    String name = ps.parseString(","); //$NON-NLS-1$
                    String x = ps.parseInt(","); //$NON-NLS-1$
                    String y = ps.parseInt(")"); //$NON-NLS-1$

                    if (Integer.parseInt(ref) == 1) {
                        for (int i = 0; i < this.getListOfAttrSize(); i++) {
                            IAttribute attr = this.getNthAttr(i);
                            if (attr.getName().equals(name)) {
                                attr.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                                break;
                            }
                        }
                        continue;
                    } 
                    
                    IArc arc = getAnArc(Integer.parseInt(ref));
                    if (arc != null) {
                    	for (int i = 0; i < arc.getListOfAttrSize(); i++) {
                    		IAttribute attr = arc.getNthAttr(i);
                            if (attr.getName().equals(name)) {
                            	attr.setPosition(Integer.parseInt(x),Integer.parseInt(y));
                                break;
                            }
                        }
                    	continue;
                    }
                     
                    INode node = getANode(Integer.parseInt(ref));
                    if (node != null) {
                    	for (int i = 0; i < node.getListOfAttrSize(); i++) {
                    		IAttribute attr = node.getNthAttr(i);
                            if (attr.getName().equals(name)) {
                            	attr.setPosition(Integer.parseInt(x),Integer.parseInt(y));
                                break;
                            }
                        }
                    	continue;
                    }
                    
                    throw new SyntaxErrorException(Coloane.traduction.getString("model.Model.41")); //$NON-NLS-1$
                }
            }
    		
    		 /* TODO: Prendre en compte les points d'inflexion */

    	} catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (SyntaxErrorException e) {
            e.toString();
        }
    }


    
    /**
     * Traduit un modele en chaines de caracteres CAMI correspondantes.
     * @return la chaine de caracteres CAMI correspondante a l'objet Model
     */
    public String[] translate() {
        Vector<String> vec = new Vector<String>();
        String[] nodes;
        String[] arcs;
        StringBuffer s;
        
        // Ajout du modele
        s = new StringBuffer();
        s.append("CN("); //$NON-NLS-1$
        s.append(this.getFormalism().length() + ":" + this.getFormalism()); //$NON-NLS-1$
        s.append(","); //$NON-NLS-1$
        s.append("1"); //$NON-NLS-1$
        s.append(")"); //$NON-NLS-1$
        vec.addElement(s.toString());
        
        
        for (int i = 0; i < this.getListOfAttrSize(); i++) {
            vec.addAll(Arrays.asList(this.getNthAttr(i).translate()));
        }
      	
        // Ajout des noeuds
        for (int i = 0; i < this.getListOfNodeSize(); i++) {
        	nodes = (this.getNthNode(i)).translate();
            
        	for (int j = 0; j < nodes.length; j++) {
                if (!nodes[j].equals("")) { //$NON-NLS-1$
                    vec.add(nodes[j]);
                }
            }
        }

        // Ajout des arcs
        for (int i = 0; i < this.getListOfArcSize(); i++) {
        	arcs = (this.getNthArc(i)).translate();
            
        	for (int j = 0; j < arcs.length; j++) {
                if (!arcs[j].equals("")) { //$NON-NLS-1$
                    vec.add(arcs[j]);
                }
            }
        
        }
        String[] cami = new String[vec.size()];
        vec.toArray(cami);
        return cami;
    }
}

