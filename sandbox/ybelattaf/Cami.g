grammar Cami;

@header{
//package fr.lip6.move.coloane.api.camiParser;

//import fr.lip6.move.coloane.api.session.states.authentication.*;
    import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
    import java.util.ArrayList;
}

@member{
    ArrayList<String> listOfArgs;
}

/* Ouverture de la connexion */
ack_open_communication
	:
	'SC(' CAMI_STRING ')'{
            
            listOfArgs.add("SC");
            listOfArgs.add($CAMI_STRING.text);
        }
	;
	

ack_open_connection
	:
	'OC(' NUMBER {
            listOfArgs.add($CAMI_STRING.text);
        } 
    ',' 
    NUMBER {listOfArgs.add($CAMI_STRING.text);
            CamiObjectBuilder.buildFkInfo(listOfArgs);
        }
    ')'    
	;


CAMI_STRING
	@init{int nbToRead = 0;}
    	:
	NUMBER {nbToRead = Integer.parseInt($NUMBER.text);}
	':' 
	fs=FIXED_LENGTH_STRING[nbToRead]{setText($fs.text);}
	;

NUMBER	: 	
	'0'..'9'+
	;
