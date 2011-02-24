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
package fr.lip6.move.coloane.projects.its.syntax.tpn;

import fr.lip6.move.coloane.core.formalisms.checkers.CheckerResult;
import fr.lip6.move.coloane.interfaces.formalism.IAttributeChecker;
import fr.lip6.move.coloane.interfaces.formalism.ICheckerResult;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.projects.its.antlrutil.ErrorReporter;
import main.antlr3.fr.lip6.move.coloane.projects.its.expression.parser.IntegerExpressionParserLexer;
import main.antlr3.fr.lip6.move.coloane.projects.its.expression.parser.IntegerExpressionParserParser;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;


/**
 * A syntax rule to check the EFT is before LFT.
 * @author Yann
 *
 */
public final class IntegerExpressionCheck implements IAttributeChecker {

	/**
	 * {@inheritDoc}
	 */
	public ICheckerResult check(IAttribute att) {

		if (att.getValue() != null && ! att.getValue().equals("")) {
			//				if (name.equals("marking")
			//						|| name.equals("earliestFiringTime")
			//						|| name.equals("latestFiringTime")
			//						|| name.equals("valuation")
			//						|| name.equals("size")) {
			IntegerExpressionParserLexer lexer;
			lexer = new IntegerExpressionParserLexer(new ANTLRStringStream(att.getValue()));

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			IntegerExpressionParserParser parser = new IntegerExpressionParserParser(tokens);
			ErrorReporter report = new ErrorReporter();
			parser.setErrorReporter(report);
			try {
				// IntegerExpression expr = 
					parser.prog();
				int nberr = 0;
				for (String err: report) {
					nberr++;					
				}
				String msg="";
				if (nberr == 0) {
					return new CheckerResult(false, "");
				} else if (nberr > 1) {
					msg = "Multiple syntax errors ";
				} else {
					msg = "Syntax error ";
				}
				msg += "parsing integer expression for \""+att.getName()+"\". Use $ to prefix variables, and arithmetic operations only.\n";
				for (String error: report) {
					msg += error + "\n";
				}
				return new CheckerResult(true, msg);
			} catch (RecognitionException e) {
				String msg = "Syntax error parsing integer expression for \""+att.getName()+"\". Use $ to prefix variables, and arithmetic operations only.\n";
				msg += e.getLocalizedMessage();
				return new CheckerResult(true, msg);
			}
		}


		return new CheckerResult(false, "");
	}

//	void addCheckFail (IElement elt, IAttribute att, String msg, Result result) {
//		SubResult sr = new SubResult();
//		sr.setName(getName());
//		sr.addAttributeOutline(elt.getId(), att.getName());
//		sr.addObjectOutline(elt.getId());
//		sr.addTextualResults("Syntax error parsing integer expression for \""+att.getName()+"\". Use $ to prefix variables, and arithmetic operations only.\n" + msg);
//		result.addChild(sr);
//	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Integer expressions syntax check.";
	}



	public ICheckerResult performCheck(IAttribute attribute) {
		return check(attribute);
	}

}
