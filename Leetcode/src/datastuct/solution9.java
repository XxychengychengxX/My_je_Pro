package datastuct;

import java.util.ArrayList;

/*请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 

注意：

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
空白格用 '.' 表示。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/valid-sudoku
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class solution9 {
	public boolean isValidSudoku(char[][] board) {
		ArrayList<Character> arrayList = new ArrayList<>();
//        for (char[] a : board) {
//            ArrayList<Character> arrayList=new ArrayList<>();
//
//            for (char b : a
//            ) {
//            if(b!='.'){
//                if (arrayList.contains(b))
//                    return false;
//                else arrayList.add(b);
//            }
//            }
//
//        }
		for (int i = 0, j = 0; i < board.length; i++, j++) { // 判断行

			for (char a : board[i]) {
				if (a != '.') {
					if (arrayList.contains(a))
						return false;
					else
						arrayList.add(a);
				}
			}
			arrayList.clear();

			int row = 0;
			while (row < board.length) { // 判断列
				char c = board[row][j];
				if (c != '.') {
					if (arrayList.contains(c))
						return false;
					else
						arrayList.add(c);

				}
				row++;
			}
			arrayList.clear();

			if (i % 3 == 0 && j % 3 == 0) {
				int row1 = i;
				int column = 0;
				int count = 1;
				while (count <= 27) {
					char c = board[row1][column];
					if (c != '.') {
						if (arrayList.contains(c))
							return false;
						else
							arrayList.add(c);
					}
					column++;
					if (count % 3 == 0 && row1 != i + 2) {
						row1 += 1;
						column -= 2;
					} else if (count % 9 == 0) {
						row1 = i;
						arrayList.clear();
					}
					count++;
				}

			}

		}

		return true;
		/*
		 * public boolean isValidSudoku(char[][] board) { //定义数字行内出现的次数 int[][] row =
		 * new int[9][9]; //定义数字列内出现的次数 int[][] column = new int[9][9];
		 * //定义数字九宫格内出现的次数最大为9次 int[][][] jiugongge = new int[3][3][9]; //遍历数组 for (int
		 * i =0;i <9;i++){ for(int j = 0;j<9;j++){ char c = board[i][j]; //只要存在数字 if (c
		 * !='.'){ //把数字-1化成索引下标,c是字符串要减去字符串，-1会报错。 int index = c-'1';
		 * //这个时候++意思是第i行这个c值次数+1，默认row第二位就是{1-9}-1；每一行都有可能是1-9
		 * //例如现在是第一行第一列是9，就在row[1][8]号位置+1 row[i][index]++; //列同理 column[j][index]++;
		 * //并且九宫格内次数也要+1,例如也是第1行第一列，i/3 j/3会自动定位到所在的小宫格 jiugongge[i/3][j/3][index]++;
		 * //次数大于1就不成立一个数独 if
		 * (row[i][index]>1||column[j][index]>1||jiugongge[i/3][j/3][index]>1) return
		 * false; } } } return true; }
		 */

	}
}
