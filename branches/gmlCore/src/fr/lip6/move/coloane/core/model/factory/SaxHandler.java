package fr.lip6.move.coloane.core.model.factory;

import fr.lip6.move.coloane.core.formalisms.Formalism;
import fr.lip6.move.coloane.core.formalisms.elements.ArcFormalism;
import fr.lip6.move.coloane.core.formalisms.elements.AttributeFormalism;
import fr.lip6.move.coloane.core.formalisms.elements.ElementFormalism;
import fr.lip6.move.coloane.core.formalisms.elements.NodeFormalism;

import java.util.Deque;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author Elodie Banel
 */
public class SaxHandler extends DefaultHandler {
	
	private Formalism form;
	private AttributeFormalism lastComplex = null;
	private ElementFormalism lastElement = null;
	private Deque<String> last = new LinkedList<String>();
	
	/**
	 * @param form The formalism this xml parser will be defining
	 */
	public SaxHandler(Formalism form) {
		this.form = form;
	}
	
	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String qName, Attributes attributes) {

		if (last.isEmpty()) {

			//the first tag must be the formalism
			if (qName.equals("formalism")) { //$NON-NLS-1$
				form.setName(attributes.getValue("name")); //$NON-NLS-1$
				String abstr = attributes.getValue("abstract"); //$NON-NLS-1$
				if (abstr != null && abstr.equals("true")) { //$NON-NLS-1$
					form.setAbstract(true);
				} else {
					form.setAbstract(false);
				}
			}

		} else {

			if (qName.equals("nodeType")) { //$NON-NLS-1$
				NodeFormalism node = new NodeFormalism(attributes.getValue("name"), form); //$NON-NLS-1$
				form.addElement(node);
				lastElement = node;
			}

			if (qName.equals("arcType")) { //$NON-NLS-1$
				ArcFormalism arc = new ArcFormalism(attributes.getValue("name"), form); //$NON-NLS-1$
				form.addElement(arc);
				lastElement = arc;
			}

			if (qName.equals("complexAttribute")) { //$NON-NLS-1$
				//recuperer la reference.
				String ref = attributes.getValue("refType"); //$NON-NLS-1$
				if (ref == null) {
					ref = ""; //the attribute belongs to no element //$NON-NLS-1$
				}
				AttributeFormalism attribute = new AttributeFormalism(attributes.getValue("name"), ref, form); //$NON-NLS-1$
				form.addAttribute(attribute);
				lastComplex = attribute;
			}

			if (qName.equals("leafAttribute")) { //$NON-NLS-1$
				String ref = attributes.getValue("refType"); //$NON-NLS-1$
				if (ref == null) {
					ref = ""; //the attribute belongs to no element //$NON-NLS-1$
				}
				String defaultval = attributes.getValue("defaultValue"); //$NON-NLS-1$
				AttributeFormalism attribute;
				if (defaultval == null) {
					attribute = new AttributeFormalism(attributes.getValue("name"), ref, form); //$NON-NLS-1$
				} else {
					attribute = new AttributeFormalism(attributes.getValue("name"), ref, defaultval, form); //$NON-NLS-1$
				}
				form.addAttribute(attribute);
			}
	
			if (lastComplex != null) {
				if (qName.equals("child")) { //$NON-NLS-1$

					String smin = attributes.getValue("minOccurs"); //$NON-NLS-1$
					int min = 1;
					if (smin != null) {
						min = Integer.valueOf(smin);
					}

					String smax = attributes.getValue("maxOccurs"); //$NON-NLS-1$
					int max = -1;
					if (smax != null) {
						max = Integer.valueOf(smax);
					}

					lastComplex.addAttribute(attributes.getValue("refName"), min, max); //$NON-NLS-1$
				}
			}

			if (lastElement != null) {
				if (qName.equals("ref")) { //$NON-NLS-1$

					String smin = attributes.getValue("minOccurs"); //$NON-NLS-1$
					int min = 0;
					if (smin != null) {
						min = Integer.valueOf(smin);
					}

					String smax = attributes.getValue("maxOccurs"); //$NON-NLS-1$
					int max = -1;
					if (smax != null) {
						max = Integer.valueOf(smax);
					}

					lastElement.addReference(attributes.getValue("href"), min, max); //$NON-NLS-1$
				}
			}
		}

		last.add(qName);
	}
	
	/** {@inheritDoc} */
	@Override
	public final void endElement(String uri, String localName, String qName) {

		if (qName.equals("complexAttribute")) { //$NON-NLS-1$
			lastComplex = null;
		}
		if (qName.equals("arcType") || qName.equals("nodeType")) { //$NON-NLS-1$ //$NON-NLS-2$
			lastElement = null;
		}
		last.removeLast();
	}
	
	/** {@inheritDoc} */
	@Override
	public void characters(char[] ch, int start, int length) {
		//I don't think anything should be here as of now.
	}
	
	/**
	 * @return The formalism (to be used after running a parsing function using this handler)
	 */
	public final Formalism getFormalism() {
		return form;
	}

}
