package observerpattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		BankAccount bankAccount = BankAccount.getInstance("PraSri", 100);

		Scanner scanner = new Scanner(System.in);
		String answer = null;
		while (true) {
			System.out.println("Welcome " + bankAccount.owner + " to Java Bank!");
			System.out.println("Which operation do you want to do?");
			System.out.println("(1) Withdrawal Operation");
			System.out.println("(2) Credit Operation");
			System.out.println("(Other) Exit");

			int a = scanner.nextInt();
			if (a == 1) {
				System.out.println("Your balance is " + bankAccount.balance + "$");
				System.out.println("How many money do you want to withdraw?");

				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				try {
					answer = input.readLine();

					double withdraw_amount = Double.parseDouble(answer);
					bankAccount.withdraw(withdraw_amount);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			else if (a == 2) {
				System.out.println("Your balance is " + bankAccount.balance + "$");
				System.out.println("How many monet do you want to credit?");

				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				try {
					answer = input.readLine();

					double credit_amount = Double.parseDouble(answer);
					bankAccount.credit(credit_amount);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Good Bye!");
				break;
			}
		}

	}
}
