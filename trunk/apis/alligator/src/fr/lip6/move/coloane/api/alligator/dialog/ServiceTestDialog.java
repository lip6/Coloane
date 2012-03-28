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
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;

public class ServiceTestDialog implements IService {

	public ServiceTestDialog() {
	}

	public List<IResult> run(final IGraph model, IProgressMonitor monitor) throws ServiceException {
		final List<DescriptionItem> params = new ArrayList<DescriptionItem>();
		params.add(new DescriptionItem(ItemType.MODEL, "https://alligator.lip6.fr/pt-net.fml"));
//		params.add(new DescriptionItem(ItemType.STRING, "toto"));
//		params.add(new DescriptionItem(ItemType.TEXT, "titi"));
//		params.add(new DescriptionItem(ItemType.BOOLEAN, "tata"));
//		params.add(new DescriptionItem(ItemType.SINGLE_CHOICE, "single_choice", Arrays.asList("a", "b", "c")));
//		params.add(new DescriptionItem(ItemType.MULTI_CHOICES, "multi", Arrays.asList("a", "b", "c")));
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
//				ParametersWizard wizard = new ParametersWizard(params);
//				WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
//				dialog.open();
			}
		});
		String endl = System.getProperty("line.separator", "\n");
		List<IResult> results = new ArrayList<IResult>();
		IResult root = new Result("test");
		root.addSubResult(new SubResult("test1", "singleline"));
		root.addSubResult(new SubResult("test2", "line1\nline2\nline3"));
		root.addSubResult(new SubResult("test3", "singleline"));
		root.addSubResult(new SubResult("test4", "line1" + endl + "line2" + endl + "line3"));
		results.add(root);
		return results;
	}

}
