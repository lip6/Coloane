package fr.lip6.move.coloane.projects.its.checks.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import fr.lip6.move.coloane.projects.its.checks.CTLCheckService;
import fr.lip6.move.coloane.projects.its.plugin.editors.MultiPageEditor;

public class CTLCheckServiceDetailsPage extends ITSCheckServiceDetailsPage {

	public CTLCheckServiceDetailsPage(MultiPageEditor master) {
		super(master);
	}

	
	@Override
	public CTLCheckService getInput() {
		return (CTLCheckService) super.getInput();
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(Composite parent) {
		Button add = getToolkit().createButton(parent, "Add a formula", SWT.PUSH);
		add.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				getInput().addFormula("Formula", "", "New formula");
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		
		super.createContents(parent);


		parent.pack();
	}



}
