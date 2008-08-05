package fr.lip6.move.coloane.apiws.session;

import fr.lip6.move.coloane.apiws.interfaces.session.ISessionController;
import fr.lip6.move.coloane.apiws.interfaces.session.ISessionStateMachine;
import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.apiws.objects.api.SessionInfo;
import fr.lip6.move.coloane.apiws.utils.CamiModelTranslator;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.objects.ISessionInfo;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.wrapper.ws.WrapperStub.Attribute;
import fr.lip6.move.wrapper.ws.WrapperStub.AttributeValue;
import fr.lip6.move.wrapper.ws.WrapperStub.BArc;
import fr.lip6.move.wrapper.ws.WrapperStub.BNode;
import fr.lip6.move.wrapper.ws.WrapperStub.DBAnswer;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Model;
import fr.lip6.move.wrapper.ws.WrapperStub.Option;
import fr.lip6.move.wrapper.ws.WrapperStub.Position;
import fr.lip6.move.wrapper.ws.WrapperStub.QO;
import fr.lip6.move.wrapper.ws.WrapperStub.QT;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;
import fr.lip6.move.wrapper.ws.WrapperStub.Service;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithObjects;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithOneObject;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithOneText;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithTexts;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
	public final int getDate() {
		return sessionDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getFormalism() {
		return sessionFormalism;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return sessionName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getId() {
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
	public final ISessionInfo open(int sessionDate, String sessionFormalism, String sessionName) throws ApiException {
		this.sessionDate = sessionDate;
		this.sessionFormalism = sessionFormalism;
		this.sessionName = sessionName;

		Session sessionOpened = null;

		if (!sessionController.getConnection().isConnectionOpened()) {
			LOGGER.warning("Impossible d'ouvrir la session: " + this.sessionName + " [connexion fermée]");
			throw new ApiException("Impossible d'ouvrir la session '" + this.sessionName + "' car la connexion est fermée");
		}

		// Test si on peut ouvrir la session
		if (sessionController.openSession(this)) {
			// Met à jours l'automate de la session
			if (!automate.goToWaitingForUpdatesAndMenusState()) {
				LOGGER.warning("La session '" + this.sessionName + "' ne peut pas être en état d'attendre des menus");
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_MENUS_AND_UPDATES_STATE");
			}

			// Demande l'ouverture de la session
			LOGGER.finer("Demande l'ouverture de la session: " + this.sessionName);
			sessionOpened = speaker.openSession(sessionFormalism);

			// Récupere l'identifiant de la session pour pouvoir interagir dessus via le wrapper
			this.idSession = sessionOpened.getSessionId();

			// Récupére le menu de la session
			this.menus = sessionOpened.getMenu();

			// Notifie la fin de l'ouverture de la session
			sessionController.notifyEndOpenSession(this, sessionOpened.getMenu());

		}

		LOGGER.fine("Ouverture de la session: " + this.sessionName);
		return new SessionInfo(sessionOpened);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void suspend() throws ApiException {

		if (!sessionController.getConnection().isConnectionOpened()) {
			LOGGER.warning("Impossible de suspendre la session: " + this.sessionName + " [connexion fermée]");
			throw new ApiException("Impossible de suspendre la session '" + this.sessionName + "' car la connexion est fermée");
		}

		// Test si on peut suspendre la session
		if (sessionController.suspendSession(this)) {
			// Notifie la fin de la suspension de la session
			sessionController.notifyEndSuspendSession(this);
		}
		LOGGER.fine("Suspension de la session: " + sessionName);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void resume() throws ApiException {

		if (!sessionController.getConnection().isConnectionOpened()) {
			LOGGER.warning("Impossible de restaurer la session: " + this.sessionName + " [connexion fermée]");
			throw new ApiException("Impossible de restaurer la session '" + this.sessionName + "' car la connexion est fermée");
		}

		// Test si la session est déjà active
		if (sessionController.isActivateSession(this)) {
			LOGGER.fine("Restauration de la session: " + sessionName + " [session déjà active]");
			return;
		}

		// Test si la session est unique
		if (sessionController.onlyOneSession()) {
			sessionController.notifyEndResumeSession(this);
			return;
		}

		// Test si on peut restaurer
		if (sessionController.resumeSession(this)) {

			// Demande au wrapper changer de session: restaure la session
			LOGGER.finer("Demande la restauration de la session: " + sessionName);
			speaker.changeSession(this.getId());

			// Notifie la fin de la restauration de la session
			sessionController.notifyEndResumeSession(this);
		}

		LOGGER.fine("Restauration de la session: " + sessionName);
		return;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void close() throws ApiException {
		if (!sessionController.getConnection().isConnectionOpened()) {
			LOGGER.warning("Impossible de fermer la session: " + sessionName + " [connexion fermée]");
			return;
		}

		// Test si la session est déjà fermer
		if (this.automate.getState() == ISessionStateMachine.CLOSE_SESSION_STATE) {
			LOGGER.fine("Fermeture de la session: " + sessionName + " [était déjà fermée]");
			return;
		}

		// Test si on peut fermer la session
		if (sessionController.closeSession(this)) {
			// Met à jours l'automate de la session
			if (!automate.goToWaitingForCloseSessionState()) {
				LOGGER.warning("La session '" + sessionName + "' ne peut pas être en état d'attente pour une fermeture");
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_CLOSE_SESSION_STATE");
			}

			// Demande au wrapper de fermer la session
			LOGGER.finer("Demande la fermeture de la session: " + sessionName);
			Session sessionToResme = speaker.closeSession(idSession);

			// Notifie la fin de la fermeture de la session
			sessionController.notifyEndCloseSession(this, sessionToResme.getSessionId());

		}

		LOGGER.fine("Fermeture de la session: " + sessionName);
		return;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean sendDialogAnswer(IDialogAnswer dialogAnswer) throws ApiException {
		if (!sessionController.getConnection().isConnectionOpened()) {
			LOGGER.warning("Impossible d'envoyer une boîte de dialogue réponse pour la session: " + sessionName + " [connexion fermée]");
			return false;
		}

		// Création d'une boîte de dialogue pour le wrapper
		DialogBox answer = new DialogBox();
		// Création de la boîte de dialogue réponse pour le wrapper
		answer.setAnswer(new DBAnswer());

		// Initialisation de la boîte de dialogue réponse à envoyer au wrapper
		answer.getAnswer().setId(dialogAnswer.getIdDialog());
		answer.getAnswer().setButtonAnswer(dialogAnswer.getButtonType());
		answer.getAnswer().setModified(dialogAnswer.isModified());
		answer.getAnswer().setValue(join(dialogAnswer.getAllValue(), "\n"));

		// Initialisation du champ objects de la boîte de dialogue réponse en un tableau vide
		// car on ne le gére pas pour l'instant
		answer.getAnswer().setObjects(new int[0]);

		/*
		// Transformation de la list d'objets en un tableau d'objets pour le wrapper
		List<Integer> objects = dialogAnswer.getObjects();
		if (objects != null) {
			int [] objectsArray = new int[objects.size()];
			int cpt = 0;
			for (Integer line : objects) {
				objectsArray[cpt++] = line.intValue();
			}
			answer.getAnswer().setObjects(objectsArray);
		}
		*/

		// Envoyer la boîte de dialogue réponse à envoyer au wrapper
		LOGGER.finer("Demande l'envoi d'une boîte de dialogue réponse pour la session: " + sessionName);
		speaker.answerToDialogBox(answer);

		LOGGER.fine("Fin de l'envoi d'une boîte de dialogue réponse pour la session: " + sessionName);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void askForService(IService service, List<String> options, List<IElement> objects, List<String> texts, IGraph inputModel, IGraph outputModel) throws ApiException {

		if (!sessionController.getConnection().isConnectionOpened()) {
			LOGGER.warning("Impossible d'exécuter un service pour la session: " + sessionName + " [connexion fermée]");
			return;
		}


		// Test si on peut exécuter un service
		if (sessionController.askForService(this)) {
			// Met à jours l'automate de la session
			if (!automate.goToWaitingForResultState()) {
				LOGGER.warning("La session '" + sessionName + "' ne peut pas être en état de demander un service");
				throw new ApiException("Impossible d'aller a l'etat WAITING_FOR_RESULT_STATE");
			}

			String rootName = service.getRoot();
			Question root = null;

			String serviceName = service.getName();
			Question question = null;

			List<Option> theOptions = null;

			// Traduction du model en objets pour le wrapper
			LOGGER.finer("Traduction du model pour la session: " + sessionName);
			//Model theModel = translateModel(inputModel);


			/////////////////////////      Pour envoyer un model en CAMI    ////////////////////////////
			Model theModel = new Model();
			theModel.setCami(join(CamiModelTranslator.translateModel(inputModel), "\n"));
			theModel.setParsing(true);
			////////////////////////////////////////////////////////////////////////////////////////////


			// Détérmine le menu principal et le service demander à envoyer au wrapper
			LOGGER.finer("Recherche pour la session '" + sessionName + "' le service demander: " + serviceName);
			for (int i = 0; i < menus.getRoots().length; i++) {
				if (menus.getRoots()[i].getName().equals(rootName)) {
					root = (Question) menus.getRoots()[i].getRoot();
					question = getQuestion(menus.getRoots()[i].getRoot(), serviceName);
				}
			}

			// Teste si le menu principal du service demander existe
			if (root == null) {
				LOGGER.warning("Le menu principal: " + rootName + " n'existe pas");
				throw new ApiException("Le menu principal: " + rootName + " n'existe pas.");
			}

			// Teste si le service demander existe
			if (question == null) {
				LOGGER.warning("Le service: " + serviceName + " n'existe pas");
				throw new ApiException("Le service: " + serviceName + " n'existe pas.");
			}

			// Invalidation si nécessaire du model
			LOGGER.finer("Invalidation du model de la session '" + sessionName + "' si nécessaire: invalidateTheModel = " + invalidateTheModel);
			theModel.setInvalidate(invalidateTheModel);

			// Exécute le service demander
			RService result = null;
			if (question instanceof Service) {
				LOGGER.finer("Demande l'exécution du service simple: " + serviceName);
				result = speaker.executService(idSession, root, (Service) question, theOptions, theModel);
			} else if (question instanceof ServiceWithObjects) {
				LOGGER.finer("Demande l'exécution du service sur des objets: " + serviceName);
				// Construit le tableau d'objets pour le wrapper
				QO[] objectsArray = null;
				if (objects != null) {
					objectsArray = new QO[objects.size()];
					int cpt = 0;
					for (IElement obj : objects) {
						QO myQO = new QO();
						myQO.setId(obj.getId());
						objectsArray[cpt++] = myQO;
					}
				}
				// Initialise le tableau d'objet pour le service demander wrapper
				((ServiceWithObjects) question).setObjects(objectsArray);
				result = speaker.executeServiceWithObjects(idSession, root, (ServiceWithObjects) question, theOptions, theModel);
			} else if (question instanceof ServiceWithOneObject) {
				LOGGER.finer("Demande l'exécution du service sur un objet: " + serviceName);
				// Construit l'objet pour le wrapper
				QO myQO = null;
				if (objects != null && objects.size() == 1) {
					myQO = new QO();
					myQO.setId(objects.get(0).getId());
				}
				// Initialise l'objet sur lequel exécuter le service
				((ServiceWithOneObject) question).setObject(myQO);
				result = speaker.executeServiceWithOneObject(idSession, root, (ServiceWithOneObject) question, theOptions, theModel);
			} else if (question instanceof ServiceWithOneText) {
				LOGGER.finer("Demande l'exécution du service sur un texte: " + serviceName);
				// Construit le texte pour le wrapper
				QT myQT = null;
				if (texts != null && texts.size() == 1) {
					myQT = new QT();
					myQT.setName(texts.get(0));
				}
				// Initialise le text sur lequel exécuter le service
				result = speaker.executeServiceWithOneText(idSession, root, (ServiceWithOneText) question, theOptions, theModel);
			} else if (question instanceof ServiceWithTexts) {
				LOGGER.finer("Demande l'exécution du service sur du texte: " + serviceName);
				// Construit le tableau de texts pour le wrapper
				QT[] textsArray = null;
				if (texts != null) {
					textsArray = new QT[texts.size()];
					int cpt = 0;
					for (String tex : texts) {
						QT myQT = new QT();
						myQT.setName(tex);
						textsArray[cpt++] = myQT;
					}
				}
				// Initialise le tableau de texts pour le service demander wrapper
				((ServiceWithTexts) question).setTexts(textsArray);
				result = speaker.executeServiceWithTexts(idSession, root, (ServiceWithTexts) question, theOptions, theModel);
			} else {
				LOGGER.finer("Demande l'exécution du service: " + serviceName + "qui n'est pas reconnu comme un des cinq types de services possible");
				throw new ApiException("Le service: " + serviceName + " n'est pas reconnu comme un des cinq types de services possible");
			}

			LOGGER.finer("Fin de l'exécution du service: " + serviceName);

			// Réinitialise le boolean sur l'invalidation du model à false
			LOGGER.finer("Réinitialise invalidateTheModel à false");
			this.invalidateTheModel = false;

			// Notifie la fin de l'exécution du service demander
			sessionController.notifyEndResult(this, result, service);

		}

		LOGGER.finer("Exécution du service: " + service.getName());
		return;
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
			// Initialisation des attributs
			List<Attribute> myAttributes = new ArrayList<Attribute>();

			if (node.getAttribute("name") != null) {
				Attribute attName = new Attribute();
				attName.setAttName("name");

				AttributeValue attValueName = new AttributeValue();
				attValueName.setValue(node.getAttribute("name").getValue());
				AttributeValue[] attsValueArray = new AttributeValue[1];
				attsValueArray[0] = attValueName;

				attName.setValues(attsValueArray);

				myAttributes.add(attName);
			}

			if (node.getAttribute("marking") != null) {
				Attribute attMarking = new Attribute();
				attMarking.setAttName("marking");

				AttributeValue attValueMarking = new AttributeValue();
				attValueMarking.setValue(node.getAttribute("marking").getValue());
				AttributeValue[] attsValueArray = new AttributeValue[1];
				attsValueArray[0] = attValueMarking;

				attMarking.setValues(attsValueArray);

				myAttributes.add(attMarking);
			}

			Attribute[] theAttributs = new Attribute[myAttributes.size()];
			int cpt = 0;
			for (Attribute myAtt : myAttributes) {
				theAttributs[cpt++] = myAtt;
			}

			noeud.setAtts(theAttributs);

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
			// Initialisation du type de l'arc pour le wrapper
			theArc.setType(arc.getArcFormalism().getName());
			// Initialisation des attributs
			List<Attribute> myAttributes = new ArrayList<Attribute>();

			if (arc.getAttribute("name") != null) {
				Attribute attName = new Attribute();
				attName.setAttName("valuation");

				AttributeValue attValueName = new AttributeValue();
				attValueName.setValue(arc.getAttribute("valuation").getValue());
				AttributeValue[] attsValueArray = new AttributeValue[1];
				attsValueArray[0] = attValueName;

				attName.setValues(attsValueArray);

				myAttributes.add(attName);
			}

			Attribute[] theAttributs = new Attribute[myAttributes.size()];
			int cpt = 0;
			for (Attribute myAtt : myAttributes) {
				theAttributs[cpt++] = myAtt;
			}

			theArc.setAtts(theAttributs);

			// Ajout d'un arc dans le model pour le wrapper
			theModel.addArcs(theArc);
		}

		return theModel;
	}

	/**
	 * Transforme une liste de chaînes-de-caractères en une seul chaîne-de-caractères
	 * @param s la liste de chaînes-de-caractères
	 * @param delimiter le délimiteur à concaténer sur chacune des lignes
	 * @return une chaîne-de-caractères
	 */
	private String join(Collection<String> s, String delimiter) {
		StringBuilder buffer = new StringBuilder();
		Iterator<String> iter = s.iterator();
		if (iter.hasNext()) {
			buffer.append(iter.next());
			while (iter.hasNext()) {
				buffer.append(delimiter);
				buffer.append(iter.next());
			}
		}
		return buffer.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public final void setNewGraph(IGraph newGraph) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	public final void invalidModel() {
		LOGGER.finer("Invalidation du model de la session '" + sessionName);
		this.invalidateTheModel = true;
		sessionController.notifyEndInvalidate(this, menus);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void sendModel(IGraph model) throws ApiException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return <code>true</code> si le model est invalidé, <code>false</code> sinon
	 */
	public final boolean isInvalidate() {
		return invalidateTheModel;
	}

	/**
	 * Initialise le invalidateTheModel
	 * @param invalidateTheModel la nouvelle valeur
	 */
	public final void setInvalidate(boolean invalidateTheModel) {
		this.invalidateTheModel = invalidateTheModel;
	}

}
