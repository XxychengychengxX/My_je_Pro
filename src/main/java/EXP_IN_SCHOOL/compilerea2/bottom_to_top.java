package EXP_IN_SCHOOL.compilerea2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class bottom_to_top {

    //action映射表
    private static final HashMap<Character, Integer> actionmapping = new HashMap<>();
    //Goto映射表
    private static final HashMap<Character, Integer> gotomapping = new HashMap<>();

    //action表
    private static final String[][] action = new String[][]{
            {"s4", "null", "null", "null", "null", "null", "s5", "null"},
            {"null", "null", "s6", "s7", "null", "null", "null", "acc"},
            {"null", "r3", "r3", "r3", "s8", "s9", "null", "r3"},
            {"null", "r6", "r6", "r6", "r6", "r6", "null", "r6"},
            {"s4", "null", "null", "null", "null", "null", "s5", "null"},
            {"null", "r8", "r8", "r8", "r8", "r8", "null", "r8"},
            {"s4", "null", "null", "null", "null", "null", "s5", "null"},
            {"s4", "null", "null", "null", "null", "null", "s5", "null"},
            {"s4", "null", "null", "null", "null", "null", "s5", "null"},
            {"s4", "null", "null", "null", "null", "null", "s5", "null"},
            {"null", "s15", "s6", "s7", "null", "null", "null", "null"},
            {"null", "r1", "r1", "r1", "s8", "s9", "null", "r1"},
            {"null", "r2", "r2", "r2", "s8", "s9", "null", "r2"},
            {"null", "r4", "r4", "r4", "r4", "r4", "null", "r4"},
            {"null", "r5", "r5", "r5", "r5", "r5", "null", "r5"},
            {"null", "r7", "r7", "r7", "r7", "r7", "null", "r7"}};

    //goto表
    private static final int[][] gt = new int[][]{
            {1, 2, 3},
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1},
            {10, 2, 3},
            {-1, -1, -1},
            {-1, 11, 3},
            {-1, 12, 3},
            {-1, -1, 13},
            {-1, -1, 14},
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}};

    //文法符号
    private static final String[] grammar = new String[]{
            "e->E", "E->E+T", "E->E-T", "E->T", "T->T*F", "T->T/F", "T->F", "F->(E)", "F->i"
    };

    //分析栈，保存当前符号串
    private static Stack<Character> characterStack = new Stack<>();

    //状态栈
    private static Stack<Integer> stateStack = new Stack<>();



    //进行编号映射，能从gt数组和action数组中找到当前要执行的动作
    private static void initmapping() {
        actionmapping.put('(', 0);
        actionmapping.put(')', 1);
        actionmapping.put('+', 2);
        actionmapping.put('-', 3);
        actionmapping.put('*', 4);
        actionmapping.put('/', 5);
        actionmapping.put('i', 6);
        actionmapping.put('#', 7);
        gotomapping.put('E', 0);
        gotomapping.put('T', 1);
        gotomapping.put('F', 2);
    }

    public static void main(String[] args) {
        //状态栈和符号栈的初始化
        //characterStack.push('-');
        stateStack.push(0);
        //映射的初始化
        initmapping();
        System.out.println("please input the algorithm:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine() + "#";
        /*String s = "(4+9)-2+(9/3)#";*/
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= '9' && chars[i] >= '0')
                chars[i] = 'i';
        }
        int index = 0;
        printstatus(chars, index);
        analyze(chars, index);
    }


    /**
     * 进行SLR(1)分析
     * @param chars 输入串
     * @param index 当前输入串指针
     */
    private static void analyze(char[] chars, int index) {
        while (true) {
            //现在面临的输入符号
            char nowchar = chars[index];
            //现在面临的状态栈栈顶
            int peek = stateStack.peek();
            //查找action子表,得到当前的动作action
            String action = bottom_to_top.action[peek][actionmapping.get(nowchar)];
            //如果是shift（移进）
            if (action.startsWith("s")) {
                characterStack.push(nowchar);
                stateStack.push(Integer.valueOf(action.substring(1)) );
                index++;
            }
            //如果是规约项目
            else if (action.startsWith("r")) {
                //查找用第几个产生式规约
                int i = action.charAt(1) - '0';
                //当前使用的产生式
                String nowgram = grammar[i];
                //分离出左部和右部
                String[] split = nowgram.split("->");
                String left = split[0];
                String right = split[1];
                //当前符号栈顶弹出
                for (int j = 0; j < right.length(); j++) {
                    characterStack.pop();
                    stateStack.pop();
                }
                //当前状态栈顶
                int nowstatu = stateStack.peek();
                characterStack.push(left.charAt(0));
                int putin = gt[nowstatu][gotomapping.get(characterStack.peek())];
                stateStack.push(putin);
            } else if (action.equals("acc")) {
                System.out.println("分析成功");
                return;
            }else {
                System.out.println("分析失败，语句有误");
            }
            printstatus(chars, index);
        }

    }

    /**
     * 打印当前分析状态
     * @param chars 输入的字符串转化成的字符数组
     * @param index 当前数组下标
     */
    private static void printstatus(char[] chars, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : characterStack) {
            stringBuilder.append(character);
        }

        StringBuilder stringBuilder3 = new StringBuilder();
        for (int i = index; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
            stringBuilder3.append(chars[i]);
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        for (Integer integer : stateStack) {
            stringBuilder2.append(integer);
        }
        System.out.println("  -> " + stringBuilder);
        System.out.println("当前状态栈是 ：" + stringBuilder2);
        System.out.println("当前输入串是 : " + stringBuilder3 );
        System.out.println("即将采取的动作是 ：" + action[stateStack.peek()][actionmapping.get(chars[index])] + "\n");
    }

}


