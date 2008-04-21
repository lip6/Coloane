package fr.lip6.move.coloane.api.interfaces;

/**
 * cette interface represente un attribut;
 * @author kahoo & UU
 *
 */

	public interface IAttribute {


		public String getType();

		public int getObjectId();

		public int getLine();

		public int getOperationId();

		public String getContains();
}
