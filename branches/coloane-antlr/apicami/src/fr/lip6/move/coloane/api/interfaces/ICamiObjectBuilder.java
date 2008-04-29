package fr.lip6.move.coloane.api.interfaces;
import java.util.ArrayList;

/**
 * cette interface nous construit des objets.
 * @author KAHOO & UU
 *
 */
public interface ICamiObjectBuilder {


	/**
	 * nous construit l'objet IMenu.
	 * @param les commandes venant de FrameKit.
	 * @return IMenu .
	 */
		IMenu buildMenu(ArrayList<ArrayList<String>> CamiMenu);


		/**
		 * nous construit l'objet IArc.
		 * @param les commandes venant de FrameKit.
		 * @return IArc .
		 */
		IArc buildArc(ArrayList<String> CamiArc);

		/**
		 * nous construit l'objet IBox.
		 * @param les commandes venant de FrameKit.
		 * @return IBox .
		 */
		IBox buildBox(ArrayList<String> CamiBox);

		/**
		 * nous construit l'objet IDialog.
		 * @param les commandes venant de FrameKit.
		 * @return IDialog.
		 */
		IDialog buildDialog(ArrayList<String> CamiDialog);

		/**
		 * nous construit l'objet IDomainTable.
		 * @param les commandes venant de FrameKit.
		 * @return IDomainTable.
		 */
		IDomainTable buildDomainTable(ArrayList<String> CamiDomainTable);

		/**
		 * nous construit l'objet IModel.
		 * @param les commandes venant de FrameKit.
		 * @return IModel.
		 */
		IModel buildModel(ArrayList<String> CamiModel);


		/**
		 * nous construit l'objet INode.
		 * @param les commandes venant de FrameKit.
		 * @return INode .
		 */
		INode buildNode(ArrayList<String> CamiNode);


		/**
		 * nous construit l'objet IObjectAttribute.
		 * @param les commandes venant de FrameKit.
		 * @return IObjectAttribute.
		 */
		IObjectAttribute buildObjectAttribute(ArrayList<String> CamiObjectAttribute);


		/**
		 * nous construit l'objet IObjectDomainTable.
		 * @param les commandes venant de FrameKit.
		 * @return IObjectDomainTable .
		 */
		IObjectDomainTable buildObjectDomainTable(ArrayList<String> CamiObjectDomainTable);

		/**
		 * nous construit l'objet IResult.
		 * @param les commandes venant de FrameKit.
		 * @return IResult .
		 */
		IResult buildResult(ArrayList<String> CamiResult);

		/**
		 * nous construit l'objet IUpdate.
		 * @param les commandes venant de FrameKit.
		 * @return IUpdate .
		 */
		IUpdateItem buildUpdateItem(ArrayList<String> CamiUpdateItem);

		/**
		 * nous construit l'objet IAttributeModify.
		 * @param les commandes venant de FrameKit.
		 * @return IAttributeModify.
		 */
		IAttributeModify buildAttributeModify(ArrayList<String> CamiAttributeModify);

		/**
		 * nous construit l'objet IFKVersion.
		 * @param les commandes venant de FrameKit.
		 * @return IFKVersion.
		 */
		public IFkVersion buildFkVersion(ArrayList<String> camiFKVersion);


		/**
		 * nous construit l'objet IFKInfo.
		 * @param les commandes venant de FrameKit.
		 * @return IFKInfo.
		 */
		IFKInfo buildFKInfo(ArrayList<String> CamiFKInfo);
}
