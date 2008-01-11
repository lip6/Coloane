package com.eclipsetotale.tutorial.horloge;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class HorlogeView extends ViewPart {

   public HorlogeView() {
   }

   @Override
   public void createPartControl(Composite parent) {

      // Nom d'horloge sélectionné dans la page de préférences
      String nomHorlogeCourante = PrefPage.getHorlogeCourante();

      // Récupération de l'extension associée au nom d'horloge
      String extensionPointId = "com.eclipsetotale.tutorial.horloge.horloges";
      IConfigurationElement[] contributions =
         Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointId);
      IConfigurationElement extensionHorloge = null;
      for (int i = 0; i < contributions.length; i++) {
         if(contributions[i].getAttribute("nom").equals(nomHorlogeCourante)) {
            extensionHorloge = contributions[i];
            break;
         }
      }

      // Si une extension est disponible, la classe 'Horloge' correspondante
      // est instanciée via la méthode 'createExecutableExtension'
      if(extensionHorloge != null) {
         try {
            Horloge horloge =
               (Horloge)extensionHorloge.createExecutableExtension("classe");
            horloge.afficher(parent);
         } catch (CoreException e) {
            String msg = "Impossible d'afficher l'horloge";
            parent.setLayout(new RowLayout());
            (new Label(parent, SWT.NONE)).setText(msg);
         }
      }
   }

   @Override
   public void setFocus() {
   }

}