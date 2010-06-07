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
package fr.lip6.move.coloane.projects.its.checks.ui;

import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.projects.its.checks.ITSCheckService;
import fr.lip6.move.coloane.projects.its.checks.OrderParameter;
import fr.lip6.move.coloane.projects.its.order.Ordering;
import fr.lip6.move.coloane.projects.its.order.Orders;
import fr.lip6.move.coloane.projects.its.plugin.editors.MultiPageEditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

/**
 * A details page for a variable binding.
 * @author Yann
 */
public final class ITSCheckServiceDetailsPage extends CheckServiceDetailsPage {

	private Combo orderSelector;	
	
	/**
	 * Ctor. pass master for openDirectory button action.
	 * @param master the master page
	 */
	public ITSCheckServiceDetailsPage(MultiPageEditor master) {
		super(master);
	}
	
	/** Overload to refine typing of the input. */
	@Override
	public ITSCheckService getInput() {
		return (ITSCheckService) super.getInput();
	};
	
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(Composite parent) {
		super.createContents(parent);
				
		FormToolkit toolkit = getToolkit();
		Section s1 = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED );
		s1.marginWidth = 4;
		s1.marginHeight = 4;
		s1.setText("Variable Order"); //$NON-NLS-1$
		//		s1.setDescription(Messages.getString("TypeOneDetailsPage.name")); //$NON-NLS-1$
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		Composite client = toolkit.createComposite(s1);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = 10;
		glayout.marginHeight = 5;
		glayout.numColumns = 2;
		client.setLayout(glayout);

		GridData gd;
		toolkit.createLabel(client, "Order Chosen"); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;

		orderSelector = new Combo(client, SWT.DROP_DOWN);
		orderSelector.setLayoutData(gd);
		orderSelector.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				ITSCheckService service = getInput();
				int n = orderSelector.getSelectionIndex();
				if (n == -1) {
					return;
				}
				String[] suggs = orderSelector.getItems();
				Orders orders = service.getParent().getOrders();
				for (Ordering o : orders) {
					if (o.getName().equals(suggs[n])) {
						service.getOrder().setSelection(o);
						break;
					}
				}
			}
		});		
		
		toolkit.paintBordersFor(s1);
		toolkit.paintBordersFor(client);
		s1.setClient(client);
	}

	/**
	 * Extract the required labels andd scan type list for matching types.
	 * @param input the concept to satisfy
	 * @return the matching type names
	 */
	private String[] getSuggestions(ITSCheckService input) {
		if (input == null) {
			return new String[0];
		}
		// build suggestion list
		List<String> suggestions = new ArrayList<String>();
		for (Ordering o : input.getOrder().getOrders()) {
			suggestions.add(o.getName());
		}
		return suggestions.toArray(new String[suggestions.size()]);
	}

	
	/**
	 * refresh the state
	 */
	protected void update() {
		super.update();
		ITSCheckService input = getInput();		
		
		String[] items = getSuggestions(input);
		orderSelector.setItems(items);
		OrderParameter op = input.getOrder();
		for (int i = 0; i < items.length; i++) {
			if (op.getSelection() != null && items[i].equals(op.getSelection().getName())) {
				orderSelector.select(i);
				break;
			}
		}

	}	
}
