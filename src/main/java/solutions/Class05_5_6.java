package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Class05_5_6 {
	public static void main(String[] args) {
		int n = 5;
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		System.out.println(Arrays.toString(solution3(n, stages)));
	}

	public static int[] solution(int n, int[] stages) {
		Arrays.sort(stages);
		System.out.println(Arrays.toString(stages));
		// [1, 2, 2, 2, 3, 3, 4, 6]
		int currentStage = 1;
		int userCount = stages.length;
		int count = 0;
		int survivedUserCount = userCount;
		// double[] failRate = new double[n];
		// Map<Integer, Double> failRate = new HashMap<>();
		double[][] failRate = new double[2][n];
		for (int i = 0; i < stages.length; i++) {
			if (currentStage == stages[i]) {
				count++;
			}

			if (currentStage != stages[i]) {
				System.out.println("currentStage = " + currentStage);
				failRate[0][currentStage-1] = currentStage;
				failRate[1][currentStage-1] = (double)count / survivedUserCount;
				currentStage++;
				survivedUserCount -= count;
				count = 0;
			}

			if (stages[i] == n) {
				failRate[0][currentStage-1] = n;
				failRate[1][currentStage-1] = (double) count / survivedUserCount;
				break;
			}

			if (stages[i] == n + 1) {
				failRate[0][currentStage-1] = n;
				failRate[1][currentStage-1] = (double) count / survivedUserCount;
				break;
			}
		}

		System.out.println(Arrays.deepToString(failRate));

		return null;
	}


	public static int[] solution2(int n, int[] stages) {
		int[] stayAtStage = new int[n + 2]; // (0은 버림 1부터 시작) 각 스테이지에 머물고 있는 사람 수(1부터, n+1은 클리어 사람)
		int[] playersAtOrAbove = new int[n + 1]; // 각 스테이지 이상 도달한 사람 수

		System.out.println(stages.length);
		System.out.println(stayAtStage.length);
		// 스테이지별 도전자 수 저장
		for (int stage : stages) {
			//System.out.println(stage);
			stayAtStage[stage]++; // stage의 배열의 하나 값 = stayAtStage 의 index 두번 오면 사람이 두명인거
		}

		System.out.println(Arrays.toString(stayAtStage));

		// 각 스테이지 이상 도달한 사람 수 계산
		//1.마지막스테이지 + 클리어 한 사람 => 5랑 6인 사람( 즉 n이랑 n+1에 있던 사람)
		playersAtOrAbove[n] = stayAtStage[n] + stayAtStage[n + 1]; // 마지막 스테이지 + 클리어한 사람들
		for (int i = n - 1; i > 0; i--) { // 4, 3, 2, 1
			System.out.println("playersAtOrAbove[" + i + "] = " + playersAtOrAbove[i + 1] +"+" + stayAtStage[i]);
			playersAtOrAbove[i] = playersAtOrAbove[i + 1] + stayAtStage[i];
			// 이전 클리어 한 사람 cnt + 지금 여기에 머무르고 있는 사람 cnt
		}
		System.out.println(Arrays.toString(playersAtOrAbove));
		// 실패율 저장 리스트 (스테이지 번호, 실패율)
		List<double[]> failRateList = new ArrayList<>();
		for (int i = 1; i <= n; i++) { //각 스테이지별 실패율 1~5
			double failRate = (playersAtOrAbove[i] == 0) ? 0 : (double) stayAtStage[i] / playersAtOrAbove[i];
			failRateList.add(new double[]{i, failRate});
		}


		// 실패율 내림차순 정렬 (같으면 스테이지 번호 오름차순)
		failRateList.sort((a, b)
			-> Double.compare(b[1], a[1]) != 0 ? Double.compare(b[1], a[1]) : Integer.compare((int) a[0], (int) b[0]));

		failRateList.forEach(arr -> System.out.println(Arrays.toString(arr)));

		// 결과 배열로 변환
		return failRateList.stream().mapToInt(e -> (int)e[0]).toArray();
	}


	public static int[] solution3(int n, int[] stages) {
		int[] memberByStage = new int[n+2]; //클리어 스테이지까지

		for(int stage : stages){
			memberByStage[stage]++;
		}

		// 각 스테이지별 인원수
		// {0, 1, 3, 2, 1, 0, 1}

		//각 스테이지별 클리어 인원수를 구해야함
		int[] clearMeberCntByStage = new int[n+1];

		//stage 5의 클리어한 사람 수는 = 5 스테이지에 있는 사람 + 6 스테이지에 있는 사람
		clearMeberCntByStage[n] = memberByStage[n] + memberByStage[n+1];
		System.out.println(clearMeberCntByStage[n]);
		System.out.println("n = " + n);
		for(int i = n-1; i > 0; i--){ // 4, 3, 2, 1
			System.out.println("clearMeberCntByStage["+i+"] = "
				+ "clearMeberCntByStage[" + (i-1)+ "]= " + clearMeberCntByStage[i+1]
				+" memberByStage[" + i+ "] = " + memberByStage[i]);
			clearMeberCntByStage[i] = clearMeberCntByStage[i+1] + memberByStage[i];
		}

		System.out.println(Arrays.toString(clearMeberCntByStage));

		List<double[]> failRateList = new ArrayList<>();
		// 실패율 구하기
		for(int i = 1; i<= n; i++){
			//실패율
			double failRate = (double)memberByStage[i]/clearMeberCntByStage[i];
			failRateList.add(new double[]{i, failRate});
		}

		failRateList.forEach(arr -> System.out.println(Arrays.toString(arr)));

		//실패율 desc, 스테이지 asc
		failRateList.sort((a,b) -> Double.compare(b[1], a[1]) != 0 ?
			Double.compare(b[1], a[1]) : Integer.compare((int)a[0], (int)b[0])
		);

		// 이제 스테이지값 리턴
		int[] result = failRateList.stream().mapToInt(a -> (int)a[0]).toArray();
		return result;
	}

	// 책 풀이
	private int[] bookSolution(int n, int[] stages){
		int[] challengers = new int[n + 2];
		for (int i = 1; i < stages.length; i++) {
			challengers[i]++;
		}

		HashMap<Integer, Double> fails = new HashMap<>();
		double total = stages.length;

		for (int i = 1; i <= n; i++) {
			if(challengers[i] == 0){
				fails.put(i, 0.0);
			}else{
				fails.put(i, challengers[i] / total);
				total -= challengers[i];
			}
		}

		return fails.entrySet().stream().sorted((a, b)
			-> Double.compare(b.getValue(), a.getValue())).mapToInt(HashMap.Entry::getKey).toArray();
	}
}
