package fr.lip6.move.coloane.api.camiCommands;

import org.antlr.stringtemplate.StringTemplate;

public class CamiBuilder {

	static StringTemplate camiTemplate = new StringTemplate("$command$($arg; separator=\",\"$)");
	
	private synchronized static String generate(String commandName , Object ... args) {
		
		camiTemplate.reset(); 
		
		camiTemplate.setAttribute("command", commandName);
		for( Object arg : args ) {
			if( arg instanceof String ) {
				camiTemplate.setAttribute("arg",((String)arg).length() + ":" + (String)arg);
			}
			else
			{
				camiTemplate.setAttribute("arg", ((Integer)arg).intValue() );
			}
		}

		System.out.println("Generated command: " + camiTemplate.toString()); // TODO remove
		
		return camiTemplate.toString();
	}

	public static String authentication(String login, String password) {
		return generate("SC",login,password);
	}

	public static String clientVersion(String apiName, String apiVersion, String login) {
		return generate("OC",apiName,apiVersion,login,0);
	}
	
}
