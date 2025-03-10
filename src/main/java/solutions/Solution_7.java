package solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution_7 {
	public static void main(String[] args) {
		// String dirs = "ULURRDLLU";
		String dirs = "LULLLLLLU";
		System.out.println(solution3(dirs));
	}

	public static int solution(String dirs){
		String[] dirsArr = new String[dirs.length()];
		for (int i = 0; i < dirs.length(); i++){
			dirsArr[i] = String.valueOf(dirs.charAt(i));
		}

		int[] position = {0,0};

		Set<List<Integer>> save = new HashSet<>(); //int[] <- 는 주소값으로 들어가서 중복이 들어가게됨

		for (int i = 0; i < dirsArr.length; i++) {
			position = addPosition(dirsArr[i], position[0], position[1]);
			System.out.println(">" + Arrays.toString(position));
			if (position[0] >= -5 && position[0] <= 5 && position[1] >= -5 && position[1] <= 5) {
				// System.out.println(Arrays.toString(position));
				save.add(Arrays.asList(position[0], position[1]));
			}
		}

		System.out.println(Arrays.toString(position));
		System.out.println("------");
		System.out.println(save);
		return save.size() +1;
	}

	private static int[] addPosition(String sign, int x, int y) {
		int[] position = {x, y};
		switch (sign) {
			case("U") :
				y++;
				position[1] = y;
				break;
			case("D") :
				y--;
				position[1] = y;
				break;
			case("R") :
				x++;
				position[0] = x;
				break;
			case("L") :
				x--;
				position[0] = x;
				break;
		}

		return position;
	}

	public static int solution1(String dirs){
		Set<String> visitedPaths = new HashSet<>(); // 방문한 길을 저장할 Set
		int x = 0, y = 0; // 현재 위치

		// 방향 이동 정의
		Map<Character, int[]> moves = Map.of(
			'U', new int[]{0, 1},  'D', new int[]{0, -1},
			'L', new int[]{-1, 0}, 'R', new int[]{1, 0}
		);

		for (char dir : dirs.toCharArray()) {
			int nx = x + moves.get(dir)[0]; // 새로운 x 좌표
			int ny = y + moves.get(dir)[1]; // 새로운 y 좌표

			// 좌표평면 범위 (-5 ~ 5) 확인
			if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;

			// 이동한 길을 양방향으로 저장 (예: "0,0->0,1" 과 "0,1->0,0"은 같은 길)
			String path1 = x + "," + y + "->" + nx + "," + ny;
			String path2 = nx + "," + ny + "->" + x + "," + y;
			visitedPaths.add(path1);
			visitedPaths.add(path2);

			// 현재 위치 업데이트
			x = nx;
			y = ny;
		}

		return visitedPaths.size() / 2;
	}

	public static int solution3(String dirs){

		Set<String> stringPath = new HashSet<>();

		Map<Character, int[]> movePosition = Map.of(
			'U', new int[]{0,1},
			'D', new int[]{0,-1},
			'L', new int[]{-1,0},
			'R', new int[]{1,0}
		);

		int x = 0;
		int y = 0;

		for(int i = 0; i < dirs.length(); i++){
			int nx = x + movePosition.get(dirs.charAt(i))[0];
			int ny = y + movePosition.get(dirs.charAt(i))[1];

			if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue; // skip
			String path1 = x + "," + y + "->" + nx + "," + ny;
			String path2 = nx + "," + ny + "->" + x + "," + y;

			stringPath.add(path1);
			stringPath.add(path2);

			x = nx;
			y = ny;

		}

		return stringPath.size()/2;
	}

}
