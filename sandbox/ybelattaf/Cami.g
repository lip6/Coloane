grammar Cami;

@header{
    import fr.lip6.move.coloane.api.cami.CamiObjectBuilder;
    import fr.lip6.move.coloane.api.interfaces.IFKVersion;

    import java.util.ArrayList;
}

@members{
    ArrayList<String> listOfArgs;
}

/* Ouverture de la connexion  SC */
ack_open_communication
	:
	'SC(' CAMI_STRING ')'{
            
            listOfArgs.add("SC");
            listOfArgs.add($CAMI_STRING.text);
        }
	;
	

/* Ouverture de la connexion OC */
ack_open_connection
	:
	'OC(' 
    v1=NUMBER {
            listOfArgs.add($v1.text);
        } 
    ',' 
    v2=NUMBER {listOfArgs.add($v2.text);
            IFKVersion info = CamiObjectBuilder.buildFkVersion(listOfArgs);
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

fragment
FIXED_LENGTH_STRING
	[int len]
	:   
	( { len > 0 }?=> .{len--;})* // Gated predicate : deactivate the '.' when len chars have been read
	;

NUMBER	: 	
	'0'..'9'+
	;
