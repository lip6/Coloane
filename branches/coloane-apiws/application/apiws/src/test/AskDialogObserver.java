package test;

import fr.lip6.move.coloane.apiws.exceptions.WrapperException;
import fr.lip6.move.coloane.apiws.interfaces.evenements.IAskDialog;
import fr.lip6.move.coloane.apiws.interfaces.observers.IAskDialogObserver;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.IListener;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

public class AskDialogObserver implements IAskDialogObserver {
	
	public String printDB(DialogBox box){
		String res="";
		res+="DIALOG BOX\n";
		res+="\t- ID \t\t= "+box.getId()+"\n";
		switch(box.getType()){
		case 1:
			res+="\t- TYPE \t\t= NORMAL"+"\n";
			break;
		case 2:
			res+="\t- TYPE \t\t= WARNING"+"\n";
			break;
		case 3:
			res+="\t- TYPE \t\t= ERROR"+"\n";
			break;
		case 4:
			res+="\t- TYPE \t\t= INTERACTIF"+"\n";
			break;
		}
		switch(box.getTypeButton()){
		case 1:
			res+="\t- BUTTON \t= N/A"+"\n";
			break;
		case 2:
			res+="\t- BUTTON \t= OK"+"\n";
			break;
		case 3:
			res+="\t- BUTTON \t= OK or CANCEL"+"\n";
			break;
		}
		res+="\t- Title \t= "+box.getTitle()+"\n";
		res+="\t- Message \t= "+box.getMessage()+"\n";
		res+="\t- Title \t= "+box.getHelp()+"\n";
		switch(box.getInputType()){
		case 1:
			res+="\t- Input \t= Authorized"+"\n";
			break;
		case 2:
			res+="\t- Input \t= Not Authorized"+"\n";
			break;
		case 5:
			res+="\t- Input \t= Authorized with annulation"+"\n";
			break;
		}
		switch(box.getLineType()){
		case 1:
			res+="\t- Select \t= Only one line"+"\n";
			break;
		case 2:
			res+="\t- Select \t= Combo Box with one choose"+"\n";
			break;
		case 5:
			res+="\t- Select \t= Combo Box with * choose"+"\n";
			break;
		}
		return res;
	}
	public void printEltFromSubMenu(SubMenu sub,String dec){
		/* Print the simple service */
		if(sub.getServices()!=null){
			for(int ii=0;ii<sub.getServices().length;ii++){
				Question q = sub.getServices()[ii];
				if(q.getVisibility())
					System.out.println(dec+"[+] [SS] "+q.getName());
				else
					System.out.println(dec+"[-] [SS] "+q.getName());
			}
		}
		/* Print the option */
		if(sub.getOption()!=null){
			for(int ii=0;ii<sub.getOption().length;ii++){
				Question q = sub.getOption()[ii];
				if(q.getVisibility())
					System.out.println(dec+"[+] [OP] "+q.getName());
				else
					System.out.println(dec+"[-] [OP] "+q.getName());
			}
		}
		/* Print the Service with multiple objects */
		if(sub.getServicesWithObjects()!=null){
			for(int ii=0;ii<sub.getServicesWithObjects().length;ii++){
				Question q = sub.getServicesWithObjects()[ii];
				if(q.getVisibility())
					System.out.println(dec+"[+] [SOs] "+q.getName());
				else
					System.out.println(dec+"[-] [SOs] "+q.getName());
			}
		}
		/* Print the Service with one object */
		if(sub.getServicesWithOneObject()!=null){
			for(int ii=0;ii<sub.getServicesWithOneObject().length;ii++){
				Question q = sub.getServicesWithOneObject()[ii];
				if(q.getVisibility())
					System.out.println(dec+"[+] [SO] "+q.getName());
				else
					System.out.println(dec+"[-] [SO] "+q.getName());
			}
		}
		/* Print the Service with multiple texts */
		if(sub.getServiceWithTexts()!=null){
			for(int ii=0;ii<sub.getServiceWithTexts().length;ii++){
				Question q = sub.getServiceWithTexts()[ii];
				if(q.getVisibility())
					System.out.println(dec+"[+] [STs] "+q.getName());
				else
					System.out.println(dec+"[-] [STs] "+q.getName());
			}
		}
		/* Print the Service with one text */
		if(sub.getServiceWithOneText() !=null){
			for(int ii=0;ii<sub.getServiceWithOneText().length;ii++){
				Question q = sub.getServiceWithOneText()[ii];
				if(q.getVisibility())
					System.out.println(dec+"[+] [ST] "+q.getName());
				else
					System.out.println(dec+"[-] [ST] "+q.getName());
			}
		}
		/* By Recursivity only if submenu exists */
		if(sub.getSubMenus() !=null){
			for(int ii=0;ii<sub.getSubMenus().length;ii++){
				SubMenu q = sub.getSubMenus()[ii];
				if(q.getVisibility())
					System.out.println(dec+"[+] "+q.getName());
				else
					System.out.println(dec+"[-] "+q.getName());
				printEltFromSubMenu(q,dec+"  ");
			}
		}
	}

	
	public void update(IAskDialog d,IListener asynchronousSpeaker) {
		try {
			for (DialogBox dialog : d.getDialogs()){
				System.out.println("-------------------DIALOG-------------------");
				printDB(dialog);
				asynchronousSpeaker.answerToDialogBox(dialog);
				System.out.println("--------------------------------------------");
			}
		} catch (WrapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
