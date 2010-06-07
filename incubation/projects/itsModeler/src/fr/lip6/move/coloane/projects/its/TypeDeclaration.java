package fr.lip6.move.coloane.projects.its;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.projects.its.antlrutil.ErrorReporter;
import fr.lip6.move.coloane.projects.its.expression.Constant;
import fr.lip6.move.coloane.projects.its.expression.EvaluationContext;
import fr.lip6.move.coloane.projects.its.expression.IEvaluationContext;
import fr.lip6.move.coloane.projects.its.expression.IVariable;
import fr.lip6.move.coloane.projects.its.expression.IVariableBinding;
import fr.lip6.move.coloane.projects.its.expression.Infinity;
import fr.lip6.move.coloane.projects.its.expression.IntegerExpression;
import fr.lip6.move.coloane.projects.its.expression.Variable;
import fr.lip6.move.coloane.projects.its.expression.parser.IntegerExpressionParserLexer;
import fr.lip6.move.coloane.projects.its.expression.parser.IntegerExpressionParserParser;
import fr.lip6.move.coloane.projects.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.projects.its.obs.SimpleObservable;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.resources.IFile;

/**
 * A type declaration, base class for {@link CompositeTypeDeclaration}.
 * Handles :
 * * load from XML of a graph (factory style)
 * * parameters (variables in the models)
 * * model role : notification of updates
 * @author Yann
 *
 */
public class TypeDeclaration extends SimpleObservable implements ISimpleObserver {
	private String typeName;
	private IFile typeFile;
	/** The underlying coloane Graph */
	private IGraph graph;
	private Set<String> labels = null;
	private TypeList typeList;
	private EvaluationContext context;
	private Map<IAttribute, IntegerExpression> attribs = new HashMap<IAttribute, IntegerExpression>();

	/**
	 * Protected ctor used to initialize a td.
	 * @param typeName name of type
	 * @param modelFile file (resource) holding the model
	 * @param graph graph loaded from file
	 * @param types types to add to (parent)
	 */
	protected TypeDeclaration(String typeName, IFile modelFile, IGraph graph, TypeList types) {
		this.typeName = typeName;
		typeFile = modelFile;
		this.graph = graph;
		typeList = types;
	}

	/**
	 * Accessor, return the (unique in the types list) name of this type.
	 * @return the name of this type
	 */
	public final String getTypeName() {
		return typeName;
	}

	/**
	 * Update the type name, notify observers.
	 * @param typeName the new name
	 */
	public final void setTypeName(String typeName) {
		if (!this.typeName.equals(typeName)) {
			this.typeName = typeName;
			notifyObservers();
		}
	}

	/**
	 * Workspace path to file resource.
	 * @return path to the resource
	 */
	public final String getTypePath() {
		return typeFile.getFullPath().toString();
	}
	/**
	 * The resource this type is built upon.
	 * @return the file resource of the coloane model
	 */
	public final IFile getTypeFile() {
		return typeFile;
	}
	/**
	 * The formalism name of this type's graph.
	 * @return qualified formalism name
	 */
	public final String getTypeType() {
		return graph.getFormalism().getName();
	}

	/**
	 * The graph of the underlying coloane model.
	 * @return the graph
	 */
	public final IGraph getGraph() {
		return graph;
	}
	/**  Load a IGraph from a file
	 * @param typePath the file to load from
	 * @return 	the coloane graph model
	 * @throws IOException if any problems during parse or file load.
	 */
	private static IGraph loadGraph(IFile typePath) throws IOException {
		// Construction d'un modele en memoire a partir de se representation en XML
		IGraph graph = ModelLoader.loadGraphFromXML(typePath);

		// Si le chargement a �chou�, on annule l'ouverture de l'�diteur
		if (graph == null) {
			throw new IOException("Load from XML file failed !");
		}
		return graph;
	}

	/**
	 * Factory operation to build concrete TypeDescriptions
	 * @param name name of the resulting type
	 * @param file the base file containing a coloane model
	 * @param types the types to load into
	 * @return an initialized type declaration instance
	 * @throws IOException in case of XML read/file open problems
	 */
	public static TypeDeclaration create(String name, IFile file, TypeList types) throws IOException {
		IGraph graph = loadGraph(file);
		String form = graph.getFormalism().getName();
		if (form.equals("ITSComposite") || form.equals("Scalar Set Composite")) {
			return new CompositeTypeDeclaration(name, file, graph, types);
		} else {
			return new TypeDeclaration(name, file, graph, types);
		}
	}

	/**
	 * Compute the interface of a type.
	 * @return the set of public labels of this type (ITS action alphabet)
	 * 
	 */
	protected Set<String> computeLabels() {
		Set<String> labels = new HashSet<String>();
		Collection<INode> nodes = graph.getNodes();
		if (graph.getFormalism().getName().equals("Time Petri Net")) {
			for (INode node : nodes) {
				if ("transition".equals(node.getNodeFormalism().getName())) {
					IAttribute visibility = node.getAttribute("visibility");
					if ("public".equals(visibility.getValue())) {
						IAttribute atts = node.getAttribute("label");
						labels.add(atts.getValue());
					}
				}
			}
		}
		return labels;
	}

	/**
	 * Handle caching of computeLabels.
	 * @return the interface (ITS action alphabet) of this type
	 */
	public final Collection<String> getLabels() {
		if (labels == null) {
			labels = computeLabels();
		}
		return labels;
	}

	/** Specifies if all the concepts of this type have an effective realization.
	 * @return true for a basic type declaration
	 */
	public boolean isSatisfied() {
		return true;
	}

	/**
	 * Return the parent types list.
	 * @return the parent types instance
	 */
	public final TypeList getTypeList() {
		return typeList;
	}

	/**
	 * Clear any references to this type (see behavior in composite type decl.
	 * @param t the type to be removed
	 */
	public void unsetTypeDeclaration(TypeDeclaration t) {
		// NOP
	}

	/**
	 * Return the evaluation context that allow to resolve all integer expressions in the model.
	 * side effect: load the attributes that use these int expressions if not done already.
	 * @return the integer parameters of this type
	 */
	public final IEvaluationContext getParameters() {
		if (context == null) {
			try {
				context = computeParameters();
				context.addObserver(this);
			} catch (ColoaneException e) {
				final Logger logger = Logger.getLogger("fr.lip6.move.coloane.its"); //$NON-NLS-1$
				logger.warning("Model contains syntax errors. Please validate it through syntax check before import. Some model elements were not fully parsed." + e);
				
			}
		}
		return context;
	}

	/**
	 * load the attributes that use  int expressions.
	 * @return an evaluation context
	 * @throws ColoaneException in case of parse errors.
	 */
	protected EvaluationContext computeParameters() throws ColoaneException {
		EvaluationContext context = new EvaluationContext();
		for (INode node : graph.getNodes()) {
			if ("place".equals(node.getNodeFormalism().getName())) {
				IAttribute attrib = node.getAttribute("marking");
				parseIntExpression(attrib, context);
			} else if ("transition".equals(node.getNodeFormalism().getName())) {
				IAttribute eft = node.getAttribute("earliestFiringTime");
				parseIntExpression(eft, context);
				IAttribute lft = node.getAttribute("latestFiringTime");
				parseIntExpression(lft, context);
			}
		}
		for (IArc arc : graph.getArcs()) {
			// supports null attribute passing: some arcs have no valuation
			parseIntExpression(arc.getAttribute("valuation"), context);
		}
		for (IAttribute att : graph.getAttributes()) {
			if (att.getName().equals("size")) {
				parseIntExpression(att, context);
			}
		}
		return context;
	}

	/**
	 * Do the actual loading of a given attribute value = parse an int expression
	 * @param attrib attrib to load or null
	 * @param context the current context (can be updated)
	 * @throws ColoaneException if syntax errors occur
	 */
	private void parseIntExpression(IAttribute attrib, IEvaluationContext context) throws ColoaneException {
		if (attrib == null) {
			return;
		}
		String mark = attrib.getValue();
		if (mark != null && !"".equals(mark)) {

			IntegerExpressionParserLexer lexer;
			lexer = new IntegerExpressionParserLexer(new ANTLRStringStream(mark));

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			IntegerExpressionParserParser parser = new IntegerExpressionParserParser(tokens);
			IntegerExpression expr;
			ErrorReporter report = new ErrorReporter();
			parser.setErrorReporter(report);
			try {
				expr = parser.prog();
				if (report.iterator().hasNext())
					throw new RecognitionException();
			} catch (RecognitionException e) {
				context.declareVariable(new Variable("SYNTAX ERRORS IN MODEL, PLEASE RUN SYNTAX CHECK"));
				return;
			}
			if (!(expr instanceof Constant) && expr != null && !(expr instanceof Infinity)) {
				// dont store the mapping for trivial integers
				attribs.put(attrib, expr);
				// could be empty for simple expressions, eg 3+ 2
				for (IVariable var : expr.supportingVariables()) {
					context.declareVariable(var);
				}
			}
		}
	}

	/**
	 * Notify a model change has occurred.
	 * {@inheritDoc}
	 */
	public final void update() {
		notifyObservers();
	}

	/**
	 * Build a new graph by replacing attribute values with int expressions by their concrete values.
	 * @return the new graph
	 */
	public final IGraph getInstantiatedGraph() {
		// first build a copy of the graph in fr.lip6.move.coloane.its original state
		IGraph copy = new GraphModelFactory().copyGraph(graph);
		// ensure attribs and context is up to date
		getParameters();
		// now edit the graph = update all attributes hit by int expressions
		for (Entry<IAttribute, IntegerExpression> it : attribs.entrySet()) {
			IAttribute att = it.getKey();
			IElement parent = att.getReference();
			IAttribute toupd;
			if (graph.getId() == parent.getId()) {
				toupd = copy.getAttribute(att.getName());
			} else {
				toupd = copy.getObject(parent.getId()).getAttribute(att.getName());
			}
			String newval = Integer.toString(it.getValue().evaluate(context));
			toupd.setValue(newval);
		}
		return copy;
	}

	
	public void reload() throws IOException {
		// free the current data
		attribs.clear();
		// force cache clear
		EvaluationContext oldcontext = context;
		context = null;		
		labels = null;
		graph = loadGraph(typeFile);
		// refresh the caches
		getLabels();
		getParameters();
		// copy old valuations back in
		for (IVariableBinding vb : oldcontext.getBindings()) {
			if (context.containsVariable(vb.getVariable())) {
				context.setVariableValue(vb.getVariable(), vb.getVariableValue());
			}
		}
	}
	
//	protected List<INode> computeStateVariables () {
//		List<INode> vars;
//		EvaluationContext context = new EvaluationContext();
//		for (INode node : graph.getNodes()) {
//			if ("place".equals(node.getNodeFormalism().getName())) {
//				IAttribute attrib = node.getAttribute("marking");
//				parseIntExpression(attrib, context);
//			} else if ("transition".equals(node.getNodeFormalism().getName())) {
//				IAttribute eft = node.getAttribute("earliestFiringTime");
//				parseIntExpression(eft, context);
//				IAttribute lft = node.getAttribute("latestFiringTime");
//				parseIntExpression(lft, context);
//			}
//		}
//		for (IArc arc : graph.getArcs()) {
//			// supports null attribute passing: some arcs have no valuation
//			parseIntExpression(arc.getAttribute("valuation"), context);
//		}
//		for (IAttribute att : graph.getAttributes()) {
//			if (att.getName().equals("size")) {
//				parseIntExpression(att, context);
//			}
//		}
//		return context;		
//	}
//	
//	public List<INode> getStateVariables () {
//		if (stateVariables == null) {
//			
//		}
//		return stateVariables;
//	}
}


