import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_�������ű��1 {
	
	static int[][] map;
	static int N;
	static int sum = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][N+1];
		for(int i=1; i<=N ; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}//�� �׸���.
		dfs(1,2,0);
		System.out.println(sum);
	}//end main class.
	
	public static void dfs(int dx , int dy, int dtype) { 
		if(dx == N && dy == N) {
			sum++;
			return ;
		}
		switch(dtype) {
		case 0:{//���ʿ��� ���°��.
			if(dy+1 <= N && map[dx][dy+1] != 1) dfs(dx,dy+1,0);
			if(dx+1 <= N && dy+1 <= N && map[dx+1][dy+1] != 1 && map[dx][dy+1] != 1 && map[dx+1][dy] != 1) dfs(dx+1,dy+1,1);
			break;
		}
		case 1:{//�밢������ ���°��.
			if(dy+1 <= N && map[dx][dy+1] != 1) dfs(dx,dy+1,0);
			if(dx+1 <= N && dy+1 <= N && map[dx+1][dy+1] != 1 && map[dx][dy+1] != 1 && map[dx+1][dy] != 1) dfs(dx+1,dy+1,1);
			if(dx+1 <= N && map[dx+1][dy] != 1) dfs(dx+1,dy,2);
			break;
		}
		case 2:{//������ ���°��.
			if(dx+1 <= N && map[dx+1][dy] != 1) dfs(dx+1,dy,2);
			if(dx+1 <= N && dy+1 <= N && map[dx+1][dy+1] != 1 && map[dx][dy+1] != 1 && map[dx+1][dy] != 1) dfs(dx+1,dy+1,1);
			break;
		}
		}
	}//end dfs method.
}
