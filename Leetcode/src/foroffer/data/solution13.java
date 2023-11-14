package foroffer.data;

public class solution13 {

	public ListNode deleteNode(ListNode head, int val) {
		if (head.val == val) {
			head = head.next;
			return head;
		}
		ListNode working = head.next;
		ListNode temp = head;
		while (working != null) {
			if (working.val == val) {
				temp.next = working.next;
				break;
			} else {
				working = working.next;
				temp = temp.next;
			}
		}
		return head;
	}

	// 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
	// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
	public ListNode getKthFromEnd(ListNode head, int k) {
		ListNode working = head.next;
		ListNode temp = head;
		for (int i = 2; i <= k; i++) {
			working = working.next;
		}
		while (working != null) {
			temp = temp.next;
			working = working.next;
		}
		return temp;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode already = new ListNode(-1);
		ListNode temp = already;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				temp.next = l1;
				l1 = l1.next;
			} else {
				temp.next = l2;
				l2 = l2.next;
			}
			temp = temp.next;
		}
		if (l1 == null)
			while (l2 != null) {
				temp.next = l2;
				l2 = l2.next;
				temp = temp.next;
			}
		else
			while (l1 != null) {
				temp.next = l1;
				l1 = l1.next;
				temp = temp.next;
			}
		return already.next;
	}

	// 输入两个链表，找出它们的第一个公共节点。
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int lena = 0, lenb = 0;
		ListNode a = headA, b = headB;

		while (a != null) {
			lena++;
			a = a.next;
		}

		while (b != null) {
			lenb++;
			b = b.next;
		}

		int d = lena - lenb;

		if (d > 0) {
			while (d-- > 0)
				headA = headA.next;
		} else if (d < 0) {
			d = -d;
			while (d-- > 0)
				headB = headB.next;
		}

		while (headA != headB) {
			headA = headA.next;
			headB = headB.next;
		}
		return headA;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
