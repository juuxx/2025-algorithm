package solutions.stack;

import java.util.Arrays;
import java.util.Stack;

public class Solution_12 {
	public static void main(String[] args) {
		int[] prices = {1, 6, 9, 5, 3, 2, 7};
		System.out.println(Arrays.toString(solution1(prices)));
	}

	public static int[] solution(int[] prices) {
		int[] result = new int[prices.length];

		desc(prices);
		for (int i = 0; i < prices.length; i++) {
			System.out.println("index = " + i);
			if (i == 0) {
				result[0] = 0;
			}else {
				if (prices[i] < prices[i - 1]) {
					result[i] = i;
				} else {
					result[i] = 1;
				}
			}
		}

		System.out.println(Arrays.toString(result));
		desc(result);
		return result;
	}

	public static void desc(int[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length - i -1];
			arr[arr.length - i - 1] = temp;
		}
	}

	public static int[] solution1(int[] prices) {
		int n = prices.length;
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>(); // 가격이 유지되는 시간을 계산할 인덱스 스택

		for (int i = 0; i < n; i++) {
			// 현재 가격이 이전 가격보다 작아지는 경우 → 스택에서 꺼내며 결과 계산
			while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
				int idx = stack.pop(); // 가격이 떨어지는 시점
				result[idx] = i - idx; // 지속 시간 저장
			}
			stack.push(i); // 현재 인덱스를 스택에 저장
		}

		// 스택에 남아있는 인덱스들은 끝까지 가격이 유지된 경우
		while (!stack.isEmpty()) {
			int idx = stack.pop();
			result[idx] = (n - 1) - idx; // 남은 초 계산
		}

		return result;
	}


}
