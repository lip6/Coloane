package com.eclipsetotale.tutorial.horloge;

import java.util.Date;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class PrefPage extends org.eclipse.jface.preference.PreferencePage
      implements IWorkbenchPreferencePage {

   private static final String HORLOGE = "horloge";
   private Combo comboHorloges;

   @Override
   protected Control createContents(Composite parent) {
      Composite content = new Composite(parent, SWT.NONE);
      content.setLayout(new GridLayout(2, false));

      (new Label(content, SWT.NONE)).setText("Type d'horloge : ");
      comboHorloges = new Combo(content, SWT.DROP_DOWN);
      comboHorloges.setItems(getNomsHorloges());
      comboHorloges.setText(getHorlogeCourante());

      return content;
   }

   /**
    * Liste des noms d'horloge apparaissant dans le registre des extensions
    */
   static private String[] getNomsHorloges() {
      String extensionPointId = "com.eclipsetotale.tutorial.horloge.horloges";
      IConfigurationElement[] contributions =
         Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointId);
      String[] nomsHorloges = new String[contributions.length];
      for (int i = 0; i < contributions.length; i++) {
         nomsHorloges[i] = contributions[i].getAttribute("nom");
      }
      return nomsHorloges;
   }

   /**
    * Nom de l'horloge actuellement sélectionnée dans la page de préférences.
    * Si aucun retourne le nom de la première horloge.
    */
   static public String getHorlogeCourante() {
      IPreferenceStore prefStore =
         Activator.getDefault().getPreferenceStore();
      String nomHorloge = prefStore.getString(HORLOGE);
      if (nomHorloge.equals("")) {
         nomHorloge = getNomsHorloges()[0];
         prefStore.setValue(HORLOGE, nomHorloge);
      }
      return nomHorloge;
   }

   @Override
   public boolean performOk() {
      // Stockage du nom de l'horloge dans les préférences du plugin
      IPreferenceStore prefStore =
         Activator.getDefault().getPreferenceStore();
      prefStore.setValue(HORLOGE, comboHorloges.getText());

      return true;
   }

   public void init(IWorkbench workbench) {
   }

} 