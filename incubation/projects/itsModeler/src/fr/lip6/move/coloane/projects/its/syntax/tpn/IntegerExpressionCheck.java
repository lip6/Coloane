package fr.lip6.move.coloane.projects.its.syntax.tpn;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.projects.its.expression.IntegerExpression;
import fr.lip6.move.coloane.projects.its.expression.parser.ErrorReporter;
import fr.lip6.move.coloane.projects.its.expression.parser.IErrorReporter;
import fr.lip6.move.coloane.projects.its.expression.parser.IntegerExpressionParserLexer;
import fr.lip6.move.coloane.projects.its.expression.parser.IntegerExpressionParserParser;
import fr.lip6.move.coloane.projects.its.syntax.ISyntaxRule;
import fr.lip6.move.coloane.projects.its.syntax.Result;
import fr.lip6.move.coloane.projects.its.syntax.SubResult;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;


/**
 * A syntax rule to check the EFT is before LFT.
 * @author Yann
 *
 */
public final class IntegerExpressionCheck implements ISyntaxRule {

	private List<IElementFormalism> ruleTypes;

	/**
	 * Ctor.
	 */
	public IntegerExpressionCheck() {
		ruleTypes = new ArrayList<IElementFormalism>();
		IGraphFormalism tpn = FormalismManager.getInstance().getFormalismByName("Time Petri Net").getMasterGraph();
		ruleTypes.add(tpn.getElementFormalism("transition"));
		ruleTypes.add(tpn.getElementFormalism("place"));
		for ( IElementFormalism ief : tpn.getAllElementFormalism()) {
			if (ief instanceof IArcFormalism) {
				IArcFormalism af = (IArcFormalism) ief;
				ruleTypes.add(af);
			}
		}
		IGraphFormalism  ss = FormalismManager.getInstance().getFormalismByName("Scalar Set Composite").getMasterGraph();
		ruleTypes.add(ss);		
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean check(IElement elt, Result result) {
		boolean testok = true;
		for (IAttribute att : elt.getAttributes()) {
			if (att.getValue() != null && att.getValue() != "") {
				String name = att.getName();
				if (name.equals("marking")
						|| name.equals("earliestFiringTime")
						|| name.equals("latestFiringTime")
						|| name.equals("valuation")
						|| name.equals("size")) {
					IntegerExpressionParserLexer lexer;
					lexer = new IntegerExpressionParserLexer(new ANTLRStringStream(att.getValue()));

					CommonTokenStream tokens = new CommonTokenStream(lexer);

					IntegerExpressionParserParser parser = new IntegerExpressionParserParser(tokens);
					IntegerExpression expr;
					ErrorReporter report = new ErrorReporter();
					parser.setErrorReporter(report);
					try {
						expr = parser.prog();
						for (String error: report) {
							addCheckFail(elt, att, error, result);
							testok = false;							
						}
					} catch (RecognitionException e) {
						addCheckFail(elt, att, e.getLocalizedMessage(), result);
						testok = false;
					}
				}
			}
		}
		return testok;
	}

	void addCheckFail (IElement elt, IAttribute att, String msg, Result result) {
		SubResult sr = new SubResult();
		sr.setName(getName());
		sr.addAttributeOutline(elt.getId(), att.getName());
		sr.addObjectOutline(elt.getId());
		sr.addTextualResults("Syntax error parsing integer expression for \""+att.getName()+"\". Use $ to prefix variables, and arithmetic operations only.\n" + msg);
		result.addChild(sr);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Integer expressions syntax check.";
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterable<IElementFormalism> getRuleTypes() {
		return ruleTypes;
	}

}
