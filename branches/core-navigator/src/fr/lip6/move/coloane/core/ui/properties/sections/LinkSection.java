package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler;
import fr.lip6.move.coloane.core.ui.properties.LabelText;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.beans.PropertyChangeEvent;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Section in property view to edit link between the current selected node and a public node.
 *
 * @author Clément Démoulins
 */
public class LinkSection extends AbstractSection<INode> {

	private static final String NONE = "none";

	private List listWidget;

	private SelectionListener linkSelectionListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) { }

		public void widgetSelected(SelectionEvent e) {
			System.err.println(((List) e.widget).getSelection()[0]);
		}
	};

	/** {@inheritDoc} */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;

		listWidget = getWidgetFactory().createList(composite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH + 7);
		listWidget.setLayoutData(data);
		listWidget.addSelectionListener(linkSelectionListener);

		// Etiquette
		CLabel label = getWidgetFactory().createCLabel(composite, "Elements :"); //$NON-NLS-1$
		data = new FormData();
		data.top = new FormAttachment(listWidget, 0, SWT.TOP);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, LabelText.LABEL_WIDTH);
		label.setLayoutData(data);
	}

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		listWidget.removeAll();
		listWidget.add(NONE);

		ColoaneEditor coloaneEditor = (ColoaneEditor) getPart();
		IFile currentModel = ((FileEditorInput) coloaneEditor.getEditorInput()).getFile();

		INodeFormalism nodeFormalism = getElements().get(0).getNodeFormalism();
		for (INode node : getElements()) {
			if (!nodeFormalism.getName().equals(node.getNodeFormalism().getName())) {
				listWidget.pack();
				listWidget.redraw();
				return;
			}
		}

		try {
			for (IResource resource : currentModel.getParent().members()) {
				if (resource.getName().endsWith("model") && resource instanceof IFile) { //$NON-NLS-1$
					IFile file = (IFile) resource;
					for (PublicNodeHandler.PublicNode publicNode : ModelLoader.loadFromXML(file, new PublicNodeHandler(nodeFormalism)).getPublicNodes()) {
						listWidget.add(file.getFullPath() + " - " + publicNode.toString());
					}
				}
			}
			listWidget.pack();
			listWidget.redraw();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	public void propertyChange(PropertyChangeEvent evt) { }
}
