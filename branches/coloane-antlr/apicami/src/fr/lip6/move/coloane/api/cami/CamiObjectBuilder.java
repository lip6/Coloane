package fr.lip6.move.coloane.api.cami;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.camiObject.AttributeModify;

import fr.lip6.move.coloane.api.camiObject.FkInfo;
import fr.lip6.move.coloane.api.camiObject.FkVersion;
import fr.lip6.move.coloane.api.camiObject.Menu;
import fr.lip6.move.coloane.api.camiObject.UpdateItem;
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




	public static IFkInfo buildFkInfo(ArrayList<String> camiFKInfo) {
		String aboutService = camiFKInfo.get(0);
		String  incremental = camiFKInfo.get(1);
		String nameService = camiFKInfo.get(2);
		String resultatCalcule = camiFKInfo.get(3);
        IFkInfo kfi = new FkInfo(aboutService,incremental,nameService,resultatCalcule);
		return kfi;
	}



	public static IFkVersion buildFkVersion(ArrayList<String> camiFkVersion) {
		String fkname = camiFkVersion.get(0);
		int fkmajor;
		String tmpfkmajor = camiFkVersion.get(1);
		if ( tmpfkmajor != null ) {
		fkmajor = Integer.parseInt(tmpfkmajor);
		} else {
	    fkmajor = -1;
		}

		int fkminor;
		String tmpfkminor = camiFkVersion.get(2);
		if ( tmpfkminor != null ) {
		fkminor = Integer.parseInt(tmpfkminor);
		} else {
		fkminor = -1;
		}

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
        	 int questionT;
        	 String tmpquestionT = aq.get(1);
        	 if ( tmpquestionT != null){
        	 questionT = Integer.parseInt(tmpquestionT);
        	 }else {
        		questionT = -1;
        	 }

        	 int questionB;
        	 String tmpquestionB = aq.get(2);
        	 if ( tmpquestionB != null){
        	 questionB = Integer.parseInt(tmpquestionB);
        	 }else {
        		questionB = -1;
        	 }

        	 root  = new Menu(null,name,questionT,questionB,false,false,false,null,false,null);
        	 isRoot = false;
         }
           else {
			IMenu parent = null;
            String parentName = aq.get(0);
            String name = aq.get(1);

			/* le type de la question*/
            int questionType;
       	 String tmpquestionType = aq.get(2);
       	 if ( tmpquestionType != null){
       	 questionType = Integer.parseInt(tmpquestionType);
       	 }else {
       		questionType = -1;
       	 }


			/* la question precedente*/
       	   int questionBehavior;
   	       String tmpquestionBehavior = aq.get(3);
   	       if ( tmpquestionBehavior != null){
   	       questionBehavior = Integer.parseInt(tmpquestionBehavior);
   	       }else {
   		   questionBehavior = -1;
   	       }


			/* (1) valider (2) ne pas valider*/
   	       String tmpValid2 = aq.get(4);
   	       boolean valid;
   	       if (tmpValid2 != null ) {

			int tmpValid = Integer.parseInt(tmpValid2);
			 if (tmpValid == 1){
				  valid = true;
			 }
			 else {
			      valid = false;
			 }

   	       }else {
   	    	   valid = false;
   	       }


			 /* (1) interdit (2) permis*/
   	       String tmpDialogAllowed2 = aq.get(5);
   	        boolean dialogAllowed ;
			 if (tmpDialogAllowed2 != null){
			int tmpDialogAllowed = Integer.parseInt(tmpDialogAllowed2);
			 if (tmpDialogAllowed == 1){
				 dialogAllowed = false;
			 }
			 else {
			     dialogAllowed = true;
			 }
			 }else {
				 dialogAllowed = true;
			 }

            /* (1) arret impossible (2) arret possible (suspendre) */
			 String tmpStopAuthorized2 = aq.get(6);
			 boolean stopAuthorized;
			 if (tmpStopAuthorized2 != null){
			 int tmpStopAuthorized = Integer.parseInt(tmpStopAuthorized2);
			 if (tmpStopAuthorized == 1){
				 stopAuthorized = false;
			 }
			 else {
			    stopAuthorized = true;
			 }
			 }
			 else {
				 stopAuthorized = true;
			 }

			 String outputFormalism = aq.get(7);

			 /* (1) ENABLE (2) Disable*/
			 String tmpActivate2 = aq.get(8);
			 boolean activate;
			 if(tmpActivate2 != null){
			 int tmpActivate = Integer.parseInt(tmpActivate2);

			 if (tmpActivate == 1){
				 activate = true;
			 }
			 else {
			    activate = false;
			 }
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

	/**
	 * nous genere les interfaces des TQ
	 * @param camiUpdateItem
	 * @return un tableau de TQ 7 & 8
	 */
	public static ArrayList<IUpdateItem> buildUpdateItem(ArrayList<ArrayList<String>> camiUpdateItem) {
        ArrayList<IUpdateItem> tab = new  ArrayList<IUpdateItem> ();
        for(ArrayList<String> tq : camiUpdateItem){
        String rootName = tq.get(0);
        String serviceName = tq.get(1);
        String tmpState2 = tq.get(2);
        boolean state;
        if (tmpState2 != null){
        int tmpState = Integer.parseInt(tmpState2);

        if (tmpState == 7){
        	state = true;
        }
        else {
        	state = false;
        }
        } else {
        	state = true;
        }
        IUpdateItem update = new UpdateItem(rootName,serviceName,state);
        tab.add(update);
        }
		return tab;
	}






}
