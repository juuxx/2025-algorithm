package solutions.stack;

import java.util.Map;
import java.util.Stack;

public class Solution_10 {
	public static void main(String[] args) {
		String s = "}}}";
		System.out.println(solution2(s));
	}

	private static int solution(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			StringBuilder sb = new StringBuilder();
			String front = s.substring(i, s.length());
			String back = s.substring(0, i);
			sb.append(front).append(back);
			count += stack(sb.toString());
		}

		return count;
	}

	public static void stackCharacter(Character c, Stack<Character> stack, Character start, Character end) {
		if (c == start || c == end) {
			if (c == start) {
				stack.push(start);
			} else {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			}
		}
	}

	public static int stack(String s) {
		char[] arr = s.toCharArray();
		Stack<Character> rs1 = new Stack<>();
		Stack<Character> rs2 = new Stack<>();
		Stack<Character> rs3 = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			stackCharacter(arr[i], rs1, '(', ')');
			stackCharacter(arr[i], rs2, '[', ']');
			stackCharacter(arr[i], rs3, '{', '}');
		}

		int rsCount1 = 0;
		int rsCount2 = 0;
		int rsCount3 = 0;

		for (char c : arr) {
			if (c == ')') {
				rsCount1++;
			}
			if (c == ']') {
				rsCount2++;
			}
			if (c == '}') {
				rsCount3++;
			}
		}

		if (s.length() == rsCount1 || s.length() == rsCount2 || s.length() == rsCount3) {
			return 0;
		}



		if (rs1.isEmpty() && rs2.isEmpty() && rs3.isEmpty()) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int solution2(String s) {
		int count = 0;
		int len = s.length();

		for (int i = 0; i < len; i++) {
			String rotated = s.substring(i) + s.substring(0, i);
			if(isValid(rotated)) count++;
		}

		return count;
	}

	private static boolean isValid(String rotated) {
		Stack<Character> stack = new Stack<>();
		Map<Character, Character> matchingBrackets = Map.of(
			')', '(',
			'}', '{',
			']', '['
		);

		for (char c : rotated.toCharArray()) {
			if (matchingBrackets.containsValue(c)) {
				stack.push(c);
			} else if (matchingBrackets.containsKey(c)) {
				if (stack.isEmpty() || stack.pop() != matchingBrackets.get(c)) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}
}
