import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_ĳ�����潺2 {
	
	static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}//�� ��ǥ �־�� ��.
	
	static int row; //��
	static int col; //��
	static int D;	//��Ÿ�.
	static int[][] map; //�ʱ� ��.
	static int[][] direction = {{0,-1},{-1,0}, {0,1}}; //For BFS.
	static int answer = 0; //��� ��.
	static Pos enemy1 = new Pos(0,0); //��1 ��ǥ.
	static Pos enemy2 = new Pos(0,0); //��2 ��ǥ.
	static Pos enemy3 = new Pos(0,0); //��3 ��ǥ.
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();//��.
		col = sc.nextInt();//��.
		D = sc.nextInt();//��Ÿ�.
		map = new int[row+1][col];//���� ��.
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				map[i][j] = sc.nextInt();
			}
		}//�� �׸���.
		for(int i=0; i<col-2; i++) {
			map[row][i] = 5;
			dfs(i,1);
			map[row][i] = 0;
		}
		System.out.println(answer);
	}
	public static void dfs(int idx, int cnt) {
		if(cnt == 3) {
			int[][] game_map = new int[row+1][col];
			for(int i=0; i<=row; i++) {
				for(int j=0; j<col; j++) {
					game_map[i][j] = map[i][j];
				}
			}
			int temp = game(game_map);
			if(temp > answer) answer = temp;
			return ;
		}
		for(int i=idx+1; i<col-2+cnt; i++) {
			map[row][i] = 5;
			dfs(i,cnt+1);
			map[row][i] = 0;
		}
	}
	
	public static int game(int[][] game_map) {
		int[] archer_list = new int[3];
		int top = -1;
		int sum = 0; //�̹� �ùĿ��� ���� ���� ��.
		for(int i=0; i<col; i++) {
			if(game_map[row][i] == 5) {
				archer_list[++top] = i;
			}
		}//�ü� ��ġ Ȯ��.
		
//		for(int i=0; i<=row; i++) {
//			for(int j=0; j<col; j++) {
//				System.out.print(game_map[i][j] + " ");
//			}
//			System.out.println(" ");
//		}
		
		while(!clear(game_map)) {
			boolean[][] visited = new boolean[row][col];
			enemy1 = bfs(row-1, archer_list[0], game_map, visited);
			visited = new boolean[row][col];
			enemy2 = bfs(row-1, archer_list[1], game_map, visited);
			visited = new boolean[row][col];
			enemy3 = bfs(row-1, archer_list[2], game_map, visited);
			//�ü� ������ ����ȭ ���� ã��.
//			System.out.println("enemy1 : " + enemy1.x + " , " + enemy1.y);
//			System.out.println("enemy2 : " + enemy2.x + " , " + enemy2.y);
//			System.out.println("enemy3 : " + enemy3.x + " , " + enemy3.y);
			if(enemy1.x != -1 && game_map[enemy1.x][enemy1.y] == 1) {
				sum++;
				game_map[enemy1.x][enemy1.y] = 0;
			}
			if(enemy2.x != -1 && game_map[enemy2.x][enemy2.y] == 1) {
				sum++;
				game_map[enemy2.x][enemy2.y] = 0;
			}
			if(enemy3.x != -1 && game_map[enemy3.x][enemy3.y] == 1) {
				sum++;
				game_map[enemy3.x][enemy3.y] = 0;
			}
			move(game_map);
//			for(int i=0; i<=row; i++) {
//				for(int j=0; j<col; j++) {
//					System.out.print(game_map[i][j] + " ");
//				}
//				System.out.println(" ");
//			}
//			System.out.println("**********************");
		}
		
		
		return sum;
	}
	public static boolean clear(int[][] game_map) {
		for(int i=0; i<row; i++) {
			for (int j = 0; j < col; j++) {
				if(game_map[i][j] == 1) return false;
			}
		}
		return true;
	}//�ʿ� �� �ֳ� ���� ã�ƺ��� �޼���.
	public static Pos bfs(int x, int y, int[][] game_map, boolean[][]visited) {
		//System.out.println("bsf ���� : " + x + " , " + y);
		Queue<Pos> queue = new LinkedList<Pos>();
		queue.add(new Pos(x,y));
		while(true) { //���� ��� �� �͵�. �ε��� ���� ���� �ִ���, 
			int dx = queue.peek().x;
			int dy = queue.peek().y;
			queue.poll();
			visited[dx][dy] = true;
			int temp = Math.abs(x-dx+1) + Math.abs(y-dy);
			if(temp > D) return new Pos(-1,-1);
			else {
				if(game_map[dx][dy] == 1) return new Pos(dx,dy);
				else {
					for(int i=0; i<3; i++) {
						int new_x = dx + direction[i][0];
						int new_y = dy + direction[i][1];
						if(new_x >=0 && new_x < row && new_y >=0 && new_y<col) {
							if(!visited[new_x][new_y]) {
								queue.add(new Pos(new_x, new_y));
							}
						}
						//System.out.println("��~");
					}
				}
			}
		}
	}//���ʿ켱�� bfs.
	public static void move(int[][] game_map) {
		for(int i=row-1; i>=1; i--) {
			for(int j=0; j<col;j++) {
				game_map[i][j] = game_map[i-1][j];
			}
		}
		for(int i=0; i<col; i++) {
			game_map[0][i] = 0;
		}
	}//�ùĳ����� �� �̵��ϴ� �޼���.
}
