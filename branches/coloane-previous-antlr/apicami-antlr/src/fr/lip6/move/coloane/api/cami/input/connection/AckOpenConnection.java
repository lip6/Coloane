package fr.lip6.move.coloane.api.cami.input.connection;

import fr.lip6.move.coloane.api.cami.ICommand;

public final class AckOpenConnection implements ICommand {

	private int major;
	private int minor;

	public AckOpenConnection(int major, int minor) {
		this.major = major;
		this.minor = minor;
	}

	public int getVersionPart1() {
		return major;
	}

	public int getVersionPart2() {
		return minor;
	}

	public String version() {
		return major + "." + minor;
	}
}
