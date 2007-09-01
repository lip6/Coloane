package fr.lip6.move.coloane.api.session.states.authentication;

import fr.lip6.move.coloane.api.camiCommands.CamiBuilder;
import fr.lip6.move.coloane.api.session.controller.IController;
import fr.lip6.move.coloane.api.session.controller.IMessage;
import fr.lip6.move.coloane.api.session.controller.IState;
import fr.lip6.move.coloane.api.session.controller.State;
import fr.lip6.move.coloane.api.session.states.MessageFormatFailure;

public final class AuthenticationState extends State {

	public AuthenticationState(IController controller) {
		super(controller);
	}

	public IState apply(IMessage message) throws RewindException, ErrorException {
		
		try {
			
			try {
				LoginPassword mess = (LoginPassword)message;
				
				this.getController().getToFrameKit().write(CamiBuilder.authentication(mess.getLogin(),mess.getPassword()));
				this.getController().getParser().open_communication();
				
				this.getController().getToFrameKit().write(CamiBuilder.clientVersion(mess.getApiName(),mess.getApiVersion(),mess.getLogin()));
				this.getController().getToColoane().put(this.getController().getParser().check_version());
				
				// TODO: this.getController.addState();
				
				return this;
			
			} catch (AuthenticationFailure e) {
				this.getController().getToColoane().put(e);
				return this;
			
			} catch (VersionFailure e) {
				this.getController().getToColoane().put(e);
				return this;
				
			} catch (MessageFormatFailure e) {
				this.getController().getToColoane().put(e);
				return this;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorException();
		} 
	}
}
