package fr.lip6.move.coloane.ui.commands;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import fr.lip6.move.coloane.motor.formalism.Formalism;
import fr.lip6.move.coloane.motor.models.ArcImplAdapter;
import fr.lip6.move.coloane.motor.models.NodeImplAdapter;

/**
 * A command to reconnect a connection to a different start point or end point.
 * The command can be undone or redone.
 * <p>
 * This command is designed to be used together with a GraphicalNodeEditPolicy.
 * To use this command propertly, following steps are necessary:
 * </p>
 * <ol>
 * <li>Create a subclass of GraphicalNodeEditPolicy.</li>
 * <li>Override the <tt>getReconnectSourceCommand(...)</tt> method. Here you
 * need to obtain the Connection model element from the ReconnectRequest, create
 * a new ConnectionReconnectCommand, set the new connection <i>source</i> by
 * calling the <tt>setNewSource(Shape)</tt> method and return the command
 * instance.
 * <li>Override the <tt>getReconnectTargetCommand(...)</tt> method.</li>
 * Here again you need to obtain the Connection model element from the
 * ReconnectRequest, create a new ConnectionReconnectCommand, set the new
 * connection <i>target</i> by calling the <tt>setNewTarget(Shape)</tt>
 * method and return the command instance.</li>
 * </ol>
 * 
 * @see org.eclipse.gef.examples.petrinets.parts.ShapeEditPart#createEditPolicies()
 *      for an example of the above procedure.
 * @see org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy
 * @see #setNewSource(Shape)
 * @see #setNewTarget(Shape)
 * @author yutao
 */
public class ArcReconnectCmd extends Command {

        /**
         * arc
         */
        private ArcImplAdapter connection;

        /**
         * noeud source
         */
        private NodeImplAdapter newSource;

        /**
         * noeud cible
         */
        private NodeImplAdapter newTarget;

        /**
         * ancien noeud source
         */
        private final NodeImplAdapter oldSource;

        /**
         * ancienne noeud cible
         */
        private final NodeImplAdapter oldTarget;

        /**
         * Reconnecter
         * @param connection arc
         */
        public ArcReconnectCmd(ArcImplAdapter connection) {

                if (connection == null) {
                    throw new IllegalArgumentException();
                }

                this.connection = connection;
                this.oldSource = connection.getSource();
                this.oldTarget = connection.getTarget();
        }

        /**
         * Savoir si on peut executer
         * @return booleen
         */
        public boolean canExecute() {
                

                if (this.newSource != null) {
                        if (!checkSourceReconnection()) {
                            return false;
                        }
                } else if (this.newTarget != null) {
                        if (!checkTargetReconnection()) {
                            return false;
                        }
                } else {
                        return false;
                }

                Formalism form = this.connection.getElementBase()
                                        .getFormalism();

                if (newSource != null) {
                        if (!form.isLinkAllowed(newSource.getElementBase(),
                                                oldTarget.getElementBase())) {
                            return false;
                        }
                } else if (newTarget != null) {
                        if (!form.isLinkAllowed(oldSource.getElementBase(),
                                                newTarget.getElementBase())) {
                            return false;
                        }
                } else {
                        return false;
                }                

                
                return true;
                
        }

        /**
         * Verification noeud source
         * @return booleen
         */
        private boolean checkSourceReconnection() {
                // return false, if connection exists
                for (Iterator itr = this.newSource.getSourceArcs().iterator(); itr
                                .hasNext();) {
                        ArcImplAdapter conn = (ArcImplAdapter) itr.next();

                        if (conn.getTarget().equals(this.oldTarget)
                                        && conn.equals(this.connection)) {
                            return false;
                        }
                }

                return true;
        }

        
        /**
         * Verification noeud cible
         * @return booleen
         */
        private boolean checkTargetReconnection() {
                // return false, if the connection exists already
                for (Iterator iter = newTarget.getTargetArcs().iterator(); iter
                                .hasNext();) {
                        ArcImplAdapter conn = (ArcImplAdapter) iter.next();
                        // return false if a oldSource -> newTarget connection
                        // exists already
                        // and it is a differenct instance that the
                        // connection-field
                        if (conn.getSource().equals(oldSource)
                                        && conn.equals(connection)) {
                                return false;
                        }
                }
                return true;
        }

        /**
         * Executer
         *
         */
        public void execute() {
                if (newSource != null) {
                        this.connection.reconnect(newSource, oldTarget);
                } else if (newTarget != null) {
                        this.connection.reconnect(oldSource, newTarget);
                } else {
                        throw new IllegalStateException("Should not happen");
                }

        }

        public void undo() {
                this.connection.reconnect(oldSource, oldTarget);
        }

        /**
         * Set a new source endpoint for this connection. When execute() is
         * invoked, the source endpoint of the connection will be attached to
         * the supplied Shape instance.
         * <p>
         * Note: Calling this method, deactivates reconnection of the <i>target</i>
         * endpoint. A single instance of this command can only reconnect either
         * the source or the target endpoint.
         * </p>
         * 
         * @param connectionSource
         *                a non-null Shape instance, to be used as a new source
         *                endpoint
         * @throws IllegalArgumentException
         *                 if connectionSource is null
         */
        public void setNewSource(NodeImplAdapter connectionSource) {
                if (connectionSource == null) {
                        throw new IllegalArgumentException();
                }
                setLabel("move connection startpoint");
                newSource = connectionSource;
                newTarget = null;
        }

        /**
         * Set a new target endpoint for this connection When execute() is
         * invoked, the target endpoint of the connection will be attached to
         * the supplied Shape instance.
         * <p>
         * Note: Calling this method, deactivates reconnection of the <i>source</i>
         * endpoint. A single instance of this command can only reconnect either
         * the source or the target endpoint.
         * </p>
         * 
         * @param connectionTarget
         *                a non-null Shape instance, to be used as a new target
         *                endpoint
         * @throws IllegalArgumentException
         *                 if connectionTarget is null
         */
        public void setNewTarget(NodeImplAdapter connectionTarget) {
                if (connectionTarget == null) {
                        throw new IllegalArgumentException();
                }
                setLabel("move connection endpoint");
                newSource = null;
                newTarget = connectionTarget;
        }

}
