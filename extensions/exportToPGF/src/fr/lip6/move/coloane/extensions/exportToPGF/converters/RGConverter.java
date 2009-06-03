package fr.lip6.move.coloane.extensions.exportToPGF.converters;

import fr.lip6.move.coloane.extensions.exportToPGF.Exporter;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.INode;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Converter for PT-Net formalism.
 */
public final class RGConverter implements Converter {

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
		return value;
	}

	/**
	 * @param query The query.
	 * @param node The node
	 */
	public void setGraphicalDescription(StringTemplate query, INode node) {
		if (node.getNodeFormalism().getName().equals("state")) {
			query.setAttribute("stateSize", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
		} else if (node.getNodeFormalism().getName().equals("initial_state")) {
			query.setAttribute("initial_stateSize", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
		} else if (node.getNodeFormalism().getName().equals("terminal_state")) {
			query.setAttribute("terminal_stateSize", node.getNodeFormalism().getGraphicalDescription().getHeight() * Exporter.getRatio());
		}
	}


	/** {@inheritDoc} */
	public String getName(IAttribute attribute) {
		if (attribute.getName().equals("author(s)")) {
			return "authors";
		}
		return attribute.getName();
	}
}
