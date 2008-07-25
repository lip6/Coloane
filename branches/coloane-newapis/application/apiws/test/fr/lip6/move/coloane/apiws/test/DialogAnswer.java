package fr.lip6.move.coloane.apiws.test;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

import java.util.List;

/**
 * Cette classe definie une boîte de dialogue réponse.
 */
public class DialogAnswer implements IDialogAnswer {

	private int buttonType;

	private int idDialg;

	private List<Integer> objects;

	private String value;

	private boolean modified;

	/**
	 * Constructeur
	 * @param idDialg l'identifiant de la boîte de dialogue à laquelle on répond
	 * @param buttonType le type du boutton choisi
	 * @param modified si la boîte de dialogue est modifié
	 * @param value la valeur saisie
	 * @param objects les objets
	 */
	public DialogAnswer(int idDialg, int buttonType, boolean modified, String value, List<Integer> objects) {
		this.buttonType = buttonType;
		this.idDialg = idDialg;
		this.objects = objects;
		this.value = value;
		this.modified = modified;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getButtonType() {
		return buttonType;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getIdDialog() {
		return idDialg;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<Integer> getObjects() {
		return objects;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isModified() {
		return modified;
	}

}
