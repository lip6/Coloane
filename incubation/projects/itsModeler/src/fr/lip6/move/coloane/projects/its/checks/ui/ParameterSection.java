package fr.lip6.move.coloane.projects.its.checks.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import fr.lip6.move.coloane.projects.its.checks.ParameterList;

public class ParameterSection {

	ParameterList input;
	private Map<Text, String> params = new HashMap<Text, String>();
	private FormToolkit toolkit;
	private Section section;
	private Composite parent;
	private boolean isEditable;


	public ParameterSection(final FormToolkit formToolkit, Composite parent, boolean isEditable) {
		toolkit = formToolkit;
		this.parent = parent;
		this.isEditable= isEditable;
	}

	public ParameterList getInput() {
		return input;
	}

	public void setInput(ParameterList input) {
		if (input != this.input)
		{
			this.input = input;
			createDetails(parent);
		}
		update();
	}

	public void update() {
		for (Entry<Text, String> entry : params.entrySet()) {
			String s = (input != null && input.getParameterValue(entry.getValue()) != null) ?
					getInput().getParameterValue(entry.getValue()) : "";
					entry.getKey().setText(s);
		}
	}

	protected void createDetails(Composite parent) {		
		if (section != null) {
			section.dispose();
			params.clear();
			section = null;
		}
		if (! input.getParameters().isEmpty()) {
			section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
			section.marginWidth = 4;
			section.marginHeight = 4;
			section.setText("Tool Settings"); //$NON-NLS-1$
			TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
			td.grabHorizontal = true;
			section.setLayoutData(td);
			Composite client = toolkit.createComposite(section);
			GridLayout glayout = new GridLayout();
			glayout.marginWidth = 10;
			glayout.marginHeight = 5;
			glayout.numColumns = 2;
			client.setLayout(glayout);
			for (String param : input.getParameters()) {
				toolkit.createLabel(client, param); //$NON-NLS-1$
				Text tf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
				tf.setEditable(isEditable);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
				gd.widthHint = 10;
				tf.setLayoutData(gd);
				// store this param
				params.put(tf, param);
				if (isEditable)
					tf.addModifyListener(new ParamListener(param));
			}
			toolkit.paintBordersFor(section);
			toolkit.paintBordersFor(client);
			section.setClient(client);
			parent.pack();
		}
	}

	private class ParamListener implements ModifyListener {

		private String param;

		public ParamListener(String param) {
			this.param = param;
		}

		public void modifyText(ModifyEvent e) {
			if (getInput() != null) {
				for (Entry<Text, String> entry : params.entrySet()) {
					if (entry.getValue().equals(param)) {
						String s = entry.getKey().getText();
						getInput().setParameterValue(param, s);
						break;
					}
				}
			}
		}
	}


}
