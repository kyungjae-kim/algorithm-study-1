import java.util.Scanner;

public class BOJ_�ٸ������2 {
	static int row;
	static int col;
	static int[][] map;
	static boolean[][] visited;
	static int island;
	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};//�����¿�.
	static int[][] bridge;
	static boolean[] visited_bridge;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		map = new int[row+1][col+1];
		visited = new boolean[row+1][col+1];
		for(int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				map[i][j] = sc.nextInt();
			}
		}//input.
		for(int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {
					island++;
					dfs(i,j);
				}
			}
		}
		bridge = new int[island+1][island+1]; //���� �� �������� 1�� �� ���� 2���� �迭�� �ϳ� ������ش�.
		visited_bridge = new boolean[island+1];
		for(int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				if(map[i][j] != 0) {
					int start_island = map[i][j]; //���� ������ ����ߴ���.
					for(int k=0; k<4; k++) { // x �� y �� �ε��� ���� ���� �ְ� 0�̾ƴϸ� ��.
						int new_x = i + direction[k][0];
						int new_y = j + direction[k][1];
						if(new_x > 0 && new_y > 0 && new_x<= row && new_y<= col && map[new_x][new_y] == 0) {
							go(start_island, new_x, new_y, k);
						}
					}
				}
			}
		}//�� ������ ���� �����ϰ� ���� ���� �ٸ� ������ ����.
		
	
		int sum = 0;
		visited_bridge[1] = true;
		while(!check_end()) {
			int min = Integer.MAX_VALUE;
			int end_island = 0;
			for(int i=2; i<=island; i++) {
				if(!visited_bridge[i] && bridge[1][i] != 0 && min > bridge[1][i]){
					min = bridge[1][i];
					end_island = i;
				}
			}
			if(end_island == 0) {
				sum = -1;
				break;
			}//��� ������ �湮������ �ʾ����� ���̻� �ٸ������� �� ���� ���.
			visited_bridge[end_island] = true;
			sum += bridge[1][end_island];
			for(int i=2; i<=island; i++) {
				if(bridge[1][i] == 0) bridge[1][i] = bridge[end_island][i];
				else{
					if(bridge[end_island][i] != 0 && bridge[1][i] > bridge[end_island][i]) bridge[1][i] = bridge[end_island][i];
				}
			}
		}
		System.out.println(sum);
	}//end main.
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		map[x][y] = island;
		for(int i=0; i<4; i++) { // x �� y �� �ε��� ���� ���� �ְ� 0�̾ƴϸ� ��.
			int new_x = x + direction[i][0];
			int new_y = y + direction[i][1];
			if(new_x > 0 && new_y > 0 && new_x<= row && new_y<= col && !visited[new_x][new_y] && map[new_x][new_y] != 0) {
				dfs(new_x,new_y);
			}
		}
	}//end dfs.
	
	public static void go(int start_island, int x, int y, int direct) {
		int cnt = 0;
		while(true) {
			if(x<=0 || x>row || y<=0 || y>col) {
				break;
			}
			if(map[x][y] != 0) {
				int end_island = map[x][y]; //���� ���� �����ߴ���.
				if(cnt > 1) {
					if(bridge[start_island][end_island] == 0) bridge[start_island][end_island] = cnt;
					else {
						if(bridge[start_island][end_island] > cnt) bridge[start_island][end_island] = cnt;
					}
				}
				break;
			}
			x += direction[direct][0];
			y += direction[direct][1];
			cnt++;
		}
	}//end go.
	
	public static boolean check_end() {
		for(int i=1; i<=island; i++) {
			if(!visited_bridge[i]) return false;
		}
		return true;
	}
}
