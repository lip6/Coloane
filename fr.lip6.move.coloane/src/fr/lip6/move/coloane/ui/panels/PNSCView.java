package fr.lip6.move.coloane.ui.panels;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;

public class PNSCView extends ViewPart {
	protected List resultsList;
	protected Vector<PNSCResult> results;
	protected StyledText text;
	
	public PNSCView() {
		super();
		results = new Vector<PNSCResult>();
	}

	
	public void add(PNSCResult result) {
		results.add(result);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		/* This view will be divided in two parts :
		 * - to the left, we will have a list off errors
		 * - to the right, we will have the text correponding to the
		 *   selected error
		 */ 
		parent.getShell().setLayout(new GridLayout(2, true));
		resultsList = new List(parent, SWT.SINGLE | SWT.BORDER);
		
		text =
			new StyledText(parent, SWT.READ_ONLY | SWT.BORDER);
		text.setJustify(true);
		text.setAlignment(SWT.CENTER);
		
		setSelectionListener();
		setList();
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
	
	protected void setList() {
		for (int i = 0; i < results.size(); i++)
			resultsList.add(results.get(i).getResultName());
		
		
		if (results.size() > 0) {
			text.setText(results.get(0).getResultDescription());
		} else
			text.setText("Your network is perfect :D");
	}
	
	protected void setSelectionListener() {
		resultsList.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				text.setText(results.get(resultsList.
						getSelectionIndex()).getResultDescription());
				
			}

			public void widgetSelected(SelectionEvent e) {
				text.setText(results.get(resultsList.
						getSelectionIndex()).getResultDescription());
			}
		});
	}
}
