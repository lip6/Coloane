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

import fr.lip6.move.alligator.interfaces.DescriptionItem;
import fr.lip6.move.alligator.interfaces.Item;
import fr.lip6.move.alligator.interfaces.ItemType;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.extensions.importExportLola.ExportToLola;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Wizard to prepare an invocation of an Alligator Service.
 * 
 * @author Clément Démoulins
 */
public class ParametersWizard extends Wizard {
	
	// To memorize the previous selection
	// This patches through the fact that Coloane Model view does not provide the appropriate selection hooks.
	// We use jdt.package explorer view the first time, but then we use the stored value.
	private static List<IResource> lastSelection = null;
	
	private ChooseModelsPage modelsPage;
	private ParametersPage parametersPage;
	
	private List<Item> parameters = new ArrayList<Item>();
	
	
	/**
	 * The list of formats currently supported for MODEL elements.
	 * According to the type, the appropriate IExportTo extension is called on the coloane models.
	 * The default value is GML.
	 */
	private enum ModelFormat { GML, LOLA };
	
	private ModelFormat format = ModelFormat.GML;

	/**
	 * Constructor
	 * @param params list of DescriptionItem provided by an Alligator Service
	 * @param serviceName the name of the service, unique to store previous values.
	 * @param serviceDescription the short description field of the service
	 */
	public ParametersWizard(List<DescriptionItem> params, String serviceName, String serviceDescription) {
		super();

		for (DescriptionItem p : params) {
			if (p.getType() == ItemType.MODEL) {
				if (p.getDefaultValue().equals(ModelFormat.LOLA.toString())) {
					format = ModelFormat.LOLA;
				}

				// This piece of code retrieves the current selection and sets up the export resource wizard to use it.
				// It has to by run by the ui Thread for some (obscure to me) reason.
				// SyncExec ensures we wait until it's done, otherwise no models page...
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						// this is our target
						IStructuredSelection selection = null;
						if (lastSelection != null) {
							// Wrap the list of resources into a selection
							selection = new StructuredSelection(lastSelection);
						} else {
							// grab the mai workbench from eclipse
							IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
							// yes it can be null, depending on state of eclipse
							if (window != null) {
								// looks good, grab the selection of the Java package view : this selection is compatible with the wizard.
								ISelection sel = window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
								if (sel instanceof IStructuredSelection) {
									// I've never see this cast fail if we made it this far
									selection = (IStructuredSelection) sel;
									// passing the selection at construction time
									modelsPage = new ChooseModelsPage("Choose the models to send to the platform", selection);
									// add to the wizard's pages
									addPage(modelsPage);
									return;
								}
							}
						}
						// So if we get here, one of the steps above didn't work, sorry.
						// Passing null as selection => default workspace view.
						// passing the selection at construction time => correct model selected
						modelsPage = new ChooseModelsPage("Choose the models to send to the platform", selection);
						// add to the wizard's pages
						addPage(modelsPage);
					}

				});
				// only one MODEL type Item is expected from a service currently.
				break;
			}
		}

		for (DescriptionItem p : params) {
			if (p.getType() != ItemType.MODEL) {
				parametersPage = new ParametersPage("Parameters needed by " + serviceName + " service", params, serviceDescription);
				addPage(parametersPage);
				break;
			}
		}
	}

	/** {@inheritDoc}
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public final boolean performFinish() {
		if (modelsPage != null) {
			IExportTo exporter;
			switch (format) {
				case LOLA :
					exporter = new ExportToLola();
					break;
				case GML :
				default:
					exporter = new ExportToGML();
					break;
			}

			// Store for next call to a service.
			lastSelection  = modelsPage.getSelectedResources();

			// convert the models to GML
			for (IResource resource : modelsPage.getSelectedResources()) {
				if (resource != null && resource instanceof IFile) {
					IFile modelFile = (IFile) resource;
					IGraph graph = ModelLoader.loadGraphFromXML(modelFile);
					try {
						// Create temp file.
					    File temp = File.createTempFile("pattern", ".suffix");

					    // Delete temp file when program exits.
					    temp.deleteOnExit();

						exporter.export(graph, temp.getAbsolutePath(), new NullProgressMonitor());

						String output = slurp(temp.getAbsolutePath());

						parameters.add(new Item(ItemType.MODEL, modelFile.getName(), output));
					} catch (IOException e) {
						Coloane.showErrorMsg(e.getMessage());
						return false;
					} catch (ExtensionException e) {
						Coloane.showErrorMsg(e.getMessage());
						return false;
					}
				}
			}
		}
		if (parametersPage != null) {
			parameters.addAll(parametersPage.getParameters());
		}
		return true;
	}

	
	/**
	 * Prints a file's content into a String
	 * @param outputFile the file to read
	 * @return the file's content
	 * @throws IOException something is wrong
	 */
	private String slurp(String outputFile) throws IOException {
		File file = new File(outputFile);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line  = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		reader.close();
		return stringBuilder.toString();
	}

	
	/**
	 * @return unmodifiable list of the parameters
	 */
	public final List<Item> getParameters() {
		return Collections.unmodifiableList(parameters);
	}

}
