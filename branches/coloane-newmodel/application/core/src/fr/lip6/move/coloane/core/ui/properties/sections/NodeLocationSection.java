package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.commands.NodeSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.model.interfaces.INode;
import fr.lip6.move.coloane.core.ui.model.interfaces.INodeGraphicInfo;
import fr.lip6.move.coloane.core.ui.properties.LabelText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
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
 * Section qui permet de gérer la position d'un noeud.
 */
public class NodeLocationSection extends AbstractSection<INode> implements PropertyChangeListener {
	/** Widgets qui permettent de modifier les coordonnées du noeud. */
	private Spinner x, y;

	/**
	 * Permet de mettre à jour le modèle du noeud.
	 */
	class SpinnerModifyListener implements ModifyListener {
		private int i;
		/**
		 * @param i 0 pour modifier x, 1 pour modifier y.
		 */
		public SpinnerModifyListener(int i) {
			this.i = i % 2; // pour être sur qu'on a 0 ou 1.
		}

		/* (non-Javadoc)
		 * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
		 */
		public final void modifyText(ModifyEvent e) {
			Spinner spinner = (Spinner) e.widget;
			INodeGraphicInfo graphicInfo = getElement().getGraphicInfo();
			int[] location = new int[2];
			location[0] = graphicInfo.getLocation().x;
			location[1] = graphicInfo.getLocation().y;
			if (spinner.getSelection() != location[i]) {
				location[i] = spinner.getSelection();
				getCommandStack().execute(new NodeSetConstraintCmd(
						getElement(),
						new Rectangle(new Point(location[0], location[1]), getElement().getGraphicInfo().getSize()))
				);
			}
		}
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;

		// Widget pour modifier la coordonnée x
		x = new Spinner(composite, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH + 7);
		x.setLayoutData(data);
		x.setMinimum(0);
		x.setMaximum(Integer.MAX_VALUE);
		x.addModifyListener(new SpinnerModifyListener(0));

		// Widget pour modifier la coordonnée y
		y = new Spinner(composite, SWT.BORDER);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(x, 5);
		y.setLayoutData(data);
		y.setMinimum(0);
		y.setMaximum(Integer.MAX_VALUE);
		y.addModifyListener(new SpinnerModifyListener(1));

		// Etiquette
		CLabel label = getWidgetFactory().createCLabel(composite, Messages.NodeLocationSection_0 + " :"); //$NON-NLS-1$
		data = new FormData();
		data.bottom = new FormAttachment(x, 0, SWT.BOTTOM);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			Point location = getElement().getGraphicInfo().getLocation();
			x.setSelection(location.x);
			x.layout();
			y.setSelection(location.y);
			y.layout();
		}
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public final void propertyChange(PropertyChangeEvent evt) {
		if (INode.LOCATION_PROP.equals(evt.getPropertyName())) {
			refresh();
		}
	}
}
