import java.util.Scanner;

/*
 * BOJ 17070 ������ �ű�� 1
 */

public class BOJ17070 {
	
	static int N;
	static int[][] map;
	static int count;
	static int[] dy = {0, 1, 1}; // �� . ������, �밢��, �Ʒ���
	static int[] dx = {1, 1, 0}; // ��
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // ���� ũ��
		map = new int[N+1][N+1]; // �ε��� 1���� ����
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				map[i][j] = sc.nextInt(); // 0�̸� �� ����, 1�̸� ��
			}
		}
		
		// ó�� �������� ��ġ
		int[] p1 = {1, 1}; // y, x
		int[] p2 = {1, 2};
		
		count = 0;
		
		solution(p2[0], p2[1], 0);
		
		System.out.println(count);
		
		// �տ� �ִ� ���� ����ϸ� �Ǵ� �� �ƴѰ�? �ڿ� �κ��� ���� �տ� �ִ� ��ǥ�� ���� �Ǿ� �����ϱ�
		
		// ���η� ������ ������, �밢�����θ� �̵�
		// ���η� ������ �Ʒ���, �밢�����θ� �̵�
		// �밢������ ������ ������, �Ʒ���, �밢������ �̵� 
	}
	
	public static void solution(int py, int px, int dir) { // dir : 0:����, 1:����, 2:�밢��
		if(py==N && px==N) {
			count++;
			return;
		}
		
		int[] next = getDir(dir);
		
		for (int i = 0; i < next.length; i++) {
//			System.out.println("next : " + next[i]);
			int ny = py + dy[next[i]];
			int nx = px + dx[next[i]];
			
			// ���� �˻�
			if(ny<1 || ny>N || nx<1 || nx>N || map[ny][nx]!=0) continue;
			if(next[i] == 1 && (map[(ny-1)][nx]!=0 || map[ny][nx-1]!=0)) { // �밢���� �� �� ĭ Ž��
				continue;
			}
			
			solution(ny, nx, next[i]);
		}
		
	}
	
	public static int[] getDir(int type) {
		if(type==0) { // type : 0:����, 1:�밢��, 2:����
			int[] tmp = {0, 1};
			return tmp;
		} else if(type==1) {
			int[] tmp = {0, 1, 2};
			return tmp;
		} else if(type==2) {
			int[] tmp = {1, 2};
			return tmp;
		}
		return null;
	}
}
