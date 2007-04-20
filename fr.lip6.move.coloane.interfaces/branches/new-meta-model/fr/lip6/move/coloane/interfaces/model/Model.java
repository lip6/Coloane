package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;
import java.io.File;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.Serializable;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

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
 * Le modele generique porte l'information sur son formalisme en tant que chaine de caracteres.
 */
public abstract class Model implements IModel, Serializable {

    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Type du modele. */
    private String formalism = "";
    
    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    private int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    private int yPosition;

    /** Liste de l'ensemble des elements noeuds du modele. */
    private Vector<INode> listOfNode;

    /** Liste de l'ensemble des attributs du modele. */
    private Vector<IAttribute> listOfAttr;

    /** Liste de l'ensemble des arcs du modele. */
    private Vector<IArc> listOfArc;
    
    /** ID maximum du modele */
    private int maxId;

    /**
     * Constructeur 
     */
    public Model() {
        this.xPosition = 20;
        this.yPosition = 20;
        this.listOfArc = new Vector<IArc>();
        this.listOfAttr = new Vector<IAttribute>();
        this.listOfNode = new Vector<INode>();
        this.maxId = 1;
    }
    
    /**
     * Constructeur
     * 
     * @param commands Une suite de commandes qui permettent de construire le modele.
     */
    public Model(Vector<String> commands) {
        this.xPosition = 20;
        this.yPosition = 20;
        this.listOfArc = new Vector<IArc>();
        this.listOfAttr = new Vector<IAttribute>();
        this.listOfNode = new Vector<INode>();
        this.maxId = 1;
        
        try {
        	// Tentative de construction du modele depuis les commandes transmises par l'API
			this.buildModel(commands);
		} catch (SyntaxErrorException e) {
			e.toString();
		}        
    }
    
    /**
     * Constructeur  
     * 
     * @param commandsFile Un fichier contenant des commandes pour construire le modele
     * @throws IOException Erreur de lecture dans le fichier
     */
    public Model(File commandsFile) throws IOException {
        this.xPosition = 20;
        this.yPosition = 20;
        this.listOfArc = new Vector<IArc>();
        this.listOfAttr = new Vector<IAttribute>();
        this.listOfNode = new Vector<INode>();
        this.maxId = 1;

        BufferedReader buffer;
        
        // Un vecteur de commandes de construction
        Vector<String> commands = new Vector<String>();

        try {
        	// Lecture du fichier
        	buffer = new BufferedReader(new FileReader(commandsFile));
            while (buffer.ready()) {
            	commands.add(buffer.readLine());
            }
            buffer.close();
            
            this.buildModel(commands);        
            
        // TODO : Documenter cette exception
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        
        // Erreur de localisation du fichier
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
            
        // Erreur de lecture du fichier
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
            
        // Erreur de syntaxe
        } catch (SyntaxErrorException e) {
            e.toString();
        }
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#buildModel(java.util.Vector)
	 */
    /*public abstract void buildModel(Vector<String> commands) throws SyntaxErrorException;
    */
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getANode(int)
	 */
    public INode getANode(int uniqueId) {
    	// Parcours ed la liste des noeuds
    	Enumeration e = this.listOfNode.elements();
    	
        while (e.hasMoreElements()) {
        	INode node = (INode) e.nextElement();
            if (node.getId() == uniqueId) {
                return node;
            }
        }
        return null;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getAnArc(int)
	 */
    public IArc getAnArc(int uniqueId) {
        // Parcours de la liste des arcs
    	Enumeration e = this.listOfArc.elements();

        while (e.hasMoreElements()) {
        	IArc arc = (IArc) e.nextElement();
            if (arc.getId() == uniqueId) {
                return arc;
            }
        }
        return null;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#addNode(fr.lip6.move.coloane.interfaces.model.INode)
	 */
    public void addNode(INode node) {
    	
    	// On ajoute le noeud seulement s'il n'est pas deja dans le modele
    	if (!this.listOfNode.contains(node)) {
            this.listOfNode.addElement(node);
            
            // Gestion de l'identifiant du noeud
            if (node.getId() == 0) {
            	node.setId(this.maxId+1);
            	this.maxId++;
            }
        }
        
        
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#addArc(fr.lip6.move.coloane.interfaces.model.Arc)
	 */
    public void addArc(IArc arc) {
    	INode start = arc.getStartingNode();
        INode end = arc.getEndingNode();
        
        // Si l'arc est deja dans le modele...
        if (this.listOfArc.contains(arc)) {
        	return;
        }
       
        if ((start != null) && (end != null)) {
        	//Les noeuds cible et source de l'arc doivent être présent dans le modèle
        	if((listOfNode.contains(start)) && (listOfNode.contains(end))){
        		
        		start.addOutputArc(arc);
        		end.addInputArc(arc);
        		this.listOfArc.addElement(arc);
        	
        
        		// Gestion de l'identifiant du noeud
        		if (arc.getId() == 0) {
        			arc.setId(this.maxId+1);
        			this.maxId++;
        		}
        	}
        	else {System.out.println("Un des noeuds de l'arc est manquant : arc "+arc.getId());}
        }
        else {
            System.out.println("Debut ou fin du noeud manquant "+arc.getId());
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#addAttribute(fr.lip6.move.coloane.interfaces.model.IAttribute)
	 */
    public void addAttribute(IAttribute attribute) {
        this.listOfAttr.addElement(attribute);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#removeNode(fr.lip6.move.coloane.interfaces.model.INode)
	 */
    public void removeNode(INode node) {

        if (this.listOfNode.contains(node)) {
            int nbIn = node.getListOfInputArcSize();
            int nbOut = node.getListOfOutputArcSize();

            try {
                // on retire les arcs entrants
                for (int i = 0; i < nbIn; i++) {
                	IArc in = node.getNthInputArc(0);
                    this.removeArc(in);
                }

                // on retire les arcs sortants
                for (int i = 0; i < nbOut; i++) {
                	IArc out = node.getNthOutputArc(0);
                    this.removeArc(out);
                }
                this.listOfNode.remove(node);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#removeArc(fr.lip6.move.coloane.interfaces.model.IArc)
	 */
    public void removeArc(IArc arc) {
        if (this.listOfArc.contains(arc)) {
            INode in = arc.getStartingNode();
            INode out = arc.getEndingNode();
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

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getListOfAttrSize()
	 */
    public int getListOfAttrSize() {
        return this.listOfAttr.size();
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getListOfArcSize()
	 */
    public int getListOfArcSize() {
        return this.listOfArc.size();
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getListOfNodeSize()
	 */
    public int getListOfNodeSize() {
        return this.listOfNode.size();
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getNthAttr(int)
	 */
    public IAttribute getNthAttr(int index) {
        return (IAttribute) this.listOfAttr.get(index);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getNthArc(int)
	 */
    public IArc getNthArc(int index) {
        return (IArc) this.listOfArc.get(index);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getNthNode(int)
	 */
    public INode getNthNode(int index) {
        return (INode) this.listOfNode.get(index);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getXPosition()
	 */
    public int getXPosition() {
        return this.xPosition;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getYPosition()
	 */
    public int getYPosition() {
        return this.yPosition;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#setPosition(int, int)
	 */
    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#setFormalism(java.lang.String)
	 */
    public void setFormalism(String formalism) {
    	this.formalism = formalism;
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getFormalism()
	 */
    public String getFormalism () {
    	return this.formalism;
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#getMaxId()
	 */
    public int getMaxId() {
        int max = 1;
        INode nodes;
        IArc arcs;

        // Parcours des noeuds
        for (int i = 0; i < this.getListOfNodeSize(); i++) {
            nodes = getNthNode(i);

            if (max < nodes.getId()) {
                max = nodes.getId();
            }
        }

        // Parcours des arcs
        for (int i = 0; i < this.getListOfArcSize(); i++) {
            arcs = getNthArc(i);

            if (max < arcs.getId()) {
                max = arcs.getId();
            }
        }

        return max;
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#setMaxId(int)
	 */
    public int setMaxId(int max) {
    	this.maxId = max;
    	return max;
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IModel#translate()
	 */
   public abstract String[] translate();
}

