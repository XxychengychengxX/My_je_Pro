package datastuct;
/*给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

import java.util.ArrayList;
import java.util.Arrays;

public class solution5 {
	public static void main(String[] args) {
		int[] a = { 1, 2, 2, 1 };
		int[] b = { 2, 2 };
//        for (int a1 = 0; a1 < a.length; a1++) {
//            a[a1] = (int) (Math.random() * (-100) + 50);
//            b[a1] = (int) (Math.random() * (-100) + 50);
//        }
		intersect(a, b);
	}

	public static void intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		ArrayList<Integer> ints = new ArrayList<>();
		int i = 0, j = 0;
		while (i < nums1.length && j < nums2.length) {

			if (nums1[i] > nums2[j]) {
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				ints.add(nums1[i]);
				i++;
				j++;
			}
		}

		System.out.println(Arrays.toString(ints.stream().mapToInt(Integer::valueOf).toArray()));
	}

	/*
	 * class Solution { public int[] intersect(int[] nums1, int[] nums2) { if
	 * (nums1.length > nums2.length) { return intersect(nums2, nums1); }
	 * Map<Integer, Integer> map = new HashMap<Integer, Integer>(); for (int num :
	 * nums1) { int count = map.getOrDefault(num, 0) + 1; map.put(num, count); }
	 * int[] intersection = new int[nums1.length]; int index = 0; for (int num :
	 * nums2) { int count = map.getOrDefault(num, 0); if (count > 0) {
	 * intersection[index++] = num; count--; if (count > 0) { map.put(num, count); }
	 * else { map.remove(num); } } } return Arrays.copyOfRange(intersection, 0,
	 * index); } }
	 * 
	 * 作者：LeetCode-Solution
	 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/
	 * liang-ge-shu-zu-de-jiao-ji-ii-by-leetcode-solution/ 来源：力扣（LeetCode）
	 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	 */
}
