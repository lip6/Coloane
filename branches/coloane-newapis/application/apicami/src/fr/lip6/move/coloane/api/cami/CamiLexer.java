// $ANTLR 3.0.1 Cami.g 2008-07-21 15:48:52

package fr.lip6.move.coloane.api.cami;
            
        

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CamiLexer extends Lexer {
    public static final int T14=14;
    public static final int T29=29;
    public static final int T9=9;
    public static final int NUMBER=5;
    public static final int T36=36;
    public static final int T58=58;
    public static final int T35=35;
    public static final int T61=61;
    public static final int T45=45;
    public static final int T20=20;
    public static final int T34=34;
    public static final int NEWLINE=6;
    public static final int T64=64;
    public static final int CAMI_STRING=4;
    public static final int T25=25;
    public static final int T18=18;
    public static final int T37=37;
    public static final int T26=26;
    public static final int T32=32;
    public static final int T17=17;
    public static final int T51=51;
    public static final int T46=46;
    public static final int T16=16;
    public static final int T38=38;
    public static final int T41=41;
    public static final int T24=24;
    public static final int T19=19;
    public static final int T39=39;
    public static final int T21=21;
    public static final int T62=62;
    public static final int T44=44;
    public static final int T55=55;
    public static final int T68=68;
    public static final int T33=33;
    public static final int T11=11;
    public static final int T22=22;
    public static final int T50=50;
    public static final int FIXED_LENGTH_STRING=7;
    public static final int T43=43;
    public static final int T12=12;
    public static final int T23=23;
    public static final int T28=28;
    public static final int T42=42;
    public static final int T66=66;
    public static final int T40=40;
    public static final int T63=63;
    public static final int T57=57;
    public static final int T13=13;
    public static final int T65=65;
    public static final int T56=56;
    public static final int T10=10;
    public static final int T59=59;
    public static final int T48=48;
    public static final int T15=15;
    public static final int T54=54;
    public static final int EOF=-1;
    public static final int T67=67;
    public static final int T47=47;
    public static final int Tokens=69;
    public static final int T53=53;
    public static final int T60=60;
    public static final int T31=31;
    public static final int T49=49;
    public static final int T8=8;
    public static final int T27=27;
    public static final int T52=52;
    public static final int T30=30;
    public CamiLexer() {;} 
    public CamiLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Cami.g"; }

    // $ANTLR start T8
    public final void mT8() throws RecognitionException {
        try {
            int _type = T8;
            // Cami.g:7:4: ( 'SC(' )
            // Cami.g:7:6: 'SC('
            {
            match("SC("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T8

    // $ANTLR start T9
    public final void mT9() throws RecognitionException {
        try {
            int _type = T9;
            // Cami.g:8:4: ( ')' )
            // Cami.g:8:6: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T9

    // $ANTLR start T10
    public final void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // Cami.g:9:5: ( 'OC(' )
            // Cami.g:9:7: 'OC('
            {
            match("OC("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T10

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // Cami.g:10:5: ( ',' )
            // Cami.g:10:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // Cami.g:11:5: ( 'FC()' )
            // Cami.g:11:7: 'FC()'
            {
            match("FC()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // Cami.g:12:5: ( 'OS(' )
            // Cami.g:12:7: 'OS('
            {
            match("OS("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // Cami.g:13:5: ( 'TD()' )
            // Cami.g:13:7: 'TD()'
            {
            match("TD()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // Cami.g:14:5: ( 'FA()' )
            // Cami.g:14:7: 'FA()'
            {
            match("FA()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // Cami.g:15:5: ( 'SS()' )
            // Cami.g:15:7: 'SS()'
            {
            match("SS()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // Cami.g:16:5: ( 'RS(' )
            // Cami.g:16:7: 'RS('
            {
            match("RS("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // Cami.g:17:5: ( 'FS(' )
            // Cami.g:17:7: 'FS('
            {
            match("FS("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // Cami.g:18:5: ( 'TL()' )
            // Cami.g:18:7: 'TL()'
            {
            match("TL()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // Cami.g:19:5: ( 'VI(' )
            // Cami.g:19:7: 'VI('
            {
            match("VI("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // Cami.g:20:5: ( 'FL()' )
            // Cami.g:20:7: 'FL()'
            {
            match("FL()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // Cami.g:21:5: ( 'DQ()' )
            // Cami.g:21:7: 'DQ()'
            {
            match("DQ()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // Cami.g:22:5: ( 'FQ()' )
            // Cami.g:22:7: 'FQ()'
            {
            match("FQ()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // Cami.g:23:5: ( 'VQ(' )
            // Cami.g:23:7: 'VQ('
            {
            match("VQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // Cami.g:24:5: ( 'CQ(' )
            // Cami.g:24:7: 'CQ('
            {
            match("CQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // Cami.g:25:5: ( 'AQ(' )
            // Cami.g:25:7: 'AQ('
            {
            match("AQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // Cami.g:26:5: ( 'TQ(' )
            // Cami.g:26:7: 'TQ('
            {
            match("TQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // Cami.g:27:5: ( '7' )
            // Cami.g:27:7: '7'
            {
            match('7'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // Cami.g:28:5: ( '8' )
            // Cami.g:28:7: '8'
            {
            match('8'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // Cami.g:29:5: ( 'QQ(' )
            // Cami.g:29:7: 'QQ('
            {
            match("QQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // Cami.g:30:5: ( 'TR(' )
            // Cami.g:30:7: 'TR('
            {
            match("TR("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // Cami.g:31:5: ( 'WN(' )
            // Cami.g:31:7: 'WN('
            {
            match("WN("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // Cami.g:32:5: ( 'MO(' )
            // Cami.g:32:7: 'MO('
            {
            match("MO("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // Cami.g:33:5: ( 'KO(1,' )
            // Cami.g:33:7: 'KO(1,'
            {
            match("KO(1,"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // Cami.g:34:5: ( 'DF(-2,' )
            // Cami.g:34:7: 'DF(-2,'
            {
            match("DF(-2,"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // Cami.g:35:5: ( 'DR()' )
            // Cami.g:35:7: 'DR()'
            {
            match("DR()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // Cami.g:36:5: ( '<EOF>' )
            // Cami.g:36:7: '<EOF>'
            {
            match("<EOF>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // Cami.g:37:5: ( 'RQ(' )
            // Cami.g:37:7: 'RQ('
            {
            match("RQ("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // Cami.g:38:5: ( 'FR(' )
            // Cami.g:38:7: 'FR('
            {
            match("FR("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // Cami.g:39:5: ( 'ZA(' )
            // Cami.g:39:7: 'ZA('
            {
            match("ZA("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // Cami.g:40:5: ( 'FE()' )
            // Cami.g:40:7: 'FE()'
            {
            match("FE()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start T42
    public final void mT42() throws RecognitionException {
        try {
            int _type = T42;
            // Cami.g:41:5: ( 'DE(' )
            // Cami.g:41:7: 'DE('
            {
            match("DE("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T42

    // $ANTLR start T43
    public final void mT43() throws RecognitionException {
        try {
            int _type = T43;
            // Cami.g:42:5: ( 'DE()' )
            // Cami.g:42:7: 'DE()'
            {
            match("DE()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T43

    // $ANTLR start T44
    public final void mT44() throws RecognitionException {
        try {
            int _type = T44;
            // Cami.g:43:5: ( 'RT(' )
            // Cami.g:43:7: 'RT('
            {
            match("RT("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T44

    // $ANTLR start T45
    public final void mT45() throws RecognitionException {
        try {
            int _type = T45;
            // Cami.g:44:5: ( 'RO(' )
            // Cami.g:44:7: 'RO('
            {
            match("RO("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T45

    // $ANTLR start T46
    public final void mT46() throws RecognitionException {
        try {
            int _type = T46;
            // Cami.g:45:5: ( 'ME(' )
            // Cami.g:45:7: 'ME('
            {
            match("ME("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T46

    // $ANTLR start T47
    public final void mT47() throws RecognitionException {
        try {
            int _type = T47;
            // Cami.g:46:5: ( 'MT(' )
            // Cami.g:46:7: 'MT('
            {
            match("MT("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T47

    // $ANTLR start T48
    public final void mT48() throws RecognitionException {
        try {
            int _type = T48;
            // Cami.g:47:5: ( 'CN(' )
            // Cami.g:47:7: 'CN('
            {
            match("CN("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T48

    // $ANTLR start T49
    public final void mT49() throws RecognitionException {
        try {
            int _type = T49;
            // Cami.g:48:5: ( 'CB(' )
            // Cami.g:48:7: 'CB('
            {
            match("CB("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T49

    // $ANTLR start T50
    public final void mT50() throws RecognitionException {
        try {
            int _type = T50;
            // Cami.g:49:5: ( 'CA(' )
            // Cami.g:49:7: 'CA('
            {
            match("CA("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T50

    // $ANTLR start T51
    public final void mT51() throws RecognitionException {
        try {
            int _type = T51;
            // Cami.g:50:5: ( 'CT(' )
            // Cami.g:50:7: 'CT('
            {
            match("CT("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T51

    // $ANTLR start T52
    public final void mT52() throws RecognitionException {
        try {
            int _type = T52;
            // Cami.g:51:5: ( 'CM(' )
            // Cami.g:51:7: 'CM('
            {
            match("CM("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T52

    // $ANTLR start T53
    public final void mT53() throws RecognitionException {
        try {
            int _type = T53;
            // Cami.g:52:5: ( 'SU(' )
            // Cami.g:52:7: 'SU('
            {
            match("SU("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T53

    // $ANTLR start T54
    public final void mT54() throws RecognitionException {
        try {
            int _type = T54;
            // Cami.g:53:5: ( 'SI(' )
            // Cami.g:53:7: 'SI('
            {
            match("SI("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T54

    // $ANTLR start T55
    public final void mT55() throws RecognitionException {
        try {
            int _type = T55;
            // Cami.g:54:5: ( 'TD(' )
            // Cami.g:54:7: 'TD('
            {
            match("TD("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T55

    // $ANTLR start T56
    public final void mT56() throws RecognitionException {
        try {
            int _type = T56;
            // Cami.g:55:5: ( 'OB(' )
            // Cami.g:55:7: 'OB('
            {
            match("OB("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T56

    // $ANTLR start T57
    public final void mT57() throws RecognitionException {
        try {
            int _type = T57;
            // Cami.g:56:5: ( 'AT(' )
            // Cami.g:56:7: 'AT('
            {
            match("AT("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T57

    // $ANTLR start T58
    public final void mT58() throws RecognitionException {
        try {
            int _type = T58;
            // Cami.g:57:5: ( 'DB()' )
            // Cami.g:57:7: 'DB()'
            {
            match("DB()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T58

    // $ANTLR start T59
    public final void mT59() throws RecognitionException {
        try {
            int _type = T59;
            // Cami.g:58:5: ( 'FB()' )
            // Cami.g:58:7: 'FB()'
            {
            match("FB()"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T59

    // $ANTLR start T60
    public final void mT60() throws RecognitionException {
        try {
            int _type = T60;
            // Cami.g:59:5: ( 'PO(' )
            // Cami.g:59:7: 'PO('
            {
            match("PO("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T60

    // $ANTLR start T61
    public final void mT61() throws RecognitionException {
        try {
            int _type = T61;
            // Cami.g:60:5: ( 'pO(' )
            // Cami.g:60:7: 'pO('
            {
            match("pO("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T61

    // $ANTLR start T62
    public final void mT62() throws RecognitionException {
        try {
            int _type = T62;
            // Cami.g:61:5: ( 'DS(' )
            // Cami.g:61:7: 'DS('
            {
            match("DS("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T62

    // $ANTLR start T63
    public final void mT63() throws RecognitionException {
        try {
            int _type = T63;
            // Cami.g:62:5: ( 'CE(' )
            // Cami.g:62:7: 'CE('
            {
            match("CE("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T63

    // $ANTLR start T64
    public final void mT64() throws RecognitionException {
        try {
            int _type = T64;
            // Cami.g:63:5: ( 'FF(' )
            // Cami.g:63:7: 'FF('
            {
            match("FF("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T64

    // $ANTLR start T65
    public final void mT65() throws RecognitionException {
        try {
            int _type = T65;
            // Cami.g:64:5: ( 'DC(' )
            // Cami.g:64:7: 'DC('
            {
            match("DC("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T65

    // $ANTLR start T66
    public final void mT66() throws RecognitionException {
        try {
            int _type = T66;
            // Cami.g:65:5: ( 'AD(' )
            // Cami.g:65:7: 'AD('
            {
            match("AD("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T66

    // $ANTLR start T67
    public final void mT67() throws RecognitionException {
        try {
            int _type = T67;
            // Cami.g:66:5: ( 'CD(' )
            // Cami.g:66:7: 'CD('
            {
            match("CD("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T67

    // $ANTLR start T68
    public final void mT68() throws RecognitionException {
        try {
            int _type = T68;
            // Cami.g:67:5: ( 'DG(' )
            // Cami.g:67:7: 'DG('
            {
            match("DG("); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T68

    // $ANTLR start CAMI_STRING
    public final void mCAMI_STRING() throws RecognitionException {
        try {
            int _type = CAMI_STRING;
            Token fs=null;
            Token NUMBER1=null;

            int nbToRead = 0;
            // Cami.g:763:6: ( NUMBER ':' fs= FIXED_LENGTH_STRING[nbToRead] )
            // Cami.g:764:2: NUMBER ':' fs= FIXED_LENGTH_STRING[nbToRead]
            {
            int NUMBER1Start518 = getCharIndex();
            mNUMBER(); 
            NUMBER1 = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, NUMBER1Start518, getCharIndex()-1);
            nbToRead = Integer.parseInt(NUMBER1.getText());
            match(':'); 
            int fsStart529 = getCharIndex();
            mFIXED_LENGTH_STRING(nbToRead); 
            fs = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fsStart529, getCharIndex()-1);
            setText(fs.getText());

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CAMI_STRING

    // $ANTLR start FIXED_LENGTH_STRING
    public final void mFIXED_LENGTH_STRING(int len) throws RecognitionException {
        try {
            // Cami.g:772:2: ( ({...}? => . )* )
            // Cami.g:773:2: ({...}? => . )*
            {
            // Cami.g:773:2: ({...}? => . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\uFFFE')) && ( len > 0 )) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Cami.g:773:4: {...}? => .
            	    {
            	    if ( !( len > 0 ) ) {
            	        throw new FailedPredicateException(input, "FIXED_LENGTH_STRING", " len > 0 ");
            	    }
            	    matchAny(); 
            	    len--;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end FIXED_LENGTH_STRING

    // $ANTLR start NUMBER
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            // Cami.g:776:8: ( ( '0' .. '9' )+ )
            // Cami.g:777:2: ( '0' .. '9' )+
            {
            // Cami.g:777:2: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Cami.g:777:2: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NUMBER

    // $ANTLR start NEWLINE
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            // Cami.g:782:2: ( ( ( '\\r' )? '\\n' )+ )
            // Cami.g:783:2: ( ( '\\r' )? '\\n' )+
            {
            // Cami.g:783:2: ( ( '\\r' )? '\\n' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\n'||LA4_0=='\r') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Cami.g:783:4: ( '\\r' )? '\\n'
            	    {
            	    // Cami.g:783:4: ( '\\r' )?
            	    int alt3=2;
            	    int LA3_0 = input.LA(1);

            	    if ( (LA3_0=='\r') ) {
            	        alt3=1;
            	    }
            	    switch (alt3) {
            	        case 1 :
            	            // Cami.g:783:4: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }

            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            skip();

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NEWLINE

    // $ANTLR start EOF
    public final void mEOF() throws RecognitionException {
        try {
            int _type = EOF;
            // Cami.g:786:9: ()
            // Cami.g:787:10: 
            {

                     System.out.println("je parse EOOOFFFFF"); 
                     skip();

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EOF

    public void mTokens() throws RecognitionException {
        // Cami.g:1:8: ( T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | CAMI_STRING | NUMBER | NEWLINE | EOF )
        int alt5=65;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // Cami.g:1:10: T8
                {
                mT8(); 

                }
                break;
            case 2 :
                // Cami.g:1:13: T9
                {
                mT9(); 

                }
                break;
            case 3 :
                // Cami.g:1:16: T10
                {
                mT10(); 

                }
                break;
            case 4 :
                // Cami.g:1:20: T11
                {
                mT11(); 

                }
                break;
            case 5 :
                // Cami.g:1:24: T12
                {
                mT12(); 

                }
                break;
            case 6 :
                // Cami.g:1:28: T13
                {
                mT13(); 

                }
                break;
            case 7 :
                // Cami.g:1:32: T14
                {
                mT14(); 

                }
                break;
            case 8 :
                // Cami.g:1:36: T15
                {
                mT15(); 

                }
                break;
            case 9 :
                // Cami.g:1:40: T16
                {
                mT16(); 

                }
                break;
            case 10 :
                // Cami.g:1:44: T17
                {
                mT17(); 

                }
                break;
            case 11 :
                // Cami.g:1:48: T18
                {
                mT18(); 

                }
                break;
            case 12 :
                // Cami.g:1:52: T19
                {
                mT19(); 

                }
                break;
            case 13 :
                // Cami.g:1:56: T20
                {
                mT20(); 

                }
                break;
            case 14 :
                // Cami.g:1:60: T21
                {
                mT21(); 

                }
                break;
            case 15 :
                // Cami.g:1:64: T22
                {
                mT22(); 

                }
                break;
            case 16 :
                // Cami.g:1:68: T23
                {
                mT23(); 

                }
                break;
            case 17 :
                // Cami.g:1:72: T24
                {
                mT24(); 

                }
                break;
            case 18 :
                // Cami.g:1:76: T25
                {
                mT25(); 

                }
                break;
            case 19 :
                // Cami.g:1:80: T26
                {
                mT26(); 

                }
                break;
            case 20 :
                // Cami.g:1:84: T27
                {
                mT27(); 

                }
                break;
            case 21 :
                // Cami.g:1:88: T28
                {
                mT28(); 

                }
                break;
            case 22 :
                // Cami.g:1:92: T29
                {
                mT29(); 

                }
                break;
            case 23 :
                // Cami.g:1:96: T30
                {
                mT30(); 

                }
                break;
            case 24 :
                // Cami.g:1:100: T31
                {
                mT31(); 

                }
                break;
            case 25 :
                // Cami.g:1:104: T32
                {
                mT32(); 

                }
                break;
            case 26 :
                // Cami.g:1:108: T33
                {
                mT33(); 

                }
                break;
            case 27 :
                // Cami.g:1:112: T34
                {
                mT34(); 

                }
                break;
            case 28 :
                // Cami.g:1:116: T35
                {
                mT35(); 

                }
                break;
            case 29 :
                // Cami.g:1:120: T36
                {
                mT36(); 

                }
                break;
            case 30 :
                // Cami.g:1:124: T37
                {
                mT37(); 

                }
                break;
            case 31 :
                // Cami.g:1:128: T38
                {
                mT38(); 

                }
                break;
            case 32 :
                // Cami.g:1:132: T39
                {
                mT39(); 

                }
                break;
            case 33 :
                // Cami.g:1:136: T40
                {
                mT40(); 

                }
                break;
            case 34 :
                // Cami.g:1:140: T41
                {
                mT41(); 

                }
                break;
            case 35 :
                // Cami.g:1:144: T42
                {
                mT42(); 

                }
                break;
            case 36 :
                // Cami.g:1:148: T43
                {
                mT43(); 

                }
                break;
            case 37 :
                // Cami.g:1:152: T44
                {
                mT44(); 

                }
                break;
            case 38 :
                // Cami.g:1:156: T45
                {
                mT45(); 

                }
                break;
            case 39 :
                // Cami.g:1:160: T46
                {
                mT46(); 

                }
                break;
            case 40 :
                // Cami.g:1:164: T47
                {
                mT47(); 

                }
                break;
            case 41 :
                // Cami.g:1:168: T48
                {
                mT48(); 

                }
                break;
            case 42 :
                // Cami.g:1:172: T49
                {
                mT49(); 

                }
                break;
            case 43 :
                // Cami.g:1:176: T50
                {
                mT50(); 

                }
                break;
            case 44 :
                // Cami.g:1:180: T51
                {
                mT51(); 

                }
                break;
            case 45 :
                // Cami.g:1:184: T52
                {
                mT52(); 

                }
                break;
            case 46 :
                // Cami.g:1:188: T53
                {
                mT53(); 

                }
                break;
            case 47 :
                // Cami.g:1:192: T54
                {
                mT54(); 

                }
                break;
            case 48 :
                // Cami.g:1:196: T55
                {
                mT55(); 

                }
                break;
            case 49 :
                // Cami.g:1:200: T56
                {
                mT56(); 

                }
                break;
            case 50 :
                // Cami.g:1:204: T57
                {
                mT57(); 

                }
                break;
            case 51 :
                // Cami.g:1:208: T58
                {
                mT58(); 

                }
                break;
            case 52 :
                // Cami.g:1:212: T59
                {
                mT59(); 

                }
                break;
            case 53 :
                // Cami.g:1:216: T60
                {
                mT60(); 

                }
                break;
            case 54 :
                // Cami.g:1:220: T61
                {
                mT61(); 

                }
                break;
            case 55 :
                // Cami.g:1:224: T62
                {
                mT62(); 

                }
                break;
            case 56 :
                // Cami.g:1:228: T63
                {
                mT63(); 

                }
                break;
            case 57 :
                // Cami.g:1:232: T64
                {
                mT64(); 

                }
                break;
            case 58 :
                // Cami.g:1:236: T65
                {
                mT65(); 

                }
                break;
            case 59 :
                // Cami.g:1:240: T66
                {
                mT66(); 

                }
                break;
            case 60 :
                // Cami.g:1:244: T67
                {
                mT67(); 

                }
                break;
            case 61 :
                // Cami.g:1:248: T68
                {
                mT68(); 

                }
                break;
            case 62 :
                // Cami.g:1:252: CAMI_STRING
                {
                mCAMI_STRING(); 

                }
                break;
            case 63 :
                // Cami.g:1:264: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 64 :
                // Cami.g:1:271: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 65 :
                // Cami.g:1:279: EOF
                {
                match(EOF); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\1\30\13\uffff\1\106\1\110\10\uffff\1\114\66\uffff\1\120\1\122\4"+
        "\uffff";
    static final String DFA5_eofS =
        "\123\uffff";
    static final String DFA5_minS =
        "\1\12\1\103\1\uffff\1\102\1\uffff\1\101\1\104\1\117\1\111\1\102"+
        "\1\101\1\104\2\60\2\uffff\1\105\5\uffff\1\60\22\uffff\1\50\16\uffff"+
        "\1\50\24\uffff\2\51\4\uffff";
    static final String DFA5_maxS =
        "\1\160\1\125\1\uffff\1\123\1\uffff\1\123\1\122\1\124\1\121\1\123"+
        "\2\124\2\72\2\uffff\1\124\5\uffff\1\72\22\uffff\1\50\16\uffff\1"+
        "\50\24\uffff\2\51\4\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\1\uffff\1\4\11\uffff\1\27\1\31\1\uffff\1\33\1\36\1"+
        "\41\1\65\1\66\1\uffff\1\100\1\101\1\56\1\57\1\11\1\1\1\6\1\61\1"+
        "\3\1\64\1\5\1\40\1\10\1\20\1\71\1\42\1\13\1\16\1\uffff\1\14\1\30"+
        "\1\24\1\46\1\37\1\45\1\12\1\21\1\15\1\17\1\67\1\35\1\72\1\63\1\uffff"+
        "\1\75\1\34\1\74\1\53\1\51\1\52\1\22\1\54\1\70\1\55\1\23\1\73\1\62"+
        "\1\25\1\76\1\26\1\50\1\47\1\32\1\77\2\uffff\1\7\1\60\1\44\1\43";
    static final String DFA5_specialS =
        "\123\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\27\2\uffff\1\27\33\uffff\1\2\2\uffff\1\4\3\uffff\7\26\1\14"+
            "\1\15\1\26\2\uffff\1\22\4\uffff\1\13\1\uffff\1\12\1\11\1\uffff"+
            "\1\5\4\uffff\1\21\1\uffff\1\20\1\uffff\1\3\1\24\1\16\1\7\1\1"+
            "\1\6\1\uffff\1\10\1\17\2\uffff\1\23\25\uffff\1\25",
            "\1\34\5\uffff\1\32\11\uffff\1\33\1\uffff\1\31",
            "",
            "\1\36\1\37\17\uffff\1\35",
            "",
            "\1\43\1\40\1\41\1\uffff\1\46\1\45\5\uffff\1\50\4\uffff\1\44"+
            "\1\42\1\47",
            "\1\51\7\uffff\1\52\4\uffff\1\54\1\53",
            "\1\55\1\uffff\1\56\1\uffff\1\60\1\57",
            "\1\62\7\uffff\1\61",
            "\1\67\1\66\1\uffff\1\70\1\72\1\71\11\uffff\1\63\1\65\1\64",
            "\1\74\1\76\1\uffff\1\73\1\101\7\uffff\1\102\1\75\2\uffff\1\77"+
            "\2\uffff\1\100",
            "\1\104\14\uffff\1\103\2\uffff\1\105",
            "\12\26\1\107",
            "\12\26\1\107",
            "",
            "",
            "\1\112\11\uffff\1\113\4\uffff\1\111",
            "",
            "",
            "",
            "",
            "",
            "\12\26\1\107",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\115",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\116",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\117",
            "\1\121",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T8 | T9 | T10 | T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | T56 | T57 | T58 | T59 | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | CAMI_STRING | NUMBER | NEWLINE | EOF );";
        }
    }
 

}