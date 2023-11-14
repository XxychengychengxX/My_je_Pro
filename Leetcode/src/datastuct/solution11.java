package datastuct;

import java.util.Stack;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
//有效字符串需满足：
//
//左括号必须用相同类型的右括号闭合。
//左括号必须以正确的顺序闭合。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/valid-parentheses
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class solution11 {
	public static void main(String[] args) {
		String string = "(){}[{]}";

		System.out.println(isValid(string));
	}

	public static boolean isValid(String s) {
		char[] a = s.toCharArray();
		Stack<Character> characters = new Stack<>();
		try {
			for (char c : a) {
				if (c == '(' || c == '{' || c == '[')
					characters.push(c);
				else if (c == ')') {
					if (characters.pop() != '(') {
						return false;
					}
				} else if (c == '}') {
					if (characters.pop() != '{') {
						return false;
					}
				} else if (c == ']') {
					if (characters.pop() != '[') {
						return false;
					}
				}

			}
		} catch (Exception e) {
			return false;
		}
		return characters.isEmpty();
	}
}
