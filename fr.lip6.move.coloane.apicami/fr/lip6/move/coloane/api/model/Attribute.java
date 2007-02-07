package fr.lip6.move.coloane.api.model;

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
public class Attribute extends fr.lip6.move.coloane.interfaces.model.Attribute implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructeur */
	public Attribute(String name, String[] value, int refId) {
		super(name, value, refId);
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Traduit un objet Attribute en la chaine de caracteres CAMI correspondante.
     * @return String[]
     */
    public String[] translate() {
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
