package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.model.AttributeModel;
import fr.lip6.move.coloane.core.ui.files.IModelHandler;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.util.Stack;

import org.eclipse.draw2d.geometry.Point;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class InnerAttributeParser extends DefaultHandler {

	/** Object Stack */
	private Stack<Object> stack = new Stack<Object>();

	/** Various data */
	private StringBuilder data = new StringBuilder();

	public InnerAttributeParser(IAttribute start) {
		stack.push(start);
	}
	
	/** {@inheritDoc} */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {
		data.setLength(0);

		if (IModelHandler.ATTRIBUTE_MARKUP.equals(baliseName)) 
			startAttribute(attributes.getValue(IModelHandler.ATTRIBUTE_NAME_MARKUP), attributes);

	}

	/** {@inheritDoc} */
	@Override
	public final void characters(char[] ch, int start, int length) throws SAXException {
		data.append(this.deformat(new String(ch, start, length)));
	}

	/** {@inheritDoc} */
	@Override
	public final void endElement(String uri, String localName, String baliseName) throws SAXException {
		if (IModelHandler.ATTRIBUTE_MARKUP.equals(baliseName)) {
			endAttribute();
		}
	}

	/**
	 * Deal with special characters (unprotect)
	 * @param protectedTxt The text to clean up
	 * @return The text without any protection
	 */
	private String deformat(String protectedTxt) {
		String txt = protectedTxt;
		txt = txt.replaceAll("&amp;", "&"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&lt;", "<"); //$NON-NLS-1$ //$NON-NLS-2$
		txt = txt.replaceAll("&gt;", ">"); //$NON-NLS-1$ //$NON-NLS-2$
		return txt;
	}
	
	/**
	 * Parse an attribute.<br>
	 * The stack should contain the {@link IElement} to which this attribute will be attached to
	 * @param name Attribute name
	 * @param attributes Set of attributes attached to the current element
	 * @throws SAXException 
	 */
	private void startAttribute(String name, Attributes attributes) throws SAXException {
		Object o = stack.peek();
		IAttribute attribute = null;
		if (o instanceof IAttribute){
			IAttribute attr = (IAttribute) o;
			attribute = new AttributeModel(attr.getReference(), attr, (IAttributeFormalism)attr.getAttributeFormalism(),name);
			if (attribute.getAttributeFormalism() == null) {
				throw new SAXException();
			} else attr.addAttribute(attribute);
		}
		
		int x = Integer.parseInt(attributes.getValue(IModelHandler.ATTRIBUTE_X_MARKUP));
		int y = Integer.parseInt(attributes.getValue(IModelHandler.ATTRIBUTE_Y_MARKUP));
		Point location = new Point(x, y);

		stack.push(location);
		stack.push(attribute);
	}
	
	/** End of an attribute (assigned its value) */
	private void endAttribute() {
		IAttribute attribute = (IAttribute) stack.pop();
		Point location = (Point) stack.pop();
		String value = data.toString();
		if (attribute != null) {
			attribute.setValue(value);
			attribute.getGraphicInfo().setLocation(location);
		}
	}

}