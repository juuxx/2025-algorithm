package solutions.stack;

import java.util.Stack;

public class Solution_9 {
	public static void main(String[] args) {
		int decimal = 10;
		System.out.println(solution2(decimal));
		sbInsert();
	}

	public static String solution(int decimal) {
		/**
		 * 10 / 2 -> 0
		 * 5 / 2 -> 1
		 * 2 / 2 -> 0
		 * 1
		 *
		 * 27 / 2 -> 1
		 * 13/ 2 -> 1
		 * 6/ 2 -> 0
		 * 3/2 -> 1
		 * 1
		 */
		Stack<Integer> digit = new Stack<>();
		while (decimal  > 0) {
			int result = decimal%2;
			digit.push(result);
			// System.out.println("digit = " + result);
			decimal = decimal / 2;
			// System.out.println("decimal = " + decimal);

		}
		System.out.println(digit.size());
		// String result = ""; //String 은 시간복잡도에서 좋지 않음 sb 변경
		StringBuilder result = new StringBuilder();

		// stack 을 size로 for문 돌리면 pop 하면서 사이즈가 점점 줄어들어서 안됨!!!!!
		// for-each 쓰면 LIFO 가 아니라 삽입된 순서대로 나옴 안됨
		while (!digit.isEmpty()) {
			String pop = String.valueOf(digit.pop());
			System.out.println(pop);
			result.append(pop);
		}

		return result.toString();
	}

	private static String solution2(int decimal) {
		return Integer.toBinaryString(decimal); 	//자바 내장 함수
	}

	public static String solution3(int n) {
		if (n == 0) return "0"; // 예외 처리

		StringBuilder binary = new StringBuilder();
		while (n > 0) {
			binary.insert(0, n % 2); // 앞에 추가 (스택처럼 활용)
			n /= 2;
		}

		return binary.toString();
	}

	public static void sbInsert() {
		StringBuilder sb = new StringBuilder();
		sb.insert(0, "C");
		sb.insert(0, "B");
		sb.insert(0, "A");
		System.out.println(sb.toString());
	}

}
