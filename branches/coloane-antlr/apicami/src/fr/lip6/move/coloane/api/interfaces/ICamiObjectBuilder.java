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
		IMenu BuildMenu(ArrayList<ArrayList<String>> CamiMenu);


		/**
		 * nous construit l'objet IArc.
		 * @param les commandes venant de FrameKit.
		 * @return IArc .
		 */
		IArc BuildArc(ArrayList<ArrayList<String>> CamiArc);

		/**
		 * nous construit l'objet IBox.
		 * @param les commandes venant de FrameKit.
		 * @return IBox .
		 */
		IBox BuildBox(ArrayList<ArrayList<String>> CamiBox);

		/**
		 * nous construit l'objet IDialog.
		 * @param les commandes venant de FrameKit.
		 * @return IDialog.
		 */
		IDialog BuildDialog(ArrayList<ArrayList<String>> CamiDialog);

		/**
		 * nous construit l'objet IDomainTable.
		 * @param les commandes venant de FrameKit.
		 * @return IDomainTable.
		 */
		IDomainTable BuildDomainTable(ArrayList<ArrayList<String>> CamiDomainTable);

		/**
		 * nous construit l'objet IModel.
		 * @param les commandes venant de FrameKit.
		 * @return IModel.
		 */
		IModel BuildModel(ArrayList<ArrayList<String>> CamiModel);


		/**
		 * nous construit l'objet INode.
		 * @param les commandes venant de FrameKit.
		 * @return INode .
		 */
		INode BuildNode(ArrayList<ArrayList<String>> CamiNode);


		/**
		 * nous construit l'objet IObjectAttribute.
		 * @param les commandes venant de FrameKit.
		 * @return IObjectAttribute.
		 */
		IObjectAttribute BuildObjectAttribute(ArrayList<ArrayList<String>> CamiObjectAttribute);


		/**
		 * nous construit l'objet IObjectDomainTable.
		 * @param les commandes venant de FrameKit.
		 * @return IObjectDomainTable .
		 */
		IObjectDomainTable BuildObjectDomainTable(ArrayList<ArrayList<String>> CamiObjectDomainTable);

		/**
		 * nous construit l'objet IResult.
		 * @param les commandes venant de FrameKit.
		 * @return IResult .
		 */
		IResult BuildResult(ArrayList<ArrayList<String>> CamiResult);

		/**
		 * nous construit l'objet IUpdate.
		 * @param les commandes venant de FrameKit.
		 * @return IUpdate .
		 */
		IUpdate BuildUpdate(ArrayList<ArrayList<String>> CamiUpdate);

		/**
		 * nous construit l'objet IAttributeModify.
		 * @param les commandes venant de FrameKit.
		 * @return IAttributeModify.
		 */
		IAttributeModify BuildAttributeModify(ArrayList<ArrayList<String>> CamiAttributeModify);
}
