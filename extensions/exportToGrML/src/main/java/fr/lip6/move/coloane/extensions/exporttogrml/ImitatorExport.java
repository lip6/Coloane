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
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extensions.exporttogrml;

import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ActionCosmosParserLexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ActionCosmosParserParser;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.DeclarativeParserCosmosLexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.DeclarativeParserCosmosParser;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.DeclarativeParserPTALexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.DeclarativeParserPTAParser;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ExpressionParserCosmosLexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.ExpressionParserCosmosParser;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.HASLformulaParserLexer;
import fr.lip6.move.coloane.extensions.exporttogrml.antlr.HASLformulaParserParser;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.neoppod.grml.GrmlCheckExecutables;
import fr.lip6.move.neoppod.grml.Model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.sf.saxon.s9api.SaxonApiException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.InputSource;

/**
 * A class to export the Parametric timed automata formalism to GrML
 * 
 * @author Etienne    Andre
 * @author Benoît     Barbot
 * @author Maximilien Colange
 *
 */
public class ImitatorExport implements IGrMLExport {

	/**
	 * allows convenient multi-value initialization:
	 * "new STAttrMap().put(...).put(...)"
	 * 
	 * comes from ANTLR-generated code
	 */
	public static class STAttrMap extends HashMap<String, Object> {

		/**
		 * Explicit definition to please java compiler
		 * Using value from class HashMap
		 */
		private static final long serialVersionUID = 362498820763181265L;
		/**
		 * 
		 * @param attrName the key to which a new value is associated
		 * @param value the value associated to the key
		 * @return the updated map
		 */
		public final STAttrMap put(String attrName, Object value) {
			super.put(attrName, value);
			return this;
		}

		/**
		 * 
		 * @param attrName the key to which a new value is associated
		 * @param value the value associated to the key
		 * @return the updated map
		 */
		public final STAttrMap put(String attrName, int value) {
			super.put(attrName, new Integer(value));
			return this;
		}
	}

	private StringTemplateGroup templates;

	private String fmlXml;

	/**
	 * The constructor
	 * 
	 * @throws IOException if the StringTemplateGroup initialization fails
	 */
	public ImitatorExport() throws IOException {
		initTemplateGroup();
	}

	/**
	 * Initialize the StringTemplate group
	 * @throws IOException if the StringTemplate Group file is not found
	 */
	private void initTemplateGroup() throws IOException {
		InputStream groupFileIS = this.getClass().getResourceAsStream("/resources/SNBFML.stg");
		if (groupFileIS == null) {
			throw new IOException("String Template Group File not found");
		}
		InputStreamReader groupFileR = new InputStreamReader(groupFileIS);
		templates = new StringTemplateGroup(groupFileR);
		groupFileR.close();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void export(IGraph graph, Writer writer, String fmlUrl, IProgressMonitor monitor)
			throws ExtensionException {
		monitor.beginTask("export", graph.getArcs().size() + graph.getNodes().size() + 1);
		StringTemplate modelST = exportGraph(graph, fmlUrl, monitor);

		try {
			writer.write(modelST.toString());
		} catch (IOException e) {
			throw new ExtensionException(e.getMessage());
		}
	}

	/**
	 * FIXME: add a comment
	 * @param fmlUrl FIXME: add a comment
	 */
	private void initInputSource(String fmlUrl) {
		try {
			StringReader content = new StringReader("<?xml version='1.0' encoding='UTF-8'?>\n<model formalismUrl='" + fmlUrl + "' xmlns='http://cosyverif.org/ns/model'/>");
			Model fakeModel = new Model(new GrmlCheckExecutables(), content);
			fmlXml = fakeModel.getFormalism().getData();
			//fmlXmlSource = new InputSource(new StringReader(fmlXml));
		} catch (SaxonApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * FIXME: add a comment
	 * @param request FIXME: add a comment
	 * @return FIXME: add a comment
	 */
	private boolean xPathRequest(String request) {
		try {
			NamespaceContext ctx = new NamespaceContext() {
				public String getNamespaceURI(String prefix) {
					if (prefix.equals("fml")) {
						return "http://cosyverif.org/ns/formalism";
					} else {
						return null;
					}
				}

				// Dummy implementation - not used!
				public Iterator<?> getPrefixes(String val) {
					return null;
				}

				// Dummy implementation - not used!
				public String getPrefix(String uri) {
					return null;
				}
			};

			XPath xpath = XPathFactory.newInstance().newXPath();
			xpath.setNamespaceContext(ctx);
			InputSource fmlXmlSource = new InputSource(new StringReader(fmlXml));
			String hasDeclaration = xpath.evaluate(request, fmlXmlSource);
			return !hasDeclaration.equals("");

		} catch (XPathExpressionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			return false;
		}

	}

	/**
	 * FIXME: add a comment
	 * @param s FIXME: add a comment
	 * @return FIXME: add a comment
	 */
	private boolean hasAttribute(String s) {
		return xPathRequest("/fml:formalism/fml:complexAttribute[@name='" + s + "']");
	}

//	// TODO: redo this function using a list
//	private boolean hasSubSubAttribute(String attr1, String attr2, String attr3) {
//		return xPathRequest("/fml:formalism/fml:complexAttribute[@name='"+ attr1 +"']/fml:complexAttribute[@name='"+ attr2 +"']/fml:complexAttribute[@name='"+ attr3 +"']");
//	}


	/**
	 * The function that exports the graph
	 * 
	 * @param graph the graph to export
	 * @param fmlUrl the URL of the target FML
	 * @param monitor to monitor the export
	 * @return a string template
	 * @throws ExtensionException if the export fails
	 */
	private StringTemplate exportGraph(IGraph graph, String fmlUrl, IProgressMonitor monitor) throws ExtensionException {
		initInputSource(fmlUrl);

		monitor.setTaskName("Create preamble");

		StringTemplate result = templates.getInstanceOf("modelBalise", new STAttrMap().put("version", "1.0").put("encoding", "UTF-8").put("form", fmlUrl));

		Map<String, String> symbolTable = new HashMap<String, String>();

		// Export model attributes
		monitor.setTaskName("Export model attributes");

		if (hasAttribute("declaration")) {
			// Case IMITATOR (most probably)
			if (hasAttribute("discretes")) {
				exportDeclarativePTA(graph, result, monitor, symbolTable);
				// Case COSMOS (most probably)
			} else {
				IAttribute declarativePart = graph.getAttribute("declarations");
				if (declarativePart != null) {
					if (!declarativePart.getValue().equals("")) {
						symbolTable = exportDeclarativePart(declarativePart.getValue(), result, monitor);
					}
				} else {
					throw new ExtensionException("Expecting a 'declarations' field required by the formalism");
				}
			}
		}

		// Case HASL formula
		if (hasAttribute("HASLFormula")) {
			IAttribute HASLPart = graph.getAttribute("HASL Formula");
			if (HASLPart != null) {
				if (!HASLPart.getValue().equals("")) {
					try {
						exportAttribute(HASLPart, result, monitor, symbolTable);
					} catch (RecognitionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				throw new ExtensionException("Expecting a 'HASL Formula' field required by the formalism");
			}
		}




		/*for (IAttribute attr : graph.getAttributes()) {
			if (!attr.getName().equals("declarations")) {
				try {
					exportAttribute(attr, result, monitor, symbolTable);
				} catch (RecognitionException e) {
					String s = "Error in unparsed attribute of graph : " + attr.getName() + "\n";
					s = s + "Unfortunately for you, this error should not occur\n";
					s = s + "What the hell did you do ?\n";
					throw new ExtensionException(s);
				}
			}
		}*/


		/*// Specific code for PTAs
		if(hasAttribute("discretes")) {
			exportDeclarativePTA(graph, result,monitor);
		}*/

		monitor.worked(1);

		//Export nodes
		monitor.setTaskName("Export nodes");
		for (INode node : graph.getNodes()) {
			exportNode(node, result, monitor, symbolTable);
			monitor.worked(1);
		}

		//Export Arcs
		monitor.setTaskName("Export arcs");
		for (IArc arc : graph.getArcs()) {
			exportArc(arc, result, monitor, symbolTable);
			monitor.worked(1);
		}

		monitor.done();
		return result;
	}

	/**
	 * Export a formula
	 * @param attr the string containing the formula to parse
	 * @param currentST the StringTemplate to update with the result of the export of the formula
	 * @param symbols the table of symbols
	 * @throws RecognitionException if the parsing fails
	 */
	private void exportIntFormula(IAttribute attr, StringTemplate currentST, Map<String, String> symbols) throws RecognitionException {
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
	 * Exports a real formula
	 * 
	 * @param attr the attribute containing the formula to parse
	 * @param currentST the StringTemplate to update with the result of the export
	 * @param symbols the table of symbols
	 * @throws RecognitionException if the parsing fails
	 */
	private void exportRealFormula(IAttribute attr, StringTemplate currentST, Map<String, String> symbols) throws RecognitionException {
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
	 * Exports a boolean formula
	 * 
	 * @param attr the attribute containing the formula to parse
	 * @param currentST the StringTemplate to update with the result of the export
	 * @param symbols the table of symbols
	 * @throws RecognitionException if the parsing fails
	 */
	// WARNING: the FML formalism name and the Coloane "zone" name must have the same name!!!!!!
	private void exportBoolFormula(IAttribute attr, StringTemplate currentST, Map<String, String> symbols) throws RecognitionException {
		ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
		parser.setTemplateLib(templates);

		StringTemplate tmp = templates.getInstanceOf("balise");
		tmp.setAttribute("name", attr.getName());
		tmp.setAttribute("content", parser.boolExprW(symbols).st);
		currentST.setAttribute("content", tmp);
	}

	


	/**
	 * Export the declarative part
	 * 
	 * @return the symbols table
	 * @param value declarative part to export
	 * @param modelST the result being built
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	private Map<String, String> exportDeclarativePart(String value, StringTemplate modelST, IProgressMonitor monitor) throws ExtensionException {

		DeclarativeParserCosmosLexer lexer = new DeclarativeParserCosmosLexer(new ANTLRStringStream(value));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DeclarativeParserCosmosParser parser = new DeclarativeParserCosmosParser(tokens);
		parser.setTemplateLib(templates);

		try {
			modelST.setAttribute("content", parser.const_list(hasAttribute("constants"), hasAttribute("variables")));
		} catch (RecognitionException e) {
			throw new ExtensionException("Fail to parse Declarative part");
		}

		return parser.getSymbols();
	}
	
	/**
	 * Export the declaration PTA part
	 * 
	 * @param graph graph whose declarative part is exported
	 * @param modelST the result being built
	 * @param monitor monitors the export
	 * @param symbolTable 
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportDeclarativePTA(IGraph graph, StringTemplate modelST, IProgressMonitor monitor, Map<String, String> symbolTable) throws ExtensionException {
		// la liste des blocks XML que je vais générer
		List<StringTemplate> tmpConsts = new ArrayList<StringTemplate>();
		List<StringTemplate> tmpVars = new ArrayList<StringTemplate>();

		// je prends la partie discrete
		IAttribute discretePart = graph.getAttribute("Discrete");
		if (discretePart != null) {
			// s'il y a quelque chose a parser
			if (!discretePart.getValue().equals("")) {
				// j'ouvre lexer, parser ...
				DeclarativeParserPTALexer lexer = new DeclarativeParserPTALexer(new ANTLRStringStream(discretePart.getValue()));
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				DeclarativeParserPTAParser parser = new DeclarativeParserPTAParser(tokens);
				parser.setTemplateLib(templates);

				try {
					// et là je parse
					// d'après la grammaire, la règle "name_list" renvoie un StringTemplate que je stocke
					tmpVars.add((StringTemplate) parser.name_list("discretes", "discrete").getTemplate());
				} catch (RecognitionException e) {
					throw new ExtensionException("Fail to parse Declarative part");
				}
			}
		}

		// rebelote, je prends la partie clocks
		IAttribute clockPart = graph.getAttribute("Clocks");
		if (clockPart != null) {
			// s'il y a quelque chose a parser
			if (!clockPart.getValue().equals("")) {
				// j'ouvre lexer, parser ...
				DeclarativeParserPTALexer lexer = new DeclarativeParserPTALexer(new ANTLRStringStream(clockPart.getValue()));
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				DeclarativeParserPTAParser parser = new DeclarativeParserPTAParser(tokens);
				parser.setTemplateLib(templates);
				try {
					// et là je parse
					// d'après la grammaire, la règle "name_list" renvoie un StringTemplate que je stocke
					tmpVars.add((StringTemplate) parser.name_list("clocks", "clock").getTemplate());
				} catch (RecognitionException e) {
					throw new ExtensionException("Fail to parse clocks part");
				}
			}
		}

		// rebelote, je prends la partie parameters
		IAttribute parametersPart = graph.getAttribute("Parameters");
		if (parametersPart != null) {
			// s'il y a quelque chose a parser
			if (!parametersPart.getValue().equals("")) {
				// j'ouvre lexer, parser ...
				DeclarativeParserPTALexer lexer = new DeclarativeParserPTALexer(new ANTLRStringStream(parametersPart.getValue()));
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				DeclarativeParserPTAParser parser = new DeclarativeParserPTAParser(tokens);
				parser.setTemplateLib(templates);
				try {
					// et là je parse
					// d'après la grammaire, la règle "name_list" renvoie un StringTemplate que je stocke
					tmpConsts.add((StringTemplate) parser.name_list("parameters", "parameter").getTemplate());
				} catch (RecognitionException e) {
					throw new ExtensionException("Fail to parse parameters part");
				}
			}
		}
		// maintenant j'ai une liste de deux StringTemplate, qui correspondent à deux blocs GrML : une pour le bloc "discretes" et l'autre pour le bloc "clocks"
		// je vais les encapsuler dans une nouvelle balise "declaration"

		// je crée un StringTemplate de balise XML
		StringTemplate vars = templates.getInstanceOf("balise");
		vars.setAttribute("name", "variables");
		// son contenu c'est les deux StringTemplates du dessus : encapsulation réussie
		vars.setAttribute("content", tmpVars);

		// je crée un StringTemplate de balise XML
		StringTemplate consts = templates.getInstanceOf("balise");
		consts.setAttribute("name", "constants");
		consts.setAttribute("content", tmpConsts);

		// je crée un StringTemplate de balise XML
		StringTemplate decl = templates.getInstanceOf("balise");
		// son attribut "name" est "declaration
		decl.setAttribute("name", "declaration");
		// ETIENNE veut ajouter l'arbre "variables" (defini dans vars) ET l'arbre "constants" (defini dans consts) sous "declaration"
		// MAXIMILIEN : d'apres l'API a la !@#$% des StringTemplates (http://www.stringtemplate.org/api3/index.html), "setAttribute" est trompeur
		// cette methode gere toute seule la presence de plusieurs fils, c'est plutot un "addElementToAttribute"
		// donc la, tu vas avoir
		// <attribute name="declaration">
		// 	<attribute name="variables">
		//		les variables
		//	</attribute>
		// 	<attribute name="constants">
		//		les constants
		//	</attribute>
		// </attribute>
		decl.setAttribute("content", vars);
		decl.setAttribute("content", consts);
		
		
		
		// Initial constraint (for IMITATOR)
		// WARNING: the FML formalism name and the Coloane "zone" name must have the same name!!!!!!
		if (hasAttribute("initialConstraint")) {
			IAttribute attr = graph.getAttribute("initialConstraint");

			if (attr != null) {
				if (!attr.getValue().equals("")) {
					try {
						exportBoolFormula(attr, decl, symbolTable);
					} catch (RecognitionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} //else throw new ExtensionException("Expecting an initial constraint field required by the formalism");
		}
		
		// je pousse le tout dans le modèle
		modelST.setAttribute("content", decl);

		// NB : ici on ne renvoie rien, mais on peut envisager de récupérer les noms des clocks et variables sous forme de listes de String par exemple
		// le mieux pour faire ça, c'est de le stocker dans le parser pendant le parse, et de le récupérer à la fin (cf le .g de Cosmos par exemple)
	}
	
	/**
	 * Export the discrete declaration part
	 * 
	 * @return the symbols table
	 * @param value declarative part to export
	 * @param modelST the result being built
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	/*private void exportDiscretePart(String value, StringTemplate modelST, IProgressMonitor monitor) throws ExtensionException {

		DeclarativeParserPTALexer lexer = new DeclarativeParserPTALexer(new ANTLRStringStream(value));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DeclarativeParserPTAParser parser = new DeclarativeParserPTAParser(tokens);
		parser.setTemplateLib(templates);

		try {
				modelST.setAttribute("content", parser.name_list("variables", "discretes", "discrete"));
			} catch (RecognitionException e) {
			throw new ExtensionException("Fail to parse Declarative part");
		}

	}*/



	/**
	 * Export the declarative part
	 * 
	 * @return the symbols table
	 * @param value declarative part to export
	 * @param modelST the result being built
	 * @param monitor monitors the export
	 * @throws ExtensionException if the parser throws an exception
	 */
	/*
	private void exportDeclarativePart2(String value,Map<String, String> symbolTable, StringTemplate modelST, IProgressMonitor monitor) throws ExtensionException {

		DeclarativeParserCosmosLexer lexer = new DeclarativeParserCosmosLexer(new ANTLRStringStream(value));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DeclarativeParserCosmosParser parser = new DeclarativeParserCosmosParser(tokens);
		parser.setTemplateLib(templates);

		try {
			modelST.setAttribute("content", parser.var_list(symbolTable));
		} catch (RecognitionException e) {
			throw new ExtensionException("Fail to parse Declarative part");
		}

	}
	 */

	/**
	 * Export a node object
	 *
	 * @param node node to export
	 * @param modelST the result being built
	 * @param monitor monitor the export
	 * @param symbols the table of symbols
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportNode(INode node, StringTemplate modelST, IProgressMonitor monitor, Map<String, String> symbols) throws ExtensionException {
		STAttrMap stAttrMap = new STAttrMap();
		stAttrMap.put("id", node.getId());
		stAttrMap.put("type", node.getNodeFormalism().getName());
		stAttrMap.put("x", node.getGraphicInfo().getLocation().x());
		stAttrMap.put("y", node.getGraphicInfo().getLocation().y());

		StringTemplate currentST = templates.getInstanceOf("node", stAttrMap);
		for (IAttribute attr : node.getAttributes()) {
			try {
				exportAttribute(attr, currentST, monitor, symbols);
			} catch (RecognitionException e) {
				String s;
				s = "Error in " + node.getNodeFormalism().getName() + " \"" + node.getAttribute("name").getValue() + "\"\n";
				s = s + "in attribute " + attr.getName() + "\n";
				s = s + "at " + e.token.getText() + "\n";
				throw new ExtensionException(s);
			}
		}
		modelST.setAttribute("content", currentST);
	}

	/**
	 * Export an arc object
	 *
	 * @param arc arc to export
	 * @param modelST the result being built
	 * @param monitor monitor the export
	 * @param symbols the table of symbols
	 * @throws ExtensionException if the parser throws an exception
	 */
	private void exportArc(IArc arc, StringTemplate modelST, IProgressMonitor monitor, Map<String, String> symbols) throws ExtensionException {
		INode source = arc.getSource();
		INode target = arc.getTarget();
		StringTemplate currentST = templates.getInstanceOf("arc");
		currentST.setAttribute("id", arc.getId());
		currentST.setAttribute("type", arc.getArcFormalism().getName());
		currentST.setAttribute("source", source.getId());
		currentST.setAttribute("target", target.getId());
		try {
			for (IAttribute attr : arc.getAttributes()) {
				exportAttribute(attr, currentST, monitor, symbols);
			}
		} catch (RecognitionException e) {
			String s;
			s = "Error on arc from " + source.getNodeFormalism().getName() + " \"" + source.getAttribute("name").getValue();
			s = s + "\" to " + target.getNodeFormalism().getName() + " \"" + target.getAttribute("name").getValue() + "\"\n";
			s = s + "at : " + e.token.getText() + "\n";
			throw new ExtensionException(s);
		}
		modelST.setAttribute("content", currentST);
	}

	/**
	 * Export an attribute object
	 *
	 * @param attr attribute to export
	 * @param currentST the result being built
	 * @param monitor monitor the export
	 * @param symbols table of symbols
	 * @throws ExtensionException if the attribute is a domain and its export throws an exception
	 * @throws RecognitionException if ANTLR throws an exception (guards, marking, valuation)
	 */
	private void exportAttribute(IAttribute attr, StringTemplate currentST, IProgressMonitor monitor, Map<String, String> symbols) throws ExtensionException, RecognitionException {
		if (attr.getName().equals("distribution")) {
			ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
			parser.setTemplateLib(templates);
			currentST.setAttribute("content", parser.distribution(symbols));
		} else if (attr.getName().equals("marking")
				| attr.getName().equals("valuation")
				| attr.getName().equals("service")) {
			exportIntFormula(attr, currentST, symbols);
		} else if (attr.getName().equals("priority")
				| attr.getName().equals("weight")) {
			exportRealFormula(attr, currentST, symbols);
		} else if (attr.getName().equals("guard")
				| attr.getName().equals("invariant")) {
			exportBoolFormula(attr, currentST, symbols);
		} else if (attr.getName().equals("action")) {
			ActionCosmosParserLexer lexer = new ActionCosmosParserLexer(new ANTLRStringStream(attr.getValue()));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ActionCosmosParserParser parser = new ActionCosmosParserParser(tokens);
			parser.setTemplateLib(templates);
			currentST.setAttribute("content", parser.action());
		} else if (attr.getName().equals("HASL Formula")) {
			StringTemplate tmp = templates.getInstanceOf("balise");
			tmp.setAttribute("name", "HASLFormula");

			HASLformulaParserLexer lexer = new HASLformulaParserLexer(new ANTLRStringStream(attr.getValue()));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			HASLformulaParserParser parser = new HASLformulaParserParser(tokens);
			parser.setTemplateLib(templates);
			tmp.setAttribute("content", parser.haslForm());
			currentST.setAttribute("content", tmp);
		} else if (attr.getName().equals("update")) {
			ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
			parser.setTemplateLib(templates);
			currentST.setAttribute("content", parser.update(symbols));
		} else if (attr.getName().equals("flow")) {
			ExpressionParserCosmosLexer lexer = new ExpressionParserCosmosLexer(new ANTLRStringStream(attr.getValue()));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			ExpressionParserCosmosParser parser = new ExpressionParserCosmosParser(tokens);
			parser.setTemplateLib(templates);
			currentST.setAttribute("content", parser.flow(symbols));
		} else {
			StringTemplate tmp = templates.getInstanceOf("balise");
			tmp.setAttribute("name", attr.getName());
			tmp.setAttribute("content", attr.getValue());
			currentST.setAttribute("content", tmp);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<String> getFormalisms() {
		List<String> result = new ArrayList<String>();
		result.add("Cosmos");
		return result;
	}

}
