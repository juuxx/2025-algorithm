package solutions.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution_11 {
	public static void main(String[] args) {
		String s = "baabaa";
		System.out.println(solution2(s));
	}

	/**
	 * stack으로 풀었는데... 결국 stack이 아닌 ㅠ...
	 */
	public static int solution(String s) {

		Stack<Character> stack = new Stack<>();
		char[] arr = s.toCharArray();
		List<Character> list = new ArrayList<>();
		for (char c : arr) {
			list.add(c);
		}

		int count = 0;
		int maxForCount = list.size() / 2;

		while (list.size() > 0 && maxForCount >= count) {
			for (int i = 0; i < list.size(); i++) {
				char c = list.get(i);
				if (stack.isEmpty()) {
					stack.push(c);
				} else {
					if (stack.pop() != c) {
						stack.push(c);
					}else{
						list.remove(i);
						list.remove(i-1);
						break;
					}
				}
			}
			count++;
		}

		return list.isEmpty() ? 1 : 0;
	}

	public static int solution2(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!stack.isEmpty() && stack.peek() == c) {
				stack.pop();
			} else {
				stack.push(c);
			}
		}

		return stack.isEmpty() ? 1 : 0;
	}

	// stack 에 데이터를 쌓자!
	// 앞에 값이랑 다르면 push() 같으면 pop()
}
