package fr.lip6.move.coloane.interfaces.model.core;

import fr.lip6.move.coloane.interfaces.model.IAttributeGraphicInfo;

public interface ICoreAttributeGraphicInfo extends IAttributeGraphicInfo, ICoreLocationInfo {

	/** Niveau 1 */
	int L1 = 1;
	int SIZE_L1 = 11;

	/** Niveau 2 **/
	int L2 = 2;
	int SIZE_L2 = 10;

	/** Affichage normal **/
	int NOR = 3;
	int SIZE_NOR = 9;

	/** Par defaut **/
	int SIZE_DEF = 9;

	/** Police **/
	String FONT = "arial"; //$NON-NLS-1$
}
