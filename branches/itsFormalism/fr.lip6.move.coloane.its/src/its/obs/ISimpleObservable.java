package its.obs;

public interface ISimpleObservable {

	public abstract void addObserver(ISimpleObserver o);

	public abstract void deleteObserver(ISimpleObserver o);

}