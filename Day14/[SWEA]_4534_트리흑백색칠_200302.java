import java.io.*;
import java.util.*;

public class SWEA_4534_Ʈ������ĥ {
	static ArrayList<Integer>[] list;
	static long mod = 1000000007; //������ ��.
	static long[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int test=1; test<=TC; test++) {
			long answer = 0;
			int N = Integer.parseInt(br.readLine());
			list = new ArrayList[N+1];
			dp = new long[N+1][2];
			for(int i=1; i<=N; i++) {
				list[i] = new ArrayList<Integer>();
			}
			for(int i=1; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				list[start].add(end);
				list[end].add(start);
			}//���� �Է¹ޱ�.
			answer = (dfs(0,1,0) + dfs(0,1,1)) % mod; //ù��° 1�� �����ؼ� color �� ���̳� ���̳�.
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}//end TestCase.
		System.out.print(sb);
	}//end main.
	public static long dfs(int parent, int now, int color) {
		int cnt = 0; //�������� �ƴ��� Ȯ���ϱ� ���� ����.
		for(int i=0; i<list[now].size(); i++) {
			if(list[now].get(i) == parent) continue;
			cnt++;
		}
		if(cnt == 0) {
			//System.out.println("��������̴�.");
			return 1; //���� ����� ���.
		}
		if(dp[now][color] != 0)
			return dp[now][color];
		if(color == 0) { //������ ���ϰ��.
			long black = 1;
			for(int i=0; i<list[now].size(); i++) {
				if(list[now].get(i) == parent) continue;
				long sum = dfs(now,list[now].get(i),1);
				black = (black*sum)%mod;
			}
			return dp[now][color] = black;
		}
		else { //������ ���ϰ��.
			long white = 1;
			for(int i=0; i<list[now].size(); i++) {
				if(list[now].get(i) == parent) continue;
				long sum = (dfs(now,list[now].get(i),0) + dfs(now,list[now].get(i),1))%mod;
				white = (white*sum)%mod;
			}
			return dp[now][color] = white;
		}
	}
}//end class.
