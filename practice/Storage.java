
public class Storage {
	
	
	public long prison(int n, int m, int[] hor, int[] ver) {
		boolean[] h = new boolean[n + 1], v = new boolean[m + 1];
		for (int i = 0; i < hor.length; i++) {
			h[hor[i]] = true;
		}
		for (int j = 0; j < ver.length; j++) {
			v[ver[j]] = true;
		}
		int inARowHor = 0, inARowVer = 0;
		
		for (int i = 1, j = 0; i <= n; i++) {
			if (!h[i])
				j = 0;
			else {
				j++;
				inARowHor = Math.max(inARowHor, j);
			}
		}
		
		for (int i = 1, j = 0; i <= m; i++) {
			if (!v[i])
				j = 0;
			else {
				j++;
				inARowVer = Math.max(inARowVer, j);
			}
		}
		return (long) (inARowHor + 1) * (inARowVer + 1);
	}
}
