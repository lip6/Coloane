package fr.lip6.move.coloane.core.model.interfaces;

import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.model.ILocationInfo;

/**
 * Interface définissant des objets positionnables sur l'éditeur.<br>
 * Ces objets doivent obligatoirement proposer :
 * <ul>
 * 	<li>Une manière de récupérer les informations de positionnement</li>
 * 	<li>La gestion des guides verticaux et horizontaux</li>
 * </ul>
 * @see {@link NodeModel}, {@link AttributeModel}
 */
public interface ILocatedElement {

	/**
	 * Retourne les informations sur la position de l'objet
	 * @return Les informations de positionnement
	 */
	ILocationInfo getLocationInfo();

	/**
	 * @return le guide horizontal associé à l'objet
	 */
	EditorGuide getHorizontalGuide();

	/**
	 * @return le guide vertical associÃ© Ã  l'objet
	 */
	EditorGuide getVerticalGuide();

	/**
	 * Positionne un nouveau guide horizontal sur l'objet
	 * @param guide Le guide horizontal
	 */
	void setHorizontalGuide(EditorGuide guide);

	/**
	 * Positionne un nouveau guide vertical sur l'objet
	 * @param guide Le guide vertical
	 */
	void setVerticalGuide(EditorGuide guide);
}
