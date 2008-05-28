package fr.lip6.move.coloane.interfaces.objects;

public interface IInflexPoint {

	int getXPosition();

	int getYPosition();

	void setPosition(int x, int y);

	Object clone() throws CloneNotSupportedException;
}
