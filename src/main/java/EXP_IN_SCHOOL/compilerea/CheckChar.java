package EXP_IN_SCHOOL.compilerea;

import java.util.Arrays;
import java.util.HashMap;

public class CheckChar {
    //定义的表
    public static HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
    //最终答案
    public static HashMap<String, String> ans = new HashMap<>();

    //存放
    static {
        stringIntegerHashMap.put("whlie", 1);
        stringIntegerHashMap.put("for", 2);
        stringIntegerHashMap.put("if", 3);
        stringIntegerHashMap.put("do", 4);
        stringIntegerHashMap.put("switch", 5);
        stringIntegerHashMap.put("case", 6);
        stringIntegerHashMap.put("return", 7);
        stringIntegerHashMap.put("+", 8);
        stringIntegerHashMap.put("-", 9);
        stringIntegerHashMap.put("*", 10);
        stringIntegerHashMap.put("/", 11);
        stringIntegerHashMap.put("%", 12);
        stringIntegerHashMap.put(">", 13);
        stringIntegerHashMap.put("<", 14);
        stringIntegerHashMap.put(">=", 15);
        stringIntegerHashMap.put("<=", 16);
        stringIntegerHashMap.put("!=", 17);
        stringIntegerHashMap.put("(", 18);
        stringIntegerHashMap.put(")", 19);
        stringIntegerHashMap.put("{", 20);
        stringIntegerHashMap.put("}", 21);
        stringIntegerHashMap.put(";", 22);
    }

    public CheckChar() {
    }

    public static HashMap<String, Integer> getStringIntegerHashMap() {
        return stringIntegerHashMap;
    }

    public static void setStringIntegerHashMap(HashMap<String, Integer> stringIntegerHashMap) {
        CheckChar.stringIntegerHashMap = stringIntegerHashMap;
    }

    public static HashMap<String, String> getAns() {
        return ans;
    }

    public static void setAns(HashMap<String, String> ans) {
        CheckChar.ans = ans;
    }

    public static void isKeyword(String s) {
        switch (s) {
            case "whlie":
                ans.put("1", null);
                break;
            case "for":
                ans.put("2", null);
                break;
            case "if":
                ans.put("3", null);
                break;
            case "do":
                ans.put("4", null);
                break;
            case "switch":
                ans.put("5", null);
                break;
            case "case":
                ans.put("6", null);
                break;
            case "return":
                ans.put("7", null);
                break;
        }
    }

    public static void isCorporator(String s) {
        switch (s) {
            case "(" -> ans.put("20", null);
            case ")" -> ans.put("21", null);
            case "{" -> ans.put("22", null);
            case "}" -> ans.put("23", null);
            case ";" -> ans.put("24", null);

        }
    }

    public static void isSeperator(String s) {
        switch (s) {
            case "+" -> ans.put("8", null);
            case "-" -> ans.put("9", null);
            case "*" -> ans.put("10", null);
            case "/" -> ans.put("11", null);
            case "%" -> ans.put("12", null);
            case ">" -> ans.put("13", null);
            case "<" -> ans.put("14", null);
            case ">=" -> ans.put("15", null);
            case "<=" -> ans.put("16", null);
            case "!=" -> ans.put("17", null);
            case "==" -> ans.put("18", null);
            case "=" -> ans.put("19", null);
        }
    }

    public static HashMap<String, String> status_pre(String s0) {
        Status status = Status.Init;
        char[] chars = s0.toCharArray();
        return status_change(chars, status);
    }


    public static void charahander(String s) {
        String[] s1 = s.strip().split(" ");
        for (int i = 0; i < s1.length; i++) {
            /* CheckChar.isKeyword(s1[i]);
            CheckChar.isCorporator(s1[i]);
            CheckChar.isSeperator(s1[i]);*/
            //短语
            String s2 = s1[i];
            CheckChar.status_pre(s2);

        }
    }

    public static HashMap<String, String> status_change(char[] chars, Status status) {
        int numstart = 0;
        int wordstart = 0;
        char corporate;
        for (int i = 0; i < chars.length; i++) {
            switch (status) {

                case Init:
                    if (chars[i] <= '9' && chars[i] >= '0') {
                        numstart = i;
                        status = Status.Num_1;
                    } else if ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z')) {
                        wordstart = i;
                        status = Status.Word_1;
                    } else if (chars[i] == '(' || chars[i] == ')' || chars[i] == '{' ||
                            chars[i] == '}' || chars[i] == ';') {
                        /*ans.put(String.valueOf(stringIntegerHashMap.get(String.valueOf
                        (chars[i]))), null);*/
                        /*System.out.println(stringIntegerHashMap.get(String.valueOf(chars[i])) +
                                "分隔符");*/
                        System.out.println(chars[i] + "  分隔符");
                    } else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' ||
                            chars[i] == '/' || chars[i] == '%') {
                        /*ans.put(String.valueOf(stringIntegerHashMap.get(String.valueOf
                        (chars[i]))), null);*/
                        System.out.println(chars[i] + "  运算符");
                    } else if (chars[i] == '>' || chars[i] == '<' || chars[i] == '=' ||
                            chars[i] == '!') {
                        status = Status.Corporator_1;
                    }
                    break;

                case Num_1:
                    //如果还是数字
                    if (chars[i] <= '9' && chars[i] >= '0') {
                        continue;
                    }
                    //否则计入
                    else {
                        /*StringBuilder stringBuilder = new StringBuilder();
                        int j = i;
                        //查找到上一个不是数字的地点
                        while (chars[j] <= '9' && chars[j] >= '0' && j > 0) {
                            stringBuilder.append(chars[j]);
                            j--;
                        }*/
                        ans.put("Nums", String.valueOf(chars).substring(numstart, i));
                        i--;
                        status = Status.Init;
                    }
                    break;

                case Word_1:
                    if ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z') ||
                            (chars[i] <= '9' && chars[i] >= '0')) {
                        continue;
                    } else {
                        //找到该单词
                        String duanyu = String.valueOf(chars).substring(wordstart, i);
                        i--;
                        status = Status.Init;
                        //如果是关键字
                        if (stringIntegerHashMap.containsKey(duanyu)) {
                            System.out.println(duanyu + "  关键字");
                        } else
                            System.out.println(duanyu + "  单词");
                    }
                    break;
                case Corporator_1:
                    corporate=chars[i];
                    if (chars[i] == '=') {
                        System.out.println(chars[i] + chars[i - 1]+ "  运算符");
                    } else {
                        i--;
                        System.out.println(chars[i] + "  运算符");
                        status = Status.Init;
                    }
                    break;

            }
        }
        switch (status) {
            case Num_1:
                ans.put("Nums", Arrays.toString(chars).substring(numstart, chars.length));
                break;
            case Word_1:
                String string = String.valueOf(chars);
                String duanyu = string.substring(wordstart, chars.length);
                if (stringIntegerHashMap.containsKey(duanyu)) {
                    System.out.println(duanyu + "关键字");
                } else
                    System.out.println(duanyu + "单词");
                break;
            case Corporator_1:

        }

        return ans;
    }

    public enum Characters {
        Keyword,
        Identity,
        Nums,
        Corporator,
        Seperator
    }

    public enum Status {
        Init,
        Word_1,
        Word_End,
        Corporator_1,
        Corporator_End,
        Seperator_End,
        Num_1,
        Num_End,
        Error,
        End
    }
}
