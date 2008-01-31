package fr.lip6.move.coloane.api.session.controller;

/**
                              .%20DDDD02+                                  
                           .%ANMMMMNNMMMMNQ%.                              
                          .QNMMMMMMNNMMMMMMMQ%                             
                         %UMMMMMMMMNNMMMMMMMMN$                            
                         QMMMMMMMMMNNMMMMMMMMMH.                           
                        AMMMMMMMMMMNNMMMMMMMMMMQ                           
                       %MMMMMMMMMMMNNMMMMMMMMMMM$                          
                       YMMMMMMMMMMMNNMMMMMMMMMMM0.                         
                       AMMMMMMMMMMMNNMMMMMMMMMMMU+                         
                       UMMMMMMMMMMMNNMMMMMMMMMMMQ%                         
                       UMMHA00A#MMMNNMMMHUDYDQMMQ%                         
                       UM#0Y2$++YUMHHNH0++%2Y0QMQ%                         
                       AMQY%+%2Y2$#NH#2%YY$++YAMQ%                         
                       DMQY0UUUD%%UHHQ2%0AUQD2AMA+                         
                       2MHD2A2$20A#NHHA0Y22UYYQMY                          
                       +NMU%+$2YYQNNNNND222++2HM+                          
                        2MN2$DNHHMHNNHMNHNH$$UM0                           
                         UMQ0DQNMNMMMMHMM#U0UMQ                            
                         +AMMMMMHHMMMMMHMMMMMH%                            
    .$AUQ#UNNNNNN0 ...+  +.           +HMUY           .....%%   0+   Y.    
    .YAAUQQNNMMNN. ..%D. ++           %HMUY%             .++   +Y.   0     
    .%DUQ#UNNNNNH ...%$  %.           0UM#20$                  A%    D     
       $QUQNMMMMH  +.  .++           +ADMHY%02.               YA    .0     
       2Y%2Y0DD00+  ...+.           +0$YMND .00+            .YD.    Y2     
       2$       .$                  DD 2MMA   2D02.      .%YU#.     A$     
       2$        Y+               %0Y. $HQU    +YDDDDDDAUQ#QDU      Y$     
       2%        D2+            +2A0   $#H#.      +$2Y0002$.+U      .2     
       2%       .A++%%.      .%YAA%.  .2#UU$+               %U       Y     
       2%       $A  $D%$2YYYY00Y%.   .$0$..DY.              20       Y     
       Y+       Y2  %D .+%%%%.    +%0DUH$.$N#D$.            0$      .0     
       0.       0.  .0.       .+$YQHMHUQ0$0#QQNUY%          A.  .+$2A#+    
       D        0   .Y%     .%0QNMMMNQY+++++2#NMN#2%       .A%2DUU#HMN%    
       D        0    %Y+$0A#HMMMMMU0+   ..    $QNMNHQ2.    0NNNNMMMMMM$    
      Y2        Y    %HNMMMMMNQD$+      ..      %YHNMMHU0..QQMMMMMMMMM$    
      #+        Y    2MMNHQY$.          ..        .%ANMMMH#0DMMMMMMMMM%    
     $U         Y   .0AY+.              +            +0#NMH 0MMMMMMMMM+    
    +D$         Y   2Y                  .              .%QU YMMMMMMMMN.    
 #MMMMMMA                    YMMMMMMMMND          %UMM#QNMMMMMMM$          
+MMMMMMM%                  + +HMMMMMMMQ.          UMM#+DMMMMMMM#.          
$MMMMMMN.                  +% QMMMMMM#2 %       .YMM#+ DMMMMMMN2           
0MMMMMM#                   .%.DMMMMMNU +.       2HNQ.  0NMMMMMH%           
AMMMMMMQ                    .00MMMMMH0++        .%.    YNMMMMMQ            
AMMMMMNU                     %AMMMMMQ0%                0NMMMMNU            
0MMMMMHD                      0NMMMMQ2                 DNMNMMHD            
%MMMMMMA       .+.            +#MMMM2                  UMMNMNNY            
 #MMMMMN$     %##%..  ...      2NMM#                   QMNNMNM$            
 0MMMMMMA     .DU+ ..  .%%+     YH#$                   #MNNMNM%            


public interface IState {

	public class RewindException extends Exception {
		private static final long serialVersionUID = 7813190613177365773L;		
	}
	
	public class ErrorException extends Exception {
		private static final long serialVersionUID = 1882292943740356666L;
	}
	
	public IState apply(IMessage m) throws RewindException, ErrorException;
		
}
