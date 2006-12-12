package fr.lip6.move.coloane.communications.models;

import java.util.Vector;
import java.io.Serializable;

/**
 * Classe representant un attribut d'un modele. Un attribut se structure de la maniere suivante :
 * <ul>
 * 	<li> Nom de l'attribut sous forme de chaine de caracteres
 * 	<li> La valeur de l'attribut (ensemble de chaine de caracteres java quelconque representant les differents lignes)
 * 	<li> Abscisse et ordonnee de l'attribut
 * 	<li> Identifiant de l'element d'un modele dont il est affecte
 * </ul>
 * 
 */
public class Attribute implements Serializable {

    /** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
    private static final long serialVersionUID = 1L;

    /** Nom de l'attribut sous forme de chaine de caracteres. */
    private String name;

    /** Valeur de l'attribut. Cette valeur peut etre de n'importe qu'elle type de String java. */
    private Vector<String> value;

    /** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
    private int xPosition;

    /** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
    private int yPosition;

    /** Identifiant unique de l'element du modele possedant cette attribut. */
    private int refId;

    /**
     * Constructeur de la classe Attribute.
     * 
     * @param name le nom de l'attribut
     * @param value la valeur de l'attribut
     * @param refId l'id de l'objet possedant cette attribut.
     */
    public Attribute(String name, String[] value, int refId)	{
    	this.name = name;
    	this.value = new Vector<String>();
    	this.refId = refId;
    	this.xPosition = 0;
    	this.yPosition = 0;
    	
    	if (value.length == 0) {
    		this.value.addElement(new String(""));
        } else {
    		for (int i = 0; i < value.length; i++) {
    			this.value.addElement(value[i]);
    		}	
        }	
    }	

    /**
     * Retourne le nom de l'attribut.
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Cette methode permet de fixer les coordonnees spatiales de l'attribut
     * @param x est la valeur de la coordonee x
     * @param y est la valeur de la coordonee y
     */
    public void setPosition(int x, int y) {
    	this.xPosition = x;
    	this.yPosition = y;
    }

    /**
     * Retourne l'abscisse de l'attribut.
     * @return int
     */
    public int getXPosition() {
        return this.xPosition;
    }

    /**
     * Retourne l'ordonnee de l'attribut.
     * @return la coordonnee y de l'attribut
     */
    public int getYPosition() {
        return this.yPosition;
    }

    /**
     * Retourne l'identifiant du noeud parent de cet attribut 
     * @return int
     */
    public int getRefId() {
        return this.refId;
    }

    /**
     * Cette methode modifie la valeur de la premiere ligne de l'attribut.
     * @param value Valeur de l'attribut
     */
    public void setValue(String value) {
    	this.value.set(0, value);
    }

    /**
     * Mdifie la valeur de la ligne line de l'attribut.
     * @param value la valeur de l'attribut
     * @param numLine la ligne de la valeur de l'attribut
     * @throws Exception si ce numuro de ligne n'existe pas
     */
    public void setValue(String value, int numLine) throws Exception {
        if (numLine < this.value.size()) {
            this.value.set(numLine, value);
        } else {
            throw new Exception("Numero de ligne hors limite");
        }
    }

    /**
     * Augmente le nombre de lignes de la valeur de l'attribut.
     * @param nb Nombre de lignes a rajouter a la valeur de l'attribut
     * @throws Exception si le nombre a ajouter est negatif
     */
    public void riseNbLine(int nb) throws Exception {
        if (nb >= 0) {
            this.value.setSize(this.value.size() + nb);
        } else {
            throw new Exception("Le nombre de ligne a ajouter doit tre positif");
        }
    }

    /**
     * Cette methode retire la ligne line de la valeur de l'attribut. 
     * La derniere ligne ne peut pas etre retiree.
     * @param line Ligne a retirer
     */
    public void removeLine(int line) {
        if (this.value.size() > 1) {
            this.value.remove(line);
        }
    }

    /**
     * Retourne la valeur de la premiere ligne de l'attribut.
     * @return String
     */
    public String getValue() {
        return (String) this.value.get(0);
    }

    /**
     * Retourne la valeur de la ligne line de l'attribut.
     * @param line la ligne de la valeur de l'attribut (0 pour la 1er ligne)
     * @return String
     */
    public String getValue(int line) {
        try {
            return (String) this.value.get(line);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Retourne le nombre de lignes de l'attribut.
     * @return int
     */
    public int getSize() {
        return this.value.size();
    }

    /**
     * Traduit un objet Attribute en la chaine de caracteres CAMI correspondante.
     * @return String[]
     */
    public String[] translateToCAMI() {
        StringBuffer s;
        String[] stringToReturn = null;
        
        Vector<String> vectorStringToReturn = new Vector<String>();
        
        if (this.getSize() == 1) {
        	if (this.value != null) {
        		if (!this.getValue(0).equals("")) {
        			s = new StringBuffer();
        			s.append("CT(");
        			s.append(this.name.length() + ":" + this.name);
        			s.append(",");
        			s.append(this.refId);
        			s.append(",");
        			s.append(this.getValue(0).length() + ":" + this.getValue(0));
        			s.append(")");
        			vectorStringToReturn.addElement(s.toString());
        		} else {
        			vectorStringToReturn.addElement("");
        		}	
        	} else {
        		vectorStringToReturn.addElement(new String(""));
        	}
        } else {	
        	if (this.getSize() > 1) {
        	
        		for (int i = 0; i < this.getSize(); i++) {
        			if (!this.getValue(i).equals("")) {        				
        				s = new StringBuffer();
        				s.append("CM(");
        				s.append(this.name.length() + ":" + this.name);
        				s.append(",");
        				s.append(this.refId);
        				s.append(",");
        				s.append(i + 1);
        				s.append(",");
        				s.append(1); //archaisme de Framekit
        				s.append(",");
        				s.append(this.getValue(i).length() + ":" + this.getValue(i));
        				s.append(")");
        				vectorStringToReturn.addElement(s.toString());
        			}
        		}
        	}
        }	
        
        if (this.xPosition != 0 || this.yPosition != 0) { 
        	s = new StringBuffer();
        	s.append("PT(");
        	s.append(this.refId);
        	s.append(",");
        	s.append(this.name.length() + ":" + this.name);
        	s.append(",");
        	s.append(this.xPosition);
        	s.append(",");
        	s.append(this.yPosition);
        	s.append(")");
        	vectorStringToReturn.addElement(s.toString());
        }
        
        stringToReturn = new String[vectorStringToReturn.size()];
        vectorStringToReturn.toArray(stringToReturn);
         return stringToReturn;
    }
}
