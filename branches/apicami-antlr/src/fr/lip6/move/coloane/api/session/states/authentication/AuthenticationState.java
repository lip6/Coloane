package fr.lip6.move.coloane.api.session.states.authentication;

import fr.lip6.move.coloane.api.cami.ICommand;
import fr.lip6.move.coloane.api.cami.VoidVisitor;
import fr.lip6.move.coloane.api.cami.input.connection.AckOpenCommunication;
import fr.lip6.move.coloane.api.cami.input.connection.AckOpenConnection;
import fr.lip6.move.coloane.api.cami.input.connection.CloseConnectionPanic;
import fr.lip6.move.coloane.api.cami.output.CamiBuilder;
import fr.lip6.move.coloane.api.session.controller.IController;
import fr.lip6.move.coloane.api.session.controller.IMessage;
import fr.lip6.move.coloane.api.session.controller.IState;
import fr.lip6.move.coloane.api.session.controller.State;
import fr.lip6.move.coloane.api.session.states.VoidMessage;

public final class AuthenticationState extends State {

	public AuthenticationState(IController controller) {
		super(controller);
	}

	
	public class ConnectionVisitor extends VoidVisitor<ICommand> {
		
		public void defaultVisit(ICommand o) {
		}
		
		public void visitExact(CloseConnectionPanic ccp) {
			System.out.println(this.getClass().getName() + "Throws an exception");
		}
	}
	
	public class AuthenticationVisitor extends ConnectionVisitor {

		public void visitExact(AckOpenCommunication aoc) {
			System.out.println("Set connectionResult.fkId to " + aoc.getHostInformation()); // TODO do the real stuff!!!
		} 
	}
	
	public class VersionVisitor extends ConnectionVisitor {
		
		public void visitExact(AckOpenConnection aoc) {
			System.out.println("Set connectionResult.version to " + aoc.version()); // TODO do the real stuff!!!
		}
		
	}
	
	public IState apply(IMessage message) throws RewindException, ErrorException {
		
		try {
				ICommand command;
			
				LoginPassword mess = (LoginPassword)message;
				
				this.getController().getToFrameKit().write(CamiBuilder.authentication(mess.getLogin(),mess.getPassword()));
				command = this.getController().getParser().open_communication();
				new AuthenticationVisitor().visit(command);
				
				
				this.getController().getToFrameKit().write(CamiBuilder.clientVersion(mess.getApiName(),mess.getApiVersion(),mess.getLogin()));
				
				command = this.getController().getParser().check_version();
				new VersionVisitor().visit(command);
								
				this.getController().getToColoane().put(new VoidMessage());
				// TODO: this.getController.addState();
							
				return this;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorException();
		} 
	}
}
