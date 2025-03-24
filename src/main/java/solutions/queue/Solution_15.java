package solutions.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_15 {
	public static void main(String[] args) {
		int n = 5;
		int k = 2;

		System.out.println(solution(n, k));
	}

	private static int solution(int n, int k) {
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = k; i < n + k; i++) {
			// 2, 3, 4, 5, || 1
			if (i <= n) {	//2, 3, 4, 5
				queue.add(i);
			} else {
				queue.add(i - n);
			}
		}

		while (queue.size() > 1) {
			queue.poll(); // k번째 제거
			for (int i = 1; i < k; i++) {
				queue.add(queue.poll()); // k-1명 돌리기
			}
		}
		int result = queue.poll();
		return result;
	}
}
