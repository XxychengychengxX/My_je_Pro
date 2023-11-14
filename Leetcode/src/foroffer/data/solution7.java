package foroffer.data;

import java.util.HashMap;

//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
public class solution7 {
	public char firstUniqChar(String s) {
		if (s.length() == 0) {
			return ' ';
		}
//        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
		HashMap<Character, Boolean> characterBooleanHashMap = new HashMap<>();
		char[] chars = s.toCharArray();
		for (char c : chars) {
			characterBooleanHashMap.put(c, !characterBooleanHashMap.containsKey(c));
		}

		for (char c : chars) {
			if (characterBooleanHashMap.get(c)) {
				return c;
			}
		}

		return ' ';
	}

}
