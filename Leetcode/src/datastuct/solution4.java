package datastuct;

/*给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
import java.util.Arrays;

public class solution4 {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
//        List<int[]> a= Arrays.asList(nums1);
//        List<int[]> b=Arrays.asList(nums2);
//        a.add(nums2);
//
//        Arrays.sort(a);
		if (n == 0)
			System.out.println(Arrays.toString(nums1));
		else {
			for (int i = n; i < m; i++) {
				nums1[i] = nums2[i - n];

			}
			Arrays.sort(nums1);
			System.out.println(Arrays.toString(nums1));

		}
	}
	/*
	 * 方法一没有利用数组 \textit{nums}_1nums
	 * 
	 * 已经被排序的性质。为了利用这一性质，我们可以使用双指针方法。这一方法将两个数组看作队列，每次从两个数组头部取出比较小的数字放到结果中。如下面的动画所示：
	 * 
	 * 
	 * class Solution { public void merge(int[] nums1, int m, int[] nums2, int n) {
	 * int p1 = 0, p2 = 0; int[] sorted = new int[m + n]; int cur; while (p1 < m ||
	 * p2 < n) { if (p1 == m) { cur = nums2[p2++]; } else if (p2 == n) { cur =
	 * nums1[p1++]; } else if (nums1[p1] < nums2[p2]) { cur = nums1[p1++]; } else {
	 * cur = nums2[p2++]; } sorted[p1 + p2 - 1] = cur; } for (int i = 0; i != m + n;
	 * ++i) { nums1[i] = sorted[i]; } } }
	 * 
	 * 作者：LeetCode-Solution
	 * 链接：https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang
	 * -ge-you-xu-shu-zu-by-leetco-rrb0/ 来源：力扣（LeetCode）
	 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	 */
}
