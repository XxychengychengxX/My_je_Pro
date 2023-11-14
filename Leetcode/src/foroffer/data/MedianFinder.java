package foroffer.data;

import java.util.ArrayList;

/*如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例 1：

输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
示例 2：

输入：
["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5vd1j2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

public class MedianFinder {
	public ArrayList<Double> arrayList;

	public MedianFinder() {
		this.arrayList = new ArrayList<>();
	}

	/*
	 * initialize your data structure here.
	 * 
	 * public static void main(String[] args) { int k = 11; for (int i = 0; i < k;
	 * i++) { arrayList.add(Math.random()*(-100)+50); } arrayList.add(1.0);
	 * arrayList.add(2.0); arrayList.sort(Double::compare);
	 * System.out.println(arrayList); if (arrayList.size() % 2 == 0)
	 * System.out.println((arrayList.get(arrayList.size() / 2) +
	 * arrayList.get(arrayList.size() / 2 - 1)) / 2); else
	 * System.out.println(arrayList.get(arrayList.size() / 2 - 1));
	 * arrayList.add(3.0); arrayList.sort(Double::compare);
	 * System.out.println(arrayList); if (arrayList.size() % 2 == 0)
	 * System.out.println((arrayList.get(arrayList.size() / 2) +
	 * arrayList.get(arrayList.size() / 2 - 1)) / 2); else
	 * System.out.println(arrayList.get(arrayList.size() / 2 )); }
	 */
	public void addNum(int num) {
		this.arrayList.add((double) num);
	}

	public double findMedian() {
		this.arrayList.sort(Double::compare);
		if (this.arrayList.size() % 2 == 0)
			return (this.arrayList.get(this.arrayList.size() / 2) + this.arrayList.get(this.arrayList.size() / 2 - 1))
					/ 2;
		else
			return this.arrayList.get(this.arrayList.size() / 2);
	}

	/*
	 * class MedianFinder { Queue<Integer> A, B; public MedianFinder() { A = new
	 * PriorityQueue<>(); // 小顶堆，保存较大的一半 B = new PriorityQueue<>((x, y) -> (y - x));
	 * // 大顶堆，保存较小的一半 } public void addNum(int num) { if(A.size() != B.size()) {
	 * A.add(num); B.add(A.poll()); } else { B.add(num); A.add(B.poll()); } } public
	 * double findMedian() { return A.size() != B.size() ? A.peek() : (A.peek() +
	 * B.peek()) / 2.0; } }
	 * 
	 * 作者：Krahets
	 * 链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/5v0zcc/
	 * 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	 */

}