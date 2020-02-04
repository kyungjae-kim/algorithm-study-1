import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_�������ű��1_200204 {
	final static int[] dx = { 0, 1, 1 };
	final static int[] dy = { 1, 1, 0 }; // 0 : ����, 1 : �밢��, 2 : ����
	static int count = 0; // path ����
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // ����� 0, �� 1
			}
		} // input
		
		dfs(map, 0, 1, 0);
		
		System.out.println(count);
	}
	
	public static void dfs(int[][] map, int x, int y, int dir) {
		if(x == map.length - 1 && y == map[0].length - 1) { // �����ϸ� count++, return
			count++;
			return;
		}
		
		for(int i = dir - 1; i <= dir + 1; i++) { // dir�� ���� Ž�� �޸� ��
			if(i == -1 || i == 3) continue;
			else {
				if(x + dx[i] < map.length && y + dy[i] < map[0].length) { // ���� ������� Ȯ��
					if(i == 1) { // dir�� �밢���� ���� ������ ��� ��������� Ȯ��
						if(map[x + dx[0]][y + dy[0]] == 0 && map[x + dx[1]][y + dy[1]] == 0 && map[x + dx[2]][y + dy[2]] == 0) {
							dfs(map, x + dx[i], y + dy[i], i);
						}
					} else {
						if(map[x + dx[i]][y + dy[i]] == 0) { // ���� ĭ�� ��������� Ȯ��
							dfs(map, x + dx[i], y + dy[i], i);
						}
					}
				}
			}
		}
		
		return;
	}
}
