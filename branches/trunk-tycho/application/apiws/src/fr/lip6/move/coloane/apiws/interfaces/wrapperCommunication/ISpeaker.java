package fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication;

import fr.lip6.move.coloane.apiws.stubs.WrapperStub;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Authentification;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.DialogBox;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Model;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Option;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Question;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.RService;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Service;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithObjects;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithOneObject;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithOneText;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.ServiceWithTexts;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Session;
import fr.lip6.move.coloane.apiws.stubs.WrapperStub.Unauthentification;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;

import java.util.List;

/**
 * Cette interface représent un speaker pour la communication avec le wrapper.
 *
 * @author Monir CHAOUKI
 */
public interface ISpeaker {

	/**
	 * Recupere l'authentification de l'utilisateur
	 * @return l'authentification de l'utilisateur
	 */
	Authentification getAuthentification();

	/**
	 * Recupere le stub de communication
	 * @return le stub de communication
	 */
	WrapperStub getStub();

	/**
	 * Demander une ouverture de connexion au wrapper
	 * @param login le login de l'utilisateur
	 * @param password le password de l'utilisateur
	 * @return un objet représentant la connexion
	 * @throws ApiException si la connection échoue
	 */
	Authentification openConnection(String login, String password) throws ApiException;

	/**
	 * Demander une ouverture de session au wrapper
	 * @param nameFormalism le nom du formalisme pour l'ouverture de la session
	 * @return l'objet représentant la session ouverte
	 * @throws ApiException si l'ouverture de la session échoue
	 */
	Session openSession(String nameFormalism) throws ApiException;

	/**
	 * Demander un changement de session au wrapper
	 * @param idSession l'identifiant de la session a réstauré
	 * @return l'objet représentant la session réstaurer
	 * @throws ApiException si la réstauration de la session échoue
	 */
	Session changeSession(String idSession) throws ApiException;

	/**
	 * Demander une fermeture de session au wrapper
	 * @param idSession l'identifiant de la session a réstauré
	 * @return l'objet représentant la session réstaurer
	 * @throws ApiException si la fermeture de la session échoue
	 */
	Session closeSession(String idSession) throws ApiException;

	/**
	 * Demander une fermeture de connexion
	 * @return l'objet représentant la déconnexion
	 * @throws ApiException si la déconnexion échoue
	 */
	Unauthentification closeConnection() throws ApiException;

	/**
	 * Repond a la boite de dialog
	 * @param answer la boite de dialog a la quelle on repond
	 * @return Un message decrivant la bonne reception de la boite de dialog reponse
	 * @throws ApiException si la réponse à la boîte de dialogue échoue
	 */
	String answerToDialogBox(DialogBox answer) throws ApiException;

	/**
	 * Demander l'execution d'un service simple au wrapper
	 * @param idSession l'identifiant de la session sur laquelle il faut exécuter le service.
	 * @param root le menu principal où se trouve le service à executer
	 * @param question le service simple à executer
	 * @param options la liste des options pour le service
	 * @param theModel le model sur lequel exécuter le service
	 * @return le résultat de l'execution du service
	 * @throws ApiException si l'exécution du service échoue
	 */
	RService executService(String idSession, Question root, Service question, List<Option> options, Model theModel) throws ApiException;

	/**
	 * Demander l'execution d'un service sur des objets au wrapper
	 * @param idSession l'identifiant de la session sur laquelle il faut exécuter le service.
	 * @param root le menu principal où se trouve le service à executer
	 * @param question le service sur des objets à executer
	 * @param options la liste des options pour le service
	 * @param theModel le model sur lequel exécuter le service
	 * @return le résultat de l'execution du service
	 * @throws ApiException si l'exécution du service échoue
	 */
	RService executeServiceWithObjects(String idSession, Question root, ServiceWithObjects question, List<Option> options, Model theModel) throws ApiException;

	/**
	 * Demander l'execution d'un service sur un objet au wrapper
	 * @param idSession l'identifiant de la session sur laquelle il faut exécuter le service.
	 * @param root le menu principal où se trouve le service à executer
	 * @param question le service sur des objets à executer
	 * @param options la liste des options pour le service
	 * @param theModel le model sur lequel exécuter le service
	 * @return le résultat de l'execution du service
	 * @throws ApiException si l'exécution du service échoue
	 */
	RService executeServiceWithOneObject(String idSession, Question root, ServiceWithOneObject question, List<Option> options, Model theModel) throws ApiException;

	/**
	 * Demander l'execution d'un service sur un texte au wrapper
	 * @param idSession l'identifiant de la session sur laquelle il faut exécuter le service.
	 * @param root le menu principal où se trouve le service à executer
	 * @param question le service sur un texte à executer
	 * @param options la liste des options pour le service
	 * @param theModel le model sur lequel exécuter le service
	 * @return le résultat de l'execution du service
	 * @throws ApiException si l'exécution du service échoue
	 */
	RService executeServiceWithOneText(String idSession, Question root, ServiceWithOneText question, List<Option> options, Model theModel) throws ApiException;

	/**
	 * Demander l'execution d'un service sur du texte au wrapper
	 * @param idSession l'identifiant de la session sur laquelle il faut exécuter le service.
	 * @param root le menu principal où se trouve le service à executer
	 * @param question le service sur du texte à executer
	 * @param options la liste des options pour le service
	 * @param theModel le model sur lequel exécuter le service
	 * @return le résultat de l'execution du service
	 * @throws ApiException si l'exécution du service échoue
	 */
	RService executeServiceWithTexts(String idSession, Question root, ServiceWithTexts question, List<Option> options, Model theModel) throws ApiException;
}
