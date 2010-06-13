grammar CTLParser;

options {
  language = Java;
}



@lexer::header {

package fr.lip6.move.coloane.projects.its.ctl.parser;

}

@parser::header {

package fr.lip6.move.coloane.projects.its.ctl.parser;

import fr.lip6.move.coloane.projects.its.ctl.*;
import fr.lip6.move.coloane.projects.its.antlrutil.*;
import fr.lip6.move.coloane.projects.its.checks.ui.controls.CTLText;

}

@members {
    private CTLText errorReporter = null;
    public void setErrorReporter(CTLText errorReporter) {
        this.errorReporter = errorReporter;
    }
    public void emitErrorMessage(String msg,int charAt) {
        errorReporter.reportError(msg,charAt);
    }
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
      super.displayRecognitionError(tokenNames, e);
      errorReporter.reportError(getErrorMessage(e, tokenNames),e.charPositionInLine);
      
    };
    
}

ctlformula returns [CTLFormula form] : (f=formula {form = f;} ';')* EOF ;

formula returns [CTLFormula form] : 
  f=uformula
;

uformula returns [CTLFormula form] :
  ( 'A' '(' f=formula 'U' g=formula  ')' )
  |
  ( 'E' '(' f=formula 'U' g=formula  ')' ) 
  |
  implyformula   
; 

implyformula returns [CTLFormula form] : 
  f=equivformula
  ('->' g=equivformula)? 
;

equivformula returns [CTLFormula form] :
  f=xorformula 
  ('<->' g=xorformula )?
;

xorformula returns [CTLFormula form] :
  f=orformula ('^' g=orformula )?
;

orformula returns [CTLFormula form] :
 f=andformula 
 ('+' g=andformula )?
;

andformula returns [CTLFormula form] :
 f=timeformula 
 ('*' g=timeformula )?
;

timeformula returns [CTLFormula form] :
  negformula
  |
  ( 'AG' f=negformula  )
  |
  ( 'AF' f=negformula  )
  |
  ( 'AX' f=negformula  )
  |
  ( 'EG' f=negformula  )
  |
  ( 'EF' f=negformula  )
  |
  ( 'EX' f=negformula  )
;

negformula returns [CTLFormula form] :
  atomicformula
  |
  ( '!' f=atomicformula  )
;

atomicformula returns [CTLFormula form] :
  predicate
  |
  ( '(' f=formula  {form=f;} ')' )
  ;
  
predicate returns [CTLFormula form] :
  'TRUE'
  |
  'FALSE'
  |
  VARIABLE '=' NUMBER   
  ;




fragment LETTER : 'a'..'z' | 'A'..'Z' | '_'
  ;
fragment DIGIT  : '0'..'9'
  ;
fragment STRING : '"'.*'"'
  ;
fragment DOLLAR : '$';
// ignore whitespace
WS  : ( ' ' | '\t' | '\n' | '\r') { $channel = HIDDEN; }
  ;
NUMBER : DIGIT (DIGIT)*
  ;
  
INFINITY : 'inf'
  ;

VARIABLE  : ( STRING | (LETTER (LETTER | DIGIT)*) )
  ;
  
// LABEL : ' ' ( options{greedy=false;}: .* ) ' ';

// LETTER (LETTER | DIGIT | ' ' | '-')* );
  
