package fr.lip6.move.coloane.api.model;

import java.util.Vector;
import java.io.Serializable;

/**
 * Enrichissement de la definition d'un attribut generique par sa traduction en CAMI
 * @see fr.lip6.move.coloane.interfaces.model.Attribute
 */
public class Attribute extends fr.lip6.move.coloane.interfaces.model.Attribute implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Constructeur */
	public Attribute(String name, String value, int refId) {
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
		String [] tab_val = null;
		String val=this.getValue();
		Vector<String> vectorStringToReturn = new Vector<String>();


		tab_val=val.split("(\n\r)|(\r\n)|(\n)|(\r)");

		//  if (val.equals("")){vectorStringToReturn.addElement(new String(""));}

		if(tab_val.length==1 && tab_val[0].length()<=255){

			if (!val.equals("")){
				s = new StringBuffer();
				s.append("CT(");
				s.append(this.name.length() + ":" + this.name);
				s.append(",");
				s.append(this.refId);
				s.append(",");
				s.append(tab_val[0].length() + ":" + tab_val[0] );
				s.append(")");
				vectorStringToReturn.addElement(s.toString());
			}


		} else {
			int cpt_lig=1; //compteur ligne utile

			for(int i=0;i<tab_val.length;i++){

				if (tab_val[i].length()<255) {   				
					s = new StringBuffer();
					s.append("CM(");
					s.append(this.name.length() + ":" + this.name);
					s.append(",");
					s.append(this.refId);
					s.append(",");
					s.append(cpt_lig++);
					s.append(",");
					s.append(1); //archaisme de Framekit
					s.append(",");
					s.append(tab_val[i].length() + ":" + tab_val[i]);
					s.append(")");
					vectorStringToReturn.addElement(s.toString());
				}else{
					int start=0;
					int end=255;


					while(end<tab_val[i].length()){

						String sub=tab_val[i].substring(start,end);

						s = new StringBuffer();
						s.append("CM(");
						s.append(this.name.length() + ":" + this.name);
						s.append(",");
						s.append(this.refId);
						s.append(",");
						s.append(cpt_lig++);
						s.append(",");
						s.append(1); //archaisme de Framekit
						s.append(",");
						s.append(sub.length() + ":" + sub);
						s.append(")");
						vectorStringToReturn.addElement(s.toString());

						start+=255;
						end+=255;
					}

					String sub=tab_val[i].substring(start,tab_val[i].length());
					s = new StringBuffer();
					s.append("CM(");
					s.append(this.name.length() + ":" + this.name);
					s.append(",");
					s.append(this.refId);
					s.append(",");
					s.append(cpt_lig++);
					s.append(",");
					s.append(1); //archaisme de Framekit
					s.append(",");
					s.append(sub.length() + ":" + sub);
					s.append(")");
					vectorStringToReturn.addElement(s.toString());
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
		}
		stringToReturn = new String[vectorStringToReturn.size()];
		vectorStringToReturn.toArray(stringToReturn);
		return stringToReturn;
	}
}
