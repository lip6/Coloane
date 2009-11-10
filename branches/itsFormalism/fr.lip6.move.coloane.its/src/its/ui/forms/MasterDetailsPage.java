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
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.*;
import org.eclipse.ui.forms.widgets.*;

import testits.editors.MultiPageEditor;
/**
 * @author dejan
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class MasterDetailsPage extends FormPage {
	private ScrolledPropertiesBlock block;
	private MultiPageEditor mpe;
	public MasterDetailsPage(MultiPageEditor editor) {
		super(editor, "fourth", "Tree view"); //$NON-NLS-1$ //$NON-NLS-2$
		block = new ScrolledPropertiesBlock(this);
		this.mpe = editor;
	}
	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		//FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Tree view"); //$NON-NLS-1$
		form.setBackgroundImage(ITSEditorPlugin.getDefault().getImage(
				ITSEditorPlugin.IMG_FORM_BG));
		block.createContent(managedForm);
	}
	public MultiPageEditor getMpe() {
		return mpe;
	}
	public void refresh() {
		block.refresh();
	}
}