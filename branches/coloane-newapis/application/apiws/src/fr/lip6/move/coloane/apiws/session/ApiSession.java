package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.objects.api.SessionInfo;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.wrapper.ws.WrapperStub.DBAnswer;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Model;
import fr.lip6.move.wrapper.ws.WrapperStub.Option;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

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

			speaker.closeSession(idSession);
			sessionController.notifyEndCloseSession(this);

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
	public final boolean askForService(String rootName, String menuName, String serviceName, List<String> options, IGraph model) throws ApiException {

		if (sessionController.askForService(this)) {
			if (!automate.goToWaitingForResultState()) {
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_RESULT_STATE");
			}

			Question root = null;
			Question question = null;
			List<Option> theOptions = null;
			Model theModel = translateModel(model);

			for (int i = 0; i < menus.getRoots().length; i++) {
				if (menus.getRoots()[i].getName().equals(rootName)) {
					root = (Question) menus.getRoots()[i].getRoot();
					question = getQuestion(menus.getRoots()[i].getRoot(), serviceName);
				}
			}

			if (root == null) {
				throw new ApiException("Le menu principal: " + rootName + " n'existe pas.");
			}

			if (question == null) {
				throw new ApiException("Le service: " + serviceName + " n'existe pas.");
			}

			RService result = speaker.executService(idSession, root, question, theOptions, theModel);

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

		Collection<IArc> arcs = model.getArcs();
		Collection<INode> nodes = model.getNodes();
		Collection<IAttribute> attributs = model.getAttributes();

		for (IArc arc : arcs) {
			arc.getAttributes();
		}

		for (INode node : nodes) {
			node.getAttributes();
		}

		for (IAttribute attribut : attributs) {
			attribut.getName();
		}


		return theModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean askForService(String rootName, String menuName, String serviceName, List<String> options, IGraph model, String date) throws ApiException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void invalidModel() throws ApiException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public final void sendModel(IGraph model) throws ApiException {
		// TODO Auto-generated method stub

	}

}
