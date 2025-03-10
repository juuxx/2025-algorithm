package solutions.array;

import java.util.Arrays;
import java.util.HashSet;

public class Solution_3 {
	/**
	 * 정수 배열 numbers 가 주어집니다 numbers 에서 서로 다른 인덱스에 있는 2개의 수를 뽑아 더해 만들 수 있는 모든 수를 배열에 오름차순으로 담아 반환하는 함수를 완성하세요
	 * 입출력 예
	 * numbers [2, 1, 3, 4, 1]
	 * result [2, 3, 4, 5, 6, 7]
	 * @param args
	 */
	public static void main(String[] args) {
		int[] numbers = {2, 1, 3, 4, 1};
		System.out.println(Arrays.toString(solution(numbers)));
	}


	public static int[] solution(int[] numbers){
		HashSet<Integer> result = new HashSet<>();
		for(int i = 0; i < numbers.length-1; i++){
			for (int j = i+1; j < numbers.length; j++) {
				int temp = numbers[i] + numbers[j];
				result.add(temp);
			}
		}

		// int[] arr = result.stream().mapToInt(Integer::intValue).toArray();
		// Arrays.sort(arr);
		// return arr;
		return result.stream().sorted().mapToInt(Integer::intValue).toArray();
	}


}
