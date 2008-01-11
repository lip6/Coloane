 package com.eclipsetotale.tutorial.horloge.nebula;

import org.eclipse.swt.widgets.Composite;
import com.eclipsetotale.tutorial.horloge.Horloge;

public class HorlogeImpl implements Horloge {

   public void afficher(Composite parent) {
      new HorlogeNebula(parent);
   }
}