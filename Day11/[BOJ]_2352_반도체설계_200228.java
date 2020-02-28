import java.io.*;
import java.util.*;

public class BOJ_2352_�ݵ�ü���� {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] list = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}//end input.
		int[] dp = new int[N+1];
		int top = -1;
		dp[++top] = list[1];
		int max = Integer.MIN_VALUE;
		for(int i=2; i<=N; i++) {
			if(list[i] > dp[top]) dp[++top] = list[i];
			else {
				int idx = Arrays.binarySearch(dp, 0,top+1,list[i]); //���̺귯�� ���. 
				dp[-(idx+1)] = list[i];
			}
		}//end for.
		System.out.println(top+1);//top�� ���ÿ��̹Ƿ� +1 ����� ��.
		//�ᱹ ������� ���ϴ°Ͱ� ����. ������ ��.. �ε����� �����Կ� ���� ���ڰ� Ŀ���� �� ���� �ᱹ �ȸ����⋚����.
	}//end main.
}//end class.
