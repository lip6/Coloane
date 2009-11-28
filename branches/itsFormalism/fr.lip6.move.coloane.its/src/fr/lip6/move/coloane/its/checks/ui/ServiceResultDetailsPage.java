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

import fr.lip6.move.coloane.its.checks.ServiceResult;
import fr.lip6.move.coloane.its.plugin.editors.MultiPageEditor;
import fr.lip6.move.coloane.its.ui.forms.ITSDetailsPage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A details page for a variable binding.
 * @author Yann
 */
public final class ServiceResultDetailsPage extends ITSDetailsPage<ServiceResult> {
	private Text isOktf;
	private Text datetf;
	private Text reporttf;
	private MultiPageEditor mpe;

	
	/**
	 * Ctor. pass master for openDirectory button action.
	 * @param master the master page
	 */
	public ServiceResultDetailsPage(MultiPageEditor master) {
		this.mpe = master;
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

		FormToolkit toolkit = getToolkit();
		Section s1 = toolkit.createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		s1.marginWidth = 10;
		s1.setText("Check Result Description"); //$NON-NLS-1$
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
		toolkit.createLabel(client, "Result"); //$NON-NLS-1$
		isOktf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		isOktf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		isOktf.setLayoutData(gd);

		toolkit.createLabel(client, "Date"); //$NON-NLS-1$

		datetf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		datetf.setLayoutData(gd);
		datetf.setEditable(false);
		
		createSpacer(toolkit, client, 2);

		toolkit.createLabel(client, "Raw Output"); //$NON-NLS-1$
		reporttf = toolkit.createText(client, "", SWT.MULTI | SWT.WRAP | SWT.V_SCROLL); //$NON-NLS-1$
		reporttf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING | GridData.FILL_VERTICAL);
		gd.widthHint = 10;
		reporttf.setLayoutData(gd);
		
		toolkit.paintBordersFor(s1);
		s1.setClient(client);
	}
	/**
	 * refresh the state
	 */
	protected void update() {
		ServiceResult input = getInput();
		// CHECKSTYLE OFF
		isOktf.setText(input != null ? (input.isSuccess() ? "PASS" : "FAIL") : "");
		datetf.setText(input != null && input.getDate() != null ? input.getDate() : "");
		reporttf.setText(input != null && input.getReport() != null ? input.getReport() : "");
		// CHECKSTYLE ON
	}
}
