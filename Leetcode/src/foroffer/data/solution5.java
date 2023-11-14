package foroffer.data;

/*
在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。

 

作者：Krahets
链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/5v76yi/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class solution5 {
	public static boolean findNumberIn2DArray(int[][] matrix, int target) {
//        if (matrix.length == 0)
//            return false;
//        int temp = Integer.MAX_VALUE;
//        int row = matrix.length / 2;
//        int colunm = matrix[0].length / 2;
//        if (row == 0) {
//            for (int a : matrix[0]
//            ) {
//                if (a == target)
//                    return true;
//            }
//            return false;
//        }
//        if (colunm == 0) {
//            for (int[] ints : matrix)
//                if (ints[0] == target)
//                    return true;
//            return false;
//        }
//        try {
//            while (true) {
//                if (matrix[row][colunm] == target)
//                    return true;
//                else if (matrix[row][colunm] < target) {
//                    for (int i = row; i < matrix.length; i++) {
//                        if (matrix[i][colunm] == target)
//                            return true;
//                    }
//                    for (int i = colunm; i < matrix[0].length; i++) {
//                        if (matrix[row][i] == target)
//                            return true;
//                    }
//                    if (matrix[++row][++colunm] == temp)
//                        return false;
//                    else temp = matrix[row - 1][colunm - 1];
//                    //                row++;
//                    //                colunm++; 死循环
//                }
//
//                else if (matrix[row][colunm] > target) {
//                    for (int i = row; i >=0; i--) {
//                        if (matrix[i][colunm] == target)
//                            return true;
//                    }
//                    for (int i = 0; i < colunm; i++) {
//                        if (matrix[row][i] == target)
//                            return true;
//                    }
//                    if (matrix[--row][--colunm] == temp)
//                        return false;
//                    else temp = matrix[row + 1][colunm + 1];
//                    //                row--;
//                    //                colunm--;死循环
//
//                }
//            }
//        } catch (Exception e) {
//            return false;
//        }

		// 直接遍历整个二维数组
		for (int[] ints : matrix) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (ints[j] == target) {
					return true;
				}
			}
		}

		return false;
	}
	/*
	 * class Solution { public boolean findNumberIn2DArray(int[][] matrix, int
	 * target) { // 以左下角的数字为准，跟target比较，决定是排除列还是行 int i = matrix.length - 1; int j =
	 * 0; //i为行，j为列
	 * 
	 * while(i >= 0 && j < matrix[0].length){ if(matrix[i][j] > target)
	 * //matrix[i][j]为该行最小值还大于target，排除该行 i--; else if(matrix[i][j] < target)
	 * //matrix[i][j]为该列最大值还小于target，排除该列 j++; else return true; } return false; } }
	 */
}
