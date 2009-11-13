package its;

import its.expression.Constant;
import its.expression.EvaluationContext;
import its.expression.IEvaluationContext;
import its.expression.IVariable;
import its.expression.IntegerExpression;
import its.expression.parser.IntegerExpressionParserLexer;
import its.expression.parser.IntegerExpressionParserParser;
import its.obs.SimpleObservable;

import java.util.Map;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class ParametricTimePetriNet extends SimpleObservable {

	private IGraph  graph;
	private Map<IAttribute,IntegerExpression> attribs;
	private IEvaluationContext context;
	
	public ParametricTimePetriNet(IGraph graph) {
		this.graph = graph;
	}
	
	public IEvaluationContext computeParameters () throws ColoaneException {
		if (context == null) {
			context = new EvaluationContext();
			for (INode node : graph.getNodes()) {
				if ("place".equals(node.getNodeFormalism().getName())) {
					IAttribute attrib = node.getAttribute("marking");
					parseIntExpression(attrib);
				}
			}
			for (IArc arc : graph.getArcs()) {
				// supports null attribute passing: some arcs have no valuation
				parseIntExpression(arc.getAttribute("valuation"));
			}
		}		
		return context;
	}

	private void parseIntExpression(IAttribute attrib) throws ColoaneException {
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
			if (! (expr instanceof Constant)) {
				// dont store the mapping for trivial integers
				attribs.put(attrib,expr);
				// could be empty for simple expressions, eg 3+2
				for (IVariable var : expr.supportingVariables())
					context.declareVariable(var);
			}
		}
	}

	
}
