package graphs;

public class FindTheTownJudge {

    public static int findJudge(int n, int[][] trust) {

        // If the length of the trust array is less than n - 1,
        // it means there is no judge.
        if (trust.length < n - 1) {
            return -1;
        }

        // Initialize indegree and outdegree arrays
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];

        // Loop over the trust relationships, and calculate the in-degree and outdegree
        for (int[] relation : trust) {
            int a = relation[0];
            int b = relation[1];

            // Person a trusts someone, increase their outdegree
            outdegree[a]++;
            // Person b is trusted, increase their indegree
            indegree[b]++;
        }

        // Find the person who is trusted by everyone and trusts no one
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == n - 1 && outdegree[i] == 0) {
                return i;
            }
        }

        // If no judge is found, return -1
        return -1;
    }


}
