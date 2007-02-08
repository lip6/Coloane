package fr.lip6.move.coloane.api.model;

import java.util.Vector;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

import java.io.Serializable;

import fr.lip6.move.coloane.api.utils.CamiParser;
import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Enrichissement de la definition d'un modele generique par :
 * <ul>
 * 	<li>Sa traduction en CAMI</li>
 * 	<li>Sa construction a partir de commande CAMI</li>
 * </ul>
 * @see fr.lip6.move.coloane.interfaces.model.Model
 */
public class Model extends fr.lip6.move.coloane.interfaces.model.Model implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Model(Vector<String> commands) {
		super(commands);
	}

	/**
     * Construction du modele a partir d'un vecteur de commandes CAMI
     * @param camiCommande Le vecteur de commandes CAMI
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

                type = st.nextToken("(");
	
                // Decouverte d'un noeud
                if (type.equals("CN")) { 
                	String nodeType = ps.parseString(",");
                    String nodeId = ps.parseInt(")");

                    // Si le noeud en cours n'est pas le noeud principal du modele
                    if (Integer.parseInt(nodeId) != 1) {
                    	INode node = new Node(nodeType, 0, 0, Integer.parseInt(nodeId));
                        this.addNode(node);
                    }
                    continue; // Prochaine commande
                }
                
                // Decouverte d'un arc
                if (type.equals("CA")) { 
                    String arcType = ps.parseString(",");
                    String arcId = ps.parseInt(",");
                    String from = ps.parseInt(",");
                    String to = ps.parseInt(")");

                    // Recherche de la source et de la cible
                    INode nodeBegin = getANode(Integer.parseInt(from));
                    INode nodeEnd = getANode(Integer.parseInt(to));

                    if (nodeBegin == null || nodeEnd == null) {
                        throw new SyntaxErrorException("Noeud source ou cible manquant");
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
                if (type.equals("CT")) { 
                    String[] value = new String[1];

                    String name = ps.parseString(",");
                    String ref = ps.parseInt(",");
                    value[0] = ps.parseString(")");

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
                    throw new SyntaxErrorException("Element referent introuvable");
                }
                
                // Creation d'une ligne dans un attribut multi-ligne
                if (type.equals("CM")) { 
                    String[] value = new String[1];
                    boolean found = false;

                    String name = ps.parseString(",");
                    String ref = ps.parseInt(",");
                    ps.parseInt(",");
                    ps.parseInt(",");
                    value[0] = ps.parseString(")");
                    st = new StringTokenizer(line);

                    try {
                        // Si le referent a comme identifiant 1, alors il faut attacher l'attribut au modele
                    	if (Integer.parseInt(ref) == 1) {
                            
                    		// On recherche l'attribut a modifier
                    		for (int i = 0; i < this.getListOfAttrSize(); i++) {
                                IAttribute a = this.getNthAttr(i);
                                if (name.equals((a).getName())) {
                                    // On ajoute une ligne
                                    a.riseNbLine(1);
                                    a.setValue(value[0], a.getSize() - 1);
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
                                	// On ajoute une ligne
                                	att.riseNbLine(1);
                                	att.setValue(value[0],att.getSize() - 1);
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
                                	// On ajoute une ligne
                                	att.riseNbLine(1);
                                	att.setValue(value[0], att.getSize() - 1);
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
                        throw new SyntaxErrorException("Construction d'un attribut multiligne incorrecte");
                    }

                }
                
                // Decouverte d'une position de noeud
                if (type.equals("PO") || type.equals("pO")) { 
                    String ref = ps.parseInt(",");
                    String x = ps.parseInt(",");
                    String y = ps.parseInt(")");
                    
                    if (Integer.parseInt(ref) == 1) {
                    	this.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                    }
                    
                    if (Integer.parseInt(ref) != -1) {
                        INode node = getANode(Integer.parseInt(ref));
                        if (node != null) {
                        	node.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                        } else {
                            throw new SyntaxErrorException("La position est attachee a un element introuvable ou incorrect");
                        }
                    }
                }
                
                // Decouverte d'une position de texte
                if (type.equals("PT")) { 
                    String ref = ps.parseInt(",");
                    String name = ps.parseString(",");
                    String x = ps.parseInt(",");
                    String y = ps.parseInt(")");

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
                    
                    throw new SyntaxErrorException("Impossible d'attachee la position de texte a un attribut");
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

      	
        // Ajout des noeuds
        for (int i = 0; i < this.getListOfNodeSize(); i++) {
        	nodes = (this.getNthNode(i)).translate();
            
        	for (int j = 0; j < nodes.length; j++) {
                if (!nodes[j].equals("")) {
                    vec.add(nodes[j]);
                }
            }
        }

        // Ajout des arcs
        for (int i = 0; i < this.getListOfArcSize(); i++) {
        	arcs = (this.getNthArc(i)).translate();
            
        	for (int j = 0; j < arcs.length; j++) {
                if (!arcs[j].equals("")) {
                    vec.add(arcs[j]);
                }
            }
        }

        String[] cami = new String[vec.size()];
        vec.toArray(cami);
        return cami;
    }
}

