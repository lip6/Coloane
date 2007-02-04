package fr.lip6.move.coloane.api.model;

import java.util.Arrays;
import java.util.Vector;
import java.io.Serializable;

/**
 * Le noeud d'un modele
 */
public class Node extends Base implements Serializable {

    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Type du noeud */
    private String nodeType;
    
    /** Identificateur du noeud */
    private int id;
    
    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    public int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    public int yPosition;

    /** Liste des arc entrants */
    private Vector<Arc> listOfInputArc;

    /** Liste des arcs sortants */
    private Vector<Arc> listOfOutputArc;

    /** Liste des attributs du noeud*/
    private Vector<Attribute> listOfAttr;

    /**
     * Constructeur
     * 
     * @param uniqueId	Identifiant du noeud
     * @param nodeType	Type du noeud
     * @param x			Position x du noeud
     * @param y			Position y du noeud
     */
    public Node(String nodeType, int x, int y, int id) {
        this.nodeType = nodeType;
        this.id = id;
        xPosition = x;
        yPosition = y;
        this.listOfAttr = new Vector<Attribute>(0);
        this.listOfInputArc = new Vector<Arc>(0);
        this.listOfOutputArc = new Vector<Arc>(0);
        
        System.out.println("3. Creation dun noeud avec id:"+this.id);
    }

    /**
     * Retourne l'identifiant du noeud 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * This method sets the x and y position of the node
     * 
     * @param x	Position x
     * @param y Position y
     * 
     */
    public void setPosition(int x, int y) {
    	xPosition = x;
    	yPosition = y;
    }

    /**
     * Ajout d'un arc entrant 
     * @param arc Arc a ajouter
     */
    public void addInputArc(Arc arc) {
    	this.listOfInputArc.addElement(arc);
    }

    /**
     * Ajoute un arc sortant
     * @param arc Arc a ajouter
     */
    public void addOutputArc(Arc arc) {
    	this.listOfOutputArc.addElement(arc);
    }


    /**
     * Ajoute un attribut au noeud 
     * @param attribute Attribut a ajouter
     */
    public void addAttribute(Attribute attribute) {
        if (id == attribute.getRefId()) {
            this.listOfAttr.addElement(attribute);
        } 
    }

 
    /**
     * Retourne le nombre d'attributs du noeud
     * @return int
     */
    public int getListOfAttrSize() {
        return this.listOfAttr.size();
    }
    
    /**
     * Retourne le nieme attribut de la liste
     * @param index Index de l'attribut
     * @return Attribute
     */
    public Attribute getNthAttr(int index) {
        try {
            return (Attribute) this.listOfAttr.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
        	return null;
        }
    }

    /**
     * Traduction du noeud noeud en chaine de commandes CAMI
     * @return String[]
     */
    public String[] translateToCAMI() {
        String[] stringToReturn;
        StringBuffer s;
        Vector<String> vectorStringToReturn = new Vector<String>(0);
        
        s = new StringBuffer();
        s.append("CN(");
        s.append(this.nodeType.length() + ":" + this.nodeType);
        s.append(",");
        s.append(id);
        s.append(")");
        vectorStringToReturn.addElement(s.toString());
        
        s = new StringBuffer();
        s.append("PO(");
        s.append(id);
        s.append(",");
        s.append(xPosition);
        s.append(",");
        s.append(yPosition);
        s.append(")");
        vectorStringToReturn.addElement(s.toString());
        
        for (int i = 0; i < this.getListOfAttrSize(); i++) {
            vectorStringToReturn.addAll(Arrays.asList(this.getNthAttr(i).translateToCAMI()));
        }
        
        stringToReturn = new String[vectorStringToReturn.size()];
        vectorStringToReturn.toArray(stringToReturn);
        return stringToReturn;
    }
}

