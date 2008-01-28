package fr.lip6.move.coloane.api.session.controller;

public abstract class State implements IState {

	private IController controller;

	public State(IController controller) {
		this.controller = controller;
	}

	protected IController getController() {
		return this.controller;
	}

}
