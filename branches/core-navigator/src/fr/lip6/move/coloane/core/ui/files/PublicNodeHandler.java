package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Find public node in a xml file.
 *
 * @author Clément Démoulins
 */
public class PublicNodeHandler extends DefaultHandler {

	private IFile file;
	private INodeFormalism nodeFormalism;
	private PublicNode current;
	private List<PublicNode> publicNodes = new ArrayList<PublicNode>();

	private StringBuilder data;

	/**
	 * Class for public node with minimun data for link a node.
	 */
	public class PublicNode {
		private int id;
		private String name;
		private IFile file;

		/**
		 * @return id of the node
		 */
		public final int getId() {
			return id;
		}
		/**
		 * @param id id of the node
		 */
		public final void setId(int id) {
			this.id = id;
		}
		/**
		 * @return name or null if not set
		 */
		public final String getName() {
			return name;
		}
		/**
		 * @param name name of the node
		 */
		public final void setName(String name) {
			this.name = name;
		}
		/**
		 * @return file
		 */
		public final IFile getFile() {
			return file;
		}
		/**
		 * @param file file
		 */
		public final void setFile(IFile file) {
			this.file = file;
		}
		/** {@inheritDoc} */
		@Override
		public final String toString() {
			StringBuilder sb = new StringBuilder();
			if (file != null) {
				sb.append(file.getFullPath().toString());
			}
			sb.append("/"); //$NON-NLS-1$
			if (name != null) {
				sb.append(name);
			}
			sb.append(" [@" + id + "]"); //$NON-NLS-1$ //$NON-NLS-2$
			return sb.toString();
		}
	}

	/**
	 * @param file file
	 * @param nodeFormalism find public node for this formalism
	 */
	public PublicNodeHandler(IFile file, INodeFormalism nodeFormalism) {
		this.file = file;
		this.nodeFormalism = nodeFormalism;
	}

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if ("node".equals(name) && Boolean.valueOf(attributes.getValue("public"))) { //$NON-NLS-1$ //$NON-NLS-2$
			String nodeFormalismName = attributes.getValue("nodetype"); //$NON-NLS-1$
			if (nodeFormalismName != null && nodeFormalismName.equals(nodeFormalism.getName())) {
				final int id = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$
				current = new PublicNode();
				current.setFile(file);
				current.setId(id);
				publicNodes.add(current);
			}
		} else if (current != null && "attribute".equals(name)) { //$NON-NLS-1$
			String attrName = attributes.getValue("name"); //$NON-NLS-1$
			if (attrName != null && attrName.equals("name")) { //$NON-NLS-1$
				data = new StringBuilder();
			} else {
				data = null;
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void endElement(String uri, String localName, String name) throws SAXException {
		if (current != null && "attribute".equals(name) && data != null) { //$NON-NLS-1$
			current.setName(data.toString());
			data = null;
		} else if ("node".equals(name)) { //$NON-NLS-1$
			current = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void characters(char[] ch, int start, int length) throws SAXException {
		if (data != null) {
			data.append(this.deformat(new String(ch, start, length)));
		}
	}

	/**
	 * Gestion des caracteres speciaux (deprotection)
	 *
	 * @param protectedTxt Le texte a deproteger
	 * @return Le texte transforme et deprotege
	 */
	private String deformat(String protectedTxt) {
		String txt = protectedTxt;
		txt = txt.replaceAll("&amp;", "&"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&lt;", "<"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&gt;", ">"); //$NON-NLS-1$ //$NON-NLS-2$
		return txt;
	}

	/**
	 * @return List of public node.
	 */
	public final List<PublicNode> getPublicNodes() {
		return publicNodes;
	}

}
