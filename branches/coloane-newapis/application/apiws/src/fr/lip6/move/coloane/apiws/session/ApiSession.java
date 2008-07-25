package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.objects.api.SessionInfo;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.wrapper.ws.WrapperStub.BArc;
import fr.lip6.move.wrapper.ws.WrapperStub.BNode;
import fr.lip6.move.wrapper.ws.WrapperStub.DBAnswer;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Model;
import fr.lip6.move.wrapper.ws.WrapperStub.Option;
import fr.lip6.move.wrapper.ws.WrapperStub.Position;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.draw2d.AbsoluteBendpoint;

/**
 * Cette classe représent une session
 */
public class ApiSession implements IApiSession {

	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apiws");

	private int sessionDate;

	private String sessionFormalism;

	private String sessionName;

	private String interlocutor;

	private int mode;

	private ISessionController sessionController;

	private ISpeaker speaker;

	private ISessionStateMachine automate;

	private String idSession;

	private boolean invalidateTheModel;

	private MMenu menus;

	/**
	 * Constructeur
	 * @param sessionController le gestionnaire de sessions à utiliser
	 * @param speaker le speaker à utiliser par la session
	 */
	public ApiSession(ISessionController sessionController, ISpeaker speaker) {
		this.sessionDate = -1;
		this.sessionFormalism = null;
		this.sessionName = null;
		this.interlocutor = null;
		this.mode = -1;

		this.sessionController = sessionController;
		this.speaker = speaker;
		this.automate = SessionFactory.getNewSessionStateMachine();

		this.invalidateTheModel = false;

		this.menus = null;

		this.idSession = null;

		LOGGER.finer("Création d'une IApiSession");
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getInterlocutor() {
		return interlocutor;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getMode() {
		return mode;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getSessionDate() {
		return sessionDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getSessionFormalism() {
		return sessionFormalism;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getSessionName() {
		return sessionName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getIdSession() {
		return idSession;
	}

	/**
	 * Recupére l'automate de la session
	 * @return  l'automate de la session
	 */
	public final ISessionStateMachine getSessionStateMachine() {
		return automate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final ISessionInfo openSession(int sessionDate, String sessionFormalism, String sessionName, String interlocutor, int mode) throws ApiException {
		this.sessionDate = sessionDate;
		this.sessionFormalism = sessionFormalism;
		this.sessionName = sessionName;
		this.interlocutor = interlocutor;
		this.mode = mode;

		Session sessionOpened = null;

		if (sessionController.openSession(this)) {
			if (!automate.goToWaitingForUpdatesAndMenusState()) {
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE");
			}

			sessionOpened = speaker.openSession(sessionFormalism);
			this.idSession = sessionOpened.getSessionId();
			this.menus = sessionOpened.getMenu();

			sessionController.notifyEndOpenSession(this, sessionOpened.getMenu());

		}

		LOGGER.fine("Ouverture d'une session");

		return new SessionInfo(sessionOpened);
	}

	/**
	 * {@inheritDoc}
	 */
	public final ISessionInfo openSession(int sessionDate, String sessionFormalism, String sessionName) throws ApiException {
		// TODO Auto-generated method stub
		return openSession(sessionDate, sessionFormalism, sessionName, "FrameKit Environment", 1);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean suspendSession() throws ApiException {
		if (sessionController.suspendSession(this)) {
			sessionController.notifyEndSuspendSession(this);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean resumeSession() throws ApiException {
		if (sessionController.isActivateSession(this)) {
			return true;
		}

		if (sessionController.resumeSession(this)) {
			speaker.changeSession(this.getIdSession());
			sessionController.notifyEndResumeSession(this);
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean closeSession() throws ApiException {
		if (sessionController.closeSession(this)) {
			if (!automate.goToWaitingForCloseSessionState()) {
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_CLOSE_SESSION_STATE");
			}

			Session sessionToResme = speaker.closeSession(idSession);
			sessionController.notifyEndCloseSession(this, sessionToResme.getSessionId());

		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean sendDialogAnswer(IDialogAnswer dialogAnswer) throws ApiException {

		DialogBox answer = new DialogBox();
		answer.setAnswer(new DBAnswer());

		answer.getAnswer().setId(dialogAnswer.getIdDialog());
		answer.getAnswer().setButtonAnswer(dialogAnswer.getButtonType());
		answer.getAnswer().setModified(dialogAnswer.isModified());
		answer.getAnswer().setValue(dialogAnswer.getValue());

		List<Integer> objects = dialogAnswer.getObjects();
		if (objects != null) {
			int [] objectsArray = new int[objects.size()];
			int cpt = 0;
			for (Integer line : objects) {
				objectsArray[cpt++] = line.intValue();
			}
			answer.getAnswer().setObjects(objectsArray);
		}

		speaker.answerToDialogBox(answer);

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean askForService(String rootName, String serviceName, List<String> options, IGraph model) throws ApiException {

		// Test si on peut exécuter un service
		if (sessionController.askForService(this)) {
			// Met à jours l'automate de la session
			if (!automate.goToWaitingForResultState()) {
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_RESULT_STATE");
			}

			Question root = null;
			Question question = null;
			List<Option> theOptions = null;
			Model theModel = translateModel(model);

			// Détérmine le menu principal et le service demander à envoyer au wrapper
			for (int i = 0; i < menus.getRoots().length; i++) {
				if (menus.getRoots()[i].getName().equals(rootName)) {
					root = (Question) menus.getRoots()[i].getRoot();
					question = getQuestion(menus.getRoots()[i].getRoot(), serviceName);
				}
			}

			// Teste si le menu principal du service demander existe
			if (root == null) {
				throw new ApiException("Le menu principal: " + rootName + " n'existe pas.");
			}

			// Teste si le service demander existe
			if (question == null) {
				throw new ApiException("Le service: " + serviceName + " n'existe pas.");
			}

			// Invalidation si nécessaire du model
			theModel.setInvalidate(invalidateTheModel);

			// Exécute le service demander
			RService result = speaker.executService(idSession, root, question, theOptions, theModel);

			// Réinitialise le boolean sur l'invalidation du model à false
			this.invalidateTheModel = false;

			// Notifie la fin de l'exécution du service demander
			sessionController.notifyEndResult(this, result);

		}

		return true;
	}

	/**
	 * Récupére la question pour le service demander.
	 * @param rootMenu le menu principale où se trouve le service demander.
	 * @param serviceName le nom du service demander.
	 * @return la question pour le service demander.
	 */
	private Question getQuestion(SubMenu rootMenu, String serviceName) {

		if (rootMenu.getServices() != null) {
			for (int i = 0; i < rootMenu.getServices().length; i++) {
				if (rootMenu.getServices()[i].getName().equals(serviceName)) {
					return rootMenu.getServices()[i];
				}
			}
		}

		if (rootMenu.getServicesWithObjects() != null) {
			for (int i = 0; i < rootMenu.getServicesWithObjects().length; i++) {
				if (rootMenu.getServicesWithObjects()[i].getName().equals(serviceName)) {
					return rootMenu.getServicesWithObjects()[i];
				}
			}
		}

		if (rootMenu.getServicesWithOneObject() != null) {
			for (int i = 0; i < rootMenu.getServicesWithOneObject().length; i++) {
				if (rootMenu.getServicesWithOneObject()[i].getName().equals(serviceName)) {
					return rootMenu.getServicesWithOneObject()[i];
				}
			}
		}

		if (rootMenu.getServiceWithOneText() != null) {
			for (int i = 0; i < rootMenu.getServiceWithOneText().length; i++) {
				if (rootMenu.getServiceWithOneText()[i].getName().equals(serviceName)) {
					return rootMenu.getServiceWithOneText()[i];
				}
			}
		}

		if (rootMenu.getServiceWithTexts() != null) {
			for (int i = 0; i < rootMenu.getServiceWithTexts().length; i++) {
				if (rootMenu.getServiceWithTexts()[i].getName().equals(serviceName)) {
					return rootMenu.getServiceWithTexts()[i];
				}
			}
		}

		if (rootMenu.getSubMenus() != null) {
			for (int i = 0; i < rootMenu.getSubMenus().length; i++) {
				Question q = getQuestion(rootMenu.getSubMenus()[i], serviceName);
				if (q != null) {
					return q;
				}
			}
		}

		return null;
	}

	/**
	 * Traduit un model qui vient du core de Colane en un model compréhensible par le wrapper.
	 * @param model le modelreçu de la part du core de coloane.
	 * @return Un model compréhensible par le wrapper.
	 */
	private Model translateModel(IGraph model) {

		Model theModel = new Model();

		Collection<INode> nodes = model.getNodes();
		Collection<IArc> arcs = model.getArcs();

		for (INode node : nodes) {

			// Création d'un noeud pour le wrapper
			BNode noeud = new BNode();

			// Création de la position du noeud pour le wrapper
			Position pos = new Position();
			pos.setXx(node.getGraphicInfo().getLocation().x);
			pos.setYy(node.getGraphicInfo().getLocation().y);

			// Initialisation de l'identifiant du noeud pour le wrapper
			noeud.setId(node.getId());
			// Initialisation de la position du noeud pour le wrapper
			noeud.setPosition(pos);
			// Initialisation du type du noeud pour le wrapper
			noeud.setType(node.getNodeFormalism().getName());

			// Ajout du noeud dans le model pour le wrapper
			theModel.addNodes(noeud);
		}

		for (IArc arc : arcs) {

			// Création d'un arc pour le wrapper
			BArc theArc = new BArc();

			// Création de la liste des points du noeuds.
			Position[] listPts = new Position[arc.getInflexPoints().size()];
			int i = 0;
			for (AbsoluteBendpoint pts : arc.getInflexPoints()) {
				Position pos = new Position();
				pos.setXx(pts.x);
				pos.setYy(pts.y);

				listPts[i++] = pos;
			}

			// Initialisation de l'identifiant de l'arc pour le wrapper
			theArc.setId(arc.getId());
			// Initialisation de la source de l'arc pour le wrapper
			theArc.setSource(arc.getSource().getId());
			// Initialisation de la destination de l'arc pour le wrapper
			theArc.setDestination(arc.getTarget().getId());
			// Initialisation de la liste des points de l'arc pour le wrapper
			theArc.setPoints(listPts);

			// Ajout d'un arc dans le model pour le wrapper
			theModel.addArcs(theArc);
		}

		return theModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void invalidModel() throws ApiException {
		// TODO Auto-generated method stub
		this.invalidateTheModel = true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void sendModel(IGraph model) throws ApiException {
		// TODO Auto-generated method stub

	}

}
