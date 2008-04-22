package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.AttributeModify;
import fr.lip6.move.coloane.api.camiObject.FKVersion;
import fr.lip6.move.coloane.api.camiObject.FkInfo;
import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.IAttributeModify;
import fr.lip6.move.coloane.api.interfaces.IBox;
import fr.lip6.move.coloane.api.interfaces.ICamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IDomainTable;
import fr.lip6.move.coloane.api.interfaces.IFKInfo;
import fr.lip6.move.coloane.api.interfaces.IFkVersion;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.INode;
import fr.lip6.move.coloane.api.interfaces.IObjectAttribute;
import fr.lip6.move.coloane.api.interfaces.IObjectDomainTable;
import fr.lip6.move.coloane.api.interfaces.IResult;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;

/**
 * cette classe construit toutes les interfaces offertes a Coloane
 * @author kahoo && UU
 *
 */

public class CamiObjectBuilder{



	public IFKInfo buildFKInfo(ArrayList<String> camiFKInfo) {
		String aboutService = camiFKInfo.get(0);
		String  incremental = camiFKInfo.get(1);
		String nameService = camiFKInfo.get(2);
		String resultatCalcule = camiFKInfo.get(3);
        IFKInfo kfi = new FkInfo(aboutService,incremental,nameService,resultatCalcule);
		return kfi;
	}

	/**
	 *
	 */
	public static IFkVersion buildFkVersion(ArrayList<String> camiFkVersion) {
		String fkname = camiFkVersion.get(0);
		int fkmajor = Integer.parseInt(camiFkVersion.get(1));
		int fkminor = Integer.parseInt(camiFkVersion.get(2));

		IFkVersion fkv = new FKVersion(fkname,fkmajor,fkminor);
		return fkv;
	}


	public IArc buildArc(ArrayList<String> camiArc) {
		// TODO Auto-generated method stub
		return null;
	}

	public IAttributeModify buildAttributeModify(
			ArrayList<String> CamiAttributeModify) {
		String attributeType = CamiAttributeModify.get(1);
		String newContent =CamiAttributeModify.get(2);
		int objectID=Integer.parseInt(CamiAttributeModify.get(3));
		IAttributeModify at = new AttributeModify(attributeType,newContent,objectID);
		return at;
	}

	public IBox buildBox(ArrayList<String> camiBox) {
		// TODO Auto-generated method stub
		return null;
	}

	public IDialog buildDialog(ArrayList<String> camiDialog) {
		// TODO Auto-generated method stub
		return null;
	}

	public IDomainTable buildDomainTable(ArrayList<String> camiDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public IMenu buildMenu(ArrayList<String> camiMenu) {
		// TODO Auto-generated method stub
		return null;
	}

	public IModel buildModel(ArrayList<String> camiModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public INode buildNode(ArrayList<String> camiNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public IObjectAttribute buildObjectAttribute(
			ArrayList<String> camiObjectAttribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public IObjectDomainTable buildObjectDomainTable(
			ArrayList<String> camiObjectDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public IResult buildResult(ArrayList<String> camiResult) {
		// TODO Auto-generated method stub
		return null;
	}

	public IUpdateItem buildUpdateItem(ArrayList<String> camiUpdateItem) {
		// TODO Auto-generated method stub
		return null;
	}






}
