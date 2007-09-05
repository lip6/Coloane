package fr.lip6.move.coloane.ui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * Descripteur associe a l'edition d'un attribut
 */
public class AttributePropertyDescriptor extends PropertyDescriptor {

	/** Le titre de la boite de dialogue */
	private String title;

	/** Le texte d'aide associe a l'editeur */
	private String help;

	/** Le texte par defaut */
	private String byDefault;


	/**
	 * Constructeur
	 * @param id
	 * @param displayName Le nom de la boite de dialogue
	 * @param editorHelp Le message d'aide
	 * @param editorDefault Le texte par defaut
	 */
	public AttributePropertyDescriptor(Object id, String displayName, String editorHelp, String editorDefault) {
		super(id, displayName);
		this.title = displayName;
		this.help = editorHelp;
		this.byDefault = editorDefault;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.PropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public final CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new AttributeCellEditor(parent, this.title, this.help, this.byDefault);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}
}
