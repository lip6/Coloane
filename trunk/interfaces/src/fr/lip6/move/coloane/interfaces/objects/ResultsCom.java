package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

/**
 * Cette classe defini la listes des resultats renvoyes par la plate-forme a la
 * suite d'un appel de service. Ces resultats doivent etre envoyes a Coloane
 * pour etre affiches.
 */

public class ResultsCom implements IResultsCom {

	/** Commande RQ (reponse a une question) */
	private String question;

	/** Liste des commandes comprises entre DE et FE */
	private Vector<SousResultsCom> subResults;

	/**
	 * Constructeur
	 */
	public ResultsCom() {
		subResults = new Vector<SousResultsCom>();
		question = new String();
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultsCom#addResultats(fr.lip6.move.coloane.interfaces.objects.SousResultsCom)
	 */
	public final void addResultats(SousResultsCom sr) {
		subResults.add(sr);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultsCom#removeResultats(fr.lip6.move.coloane.interfaces.objects.SousResultsCom)
	 */
	public final void removeResultats(SousResultsCom sr) {
		subResults.remove(sr);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultsCom#getQuestion()
	 */
	public final String getQuestion() {
		return question;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultsCom#setcmdRQ(java.lang.String)
	 */
	public final void setcmdRQ(String newQuestion) {
		this.question = newQuestion;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.objects.IResultsCom#getSubResults()
	 */
	public final Vector<SousResultsCom> getSubResults() {
		return subResults;
	}

}
