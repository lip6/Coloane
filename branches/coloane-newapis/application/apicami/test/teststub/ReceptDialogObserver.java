package teststub;

import java.util.Observable;

import fr.lip6.move.coloane.api.interfaces.IDialog;

import fr.lip6.move.coloane.api.interfaces.observers.IReceptDialogObserver;


public class ReceptDialogObserver implements IReceptDialogObserver {

	public void update(IDialog dialog, Integer i ) {
		if (i == 1)
		System.out.println("boite de dialog a afficher");
		if (i == 2)
			System.out.println("boite de dialog a cacher");
		if (i == 3)
			System.out.println("boite de dialog a detruire");
		
	  System.out.println("("+ dialog.getId() + " " +
			              dialog.getType() + " " +
			              dialog.getButtonType() + "" + 
			              dialog.getTitle()+ " " + 
			              dialog.getHelp() + "" + 
			              dialog.getMessage()+ "" +
			              dialog.getInputType() + " "+
			              dialog.getMultiLine() + " " + 
			              dialog.getDefault()+ ")");
	
		
	}


	
	
}