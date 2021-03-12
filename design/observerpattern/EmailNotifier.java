package observerpattern;

public class EmailNotifier extends Notifier {

	public EmailNotifier(BankAccount bankAccount) {
		super(bankAccount);
	}

	@Override
	public void display() {
		System.out.println("Sending Email to " + bankAccount.owner);

		if (event.equals("Withdraw")) {
			System.out.println("Withdrawal operation is successful. Withdrawal amount is " + bankAccount.amount + "$");
		} else if (event.equals("Credit")) {
			System.out.println("Investment operation is successful. Investment amount is " + bankAccount.amount + "$");
		}

		System.out.println("The amount of your account is " + bankAccount.balance + "$");
		System.out.println("");
	}
}