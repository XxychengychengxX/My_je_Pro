package datastuct;

public class solution7 {

	public int[][] matrixReshape(int[][] nums, int r, int c) {
		int or = nums.length, oc = nums[0].length;
		if (or * oc != r * c)
			return nums;
		int[][] ans = new int[r][c];
		for (int i = 0, idx = 0; i < or; i++) {
			for (int j = 0; j < oc; j++, idx++) {
				ans[idx / c][idx % c] = nums[i][j];
			}
		}
		return ans;
	}
}
