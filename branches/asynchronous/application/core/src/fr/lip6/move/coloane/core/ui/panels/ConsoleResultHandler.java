package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.session.ISession;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * Display automatically a textual representation of the results in the console view.
 *
 * @author Clément Démoulins
 */
public class ConsoleResultHandler {

	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Observer to be notified of new results.
	 */
	private class ResultObserver implements Observer {
		private final ISession session;

		/**
		 * Create a result Observer for the specified session
		 * @param session a Coloane session
		 */
		public ResultObserver(ISession session) {
			this.session = session;
		}

		@Override
		public void update(Observable o, Object arg) {
			if (!(arg instanceof ResultTreeImpl)) {
				return;
			}
			final ResultTreeImpl newResult = (ResultTreeImpl) arg;
			Display.getDefault().syncExec(new Runnable() {
				private String listToString(List<Object> list) {
					StringBuilder sb = new StringBuilder();
					for (Object element : list) {
						if (sb.length() == 0) {
							sb.append(element.toString());
						} else {
							sb.append("\t"); //$NON-NLS-1$
							sb.append(element.toString());
						}
					}
					return sb.toString();
				}
				private String treeToString(IResultTree tree) {
					StringBuilder sb = new StringBuilder();
					sb.append(listToString(tree.getElement()));
					for (IResultTree child : tree.getChildren()) {
						sb.append(treeToString(child));
					}
					return sb.toString();
				}

				@Override
				public void run() {
					LOGGER.finest("New result from service " + newResult.getServiceName()); //$NON-NLS-1$
					MessageConsole console = session.getConsole();
					MessageConsoleStream messageStream = console.newMessageStream();
					messageStream.println(Messages.ConsoleResultHandler_0 + newResult.getServiceName());
					for (IResultTree child : newResult.getChildren()) {
						messageStream.println(treeToString(child));
					}
					messageStream.println();
					console.activate();
				}
			});
		}
	}
	
	/**
	 * Initialize the handler for a specified session
	 * @param session session monitored
	 */
	public final void init(ISession session) {
		session.getResultManager().addObserver(new ResultObserver(session));
	}

}
