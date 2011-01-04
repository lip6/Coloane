package fr.lip6.move.coloane.core.ui.files;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.factory.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.INodeFormalism;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Find interfaces in a XML file.
 *
 * @author Clément Démoulins
 */
public class InterfacesHandler extends DefaultHandler {
	private IFormalism formalism;

	private IFile file;
	private INodeFormalism nodeFormalism;
	private NodeInterface current;
	private List<NodeInterface> nodeInterfaces = new ArrayList<NodeInterface>();

	private StringBuilder data;

	/**
	 * Class for public node with minimum data for link a node.<br>
	 * <ol>
	 * <li>method toString() return a string to display to the users.
	 * <li>method getLink() must be use in the model to link a node to a public node.
	 * </ol>
	 */
	public static class NodeInterface {
		private int id;
		private String name;
		private IFile file;
		private ImageDescriptor icon;

		/**
		 * @return the icon
		 */
		public final ImageDescriptor getIcon() {
			return icon;
		}
		/**
		 * @param icon the icon to set
		 */
		public final void setIcon(ImageDescriptor icon) {
			this.icon = icon;
		}
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
		 * @return name or id if name is not set
		 */
		public final String getName() {
			if (name == null) {
				return "@" + id; //$NON-NLS-1$
			}
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
		/**
		 * @return String representation of a link to this node.
		 */
		public final String getLink() {
			return file.getFullPath() + "@" + id; //$NON-NLS-1$
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
	public InterfacesHandler(IFile file, INodeFormalism nodeFormalism) {
		this.file = file;
		this.nodeFormalism = nodeFormalism;
	}

	/**
	 * @param file file
	 */
	public InterfacesHandler(IFile file) {
		this(file, null);
	}

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if ("node".equals(name) && Boolean.valueOf(attributes.getValue("interface"))) { //$NON-NLS-1$ //$NON-NLS-2$
			String nodeFormalismName = attributes.getValue("nodetype"); //$NON-NLS-1$
			if (nodeFormalismName != null && (nodeFormalism == null || nodeFormalismName.equals(nodeFormalism.getName()))) {
				final int id = Integer.parseInt(attributes.getValue("id")); //$NON-NLS-1$
				current = new NodeInterface();
				current.setId(id);
				current.setFile(file);
				if (nodeFormalism != null) {
					current.setIcon(ImageDescriptor.createFromFile(Coloane.class, nodeFormalism.getGraphicalDescription().getIcon16px()));
				} else if (formalism != null) {
					IElementFormalism elementFormalism = formalism.getRootGraph().getElementFormalism(nodeFormalismName);
					if (elementFormalism != null) {
						current.setIcon(ImageDescriptor.createFromFile(Coloane.class, elementFormalism.getGraphicalDescription().getIcon16px()));
					}
				}
				nodeInterfaces.add(current);
			}
		} else if (current != null && "attribute".equals(name)) { //$NON-NLS-1$
			String attrName = attributes.getValue("name"); //$NON-NLS-1$
			if (attrName != null && attrName.equals("name")) { //$NON-NLS-1$
				data = new StringBuilder();
			} else {
				data = null;
			}
		} else if ("model".equals(name)) { //$NON-NLS-1$
			// Récupération du nom du formalisme
			String formalismName = attributes.getValue("formalism"); //$NON-NLS-1$
			try {
				formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
			} catch (IllegalArgumentException e) {
				throw new SAXException(e);
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
	public final List<NodeInterface> getInterfaces() {
		return nodeInterfaces;
	}

}
