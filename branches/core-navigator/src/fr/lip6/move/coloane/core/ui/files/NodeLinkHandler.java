package fr.lip6.move.coloane.core.ui.files;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Find link to public node in models. All link are returned, even the broken links.
 * 
 * @author Clément Démoulins
 */
public class NodeLinkHandler extends DefaultHandler {
	
	private List<NodeLink> links = new ArrayList<NodeLink>();
	
	/**
	 * Object representation for a link.
	 */
	public static class NodeLink {
		private final int id;
		private final String path;

		/**
		 * @param link path@id
		 */
		public NodeLink(String link) {
			String[] args = link.split("@"); //$NON-NLS-1$
			if (args.length != 2) {
				throw new IllegalArgumentException(link);
			}
			this.id = Integer.valueOf(args[1]);
			this.path = args[1];
		}
		/**
		 * @return id
		 */
		public final int getId() {
			return id;
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
		if ("node".equals(name) && attributes.getValue("link") != null) { //$NON-NLS-1$ //$NON-NLS-2$
			links.add(new NodeLink(attributes.getValue("link"))); //$NON-NLS-1$
		}
	}

	/**
	 * @return all link to a public node without check the target of the link.
	 */
	public final List<NodeLink> getNodeLinks() {
		return links;
	}

}
