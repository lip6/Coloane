/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.checks.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.lip6.move.coloane.projects.its.checks.ParameterList;
import fr.lip6.move.coloane.projects.its.ui.forms.ITSDetailsPage;
import fr.lip6.move.coloane.projects.its.variables.IModelVariable;

public class VariableDetailsPage extends ITSDetailsPage<IModelVariable> {

	private static final String VARNAME = "Variable Name";
	private static final String VARDESC = "Description";
	private static final String VARID = "ITS ID";
	private static final String VARQUAL = "Qualified name";
	
	private ParameterSection params;
	
	@Override
	protected void update() {
		IModelVariable input = getInput();
		params.setInput(getParameters(input));		
	}

	private ParameterList getParameters(IModelVariable input) {
		ParameterList pl = new ParameterList();
		pl.addParameter(VARNAME);
		pl.setParameterValue(VARNAME, input.getName());
		pl.addParameter(VARDESC);
		pl.setParameterValue(VARDESC, input.getDescription());
		pl.addParameter(VARQUAL);
		pl.setParameterValue(VARQUAL, input.getQualifiedName());
		pl.addParameter(VARID);
		pl.setParameterValue(VARID, input.getId());
		return pl;
	}

	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		parent.setLayout(layout);
				
		params = new ParameterSection("Variable Description", getToolkit(), parent, false);
	}

}
