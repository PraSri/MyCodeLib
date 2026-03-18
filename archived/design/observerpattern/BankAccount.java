package observerpattern;

import java.util.ArrayList;

public class BankAccount implements Subject {
	private static BankAccount uniqueInstance;

	private ArrayList<Observer> observers;
	private double account;
	public String owner;
	public double amount;
	public double balance;
	public String event;

	private BankAccount(String owner, double balance) {
		this.owner = owner;
		this.balance = balance;

		observers = new ArrayList<>();
	}

	public static BankAccount getInstance(String owner, double balance) {
		if (uniqueInstance == null) {
			uniqueInstance = new BankAccount(owner, balance);
			SmsNotifier smsNotifier = new SmsNotifier(uniqueInstance);
			EmailNotifier emailNotifier = new EmailNotifier(uniqueInstance);
		}
		return uniqueInstance;
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void unregisterObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(account, event);
		}
	}

	public void statusChanged() {
		notifyObservers();
	}

	public void withdraw(double amount) {
		this.amount = amount;
		if ((balance - amount) > 0) {
			balance -= amount;
			this.event = "Withdraw";
			statusChanged();
		} else {
			System.out.println("You cannot withdraw!");
			System.out.println("");
		}
	}

	public void credit(double amount) {
		this.amount = amount;
		balance += amount;
		this.event = "Credit";
		statusChanged();
	}

}
