package fr.lip6.move.coloane.extension.exporttogml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The main class for the export
 * 
 * @author colange
 *
 */
public class ExportToGML implements IExportTo {

	/** (non-Javadoc)
	 * @see fr.lip6.move.coloane.core.extensions.IExportTo#export(fr.lip6.move.coloane.interfaces.model.IGraph, java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
	 */
	/**
	 * Performs the export
	 * 
	 * @param graph the graph to be exported
	 * @param filePath the export file name
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	public final void export(IGraph graph, String filePath, IProgressMonitor monitor)
			throws ExtensionException {

		Writer out = null;
		try {
			out = new BufferedWriter(new FileWriter(filePath));
			exportGraph(graph, out, monitor);
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new ExtensionException(e.getMessage());
			}
		}
	}

	/**
	 * Export a graph object
	 *
	 * @param graph graph to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @throws IOException if the writer throw an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportGraph(IGraph graph, Writer out, IProgressMonitor monitor) throws IOException, ExtensionException {
		String gap = "\t";
		Map<String, String> symbolTable = null;

		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
		out.write("<model formalismUrl=\"snb.fml\"\n");
		out.write("    xmlns=\"http://gml.lip6.fr/model\">\n");

		// Export model attributes
		IAttribute declarativePart = graph.getAttribute("declaration");
		symbolTable = exportDeclarativePart(declarativePart.getValue(), out, monitor, gap);
		for (IAttribute attr : graph.getAttributes()) {
			if (attr != declarativePart) {
				exportAttribute(attr, out, monitor, gap, symbolTable);
			}
		}
    	out.write("\n");

    	//Export nodes
    	for (INode node : graph.getNodes()) {
    		exportNode(node, out, monitor, gap, symbolTable);
    	}
    	out.write("\n");

    	//Export Arcs
    	for (IArc arc : graph.getArcs()) {
    		exportArc(arc, out, monitor, gap, symbolTable);
    	}
    	out.write("\n");

    	out.write("</model>");
	}

	/**
	 * Export an attribute object
	 *
	 * @param attr attribute to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @param gap gap
	 * @param symbols table of symbols
	 * @throws IOException if the writer throw an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportAttribute(IAttribute attr, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		if ("domain".equals(attr.getName())) {
			exportDomain(attr.getValue(), out, monitor, gap, symbols);
		} else if ("guard".equals(attr.getName())) {
			exportGuard(attr.getValue(), out, monitor, gap, symbols);
		} else if ("marking".equals(attr.getName())) {
			exportMarking(attr.getValue(), out, monitor, gap, symbols);
		} else if ("valuation".equals(attr.getName())) {
			exportValuation(attr.getValue(), out, monitor, gap, symbols);
		} else {
			out.write(gap + "<attribute name=\"" + attr.getName() + "\">" + attr.getValue() + "</attribute>\n");
		}
	}
	
	/**
	 * Export the declarative part
	 * 
	 * @return the symbols table
	 * @param value declarative part to export
	 * @param out export in this writer
	 * @param monitor monitors the export
	 * @param gap gap
	 * @throws IOException if the writer throw an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private Map<String, String> exportDeclarativePart(String value, Writer out, IProgressMonitor monitor, String gap) throws IOException, ExtensionException {
		DeclarativePartParserSN parser;
		try {
			DeclarativePartLexer lexer = new DeclarativePartLexer(new ANTLRStringStream(value));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			parser = new DeclarativePartParserSN(tokens);
			out.write(parser.declaration(gap));
		} catch (RecognitionException e) {
			throw new ExtensionException("Error parsing prod file " + e.getMessage());
		}
		return parser.getSymbols();
	}
	
	/**
	 * Export the valuation of an arc
	 * 
	 * @param value valuation to export
	 * @param out export in this writer
	 * @param monitor monitors the export
	 * @param gap gap
	 * @param symbols the table of symbols
	 * @throws IOException if the writer throws an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportValuation(String value, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		try {
			ValuationLexerSNB lexer = new ValuationLexerSNB(new ANTLRStringStream(value));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ValuationParserSNB parser = new ValuationParserSNB(tokens);
			out.write(parser.arcLabel(symbols, gap));
		} catch (RecognitionException e) {
			throw new ExtensionException("Error parsing prod file " + e.getMessage());
		}
	}
	
	/**
	 * Export the domain of a place
	 * 
	 * @param value domain to export
	 * @param out export in this writer
	 * @param monitor monitors the export
	 * @param gap gap
	 * @param symbols the table of symbols
	 * @throws IOException if the writer throws an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportDomain(String value, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		if ("domain".equals(symbols.get(value))) {
			out.write(gap + "<attribute name=\"domain\">" + value + "</attribute>\n");
		} else {
			throw new ExtensionException("Error parsing prod file : " + value + " has not been defined in domain declaration part");
		}
	}
	
	/**
	 * Export a marking
	 * 
	 * @param value marking to export
	 * @param out export in this writer
	 * @param monitor monitors the export
	 * @param gap gap
	 * @param symbols the table of symbols
	 * @throws IOException if the writer throws an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportMarking(String value, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		try {
			ValuationLexerSNB lexer = new ValuationLexerSNB(new ANTLRStringStream(value));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ValuationParserSNB parser = new ValuationParserSNB(tokens);
			out.write(parser.initMarking(symbols, gap));
		} catch (RecognitionException e) {
			throw new ExtensionException("Error parsing prod file " + e.getMessage());
		}
	}

	/**
	 * Export a node object
	 *
	 * @param node node to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @param gap gap
	 * @param symbols the table of symbols
	 * @throws IOException if the writer throw an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportNode(INode node, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		out.write(gap + "<node id=\"" + node.getNodeFormalism().getName() + node.getId() + "\" nodeType=\"" + node.getNodeFormalism().getName() + "\">\n");
		for (IAttribute attr : node.getAttributes()) {
			exportAttribute(attr, out, monitor, gap + "\t", symbols);
		}
		out.write(gap + "</node>\n");
	}
	
	/**
	 * Export a guard
	 * 
	 * @param value the guard to export
	 * @param out export in this writer
	 * @param monitor monitors the export
	 * @param gap gap
	 * @param symbols the table of symbols
	 * @throws IOException if the writer throws an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportGuard(String value, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		try {
			GuardLexer lexer = new GuardLexer(new ANTLRStringStream(value));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			GuardParser parser = new GuardParser(tokens);
			out.write(parser.transitionGuard(symbols, gap));
		} catch (RecognitionException e) {
			throw new ExtensionException("Error parsing prod file " + e.getMessage());
		}
	}

	/**
	 * Export an arc object
	 *
	 * @param arc arc to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @param gap gap
	 * @param symbols the table of symbols
	 * @throws IOException if the writer throw an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportArc(IArc arc, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		INode source = arc.getSource();
		INode target = arc.getTarget();
		out.write(gap + "<arc id=\"" + arc.getArcFormalism().getName() + arc.getId() + "\" arcType=\"" + arc.getArcFormalism().getName() + "\" "
				+ "source=\"" + source.getNodeFormalism().getName() + source.getId() + "\" "
				+ "target=\"" + target.getNodeFormalism().getName() + target.getId() + "\">\n");
		for (IAttribute attr : arc.getAttributes()) {
			exportAttribute(attr, out, monitor, gap + "\t", symbols);
		}
		out.write(gap + "</arc>\n");
	}
}
