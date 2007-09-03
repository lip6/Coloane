package fr.lip6.move.coloane.api.cami.output;

import org.antlr.stringtemplate.StringTemplate;

import com.sun.tools.javac.util.List;

import fr.lip6.move.coloane.api.cami.DialogAnswer;
import fr.lip6.move.coloane.api.cami.Question;
import fr.lip6.move.coloane.api.cami.types.InteractiveAnswerType;
import fr.lip6.move.coloane.api.cami.types.UrgentMessageType;

public class CamiBuilder {

	static StringTemplate camiTemplate = new StringTemplate("$command$($arg; separator=\",\"$)");

	/** Generate a CAMI command */
	private synchronized static String generate(String commandName, Object... args) {

		camiTemplate.reset();
		camiTemplate.setAttribute("command", commandName);
		for (Object arg : args) {
			if( arg != null ) {
				if (arg instanceof String) {
					camiTemplate.setAttribute("arg", ((String) arg).length() + ":" + (String) arg);
				} else if (arg instanceof Integer) {
					camiTemplate.setAttribute("arg", ((Integer) arg));
				}
			}
		}
		System.out.println("Generated command: " + camiTemplate.toString());
		return camiTemplate.toString();
	}

	/** To authenticate ourselves */
	public static byte[] authentication(String login, String password) {
		return generate("SC", login, password).getBytes();
	}

	/** To give our version */
	public static byte[] clientVersion(String apiName, String apiVersion, String login) {
		return generate("OC", apiName, apiVersion, login, 0).getBytes();
	}

	public static byte[] closeConnection() {
		return "FC".getBytes();
	}

	public static byte[] answerInterlocutorTable() {
		return "DI()CI(0:,0)FI()".getBytes();
	}

	public static byte[] askForService(List<Question> questions, List<String> textList, List<Integer> objectList) {
		String command = "DQ()";
		for (Question q : questions) {
			command += generate("PQ", q.rootName, q.elementName, "");
		}
		command += "DE()";
		if (textList != null) {
			for (String s : textList) {
				command += generate("QT", s);
			}
		}
		if (objectList != null) {
			for (Integer i : objectList) {
				command += generate("QO", i);
			}
		}
		command += "FE()";
		command += "FT()";
		return command.getBytes();
	}

	public static byte[] changeDate(int date) {
		return generate("MS", date).getBytes();
	}

	public static byte[] openSession(String sessionName, int date, String sessionFormalism) {
		return generate("OS", sessionName, date, sessionFormalism).getBytes();
	}
	
	public static byte[] closeCurrentSession(boolean runInBackGround) {
		return generate("FS", runInBackGround ? 0 : 1).getBytes();
	}

	public static byte[] suspendCurrentSession() {
		return "SS()".getBytes();
	}
	
	public static byte[] resumeSuspendedSession(String sessionName) {
		return generate("RS",sessionName).getBytes();
	}
	
	public static byte[] dialogAnswer(DialogAnswer answer) {
		String command = "DP()";
		command += generate("RD", answer.dialogID, answer.canceledAnswer?2:1, answer.hasModifiedEntry?2:2, answer.valueEntered);
		if( !answer.canceledAnswer ) {
			command += "DE()";
			for( String s : answer.lines ) {
				command += generate("DS", answer.dialogID, s);
			}
			command += "FE()";
		}
		command += "FP()";
		return command.getBytes();
	}
	
	public static byte[] interactiveAnswer(int dialogID, InteractiveAnswerType answerType, String message) {
		int typeValue = -1;
		switch (answerType) {
			case errorMessage:
				typeValue = 3;
				break;
			case warningMessage:
				typeValue = 2;
				break;
			case normalMessage:
				typeValue = 1;
				break;
			case normalMessageWithDisplay:
				typeValue = 0;
				break;
			default:
				break;
		}
		return generate("RI", dialogID, typeValue, message).getBytes();
	}
	
	public static byte[] urgentMessage(int dialogID, UrgentMessageType messageType) {
		int typeValue = -1;
		switch (messageType) {
			case abort:
				typeValue = 1;
				break;
			case quit:
				typeValue = 2;
				break;
			default:
				break;
		}
		return generate("MU",dialogID,typeValue).getBytes();
	}
	
	private CamiBuilder() {
	}
}
