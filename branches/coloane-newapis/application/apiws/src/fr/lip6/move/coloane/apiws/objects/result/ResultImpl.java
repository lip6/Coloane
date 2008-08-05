package fr.lip6.move.coloane.apiws.objects.result;

import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.service.IService;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe définie un résultat pour le core de Coloane.
 */
public class ResultImpl implements IResult {

	private String rootName;

	private String serviceName;

	private List<ISubResult> subResult;

	/**
	 * Constructeur
	 * @param result le resultat reçu de la part du wrapper
	 * @param service le service executer;
	 */
	public ResultImpl(RService result, IService service) {
		this.rootName = service.getRoot();

		this.serviceName = result.getAnswerToquestion().getName();

		this.subResult = new ArrayList<ISubResult>();
		this.subResult.add(new SubResultImpl(result));
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getRootName() {
		return rootName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return serviceName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getSubResults() {
		return subResult;
	}

}
