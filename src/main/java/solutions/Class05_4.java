package solutions;

import java.util.Arrays;
import java.util.Collections;

public class Class05_4 {
	public static void main(String[] args) {
		int[] input = {1, -5, 2, 4, 3};
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(solution2(input)));
	}

	// 오름차순
	public static int[] solution1(int[] input){
		int[] result = input.clone();
		Arrays.sort(result);
		return result;
	}

	//내림차순
	public static int[] solution2(int[] input){
		int[] result = input.clone();
		Arrays.sort(result);
		for (int i = 0; i < result.length/2; i++) {
			int temp = result[i];
			result[i] = result[result.length - i -1];
			result[result.length - i -1] = temp;
		}
		return result;
	}

	public static int[] solution3(int[] input){
		// int[] result = input.clone();
		// Integer[] result = Arrays.stream(input).boxed().distinct().toArray(Integer[]::new);
		// Arrays.sort(result, Collections.reverseOrder());
		// return Arrays.stream(result).mapToInt(Integer::intValue).toArray();



		// Integer[] result = Arrays.stream(input).boxed().distinct().toArray(Integer[]::new);
		// Arrays.sort(result, Collections.reverseOrder());
		//
		// return Arrays.stream(result).mapToInt(Integer::intValue).toArray();


		Integer[] result = Arrays.stream(input).boxed().distinct().toArray(Integer[]::new);
		Arrays.sort(result, Collections.reverseOrder());

		return Arrays.stream(result).mapToInt(Integer::intValue).toArray();

	}
}
