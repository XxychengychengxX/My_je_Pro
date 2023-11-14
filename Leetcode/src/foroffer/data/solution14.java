package foroffer.data;

import java.util.Arrays;

public class solution14 {

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.println(Arrays.toString(exchange(a)));
	}

	public static int[] exchange(int[] nums) {
		/*
		 * int len = nums.length; int i = 0, j = len - 1; while (i < j) { if (nums[i] %
		 * 2 != 0) { i++; } else if (nums[j] % 2 == 0) { j--; } else {
		 * //nums[i]是偶数nums[j]是奇数 int temp = nums[i]; nums[i] = nums[j]; nums[j] = temp;
		 * } } return nums; 。
		 */
		int i = 0;
		int j = nums.length - 1;
		int[] ints = new int[nums.length];
		for (int num : nums) {
			if (num % 2 == 0) {
				ints[j--] = num;
			} else {
				ints[i++] = num;
			}
		}
		return ints;
	}

	public int[] twoSum(int[] nums, int target) {
		int i = 0, j = nums.length - 1;
		while (nums[i] + nums[j] != target) {
			if (nums[i] + nums[j] < target)
				i++;
			else
				j--;

		}
		return new int[] { nums[i], nums[j] };

	}
}