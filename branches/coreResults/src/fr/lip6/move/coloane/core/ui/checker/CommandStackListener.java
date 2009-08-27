package fr.lip6.move.coloane.core.ui.checker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.commands.CompoundCommand;

/**
 * This class listens to the {@link CommandStack}.
 * When a command {@link CheckableCmd} is executed, this class execute {@link CheckableCmd#checkElements()} of each command.
 * @author Florian David
 */
public class CommandStackListener implements CommandStackEventListener {

	/** {@inheritDoc} */
	public final void stackChanged(CommandStackEvent event) {
		if ((event.getDetail() & CommandStack.POST_MASK) != 0) {
			List<CheckableCmd> checkableCommandsList = new ArrayList<CheckableCmd>();

			Command eventCommand = event.getCommand();
			// A CompoundCommand is sometimes given when an undo/redo is done.
			// We have to get all the CheckableCmd from it.
			if (eventCommand instanceof CompoundCommand) {
				CompoundCommand compoundCommand = (CompoundCommand) eventCommand;
				for (Object command : compoundCommand.getCommands()) {
					if (command instanceof CheckableCmd) {
						checkableCommandsList.add((CheckableCmd) command);
					}
				}
			} else {
				if (eventCommand instanceof CheckableCmd) {
					checkableCommandsList.add((CheckableCmd) eventCommand);
				}
			}

			// Then we call the checkElements() method for all CheckableCmd
			for (CheckableCmd command : checkableCommandsList) {
				command.checkElements();
			}
		}
	}
}
