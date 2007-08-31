package fr.lip6.move.coloane.api.session.states.authentication;

import fr.lip6.move.coloane.api.session.controller.IController;
import fr.lip6.move.coloane.api.session.controller.IMessage;
import fr.lip6.move.coloane.api.session.controller.IState;
import fr.lip6.move.coloane.api.session.controller.State;

public final class AuthenticationState extends State {

	public AuthenticationState(IController controller) {
		super(controller);
	}

	public IState apply(IMessage m) throws RewindException, ErrorException {
		
		try {
			
			try {
				LoginPassword mess = (LoginPassword)m;
				
				String command = "SC(" 	+ mess.getLogin().length() + ':' + mess.getLogin() + ',' 
										+ mess.getPassword().length() + ':' + mess.getPassword() + ')';
				
				this.getController().getToFrameKit().write(command.getBytes());
				System.err.println("sent: " + command);
				this.getController().getParser().open_communication();
				
				command = "OC(" +
								mess.getApiName().length() + ':' + mess.getApiName() + ',' +
								mess.getApiVersion().length() + ':' + mess.getApiVersion() + ',' +
								mess.getLogin().length() + ':' + mess.getLogin() + ',' +
								'0' +
							 ')';
		
				this.getController().getToFrameKit().write(command.getBytes());
				System.err.println("sent: " + command);
				this.getController().getToColoane().put(this.getController().getParser().check_version());
				
				return this;
			
			} catch (AuthenticationFailure e) {
				this.getController().getToColoane().put(e);
				return this;
			
			} catch (VersionFailure e) {
				this.getController().getToColoane().put(e);
				return this;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorException();
		} 
	}
}
