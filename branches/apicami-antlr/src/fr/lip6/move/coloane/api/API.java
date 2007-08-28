package fr.lip6.move.coloane.api;

import java.io.IOException;
import java.net.UnknownHostException;

import fr.lip6.move.coloane.api.framekit.Network;
import fr.lip6.move.coloane.api.session.controller.IController;
import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;

public class API implements IApi {

	private Network network;
	private IController controller;
	
	public API(IController controller) {
		this.network = null;
		this.controller = null;
	}
	
	public boolean openConnection(String login, String password, String ip, int port, String apiName, String apiVersion) {
		boolean result = true;
		try {
			this.network = new Network(ip, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		this.controller.setFromFrameKit(this.network.getInput());
		this.controller.setToFrameKit(this.network.getOutput());
		// TODO
		return result;
	}

	public void askForService(String rootMenuName, String menuName,
			String serviceName) {
		// TODO Auto-generated method stub

	}

	public boolean changeModeleDate(int date) {
		// TODO Auto-generated method stub
		return false;
	}

	public void closeConnexion() {
		// TODO Auto-generated method stub

	}

	public boolean closeCurrentSession() {
		// TODO Auto-generated method stub
		return false;
	}

	public void drawDialog(IDialogCom dialog) {
		// TODO Auto-generated method stub

	}

	public int getDateModel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean getDialogAnswers(IDialogResult results) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean openSession(String sessionName, int date, String sessionFormalism) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean resumeSession(String sessionName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean stopService(String serviceName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean suspendCurrentSession() {
		// TODO Auto-generated method stub
		return false;
	}

}
