package fr.lip6.move.coloane.projects.its.checks.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import fr.lip6.move.coloane.projects.its.checks.CTLFormulaDescription;
import fr.lip6.move.coloane.projects.its.checks.ui.controls.CTLText;

public class CTLSection {


	private FormToolkit toolkit;
	private Composite parent;
	private CTLFormulaDescription input;
	private Section section;


	private Text nameField;
	private Text commentsField;
	private CTLText ctlField;
	private Section helpSection;


	public CTLSection(final FormToolkit formToolkit, Composite parent) {
		toolkit = formToolkit;
		this.parent = parent;
	}

	public CTLFormulaDescription getInput() {
		return input;
	}

	public void setInput(CTLFormulaDescription input) {
		if (input != this.input)
		{
			this.input = input;
			createDetails(parent);
		}
		update();
	}



	private void createDetails(Composite parent2) {

		if (section != null) {
			section.dispose();
			section = null;
			helpSection.dispose();
			helpSection = null;
		}
		{
			section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
			section.marginWidth = 4;
			section.marginHeight = 4;
			section.setText("CTL Formula description"); //$NON-NLS-1$
			TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
			td.grabHorizontal = true;
			section.setLayoutData(td);
			Composite client = toolkit.createComposite(section);
			GridLayout glayout = new GridLayout();
			glayout.marginWidth = 10;
			glayout.marginHeight = 5;
			glayout.numColumns = 2;
			client.setLayout(glayout);

			// settings for text fields
			GridData gd;
			gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
			gd.widthHint = 10;

			toolkit.createLabel(client, "Formula Title");
			nameField = toolkit.createText(client, "");
			nameField.setLayoutData(gd);
			nameField.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent e) {
					if (getInput() != null) {
						getInput().setName(nameField.getText());
					}
				}
			});

			toolkit.createLabel(client, "Formula Description");
			commentsField = toolkit.createText(client, "", SWT.MULTI);
			GridData gdhigh = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
			gdhigh.widthHint = 10;
			gdhigh.heightHint = 50;
			commentsField.setLayoutData(gdhigh);
			commentsField.addModifyListener(new ModifyListener() {
				
				public void modifyText(ModifyEvent e) {
					if (getInput() != null) {
						getInput().setComments(commentsField.getText());
					}
				}
			});

			
			toolkit.createLabel(client, "CTL formula"); //$NON-NLS-1$

			ctlField = new CTLText(client, SWT.SINGLE);
			ctlField.setLayoutData(gd);

			

			toolkit.paintBordersFor(section);
			toolkit.paintBordersFor(client);
			section.setClient(client);
		}
		{
			helpSection = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED );
			helpSection.marginWidth = 4;
			helpSection.marginHeight = 4;
			helpSection.setText("CTL syntax help"); //$NON-NLS-1$
			//		s1.setDescription(Messages.getString("TypeOneDetailsPage.name")); //$NON-NLS-1$
			TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
			td.grabHorizontal = true;
			helpSection.setLayoutData(td);
			Composite client = toolkit.createComposite(helpSection);
			GridLayout glayout = new GridLayout();
			glayout.marginWidth = 10;
			glayout.marginHeight = 5;
			glayout.numColumns = 1;
			client.setLayout(glayout);

//			toolkit.createLabel(client, "CTL syntax"); //$NON-NLS-1$

			Browser helpField = new Browser(client, SWT.NONE);
			helpField.setText(getHelpText());

			GridData gd;
			gd = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING);
			gd.widthHint = 10;
			gd.heightHint = 200;
			helpField.setLayoutData(gd);

			toolkit.paintBordersFor(helpSection);
			toolkit.paintBordersFor(client);
			helpSection.setClient(client);
		}



		parent.pack();
	}




	public void update() {
		if (input != null) {
			nameField.setText(input.getName());
			ctlField.setText(input.getCtlFormula());
			commentsField.setText(input.getComments());
		}
	}

	private String getHelpText() {
		return "<strong>CTL (Computation Tree Logic)</strong> is a language used to describe properties of systems.<br>For the semantics of CTL, the reader should refer to the following paper<br><blockquote>E. M. Clarke, E. A. Emerson and A. P. Sistla,<br><em>Automatic Verification of Finite-State Concurrent Systems Using Temporal Logic Specifications</em>,<br>ACM Transactions on Programming Languages and Systems,<br>vol 8-2, pages 244-263, April, 1986<br></blockquote>"
		+" This syntax should be followed when VIS users create CTL files <br>and  fairness  constraint  files  for  the  commands <tt>model_check</tt>, <tt><br>approximate_model_check</tt>, and <tt>read_fairness</tt>, respectively.<br>"
		+"The syntax for CTL is:"
		+"<blockquote>	TRUE,  FALSE, and <em>var-name=value</em>  are CTL formulas,<br>    where  <em>var-name</em>  is  the  full  hierarchical  name of a<br>    variable ,  and <em>value</em> is a legal  value in  the  domain <br>    of the variable. <em>var-name1 == var-name2</em> is  the  atomic <br>    formula that is true if <em>var-name1</em> has  the  same  value <br>    as<em>  var-name2</em>. Currently  it can be used  only  in  the <br>    Boolean  domain. ( It cannot  be used for variables  of <br>    enumerated  types. )<em>  var-name1</em>[i:j] == <em> var-name2</em>[k:l] <br>    can be used if  the  lengths  of  vectors are the  same. <br>    Vector variables, the syntax of hierarchical names, and<br>    macro definition are described later in this document.<br></blockquote>"
		+"<blockquote>The following character set may be used for variable names and values:<br></blockquote>"
		+"<pre>	A-Z a-z 0-9 ^ ? | / [ ] + * $ &lt; &gt; ~ @ _ # % :  .<br></pre>"
		+"<blockquote>If f and g are CTL formulas, then so are the following:<br></blockquote>"
		+"<pre>	(f), f * g, f + g, f ^ g, !f, f -&gt; g, f &lt;-&gt; g, AG f,<br>	AF f, AX f, EG f, EF f, EX f, A(f U g) and E(f U g).<br></pre>"
		+"<blockquote><br>Binary operators must  be surrounded by  spaces, i.e.  <tt>f + g</tt> is a CTL <br>formula while<tt> f+g</tt> is  not. The  same is true  for <tt>U</tt> in until formulas. Once  parentheses are <br>inserted, the spaces  can be omitted, i.e. (f)+(g) is a valid formula. <br>Unary  temporal  operators  and  their arguments must be separated by <br>spaces unless parentheses are used.<br><br><br>The symbols have the following meanings.<br></blockquote>"
		+"<pre>	* -- AND, + -- OR, ^ -- XOR, ! -- NOT, -&gt; -- IMPLY, &lt;-&gt; -- EQUIV</pre>"
		+"<blockquote><u>Operator Precedence for CTL:</u><br><br>High<br></blockquote><pre>    	!<br><br>    	AG, AF, AX, EG, EF, EX<br><br>    	*<br><br>    	+<br><br>    	^<br><br>    	&lt;-&gt;<br><br>    	-&gt;<br><br>    	U<br><br>      Low<br>&gt;</pre>"
		+"     An entire formula should be followed by a semicolon.  All text from <tt>#</tt> to<br> the end of a line is treated as a comment. The model checker (<tt>mc</tt>) package is<br> used to decide whether or not a given FSM satisfies a given CTL formula. See <br> the help files for the <tt>model_check</tt> and <tt>approximate_model_check</tt> commands  for <br> more details.<br><br>"
		;	
	}

}
