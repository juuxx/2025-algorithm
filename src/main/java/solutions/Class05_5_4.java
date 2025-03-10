package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Class05_5_4 {

	public static void main(String[] args) {
		int[] answers = {1,2,3,4,5,1,2,3,4,5};
		System.out.println(Arrays.toString(solution(answers)));
	}


	//나와 챗지피티의 합작
	public static int[] solution(int[] answers){
		int[] a = {1, 2, 3, 4, 5};
		int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
		int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
		int tempA = 0;
		int tempB = 0;
		int tempC = 0;
		for (int i = 0; i < answers.length; i++){
			int indexA = i % a.length;
			int indexB = i % b.length;
			int indexC = i % c.length;
			if(a[indexA] == answers[i]){
				tempA++;
			}
			if(b[indexB] == answers[i]){
				tempB++;
			}
			if(c[indexC] == answers[i]){
				tempC++;
			}
		}

		//여기서부터 챗지피티...
		int maxScore = Math.max(tempA, Math.max(tempB, tempC));
		List<Integer> result = new ArrayList<>();
		if(maxScore == tempA) result.add(1);
		if(maxScore == tempB) result.add(2);
		if(maxScore == tempC) result.add(3);

		return result.stream().sorted().mapToInt(Integer::intValue).toArray();
	}

	public static int[] solution2(int[] answers){
		int[][] pattern  = {
			{1, 2, 3, 4, 5},
			{2, 1, 2, 3, 2, 4, 2, 5},
			{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
		};

		int[] socres = new int[3];

		for(int i = 0; i < answers.length; i++){
			for(int j = 0; j < pattern.length; j++){
				if(answers[i] == pattern[j][i%pattern[j].length]){
					socres[j]++;
				}
			}
		}

		int maxScore = Arrays.stream(socres).max().getAsInt();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < socres.length; i++) {
			if (socres[i] == maxScore) {
				result.add(i + 1);
			}
		}

		return result.stream().sorted().mapToInt(Integer::intValue).toArray();
	}


}
