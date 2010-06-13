package fr.lip6.move.coloane.projects.its.checks.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.lip6.move.coloane.projects.its.checks.CTLFormulaDescription;
import fr.lip6.move.coloane.projects.its.ui.forms.ITSDetailsPage;

public class CTLFormulaDescriptionDetailsPage extends ITSDetailsPage<CTLFormulaDescription> {

	
	private CTLSection ctl;
	
	@Override
	protected void update() {
		ctl.setInput(getInput());
	}

	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		parent.setLayout(layout);

		ctl = new CTLSection(getToolkit(), parent);
		ctl.setInput(getInput());
	}

}
