import java.io.*;
import java.util.*;

public class SWEA_������ {
	static int[] month;
	static int[] price;
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(st.nextToken());
		month = new int[12];
		price = new int[4];
		for(int test=1; test<=TC; test++) {
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			answer = price[3]; //�ƽ�ġ�� 1��� ���°ɷ� ����.
			//end input.
			start(0,0);
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end testCase.
		System.out.print(sb);
	}//end main.
	public static void start(int idx, int sum) {
		if(idx >= 12) {
			if(answer > sum) answer = sum;
			return ;
		}//12�� �� ����� ��쿡�� answer���� ��.
		if(month[idx] == 0) start(idx+1,sum);
		else {
			start(idx+1 , sum + (price[0]*month[idx]));
			start(idx+1 , sum + price[1]);
			start(idx+3 , sum + price[2]);
		}
	}//end start.
}//end class.
