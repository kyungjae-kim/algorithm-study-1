import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_17135_ĳ�����潺_200204 {
	static int[] dx = { 0, -1, 0 };
	static int[] dy = { -1, 0, 1 };
	static Point first = new Point(0, 0); // ù��° �ü� ���� ����� �� ��ġ
	static Point second = new Point(0, 0); // �ι�° �ü� ���� ����� �� ��ġ
	static Point third = new Point(0, 0); // ����° �ü� ���� ����� �� ��ġ
	static int ans = 0; // �� ���� �� Ƚ�� üũ
	static int max = 0; // �ִ밪
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int limitDist = Integer.parseInt(st.nextToken());
		int[][] map = new int[n + 1][m];
		int[][] check = new int[n][m]; // visited check �迭
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input
		
		for(int i = 0; i < map[0].length - 2; i++) {
			for(int j = i + 1; j < map[0].length - 1; j++) {
				for(int k = j + 1; k < map[0].length; k++) {
					ans = 0; // Ƚ�� �ʱ�ȭ
					int cnt = 0;
					int[][] copy = new int[n + 1][m];
					for(int[] temp : map){
			            copy[cnt++] = temp.clone(); 
			        } // map ���� copy �迭 ����
					
					while(true) {
						check = new int[n][m]; // visited �ʱ�ȭ
						first = bfs(copy, check, n, i, limitDist); // ù��° �ü� ���� ����� �� ��ġ
						check = new int[n][m]; // visited �ʱ�ȭ
						second = bfs(copy, check, n, j, limitDist); // �ι�° �ü� ���� ����� �� ��ġ
						check = new int[n][m]; // visited �ʱ�ȭ
						third = bfs(copy, check, n, k, limitDist); // ����° �ü� ���� ����� �� ��ġ
						
						// limitDistance �ȿ� ������ �Ѿ, ������ Ƚ�� + 1, �ߺ� ����
						if(first.x != -1) {
							if(copy[first.x][first.y] == 1) ans++;
							copy[first.x][first.y] = 0;
						}
						if(second.x != -1) {
							if(copy[second.x][second.y] == 1) ans++;
							copy[second.x][second.y] = 0;
						}
						if(third.x != -1) {
							if(copy[third.x][third.y] == 1) ans++;
							copy[third.x][third.y] = 0;
						}
						
						// �迭 �� ������ ��ĭ�� �̵�
						for(int a = n - 1; a >= 1; a--) {
							for(int b = 0; b < m; b++) {
								copy[a][b] = copy[a - 1][b];
							}
						}
						for(int b = 0; b < m; b++) {
							copy[0][b] = 0;
						}
						
						// ���� �����ִ��� Ȯ��
						if(clear(copy)) {
							break;
						}
					}
					if(ans > max) max = ans; // �ִ����� Ȯ��
				}
			}
		}
		
		System.out.println(max);
	}
	
	public static boolean clear(int[][] map) { // map clear �ƴ��� Ȯ��
		for(int i = 0; i < map.length - 1; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static Point bfs(int[][] map, int[][] check, int x, int y, int limitDist) { // �� �ü����� ���� ����� ������ �� Ȯ��, point�� return
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x, y));
		while(!q.isEmpty()) {
			int r = q.peek().x;
			int c = q.peek().y;
			q.poll();
			if(r == map.length - 1) { // ó�� ����Ҷ��� �ٷ� ���� ���� ���� ����
				q.offer(new Point(x - 1, y));
				check[x - 1][y] = 1;
				if(map[x - 1][y] == 1) { // ���� �ִٸ� return
					return new Point(x - 1, y);
				}
			} else {
				for(int i = 0; i < 3; i++) {
					if(r + dx[i] >= 0 && c + dy[i] < map[0].length && c + dy[i] >= 0) {
						if(check[r + dx[i]][c + dy[i]] == 0 && check[r][c] < limitDist) { // limitDist ������ Ž��
							check[r + dx[i]][c + dy[i]] = check[r][c] + 1;
							q.offer(new Point(r + dx[i], c + dy[i]));							
							if(map[r + dx[i]][c + dy[i]] == 1) { // ���� �ִٸ� return
								return new Point(r + dx[i], c + dy[i]);
							}
						}
					}
				}
			}
		}
		
		return new Point(-1, -1); // ���� ���ٸ� -1, -1 return
	}
}