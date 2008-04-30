package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.AttributeModify;

import fr.lip6.move.coloane.api.camiObject.FkInfo;
import fr.lip6.move.coloane.api.camiObject.FkVersion;
import fr.lip6.move.coloane.api.camiObject.Menu;
import fr.lip6.move.coloane.api.interfaces.IArc;
import fr.lip6.move.coloane.api.interfaces.IAttributeModify;
import fr.lip6.move.coloane.api.interfaces.IBox;
import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IDomainTable;
import fr.lip6.move.coloane.api.interfaces.IFkInfo;
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




	public IFkInfo buildFKInfo(ArrayList<String> camiFKInfo) {
		String aboutService = camiFKInfo.get(0);
		String  incremental = camiFKInfo.get(1);
		String nameService = camiFKInfo.get(2);
		String resultatCalcule = camiFKInfo.get(3);
        IFkInfo kfi = new FkInfo(aboutService,incremental,nameService,resultatCalcule);
		return kfi;
	}



	public static IFkVersion buildFkVersion(ArrayList<String> camiFkVersion) {
		String fkname = camiFkVersion.get(0);
		int fkmajor = Integer.parseInt(camiFkVersion.get(1));
		int fkminor = Integer.parseInt(camiFkVersion.get(2));

		IFkVersion fkv = new FkVersion(fkname,fkmajor,fkminor);
		return fkv;
	}


	public static IArc buildArc(ArrayList<String> camiArc) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IAttributeModify buildAttributeModify(
			ArrayList<String> CamiAttributeModify) {
		String attributeType = CamiAttributeModify.get(1);
		String newContent =CamiAttributeModify.get(2);
		int objectID=Integer.parseInt(CamiAttributeModify.get(3));
		IAttributeModify at = new AttributeModify(attributeType,newContent,objectID);
		return at;
	}

	public static IBox buildBox(ArrayList<String> camiBox) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IDialog buildDialog(ArrayList<String> camiDialog) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IDomainTable buildDomainTable(ArrayList<String> camiDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IMenu buildMenu(ArrayList<ArrayList<String>> camiMenu) {
         IMenu root = null;
        boolean isRoot = true;

		for(ArrayList<String> aq : camiMenu){
         if (isRoot){
        	 String name = aq.get(0);
        	 int questionT = Integer.parseInt(aq.get(1));
        	 int questionB = Integer.parseInt(aq.get(2));
        	 root  = new Menu(null,name,questionT,questionB,false,false,false,null,false,null);
        	 isRoot = false;
         }
           else {
			IMenu parent = null;
            String parentName = aq.get(0);
            String name = aq.get(1);

			/* le type de la question*/
			int questionType = Integer.parseInt(aq.get(2));

			/* la question precedente*/
			int questionBehavior = Integer.parseInt(aq.get(3));

			/* (1) valider (2) ne pas valider*/
			boolean valid;
			int tmpValid = Integer.parseInt(aq.get(4));
			 if (tmpValid == 1){
				  valid = true;
			 }
			 else {
			      valid = false;
			 }

			 /* (1) interdit (2) permis*/
			 boolean dialogAllowed ;
			int tmpDialogAllowed = Integer.parseInt(aq.get(5));
			 if (tmpDialogAllowed == 1){
				 dialogAllowed = false;
			 }
			 else {
			     dialogAllowed = true;
			 }

            /* (1) arret impossible (2) arret possible (suspendre) */
			 boolean stopAuthorized;
			 int tmpStopAuthorized = Integer.parseInt(aq.get(6));
			 if (tmpStopAuthorized == 1){
				 stopAuthorized = false;
			 }
			 else {
			    stopAuthorized = true;
			 }

			 String outputFormalism = aq.get(7);

			 /* (1) ENABLE (2) Disable*/
			 boolean activate;
			 int tmpActivate = Integer.parseInt(aq.get(8));
			 if (tmpActivate == 1){
				 activate = true;
			 }
			 else {
			    activate = false;
			 }

			ArrayList<IMenu> children= new ArrayList<IMenu>();

			IMenu menu = new Menu(parent,parentName,questionType,questionBehavior,
					valid,dialogAllowed,stopAuthorized,outputFormalism,activate,children);
			root.addMenu(parentName,menu);

        }

         }

		return root;
	}

	public static IModel buildModel(ArrayList<String> camiModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static INode buildNode(ArrayList<String> camiNode) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IObjectAttribute buildObjectAttribute(
			ArrayList<String> camiObjectAttribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IObjectDomainTable buildObjectDomainTable(
			ArrayList<String> camiObjectDomainTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IResult buildResult(ArrayList<String> camiResult) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IUpdateItem buildUpdateItem(ArrayList<String> camiUpdateItem) {
		// TODO Auto-generated method stub
		return null;
	}






}
