/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.properties.sections;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.ColoaneEditor;
import fr.lip6.move.coloane.core.ui.commands.properties.NodeLinkCmd;
import fr.lip6.move.coloane.core.ui.files.InterfacesHandler;
import fr.lip6.move.coloane.core.ui.files.InterfacesHandler.NodeInterface;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.properties.IAttributeLabel;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
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
	private static final String BROKEN = "broken node"; //$NON-NLS-1$

	private List listWidget;
	private Map<String, NodeInterface> widgetModel = new HashMap<String, NodeInterface>();

	private Composite composite;

	/** ScrolledComposite gardé en mémoire pour le rafraichissement */
	private ScrolledComposite sc;

	/**
	 * Selection Listener, link change
	 */
	private SelectionListener linkSelectionListener = new SelectionListener() {
		@Override
		public void widgetDefaultSelected(SelectionEvent e) { }

		@Override
		public void widgetSelected(SelectionEvent e) {
			CompoundCommand cc = new CompoundCommand();
			String selection = ((List) e.widget).getSelection()[0];
			String newLink;
			if (NONE.equals(selection) || BROKEN.equals(selection)) {
				newLink = null;
			} else {
				newLink = widgetModel.get(selection).getLink();
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
			Display display = Display.getCurrent();
			if (display != null) {
				display.asyncExec(new Runnable() {

					@Override
					public void run() {
						refresh();
					}
				});
			}
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

		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
				process(event.getDelta());
			}
		}
	};

	/** {@inheritDoc} */
	@Override
	public final void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data;

		listWidget = getWidgetFactory().createList(composite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		data = new FormData();
		data.top = new FormAttachment(0, 5);
		data.left = new FormAttachment(0, IAttributeLabel.LABEL_WIDTH + 7);
		listWidget.setLayoutData(data);
		listWidget.addSelectionListener(linkSelectionListener);

		// Etiquette
		CLabel label = getWidgetFactory().createCLabel(composite, "Link to :"); //$NON-NLS-1$
		data = new FormData();
		data.top = new FormAttachment(listWidget, 0, SWT.TOP);
		data.left = new FormAttachment(0, 5);
		data.right = new FormAttachment(0, IAttributeLabel.LABEL_WIDTH);
		label.setLayoutData(data);

	}

	/** {@inheritDoc} */
	@Override
	public final void refresh() {
		listWidget.removeAll();
		widgetModel.clear();
		listWidget.add(NONE);

		ColoaneEditor coloaneEditor = (ColoaneEditor) getPart();
		IFile currentModel = ((FileEditorInput) coloaneEditor.getEditorInput()).getFile();

		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener);

		// Test if all selected element use the same formalism.
		INodeFormalism nodeFormalism = getElements().get(0).getNodeFormalism();
		for (INode node : getElements()) {
			if (!nodeFormalism.getName().equals(node.getNodeFormalism().getName())) {
				redraw();
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
				if (resource.getName().endsWith(Coloane.getParam("MODEL_EXTENSION")) && resource instanceof IFile) { //$NON-NLS-1$
					IFile file = (IFile) resource;
					InterfacesHandler interfacesHandler = ModelLoader.loadFromXML(file, new InterfacesHandler(file));
					if (interfacesHandler != null) {
						for (NodeInterface nodeInterface : interfacesHandler.getInterfaces()) {
							widgetModel.put(nodeInterface.toString(), nodeInterface);
							listWidget.add(nodeInterface.toString());

							if (select == 0 && nodeInterface.getLink().equals(getElements().get(0).getNodeLink())) {
								select = listWidget.getItemCount() - 1;
							}
						}
					}
				}
			}

			// Broken link
			if (select == 0 && getElements().get(0).getNodeLink() != null) {
				listWidget.add(BROKEN);
				select = listWidget.getItemCount() - 1;
			}

			if (select == -1) {
				listWidget.deselectAll();
			} else {
				listWidget.select(select);
			}
			redraw();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Redraw this section
	 */
	public final void redraw() {
		listWidget.pack();
		listWidget.redraw();

		// Récupération du ScrolledComposite
		if (sc == null) {
			Composite tmp = composite;
			while (!(tmp instanceof ScrolledComposite)) {
				tmp = tmp.getParent();
			}
			sc = (ScrolledComposite) tmp;
		}
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		Composite tmp = composite;
		for (int i = 0; i < 20 && tmp != null; i++) {
			tmp.layout();
			tmp.redraw();
			tmp = tmp.getParent();
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void internalDispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
	}
}
