package java8;

public class FormulaImpl {

	public static void demoFormula() {
		Formula formula = new Formula() {

			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};

		System.out.println(formula.sqrt(2) + "______" + formula.calculate(2));

	}

	/*******
	 * Default methods cannot be accessed from within lambda expressions. The
	 * following code does not compile: Formula formula = (a) -> sqrt(a * 100);
	 * 
	 *******/

	public static void useLambda(Formula f) {
		String ans = "Lambda " + f.calculate(100);
		System.out.println(ans);
	}

	public static void main(String[] args) {
		demoFormula();
	}

}
