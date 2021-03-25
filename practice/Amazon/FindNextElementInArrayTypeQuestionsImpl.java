package Amazon;

import java.util.List;
import Stack.*;

public class FindNextElementInArrayTypeQuestionsImpl implements FindNextElementInArrayTypeQuestions {

	@Override
	public List<Integer> PSE(int[] a) {
		return PSE.pse(a);
	}

	@Override
	public List<Integer> PGE(int[] a) {
		return PGE.pge(a);
	}

	@Override
	public List<Integer> NSE(int[] a) {
		return NSE.nse(a);
	}

	@Override
	public List<Integer> NGE(int[] a) {
		return NGE.nge(a);
	}

}
