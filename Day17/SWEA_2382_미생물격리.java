import java.io.*;
import java.util.*;

public class SWEA_2382_�̻����ݸ� {
	static class Virus{
		int x;
		int y;
		int direction;
		int size;
		int max;
		public Virus(int x, int y, int direction, int size, int max) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.size = size;
			this.max = max;
		}
	}
	static Virus[][] map;
	static int[][] dir = {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; //���� �����ֱ����� �ǹ̾��� �� �ϳ� ����.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //���� ����.
			int M = Integer.parseInt(st.nextToken()); //�ݸ� �ð�.
			int K = Integer.parseInt(st.nextToken()); //�̻��� ��.
			map = new Virus[N][N];
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int dx = Integer.parseInt(st.nextToken());
				int dy = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				map[dx][dy] = new Virus(dx,dy,direction,size,size);
			}
			int time = 0;
			Queue<Virus> q = new LinkedList<>();
			while(time <M) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(map[i][j] != null) {
							q.add(map[i][j]);
							map[i][j] = null;
						}
					}
				}//�ϴ� �� ��������.
				while(!q.isEmpty()) {
					Virus v = q.poll();
//					System.out.println(v.x + " "  + v.y + " " + v.direction + " " + v.size);
					int direction = v.direction;
					int size = v.size;
					int dx = v.x + dir[direction][0];
					int dy = v.y + dir[direction][1];
					if(dx == 0 || dx == N-1 || dy == 0 || dy == N-1) {
						direction = direction%2 == 1 ? direction+1 : direction-1;
						size = size/2;
						if(size>0) map[dx][dy] = new Virus(dx,dy,direction,size,size);
					}
					else{
						if(map[dx][dy] == null) map[dx][dy] = new Virus(dx,dy,direction,size,size);
						else {
							if(map[dx][dy].max < size) {
								map[dx][dy].direction = direction;
								map[dx][dy].max = size;
							}
							map[dx][dy].size += size;
						}
					}
				}//end queue.
				time++;
			}//�ùķ��̼� ��ŸƮ.
			int answer = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] != null) answer += map[i][j].size;
				}
			}
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.println(sb);
	}//end main.
}//end class.
