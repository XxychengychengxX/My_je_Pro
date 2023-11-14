package EXP_IN_SCHOOL.compilerea2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Top_to_bottom {
    //分析栈，保存当前符号串
    private static Stack<Character> stack = new Stack<>();
    //hashmap，保存文法的生成式
    private static HashMap<Character, String[]> hashMap = new HashMap<>();
    //已经识别的串
    private static StringBuilder alreadycheck = new StringBuilder();


    //初始化hashmap，保存文法生成式，其中hashmap的KEY为文法产生式的左边符号，VALUE为产生式右部符号
    public static void Inithashmap() {
        hashMap.put('E', new String[]{"TM"});
        hashMap.put('M', new String[]{"+TM", "-TM", "$"});
        hashMap.put('T', new String[]{"FN"});
        hashMap.put('N', new String[]{"*FN", "/FN", "$"});
        hashMap.put('F', new String[]{"(E)", "1", "2", "3", "4", "5", "6", "7", "8", "9"
                , "0"});
    }

    //打印当前的识别状态
    private static void printstatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character next : stack) {
            stringBuilder.append(next);
        }
        System.out.println("  -> " + alreadycheck + stringBuilder.reverse());
    }

    //E的产生式
    public static void E(char[] chars, int index) {

        boolean flag=true;
        //判断是不是第一个开始符号
        if (stack.isEmpty()) {
            System.out.println("E -> TM");
            flag=false;
        }
        stack.push('M');
        stack.push('T');
        if (flag)
            printstatus();

    }

    public static void T(char[] chars, int index) {

        stack.push('N');
        stack.push('F');
        printstatus();
    }

    public static void M(char[] chars, int index) {
        /*if (index == chars.length) {
            System.out.println("Analyze successfully!");
            return;
        }*/
        char aChar = chars[index];
        String[] strings = hashMap.get('M');
        for (String string : strings) {
            if (string.startsWith(String.valueOf(aChar))) {
                char[] chars1 = string.toCharArray();
                for (int j = chars1.length - 1; j >= 0; j--) {
                    stack.push(chars1[j]);
                }
                break;
            }
        }
        printstatus();
    }

    public static void N(char[] chars, int index) {
        /*if (index == chars.length) {
            System.out.println("Analyze successfully!");
            return;
        }*/
        char aChar = chars[index];
        //找到N的产生式并且用符合目标的去配对
        String[] strings = hashMap.get('N');
        //依次将该产生式的各个符号压入栈
        for (String string : strings) {
            if (string.startsWith(String.valueOf(aChar))) {
                char[] chars1 = string.toCharArray();
                for (int j = chars1.length - 1; j >= 0; j--) {
                    stack.push(chars1[j]);
                }
                break;
            }
        }
        printstatus();
    }

    public static void F(char[] chars, int index) {
        /*if (index == chars.length) {
            System.out.println("Analyze successfully!");
            return;
        }*/
        char aChar = chars[index];
        String[] strings = hashMap.get('F');
        //探测当前符号
        for (String string : strings) {
            if (string.startsWith(String.valueOf(aChar))) {
                char[] chars1 = string.toCharArray();
                for (int j = chars1.length - 1; j >= 0; j--) {
                    stack.push(chars1[j]);
                }
                break;
            }
        }
        printstatus();
    }

    public static void main(String[] args) {
        stack.push('E');
        Inithashmap();
        System.out.println("please input the alog:");
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.nextLine().toCharArray();
        /*char[] chars = {'1', '+', '1', '*', '1'};*/
        int index = 0;

        while (index < chars.length) {
            //弹出当前栈顶符号
            char pop = stack.pop();
            //如果是终结符
            if (pop == chars[index]) {
                index++;
                alreadycheck.append(pop);
            }
            //如果是非终结符
            else {
                switch (pop) {
                    case 'E' -> E(chars, index);
                    case 'F' -> F(chars, index);
                    case 'N' -> N(chars, index);
                    case 'M' -> M(chars, index);
                    case 'T' -> T(chars, index);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            stringBuilder.append(aChar);
        }
        System.out.println("  -> "+ stringBuilder);
        System.out.println("Analyze Successfully! ");
    }
}
