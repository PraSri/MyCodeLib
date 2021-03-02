package GoldmanSachs;

public class NumberOfWaysNaturalNoCanBeExpressedAsSumOfConsecNos {
	
	public static void main(String[] args) {
		System.out.println(consecutiveNumbersSum(100));
	}

	public static int consecutiveNumbersSum(int N) {
		int ans = 0;
		int sumAs2orMoreConsecNos = 2;
		for (int i = sumAs2orMoreConsecNos; i * (i - 1) / 2 < N; ++i)
			if ((N - i * (i - 1) / 2) % i == 0)
				++ans;
		return ans;
	}

}
