/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extensions.exporttogml;

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
import java.util.HashMap;
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
 */
public class ExportToGML implements IExportTo {

	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.extensions.exporttogml"); //$NON-NLS-1$
	
	/**
	 * Map to convert Coloane formalism names to FML url.
	 */
	private final Map<String, String> formalismMap = new HashMap<String, String>();

	/**
	 * Initialize the formalismMap
	 */
	public ExportToGML() {
		// TODO: Put the associated FML URL into Coloane Formalism extension
		formalismMap.put("PT-Net", "http://alligator.lip6.fr/pt-net.fml");
		formalismMap.put("CPN", "http://alligator.lip6.fr/s-net.fml");
		formalismMap.put("SNB", "http://alligator.lip6.fr/snb.fml");
	}
	
	/**
	 * Performs the export
	 * 
	 * @param graph the graph to be exported
	 * @param filePath the export file name
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	public final void export(IGraph graph, String filePath, IProgressMonitor monitor) throws ExtensionException {
		try {
			export(graph, new FileWriter(filePath), monitor);
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		}
	}
	
	/**
	 * Performs the export
	 * 
	 * @param graph the graph to be exported
	 * @param writer Writer to write the gml model
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	public final void export(IGraph graph, Writer writer, IProgressMonitor monitor) throws ExtensionException {
		LOGGER.fine("starting export model");
		Writer out = null;
		ExtensionException exc = null;
		try {
			out = new BufferedWriter(writer);
			monitor.beginTask("export", graph.getArcs().size() + graph.getNodes().size() + 1);
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
		Map<String, String> symbolTable = new HashMap<String, String>();

		String fmlUrl;
		if (formalismMap.containsKey(graph.getFormalism().getId())) {
			fmlUrl = formalismMap.get(graph.getFormalism().getId());
		} else {
			fmlUrl = "http://alligator.lip6.fr/unknown";
		}

		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
		out.write("<model formalismUrl=\"" + fmlUrl + "\"\n");
		out.write("    xmlns=\"http://gml.lip6.fr/model\">\n");

		LOGGER.fine("GML preamble");

		// Export model attributes
		IAttribute declarativePart = graph.getAttribute("declaration");
		if (declarativePart != null) {
			if (!declarativePart.getValue().equals("")) {
				symbolTable = exportDeclarativePart(declarativePart.getValue(), out, monitor, gap);
			}
		}
		monitor.worked(1);
		for (IAttribute attr : graph.getAttributes()) {
			if (!attr.getName().equals("declaration")) {
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
    		monitor.worked(1);
    		LOGGER.fine("export node : " + node.getId());
    	}
    	out.write("\n");

    	//Export Arcs
    	for (IArc arc : graph.getArcs()) {
    		exportArc(arc, out, monitor, gap, symbolTable);
    		monitor.worked(1);
    		LOGGER.fine("export arc : " + arc.getId());
    	}
    	out.write("\n");

    	out.write("</model>");
    	monitor.done();
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
		if ("domain".equals(symbols.get(value)) || "domain_bag".equals(symbols.get(value)) || "class".equals(symbols.get(value)) || "".equals(value)) {
			out.write(gap + "<attribute name=\"domain\"><attribute name=\"type\">" + value + "</attribute></attribute>\n");
		} else {
			throw new ExtensionException("Error parsing model : the domain \"" + value + "\" has not been defined in domain or class declaration part. Its value is " + symbols.get(value));
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
