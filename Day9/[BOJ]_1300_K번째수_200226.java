import java.io.*;
import java.util.*;

public class BOJ_K��°�� {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int left = 1;
		int right = K; //M��°�� ���� M���� Ŭ �� �ֳ�? ����.
		int answer = 0;
		while(left <= right) { //���� �� ���� ���������� �������.
			int mid = (left + right)/2;
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				cnt += Math.min(N, mid/i); //�׸��� K�� N���� Ŭ ��쿡�� 1���� ����, 1���� N�� ���� ����ִµ� K/i �� ������ �װ� �Ѿ�� ��Ȳ�� �߻���. �װ� ���� ó��. -- ����2.
			}
			if(cnt < K) left = mid+1;
			else {
				// mid ���� ���� ������ K ���� ũ�� �ϴ� �ĺ����̾�.
				answer = mid;
				right = mid-1;
			}
		}
		System.out.println(answer);
	}//end main.
}//end class.
