package datastuct;

/*给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class solution6 {
	public int maxProfit(int[] prices) {
		int maxprofit = 0;
		for (int i = 0; i < prices.length - 1; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				int profit = prices[j] - prices[i];
				if (profit > maxprofit) {
					maxprofit = profit;
				}
			}
		}
		return maxprofit;
	}
}

/*
 * 1）状态定义：我们定义max_profit为第i天的最大收益 2）状态转移方程： 第i天的最大收益和第i-1天的最大收益之间的关系： i)
 * 最大收益不是第i天抛出的， ---最大收益和第i天的价格无关 ii）最大收益是在第i-1天前某天买入的，并在第i天抛出的， ---与第i天的价格有关
 * 
 * 因此第i天的最大收益等于：第i天抛出所造成的最大收益 和 第i-1天之前的最大收益 中的最大值 即： 前i天的最大收益 =
 * max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格} 其中： 前i-1天中的最小价格需时时更新并记录
 * 
 * min 和 max_profit min可以等于第一天的price max_profit可以等于0， 因为最大收益的最小值就是0，
 * 用人话叫，最低也不能赔了
 * 
 * if(prices.length <= 1) return 0; int min = prices[0], max = 0; for(int i = 1;
 * i < prices.length; i++) { max = Math.max(max, prices[i] - min); min =
 * Math.min(min, prices[i]); } return max;
 */
