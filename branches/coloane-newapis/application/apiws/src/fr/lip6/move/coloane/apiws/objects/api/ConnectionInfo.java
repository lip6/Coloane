package fr.lip6.move.coloane.apiws.objects.api;

import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;

public class ConnectionInfo implements IConnectionInfo {
	
	private String fkName;
	
	private int major;
	
	private int minor;
	
	public ConnectionInfo(Authentification a){
		this.fkName = a.getAckSC().getHostInformation();
		this.major = a.getAckOC().getMajor();
		this.minor = a.getAckOC().getMinor();
	}
	
	public int getFkMajor() {
		return major;
	}

	public int getFkMinor() {
		return minor;
	}

	public String getFkName() {
		return  fkName;
	}

}