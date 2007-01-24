package fr.lip6.move.coloane.ui.panels;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;

public class PNSCView extends ViewPart {
	List resultsList;
	Vector<PNSCResult> results;
	
	public PNSCView() {
		super();
		
	}
	
	protected void BuildresulstList(Composite parent) {
		
	}
	
	@Override
	public void createPartControl(Composite parent) {
		parent.getShell().setLayout(new GridLayout(2, true));
		resultsList = new List(parent, SWT.SINGLE | SWT.BORDER);
		resultsList.add("Premier élément");
		resultsList.add("Deuxieme élément");
		
		StyledText text =
			new StyledText(parent, SWT.READ_ONLY | SWT.BORDER);
		text.setText("Wazaaaaaaaaaaa");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}
}
