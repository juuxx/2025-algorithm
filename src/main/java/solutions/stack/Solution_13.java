package solutions.stack;

import java.util.Stack;

public class Solution_13 {
	public static void main(String[] args) {
		int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
		int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
		System.out.println(solutions(board, moves));
	}

	private static int solutions(int[][] board, int[] moves) {
		Stack<Integer> basket = new Stack<>();
		int lastPick = -1;
		int missingDollCount = 0;

		for (int i = 0; i < moves.length; i++) {
			int position = moves[i];
			for (int j = 0; j < board.length; j++) {

				if (board[j][position - 1] != 0) {
					lastPick = board[j][position - 1];

					if (basket.isEmpty()) {
						basket.push(board[j][position - 1]);
					} else {
						if (basket.peek() == lastPick) {
							basket.pop();
							missingDollCount = missingDollCount + 2;
						} else {
							basket.push(board[j][position - 1]);
						}
					}

					board[j][position - 1] = 0;
					break;
				}
			}
		}


		return missingDollCount;
	}

	/**
	 * chat gpt 가 풀어준 개선 코드
	 */
	public static int solution(int[][] board, int[] moves) {
		Stack<Integer> basket = new Stack<>();
		int poppedCount = 0; // 터진 인형 개수

		for (int move : moves) {
			int column = move - 1; // 열 인덱스 (1-based → 0-based)

			for (int row = 0; row < board.length; row++) {
				if (board[row][column] != 0) { // 인형이 있는 경우
					int pickedDoll = board[row][column];
					board[row][column] = 0; // 인형을 뽑았으므로 0으로 처리

					// 바구니에서 이전 인형과 같은지 확인
					if (!basket.isEmpty() && basket.peek() == pickedDoll) {
						basket.pop();
						poppedCount += 2; // 같은 인형 2개가 터짐
					} else {
						basket.push(pickedDoll);
					}
					break; // 한 번 집었으면 다음 move 실행
				}
			}
		}

		return poppedCount;
	}
}
