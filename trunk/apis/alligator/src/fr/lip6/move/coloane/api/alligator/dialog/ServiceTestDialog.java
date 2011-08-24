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
import fr.lip6.move.alligator.interfaces.Item;
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
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
		params.add(new DescriptionItem(ItemType.STRING, "toto"));
		params.add(new DescriptionItem(ItemType.TEXT, "titi"));
		params.add(new DescriptionItem(ItemType.BOOLEAN, "tata"));
		params.add(new DescriptionItem(ItemType.SINGLE_CHOICE, "single_choice", Arrays.asList("a", "b", "c")));
		params.add(new DescriptionItem(ItemType.MULTI_CHOICES, "multi", Arrays.asList("a", "b", "c")));
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				ParametersDialog dialog = new ParametersDialog(Display.getDefault().getActiveShell(), model, params);
				dialog.setBlockOnOpen(true);
				dialog.open();
				for (Item i : dialog.getParams()) {
					System.err.println(i.getName() + " = " + i.getValue());
				}
			}
		});
		return Collections.emptyList();
	}

}
