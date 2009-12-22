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
package fr.lip6.move.coloane.its.checks.ui;

import fr.lip6.move.coloane.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.its.Concept;
import fr.lip6.move.coloane.its.ITypeListProvider;
import fr.lip6.move.coloane.its.TypeDeclaration;
import fr.lip6.move.coloane.its.TypeList;
import fr.lip6.move.coloane.its.checks.CTLCheckService;
import fr.lip6.move.coloane.its.checks.CheckService;
import fr.lip6.move.coloane.its.checks.ServiceResult;
import fr.lip6.move.coloane.its.expression.VariableBinding;
import fr.lip6.move.coloane.its.ui.forms.ConceptDetailsPage;
import fr.lip6.move.coloane.its.ui.forms.ITSEditorPlugin;
import fr.lip6.move.coloane.its.ui.forms.TypeDeclarationDetailsPage;
import fr.lip6.move.coloane.its.ui.forms.VariableBindingDetailsPage;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
/**
 * A page to hold a check list in a tree view + hookup to details pages.
 */
public final class ChecksScrolledPropertiesBlock extends MasterDetailsBlock implements ITypeListProvider {
	private ChecksMasterDetailsPage page;
	private TreeViewer viewer;
	/**
	 * Ctor.
	 * @param page parent page
	 */
	public ChecksScrolledPropertiesBlock(ChecksMasterDetailsPage page) {
		this.page = page;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createMasterPart(final IManagedForm managedForm,
			Composite parent) {
		//final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
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
		GridData gd = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.heightHint = 20;
		gd.widthHint = 30;

		//		Table t = toolkit.createTable(client, SWT.NULL);
		Tree tree = toolkit.createTree(client, SWT.NULL);
		tree.setLayoutData(gd);

		toolkit.paintBordersFor(client);
		Composite buttonZone = toolkit.createComposite(client);
		layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		layout.verticalSpacing = 3;
		buttonZone.setLayout(layout);

		Button breload = toolkit.createButton(buttonZone, "Refresh", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL);
		breload.setLayoutData(gd);
		breload.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				page.getMpe().getTypes().reload();
			}
		});

		Button b = toolkit.createButton(buttonZone, "Add a Check", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL);
		b.setLayoutData(gd);
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				CheckService cs = new CheckService(page.getCheckList());
				cs.setWorkdir("");
				page.getCheckList().addCheck(cs);
			}
		});

		Button b2 = toolkit.createButton(buttonZone, "Add CTL Check", SWT.PUSH); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL);
		b2.setLayoutData(gd);
		b2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				CheckService cs = new CTLCheckService(page.getCheckList());
				cs.setWorkdir("");
				page.getCheckList().addCheck(cs);
			}
		});

		
//		Button b2 = toolkit.createButton(buttonZone, "Remove a type", SWT.PUSH); //$NON-NLS-1$
//		//		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
//		b2.setLayoutData(gd);
//		b2.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent event) {
//				try {
//					TypeDeclaration td = (TypeDeclaration) ((TreeSelection) viewer.getSelection()).getFirstElement();
//					new RemoveTypeAction(page.getMpe().getTypes(), td).run();
//				} catch (ClassCastException e) {
//					System.err.println("Select a type");
//				}
//			}
//		});
//
//
//		Button b3 = toolkit.createButton(buttonZone, "Export to SDD", SWT.PUSH); //$NON-NLS-1$
//		//		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
//		b3.setLayoutData(gd);
//		b3.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent event) {
//				try {
//					TypeDeclaration td = (TypeDeclaration) ((TreeSelection) viewer.getSelection()).getFirstElement();
//					page.getMpe().exportToSDD(td);
//				} catch (ClassCastException e) {
//					System.err.println("Select a type");
//				}
//			}
//		});

		toolkit.paintBordersFor(client);
		
		viewer = new TreeViewer(tree);
		viewer.setContentProvider(new CheckListTreeProvider());
		viewer.setLabelProvider(new CheckListTreeLabelProvider());
		viewer.setInput(page.getCheckList());

		section.setClient(client);
		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
			}
		});

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				try {
					TreeSelection o = (TreeSelection) event.getSelection();
					TypeDeclaration td = (TypeDeclaration) o.getFirstElement();
					page.getMpe().openEditor(td);
				} catch (ClassCastException e) {
					// a concept was double clicked
					return;
				}
			}
		});

	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		Action haction = new Action("hor", IAction.AS_RADIO_BUTTON) { //$NON-NLS-1$
			@Override
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
		Action vaction = new Action("ver", IAction.AS_RADIO_BUTTON) { //$NON-NLS-1$
			@Override
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(CompositeTypeDeclaration.class, new TypeDeclarationDetailsPage(getPage().getMpe()));
		detailsPart.registerPage(TypeDeclaration.class, new TypeDeclarationDetailsPage(getPage().getMpe()));
		detailsPart.registerPage(Concept.class, new ConceptDetailsPage(this));
		detailsPart.registerPage(VariableBinding.class, new VariableBindingDetailsPage());
		//checks
		detailsPart.registerPage(CheckService.class, new CheckServiceDetailsPage(getPage().getMpe(), new CheckService(page.getCheckList())));
		detailsPart.registerPage(CTLCheckService.class, new CheckServiceDetailsPage(getPage().getMpe(), new CTLCheckService(page.getCheckList())));
		detailsPart.registerPage(ServiceResult.class, new ServiceResultDetailsPage(getPage().getMpe()));
	}
	/**
	 * refresh the view
	 */
	public void refresh() {
		viewer.refresh();
	}
	/**
	 * accessor to get the master page.
	 * @return parent page
	 */
	public ChecksMasterDetailsPage getPage() {
		return page;
	}

	@Override
	public TypeList getTypes() {
		return page.getTypes();
	}
}

