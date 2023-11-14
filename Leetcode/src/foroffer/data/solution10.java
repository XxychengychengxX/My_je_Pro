package foroffer.data;

/*在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 

示例 1:

输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物


作者：Krahets
链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/5vokvr/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
public class solution10 {

	public int maxValue(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (i == 0) {
					grid[i][j] += grid[i][j - 1];
				} else if (j == 0) {
					grid[i][j] += grid[i - 1][j];
				} else {
					grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
				}
			}
		}
		return grid[m - 1][n - 1];
		// for(int j = 1; j < n; j++) // 初始化第一行
		// grid[0][j] += grid[0][j - 1];
		// for(int i = 1; i < m; i++) // 初始化第一列
		// grid[i][0] += grid[i - 1][0];
		// for(int i = 1; i < m; i++)
		// for(int j = 1; j < n; j++)
		// grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
		// return grid[m - 1][n - 1];
		//

	}
}
