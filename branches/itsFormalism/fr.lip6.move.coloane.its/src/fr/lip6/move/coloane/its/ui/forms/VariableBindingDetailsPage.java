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
package fr.lip6.move.coloane.its.ui.forms;

import fr.lip6.move.coloane.its.expression.IVariableBinding;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
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
 * A details page vor a variable binding.
 * @author Yann
 */
public final class VariableBindingDetailsPage implements IDetailsPage {
	private IManagedForm mform;
	private IVariableBinding input;
	private Text varNametf;
	private Text varValuetf;

	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#initialize(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	public void initialize(IManagedForm mform) {
		this.mform = mform;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
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
		s1.setText("Parameter Binding"); //$NON-NLS-1$
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
		toolkit.createLabel(client, "Parameter Name"); //$NON-NLS-1$
		varNametf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		varNametf.setEditable(false);

		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		varNametf.setLayoutData(gd);

		toolkit.createLabel(client, "Parameter Value"); //$NON-NLS-1$
		varValuetf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		varValuetf.setLayoutData(gd);
		varValuetf.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				try {
					if (input != null) {
						String s = varValuetf.getText();
						if (s.equals("")) {
							input.setVariableValue(null);
							return;
						}
						Integer n = Integer.parseInt(s);
						input.setVariableValue(n);
					}
				} catch (NumberFormatException ex) {
					varValuetf.setText("");
				}
			}
		});

		toolkit.paintBordersFor(s1);
		s1.setClient(client);
	}
	/**
	 * refresh the state
	 */
	private void update() {
		// CHECKSTYLE OFF
		varNametf.setText(input != null && input.getVariableName() != null ? input.getVariableName() : "");
		Integer n = input != null ? input.getVariableValue() : null;
		varValuetf.setText(n != null ?  n.toString() : ""); //$NON-NLS-1$
		// CHECKSTYLE ON
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#inputChanged(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;
		if (ssel.size() == 1) {
			input = (IVariableBinding)  ssel.getFirstElement();
		} else {
			input = null;
		}
		update();
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#commit()
	 */
	@Override
	public void commit(boolean onSave) {
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#setFocus()
	 */
	@Override
	public void setFocus() {
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#dispose()
	 */
	@Override
	public void dispose() {
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return false;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	*/
	@Override
	public boolean isStale() {
		return false;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#refresh()
	 */
	@Override
	public void refresh() {
		update();
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	*/
	@Override
	public boolean setFormInput(Object input) {
		return false;
	}
}
