package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.model.interfaces.ILocatedElement;
import fr.lip6.move.coloane.core.ui.commands.LocatedElementSetConstraintCmd;
import fr.lip6.move.coloane.core.ui.properties.LabelText;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.model.INodeGraphicInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
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

		/** {@inheritDoc} */
		public final void modifyText(ModifyEvent e) {
			Spinner spinner = (Spinner) e.widget;

			// Calcul du delta qui sera appliqué à tous les élements sélectionnés
//			Point ref = getElements().get(0).getGraphicInfo().getLocation();
			int[] location = new int[2];
//			location[0] = ref.x;
//			location[1] = ref.y;
//			int delta = spinner.getSelection() - location[i];

			CompoundCommand cc = new CompoundCommand();
			for (INode node : getElements()) {
				INodeGraphicInfo graphicInfo = node.getGraphicInfo();
				location[0] = graphicInfo.getLocation().x;
				location[1] = graphicInfo.getLocation().y;
				if (spinner.getSelection() != location[i]) {
					location[i] = spinner.getSelection();
					cc.add(new LocatedElementSetConstraintCmd(
							(ILocatedElement) node,
							new Rectangle(new Point(location[0], location[1]), graphicInfo.getSize()))
					);
				}
			}
			if (cc.size() > 0) {
				getCommandStack().execute(cc);
			}
		}
	}


	/** {@inheritDoc} */
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

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		if (!isDisposed()) {
			Point location = getElements().get(0).getGraphicInfo().getLocation();
			x.setSelection(location.x);
			x.layout();
			y.setSelection(location.y);
			y.layout();
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void propertyChange(PropertyChangeEvent evt) {
		if (ILocationInfo.LOCATION_PROP.equals(evt.getPropertyName())) {
			refresh();
		}
	}
}
