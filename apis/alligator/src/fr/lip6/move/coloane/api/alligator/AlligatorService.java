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
package fr.lip6.move.coloane.api.alligator;

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.Item;
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.alligator.interfaces.ServiceDescription;
import fr.lip6.move.alligator.interfaces.ServiceManager;
import fr.lip6.move.coloane.core.model.factory.FormalismManager;
import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.extensions.importExportCAMI.importFromCAMI.ImportFromImpl;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;
import fr.lip6.move.coloane.interfaces.objects.result.SubResult;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;

/**
 * Implementation of IApiService to manage Alligator service.
 * @see {@link fr.lip6.move.alligator.interfaces.Service}
 * @author Clément Démoulins
 */
public class AlligatorService implements IApiService {
	/** Logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

	private static final ExportToGML GRAPH_TO_GML = new ExportToGML();
	
	private ServiceDescription service;

	private AlligatorApi api;

	/**
	 * Constructor
	 * @param service Associated service
	 * @param api Alligator api
	 */
	public AlligatorService(ServiceDescription service, AlligatorApi api) {
		this.service = service;
		this.api = api;
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.interfaces.objects.services.IService#run(fr.lip6.move.coloane.interfaces.model.IGraph, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public final List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
		List<IResult> results = new ArrayList<IResult>();
		try {
			ServiceManager manager = api.getServerManager();
			if (manager == null) {
				throw new ServiceException("The connection is not available.");
			}

			List<DescriptionItem> dItems = service.getDescriptionItems();
			final List<Item> params = new ArrayList<Item>();
			for (final DescriptionItem dItem : dItems) {
				if (dItem.getType() == ItemType.MODEL) {
					StringWriter stringModel = new StringWriter();
					GRAPH_TO_GML.export(model, stringModel, monitor);
					params.add(new Item(ItemType.MODEL, model.getFormalism().getName(), stringModel.toString()));
				} else if (dItem.getType() == ItemType.STRING) {
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							final InputDialog dialog = new InputDialog(Display.getDefault().getActiveShell(), "String parameter", dItem.getName(), "", null);
							dialog.setBlockOnOpen(true);
							if (dialog.open() == Dialog.OK) {
								params.add(new Item(dItem.getType(), dItem.getName(), dialog.getValue()));
							}
						}
					});
				}
			}

			LOGGER.info("Invoke the service: " + service.getId());
			List<Item> resultItems = manager.invoke(service.getId(), params);
			LOGGER.fine("Get " + resultItems.size() + " result items.");
			IResult result = new Result(service.getName());

			// For all result items give the better feedback to the user
			for (Item item : resultItems) {

				// Create a new model from CAMI
				if (item.getType() == ItemType.CAMI_MODEL) {
					try {
						File tmp = File.createTempFile("alligator", ".cami");
						BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));
						writer.append(item.getValue());
						writer.close();
						ImportFromImpl camiImport = new ImportFromImpl();
						IGraph newGraph = camiImport.importFrom(tmp.getAbsolutePath(), FormalismManager.getInstance().getFormalismById("PT-Net"), SubMonitor.convert(null));
						result.setNewGraph(newGraph);
					} catch (IOException e) {
						LOGGER.warning(e.getMessage());
					}

				// Add a textual result in the result view
				} else {
					ISubResult sub = new SubResult(item.getName(), item.getValue());
					result.addSubResult(sub);
				}
			}
			results.add(result);
		} catch (ExtensionException e) {
			throw new ServiceException(e.getMessage());
		}
		return results;
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.interfaces.api.services.IApiService#getName()
	 */
	@Override
	public final String getName() {
		return service.getName();
	}

	/** {@inheritDoc}
	 * @see fr.lip6.move.coloane.interfaces.api.services.IApiService#getDescription()
	 */
	@Override
	public final String getDescription() {
		return service.getShortDescription();
	}

}
