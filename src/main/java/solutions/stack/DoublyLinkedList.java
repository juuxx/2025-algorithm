package solutions.stack;

import java.util.Stack;

import lombok.Getter;


public class DoublyLinkedList {
	Node head;
	Node tail;
	Node curr;
	Stack<Node> removed = new Stack<>();

	public int getCurr() {
		return curr.getIndex();
	}

	public DoublyLinkedList(int n, int k) {
		// 초기 연결 리스트 구성
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
			if (i > 0) {
				nodes[i].prev = nodes[i - 1];
				nodes[i - 1].next = nodes[i];
			}
		}
		head = nodes[0];
		tail = nodes[n - 1];
		curr = nodes[k];
	}

	public void moveUp(int x) {
		while (x-- > 0 && curr.prev != null) {
			curr = curr.prev;	// 한칸씩 올라가기
		}
	}

	public void moveDown(int x) {
		while (x-- > 0 && curr.next != null) {	//한칸씩 내려가기... 맨 밑에 다다르면 스탑
			curr = curr.next;	// 현재 위치 == next (한칸 내려옴)
		}
	}

	public void delete() {
		removed.push(curr);
		if (curr.prev != null) curr.prev.next = curr.next;  // curr : 4, 3의 next에 4(curr)의 next인 5 넣어줌 3 -> 5 (4 삭제)
		if (curr.next != null) curr.next.prev = curr.prev;  // 4(curr) 의 next 5 의 prev 값에 4(curr)의 이전값인 3 넣어줌 5 -> 3

		if (curr.next != null) {
			curr = curr.next; // curr의 next가 있으면(맨 마지막이 아닐경우) -> curr 에 next값 넣어줌 curr(4) -> curr(5)
		} else {
			curr = curr.prev;	//curr가 맨 마지막일 경우 curr의 이전값 넣어줌 curr(4) -> curr(3)
		}
	}

	public void undo() {
		if (removed.isEmpty()) return;
		Node node = removed.pop();

		if (node.prev != null) node.prev.next = node;
		if (node.next != null) node.next.prev = node;
	}

	public String getResult(int total) {
		boolean[] isDeleted = new boolean[total];
		for (Node node : removed) {
			isDeleted[node.index] = true;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < total; i++) {
			sb.append(isDeleted[i] ? "X" : "O");
		}
		return sb.toString();
	}

	public static class Node {
		int index;
		Node prev;
		Node next;

		public Node(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}
	}
}
