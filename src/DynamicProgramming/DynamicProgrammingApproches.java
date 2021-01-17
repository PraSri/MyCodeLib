package DynamicProgramming;

public interface DynamicProgrammingApproches<T> {
	
	public void recursiveRelation(T[] inputArray);
	
	public T recursiveTopDown(T[] inputArray);
	
	public T recursiveTopDownMemo(T[] inputArray);
	
	public T iterativeBottomUpMemo(T[] inputArray);
	
	public T iterativeBottomUpNvariables(T[] inputArray);

}
