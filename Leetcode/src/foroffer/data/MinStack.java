package foroffer.data;

import java.util.Objects;
import java.util.Stack;

public class MinStack {
	Stack<Integer> stack;
	Stack<Integer> stack_min;

	public MinStack() {
		this.stack = new Stack<>();
		this.stack_min = new Stack<>();
	}

//    public static void main(String[] args) {
//        MinStack obj = new MinStack();
//        obj.push(512);
//        obj.push(-1013);
//        obj.push(-1013);
//        obj.push(512);
//        obj.pop();
//        obj.min();
//        obj.pop();
//        obj.min();
//        obj.pop();
//        obj.min();
//
//
//
//    }

	public void push(int x) {
		this.stack.push(x);
		if (!this.stack_min.empty()) {
			if (x <= this.stack_min.peek()) {
				this.stack_min.push(x);
			}
		} else
			this.stack_min.push(x);
	}

	public void pop() {
		if (Objects.equals(this.stack_min.peek(), this.stack.pop()))
			this.stack_min.pop();

	}

	public int top() {
		return this.stack.peek();
	}

	public int min() {
		return this.stack_min.peek();
	}
}
