package solutions;

import java.util.Arrays;

/**
 * 행렬의 곱셈
 */
public class Class05_5_5 {

	public static void main(String[] args) {
		int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
		int[][] arr2 = {{3, 3}, {3, 3}};

		System.out.println(Arrays.deepToString(solution(arr1, arr2)));
	}

	public static int[][] solution(int[][] arr1, int[][] arr2) {
		int rowsA = arr1.length;        // arr1의 행 개수
		int colsA_rowsB = arr1[0].length; // arr1의 열 개수 = arr2의 행 개수
		int colsB = arr2[0].length;     // arr2의 열 개수

		int[][] result = new int[rowsA][colsB];

		for (int i = 0; i < rowsA; i++) {
			for (int j = 0; j < colsB; j++) {
				for (int k = 0; k < colsA_rowsB; k++) {
					result[i][j] += arr1[i][k] * arr2[k][j];
				}
			}
		}
		return result;
	}

}
