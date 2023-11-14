package datastuct;

import java.util.HashSet;
import java.util.Set;

public class _01 {

	public static void main(String[] args) {
		int[] ints = new int[] { 1, 2, 2 };
		System.out.println(solution(ints));
	}

	public static boolean solution(int[] num) {
		Set<Integer> ints = new HashSet<>() {
		};
		for (int i : num) {
			if (ints.contains(i)) {
				return false;
			} else
				ints.add(i);
		}
		return true;
	}
}
