package fr.lip6.move.coloane.interfaces.model;

import java.util.Vector;
import java.io.Serializable;

/**
 * Cette classe represente un attribut generique d'un modele. <br>
 * Un attribut se structure de la maniere suivante :
 * <ul>
 * 	<li> Nom de l'attribut sous forme de chaine de caracteres
 * 	<li> La valeur de l'attribut (ensemble de chaine de caracteres java  representant les differentes lignes)
 * 	<li> Abscisse et ordonnee de l'attribut
 * 	<li> Identifiant de l'element d'un modele auquel il est attache
 * </ul>
 * 
 */
public abstract class Attribute implements IAttribute, Serializable {

    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Nom de l'attribut sous forme de chaine de caracteres. */
    protected String name;

    /** Valeur de l'attribut. Cette valeur peut etre de n'importe qu'elle type de String java. */
    protected Vector<String> value;

    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    protected int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    protected int yPosition;

    /** Identifiant unique de l'element du modele possedant cette attribut. */
    protected int refId;

    /**
     * Constructeur de la classe Attribute.
     * 
     * @param name le nom de l'attribut
     * @param value la valeur de l'attribut
     * @param refId l'id de l'objet possedant cette attribut.
     */
    public Attribute(String name, String[] value, int refId) {
    	this.name = name;
    	this.value = new Vector<String>();
    	this.refId = refId;
    	this.xPosition = 0;
    	this.yPosition = 0;
    	
    	if (value.length > 0) {
    		for (int i = 0; i < value.length; i++) {
    			this.value.addElement(value[i]);
    		}	
        } else {
        	this.value.addElement(new String(""));
        }
    }	

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getName()
	 */
    public String getName() {
        return this.name;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#setPosition(int, int)
	 */
    public void setPosition(int x, int y) {
    	this.xPosition = x;
    	this.yPosition = y;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getXPosition()
	 */
    public int getXPosition() {
        return this.xPosition;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getYPosition()
	 */
    public int getYPosition() {
        return this.yPosition;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getRefId()
	 */
    public int getRefId() {
        return this.refId;
    }
    
    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#setRefId(int)
	 */
    public void setRefId(int ref) {
    	this.refId = ref;
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#setValue(java.lang.String)
	 */
    public void setValue(String value) {
    	this.value.set(0, value);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#setValue(java.lang.String, int)
	 */
    public void setValue(String value, int numLine) throws Exception {
        if (numLine < this.value.size()) {
            this.value.set(numLine, value);
        } else {
            throw new Exception("Numero de ligne hors limite");
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#riseNbLine(int)
	 */
    public void riseNbLine(int nb) throws Exception {
        if (nb >= 0) {
            this.value.setSize(this.value.size() + nb);
        } else {
            throw new Exception("Le nombre de lignes a ajouter doit tre positif");
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#removeLine(int)
	 */
    public void removeLine(int line) {
        if (this.value.size() > 1) {
            this.value.remove(line);
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getValue()
	 */
    public String getValue() {
        return (String) this.value.get(0);
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getValue(int)
	 */
    public String getValue(int line) {
        try {
            return (String) this.value.get(line);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getSize()
	 */
    public int getSize() {
        return this.value.size();
    }

    /* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#translateToCAMI()
	 */
    public abstract String[] translate();
}
