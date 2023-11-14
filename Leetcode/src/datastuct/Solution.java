package datastuct;

/*给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

子数组 是数组中的一个连续部分*/
class Solution {
//int ans = nums[0];int pre = 0;
//        for (int i : nums) {
//
//
//            pre = Math.max(pre + i, i);
//            ans = Math.max(pre, ans);
//        }
//        return ans;

	public static int maxSubArray(int[] nums) {

		int max = nums[0];
		for (int i = 0; i < nums.length; i++) {
			int loopmax = 0;
			int sum = 0;
			for (int j = i; j < nums.length; j++) {

				sum += nums[j];
				if (sum >= loopmax)
					loopmax = sum;
			}
			if (loopmax > max)
				max = loopmax;
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 5, 4, -1, 7, 8 };
//        for (int a1 = 0; a1 < a.length; a1++) {
//            a[a1] = (int) (Math.random() * (-100) + 50);
//        }
		int i = maxSubArray(a);
		System.out.println(i);
	}
}
