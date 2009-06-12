package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.commands.properties.NodeLinkCmd;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler;
import fr.lip6.move.coloane.core.ui.files.PublicNodeHandler.PublicNode;
import fr.lip6.move.coloane.core.ui.properties.LabelText;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Section in property view to edit link between the current selected node and a public node.
 *
 * @author Clément Démoulins
 */
public class LinkSection extends AbstractSection<INode> {

	private static final String NONE = "none"; //$NON-NLS-1$

	private List listWidget;
	private Map<String, PublicNode> model = new HashMap<String, PublicNode>();

	/**
	 * Selection Listener, link change
	 */
	private SelectionListener linkSelectionListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) { }

		public void widgetSelected(SelectionEvent e) {
			CompoundCommand cc = new CompoundCommand();
			String newLink = ((List) e.widget).getSelection()[0];
			if (NONE.equals(newLink)) {
				newLink = null;
			}
			for (INode node : getElements()) {
				cc.add(new NodeLinkCmd(node, newLink));
			}
			getCommandStack().execute(cc);
		}
	};

	/**
	 * Resource listener, need to update the list of public node
	 */
	private IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
		/** Update the list of the public node */
		private void update() {
			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					refresh();
				}
			});
		}

		/** Looking for content changing in model */
		private void process(IResourceDelta ird) {
			if (ird.getFlags() == IResourceDelta.CONTENT) {
				update();
			}
			for (IResourceDelta child : ird.getAffectedChildren(IResourceDelta.CHANGED)) {
				process(child);
			}
		}

		public void resourceChanged(IResourceChangeEvent event) {
			if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
				System.err.println();
				process(event.getDelta());
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

		listWidget = getWidgetFactory().createList(composite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, LabelText.LABEL_WIDTH + 7);
		listWidget.setLayoutData(data);
		listWidget.addSelectionListener(linkSelectionListener);

		// Etiquette
		CLabel label = getWidgetFactory().createCLabel(composite, "Link to :"); //$NON-NLS-1$
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
		model.clear();
		listWidget.add(NONE);

		ColoaneEditor coloaneEditor = (ColoaneEditor) getPart();
		IFile currentModel = ((FileEditorInput) coloaneEditor.getEditorInput()).getFile();

		currentModel.getWorkspace().addResourceChangeListener(resourceChangeListener);

		// Test if all selected element use the same formalism.
		INodeFormalism nodeFormalism = getElements().get(0).getNodeFormalism();
		for (INode node : getElements()) {
			if (!nodeFormalism.getName().equals(node.getNodeFormalism().getName())) {
				listWidget.pack();
				listWidget.redraw();
				listWidget.update();
				return;
			}
		}

		// Find and display list of public node.
		try {
			int select = 0;
			if (getElements().size() != 1) {
				select = -1;
			}

			for (IResource resource : currentModel.getParent().members()) {
				if (resource.getName().endsWith("model") && resource instanceof IFile) { //$NON-NLS-1$
					IFile file = (IFile) resource;
					for (PublicNode publicNode : ModelLoader.loadFromXML(file, new PublicNodeHandler(file, nodeFormalism)).getPublicNodes()) {
						model.put(publicNode.toString(), publicNode);
						listWidget.add(publicNode.toString());

						if (select == 0 && publicNode.toString().equals(getElements().get(0).getNodeLink())) {
							select = listWidget.getItemCount() - 1;
						}
					}
				}
			}
			if (select == -1) {
				listWidget.deselectAll();
			} else {
				listWidget.select(select);
			}
			listWidget.pack();
			listWidget.redraw();
			listWidget.update();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void internalDispose() {
		ColoaneEditor coloaneEditor = (ColoaneEditor) getPart();
		IFile currentModel = ((FileEditorInput) coloaneEditor.getEditorInput()).getFile();
		currentModel.getWorkspace().removeResourceChangeListener(resourceChangeListener);
	}
}
