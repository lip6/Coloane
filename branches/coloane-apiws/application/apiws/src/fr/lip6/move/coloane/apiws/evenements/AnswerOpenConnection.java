package fr.lip6.move.coloane.apiws.evenements;

import fr.lip6.move.coloane.apiws.interfaces.evenements.IAnswerOpenConnection;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;

public class AnswerOpenConnection implements IAnswerOpenConnection {
	
	private String fkVersion;
	private int major;
	private int minor;
	
	public AnswerOpenConnection(Authentification a){
		this.fkVersion = a.getAckSC().getHostInformation();
		this.major = a.getAckOC().getMajor();
		this.minor = a.getAckOC().getMinor();
	}

	public int getFkMajor() {
		return major;
	}

	public int getFkMinor() {
		return minor;
	}

	public String getFkVersion() {
		return fkVersion;
	}

}
