package fr.lip6.move.coloane.projects.its.checks.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ITSReachServiceDetailsPage extends ITSCheckServiceDetailsPage {

	/**
	 * {@inheritDoc} (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createContents(Composite parent) {
		Button runb = getToolkit().createButton(parent, "Run service", SWT.PUSH);

		runb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				getInput().run();
			}
		});
		
		super.createContents(parent);

		parent.pack();
	}
	
		
	
}
