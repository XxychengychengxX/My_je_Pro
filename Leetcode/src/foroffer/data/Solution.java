package foroffer.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		ListNode temp = new ListNode(0);
		ListNode listNode = temp;
		for (int i = 1; i < 5; i++) {
			temp.next = new ListNode(i);
			temp = temp.next;// 不是一个指针，就是地址
		}

		ListNode listNode1 = reverseList(listNode);
	}

	public static ListNode reverseList(ListNode head) {
		/*
		 * public ListNode reverseList(ListNode head) { ListNode result=null; ListNode
		 * temp=head; while(head!=null){ temp=head; head=head.next; temp.next=result;
		 * result=temp; } return result; }
		 */
		Stack<ListNode> stack = new Stack<>();
		while (head != null) {
			stack.push(head);
			head = head.next;
		}
		ListNode a = new ListNode(stack.pop().val);
		ListNode temp = a;
		while (!stack.empty()) {
			temp.next = stack.pop();
			temp = temp.next;
		}
		return a;
	}

	public int[] reversePrint(ListNode head) {
		LinkedList<Integer> listNodes = new LinkedList<>();
		while (head != null) {
			listNodes.add(head.val);
			head = head.next;
		}
		int[] a = new int[listNodes.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = listNodes.removeLast();
		}
//            System.out.println(Arrays.toString(a));
		return a;
	}

	// 为复制复杂链表创建的Hashmap
	Map<Node, Node> map = new HashMap<>();

	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		if (map.containsKey(head)) {
			return map.get(head);
		}

		Node node = new Node(head.val);
		map.put(head, node);

		node.next = copyRandomList(head.next);
		node.random = copyRandomList(head.random);
		return node;

	}

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode() {
		}

		public ListNode(int x) {
			val = x;
		}
	}

	public static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

}