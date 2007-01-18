package fr.lip6.move.coloane.communications.models;

import java.util.Vector;
import java.io.File;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.Serializable;


import fr.lip6.move.coloane.communications.utils.CamiParser;
import fr.lip6.move.coloane.exceptions.SyntaxErrorException;


/**
 * Classe Model permettant de representer en memoire plusieurs formalismes de
 * reseaux. Cette representaion generique optimise le parcours du modele et la
 * recuperation des informations relatifs aux elements de celui-ci. Les
 * differents formalisme traites sont :
 * <ul>
 * <li> Reseaux de Petri
 * <li> Automates d'etat
 * <li> Prefix nets
 * </ul>
 * 
 */
public class Model extends Base implements Serializable {

    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Type du modele. */
    private String formalism;
    
    /** Type du modele. */
    private int id;
    
    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    public int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    public int yPosition;

    /** Liste de l'ensemble des elements noeuds du modele. */
    private Vector<Node> listOfNode;

    /** Liste de l'ensemble des attributs du modele. */
    private Vector<Attribute> listOfAttr;

    /** Liste de l'ensemble des arcs du modele. */
    private Vector<Arc> listOfArc;

    /**
     * Constructeur de la classe Model. 
     * @param formalism Formalisme (ex : AMI-Net)
     */
    public Model(String formalism) {
        this.formalism = formalism;
        this.xPosition = 20;
        this.yPosition = 20;
        this.id = 1;
        this.listOfArc = new Vector<Arc>();
        this.listOfAttr = new Vector<Attribute>();
        this.listOfNode = new Vector<Node>();
    }
    
    /**
     * Construction du modele a partir d'un vecteur de commande CAMI
     * @param Vector camiCommande Le vecteur de commandes CAMI
     * @throws SyntaxErrorException
     */
    private void buildModel(Vector camiCommande) throws SyntaxErrorException {
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
                    String nodeType;
                    String nodeId;
                    Node node;

                    nodeType = ps.parseString(",");
                    nodeId = ps.parseInt(")");

                    if (Integer.parseInt(nodeId) == 1) {
                        this.formalism = nodeType;
                        this.id = Integer.parseInt(nodeId);
                    } else {
                        node = new Node(nodeType, 0, 0, Integer.parseInt(nodeId));
                        this.listOfNode.addElement(node);
                    }
                }
                
                // Decouverte d'un arc
                if (type.equals("CA")) { 
                    Arc arc;
                    String arcType;
                    String arcId;
                    String from;
                    String to;
                    Node nodeBegin;
                    Node nodeEnd;

                    arcType = ps.parseString(",");
                    arcId = ps.parseInt(",");
                    from = ps.parseInt(",");
                    to = ps.parseInt(")");

                    nodeBegin = getANode(Integer.parseInt(from));
                    nodeEnd = getANode(Integer.parseInt(to));
                    System.out.println("Arc "+arcId+" from "+from+" to "+to);

                    if (nodeBegin == null || nodeEnd == null) {
                        throw new SyntaxErrorException();
                    }

                    arc = new Arc(arcType, Integer.parseInt(arcId));
                    arc.setStartingNode(nodeBegin);
                    arc.setEndingNode(nodeEnd);

                    nodeBegin.addOutputArc(arc);
                    nodeEnd.addInputArc(arc);

                    this.listOfArc.addElement(arc);
                }
                
                // Decouverte d'attribut sur une ligne
                if (type.equals("CT")) { 
                    Attribute attr;
                    String name;
                    String ref;
                    String[] value = new String[1];
                    Arc arc;
                    Node node;

                    name = ps.parseString(",");
                    ref = ps.parseInt(",");
                    value[0] = ps.parseString(")");

                    attr = new Attribute(name, value, Integer.parseInt(ref));

                    if (Integer.parseInt(ref) == 1) {
                        this.listOfAttr.addElement(attr);
                    } else {
                        arc = getAnArc(Integer.parseInt(ref));
                        if (arc != null) {
                            arc.addAttribute(attr);
                        } else {
                            node = getANode(Integer.parseInt(ref));
                            if (node != null) {
                                node.addAttribute(attr);
                            } else {
                                throw new SyntaxErrorException();
                            }
                        }
                    }
                }
                
                // Decouverte d'un attribut
                if (type.equals("CM")) { 
                    Attribute attr;
                    Attribute tmp;
                    String name;
                    String ref;
                    String[] value = new String[1];
                    Arc arc;
                    Node node;

                    boolean found = false;

                    name = ps.parseString(",");
                    ref = ps.parseInt(",");
                    line = ps.parseInt(",");
                    ps.parseInt(",");
                    value[0] = ps.parseString(")");
                    st = new StringTokenizer(line);

                    try {
                        if (Integer.parseInt(ref) == 1) {
                            for (int i = 0; i < this.getListOfAttrSize(); i++) {
                                tmp = this.getNthAttr(i);
                                if (name.equals((tmp).getName())) {
                                    // On ajoute une ligne
                                    tmp.riseNbLine(1);
                                    tmp.setValue(value[0], tmp.getSize() - 1);
                                    found = true;
                                }
                            }
                            if (!found) {
                                // On cree un nouvel attribut
                                attr = new Attribute(name, value, Integer.parseInt(ref));
                                this.addAttribute(attr);
                            }
                        } else {
                            arc = getAnArc(Integer.parseInt(ref));
                            if (arc != null) {
                                for (int i = 0; i < arc.getListOfAttrSize(); i++) {
                                    tmp = arc.getNthAttr(i);
                                    if (name.equals((tmp).getName())) {
                                        // On ajoute une ligne
                                        tmp.riseNbLine(1);
                                        tmp.setValue(value[0],tmp.getSize() - 1);
                                        found = true;
                                    }
                                }
                                if (!found) {
                                    // On cree un nouvel attribut
                                    attr = new Attribute(name, value, Integer.parseInt(ref));
                                    arc.addAttribute(attr);
                                }
                            } else {
                                node = getANode(Integer.parseInt(ref));
                                if (node != null) {
                                    for (int i = 0; i < node.getListOfAttrSize(); i++) {
                                        tmp = node.getNthAttr(i);
                                        if (name.equals((tmp).getName())) {
                                            // On ajoute une ligne
                                            tmp.riseNbLine(1);
                                            tmp.setValue(value[0], tmp.getSize() - 1);
                                            found = true;
                                        }
                                    }
                                    if (!found) {
                                        attr = new Attribute(name, value,Integer.parseInt(ref));
                                        node.addAttribute(attr);
                                    }
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        throw new SyntaxErrorException();
                    } catch (Exception e) {
                        throw new SyntaxErrorException();
                    }

                }
                
                // Decouverte d'une position de noeud
                if (type.equals("PO")) { 
                    Node node = null;
                    String ref;
                    String x;
                    String y;

                    ref = ps.parseInt(",");
                    if (Integer.parseInt(ref) != -1) {
                        x = ps.parseInt(",");
                        y = ps.parseInt(")");

                        if (Integer.parseInt(ref) == 1) {
                            this.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                        } else {
                            node = getANode(Integer.parseInt(ref));
                            if (node != null) {
                                node.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                            } else {
                                throw new SyntaxErrorException();
                            }
                        }
                    }
                }
                
                // Decouverte d'une position de texte
                if (type.equals("PT")) { 
                    Node node = null;
                    Arc arc = null;
                    Attribute attr;
                    String name;
                    String ref;
                    String x;
                    String y;

                    ref = ps.parseInt(",");
                    name = ps.parseString(",");
                    x = ps.parseInt(",");
                    y = ps.parseInt(")");

                    if (Integer.parseInt(ref) == 1) {
                        for (int i = 0; i < this.getListOfAttrSize(); i++) {
                            attr = this.getNthAttr(i);
                            if (attr.getName().equals(name)) {
                                attr.setPosition(Integer.parseInt(x), Integer.parseInt(y));
                                break;
                            }
                        }
                    } else {
                        arc = getAnArc(Integer.parseInt(ref));
                        if (arc != null) {
                            for (int i = 0; i < arc.getListOfAttrSize(); i++) {
                                attr = arc.getNthAttr(i);
                                if (attr.getName().equals(name)) {
                                    attr.setPosition(Integer.parseInt(x),Integer.parseInt(y));
                                    break;
                                }
                            }
                        } else {
                            node = getANode(Integer.parseInt(ref));
                            if (node != null) {
                                for (int i = 0; i < node.getListOfAttrSize(); i++) {
                                    attr = node.getNthAttr(i);
                                    if (attr.getName().equals(name)) {
                                        attr.setPosition(Integer.parseInt(x),Integer.parseInt(y));
                                        break;
                                    }
                                }
                            } else {
                                throw new SyntaxErrorException();
                            }
                        }
                    }
                }
                
                /* TODO: Prendre en compte les points d'inflexion */
                
                // Mise a jour de l'indicateur d'indice
                setMaxId(this.getMaxId());
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur de la classe Model.
     * @param camiCommande un vecteur de chaine de caractere dont chaque chaine est une commande CAMI de construction d'objet du model 
     */
    public Model(Vector camiCommande) {
        this.xPosition = 20;
        this.yPosition = 20;
        this.listOfArc = new Vector<Arc>();
        this.listOfAttr = new Vector<Attribute>();
        this.listOfNode = new Vector<Node>();
        
        try {
			this.buildModel(camiCommande);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}        
    }
    
    /**
     * Constructeur de la classe Model. 
     * @param formalism formalisme du modele (ex : AMI-Net)
     * @param camiCommande Vecteur de chaine de caractere dont chaque chaine est une commande CAMI de construction d'objet du model 
     * 
     */
    public Model(String formalism, Vector camiCommande) {
    	this.formalism = formalism;
        this.xPosition = 20;
        this.yPosition = 20;
        this.listOfArc = new Vector<Arc>();
        this.listOfAttr = new Vector<Attribute>();
        this.listOfNode = new Vector<Node>();
        
        try {
			this.buildModel(camiCommande);
		} catch (SyntaxErrorException e) {
			e.printStackTrace();
		}        
    }

    /**
     * Constructeur de la classe Model.  
     * @param camiFile le fichier CAMI
     * @throws IOException Erreur de lecture dans le fichier
     */
    public Model(File camiFile) throws IOException {
        this.xPosition = 20;
        this.yPosition = 20;
        this.listOfArc = new Vector<Arc>();
        this.listOfAttr = new Vector<Attribute>();
        this.listOfNode = new Vector<Node>();

        BufferedReader buffer;
        Vector<String> camiCommande = new Vector<String>();

        try {
            buffer = new BufferedReader(new FileReader(camiFile));
            
            // Lecture du fichier
            while (buffer.ready()) {
                camiCommande.add(buffer.readLine());
            }
            buffer.close();
            this.buildModel(camiCommande);        
            
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (SyntaxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne le noeud d'identifiant uniqueID.
     * @param uniqueId identifiant unique du noeud
     * @return Node
     */
    public Node getANode(int uniqueId) {
        Enumeration enumer;
        Node node;
        enumer = this.listOfNode.elements();

        while (enumer.hasMoreElements()) {
        	node = (Node) enumer.nextElement();
            if (node.getId() == uniqueId) {
                return node;
            }
        }
        return null;
    }

    /**
     * Retourne l'arc d'identifiant uniqueID.
     * @param aUniqueId identifiant unique de l'arc
     * @return Arc
     * @see Arc
     */
    public Arc getAnArc(int uniqueId) {
        Enumeration enumer;
        Arc arc;
        enumer = this.listOfArc.elements();

        while (enumer.hasMoreElements()) {
        	arc = (Arc) enumer.nextElement();
            if (arc.getUniqueId() == uniqueId) {
                return arc;
            }
        }
        return null;
    }

    /**
     * Ajoute un noeud au modele
     * @param node Noeud a ajouter
     * @see Node
     */
    public void addNode(Node node) {
        if (!this.listOfNode.contains(node)) {
            this.listOfNode.addElement(node);
        }
    }

    /**
     * Ajoute un arc au modele
     * @param arc Arc a ajouter
     * @see Arc
     */
    public void addArc(Arc arc) {
        Node start = arc.getStartingNode();
        Node end = arc.getEndingNode();

        if ((!this.listOfArc.contains(arc)) && start != null && end != null) {
            start.addOutputArc(arc);
            end.addInputArc(arc);
            this.listOfArc.addElement(arc);
        } else {
            System.err.println("Debut ou fin du noeud manquant");
        }
    }

    /**
     * Ajoute un attribut au modele.
     * @param attribute l'attribut a ajouter au modele
     * @see Attribute
     */
    public void addAttribute(Attribute attribute) {
        this.listOfAttr.addElement(attribute);
    }

    /**
     * Permet de supprimer un noeud dans le modele.
     * @param node noeud a supprimer du modele
     */
    public void removeNode(Node node) {
        Arc in;
        Arc out;

        if (this.listOfNode.contains(node)) {
            int nbIn = node.getListOfInputArcSize();
            int nbOut = node.getListOfOutputArcSize();

            try {
                // on retire les arcs entrants
                for (int i = 0; i < nbIn; i++) {
                    in = node.getNthInputArc(0);
                    this.removeArc(in);
                }

                // on retire les arcs sortants
                for (int i = 0; i < nbOut; i++) {
                    out = node.getNthOutputArc(0);
                    this.removeArc(out);
                }
                this.listOfNode.remove(node);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Supprime un arc du modele
     * @param arc Arc a supprimer du modele
     * @see Arc
     */
    public void removeArc(Arc arc) {
        if (this.listOfArc.contains(arc)) {
            Node in = arc.getStartingNode();
            Node out = arc.getEndingNode();
            if (in != null) {
                in.removeOutputArc(arc);
            }
            if (in != null) {
                out.removeInputArc(arc);
            }
            try {
                this.listOfArc.remove(arc);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retourne le nombre d'attributs du modele.
     * @return int
     */
    public int getListOfAttrSize() {
        return this.listOfAttr.size();
    }

    /**
     * Retourne le nombre d'arcs du modele
     * @return int
     */
    public int getListOfArcSize() {
        return this.listOfArc.size();
    }

    /**
     * Retourne le nombre de noeuds du modele
     * @return int
     */
    public int getListOfNodeSize() {
        return this.listOfNode.size();
    }

    /**
     * Retourne le nieme attribut du modele.
     * @param index Indice de l'attribut
     * @return Attribute
     * @see Attribute
     */
    public Attribute getNthAttr(int index) {
        return (Attribute) this.listOfAttr.get(index);
    }

    /**
     * Retourne le nieme arc du modele.
     * @param index Indice de l'arc a retourner
     * @return Arc
     * @see Arc
     */
    public Arc getNthArc(int index) {
        return (Arc) this.listOfArc.get(index);
    }

    /**
     * Rend le nieme noeud du modele.
     * @param index Indice du noeud
     * @return Node
     */
    public Node getNthNode(int index) {
        return (Node) this.listOfNode.get(index);
    }

    /**
     * Retourne la coordonnee X
     * @return int
     */
    public int getXPosition() {
        return this.xPosition;
    }

    /**
     * Retourne la coordonnee Y
     * @return int
     */
    public int getYPosition() {
        return this.yPosition;
    }

    /**
     * Cette methode permet de fixer les coordonnees spatiales de l'attribut
     * @param x Coordonnee x
     * @param y Coordonnee y
     */
    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    /**
     * Traduit un objet Model en la chaine de caracteres CAMI correspondante.
     * @return la chaine de caracteres CAMI correspondante a l'objet Model
     */
    public String[] translateToCAMI() {
        Vector<String> vec = new Vector<String>();
        String chaine;
        String[] attributes;
        String[] nodes;
        String[] arcs;


        
        System.out.println("nombre d'attributs : "  + this.getListOfAttrSize());
        System.out.println("nombre d'arcs : "  + this.getListOfArcSize());
        System.out.println("nombre de noeuds : "  + this.getListOfNodeSize());
        
        // Ajout des attributs du modele
        for (int i = 0; i < this.getListOfAttrSize(); i++) {
            attributes = (this.getNthAttr(i)).translateToCAMI();
            
            for (int j = 0; j < attributes.length; j++) {
                if (!attributes[j].equals("")) {
                    vec.add(attributes[j]);
                }
            }
        }
       	
        // Ajout des noeuds
        for (int i = 0; i < this.getListOfNodeSize(); i++) {
        	nodes = (this.getNthNode(i)).translateToCAMI();
            
        	for (int j = 0; j < nodes.length; j++) {
                if (!nodes[j].equals("")) {
                    vec.add(nodes[j]);
                }
            }
        }

        // Ajout des arcs
        for (int i = 0; i < this.getListOfArcSize(); i++) {
        	arcs = (this.getNthArc(i)).translateToCAMI();
            
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


    /**
     * Retourne l'id maximun du modele
     * @return int
     */
    public int getMaxId() {
        int tmp = 1;
        Node lesNodes;
        Arc lesArcs;

        for (int i = 0; i < this.getListOfNodeSize(); i++) {
            lesNodes = this.getNthNode(i);

            if (tmp < lesNodes.getId()) {
                tmp = lesNodes.getId();
            }
        }

        // Ajout des arcs
        for (int i = 0; i < this.getListOfArcSize(); i++) {
            lesArcs = this.getNthArc(i);

            if (tmp < lesArcs.getUniqueId()) {
                tmp = lesArcs.getUniqueId();
            }
        }

        return tmp;
    }

}

