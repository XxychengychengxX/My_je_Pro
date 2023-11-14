package foroffer.data;

import java.util.LinkedList;
import java.util.Stack;

public class cQueue {
    Stack<Integer> stack;
    Stack<Integer> stack1;
    LinkedList<Integer> linkedList = new LinkedList<>();

    public cQueue() {
        this.stack = new Stack<>();
        this.stack1 = new Stack<>();

    }

    public static void main(String[] args) {
/*        cQueue cQueue = new cQueue();
        for (int j=0;j<100;j++)
            cQueue.appendTail(j);
        while (!cQueue.stack1.empty())
        System.out.println(cQueue.deleteHead());*/
        cQueue cQueue = new cQueue();
        cQueue.linkedList.add(1);
        cQueue.linkedList.add(2);
        cQueue.linkedList.add(2);
        System.out.println(cQueue.linkedList.poll() + "   21    " + cQueue.linkedList.peek());
    }

    public void appendTail(int value) {
        this.stack.push(value);
        this.stack1.push(stack.pop());
    }

    public int deleteHead() {
        if (!this.stack1.empty())
            return this.stack1.pop();
        else
            return -1;
    }

    /*请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/e2t5ug/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public class MaxQueue {
        /*为了实现此递减列表，需要使用 双向队列 ，假设队列已经有若干元素：

当执行入队 push_back() 时： 若入队一个比队列某些元素更大的数字 xx ，则为了保持此列表递减，需要将双向队列 尾部所有小于 xx 的元素 弹出。
当执行出队 pop_front() 时： 若出队的元素是最大元素，则 双向队列 需要同时 将首元素出队 ，以保持队列和双向队列的元素一致性。
使用双向队列原因：维护递减列表需要元素队首弹出、队尾插入、队尾弹出操作皆为 O(1)O(1) 时间复杂度。

函数设计：
初始化队列 queue ，双向队列 deque ；

最大值 max_value() ：

当双向队列 deque 为空，则返回 -1−1 ；
否则，返回 deque 首元素；
入队 push_back() ：

将元素 value 入队 queue ；
将双向队列中队尾 所有 小于 value 的元素弹出（以保持 deque 非单调递减），并将元素 value 入队 deque ；
出队 pop_front() ：

若队列 queue 为空，则直接返回 -1−1 ；
否则，将 queue 首元素出队；
若 deque 首元素和 queue 首元素 相等 ，则将 deque 首元素出队（以保持两队列 元素一致 ） ；
设计双向队列为 非单调递减 的原因：若队列 queue 中存在两个 值相同的最大元素 ，此时 queue 和 deque 同时弹出一个最大元素，而 queue 中还有一个此最大元素；即采用单调递减将导致两队列中的元素不一致。

作者：Krahets
链接：https://leetcode.cn/leetbook/read/illustration-of-algorithm/e2tfiv/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
        private LinkedList<Integer> fuzhu =new LinkedList<>();
        private  LinkedList<Integer> linkedList = new LinkedList<>();

        public MaxQueue() {
        }

        public int max_value() {
            return linkedList.isEmpty()?-1:fuzhu.peek();
        }

        public void push_back(int value) {
            linkedList.addLast(value);
            while(!fuzhu.isEmpty() && fuzhu.peekLast() < value)
                fuzhu.pollLast();
            fuzhu.offer(value);
        }
        public int pop_front() {
            if (linkedList.isEmpty())
                return -1;
            else {
                if (linkedList.peek().equals(fuzhu.peek()))
                    fuzhu.pop();
                return linkedList.pop();
            }
        }
    }



}
