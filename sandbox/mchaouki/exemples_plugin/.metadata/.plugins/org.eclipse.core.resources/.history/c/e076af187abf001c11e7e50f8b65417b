package com.eclipsetotale.tutorial.horloge.nebula;

import java.util.Date;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class HorlogeNebula {
   private CDateTime heureCDT;
   boolean isVisible = true;

   public HorlogeNebula(final Composite parent) {
      final Display display = parent.getDisplay();
      parent.setLayout(new GridLayout());

      heureCDT = new CDateTime(parent, CDT.COMPACT | CDT.BORDER | CDT.SIMPLE | CDT.TIME_MEDIUM | CDT.CLOCK_12_HOUR);

      final Runnable updateUIRunnable = new Runnable() {
         public void run() {
            try {
               if (parent.isVisible()) {
                  heureCDT.setSelection(new Date());
                  heureCDT.setFormat(CDT.TIME_MEDIUM);
               }
            } catch (Throwable e) {
               isVisible = false;
            }
         }
      };

      Thread thread = new Thread(new Runnable() {
         public void run() {
            while (isVisible) {
               display.asyncExec(updateUIRunnable);
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
               }
            }
         }
      });
      thread.start();
   }

}