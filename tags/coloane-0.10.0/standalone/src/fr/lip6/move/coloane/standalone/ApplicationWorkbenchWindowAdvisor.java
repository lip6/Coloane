package fr.lip6.move.coloane.standalone;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer config) {
        super(config);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer config) {
        return new ApplicationActionBarAdvisor(config);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer config = getWindowConfigurer();
        config.setInitialSize(new Point(800, 600));
        
        config.setShowStatusLine(false);
        
        // Barre d'outils
        config.setShowCoolBar(true);
        
        // Titre de la fenetre
        config.setTitle("Coloane");
    }
}
