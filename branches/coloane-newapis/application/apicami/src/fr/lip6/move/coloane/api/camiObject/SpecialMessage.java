package fr.lip6.move.coloane.api.camiObject;


public class SpecialMessage implements ISpecialMessage{

	private MessageType type;
	private String content;
	
	public SpecialMessage(int val , String contenu){
		this.type = MessageType(val);
		this.content = contenu;
		
	}
	
	public static MessageType MessageType(int i) {
		MessageType toReturn = MessageType.Warning;
		for (MessageType s : MessageType.values()) {
			if (s.value == i) {
				toReturn = s;
			}
		}
		return toReturn;
	}
	
	public String getContent() {
		return this.content;
	}

	public MessageType getType() {
		return this.type;
	}

}
