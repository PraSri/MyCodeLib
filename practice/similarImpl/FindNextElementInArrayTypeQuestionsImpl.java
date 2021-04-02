package similarImpl;

import java.util.List;

import Arrays.LeadersInArray;
import Stack.*;
import similar.FindNextElementInArrayTypeQuestions;

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

	@Override
	public List<Integer> leadersInArray(int[] a) {
		return LeadersInArray.getLeaders(a);
	}

}
