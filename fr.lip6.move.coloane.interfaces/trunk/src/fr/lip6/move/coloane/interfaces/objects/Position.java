package fr.lip6.move.coloane.interfaces.objects;

/**
 *
 */
public class Position implements IPosition {

	private int xPosition;
	private int yPosition;

	public Position() {
		this.xPosition = 0;
		this.yPosition = 0;
	}


	public Position(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}


	public final int getXPosition() {
		return xPosition;
	}

	public final int getYPosition() {
		return yPosition;
	}

	public final void setPosition(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}
}
