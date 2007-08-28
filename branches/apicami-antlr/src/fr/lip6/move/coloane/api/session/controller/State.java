package fr.lip6.move.coloane.api.session.controller;

import java.io.IOException;

import org.antlr.runtime.*;

import fr.lip6.move.coloane.api.camiParser.CamiLexer;
import fr.lip6.move.coloane.api.camiParser.CamiParser;

public abstract class State implements IState {

	private IController controller;

	// private CamiParser parser;

	public State(IController controller) {
		this.controller = controller;
	}

	protected IController getController() {
		return this.controller;
	}

	protected CamiParser getParser() throws IOException {
		ANTLRInputStream input = new ANTLRInputStream(this.controller.getFromFrameKit());
		CamiLexer lexer = new CamiLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		return new CamiParser(tokens);
	}
}
