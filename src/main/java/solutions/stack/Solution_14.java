package solutions.stack;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Solution_14 {
	public static void main(String[] args) {
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
		// String[] cmd = {"D 2"};
		System.out.println(solution(n, k, cmd));
	}

	/**
	 * 개선된 풀이
	 */
	public static String solution(int n, int k, String[] cmd) {
		int[] prev = new int[n];   // 이전 행
		int[] next = new int[n];   // 다음 행
		boolean[] isRemoved = new boolean[n]; // 삭제 여부
		Stack<Integer> removed = new Stack<>(); // 삭제된 행 저장용

		for (int i = 0; i < n; i++) {
			prev[i] = i - 1;
			next[i] = i + 1;
		}
		next[n - 1] = -1;

		int curr = k;

		for (String command : cmd) {
			String[] parts = command.split(" ");

			switch (parts[0]) {
				case "U":
					int up = Integer.parseInt(parts[1]);
					for (int i = 0; i < up; i++) {
						curr = prev[curr];
					}
					break;

				case "D":
					int down = Integer.parseInt(parts[1]);
					for (int i = 0; i < down; i++) {
						curr = next[curr];
					}
					break;

				case "C":
					isRemoved[curr] = true;
					removed.push(curr);

					// 연결 리스트 끊기
					if (prev[curr] != -1) next[prev[curr]] = next[curr];
					if (next[curr] != -1) prev[next[curr]] = prev[curr];

					// 커서 이동
					curr = (next[curr] != -1) ? next[curr] : prev[curr];
					break;

				case "Z":
					int restore = removed.pop();
					isRemoved[restore] = false;

					// 연결 복원
					if (prev[restore] != -1) next[prev[restore]] = restore;
					if (next[restore] != -1) prev[next[restore]] = restore;
					break;
			}
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < n; i++) {
			result.append(isRemoved[i] ? "X" : "O");
		}

		return result.toString();
	}


	/* 풀다가 포기 */
	private static String solution1(int n, int k, String[] cmd) {
		int[] table = new int[n];
		Stack<Integer> stack = new Stack<>();
		String[] selectRow = new String[n];
		String[] removedRow = new String[n];
		for (int i = 0; i < n; i++) {
			table[i] = i;
			removedRow[i] = "X";
			if (i == k) {
				selectRow[i] = "S";
			}else{
				selectRow[i] = "0";
			}
		}

		Map<String, Integer> cmdMap = Map.of(
			"D", +1,
			"U", -1
		);
		for (String c : cmd) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				if (i == n - 1) {
					sb.append("[" + i + "]: " + selectRow[i]);
				} else {
					sb.append("[" + i + "]: " + selectRow[i] + ", ");
				}
			}
			System.out.println(sb.toString());

			String[] direction = c.split(" ");
			if (direction.length > 1) { //UD
				int movedPosition =  cmdMap.get(direction[0]) * Integer.parseInt(direction[1]);
				movedPosition(selectRow, movedPosition, table);
			} else {
				if ("C".equals(direction[0])) {
					int position = selectRowPosition(selectRow);
					removedRow[position] = "O";
					table[position] = -1;
					stack.push(position);
					// 삭제후 바로 아래 행으로 selectRow 변경
					movedPosition(selectRow, 0, table);
				} else if ("Z".equals(direction[0])) {
					int recentRemoveRow = stack.pop();
					table[recentRemoveRow] = recentRemoveRow;
					removedRow[recentRemoveRow] = "X";
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String remove : removedRow) {
			sb.append(remove);
		}
		return sb.toString();
	}

	public static int selectRowPosition(String[] selectRow) {
		for (int i = 0; i < selectRow.length; i++) {
			if ("S".equals(selectRow[i])) {
				return i;
			}
		}

		return -1;
	}

	public static void movedPosition(String[] selectRow, int movedPosition, int[] table) {
		int[] filtered = Arrays.stream(table)
			.filter(num -> num != -1)
			.toArray();
		int rs = -1;
		// 시작위치
		int position = selectRowPosition(selectRow);
		int startPosition = -1;
		for (int i = 0; i < filtered.length; i++) {
			if (filtered[i] == position) {
				startPosition = i;
			}


		}
		// 종료위치
		movedPosition = movedPosition + startPosition;
		if (movedPosition > filtered.length - 1) {
			rs = filtered[filtered.length - 1];
		} else {
			rs = filtered[movedPosition];
		}

		for (int i = 0; i < selectRow.length; i++) {
			if (i == rs) {
				selectRow[i] = "S";
			} else {
				selectRow[i] = "0";
			}
		}
	}

}
