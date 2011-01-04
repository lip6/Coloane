package fr.lip6.move.coloane.projects.its.checks.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.lip6.move.coloane.projects.its.checks.ParameterList;
import fr.lip6.move.coloane.projects.its.order.Ordering;
import fr.lip6.move.coloane.projects.its.ui.forms.ITSDetailsPage;

public class OrderingDetailsPage extends ITSDetailsPage<Ordering> {

	private static final String VARNAME = "Variable Name";
	private static final String VARTYPE = "Variable Domain";
	private ParameterSection params;
	
	@Override
	protected void update() {
		Ordering input = getInput();
		params.setInput(getParameters(input));
		
	}

	private ParameterList getParameters(Ordering input) {
		ParameterList pl = new ParameterList();
		pl.addParameter(VARNAME);
		pl.setParameterValue(VARNAME, input.getName());
		pl.addParameter(VARTYPE);
		pl.setParameterValue(VARTYPE, input.getDomain().toString());
		return pl;
	}

	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		parent.setLayout(layout);
				
		params = new ParameterSection("Variable Details", getToolkit(), parent, false);
		
	}

}
