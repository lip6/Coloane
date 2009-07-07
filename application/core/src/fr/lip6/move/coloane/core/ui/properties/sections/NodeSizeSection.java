package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.commands.properties.NodeChangeSizeCmd;
import fr.lip6.move.coloane.core.ui.properties.LabelText;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Section qui permet de modifier la taille d'un noeud.
 * La taille d'un noeud est exprimé en pourcentage de la taille d'origine spécifié
 * dans le formalisme.
 */
public class NodeSizeSection extends AbstractSection<INode> {
	private static final int INCREMENT_VALUE = 10;
	private static final int MAX_VALUE = 400;
	private static final int MIN_VALUE = 80;

	/** Widget qui permet de modifier la taille d'un noeud */
	private Spinner size;

	/** Permet de mettre à jour le modèle du noeud */
	private ModifyListener listener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			CompoundCommand cc = new CompoundCommand();
			for (INode node : getElements()) {
				if (size.getSelection() != node.getGraphicInfo().getScale()) {
					cc.add(new NodeChangeSizeCmd(node, size.getSelection()));
				}
			}
			if (cc.size() > 0) {
				getCommandStack().execute(cc);
			}
		}
	};

	/** {@inheritDoc} */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;

		// Widget pour modifier la taille du noeud
		size = new Spinner(composite, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH + 7);
		size.setLayoutData(data);
		size.setMinimum(MIN_VALUE);
		size.setMaximum(MAX_VALUE);
		size.setIncrement(INCREMENT_VALUE);
		size.addModifyListener(listener);

		// Etiquette
		CLabel label = getWidgetFactory().createCLabel(composite, Messages.NodeSizeSection_0 + " :"); //$NON-NLS-1$
		data = new FormData();
		data.bottom = new FormAttachment(size, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);
	}

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			size.setSelection(getElements().get(0).getGraphicInfo().getScale());
			size.layout();
		}
	}

	/** {@inheritDoc} */
	public final void propertyChange(PropertyChangeEvent evt) {
		if (INode.RESIZE_PROP.equals(evt.getPropertyName())) {
			refresh();
		}
	}
}
