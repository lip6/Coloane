package fr.lip6.move.coloane.standalone;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class Application implements IApplication {

	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		
		try {
			int code = PlatformUI.createAndRunWorkbench(display,new ApplicationWorkbenchAdvisor());

			// Exit the application with an appropriate return code
			return code == PlatformUI.RETURN_RESTART ? EXIT_RESTART : EXIT_OK;
		} finally {
			if (display != null) { display.dispose(); }
		}
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
