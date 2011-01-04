package fr.lip6.move.coloane.extensions.exporttogml;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.logging.Logger;

import main.antlr3.fr.lip6.move.coloane.extension.DeclarativePartLexer;
import main.antlr3.fr.lip6.move.coloane.extension.DeclarativePartParserSN;
import main.antlr3.fr.lip6.move.coloane.extension.GuardLexer;
import main.antlr3.fr.lip6.move.coloane.extension.GuardParser;
import main.antlr3.fr.lip6.move.coloane.extension.ValuationLexerSNB;
import main.antlr3.fr.lip6.move.coloane.extension.ValuationParserSNB;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The main class for the export
 * 
 * @author Maximilien Colange
 *
 */
public class ExportToGML implements IExportTo {

	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.extensions.exporttogml"); //$NON-NLS-1$
	
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

		LOGGER.fine("starting export model");
		Writer out = null;
		ExtensionException exc = null;
		try {
			out = new BufferedWriter(new FileWriter(filePath));
			exportGraph(graph, out, monitor);
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		} finally {
			try {
				out.flush();
				out.close();
				if (exc != null) {
					throw exc;
				}
			} catch (IOException e) {
				throw new ExtensionException(e.getMessage());
			} catch (ExtensionException e) {
				// delete the export file
				File f = new File(filePath);
				boolean success = f.delete();
				if (!success) {
					throw new ExtensionException("Delete: deletion failed");
				}
				// re-throw the exception
				throw e;
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

		LOGGER.fine("GML preamble");

		// Export model attributes
		IAttribute declarativePart = graph.getAttribute("declaration");
		symbolTable = exportDeclarativePart(declarativePart.getValue(), out, monitor, gap);
		for (IAttribute attr : graph.getAttributes()) {
			if (attr != declarativePart) {
				try {
					exportAttribute(attr, out, monitor, gap, symbolTable);
				} catch (RecognitionException e) {
					String s = "Error in unparsed attribute of graph : " + attr.getName() + "\n";
					s = s + "Unfortunately for you, this error should not occur\n";
					s = s + "What the hell did you do ?\n";
					throw new ExtensionException(s);
				}
			}
		}
    	out.write("\n");
    	LOGGER.fine("graph's attributes : done");

    	//Export nodes
    	for (INode node : graph.getNodes()) {
    		exportNode(node, out, monitor, gap, symbolTable);
    		LOGGER.fine("export node : " + node.getId());
    	}
    	out.write("\n");

    	//Export Arcs
    	for (IArc arc : graph.getArcs()) {
    		exportArc(arc, out, monitor, gap, symbolTable);
    		LOGGER.fine("export arc : " + arc.getId());
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
	 * @throws RecognitionException if ANTLR throws an exception
	 */
	private void exportAttribute(IAttribute attr, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException, RecognitionException {
		if ("domain".equals(attr.getName())) {
			exportDomain(attr.getValue(), out, monitor, gap, symbols);
			LOGGER.finer("export domain");
		} else if ("guard".equals(attr.getName())) {
			exportGuard(attr.getValue(), out, monitor, gap, symbols);
			LOGGER.finer("export guard");
		} else if ("marking".equals(attr.getName())) {
			exportMarking(attr.getValue(), out, monitor, gap, symbols);
			LOGGER.finer("export marking");
		} else if ("valuation".equals(attr.getName())) {
			exportValuation(attr.getValue(), out, monitor, gap, symbols);
			LOGGER.finer("export valuation");
		} else {
			out.write(gap + "<attribute name=\"" + attr.getName() + "\">" + attr.getValue() + "</attribute>\n");
			LOGGER.finer("export generic attribute");
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
			throw new ExtensionException("Error in the declarative part at : " + e.token.getText());
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
	 * @throws RecognitionException if ANTLR throws an exception
	 */
	private void exportValuation(String value, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, RecognitionException {
		ValuationLexerSNB lexer = new ValuationLexerSNB(new ANTLRStringStream(value));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ValuationParserSNB parser = new ValuationParserSNB(tokens);
		out.write(parser.arcLabel(symbols, gap));
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
		if ("domain".equals(symbols.get(value)) || "class".equals(symbols.get(value)) || "".equals(value)) {
			out.write(gap + "<attribute name=\"domain\">" + value + "</attribute>\n");
		} else {
			throw new ExtensionException("Error parsing prod file : " + value + " has not been defined in domain or class declaration part");
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
	 * @throws RecognitionException if ANTLR throws an exception
	 */
	private void exportMarking(String value, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, RecognitionException {
		ValuationLexerSNB lexer = new ValuationLexerSNB(new ANTLRStringStream(value));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ValuationParserSNB parser = new ValuationParserSNB(tokens);
		out.write(parser.initMarking(symbols, gap));
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
		out.write(gap + "<node id=\"" + node.getId() + "\" nodeType=\"" + node.getNodeFormalism().getName() + "\">\n");
		for (IAttribute attr : node.getAttributes()) {
			try {
				exportAttribute(attr, out, monitor, gap + "\t", symbols);
			} catch (RecognitionException e) {
				String s;
				s = "Error in node " + node.getAttribute("name").getValue() + "\n";
				s = s + "in attribute " + attr.getName() + "\n";
				s = s + "at " + e.token.getText() + "\n";
				throw new ExtensionException(s);
			}
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
	 * @throws RecognitionException if ANTLR throws an exception
	 */
	private void exportGuard(String value, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, RecognitionException {
		GuardLexer lexer = new GuardLexer(new ANTLRStringStream(value));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		GuardParser parser = new GuardParser(tokens);
		out.write(parser.transitionGuard(symbols, gap));
	}

	/**
	 * Export an arc object
	 *
	 * @param arc arc to export
	 * @param out export in this writer
	 * @param monitor monitor the export
	 * @param gap gap
	 * @param symbols the table of symbols
	 * @throws IOException if the writer throws an exception
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportArc(IArc arc, Writer out, IProgressMonitor monitor, String gap, Map<String, String> symbols) throws IOException, ExtensionException {
		INode source = arc.getSource();
		INode target = arc.getTarget();
		out.write(gap + "<arc id=\"" + arc.getId() + "\" arcType=\"" + arc.getArcFormalism().getName() + "\" "
				+ "source=\"" + source.getId() + "\" "
				+ "target=\"" + target.getId() + "\">\n");
		try {
			for (IAttribute attr : arc.getAttributes()) {
				exportAttribute(attr, out, monitor, gap + "\t", symbols);
			}
		} catch (RecognitionException e) {
			String s;
			s = "Error on arc from " + source.getAttribute("name").getValue() + " to " + target.getAttribute("name").getValue() + "\n";
			s = s + "at : " + e.token.getText() + "\n";
			throw new ExtensionException(s);
		}
		out.write(gap + "</arc>\n");
	}
}
