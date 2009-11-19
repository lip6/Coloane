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

import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import its.CompositeTypeDeclaration;
import its.TypeDeclaration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A type declaration detail page.
 * @author Yann
 */
public final class TypeDeclarationDetailsPage implements IDetailsPage {
	private IManagedForm mform;
	private TypeDeclaration input;
	private Text typeNametf;
	private Text typeFormalismtf;
	private Text typeFiletf;
	private TableViewer lviewer;
	private ScrolledPropertiesBlock master;

	/**
	 * Ctor. pass master for openEditor button action.
	 * @param master the master page
	 */
	public TypeDeclarationDetailsPage(ScrolledPropertiesBlock master) {
		this.master = master;
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.ui.forms.IDetailsPage#initialize(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	public void initialize(IManagedForm mform) {
		this.mform = mform;
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = mform.getToolkit();
		Section s1 = toolkit.createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		s1.marginWidth = 10;
		s1.setText("Type Definition"); //$NON-NLS-1$
		//		s1.setDescription(Messages.getString("TypeOneDetailsPage.name")); //$NON-NLS-1$
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		Composite client = toolkit.createComposite(s1);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = 0;
		glayout.marginHeight = 0;
		glayout.numColumns = 2;
		client.setLayout(glayout);

		GridData gd;
		toolkit.createLabel(client, "Type Name"); //$NON-NLS-1$
		typeNametf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		typeNametf.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (input != null) {
					input.setTypeName(typeNametf.getText());
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		typeNametf.setLayoutData(gd);

		toolkit.createLabel(client, "Model File"); //$NON-NLS-1$
		typeFiletf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		typeFiletf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		typeFiletf.setLayoutData(gd);

		toolkit.createLabel(client, "Formalism"); //$NON-NLS-1$
		typeFormalismtf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		typeFormalismtf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		typeFormalismtf.setLayoutData(gd);


		createSpacer(toolkit, client, 2);

		toolkit.createLabel(client, "Interface"); //$NON-NLS-1$
		Table requiredTable = toolkit.createTable(client, SWT.SINGLE);
		gd = new GridData(GridData.FILL_BOTH);
		requiredTable.setLayoutData(gd);
		lviewer = new TableViewer(requiredTable);
		lviewer.setContentProvider(new OfferedConceptsProvider());
		lviewer.setInput(input);

		Button oeb = toolkit.createButton(client, "Open Editor", SWT.PUSH);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		oeb.setLayoutData(gd);
		oeb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				master.getPage().getMpe().openEditor(input);
			}
		});

		toolkit.paintBordersFor(s1);
		s1.setClient(client);
	}
	
	/**
	 * Provider class to display the interface of a type declaration
	 * @author Yann
	 *
	 */
	class OfferedConceptsProvider implements IStructuredContentProvider {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof CompositeTypeDeclaration) {
				TypeDeclaration td = (TypeDeclaration) inputElement;
				String[] items = td.getLabels().toArray(new String[td.getLabels().size()]);
				Arrays.sort(items);
				return items;
			} else if (inputElement instanceof TypeDeclaration) {
				TypeDeclaration td = (TypeDeclaration) inputElement;
				IGraph graph = td.getGraph();
				IGraphFormalism formalism = graph.getFormalism().getMasterGraph();
				IElementFormalism trans = formalism.getElementFormalism("transition");
				Collection<INode> nodes = graph.getNodes();

				List<String> toret = new ArrayList<String>();
				for (INode node : nodes) {
					if (node.getNodeFormalism().equals(trans)) {
						if (node.getAttribute("visibility").getValue().equals("public")) {
							toret.add(node.getAttribute("label").getValue() + " [ "
									+ node.getAttribute("earliestFiringTime").getValue() + ", "
									+ node.getAttribute("latestFiringTime").getValue() + " ]");
						}
					}
				}
				String[] items = toret.toArray(new String[toret.size()]);
				Arrays.sort(items);
				return items;
			}
			return new String[0];
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void dispose() {
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	/**
	 * Create a nice spacing between two sections
	 * @param toolkit the toolkit
	 * @param parent the parent container
	 * @param span the width
	 */
	private void createSpacer(FormToolkit toolkit, Composite parent, int span) {
		Label spacer = toolkit.createLabel(parent, ""); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.horizontalSpan = span;
		spacer.setLayoutData(gd);
	}
	/**
	 * Update the state of the viewers
	 */
	private void update() {
		typeNametf.setText(input != null && input.getTypeName() != null ? input.getTypeName() : ""); //$NON-NLS-1$
		typeFormalismtf.setText(input != null && input.getTypeType() != null ? input.getTypeType() : "");
		typeFiletf.setText(input != null && input.getTypePath() != null ? input.getTypePath() : "");
		lviewer.setInput(input);
	}
	/**
	 * {@inheritDoc} (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#inputChanged(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;
		if (ssel.size() == 1) {
			input = (TypeDeclaration)  ssel.getFirstElement();
		} else {
			input = null;
		}
		update();
	}
	/**
	 * {@inheritDoc} (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#commit()
	 */
	@Override
	public void commit(boolean onSave) {
	}
	/**
	 * {@inheritDoc} (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#dispose()
	 */
	@Override
	public void dispose() {
	}
	/**
	 * {@inheritDoc} (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStale() {
		return false;
	}
	/**
	 * {@inheritDoc} (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#refresh()
	 */
	@Override
	public void refresh() {
		update();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setFormInput(Object input) {
		return false;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
	}
}
