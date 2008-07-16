package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IInflexPoint;

public class InflexPoint implements IInflexPoint {

	private int xPosition;
	private int yPosition;

	public InflexPoint() {
		this.xPosition = 0;
		this.yPosition = 0;
	}


	public InflexPoint(int x, int y) {
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
