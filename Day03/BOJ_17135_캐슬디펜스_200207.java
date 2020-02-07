/*
 * BOJ 17135 ĳ�� ���潺
 */

import java.util.Arrays;
import java.util.Scanner;

public class BOJ17135 {
	static int N, M, D;
	static int[][] map;
	static boolean[] used; // �ü� ��ġ ���� �� �� ��
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // ��
		M = sc.nextInt(); // ��
		D = sc.nextInt(); // �ü��� ���� �Ÿ� ����
		map = new int[N+1][M];
		for (int i = 0; i < map.length-1; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = sc.nextInt(); // 0�� �� ĭ, 1�� ��
			}
		}
		used = new boolean[M]; 
//		for (int i = 0; i < map[N].length; i++) {
//			map[N][i] = 2; // ��
//		}
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		// �ü� 3���� ��ġ�Ѵ�
		pick(0, 0);
		// 3�� ��ġ �Ϸ�Ǹ� ���� ����
		// 1�� - �ü��� ����. ���ݹ��� ���� �����
		// 		���� �Ʒ��� �� ĭ �̵�. ���� ���ų� ������ ����� �� ���� �����
		// ��� ���� ���ܵǸ� ���� ��
		// �ü��� �������� ������ �� �ִ� ���� �ִ� �� ���
	}
	
	public static void pick(int idx, int cnt) { // idx : ��ġ�� �ε���, cnt : ��ġ�� ����-> �ü��� �� �� ��
		if(cnt == 3) { // �ü��� �� ���̴ϱ�
			for (int i = 0; i < M; i++) {
				if(used[i]) {
					System.out.print(i); // �� ���� ������ �� �ִ� ��ġ
					// ��¸��� �� ��ġ�� �ִ� �ֵ�� ���� ����
				}
			}
			System.out.println();
		}
		for (int i = idx; i < M; i++) {
			if(!used[i]) {
				used[i] = true;
				pick(i, cnt+1);
				used[i] = false;
			}
		}
	}
}
