grammar HASLformulaParser;

options {
  language = Java;
  output = template;
}

@lexer::header {
  package fr.lip6.move.coloane.extensions.exporttogrml.antlr;
}

@header {
  package fr.lip6.move.coloane.extensions.exporttogrml.antlr;
  import org.eclipse.jface.dialogs.MessageDialog;
  import org.eclipse.swt.widgets.Display;
  import org.eclipse.swt.widgets.Shell;
}

@members {
  //@Override
 // public void emitErrorMessage(String error){
      //final Display display = new Display();
      //final Shell shell = new Shell(display);
      //MessageDialog.openError(super.shell, "Error", "Parsing error"+ error);
  //    throw RecognitionException(error);
  //}
}

haslFormW
  : -> { %{""} }
  | (a+=haslForm)* EOF
  {
    List<StringTemplate> tmp = new ArrayList();
    for (Object x : $a) {
      StringTemplate tmp0 = templateLib.getInstanceOf("balise");
      tmp0.setAttribute("name", "HASL Formula");
      tmp0.setAttribute("content", (StringTemplate)x);
      tmp.add(tmp0);
    }
  } -> delist(arg={tmp});
   
haslForm: AVG '(' e=algExpr ')' (';')? -> balise(name={"AVG"}, content={ $e.st })
  | PROB (';')? -> balise(name={"PROB"}, content={ $e.st })
  | CDF  '(' e=algExpr ',' d=FLOAT ',' min=FLOAT ',' max=FLOAT ')' (';')? {
    StringTemplate tmp = templateLib.getInstanceOf("balise");
    tmp.setAttribute("name", "delta");
    tmp.setAttribute("content", $d.getText() );
    StringTemplate tmp2 = templateLib.getInstanceOf("balise");
    tmp2.setAttribute("name", "min");
    tmp2.setAttribute("content", $min.getText());
    StringTemplate tmp3 = templateLib.getInstanceOf("balise");
    tmp3.setAttribute("name", "max");
    tmp3.setAttribute("content", $max.getText());
    List<StringTemplate> listtmp = new ArrayList<StringTemplate>();
    listtmp.add($e.st);
    listtmp.add(tmp);
    listtmp.add(tmp2);
    listtmp.add(tmp3);
  } -> balise(name={"CDF"}, content={ listtmp })
  | PDF  '(' e=algExpr ',' d=FLOAT ',' min=FLOAT ',' max=FLOAT ')' (';')? {
  StringTemplate tmp = templateLib.getInstanceOf("balise");
    tmp.setAttribute("name", "delta");
    tmp.setAttribute("content", $d.getText());
    StringTemplate tmp2 = templateLib.getInstanceOf("balise");
    tmp2.setAttribute("name", "min");
    tmp2.setAttribute("content", $min.getText());
    StringTemplate tmp3 = templateLib.getInstanceOf("balise");
    tmp3.setAttribute("name", "max");
    tmp3.setAttribute("content", $max.getText());
    List<StringTemplate> listtmp = new ArrayList<StringTemplate>();
    listtmp.add($e.st);
    listtmp.add(tmp);
    listtmp.add(tmp2);
    listtmp.add(tmp3);
  }-> balise(name={"PDF"}, content={ listtmp });


algExpr: a=lhaFunc -> balise(name={"YHF"}, content = {$a.st});
  
lhaFunc:  
  LAST '(' a=linForm ')' -> balise(name={"last"}, content={ $a.st })
  | MEAN '(' a=linForm ')' -> balise(name={"mean"}, content={ $a.st })
  | MIN '(' a=linForm ')' -> balise(name={"min"}, content={ $a.st })
  | MAX '(' a=linForm ')' -> balise(name={"last"}, content={ $a.st });

linForm:
  a=IDENTIFIER -> exprbalise(name={"name"}, content={ $a.getText() });


AVG: 'AVG';
PROB: 'PROB';
CDF: 'CDF';
PDF: 'PDF';

LAST: 'last' | 'Last' | 'LAST';
MEAN: 'mean' | 'Mean' | 'MEAN';
MIN: 'min' | 'Min' | 'MIN';
MAX: 'max' | 'Max' | 'MAX';

  
fragment LETTER : 'a'..'z' | 'A'..'Z' ;
fragment DIGIT : '0'..'9' ;
INTEGER : ('-')? DIGIT+ ;
FLOAT: INTEGER ('.' DIGIT+)?  ;

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

WS : (' ' | '\n' | '\r' | '\t')+ { $channel = HIDDEN; } ;
