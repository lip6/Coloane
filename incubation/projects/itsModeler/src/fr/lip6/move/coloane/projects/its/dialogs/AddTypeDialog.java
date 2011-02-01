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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.dialogs;

import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.TypeList;
import fr.lip6.move.coloane.projects.its.actions.FileBrowserField;
import fr.lip6.move.coloane.projects.its.obs.ISimpleObserver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * A nice dialog to import a coloane model into an fr.lip6.move.coloane.its type list.
 * @author Yann
 *
 */
public final class AddTypeDialog extends TitleAreaDialog {

	private TypeDeclaration type;
	private FileBrowserField fileField;
	private Text newTypeTextfield;
	private TypeList types;
	private String hint;

	/**
	 * Constructor.
	 * @param parentShell the parent
	 * @param types the types
	 */
	public AddTypeDialog(Shell parentShell, TypeList types) {
		super(parentShell);
		this.types = types;
	}

	/**
	 * Getter for the type declaration.
	 * @return null if not set correctly.
	 */
	public TypeDeclaration getDeclaration() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new Type");
		setMessage("Please select the model file containing your type definition.");
		return contents;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Group fileComposite = new Group(parent, SWT.LEFT);
		GridLayout layout = new GridLayout();
		fileComposite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		fileComposite.setLayoutData(data);
		fileComposite.setText("Select model file to import");

		fileField = new FileBrowserField(fileComposite);
		fileField.setToolTipText("Select model file to import");
		fileField.setLayoutData(data);
		if (hint != null) {
			fileField.setText(hint);
		}

		Group nameComposite = new Group(parent, SWT.LEFT);
		GridLayout layout2 = new GridLayout();
		nameComposite.setLayout(layout2);
		GridData data2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		nameComposite.setLayoutData(data2);
		nameComposite.setText("Select name of type after import");

		newTypeTextfield = new Text(nameComposite, SWT.BORDER);
		newTypeTextfield.setText("Type " + types.size());
		newTypeTextfield.setToolTipText("Enter the name under which to import your new type");
		newTypeTextfield.setLayoutData(data);

		fileField.addObserver(new ISimpleObserver() {
			public void update() {
				suggestName();
			}
		});

		if (hint != null) {
			suggestName();
		}
		return parent;
	}

	/**
	 * Suggest a valid name based on current file name.
	 */
	protected void suggestName() {
		Path path = new Path(fileField.getText());
		path.removeFileExtension();
		String name = path.lastSegment();
		name.replace(".model", "");
		name = generateValidName(name);
		newTypeTextfield.setText(name);
	}

	/**
	 * Build a valid name using the provided string as base.
	 * @param name a candidate name
	 * @return a valid name
	 */
	protected String generateValidName(String name) {
		for (TypeDeclaration td : types) {
			if (td.getTypeName().equals(name)) {
				return generateValidName(name + "1");
			}
		}
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;
		((GridLayout) parent.getLayout()).numColumns++;

		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void cancelPressed() {
		type = null;
		hint = null;
		super.cancelPressed();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void okPressed() {
		hint = null;
		String filePath = fileField.getText();
		if (filePath == null || "".equals(filePath)) {
			setErrorMessage("Please Specify a model file using the \"Browse\" button");
			return;
		}
		IPath path = new Path(filePath);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);

		String typeName = newTypeTextfield.getText();
		if (typeName == null || typeName.equals("")) {
			setErrorMessage("The model type name cannot be empty.");
			return;
		}
		for (TypeDeclaration itType : types) {
			if (itType.getTypeName().equals(typeName)) {
				setErrorMessage("Type name already exists");
				return;
			}
		}
		try {
			type = TypeDeclaration.create(typeName, file, types);
		} catch (Exception ex) {
			setErrorMessage("Error loading model file: "
					+ "Your file does not seem to contain a recognized Coloane model. Details:\n" + ex.getMessage());
			return;
		}
		super.okPressed();
	}

	/**
	 * getter for type declaration.
	 * @return null if not set.
	 */
	public TypeDeclaration getType() {
		return type;
	}

	/**
	 * Set the candidate file.
	 * @param fileField the full absolute path to file to import.
	 */
	public void setFileField(String fileField) {
		this.hint = fileField;
	}
}
