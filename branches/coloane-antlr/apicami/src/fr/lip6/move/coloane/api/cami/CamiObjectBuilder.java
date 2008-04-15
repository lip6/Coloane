package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.FKVersion;
import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.IAttributeModify;
import fr.lip6.move.coloane.api.interfaces.IBox;
import fr.lip6.move.coloane.api.interfaces.ICamiObjectBuilder;
import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IDomainTable;
import fr.lip6.move.coloane.api.interfaces.IFKInfo;
import fr.lip6.move.coloane.api.interfaces.IFKVersion;
import fr.lip6.move.coloane.api.interfaces.IMenu;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.INode;
import fr.lip6.move.coloane.api.interfaces.IObjectAttribute;
import fr.lip6.move.coloane.api.interfaces.IObjectDomainTable;
import fr.lip6.move.coloane.api.interfaces.IResult;
import fr.lip6.move.coloane.api.interfaces.IUpdateItem;



public class CamiObjectBuilder implements ICamiObjectBuilder {

	public IArc BuildArc(ArrayList<ArrayList<String>> CamiArc) {
		// TODO Auto-generated method stub
		return null;
	}

	public IAttributeModify BuildAttributeModify(
			ArrayList<ArrayList<String>> CamiAttributeModify) {
		// TODO Auto-generated method stub
		return null;
	}

	public IBox BuildBox(ArrayList<ArrayList<String>> CamiBox) {
		// TODO Auto-generated method stub
		return null;
	}

	public IDialog BuildDialog(ArrayList<ArrayList<String>> CamiDialog) {
		// TODO Auto-generated method stub
		return null;
	}

	public IDomainTable BuildDomainTable(
			ArrayList<ArrayList<String>> CamiDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public IFKInfo BuildFKInfo(ArrayList<String> CamiFKInfo) {
		
		
		return null;
	}

	public IFKVersion BuildFKVersion(ArrayList<String> CamiFKVersion) {
		String fkname = CamiFKVersion.get(1);
		int fkmajor = Integer.parseInt(CamiFKVersion.get(2));
		int fkminor = Integer.parseInt(CamiFKVersion.get(3));

		IFKVersion fkv = new FKVersion(fkname,fkmajor,fkminor);
		return fkv;
	}

	public IMenu BuildMenu(ArrayList<ArrayList<String>> CamiMenu) {
		// TODO Auto-generated method stub
		return null;
	}

	public IModel BuildModel(ArrayList<ArrayList<String>> CamiModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public INode BuildNode(ArrayList<ArrayList<String>> CamiNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public IObjectAttribute BuildObjectAttribute(
			ArrayList<ArrayList<String>> CamiObjectAttribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public IObjectDomainTable BuildObjectDomainTable(
			ArrayList<ArrayList<String>> CamiObjectDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public IResult BuildResult(ArrayList<ArrayList<String>> CamiResult) {
		// TODO Auto-generated method stub
		return null;
	}

	public IUpdateItem BuildUpdateItem(
			ArrayList<ArrayList<String>> CamiUpdateItem) {
		// TODO Auto-generated method stub
		return null;
	}



}
