package dialogtest.actions;

import fr.lip6.move.coloane.ui.dialogs.DialogResult;
import fr.lip6.move.coloane.ui.dialogs.IDialog;

public class AffichageResultat {
	public static void affiche(DialogResult dr) {
		System.out.println("\nL'id du dialog est : " + dr.getDialogId());
		
		if (dr.hasBeenModified())
			System.out.println("Le texte a été modifié");
		else
			System.out.println("Le texte n'a pas été modifié");
		
		if (dr.getAnswerType() == IDialog.TERMINATED_OK)
			System.out.println("L'utilisateur a cliqué sur OK");
		else
			System.out.println("L'utilisateur a cliqué sur Cancel");
		
		System.out.println("Le dialogue a pour résultat les lignes suivantes :");
		
		int i = 0;
		for (String ligne : dr.getText())
			System.out.println("Résultat n°" + (++i) + " : " + ligne + "\n");
	}
}
