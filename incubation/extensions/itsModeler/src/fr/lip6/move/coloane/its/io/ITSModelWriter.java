package fr.lip6.move.coloane.its.io;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.extension.importExportRomeo.exportToRomeo.ExportToRomeo;
import fr.lip6.move.coloane.extensions.importExportITS.exportToITS.ExportToCompositeITS;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.its.Concept;
import fr.lip6.move.coloane.its.TypeDeclaration;
import fr.lip6.move.coloane.its.TypeList;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.NullProgressMonitor;


/** This class handles all that is necessary to convert a given **resolved** ITS type
 * to the input format used by SDD-ITS (the C++ model-checking component).
 * Essentially responsibility is:
 * * find all dependent types + sort them to have an easy declaration order.
 * * Replace any concepts or parameters of the type with their resolved values
 * * then export each type in a separate file
 * * create the ITSModel file (=> a list of types)
 * * add the "main instance" line
 * 
 * @author Yann
 *
 */
public final class ITSModelWriter {

	/**
	 * The main export function.
	 * @param types The set of types
	 * @param type the type to export
	 * @param directory the folder to export to
	 * @throws ColoaneException in case of any IO or instantiation error.
	 */
	public void exportITSModel(TypeList types, TypeDeclaration type, String directory) throws ColoaneException {
		// the types which need to be declared.

		List<TypeDeclaration> toProcess = new ArrayList<TypeDeclaration>();
		getDependentTypes(types, type, toProcess);

		try {
			// test folder existence, create if it does not exist
			
			// File creation
			FileOutputStream writer = new FileOutputStream(new File(directory + "/modelMain.xml")); //$NON-NLS-1$
			BufferedWriter sb = new BufferedWriter(new OutputStreamWriter(writer));

			sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
			sb.append("<model xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='http://coloane.lip6.fr/resources/schemas/model.xsd'>\n");
			sb.append("<types>\n");

			for (TypeDeclaration td : toProcess) {
				assert td.isSatisfied();

				// handle parameter instantiation
				IGraph satGraph = td.getInstantiatedGraph();


				// composite case
				if (td instanceof CompositeTypeDeclaration) {
					CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) td;
					assert ctd.isSatisfied();

					// replace the instance types by their corresponding concept name
					for (INode node : satGraph.getNodes()) {
						if ("instance".equals(node.getNodeFormalism().getName())) {
							IAttribute instType = node.getAttribute("type");
							instType.setValue(ctd.getConcept(instType.getValue()).getEffective().getTypeName());
						}
					}
					// export the composite ITS to simple xml format.
					new ExportToCompositeITS().export(satGraph, directory + "/" + ctd.getTypeName() + ".xml", new NullProgressMonitor());
					sb.append("<type name='" + ctd.getTypeName() + "' formalism='" + ctd.getTypeType() + "' format='Composite' path='./" + ctd.getTypeName() + ".xml' />\n");
				} else {
					// Basic case
					// currently no parameters to instantiate

					// export the TPN to Romeo xml format.
					new ExportToRomeo().export(satGraph, directory + "/" + td.getTypeName() + ".xml", new NullProgressMonitor());
					sb.append("<type name='" + td.getTypeName() + "' formalism='Time Petri Net' format='Romeo' path='./" + td.getTypeName() + ".xml' />\n");
				}
			}

			sb.append("</types>\n");
			sb.append("<main type='" + type.getTypeName() + "' state='' />\n");
			sb.append("</model>\n\n");

			// End of writing : clean & close
			sb.flush();
			writer.flush();
			sb.close();
			writer.close();
		} catch (FileNotFoundException fe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Error when creating file : bad file name."+fe);
			throw new ColoaneException("Invalid filename !" +fe);
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Erreur writing in file " + ioe);
			throw new ColoaneException("Write error :" + ioe.getMessage());
		}
	}

	/**
	 * Construct the list of types that need to be exported, in a correct order.
	 * @param types the type list
	 * @param type the type to export
	 * @param todo used in recursive calls
	 */
	private void getDependentTypes(TypeList types, TypeDeclaration type, List<TypeDeclaration> todo) {

		if (type instanceof CompositeTypeDeclaration) {
			CompositeTypeDeclaration ctd = (CompositeTypeDeclaration) type;
			for (Concept c : ctd.listConcepts()) {
				if (!todo.contains(c.getEffective())) {
					getDependentTypes(types, c.getEffective(), todo);
				}
			}
		}
		todo.add(type);
	}

}
