package fr.lip6.move.coloane.api;

import fr.lip6.move.coloane.api.framekit.Network;
import fr.lip6.move.coloane.api.session.controller.IController;
import fr.lip6.move.coloane.api.session.states.authentication.LoginPassword;
import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IDialogResult;
import fr.lip6.move.coloane.interfaces.objects.IDialogCom;

public final class API implements IApi {

	private IController controller;
	private Thread controllerThread;

	public API(IController controller) {
		this.controller = controller;
		this.controllerThread = new Thread(this.controller);
		this.controllerThread.start();
	}

	public boolean openConnection(String login,
										String password,
										String ip,
										int port,
										String apiName,
										String apiVersion) {
		try {
			this.controller.setFrameKit(new Network(ip, port));
			this.controller.getFromColoane().put(new LoginPassword(login, password, apiName, apiVersion));
			this.controller.getToColoane().take();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
