import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_���ÿ� {
	
	static int row;
	static int col;
	static int N; //��ũ�� ó�� ����.
	static Shark[][] Shark_map;
	static int ans = 0;
	static Queue<Shark> q = new LinkedList();
	
	static class Shark{
		int x;
		int y;
		int speed;
		int direction;
		int size;
		
		public Shark(int x, int y, int speed, int direction, int size) {
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}//��� Ŭ����.
	
	
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		Shark_map = new Shark[row+1][col+1];
		N = sc.nextInt();
		for(int i=0; i<N; i++) {
			int temp_x = sc.nextInt();
			int temp_y = sc.nextInt();
			int temp_speed = sc.nextInt();
			int temp_direction = sc.nextInt();
			int temp_size = sc.nextInt();
			if(temp_direction == 1 || temp_direction == 2) {
				Shark_map[temp_x][temp_y] = new Shark(temp_x, temp_y, temp_speed % (2*(row-1)), temp_direction, temp_size);
			}
			else Shark_map[temp_x][temp_y] = new Shark(temp_x, temp_y, temp_speed % (2*(col-1)), temp_direction, temp_size);
		}//�Է¹��� ��ǥ�� shark ������ �߰�.
		
		for(int i=1; i<=col; i++) {
			find_shark(i);
			for(int i2=1; i2<=row; i2++) {
				for(int j=1; j<=col; j++) {
					if(Shark_map[i2][j] != null) {
						q.add(Shark_map[i2][j]);
						Shark_map[i2][j] = null;
					}
				}
			}
			move_shark();
		}//end for.
		System.out.println(ans);
	}//end main.
	
	public static void find_shark(int col) {
		for(int i=1; i<=row; i++) {
			if(Shark_map[i][col] != null) {
				ans += Shark_map[i][col].size;
				Shark_map[i][col] = null;
				break;
			}
		}
	}//���� ����� ��� ã�Ƽ� ������ ��ȯ�ް� ���̱�.
	
	public static void move_shark() {
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			int speed = q.peek().speed;
			int distance = speed;
			int direction = q.peek().direction;
			int size = q.peek().size;
			q.poll();
			while(distance != 0) {
				switch(direction) {
				case 1:{
					if(x-1 < distance) {
						distance -= (x-1);
						x = 1;
						direction = 2;
					}
					else {
						x -= distance;
						distance = 0;
					}
					break;
				}
				case 2:{
					if(row-x < distance) {
						distance -= (row-x);
						x = row;
						direction = 1;
					}
					else {
						x += distance;
						distance = 0;
					}
					break;
				}
				case 3:{
					if(col-y < distance) {
						distance -= (col-y);
						y = col;
						direction = 4;
					}
					else {
						y += distance;
						distance = 0;
					}
					break;
				}
				case 4:{
					if(y-1 < distance) {
						distance -= (y-1);
						y = 1;
						direction = 3;
					}
					else {
						y -= distance;
						distance = 0;
					}
					break;
				}	
				}//end switch.
			}//end while1.
			if(Shark_map[x][y] == null) Shark_map[x][y] = new Shark(x,y,speed,direction,size);
			else if(Shark_map[x][y].size < size) Shark_map[x][y] = new Shark(x,y,speed,direction,size);			
		}//end while2.
	}//�� �ѹ� �� ���鼭 �޾ƿ� shark���� ó�����־� �ٽ� �ʿ� �־��ֱ�. + null�� �ƴ� ��� ������ �񱳸� ���� �Ѹ����� �����.
}
