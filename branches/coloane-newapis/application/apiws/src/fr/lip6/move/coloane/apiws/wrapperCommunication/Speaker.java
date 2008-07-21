package fr.lip6.move.coloane.apiws.wrapperCommunication;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import fr.lip6.move.coloane.apiws.interfaces.wrapperCommunication.ISpeaker;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.wrapper.ws.GExceptionException0;
import fr.lip6.move.wrapper.ws.WrapperStub;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDb;
import fr.lip6.move.wrapper.ws.WrapperStub.AnswerDbResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.Authentification;
import fr.lip6.move.wrapper.ws.WrapperStub.ChangeSession;
import fr.lip6.move.wrapper.ws.WrapperStub.ChangeSessionResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.CloseSession;
import fr.lip6.move.wrapper.ws.WrapperStub.CloseSessionResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.Connect;
import fr.lip6.move.wrapper.ws.WrapperStub.ConnectResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.CreateSession;
import fr.lip6.move.wrapper.ws.WrapperStub.CreateSessionResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.Disconnect;
import fr.lip6.move.wrapper.ws.WrapperStub.DisconnectResponse;
import fr.lip6.move.wrapper.ws.WrapperStub.Session;
import fr.lip6.move.wrapper.ws.WrapperStub.Unauthentification;

public class Speaker implements ISpeaker {
	private Authentification auth = null;
	
	private WrapperStub stub = null;
	
	public Speaker(){
		try {
			stub = new WrapperStub("http://localhost:8081/axis2/services/Wrapper");
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Authentification getAuthentification(){
		return auth;
	}
	
	public WrapperStub getStub(){
		return stub;
	}

	public Authentification openConnection(String login, String pass) throws ApiException {
		try {
			if (stub == null)
				throw new ApiException("Error of communcation : Stub is null");
			Connect req = new Connect();
			req.setLogin(login);
			req.setMdp(pass);
			ConnectResponse res = stub.connect(req);
			auth = res.get_return();
		} catch (RemoteException e) {
            e.printStackTrace();
            ApiException ee = new ApiException(e.getMessage());
			// TODO Auto-generated catch block
			throw ee;
		} catch (GExceptionException0 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auth;
	}

	public Session openSession(String nameFormalism) throws ApiException  {
		Session session = null;
		
		try {
            if(stub==null)
				throw new ApiException("Error of communcation : Stub is null");
            CreateSession req = new CreateSession();
            req.setNameFormalism(nameFormalism);
            req.setUid(auth);
            CreateSessionResponse res=stub.createSession(req);
            session=res.get_return();
        }catch (RemoteException e) {
            e.printStackTrace();
            ApiException ee = new ApiException(e.getMessage());
            // TODO Auto-generated catch block
            throw ee;
        } catch (GExceptionException0 e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
		return session;
	}

	public Session changeSession(String idSession) throws ApiException{
		Session session=null;        

		try {
			if(stub==null)
				throw new ApiException("Error of communcation : Stub is null");
			ChangeSession req = new ChangeSession();
			req.setUid(auth);
			req.setIdSession(idSession);
			ChangeSessionResponse res=stub.changeSession(req);
			session=res.get_return();
		}catch (RemoteException e) {
            e.printStackTrace();
            ApiException ee = new ApiException(e.getMessage());
			// TODO Auto-generated catch block
			throw ee;
		} catch (GExceptionException0 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return session;
	}
	
	public Session closeSession(String idSession) throws ApiException{
		Session session=null;        
        
        try {
            if(stub==null)
				throw new ApiException("Error of communcation : Stub is null");
            CloseSession req = new CloseSession();
            req.setUid(auth);
            req.setIdSession(idSession);
            CloseSessionResponse res=stub.closeSession(req);
            session=res.get_return();
        }catch (RemoteException e) {
            e.printStackTrace();
            ApiException ee = new ApiException(e.getMessage());
            // TODO Auto-generated catch block
            throw ee;
        } catch (GExceptionException0 e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        return session;    
	}

	public Unauthentification closeConnection() throws ApiException{
		Unauthentification unauth = null;
		
		try {
			if(stub==null)
				throw new ApiException("Error of communcation : Stub is null");
            Disconnect req = new Disconnect();
            req.setId(auth);
            DisconnectResponse res=stub.disconnect(req);
            unauth=res.get_return();
		}catch (RemoteException e) {
            e.printStackTrace();
            ApiException ee = new ApiException(e.getMessage());
            // TODO Auto-generated catch block
            throw ee;
        } catch (GExceptionException0 e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		
        return unauth;
	}

	public void executService() {
		// TODO Auto-generated method stub
		
	}

	public String answerToDialogBox(DialogBox answer) throws ApiException{
		String toReturn = null;
		
		try {
			if(stub==null)
				throw new ApiException("Error of communcation : Stub is null");
			AnswerDb req = new AnswerDb();
			req.setAuth(auth);
			req.setDialog(answer);
			AnswerDbResponse res=stub.answerDb(req);
			toReturn=res.get_return();
		}catch (RemoteException e) {
			ApiException ee = new ApiException(e.getMessage());
			// TODO Auto-generated catch block
			throw ee;
		} catch (GExceptionException0 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return toReturn;    
	}
}