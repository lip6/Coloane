package fr.lip6.move.coloane.ui.commands;

import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.ui.model.ArcImplAdapter;

/**
 * @author yutao
 *
 */
public class ArcDeleteCmd extends Command {
        
        /**
         * connection
         */
        private final ArcImplAdapter connection;

        
        /**
         * Effacer un arc
         * @param aaa arc ˆ effacer
         */
        public ArcDeleteCmd(ArcImplAdapter aaa) {
                
                if (aaa == null) {
                    throw new IllegalArgumentException();
                }
                setLabel(" delete arc ");
                this.connection = aaa;
        }

        /**
         * Executer
         *
         */
        public void execute() {
                this.connection.disconnect();
        }

        /**
         * Annuler
         *
         */
        public void undo() {
                this.connection.reconnect();
        }

}