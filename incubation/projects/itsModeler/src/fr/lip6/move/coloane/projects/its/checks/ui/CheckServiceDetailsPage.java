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
package fr.lip6.move.coloane.projects.its.checks.ui;

import fr.lip6.move.coloane.projects.its.checks.AbstractCheckService;
import fr.lip6.move.coloane.projects.its.plugin.editors.MultiPageEditor;
import fr.lip6.move.coloane.projects.its.ui.forms.ITSDetailsPage;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A details page for a variable binding.
 * @author Yann
 */
public class CheckServiceDetailsPage extends ITSDetailsPage<AbstractCheckService> {
	private Text serviceNametf;
	private Text foldertf;
	private MultiPageEditor mpe;
	private ParameterSection params;
	
	
	/**
	 * Ctor. pass master for openDirectory button action.
	 * @param master the master page
	 */
	public CheckServiceDetailsPage(MultiPageEditor master) {
		this.mpe = master;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		parent.setLayout(layout);
		
		FormToolkit toolkit = getToolkit();
		Section s1 = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED );
		s1.marginWidth = 4;
		s1.marginHeight = 4;
		s1.setText("Check Service Description"); //$NON-NLS-1$
		//		s1.setDescription(Messages.getString("TypeOneDetailsPage.name")); //$NON-NLS-1$
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		Composite client = toolkit.createComposite(s1);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = 10;
		glayout.marginHeight = 5;
		glayout.numColumns = 2;
		client.setLayout(glayout);

		GridData gd;
		toolkit.createLabel(client, "Service Name"); //$NON-NLS-1$
		serviceNametf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		serviceNametf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		serviceNametf.setLayoutData(gd);

		toolkit.createLabel(client, "Work folder"); //$NON-NLS-1$
		Composite folderzone = toolkit.createComposite(client, SWT.BORDER);

		glayout = new GridLayout();
		glayout.numColumns = 1;
		folderzone.setLayout(glayout);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		folderzone.setLayoutData(gd);
		
		foldertf = toolkit.createText(folderzone, "", SWT.SINGLE | SWT.H_SCROLL); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		foldertf.setLayoutData(gd);
		foldertf.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (getInput() != null) {
					String s = foldertf.getText();
					getInput().setWorkdir(s);
				}
			}
		});

		Button browseb = toolkit.createButton(folderzone, "Browse...", SWT.PUSH);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_BEGINNING);
		browseb.setLayoutData(gd);
		browseb.addSelectionListener(new SelectionAdapter() {
			DirectoryDialog dialog = new DirectoryDialog(mpe.getSite().getShell()); 
			@Override
			public void widgetSelected(SelectionEvent event) {
				dialog.setText("Choose a work folder");
				dialog.setMessage("Choose a work folder for service "+getInput().getName()+". This folder will hold intermediate files and run results.");
				String directory = dialog.open();
				foldertf.setText(directory);
			}
		});

		
		createSpacer(toolkit, client, 2);
		if (showRunButton) {
			Button runb = toolkit.createButton(client, "Run service", SWT.PUSH);
			gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END);
			runb.setLayoutData(gd);
			runb.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent event) {
					getInput().run();
				}
			});
		}		

		toolkit.paintBordersFor(s1);
		toolkit.paintBordersFor(client);
		s1.setClient(client);
		

		///// ADD THE PARAMS HERE
		params = new ParameterSection("Tool Settings",getToolkit(), parent, true);
	}



	private boolean showRunButton=true;	

	protected void setShowRunButton(boolean b) {
		showRunButton = b;
	}


	/**
	 * refresh the state
	 */
	 @Override
	protected void update() {
		AbstractCheckService input = getInput();
		params.setInput(input.getParameters());
		// CHECKSTYLE OFF
		serviceNametf.setText(input != null && input.getName() != null ? input.getName() : "");
		IFile pos = ((FileEditorInput) mpe.getEditorInput()).getFile();
		foldertf.setText(input != null && input.getWorkDir(pos) != null ? input.getWorkDir() : "");		
		// CHECKSTYLE ON
	}
	
}
