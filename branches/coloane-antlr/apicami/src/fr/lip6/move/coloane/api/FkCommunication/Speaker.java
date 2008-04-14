package fr.lip6.move.coloane.api.FkCommunication;

import fr.lip6.move.coloane.api.interfaces.IDialog;
import fr.lip6.move.coloane.api.interfaces.IModel;
import fr.lip6.move.coloane.api.interfaces.ISpeaker;

public class Speaker implements ISpeaker {

	public void AskForService(String rootName, String ServiceName) {
		// TODO Auto-generated method stub

	}

	public void AskForService(String rootName, String RootName, String date) {
		// TODO Auto-generated method stub

	}

	public void CloseConnection() {
		// TODO Auto-generated method stub

	}

	public void CloseSession(String SessionName) {
		// TODO Auto-generated method stub

	}

	public void OpenConnection(String IPAdress, int port, String login,
			String password) {
		// TODO Auto-generated method stub

	}

	public void OpenConnection(String sessionName, int date,
			String SessionFormalism) {
		// TODO Auto-generated method stub

	}

	public void ResumeSession(String SessionName) {
		// TODO Auto-generated method stub

	}

	public void SendDialogResponse(IDialog d) {
		// TODO Auto-generated method stub

	}

	public void SendModel(IModel m) {
		// TODO Auto-generated method stub

	}

	public void SuspendSession(String SessionName) {
		// TODO Auto-generated method stub

	}

}
