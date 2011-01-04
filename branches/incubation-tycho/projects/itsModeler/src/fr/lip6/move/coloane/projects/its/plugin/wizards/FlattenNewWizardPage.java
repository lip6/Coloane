package fr.lip6.move.coloane.projects.its.plugin.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (fr.lip6.move.coloane.its).
 */
public final class FlattenNewWizardPage extends AbstractNewWizardPage {

	private boolean shouldInstantiate = false;
	
	/**
	 * Constructor for ITSNewWizardPage.
	 * 
	 * @param selection the workspace item currently selected (should be a folder)
	 */
	public FlattenNewWizardPage(ISelection selection) {
		super(selection);
	}

	public boolean shouldInstantiate() {
		return shouldInstantiate;
	}
	
	@Override
	protected String getWizardDescription() {
		return "This wizard creates a new Coloane model by flattening the hierarchical ITS description.";
	}

	@Override
	protected String getWizardFileExtension() {
		return "model";
	}

	@Override
	protected String getWizardTitle() {
		return "Flatten an ITS Model";
	}
	
	@Override
	protected void addContent(Composite container) {
		Button b = new Button(container, SWT.CHECK);
		b.setText("Instantiate Variables");
		b.setToolTipText("Instantiate parameters of the model, i.e. $Variable in attributes replaced by their effective value.");
		b.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				boolean b = ((Button)e.getSource()).getSelection();
				shouldInstantiate = b;
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
}

