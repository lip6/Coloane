package fr.lip6.move.coloane.api.cami.input.connection;

import fr.lip6.move.coloane.api.cami.ICommand;

public final class AckOpenCommunication implements ICommand {

	private String hostInformation;

	public AckOpenCommunication(String hostInformation) {
		this.hostInformation = hostInformation;
	}

	public String getHostInformation() {
		return hostInformation;
	}
}
