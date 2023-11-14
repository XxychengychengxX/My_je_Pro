package inorder;

import java.util.*;


public class Solution {


    public Solution() {
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best",
                "good"}));
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


    //2.中等
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode work = new ListNode();
        ListNode ans1 = work;
        while (l1 != null || l2 != null) {
            work.next = new ListNode();
            if (l1 == null) {
                work.next.val = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                l2 = l2.next;
                work = work.next;
            } else if (l2 == null) {
                work.next.val = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                l1 = l1.next;
                work = work.next;
            } else {
                work.next.val = (l1.val + l2.val + carry) % 10;
                carry = (l1.val + l2.val + carry) / 10;
                work = work.next;
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        if (carry == 1)
            work.next = new ListNode(1);
        return ans1.next;
    }

    //3.中等（hashtable的迭代）
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        char[] chars = s.toCharArray();
        int max = 1;
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();//显然用hashtable来做
        for (int i = 0; i < chars.length; i++) {//
            if (!characterIntegerHashMap.containsKey(chars[i]))
                characterIntegerHashMap.put(chars[i], i);
            else {
                int k = characterIntegerHashMap.get(chars[i]);
                if (max < characterIntegerHashMap.size())
                    max = characterIntegerHashMap.size();
                Iterator<Map.Entry<Character, Integer>> iterator = characterIntegerHashMap.entrySet().iterator();
                //用迭代器对象来删除集合元素
                while (iterator.hasNext()) {
                    Map.Entry<Character, Integer> map = iterator.next();
                    if (map.getValue() < k)
                        iterator.remove();
                }
                //方法二：characterIntegerHashMap.entrySet().removeIf(map -> map.getValue() < k);
                int finalI = i;
                characterIntegerHashMap.compute(chars[i], (a, b) -> b = finalI);
            }
        }
        return Math.max(max, characterIntegerHashMap.size());
    }

    //4.困难*****
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0;
    }

    //5.中等
    public String longestPalindrome(String s) {
        /*
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        String ans = "";
        if (s.length() == 1)
            return s;
        char[] chars = s.toCharArray();//转成字符数组
        ans = String.valueOf(chars[0]);
        HashMap<Character, ArrayList<Integer>> characterArrayListHashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (!characterArrayListHashMap.containsKey(chars[i])) {//判断字符是否存在
                int finalI = i;
                characterArrayListHashMap.put(chars[i], new ArrayList<>() {{
                    add(finalI);//不存在则添加
                }});
            } else {
                //存在则依次判断与之前的arraylist里面的值组成的字串是不是回文字符串
                ArrayList<Integer> arrayList = characterArrayListHashMap.get(chars[i]);
                for (int k : arrayList) {
                    String substring = s.substring(k, i + 1);
                    //如果是并且该回文串的长度比我们之前的ans长
                    if (isplindromestring(substring) && substring.length() > ans.length())
                        ans = substring;
                }
                arrayList.add(i);
            }
        }
        return ans;
    }

    public boolean isplindromestring(String s) {
        StringBuilder s1 = new StringBuilder(s);
        StringBuilder reverse = s1.reverse();
        return s.equals(new String(reverse));
    }//判断是否是回文数的工具方法


    //6.中等
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.equals(""))
            return s;
        char[] chars = s.toCharArray();
        int column = s.length() / (2 * numRows - 2);//商
        int column_rest = s.length() % (2 * numRows - 2);//余数
        int a = column_rest - numRows;
        char[][] ans;
        if (a > 0)
            ans = new char[numRows][column * (1 + numRows - 2) + 1 + a];
        else
            ans = new char[numRows][column * (1 + numRows - 2) + 1];
        int count = 0;
        int i = 0, j = 0;
        while (count < chars.length) {
            while (i < numRows && count < chars.length) {
                ans[i++][j] = chars[count++];
            }
            i -= 2;
            j++;
            while (i > 0 && count < chars.length) {
                ans[i--][j++] = chars[count++];
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] an : ans) {
            for (int k = 0; k < ans[0].length; k++) {
                if (an[k] != '\u0000')
                    stringBuilder.append(an[k]);
            }
        }
        return new String(stringBuilder);
    }


    //7.中等
    public int reverse(int x) {
        StringBuilder s = new StringBuilder(String.valueOf(x));
        if (x < 0) {
            s.deleteCharAt(0);
            s.append('-');
            s.reverse();
        } else {
            s.reverse();
        }
        try {
            return Integer.parseInt(new String(s));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    //8.中等
    public int myAtoi(String s) {
        s = s.strip();
        char[] chars = s.toCharArray();//数组
        int ans = 0;//返回答案
        boolean check = false;//检查是否溢出
        boolean flag = true;
        int i;
        //循环找到所谓的+ - 号或者是数字
        for (i = 0; i < chars.length; i++) {
            if (chars[i] != '+' && chars[i] != '-' && (chars[i] < '0' || chars[i] > '9')) {
            }
            //第一个找到的是负号
            else if (chars[i] == '-') {
                flag = false;
                i++;
                break;
            } else if (chars[i] == '+') {
                i++;
                break;
            } else break;//找到了却不是负号
        }
        //开始转化
        while (i < chars.length) {
            if (chars[i] < '0' || chars[i] > '9')
                break;
            else if (ans * 10 + chars[i] - '0' < ans) {
                check = true;
                break;
            } else ans = ans * 10 + chars[i] - '0';
            i++;
        }
        //如果发生溢出
        if (check) {
            if (flag)
                return Integer.MAX_VALUE;
            else
                return Integer.MIN_VALUE;
        }
        //这里是每溢出
        else {
            if (flag) return ans;
            else return -ans;
        }
    }

    //9.中等
    public boolean isPalindrome(int x) {
        StringBuilder s = new StringBuilder(String.valueOf(x));
        return isplindromestring(new String(s));
    }


    //10.困难
    public boolean isMatch(String s, String p) {
        return true;
    }

    //11.中等（双指针）
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int ans = 0;//答案
        int temp = 0;//当前大小
        while (i != j) {
            temp = (j - i) * Math.min(height[i], height[j]);
            if (temp > ans)
                ans = temp;
            if (height[i] > height[j])
                j--;
            else i++;
        }
        return ans;
    }


    //12.中等
    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder("");
       /* int values[]={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        string reps[]={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        string res;
        for(int i=0; i<13; i++){
            while(num>=values[i]){
                num -= values[i];
                res += reps[i];
            }
        }
        return res;*/
        int thousand_rest = num / 1000;
        switch (thousand_rest) {
            case 1 -> ans.append("M");
            case 3 -> ans.append("MMM");
            case 2 -> ans.append("MM");
        }
        int hundred_rest = num / 100 - thousand_rest * 10;
        switch (hundred_rest) {
            case 1:
                ans.append("C");
                break;
            case 2:
                ans.append("CC");
                break;
            case 3:
                ans.append("CCC");
                break;
            case 4:
                ans.append("CD");
                break;
            case 5:
                ans.append("D");
                break;
            case 6:
                ans.append("DC");
                break;
            case 7:
                ans.append("DCC");
                break;
            case 8:
                ans.append("DCCC");
                break;
            case 9:
                ans.append("CM");
                break;
            case 0:

        }
        int ten_rest = num % 100 / 10;
        switch (ten_rest) {
            case 1:
                ans.append("X");
                break;
            case 2:
                ans.append("XX");
                break;
            case 3:
                ans.append("XXX");
                break;
            case 4:
                ans.append("XL");
                break;
            case 5:
                ans.append("L");
                break;
            case 6:
                ans.append("LX");
                break;
            case 7:
                ans.append("LXX");
                break;
            case 8:
                ans.append("LXXX");
                break;
            case 9:
                ans.append("XC");
                break;
            case 0:

        }
        int one_rest = num % 10;
        switch (one_rest) {
            case 1:
                ans.append("I");
                break;
            case 2:
                ans.append("II");
                break;
            case 3:
                ans.append("III");
                break;
            case 4:
                ans.append("IV");
                break;
            case 5:
                ans.append("V");
                break;
            case 6:
                ans.append("VI");
                break;
            case 7:
                ans.append("VII");
                break;
            case 8:
                ans.append("VIII");
                break;
            case 9:
                ans.append("IX");
                break;
            case 0:

        }

        return new String(ans);
    }

    //13.简单
    public int romanToInt(String s) {

        char[] chars = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'M')
                ans += 1000;
            else if (chars[i] == 'D')
                ans += 500;
            else if (chars[i] == 'L')
                ans += 50;
            else if (chars[i] == 'V')
                ans += 5;
            else if (chars[i] == 'C') {
                if (i + 1 < chars.length) {
                    if (chars[i + 1] == 'M') {
                        ans += 900;
                        i++;
                    } else if (chars[i + 1] == 'D') {
                        ans += 400;
                        i++;
                    } else ans += 100;
                } else ans += 100;
            } else if (chars[i] == 'X') {
                if (i + 1 < chars.length) {
                    if (chars[i + 1] == 'C') {
                        ans += 90;
                        i++;
                    } else if (chars[i + 1] == 'L') {
                        ans += 40;
                        i++;
                    } else ans += 10;
                } else ans += 10;
            } else if (chars[i] == 'I') {
                if (i + 1 < chars.length) {
                    if (chars[i + 1] == 'X') {
                        ans += 9;
                        i++;
                    } else if (chars[i + 1] == 'V') {
                        ans += 4;
                        i++;
                    } else ans += 1;
                } else ans += 1;
            }
        }
        return ans;
    }

    //14.简单
    public String longestCommonPrefix(String[] strs) {
        String ans = "";
        if (strs.length == 1)
            return strs[0];
        String s1 = strs[0];
        String s2 = strs[1];
        //判断开始的公共前缀是否是因为达到末尾或者遇到了不相等的字符，f为达到末尾，t为遇到不相等字符
        boolean flag = false;
        int i = 0, j = 0;
        for (; i <= s1.length() && j <= s2.length(); i++, j++) {
            if (!s1.substring(0, i).equals(s2.substring(0, j))) {
                //这里表示字符串中的第i个字符之前的由0~i-1个字符组成的字串不相等，
                // 即第i-1个字符不相等
                // 因此正确的共同前缀为0~i-2个字符组成的字串
                flag = true;

                break;
            }
        }
        //无论是到末尾还是遇到不相等字符串，都可以
        ans = s1.substring(0, --i);
        if (ans.equals(""))
            return "";
        //同上，依次判定即可
        for (String str : strs) {
            if (str.equals(""))
                return "";
            if (i <= str.length()) {
                if (!ans.equals(str.substring(0, i))) {
                    i = 0;
                    j = 0;
                    for (; i <= ans.length() && j <= str.length(); i++, j++) {
                        if (!ans.substring(0, i).equals(str.substring(0, j))) {
                            ans = ans.substring(0, --i);
                            break;
                        }
                    }
                }
            } else {
                ans = ans.substring(0, str.length());
                i = 0;
                j = 0;
                for (; i <= ans.length() && j <= str.length(); i++, j++) {
                    if (!ans.substring(0, i).equals(str.substring(0, j))) {
                        ans = ans.substring(0, --i);
                        break;
                    }
                }
            }
        }
        return ans;
    }


    //15.中等*******
    public List<List<Integer>> threeSum(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;

      /*  作者：LeetCode-Solution
        链接：https://leetcode.cn/problems/3sum/solution/san-shu-zhi-he-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    }


    //16.中等（双指针）
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int ans = Integer.MAX_VALUE;
        for (; i < nums.length - 2; i++) {
            int j = nums.length - 1, k = i + 1;
            while (j > k) {
                if (Math.abs(target - nums[i] - nums[j] - nums[k]) < Math.abs(target - ans))
                    ans = nums[i] + nums[j] + nums[k];
                if (ans == target)
                    return ans;
                if (nums[j] + nums[k] > target - nums[i])
                    j--;
                else k++;
            }
        }
        return ans;
    }

    //17.中等（数学广角：乘法法则）
    public List<String> letterCombinations(String digits) {
        //电话表
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        //答案
        List<String> strings = new ArrayList<>();

        char[] chars = digits.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            //找到当前数字对应的字母串并转成数组
            String s = phoneMap.get(chars[i]);
            char[] chars1 = s.toCharArray();
            //刚开始的列表为空
            if (strings.isEmpty()) {
                for (char c : chars1) {
                    strings.add(String.valueOf(c));
                }
            } else {
                //令k为当前列表的长度，开始乘法步骤
                int k = strings.size();
                //对当前的每个字符，都需要加到List中每个元素后面去
                for (char c : chars1) {
                    for (int j = 0; j < k; j++) {
                        strings.add(strings.get(j) + c);
                    }
                }
                //删除未拓展时一开始的那几个列表元素

                for (int j = 0; j < k; j++) {
                    strings.remove(0);
                }

            }

        }
        return strings;
    }


    //18中等
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for (; i < nums.length - 2; i++) {
            int j = nums.length - 1, k = i + 1;


        }
        return lists;
    }

    //19。中等
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode work = head.next;
        //总结点
        int count = 1;
        while (work != null) {
            work = work.next;
            count++;
        }
        //正数第num个节点(根节点算为0)
        int num = count - n;
        //删除第一个节点
        if (num == 0)
            return head.next;
        else if (num == 1) {
            head.next = head.next.next;
            return head;
        }
        work = head.next;
        while (num > 2) {
            if (work != null)
                work = work.next;
            num--;
        }
        if (work != null && work.next != null)
            work.next = work.next.next;
        return head;
    }


    //21.简单(链表合并的递归和迭代）
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //迭代法；
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = list1 == null ? list2 : list1;
        return prehead.next;
      /*
      递归法
      if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    }


    //22.中等(回溯与递归与暴力)
    public List<String> generateParenthesis(int n) {
        //暴力法，原则上行不通
       /*
        ArrayList<String> strings = new ArrayList<>();
        strings.add("()");
        StringBuilder s;
        for (int i = 1; i < n; i++) {
            int j = strings.size();
            for (int k = 0; k < j; k++) {
                s = new StringBuilder(strings.get(0));
                for (int m = 0; m < s.length(); m++) {
                    s.insert(m, "()");
                    String s1 = new String(s);
                    if (!strings.contains(s1))
                        strings.add(s1);
                    s = new StringBuilder(strings.get(0));
                }
                strings.remove(0);
            }
        }
        return strings;*/
        //递归，理论上可行：
        List<String> strings = new ArrayList<>();
        getParenthesis(strings, "", n, n);
        return strings;
    }

    public void getParenthesis(List<String> strings, String s, int left, int right) {
        if (left == 0 && right == 0) {
            strings.add(s);
            return;
        }
        if (left == right) {
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(strings, s + "(", left - 1, right);
        } else if (left < right) {
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if (left > 0) {
                getParenthesis(strings, s + "(", left - 1, right);
            }
            getParenthesis(strings, s + ")", left, right - 1);

        }
    }

    //23.困难(分治算法与复制解法）
    /*public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode();

        head.next = new ListNode();
        ListNode work = head;
        //判定总共有多少个节点
        int length = 0;
        for (ListNode list : lists) {

            while (list != null) {
                length++;
                list = list.next;
            }

        }
        //如果没有则直接返回null即可
        if (length == 0)
            return null;
        //准备好数组
        int[] ints = new int[length];
        int i = 0;
        //依次赋值
        for (ListNode list : lists) {
            while (list != null) {
                ints[i++] = list.val;
                list = list.next;
            }
        }
        //排序
        Arrays.sort(ints);
        //直接创建即可
        for (int anInt : ints) {
            work.next = new ListNode(anInt);
            work = work.next;
        }

        return head.next;
    }


    //24.中等
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }


    //25.困难
    public ListNode reverseKGroup(ListNode head, int k) {
        return null;
    }

    //26.简单
    public int removeDuplicates(int[] nums) {
        int newlength = 0;//不重复的元素数目
        int repeat = 0;//重复的元素个数，数组长度减去重复的元素个数即为newlength
        for (int i = 0; i + repeat < nums.length; i++) {
            newlength++;//元素个数加一
            if ((i + 1) < nums.length && nums[i + 1] == nums[i]) {
                int flag = 0;
                int temp = nums.length - repeat;//本轮应该查的元素数量
                while ((i + 1 + flag) < temp && nums[i + 1 + flag] == nums[i]) {
                    flag++;
                    repeat++;
                }
                int j = i + 1;
                while (j + flag < nums.length) {
                    nums[j] = nums[j + flag];
                    j++;
                }
            }
        }
        return newlength;
    }

    //27.简单
    public int removeElement(int[] nums, int val) {
        int ans = nums.length;
        for (int i = 0; i < ans; i++) {
            if (nums[i] == val) {
                System.arraycopy(nums, i + 1, nums, i, ans - 1 - i);
                ans--;
                //复制之后应该再查本位置，即小小回溯一下
                i--;
            }
        }
        return ans;
    }

    //28.简单
    public int strStr(String haystack, String needle) {
        //字串为空则返回0（与c++相同）
        if (needle.equals(""))
            return 0;
        //查找子串第一次出现的位置，若没有则返回-1；
        return haystack.indexOf(needle);
    }


    //29.中等*****
    public int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return rev ? -ans : ans;
    }

    // 快速乘
    public boolean quickAdd(int y, int z, int x) {
        // x 和 y 是负数，z 是正数
        // 需要判断 z * y >= x 是否成立
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 需要保证 result + add >= x
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 需要保证 add + add >= x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }

    //30.困难**(自己写的已超时)
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> integers = new ArrayList<>();
        int substringlength = words[0].length();
        boolean[] selected = new boolean[words.length];
        int k = s.length();
        //对主串的检测
        for (int i = 0; i < k; i++) {
            //每次检测都要将选择标志重新置为未选择

            //每次迭代都对子串检测
            for (int j = 0; j < words.length; j++) {
                Arrays.fill(selected, false);
                if (!selected[j]) {
                    if (s.startsWith(words[j])) {
                        selected[j] = true;
                        if (findmatch(s.substring(substringlength), words, selected, substringlength)) {
                            if (!integers.contains(i))
                                integers.add(i);
                        }
                    }
                }
            }
            s = s.substring(1);
        }
        return integers;
    }

    public boolean findmatch(String s, String[] words, boolean[] selected, int substringlength) {

        for (int i = 0; i < selected.length; i++) {
            if (s.equals(""))
                break;
            if (!selected[i]) {
                if (s.startsWith(words[i])) {
                    selected[i] = true;
                    return findmatch(s.substring(substringlength), words, selected, substringlength);
                }
            }
        }
        //对每个元素是否选过进行判定
        int i = 0;
        while (i < selected.length && selected[i])
            i++;
        return i == selected.length;
    }


    //30.
    //链表1
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

