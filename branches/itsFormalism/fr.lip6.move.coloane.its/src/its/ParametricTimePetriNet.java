package its;

import its.expression.EvaluationContext;
import its.expression.IVariable;
import its.expression.IntegerExpression;
import its.expression.parser.IntegerExpressionParserLexer;
import its.expression.parser.IntegerExpressionParserParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

public class ParametricTimePetriNet extends SimpleObservable implements EvaluationContext {

	private IGraph  graph;
	private Set<IVariable> variables=null;
	private Map<IAttribute,IntegerExpression> attribs;
	private Map<IVariable,Integer> values;

	public ParametricTimePetriNet(IGraph graph) {
		this.graph = graph;
	}
	
	public Set<IVariable> getVariables () throws ColoaneException {
		if (variables == null) {
			variables = new HashSet<IVariable>();
			values = new HashMap<IVariable, Integer>();
			for (INode node : graph.getNodes()) {
				if ("place".equals(node.getNodeFormalism().getName())) {
					IAttribute attrib = node.getAttribute("marking");
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
						attribs.put(attrib,expr);
						variables.addAll(expr.supportingVariables());
					}
				}
			}
		}		
		return variables;
	}

	@Override
	public Integer getVariableValue(IVariable var) {
		return values.get(var);
	}

	@Override
	public void setVariableValue(IVariable var, int value) {
		values.put(var,value);
		notifyObservers();
	}
	
}
