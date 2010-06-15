package fr.lip6.move.coloane.projects.its.checks.ui.controls;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

import fr.lip6.move.coloane.projects.its.antlrutil.ErrorReporter;
import fr.lip6.move.coloane.projects.its.checks.CheckList;
import fr.lip6.move.coloane.projects.its.ctl.CTLFormula;
import fr.lip6.move.coloane.projects.its.ctl.parser.CTLParserLexer;
import fr.lip6.move.coloane.projects.its.ctl.parser.CTLParserParser;

public class CTLText extends StyledText {

	private CheckList cl;


	public CTLText(Composite parent, int style) {
		super(parent, style | SWT.BORDER );
		addModifyListener(new GrammarListener());
	}
	
	private CTLText getSubject() {
		return this;
	}
	
	public void setCheckList (CheckList cl) {
		this.cl = cl;
	}

	private CTLFormula expr;

	private void setExpr(CTLFormula expr) {
		this.expr = expr;
	}
	
	public CTLFormula getFormula() {
		return expr;
	}
	
	class GrammarListener implements ModifyListener {
		String previousCTL = "";
		public void modifyText(ModifyEvent e) {
			String ctl = getText();
			if (ctl != null && !ctl.equals(previousCTL)) {
				
				clearErrors();
				
				
				previousCTL = ctl;
				CTLParserLexer lexer;
				lexer = new CTLParserLexer(new ANTLRStringStream(ctl));

				CommonTokenStream tokens = new CommonTokenStream(lexer);

				CTLParserParser parser = new CTLParserParser(tokens);
				setExpr(null);
				ErrorReporter report = new ErrorReporter();
				parser.setErrorReporter(getSubject());
				parser.setCheckList(cl);
				try {
					setExpr(parser.ctlformula());
					
					for (String error: report) {
						System.err.println(error);
//						addCheckFail(elt, att, error, result);
//						testok = false;							
					}
				} catch (RecognitionException ee) {
					System.err.println(ee+ee.getMessage());
					ee.printStackTrace();
//					addCheckFail(elt, att, e.getLocalizedMessage(), result);
//					testok = false;
				}

			}			
		}
		
	}

	private void clearErrors() {
		setToolTipText("");
//		setStyleRange(null);
		StyleRange sr = new StyleRange();
		sr.start = 0;
		sr.length = getText().length();
		sr.underline = false;
		setStyleRange(sr);
	}

	
	public void reportError(String msg, int charAt) {
		setToolTipText(getToolTipText()+msg+"\n");
		StyleRange sr = new StyleRange();
		sr.start = charAt == -1 ? getText().length()-1 : charAt;
		sr.length = Math.min(2,getText().length()-sr.start);
		sr.underline = true;
		sr.underlineStyle = SWT.UNDERLINE_SQUIGGLE;
		sr.underlineColor = getDisplay().getSystemColor(SWT.COLOR_RED);
		setStyleRange(sr);
	}

	
	
}
