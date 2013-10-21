/**
 * :q * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager
 * 
 * Official contacts: coloane@lip6.fr http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extensions.exporttogrml;

import fr.lip6.move.coloane.extensions.exporttogrml.antlr.DeclarativePartLexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.DeclarativePartParserSN;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.DeclarativePartParserSN.declaration_return;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ExpressionParserCosmosLexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ExpressionParserCosmosParser;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.GuardLexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.GuardParser;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ValuationLexerSNB;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ValuationParserSNB;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The implementation of the GrML Export for PT, CPN and SNB nets.
 * 
 * @author Maximilien Colange
 */
public class SNBExport
    implements IGrMLExport {

    /**
     * allows convenient multi-value initialization: "new STAttrMap().put(...).put(...)"
     * 
     * comes from ANTLR-generated code
     */
    public static class STAttrMap
        extends HashMap<String, Object> {

        /**
         * Explicit definition to please java compiler Using value from class HashMap
         */
        private static final long serialVersionUID = 362498820763181265L;

        /**
         * 
         * @param attrName
         *        the key to which a new value is associated
         * @param value
         *        the value associated to the key
         * @return the updated map
         */
        public final
            STAttrMap
            put(String attrName,
                Object value) {
            super.put(attrName, value);
            return this;
        }

        /**
         * 
         * @param attrName
         *        the key to which a new value is associated
         * @param value
         *        the value associated to the key
         * @return the updated map
         */
        public final
            STAttrMap
            put(String attrName,
                int value) {
            super.put(attrName, new Integer(value));
            return this;
        }
    }

    /** The logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.extensions.exporttogrml"); //$NON-NLS-1$

    private String formalism;
    private StringTemplateGroup templates;

    /**
     * The constructor
     * 
     * @throws IOException
     *         if the initialization of the StringTemplateGroup fails
     */
    public SNBExport()
        throws IOException {
        initTemplateGroup();
    }

    /**
     * {@inheritDoc}
     */
    public final
        void
        export(IGraph graph,
               Writer writer,
               String fmlUrl,
               IProgressMonitor monitor)
            throws ExtensionException {
        formalism = graph.getFormalism()
                         .getId();

        LOGGER.fine("starting export model");
        monitor.beginTask("export", graph.getArcs()
                                         .size() + graph.getNodes()
                                                        .size() + 1);
        StringTemplate modelST = exportGraph(graph, fmlUrl, monitor);

        try {
            writer.write(modelST.toString());
        } catch (IOException e) {
            throw new ExtensionException(e.getMessage());
        }
    }

    /**
     * Initialize the StringTemplate group
     * 
     * @throws IOException
     *         if the StringTemplate Group file is not found
     */
    private
        void
        initTemplateGroup()
            throws IOException {
        InputStream groupFileIS = this.getClass()
                                      .getResourceAsStream("/resources/SNBFML.stg");
        if (groupFileIS == null) {
            throw new IOException("String Template Group File not found");
        }
        InputStreamReader groupFileR = new InputStreamReader(groupFileIS);
        templates = new StringTemplateGroup(groupFileR);
        groupFileR.close();
    }

    /**
     * Export a graph object
     * 
     * @param graph
     *        graph to export
     * @param fmlUrl
     *        the URL of the target formalism
     * @param monitor
     *        monitor the export
     * @return the result of the export, as a StringTemplate
     * @throws ExtensionException
     *         if the parser throws an exception
     */
    private
        StringTemplate
        exportGraph(IGraph graph,
                    String fmlUrl,
                    IProgressMonitor monitor)
            throws ExtensionException {
        monitor.beginTask("Export to GrML", 1 + graph.getNodes()
                                                     .size() + graph.getArcs()
                                                                    .size());

        Map<String, String> symbolTable = new HashMap<String, String>();

        monitor.setTaskName("Create preamble");

        StringTemplate result = templates.getInstanceOf("modelBalise", new STAttrMap().put("version", "1.0")
                                                                                      .put("encoding", "UTF-8")
                                                                                      .put("form", fmlUrl));

        LOGGER.fine("GrML preamble");

        // Export model attributes
        monitor.setTaskName("Export model attributes");
        IAttribute declarativePart = graph.getAttribute("declaration");
        if (declarativePart == null)
            declarativePart = graph.getAttribute("declarations");// for SWN try to read declarations
        if (declarativePart != null) {
            if (!declarativePart.getValue()
                                .equals("")) {
                symbolTable = exportDeclarativePart(declarativePart.getValue(), result, monitor);
            }
        }
        for (IAttribute attr : graph.getAttributes()) {
            if (!attr.getName()
                     .equals("declaration") && !attr.getName()
                                                    .equals("declarations")) {
                try {
                    exportAttribute(attr, result, monitor, symbolTable);
                } catch (RecognitionException e) {
                    String s = "Error in unparsed attribute of graph : " + attr.getName() + "\n";
                    s = s + "Unfortunately for you, this error should not occur\n";
                    s = s + "What the hell did you do ?\n";
                    throw new ExtensionException(s);
                }
            }
        }

        monitor.worked(1);
        LOGGER.fine("graph's attributes : done");

        // Export nodes
        monitor.setTaskName("Export nodes");
        for (INode node : graph.getNodes()) {
            exportNode(node, result, monitor, symbolTable);
            monitor.worked(1);
            LOGGER.fine("export node : " + node.getId());
        }

        // Export Arcs
        monitor.setTaskName("Export arcs");
        for (IArc arc : graph.getArcs()) {
            exportArc(arc, result, monitor, symbolTable);
            monitor.worked(1);
            LOGGER.fine("export arc : " + arc.getId());
        }

        monitor.done();
        return result;
    }

    /**
     * Exports a real formula
     * 
     * @param attr
     *        the attribute containing the formula to parse
     * @param currentST
     *        the StringTemplate to update with the result of the export
     * @param symbols
     *        the table of symbols
     * @throws RecognitionException
     *         if the parsing fails
     */
    private
        void
        exportRealFormula(IAttribute attr,
                          StringTemplate currentST,
                          Map<String, String> symbols)
            throws RecognitionException {
        ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
        parser.setTemplateLib(templates);

        StringTemplate tmp = templates.getInstanceOf("balise");
        tmp.setAttribute("name", attr.getName());
        tmp.setAttribute("content", parser.realExprW(symbols).st);
        currentST.setAttribute("content", tmp);
    }

    /**
     * Export a formula
     * 
     * @param attr
     *        the string containing the formula to parse
     * @param currentST
     *        the StringTemplate to update with the result of the export of the formula
     * @param symbols
     *        the table of symbols
     * @throws RecognitionException
     *         if the parsing fails Added the 12/09/2013 by Benoit Barbot for SWN
     */
    private
        void
        exportIntFormula(IAttribute attr,
                         StringTemplate currentST,
                         Map<String, String> symbols)
            throws RecognitionException {
        ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
        parser.setTemplateLib(templates);

        StringTemplate tmp = templates.getInstanceOf("balise");
        tmp.setAttribute("name", attr.getName());
        tmp.setAttribute("content", parser.realExprW(symbols).st);
        currentST.setAttribute("content", tmp);
    }

    /**
     * Export an attribute object
     * 
     * @param attr
     *        attribute to export
     * @param currentST
     *        the result being built
     * @param monitor
     *        monitor the export
     * @param symbols
     *        table of symbols
     * @throws ExtensionException
     *         if the attribute is a domain and its export throws an exception
     * @throws RecognitionException
     *         if ANTLR throws an exception (guards, marking, valuation)
     */
    private
        void
        exportAttribute(IAttribute attr,
                        StringTemplate currentST,
                        IProgressMonitor monitor,
                        Map<String, String> symbols)
            throws ExtensionException, RecognitionException {
        if ("domain".equals(attr.getName())) {
            exportDomain(attr.getValue(), currentST, monitor, symbols);
            LOGGER.finer("export domain");
        } else if ("guard".equals(attr.getName())) {
            exportGuard(attr.getValue(), currentST, monitor, symbols);
            LOGGER.finer("export guard");
        } else if ("marking".equals(attr.getName())) {
            exportMarking(attr.getValue(), currentST, monitor, symbols);
            LOGGER.finer("export marking");
        } else if ("valuation".equals(attr.getName())) {
            exportValuation(attr.getValue(), currentST, monitor, symbols);
            LOGGER.finer("export valuation");
        } else if (attr.getName()
                       .equals("distribution")) { // all the following have added by Benoît Barbot to export SWN
            ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
            parser.setTemplateLib(templates);
            currentST.setAttribute("content", parser.distribution(symbols));
            LOGGER.finer("export distribution");
        } else if (attr.getName()
                       .equals("priority") | attr.getName()
                                                 .equals("weight")) {
            exportRealFormula(attr, currentST, symbols);
        } else if (attr.getName()
                       .equals("flow")) {
            ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
            parser.setTemplateLib(templates);
            currentST.setAttribute("content", parser.flow(symbols));
            LOGGER.finer("export flow");
        } else if (attr.getName()
                       .equals("service")) {
            exportIntFormula(attr, currentST, symbols);
            LOGGER.finer("export service");
        } else {
            STAttrMap stAttrMap = new STAttrMap();
            if ("note".equals(attr.getName())) {
                stAttrMap.put("name", "comments");
            } else {
                stAttrMap.put("name", attr.getName());
            }
            stAttrMap.put("x", attr.getGraphicInfo()
                                   .getLocation()
                                   .x());
            stAttrMap.put("y", attr.getGraphicInfo()
                                   .getLocation()
                                   .y());
            stAttrMap.put("content", attr.getValue());

            StringTemplate tmp = templates.getInstanceOf("locbalise", stAttrMap);
            currentST.setAttribute("content", tmp);
            LOGGER.finer("export generic attribute");
        }
    }

    /**
     * Export the declarative part
     * 
     * @return the symbols table
     * @param value
     *        declarative part to export
     * @param modelST
     *        the result being built
     * @param monitor
     *        monitors the export
     * @throws ExtensionException
     *         if the parser throws an exception
     */
    private
        Map<String, String>
        exportDeclarativePart(String value,
                              StringTemplate modelST,
                              IProgressMonitor monitor)
            throws ExtensionException {
        DeclarativePartParserSN parser;
        try {
            value = value.replaceAll("\\.\\.", " .. ")
                         .replaceAll("\\[", "[ ")
                         .replaceAll("\\]", " ]");
            DeclarativePartLexer lexer = new DeclarativePartLexer(new ANTLRStringStream(value));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new DeclarativePartParserSN(tokens);
            parser.setTemplateLib(templates);
            declaration_return declaration = parser.declaration(formalism);
            modelST.setAttribute("content", declaration);
        } catch (RecognitionException e) {
            e.printStackTrace();
            throw new ExtensionException("Error in the declarative part at : " + value.split("\n")[e.line - 1]);
        }
        return parser.getSymbols();
    }

    /**
     * Export the valuation of an arc
     * 
     * @param value
     *        valuation to export
     * @param currentST
     *        the result being built
     * @param monitor
     *        monitors the export
     * @param symbols
     *        the table of symbols
     * @throws RecognitionException
     *         if ANTLR throws an exception
     */
    private
        void
        exportValuation(String value,
                        StringTemplate currentST,
                        IProgressMonitor monitor,
                        Map<String, String> symbols)
            throws RecognitionException {
        if ("PT-Net".equals(formalism)) { // Not a clean way to resolve this issue
            int valuation;
            try {
                valuation = Integer.valueOf(value);
                StringTemplate tmp = templates.getInstanceOf("balise", new STAttrMap().put("name", "valuation")
                                                                                      .put("content", valuation));
                currentST.setAttribute("content", tmp);
            } catch (NumberFormatException e) {
                LOGGER.warning("Cannot parse valuation: " + value);
            }
        } else {
            ValuationLexerSNB lexer = new ValuationLexerSNB(new ANTLRStringStream(value));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ValuationParserSNB parser = new ValuationParserSNB(tokens);
            parser.setTemplateLib(templates);
            currentST.setAttribute("content", parser.arcLabel(symbols));
        }
    }

    /**
     * Export the domain of a place
     * 
     * @param value
     *        domain to export
     * @param currentST
     *        the result being built
     * @param monitor
     *        monitors the export
     * @param symbols
     *        the table of symbols
     * @throws ExtensionException
     *         if the parser throws an exception
     */
    private
        void
        exportDomain(String value,
                     StringTemplate currentST,
                     IProgressMonitor monitor,
                     Map<String, String> symbols)
            throws ExtensionException {
        if ("domain".equals(symbols.get(value)) || "domain_bag".equals(symbols.get(value)) ||
            "class".equals(symbols.get(value)) || "".equals(value)) {
            StringTemplate tmp0 = templates.getInstanceOf("balise", new STAttrMap().put("name", "type")
                                                                                   .put("content", value));
            StringTemplate tmp = templates.getInstanceOf("balise", new STAttrMap().put("name", "domain")
                                                                                  .put("content", tmp0));
            currentST.setAttribute("content", tmp);
        } else {
            throw new ExtensionException("Error parsing model : the domain \"" + value +
                                         "\" has not been defined in domain or class declaration part. Its value is " +
                                         symbols.get(value));
        }
    }

    /**
     * Export a marking
     * 
     * @param value
     *        marking to export
     * @param currentST
     *        the result being built
     * @param monitor
     *        monitors the export
     * @param symbols
     *        the table of symbols
     * @throws RecognitionException
     *         if ANTLR throws an exception
     */
    private
        void
        exportMarking(String value,
                      StringTemplate currentST,
                      IProgressMonitor monitor,
                      Map<String, String> symbols)
            throws RecognitionException {
        if ("PT-Net".equals(formalism)) { // Not a clean way to resolve this issue
            int marking;
            try {
                marking = Integer.valueOf(value);
                StringTemplate tmp = templates.getInstanceOf("balise", new STAttrMap().put("name", "marking")
                                                                                      .put("content", marking));
                currentST.setAttribute("content", tmp);
            } catch (NumberFormatException e) {
                LOGGER.warning("Cannot parse marking: " + value);
            }
        } else {
            ValuationLexerSNB lexer = new ValuationLexerSNB(new ANTLRStringStream(value));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ValuationParserSNB parser = new ValuationParserSNB(tokens);
            parser.setTemplateLib(templates);
            currentST.setAttribute("content", parser.initMarking(symbols));
        }
    }

    /**
     * Export a node object
     * 
     * @param node
     *        node to export
     * @param modelST
     *        the result being built
     * @param monitor
     *        monitor the export
     * @param symbols
     *        the table of symbols
     * @throws ExtensionException
     *         if the parser throws an exception
     */
    private
        void
        exportNode(INode node,
                   StringTemplate modelST,
                   IProgressMonitor monitor,
                   Map<String, String> symbols)
            throws ExtensionException {
        STAttrMap stAttrMap = new STAttrMap();
        stAttrMap.put("id", node.getId());
        stAttrMap.put("type", node.getNodeFormalism()
                                  .getName());
        stAttrMap.put("x", node.getGraphicInfo()
                               .getLocation()
                               .x());
        stAttrMap.put("y", node.getGraphicInfo()
                               .getLocation()
                               .y());

        StringTemplate currentST = templates.getInstanceOf("node", stAttrMap);
        for (IAttribute attr : node.getAttributes()) {
            try {
                exportAttribute(attr, currentST, monitor, symbols);
            } catch (RecognitionException e) {
                String s;
                s = "Error in " + node.getNodeFormalism()
                                      .getName() + " \"" + node.getAttribute("name")
                                                               .getValue() + "\"\n";
                s = s + "in attribute " + attr.getName() + "\n";
                s = s + "at " + e.token.getText() + "\n";
                throw new ExtensionException(s);
            }
        }
        modelST.setAttribute("content", currentST);
    }

    /**
     * Export a guard
     * 
     * @param value
     *        the guard to export
     * @param currentST
     *        the result being built
     * @param monitor
     *        monitors the export
     * @param symbols
     *        the table of symbols
     * @throws RecognitionException
     *         if ANTLR throws an exception
     */
    private
        void
        exportGuard(String value,
                    StringTemplate currentST,
                    IProgressMonitor monitor,
                    Map<String, String> symbols)
            throws RecognitionException {
        GuardLexer lexer = new GuardLexer(new ANTLRStringStream(value));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GuardParser parser = new GuardParser(tokens);
        parser.setTemplateLib(templates);
        currentST.setAttribute("content", parser.transitionGuard(symbols));
    }

    /**
     * Export an arc object
     * 
     * @param arc
     *        arc to export
     * @param modelST
     *        the result being built
     * @param monitor
     *        monitor the export
     * @param symbols
     *        the table of symbols
     * @throws ExtensionException
     *         if the parser throws an exception
     */
    private
        void
        exportArc(IArc arc,
                  StringTemplate modelST,
                  IProgressMonitor monitor,
                  Map<String, String> symbols)
            throws ExtensionException {
        INode source = arc.getSource();
        INode target = arc.getTarget();
        StringTemplate currentST = templates.getInstanceOf("arc");
        currentST.setAttribute("id", arc.getId());
        currentST.setAttribute("type", arc.getArcFormalism()
                                          .getName());
        currentST.setAttribute("source", source.getId());
        currentST.setAttribute("target", target.getId());
        try {
            for (IAttribute attr : arc.getAttributes()) {
                exportAttribute(attr, currentST, monitor, symbols);
            }
        } catch (RecognitionException e) {
            String s;
            s = "Error on arc from " + source.getNodeFormalism()
                                             .getName() + " \"" + source.getAttribute("name")
                                                                        .getValue();
            s = s + "\" to " + target.getNodeFormalism()
                                     .getName() + " \"" + target.getAttribute("name")
                                                                .getValue() + "\"\n";
            s = s + "at : " + e.token.getText() + "\n";
            throw new ExtensionException(s);
        }
        modelST.setAttribute("content", currentST);
    }
}
