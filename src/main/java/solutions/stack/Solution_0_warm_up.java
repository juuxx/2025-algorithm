package solutions.stack;

import java.util.Stack;

public class Solution_0_warm_up {
	public static void main(String[] args) {
		String s = "(()()";
		System.out.println(solution(s));
	}

	private static boolean solution(String s) {
		Stack<Character> parentheses = new Stack<>();
		for (char c : s.toCharArray()) {
			if ('(' == c) {
				parentheses.push(c);
			} else {
				if(!parentheses.isEmpty()){
					parentheses.pop();
				}
			}

		}
		return parentheses.isEmpty();
	}
}
