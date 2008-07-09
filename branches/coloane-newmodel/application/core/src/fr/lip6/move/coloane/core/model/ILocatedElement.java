package fr.lip6.move.coloane.core.model;

import fr.lip6.move.coloane.core.ui.rulers.EditorGuide;
import fr.lip6.move.coloane.interfaces.model.core.ICoreLocationInfo;

/**
 * Interface définissant des objets positionnables sur l'éditeur.</br>
 * Ces objets doivent obligatoirement proposer :
 * <ul>
 * 	<li>Une manière de récupérer les informations de positionnement</li>
 * 	<li>La gestion des guides verticaux et horizontaux</li>
 * </ul>
 */
public interface ILocatedElement {

	/**
	 * @return Les informations concernant le positionnement de l'objet
	 * @see ICoreLocationInfo
	 */
	ICoreLocationInfo getLocationInfo();

	/**
	 * @return le guide horizontal associé à l'objet
	 */
	EditorGuide getHorizontalGuide();

	/**
	 * @return le guide vertical associé à l'objet
	 */
	EditorGuide getVerticalGuide();

	/**
	 * Positionne un nouveau guide horizontal sur l'objet
	 * @param guide
	 */
	void setHorizontalGuide(EditorGuide guide);

	/**
	 * Positionne un nouveau guide vertical sur l'objet
	 * @param guide
	 */
	void setVerticalGuide(EditorGuide guide);
}
