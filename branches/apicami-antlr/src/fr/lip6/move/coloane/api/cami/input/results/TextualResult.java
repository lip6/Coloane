package fr.lip6.move.coloane.api.cami.input.results;

public final class TextualResult implements IResult {

	private String textResult;

	public TextualResult(String textResult) {
		this.textResult = textResult;
	}

	public String getTextResult() {
		return textResult;
	}

}
