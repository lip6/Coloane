package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Classe abstraite pour une zone de texte d'une boite de dialogue
 */
public abstract class TextArea implements ITextArea {
	private String defaultValue;
	private Control textWidget;

	/**
	 * Constructeur
	 * @param parent La boite de dialogue en cours de construction
	 * @param inputType Le type de saisie
	 * @param multiLine Indicateur de saisie multiligne
	 * @param defaultValue Valeur par default de la zone texte
	 */
	public TextArea(Composite parent, int inputType, int multiLine, String defaultValue) {
		this.defaultValue = defaultValue;
		this.textWidget = null;
	}

	/** {@inheritDoc} */
	public abstract List<String> getText();

	/** {@inheritDoc} */
	public final String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @return le widget de cette zone de texte
	 */
	public final Control getTextWidget() {
		return textWidget;
	}

	/**
	 * @param widget nouveau widget pour cette zone de texte
	 */
	public final void setTextWidget(Control widget) {
		this.textWidget = widget;
	}
}
