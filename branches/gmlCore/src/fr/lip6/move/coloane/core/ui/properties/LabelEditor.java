package fr.lip6.move.coloane.core.ui.properties;

import com.google.inject.Injector;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipselabs.xtfo.demo.rcp.partialEditing.EmbeddedXtextEditor;

public class LabelEditor implements IAttributeLabel {
	/** Nombre de ligne à afficher pour les Text multi-lignes */
	public static final int MAX_TEXT_HEIGHT = 10;

	private EmbeddedXtextEditor text;
	private CLabel label;
	private Composite parent;
	private ScrolledComposite sc;
	private int nbDelimiters = -1;

	// listener pour modifier la taille des champs de texte multiligne
	private ModifyListener listener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			redraw();
		}
	};
	
	/**
	 * Constructeur de LabelText, il est appelé par tous les constructeur public
	 * @param parent Composite parent
	 * @param factory factory utilisé pour la création des widget
	 * @param label label
	 * @param value valeur
	 * @param style style SWT
	 * @param top indicateur de positionnement utilisé par le FormLayout
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

		text.getViewer().getTextWidget().addModifyListener(listener);
		text.getViewer().getTextWidget().getVerticalBar().setVisible(true);

		redraw();
	}

	/**
	 * @param parent Composite parent
	 * @param factory factory utilisé pour la création des widget
	 * @param label label
	 * @param value valeur
	 * @param style style SWT
	 */
	public LabelEditor(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, Injector injector) {
		this(parent, factory, label, value, new FormAttachment(0, 0), injector);
	}

	/**
	 * @param parent Composite parent
	 * @param factory factory utilisé pour la création des widget
	 * @param label label
	 * @param value valeur
	 * @param style style SWT
	 * @param top indicateur de positionnement utilisé par le FormLayout
	 */
	public LabelEditor(Composite parent, TabbedPropertySheetWidgetFactory factory, String label, String value, Injector injector, IAttributeLabel top) {
		this(parent, factory, label, value, new FormAttachment(top.getControl(), 0), injector);
	}

	/** {@inheritDoc} */
	public final void redraw() {		// En cas de texte multiligne, on limite l'agrandissement
		
		int newNbDelimiters = this.getText().split("["+Text.DELIMITER+"\n]", -1).length;  //$NON-NLS-1$//$NON-NLS-2$
		if (newNbDelimiters != nbDelimiters) {
			nbDelimiters = newNbDelimiters;
			int height = text.getViewer().getTextWidget().getLineHeight()*MAX_TEXT_HEIGHT;
			height += text.getViewer().getTextWidget().getTopMargin()+text.getViewer().getTextWidget().getBottomMargin();

			height = text.getViewer().getTextWidget().computeTrim(0, 0, 0, height).height;

			((FormData) text.getViewer().getControl().getLayoutData()).height = nbDelimiters > MAX_TEXT_HEIGHT ? height : text.getViewer().getTextWidget().computeSize(SWT.DEFAULT, SWT.DEFAULT).y ;
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
		for (int i = 0; i < 20 && tmp != null; i++) {
			tmp.layout();
			tmp.redraw();
			tmp = tmp.getParent();
		}
	}

	/** {@inheritDoc} */
	public final boolean isVisible() {
		Assert.isTrue(text.getControl().isVisible() == label.isVisible());
		return text.getControl().isVisible();
	}

	/** {@inheritDoc} */
	public final void setVisible(boolean visible) {
		text.getControl().setVisible(visible);
		label.setVisible(visible);
		text.getViewer().getTextWidget().redraw();
		label.redraw();
	}

	/** {@inheritDoc} */
	public final String getText() {
		String val = text.getDocument().get();
		return val;
	}

	/** {@inheritDoc} */
	public final void setText(String string) {
		text.update(string);
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getLabel() {
		return label.getText();
	}

	/** {@inheritDoc} */
	public final Composite getParent() {
		return parent;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Control getControl() {
		return text.getViewer().getControl();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void addModifyListener(ModifyListener listener) {
		text.getViewer().getTextWidget().addModifyListener(listener);
	}

	public Control getControlText() {
		return text.getViewer().getTextWidget();
	}

}
