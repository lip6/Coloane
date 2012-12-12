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
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import java.util.ArrayList;
import java.util.List;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.Service.Description;
import org.cosyverif.alligator.service.parameter.FileParameter;
import org.cosyverif.alligator.service.parameter.ForeignModelParameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * Wizard to prepare an invocation of an Alligator Service.
 * 
 * @author Clément Démoulins
 */
public final class ParametersWizard extends Wizard {
	
	private List<IWizardPage> pages = new ArrayList<IWizardPage>();

	public ParametersWizard(Description service) {
		super();
		boolean addParametersPage = false;
		for (Parameter<?> parameter: service.getParameters()) {
			if (parameter.isInput()) {
				if (parameter instanceof ModelParameter) {
					ModelParameter p = ModelParameter.of(parameter);
					pages.add(new SelectModelPage(p));
				} else if (parameter instanceof ForeignModelParameter) {
					ForeignModelParameter p = ForeignModelParameter.of(parameter);
					pages.add(new SelectForeignModelPage(p));
				} else if (parameter instanceof FileParameter) {
					FileParameter p = FileParameter.of(parameter);
					pages.add(new SelectFilePage(p));
				} else {
					addParametersPage = true;
				}
			}
		}
		if (addParametersPage) {
			pages.add(new ParametersPage(service));
		}
		for (IWizardPage page: pages) {
			this.addPage(page);
		}
	}

	@Override
	public boolean performFinish() {
		for (IWizardPage page: pages) {
			if (page instanceof SelectResourcePage) {
				((SelectResourcePage) page).performFinish();
			} else if (page instanceof ParametersPage) {
				((ParametersPage) page).performFinish();
			}
		}
		return true;
	}

}
