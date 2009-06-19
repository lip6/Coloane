package fr.lip6.move.coloane.core.ui.files;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Find link to public node in models. All link are returned, even the broken links.
 * 
 * @author Clément Démoulins
 */
public class NodeLinksHandler extends DefaultHandler {
	
	private List<NodeLink> links = new ArrayList<NodeLink>();
	private NodeLink current;
	
	private StringBuilder data;

	/**
	 * Object representation for a link.
	 */
	public static class NodeLink {
		private final int sourceId;
		private String sourceName;
//		private final IFile sourceFile;

		private final int targetId;
		private final String path;

		/**
		 * @param sourceFile 
		 * @param sourceId The source id
		 * @param link A string in this format : path@targetId
		 */
		public NodeLink(int sourceId, String link) {
			String[] args = link.split("@"); //$NON-NLS-1$
			if (args.length != 2) {
				throw new IllegalArgumentException(link);
			}
//			this.sourceFile = sourceFile;
			this.sourceId = sourceId;
			this.targetId = Integer.valueOf(args[1]);
			this.path = args[0];
		}
		/** {@inheritDoc} */
		@Override
		public final String toString() {
			return path + "@" + targetId; //$NON-NLS-1$
		}
		/**
		 * @return the sourceFile
		 */
//		public final IFile getSourceFile() {
//			return sourceFile;
//		}
		/**
		 * @return The id of the source node
		 */
		public final int getSourceId() {
			return sourceId;
		}
		/**
		 * @return if source name is not <code>null</code> return the sourceName else return @sourceId
		 */
		public final String getSourceName() {
			if (sourceName == null) {
				return "@" + sourceId; //$NON-NLS-1$
			}
			return sourceName;
		}
		/**
		 * @param sourceName the sourceName to set
		 */
		public final void setSourceName(String sourceName) {
			this.sourceName = sourceName;
		}
		/**
		 * @return The id of the target interface (a node)
		 */
		public final int getTargetId() {
			return targetId;
		}
		/**
		 * @return path
		 */
		public final String getPath() {
			return path;
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if ("node".equals(name) && attributes.getValue("id") != null && attributes.getValue("link") != null) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			current = new NodeLink(Integer.valueOf(attributes.getValue("id")), attributes.getValue("link")); //$NON-NLS-1$ //$NON-NLS-2$
			links.add(current);
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
			current.setSourceName(data.toString());
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
	 * @return all link to a public node without check the target of the link.
	 */
	public final List<NodeLink> getNodeLinks() {
		return links;
	}

}
