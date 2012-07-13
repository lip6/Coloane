package fr.lip6.move.coloane.projects.its;

import java.io.IOException;

import org.eclipse.core.resources.IFile;

import fr.lip6.move.coloane.interfaces.model.IGraph;

public class TypeDeclarationFactory {

	
	private TypeDeclarationFactory() {}

	/**
	 * Factory operation to build concrete TypeDescriptions
	 * 
	 * @param name
	 *            name of the resulting type
	 * @param file
	 *            the base file containing a coloane model
	 * @param types
	 *            the types to load into
	 * @return an initialized type declaration instance
	 * @throws IOException
	 *             in case of XML read/file open problems
	 */
	public static ITypeDeclaration create(String name, IFile file, TypeList types)
			throws IOException {
		if ("model".equals(file.getFileExtension())) {
			IGraph graph = TypeDeclaration.loadGraph(file);
			String form = graph.getFormalism().getName();
			if (form.equals("ITSComposite") || form.equals("Scalar Set Composite")|| form.equals("Circular Set Composite")) {
				return new CompositeTypeDeclaration(name, file, graph, types);
			} else {
				return new TypeDeclaration(name, file, graph, types);
			}
		} else if ("gal".equals(file.getFileExtension())) {
			return new GALTypeDeclaration(name, file, types);
		} else {
			throw new IOException("Unknown model extension "+ file.getFileExtension());
		}
	}
	
	
}
