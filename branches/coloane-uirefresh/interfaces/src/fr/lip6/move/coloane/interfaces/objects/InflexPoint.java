package fr.lip6.move.coloane.interfaces.objects;

/**
 *
 */
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

	@Override
	public final Object clone() throws CloneNotSupportedException {
		return new InflexPoint(xPosition, yPosition);
	}
}
