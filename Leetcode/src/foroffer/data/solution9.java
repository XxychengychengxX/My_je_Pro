package foroffer.data;

public class solution9 {

	// 斐波那契数列非递归
	public int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		int a = 0, b = 1;
		for (int i = 1; i < n; i++) {
			// 跳棋模板
			int sum = (a + b) % 1000000007;
			a = b;
			b = sum;
		}
		return b;
	}

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。 答案需要取模
	 * 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
	 * 设跳上 nn 级台阶有 f(n)f(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 11 级或 22 级台阶。 当为 11 级台阶： 剩
	 * n-1n−1 个台阶，此情况共有 f(n-1)f(n−1) 种跳法； 当为 22 级台阶： 剩 n-2n−2 个台阶，此情况共有 f(n-2)f(n−2)
	 * 种跳法。 即 f(n)f(n) 为以上两种情况之和，即 f(n)=f(n-1)+f(n-2)f(n)=f(n−1)+f(n−2)
	 * ，以上递推性质为斐波那契数列。因此，本题可转化为 求斐波那契数列第 nn 项的值 ，与 剑指Offer 10- I. 斐波那契数列
	 * 等价，唯一的不同在于起始数字不同
	 * 青蛙跳台阶问题： f(0)=1f(0)=1 , f(1)=1f(1)=1 , f(2)=2f(2)=2 ； 斐波那契数列问题： f(0)=0f(0)=0
	 * , f(1)=1f(1)=1 , f(2)=1f(2)=1 。 。
	 */
	public int numWays(int n) {
		if (n == 1 || n == 0)
			return 1;
		else if (n == 2)
			return 2;
		int a = 0, b = 1;
		for (int i = 0; i < n; i++) {
			// 跳棋模板
			int sum = (a + b) % 1000000007;
			a = b;
			b = sum;
		}
		return a;
	}
}
