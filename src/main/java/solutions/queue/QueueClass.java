package solutions.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueClass {
	public static void main(String[] args) {
		queue();
		arrayDeque();
	}

	private static void arrayDeque() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.addLast(1);
		queue.addLast(2);
		queue.addLast(3);

		int first = queue.pollFirst();
		System.out.println(first);

		queue.addLast(4);
		queue.addLast(5);

		first = queue.pollFirst();
		System.out.println(first);
	}

	private static void queue() {
		Queue<Integer> queue = new ArrayDeque<>();

		queue.add(1);
		queue.add(2);
		queue.add(3);

		int first = queue.poll();
		System.out.println(first);

		queue.add(4);
		queue.add(5);

		first = queue.poll();
		System.out.println(first);
	}
}
