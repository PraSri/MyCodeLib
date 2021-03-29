package Amazon;

import DynamicProgramming.RegularExpressionMatch;

enum Set {

	Wildcard_Matching;
}

public class Questions_Set_1 {

	public void questionsList(Set set) {

		switch (set) {
		case Wildcard_Matching: {
			RegularExpressionMatch regularExpressionMatch = new RegularExpressionMatch();
			System.out.println(regularExpressionMatch.getClass());
			break;
		}

		}
	}

	public static void main(String[] args) {
		Questions_Set_1 qset = new Questions_Set_1();
		qset.questionsList(Set.Wildcard_Matching);
	}

}
