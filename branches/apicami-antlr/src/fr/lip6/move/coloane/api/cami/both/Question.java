package fr.lip6.move.coloane.api.cami.both;

public class Question {

	private String rootName;
	private String elementName;
	private String moreInformation;

	public Question(String rootName, String elementName, String moreInformation) {
		this.rootName = rootName;
		this.elementName = elementName;
		this.moreInformation = moreInformation;
	}

	public String getRootName() {
		return rootName;
	}

	public String getElementName() {
		return elementName;
	}

	public String getMoreInformation() {
		return moreInformation;
	}

}
