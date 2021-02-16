package QuickHelper;

public class SmallestPrimeFactorOfInteger {

	public static void main(String[] args) {

		sieveSmallestPrimeFactor();

		for (int i = 0; i < maxn; i++) {
			System.out.println("Number :  " + i + " SPF : " + smallestPrimeFactor[i]);
			if (i > 20)
				break;
		}

	}

	public static int maxn = 1000001;
	public static int[] smallestPrimeFactor = new int[maxn];

	public static void sieveSmallestPrimeFactor() {
		for (int i = 0; i < maxn; i++) {
			smallestPrimeFactor[i] = i;
		}
		for (int i = 4; i < maxn; i += 2) {
			smallestPrimeFactor[i] = 2;
		}
		for (int i = 3; i * i < maxn; i += 2) {
			if (smallestPrimeFactor[i] == i) {
				for (int j = i * i; j < maxn; j += 2 * i) {
					if (smallestPrimeFactor[j] == j) {
						smallestPrimeFactor[j] = i;
					}
				}
			}
		}
	}

}
