package fr.lip6.move.coloane.api.camiCommands;

public final class AckOpenConnection {

	public int versionPart1;
	public int versionPart2;
	
	public AckOpenConnection(int versionPart1, int versionPart2) {
		this.versionPart1 = versionPart1;
		this.versionPart2 = versionPart2;
	}
}
