package fr.lip6.move.coloane.core.ui.properties;

import com.google.inject.Injector;

import fr.lip6.move.coloane.core.ui.properties.editor.EmbeddedXtextEditor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * 
 */
public class LabelEditor implements IAttributeLabel {
	/** Nombre de ligne à afficher pour les Text multi-lignes */
	public static final int MAX_TEXT_HEIGHT = 10;

	private EmbeddedXtextEditor text;
	private CLabel label;
	private Composite parent;
	private ScrolledComposite sc;
	private int nbDelimiters = -1;
	
	/**
	 * Constructeur de LabelText, il est appelé par tous les constructeur public
	 * @param parent Composite parent
	 * @param factory factory utilisé pour la création des widget
	 * @param label label
	 * @param value valeur
	 * @param top indicateur de positionnement utilisé par le FormLayout
	 * @param injector the injector to provide quick fix, syntax highlighting etc.
	 */
	private LabelEditor(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, FormAttachment top, Injector injector) {
		FormData data;
		this.parent = parent;

		this.label = factory.createCLabel(parent, label);
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LABEL_WIDTH);
		data.top = top;
		this.label.setLayoutData(data);

		text = new EmbeddedXtextEditor(parent, injector);
		data = new FormData();
		data.left = new FormAttachment(this.label, 5);
		data.right = new FormAttachment(100, -5);
		data.top = top;
		text.getViewer().getControl().setLayoutData(data);

		text.getViewer().addTextListener(
				new ITextListener() {
					@Override
					public void textChanged(TextEvent event) {
						redraw();
					} });

		text.getViewer().getTextWidget().getVerticalBar().setVisible(true);

		redraw();
	}

	/**
	 * @param parent Composite parent
	 * @param factory factory utilisé pour la création des widget
	 * @param label label
	 * @param value valeur
	 * @param injector the injector to provide quick fix, syntax highlighting etc.
	 */
	public LabelEditor(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, Injector injector) {
		this(parent, factory, label, value, new FormAttachment(0, 0), injector);
	}

	/**
	 * @param parent Composite parent
	 * @param factory factory utilisé pour la création des widget
	 * @param label label
	 * @param value valeur
	 * @param top indicateur de positionnement utilisé par le FormLayout
	 * @param injector the injector to provide quick fix, syntax highlighting etc.
	 */
	public LabelEditor(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, Injector injector, IAttributeLabel top) {
		this(parent, factory, label, value, new FormAttachment(top.getControl(), 0), injector);
	}

	/** {@inheritDoc} */
	@Override
	public final void redraw() {

		// En cas de texte multiligne, on limite l'agrandissement
		int newNbDelimiters = text.getViewer().getTextWidget().getText().split("[" + Text.DELIMITER + "\n]", -1).length;  //$NON-NLS-1$//$NON-NLS-2$
		if (newNbDelimiters != nbDelimiters) {
			nbDelimiters = newNbDelimiters;
			int height = text.getViewer().getTextWidget().getLineHeight() * MAX_TEXT_HEIGHT;
			int height2 = text.getViewer().getTextWidget().getLineHeight() * (nbDelimiters);
			height += text.getViewer().getTextWidget().getTopMargin() + text.getViewer().getTextWidget().getBottomMargin();
			height2 += text.getViewer().getTextWidget().getTopMargin() + text.getViewer().getTextWidget().getBottomMargin();
			height = text.getViewer().getTextWidget().computeTrim(0, 0, 0, height).height;
			height2 = text.getViewer().getTextWidget().computeTrim(0, 0, 0, height2).height;

			if (nbDelimiters > MAX_TEXT_HEIGHT) {
				((FormData) text.getViewer().getControl().getLayoutData()).height = height;
			} else {
				((FormData) text.getViewer().getControl().getLayoutData()).height = height2;
			}

		}
		// Récupération du ScrolledComposite
		if (sc == null) {
			Composite tmp = parent;
			while (!(tmp instanceof ScrolledComposite)) {
				tmp = tmp.getParent();
			}
			sc = (ScrolledComposite) tmp;
		}
		sc.setMinSize(parent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		Composite tmp = parent;
		Composite tmp2 = parent;
		for (int i = 0; i < 3 && tmp != null; i++) {
			tmp.layout();
			tmp.redraw();
			tmp2 = tmp;
			tmp = tmp.getParent();
		}

		tmp2.update();

	}

	/** {@inheritDoc} */
	@Override
	public final boolean isVisible() {
		Assert.isTrue(text.getControl().isVisible() == label.isVisible());
		return text.getControl().isVisible();
	}

	/** {@inheritDoc} */
	@Override
	public final void setVisible(boolean visible) {
		text.getControl().setVisible(visible);
		label.setVisible(visible);
		text.getViewer().getTextWidget().redraw();
		label.redraw();
	}

	/** {@inheritDoc} */
	@Override
	public final String getText() {
		String val = text.getDocument().get();
		return val;
	}

	/** {@inheritDoc} */
	@Override
	public final void setText(String string) {
		text.update(string);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getLabel() {
		return label.getText();
	}

	/** {@inheritDoc} */
	@Override
	public final Composite getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Control getControl() {
		return text.getViewer().getControl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addModifyListener(ModifyListener listener) {
		//if the listener is only hooked up to the text viewer, some changes are not caught
		//for example, autocompletion changes the document but does not raise a modify event on the viewer
		//(it would cause an infinite loop).
		//instead, we listen only to document changes:
		//autocompletion, quickfix etc modify the document, and modifications to the viewer by keyboard etc also modify
		//the document. perfect!
		text.getDocument().addDocumentListener(new AdaptModifyDocument(listener, text.getViewer().getTextWidget()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Control getControlText() {
		return text.getViewer().getTextWidget();
	}

}

/**
 *  Class to adapt a ModifyListener to a DocumentListener
 * @author Elodie Banel
 */
class AdaptModifyDocument implements IDocumentListener {
	
	private ModifyListener m;
	private Control c;
	
	/**
	 * Create a new AdaptModifyDocument
	 * @param m The modify listener to adapt.
	 * @param c The control that this event will come from.
	 */
	AdaptModifyDocument(ModifyListener m, Control c) {
		this.m = m;
		this.c = c;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void documentAboutToBeChanged(DocumentEvent event) {
		//nothing to be done here
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void documentChanged(DocumentEvent event) {
		//create a false modifyevent that gives access to the textviewer
		//it is the only thing used by the modifylistener function
		//.... if you want to use a different modifylistener function
		//change this :P
		Event e = new Event();
		e.widget = c;
		ModifyEvent mevent = new ModifyEvent(e);
		m.modifyText(mevent);
	}
	
}
