package fr.lip6.move.coloane.api.interfaces;

/**
 * cette interface represente un attribut;
 * @author kahoo & UU
 *
 */

	public interface IAttribute {

/**
 * le type de notre attribut.
 * @return le type
 */
		public String getType();

		/**
		 * l'identifiant de l'objet auquel il appartient
		 * @return son numero
		 */
		public int getObjectId();

		/*/**
		 * le numero de ligne ou se trouve
		 * @return le numero de ligne

		public int getLine();*/



      /**
      * le contenu de l'attribut.
      * @return le contenu en string.
      */
		public String getContains();
}
