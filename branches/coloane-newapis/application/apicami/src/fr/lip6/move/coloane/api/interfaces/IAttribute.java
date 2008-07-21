package fr.lip6.move.coloane.api.interfaces;

/**
 * cette interface represente un attribut;
 * @author kahoo & UU
 *
 */

	public interface IAttribute {

		public String getName() ;



		public void setPosition(int x, int y) ;

		public int getXPosition() ;



		public int getYPosition() ;



		public int getRefId() ;



		public void setRefId(int ref) ;



		public void setValue(String attributeValue) ;



		public String getValue() ;

}
