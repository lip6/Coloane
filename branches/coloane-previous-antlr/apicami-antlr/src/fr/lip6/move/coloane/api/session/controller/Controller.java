package fr.lip6.move.coloane.api.session.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;

import fr.lip6.move.coloane.api.cami.input.parser.ANTLRSocketStream;
import fr.lip6.move.coloane.api.cami.input.parser.CamiLexer;
import fr.lip6.move.coloane.api.cami.input.parser.CamiParser;
import fr.lip6.move.coloane.api.framekit.Network;


public final class Controller implements IController {

	class EmergencyStopException extends Exception {
		private static final long serialVersionUID = -6204590417521133935L;
	}

	private Network frameKit;
	private BlockingQueue<IMessage> fromColoane;
	private BlockingQueue<IMessage> toColoane;
	private Map<SessionIdentifier, Stack<IState>> protocols;
	private final SessionIdentifier defaultSession;
	private SessionIdentifier activeSession;
	private SessionIdentifier nextSession;
	private CharStream parserStream;
	
	public Controller() throws IOException {
		this.frameKit= null;
		this.parserStream = null;
		this.fromColoane = new LinkedBlockingQueue<IMessage>();
		this.toColoane = new LinkedBlockingQueue<IMessage>();
		this.protocols = new HashMap<SessionIdentifier, Stack<IState>>();
		this.defaultSession = new SessionIdentifier(new BigInteger("0"));
		this.activeSession = defaultSession;
		this.nextSession = new SessionIdentifier(new BigInteger("1"));
		this.protocols.put(this.defaultSession, new Stack<IState>());
	}

	public void apply() throws EmergencyStopException {
		do {
			try {
				this.apply(this.fromColoane.take());
			} catch (InterruptedException e) {
				throw new EmergencyStopException();
			}
		} while (true);
	}

	private void apply(IMessage m) throws EmergencyStopException {
		Stack<IState> states = this.protocols.get(this.activeSession);
		try {
			stackLoop: while (true) {
				IState top = states.peek();
				try {
					states.push(top.apply(m));
					break stackLoop;
				} catch (IState.RewindException e) {
					states.pop();
				}
			}
		} catch (EmptyStackException x) {
			throw new EmergencyStopException();
		} catch (IState.ErrorException e) {
			throw new EmergencyStopException();
		}
	}	

	public SessionIdentifier getDefaultSessionIdentifier() {
		return this.defaultSession;
	}

	public void addState(IState s) {
		Stack<IState> stack = this.protocols.get(this.activeSession);
		stack.push(s);
	}

	public SessionIdentifier addSession(IState s) {
		SessionIdentifier newIdentifier = this.nextSession;
		this.nextSession = new SessionIdentifier(newIdentifier.getId().add(new BigInteger("1")));
		Stack<IState> newStack = (Stack<IState>) this.protocols.get(this.defaultSession).clone();
		newStack.push(s);
		this.protocols.put(newIdentifier, newStack);
		return newIdentifier;
	}
	
	public void changeSession(SessionIdentifier id) throws UnknownSessionException {
		this.activeSession = id;
		if (this.protocols.get(this.activeSession) == null) {
			throw new UnknownSessionException();
		}
	}
	
	public InputStream getFromFrameKit() throws IOException {
		System.out.println("Controller.getFromFrameKit()");
		return this.frameKit.getInput();
	}

	public OutputStream getToFrameKit() throws IOException {
		return this.frameKit.getOutput();
	}

	public BlockingQueue<IMessage> getFromColoane() {
		return fromColoane;
	}

	public BlockingQueue<IMessage> getToColoane() {
		return toColoane;
	}

	public Network getFrameKit() {
		return frameKit;
	}

	public void setFrameKit(Network frameKit) throws IOException {
		this.frameKit = frameKit;
		//this.parserStream  = new ANTLRSocketStreamDebug(this.frameKit.getInput());
		this.parserStream  = new ANTLRSocketStream(this.frameKit.getInput());
//		this.parser = new CamiParser(new CommonTokenStream(new CamiLexer(new ANTLRSocketStreamDebug(this.frameKit.getInput()))));
	}

	public void setFromColoane(BlockingQueue<IMessage> fromColoane) {
		this.fromColoane = fromColoane;
	}

	public void setToColoane(BlockingQueue<IMessage> toColoane) {
		this.toColoane = toColoane;
	}

	public void run() {
		try {
			this.apply();
		} catch (EmergencyStopException e) {
		}
	}

	public CamiParser getParser() {
		return new CamiParser(new CommonTokenStream(new CamiLexer(this.parserStream)));
//		return this.parser;
	}	
	
}
