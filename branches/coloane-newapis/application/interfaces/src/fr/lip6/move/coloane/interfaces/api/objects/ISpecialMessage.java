package fr.lip6.move.coloane.interfaces.api.objects;

public interface ISpecialMessage {
	 
	 public enum MessageType {
		 Warning(1), TraceMassage(2), ServiceState(3), Other(4);
		 
			public int value;

			private MessageType(int value) {
				this.value = value;
			}

			public int getInt() {
				return this.value;
			}
			
		}
	 
	 public MessageType getType();
	 
	 public String getContent();
	
	 
}
