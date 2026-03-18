package observerpattern;

//There is dependency as Observers themselves don’t have access to data. They are dependent on Subject to provide them data.
public interface Observer {

	 public void update(double account, String event);

}
