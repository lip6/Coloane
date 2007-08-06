package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;


/**
 * Cette classe defini la listes des reslutats renvoyes par la plate-forme
 * a la suite d'un appel de service. Ces resultats doivent etre envoyes a Coloane
 * pour affichage.
 *
 */

public abstract class ResultsCom implements IResultsCom {

	/** La liste des descriptions de resultats */
	private Vector<String> description;
	/** La liste des resultats */
	private Vector<String> elements;

	/**
	 * Constructeur
	 */
	public ResultsCom() {
		this.description = new Vector<String>();
		this.elements = new Vector<String>();
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultatsCom#addElement(String)
	 */
	public final void addElement(String element) {
		this.elements.add(element);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultatsCom#addDescription(String)
	 */
	public final void addDescription(String resultDescription) {
		this.description.add(resultDescription);
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultatsCom#getListOfElement()
	 */
	public final Vector<String> getListOfElement() {
		return this.elements;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultatsCom#getListOfDescription()
	 */
	public final Vector<String> getListOfDescription() {
		return this.description;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultatsCom#getSublistOfDescription(int)
	 */
	public final Vector<String> getSublistOfDescription(int start) {
		Vector<String> tmp = new Vector<String>();
		for (int i = start; i < this.description.size(); i++) {
			tmp.add(this.description.elementAt(i));
		}
		return tmp;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultatsCom#getHeadDescription()
	 */
	public final String getHeadDescription() {
		return this.description.elementAt(0);
	}

}
