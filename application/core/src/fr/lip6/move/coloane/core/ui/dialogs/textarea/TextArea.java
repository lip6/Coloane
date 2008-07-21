package fr.lip6.move.coloane.core.ui.dialogs.textarea;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class TextArea implements ITextArea {

	public static final int INPUT_AUTHORIZED = 1;
	public static final int INPUT_FORBIDDEN = 2;
	public static final int INPUT_AND_ABORT_AUTHORIZED = 5;

	public static final int SINGLE_LINE = 1;
	public static final int MULTI_LINE_WITH_SINGLE_SELECTION = 2;
	public static final int MULTI_LINE_WITH_MULTI_SELECTION = 5;

	private String defaultValue;
	private Control textWidget;

	/**
	 * Constructeur
	 * @param parent La boite de dialogue en cours de construction
	 * @param inputType Le type de saisie
	 * @param multiLine Indicateur de saisie multiligne
	 * @param defaultValue Valeur par default de la zone texte
	 */
	public TextArea(Composite parent, int inputType, int multiLine, String defaultVal) {
		this.defaultValue = defaultVal;
		this.textWidget = null;
	}

	/** {@inheritDoc} */
	public abstract ArrayList<String> getText();

	/** {@inheritDoc} */
	public final String getDefaultValue() {
		return defaultValue;
	}

	public final Control getTextWidget() {
		return textWidget;
	}

	public final void setTextWidget(Control widget) {
		this.textWidget = widget;
	}
}
