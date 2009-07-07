package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.extensions.exportToPGF.Exporter;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Converter for PT-Net formalism.
 */
public final class PTNetConverter implements Converter {

	/**
	 * Convert a PT-Net attribute to a textual representation.
	 * Only replace '\n' characters by '\\' as required in (La)Tex.
	 * @param attribute The attribute to convert.
	 * @return Its textual representation.
	 */
	public String convert(IAttribute attribute) {
		String value = attribute.getValue();
		// Replace new lines with LaTeX \\ only un multiline attributes:
		if (attribute.getAttributeFormalism().isMultiLine()) {
			// 8 '\' because StringTemplate or something else interprets them!
			value = value.replaceAll("\n", " \\\\\\\\ \n");
		}
		if (attribute.getName().equals("valuation") || attribute.getName().equals("marking")) {
			value = "\\ensuremath{\\begin{matrix} " + value + " \\end{matrix}}";
		}
		return value;
	}

	/** {@inheritDoc} */
	public void setGraphicalDescription(StringTemplate query, INode node) {
		System.err.println(node.getNodeFormalism().getName());
		if (node.getNodeFormalism().getName().equals("place")) {
			query.setAttribute("placeSize", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
		} else if (node.getNodeFormalism().getName().equals("transition")) {
			query.setAttribute("transitionHeight", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
			query.setAttribute("transitionWidth", node.getNodeFormalism().getGraphicalDescription().getWidth()   * Exporter.getRatio());
		}
	}

	/** {@inheritDoc} */
	public String getName(IAttribute attribute) {
		return attribute.getName();
	}
}
