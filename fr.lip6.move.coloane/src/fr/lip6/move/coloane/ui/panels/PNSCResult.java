package fr.lip6.move.coloane.ui.panels;

public class PNSCResult {
	protected String resultName;
	protected String resultDescription;
	
	public PNSCResult(String resultName,
			String resultDescription) {
		this.resultName = resultName;
		this.resultDescription = resultDescription;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public String getResultName() {
		return resultName;
	}
}
