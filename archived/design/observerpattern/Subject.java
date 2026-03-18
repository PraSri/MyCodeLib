package observerpattern;

// One to many dependency is between Subject(One) and Observer(Many).
public interface Subject {

	public void registerObserver(Observer o);

	public void unregisterObserver(Observer o);

	public void notifyObservers();

}
