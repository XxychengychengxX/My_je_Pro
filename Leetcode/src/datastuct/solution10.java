package datastuct;

public class solution10 {
	public void setZeroes(int[][] matrix) {
		int i = matrix.length;
		int j = matrix[0].length;
		int[] ints = new int[matrix[0].length];
		for (int[] a : matrix) {
			int flag = 0;
			for (int k = 0; k < a.length; k++) {
				if (a[k] == 0) {
					flag = 1;
					ints[k] = 1;
				} else if (flag == 1 && a[k] != 0) {
					a[k] = 0;
				}
			}
		}
	}
}
