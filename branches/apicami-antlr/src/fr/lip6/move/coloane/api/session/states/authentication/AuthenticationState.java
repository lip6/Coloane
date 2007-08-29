package fr.lip6.move.coloane.api.session.states.authentication;

import fr.lip6.move.coloane.api.session.controller.IController;
import fr.lip6.move.coloane.api.session.controller.IMessage;
import fr.lip6.move.coloane.api.session.controller.IState;
import fr.lip6.move.coloane.api.session.controller.State;

public final class AuthenticationState extends State {

	public AuthenticationState(IController controller) {
		super(controller);
	}

	public IState apply(IMessage m, Kind k) throws RewindException, ErrorException {
		
		try {
			
			try {
				LoginPassword mess = (LoginPassword)m;
				
				String command = "SC(" + mess.getLogin().length() + ':' + mess.getLogin() + ',' 
										+ mess.getPassword().length() + ':' + mess.getPassword() + ')';
				
				this.getController().getToFrameKit().write(command.getBytes());
				
//				byte[] answer = new byte[4];
//				this.getController().getFromFrameKit().read(answer,0,4);
//				int size = new Byte(answer[3]).intValue();
				
//				System.err.println( "size = |" + size  + "|" );
//				answer = new byte[size];
//				this.getController().getFromFrameKit().read(answer,0,size);
//				System.err.println(new String(answer));
				
//				this.getParser().open_communication();

				byte[] tmp = new byte[256];
				this.getController().getFromFrameKit().read(tmp, 0, 61);
				System.out.println("tmp" + new String(tmp));
				
				command = "OC(" +
								mess.getApiName().length() + ':' + mess.getApiName() + ',' +
								mess.getApiVersion().length() + ':' + mess.getApiVersion() + ',' +
								mess.getLogin().length() + ':' + mess.getLogin() + ',' +
								'0' +
							 ')';
		
				this.getController().getToFrameKit().write(command.getBytes());

				tmp = new byte[256];
				this.getController().getFromFrameKit().read(tmp, 0, 7);
				System.out.println("tmp" + new String(tmp));
				
//				answer = new byte[4];
//				this.getController().getFromFrameKit().read(answer,0,4);
//				size = new Byte(answer[3]).intValue();
//				
//				System.err.println( "size = |" + size  + "|" );
//				answer = new byte[size];
//				this.getController().getFromFrameKit().read(answer,0,size);
//				System.err.println(new String(answer));
			
				this.getController().getToColoane().put(this.getParser().check_version());
				
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
