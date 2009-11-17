package its;

import its.expression.Constant;
import its.expression.EvaluationContext;
import its.expression.IEvaluationContext;
import its.expression.IVariable;
import its.expression.IntegerExpression;
import its.expression.parser.IntegerExpressionParserLexer;
import its.expression.parser.IntegerExpressionParserParser;
import its.obs.ISimpleObserver;
import its.obs.SimpleObservable;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.resources.IFile;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.model.GraphModelFactory;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class TypeDeclaration extends SimpleObservable implements ISimpleObserver{
	private String typeName;
	private IFile typeFile;
	/** The underlying coloane Graph */
	private IGraph graph;
	private Set<String> labels=null;
	private TypeList typeList;
	private EvaluationContext context;

	protected TypeDeclaration (String typeName, IFile modelFile, IGraph graph, TypeList types) {
		this.typeName = typeName;
		typeFile = modelFile ;
		this.graph = graph;
		typeList = types;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		if (! this.typeName.equals(typeName)) {
			this.typeName = typeName;
			notifyObservers();
		}
	}

	public String getTypePath() {
		return typeFile.getFullPath().toString();
	}
	public IFile getTypeFile() {
		return typeFile;
	}

	public String getTypeType() {
		return graph.getFormalism().getName();
	}

	public IGraph getGraph() {		
		return graph;
	}

	/**  Load a IGraph from a file 
	 * @throws IOException */
	private static IGraph loadGraph(IFile typePath) throws IOException {
		// Construction d'un modele en memoire a partir de se representation en XML
		IGraph graph = ModelLoader.loadGraphFromXML(typePath);

		// Si le chargement a échoué, on annule l'ouverture de l'éditeur
		if (graph == null) {
			throw new IOException("Load from XML file failed !");
		}
		return graph;
	}

	/** Factory operation to build concrete TypeDescriptions */
	public static TypeDeclaration create(String name, IFile file, TypeList types) throws IOException {
		IGraph graph = loadGraph(file);
		String form = graph.getFormalism().getName(); 
		if ( form.equals("ITSComposite") || form.equals("Scalar Set Composite")) {
			return new CompositeTypeDeclaration(name,file,graph,types);
		} else {
			return new TypeDeclaration(name, file, graph, types);
		}
	}

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

	public Collection<String> getLabels() {
		if (labels == null) {
			labels = computeLabels();
		}
		return labels;
	}

	/** Specifies if all the concepts of this type have an effective realization. 
	 * @return true for a basic type declaration
	 */
	public boolean isSatisfied () {
		return true;
	}

	public TypeList getTypeList() {
		return typeList;
	}

	public void unsetTypeDeclaration(TypeDeclaration t) {
		// NOP
	}


	public IEvaluationContext getParameters() {
		if (context == null)
			try {
				context = computeParameters();
				context.addObserver(this);
			} catch (ColoaneException e) {
				final Logger logger = Logger.getLogger("fr.lip6.move.coloane.its"); //$NON-NLS-1$
				logger.warning("Model contains syntax errors. Please validate it through syntax check before import. Some model elements were not fully parsed."+e);
			}
		return context;
	}


	protected EvaluationContext computeParameters () throws ColoaneException {
		EvaluationContext context = new EvaluationContext();
		for (INode node : graph.getNodes()) {
			if ("place".equals(node.getNodeFormalism().getName())) {
				IAttribute attrib = node.getAttribute("marking");
				parseIntExpression(attrib,context);
			} else if ("transition".equals(node.getNodeFormalism().getName())) {
				IAttribute eft = node.getAttribute("earliestFiringTime");
				parseIntExpression(eft, context);
				IAttribute lft = node.getAttribute("latestFiringTime");
				parseIntExpression(lft, context);				
			}
		}
		for (IArc arc : graph.getArcs()) {
			// supports null attribute passing: some arcs have no valuation
			parseIntExpression(arc.getAttribute("valuation"),context);
		}
		for (IAttribute att : graph.getAttributes()) {
			if (att.getName().equals("size")) {
				parseIntExpression(att, context);
			}
		}
		return context;
	}

	private Map<IAttribute,IntegerExpression> attribs = new HashMap<IAttribute, IntegerExpression>();

	private void parseIntExpression(IAttribute attrib, IEvaluationContext context) throws ColoaneException {
		if (attrib == null)
			return;
		String mark = attrib.getValue();
		if (mark != null && ! "".equals(mark)) {

			IntegerExpressionParserLexer lexer;
			lexer = new IntegerExpressionParserLexer (new ANTLRStringStream(mark));

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			IntegerExpressionParserParser parser = new IntegerExpressionParserParser(tokens);
			IntegerExpression expr;
			try {
				expr = parser.prog();
			} catch (RecognitionException e) {
				throw new ColoaneException("Error parsing Marking "+e.getMessage());
			}
			if (! (expr instanceof Constant) && expr != null) {
				// dont store the mapping for trivial integers
				attribs.put(attrib,expr);
				// could be empty for simple expressions, eg 3+2
				for (IVariable var : expr.supportingVariables())
					context.declareVariable(var);
			}
		}
	}

	@Override
	public void update() {
		notifyObservers();
	}

	public IGraph getInstantiatedGraph() {
		// first build a copy of the graph in its original state
		IGraph copy = new GraphModelFactory().copyGraph(graph);
		// ensure attribs and context is up to date
		getParameters();
		// now edit the graph = update all attributes hit by int expressions
		for (Entry<IAttribute, IntegerExpression> it: attribs.entrySet()) {
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

}


