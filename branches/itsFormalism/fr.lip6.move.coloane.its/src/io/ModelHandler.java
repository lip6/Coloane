package io;

import its.CompositeTypeDeclaration;
import its.Concept;
import its.TypeDeclaration;
import its.TypeList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Classe destinee a la lecture d'un fichier XML. La lecture du fichier permet la construction d'un modele
 * @author jbvoron
 *
 */
public class ModelHandler extends DefaultHandler {
	private final Logger logger = Logger.getLogger("fr.lip6.move.coloane.its"); //$NON-NLS-1$

	private Stack<Object> stack = new Stack<Object>();

	// Correspondance entre les id du document xml et celle des nouveaux objets.
	private Map<Integer, Object> ids = new HashMap<Integer, Object>();

	// object constructed
	private TypeList types;

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		// Balise MODEL
		if ("model".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("types".equals(baliseName)) { //$NON-NLS-1$
			// stack a new TypeList
			stack.push(new TypeList());
		} else if ("type".equals(baliseName)) { //$NON-NLS-1$
			startType(attributes);
		} else if ("concept".equals(baliseName)) { //$NON-NLS-1$
			handleConcept(attributes);
		} else if ("concepts".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else {
			logger.warning("Unknown XML tag in source file: "+ baliseName); //$NON-NLS-1$
		}
	}


	/** {@inheritDoc} */
	@Override
	public final void endElement(String uri, String localName, String baliseName) throws SAXException {
		// Balise MODEL
		if ("model".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else if ("types".equals(baliseName)) { //$NON-NLS-1$
			// assign the constructed TypeList
			this.types =  (TypeList) stack.pop();
		}else if ("type".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		}else if ("concept".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		}else if ("concepts".equals(baliseName)) { //$NON-NLS-1$
			// NOP
		} else {
			logger.warning("Unknown XML tag in source file: "+ baliseName); //$NON-NLS-1$
		}
	}

	private void handleConcept(Attributes attributes) throws SAXException {
		String name = attributes.getValue("name"); //$NON-NLS-1$
		int idEffective = Integer.parseInt(attributes.getValue("effective")); //$NON-NLS-1$		
		int idParent =Integer.parseInt( attributes.getValue("parent")); //$NON-NLS-1$		

		if (idEffective == -1)
			// concept is not assigned
			return;
		
		// Get parent with maximum safeguards
		CompositeTypeDeclaration parent;
		try {
			parent = (CompositeTypeDeclaration) ids.get(idParent);
		} catch (ClassCastException e) {
			throw new SAXException("Corrupted XML file, id "+idParent+" should refer to a composite type declaration");
		} 
		if (parent == null) {
			throw new SAXException("Corrupted XML file, Dangling parent type id "+idParent+ " in concept "+name);			
		}
		// Get effective with maximum safeguards
		TypeDeclaration effective;
		try {
			effective = (TypeDeclaration) ids.get(idEffective);
		} catch (ClassCastException e) {
			throw new SAXException("Corrupted XML file, effective id "+idParent+" should refer to a type declaration");
		} 
		if (effective == null) {
			throw new SAXException("Corrupted XML file, Dangling Effective type id "+idEffective+ " in concept "+name);			
		}
		// get the concept
		Concept concept = parent.getConcept(name);
		if (concept == null) {
			logger.warning("Concept effective definition which should belong to CompositeType " +parent+
					" does not exist in the actual model file. Ignoring Concept setting.");
		} else {
			concept.setEffective(effective);
		}
	}


	/**
	 * Analyze a type declaration
	 * @param attributes Les attributs attachée à la balise
	 * @throws SAXException if the referenced model file is bad.
	 */
	private void startType(Attributes attributes) throws SAXException {
		TypeList types =  (TypeList) stack.peek();

		int id = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$

		String name = attributes.getValue("name"); //$NON-NLS-1$
		String formalism = attributes.getValue("formalism"); //$NON-NLS-1$
		String filePath = attributes.getValue("path"); //$NON-NLS-1$

		IPath path = new Path(filePath);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		if ( (file==null) || (!file.exists()) ) {
			throw new SAXException("Could not open referenced file "+filePath,null);			
		}
		TypeDeclaration type;
		try {
			type = TypeDeclaration.create(name,file,types);
		} catch (IOException e) {			
			throw new SAXException("Could not open referenced file "+filePath,null);
		}
		if (! formalism.equals(type.getTypeType())) {
			String errmsg = "Model formalism type for file "+path+" does not match file contents.\n " +
			"Expected type "+formalism+" read type "+type.getTypeType();
			logger.fine(errmsg); //$NON-NLS-1$

			throw new SAXParseException(errmsg, null);
		}
		// store type in hash
		ids.put(id, type);
		types.addTypeDeclaration(type);
	}

	/**
	 * @return the type list loaded from the XML file
	 */
	public final TypeList getTypes() {
		return types;
	}
}
