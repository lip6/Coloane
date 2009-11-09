/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package its.ui.forms;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import its.CompositeTypeDeclaration;
import its.TypeDeclaration;
import its.TypeList;
import its.typesui.TypeLabelProvider;
import its.typesui.TypeListProvider;
import its.typesui.TypeListTreeProvider;
import its.typesui.TypeTreeLabelProvider;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.widgets.*;
/**
 *
 */
public class ScrolledPropertiesBlock extends MasterDetailsBlock {
	private MasterDetailsPage page;
	private TreeViewer viewer;
	public ScrolledPropertiesBlock(MasterDetailsPage page) {
		this.page = page;
	}

	protected void createMasterPart(final IManagedForm managedForm,
			Composite parent) {
		//final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);
		section.setText("Type Declarations"); //$NON-NLS-1$
		section
				.setDescription("Models imported into the ITS referential."); //$NON-NLS-1$
		section.marginWidth = 10;
		section.marginHeight = 5;
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		client.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 20;
		gd.widthHint = 100;

//		Table t = toolkit.createTable(client, SWT.NULL);
		Tree tree = toolkit.createTree(client, SWT.NULL);
		//t.setLayoutData(gd);
		
		toolkit.paintBordersFor(client);
		Button b = toolkit.createButton(client, "Add a type", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		b.setLayoutData(gd);
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				page.getMpe().getAddAction().run();
			}
		});				


		viewer = new TreeViewer(tree);
		viewer.setContentProvider(new TypeListTreeProvider());
		viewer.setLabelProvider(new TypeTreeLabelProvider());
		viewer.setInput(page.getMpe().getTypes());

		section.setClient(client);
		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});
	}
	protected void createToolBarActions(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		Action haction = new Action("hor", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
		};
		haction.setChecked(true);
		haction.setToolTipText("Switch to horizontal"); //$NON-NLS-1$
		haction.setImageDescriptor(ITSEditorPlugin.getDefault()
				.getImageRegistry()
				.getDescriptor(ITSEditorPlugin.IMG_HORIZONTAL));
		Action vaction = new Action("ver", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
		};
		vaction.setChecked(false);
		vaction.setToolTipText("Switch to vertical"); //$NON-NLS-1$
		vaction.setImageDescriptor(ITSEditorPlugin.getDefault()
				.getImageRegistry().getDescriptor(ITSEditorPlugin.IMG_VERTICAL));
		form.getToolBarManager().add(haction);
		form.getToolBarManager().add(vaction);
	}
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(TreeNode.class, new TreeNodeDetailsPage());
		detailsPart.registerPage(CompositeTypeDeclaration.class, new CompositeDetailsPage());
		detailsPart.registerPage(TypeDeclaration.class, new TypeDeclarationDetailsPage());
	}
	public void refresh() {
		viewer.refresh();
	}
}