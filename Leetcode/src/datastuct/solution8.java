package datastuct;

import java.util.*;

public class solution8 {
	public static List<List<Integer>> generate(int numRows) {
//        List<int[]> yanghui = new ArrayList<>();

		List<List<Integer>> yanghui = new ArrayList<>();
		for (int i = 1; i <= numRows; i++) {
			List<Integer> hang = new ArrayList<>();

			if (i == 1) {
				hang.add(1);
				yanghui.add(hang);

			} else {
				hang.add(1);
				for (int j = 0; j < i - 2; j++) {
					hang.add(yanghui.get(i - 2).get(j) + yanghui.get(i - 2).get(j + 1));
				}
				hang.add(1);
				yanghui.add(hang);
			}

		}

		return yanghui;
	}

	public static void main(String[] args) {
		System.out.println(generate(5));
	}
}