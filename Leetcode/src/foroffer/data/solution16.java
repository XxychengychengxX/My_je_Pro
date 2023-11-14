package foroffer.data;

public class solution16 {
	public static void main(String[] args) {
		String s = "abccee";
		char[][] board = { { 'a', 'b', 'c', 'e' }, { 's', 'f', 'c', 's' }, { 'a', 'd', 'e', 'e' } };
//        char[][] board = {{'a', 'a' }};
//        String s = "aa";
		System.out.println(exist(board, s));
	}

	/*
	 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
	 * 
	 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
	 * 
	 *  
	 * 
	 * 作者：Krahets
	 * 链接：https://leetcode-cn.com/leetbook/read/illustration-of-algorithm/58wowd/
	 * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	 */
	public static boolean exist(char[][] board, String word) {
		char[] chars = word.toCharArray();

		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				int[][] ints1 = new int[board.length][board[0].length];
				if (board[i][j] == chars[0]) {
					try {
						if (find(board, i, j, chars, 0, ints1))
							return true;
					} catch (Exception e) {
						return false;

					}
				}
			}

		return false;
	}

	public static boolean find(char[][] board, int i, int j, char[] chars, int k, int[][] ints)
			throws ArrayIndexOutOfBoundsException {
		boolean res = false;
		if (k == chars.length)
			return true;

		else if (i >= board.length || i < 0 || j >= board[0].length || j < 0)
			return false;
		else if (ints[i][j] == 1)
			return false;
		else if (board[i][j] == chars[k]) {
			ints[i][j] = 1;
			res = find(board, i + 1, j, chars, k + 1, ints) || find(board, i - 1, j, chars, k + 1, ints)
					|| find(board, i, j + 1, chars, k + 1, ints) || find(board, i, j - 1, chars, k + 1, ints);
			ints[i][j] = 0;// 回溯！
		}
		return res;
	}

	public boolean exist1(char[][] board, String word) {
		char[] words = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (dfs(board, words, i, j, 0))
					return true;
			}
		}
		return false;
	}

	boolean dfs(char[][] board, char[] word, int i, int j, int k) {
		if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k])
			return false;
		if (k == word.length - 1)
			return true;
		board[i][j] = '\0';
		boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1)
				|| dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
		board[i][j] = word[k];
		return res;
	}
}
