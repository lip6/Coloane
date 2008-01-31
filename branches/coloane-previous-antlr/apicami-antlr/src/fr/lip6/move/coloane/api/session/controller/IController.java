package fr.lip6.move.coloane.api.session.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

import fr.lip6.move.coloane.api.cami.input.parser.CamiParser;
import fr.lip6.move.coloane.api.framekit.Network;

public interface IController extends Runnable {

	public class SessionIdentifier {
		private BigInteger id;

		public SessionIdentifier(BigInteger id) {
			this.id = id;
		}

		public BigInteger getId() {
			return id;
		}
	}
		
	class UnknownSessionException extends Exception {
		private static final long serialVersionUID = 6152106346632100839L;
	}
	
	public void changeSession(SessionIdentifier id) throws UnknownSessionException;
	
	public SessionIdentifier getDefaultSessionIdentifier();
	public void addState(IState s);
	public SessionIdentifier addSession(IState s);
	
	public InputStream getFromFrameKit() throws IOException;

	public OutputStream getToFrameKit() throws IOException;

	public BlockingQueue<IMessage> getFromColoane();

	public BlockingQueue<IMessage> getToColoane();

	public Network getFrameKit();

	public void setFrameKit(Network frameKit) throws IOException;

	public void setFromColoane(BlockingQueue<IMessage> fromColoane);

	public void setToColoane(BlockingQueue<IMessage> toColoane);
	
	public CamiParser getParser();

}
