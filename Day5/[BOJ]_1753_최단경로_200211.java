import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_�ִܰ�� {
	static class Pair implements Comparable<Pair>{
		int end;
		int weight;
		public Pair(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		public int compareTo(Pair p1) {
			return this.weight > p1.weight ? 1 : -1; //Ÿ���� ��������� �켱���� ��������ֱ�.
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		ArrayList<Pair>[] graph = new ArrayList[V+1];
        for (int i = 1; i <=V; i++) {
            graph[i] = new ArrayList<Pair>();
        }
		int start_v = sc.nextInt();
		for(int i=0; i<E; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			graph[start].add(new Pair(end,weight));
		}//end input.
		
		int distance[] = new int[V+1];
		boolean[] visited = new boolean[V+1];
		for(int i=1; i<=V; i++){
            distance[i] = Integer.MAX_VALUE;
        }//distance �迭 �� �ƽ��� �ʱ�ȭ.
        distance[start_v] =0;
        visited[start_v] =true;//ó�� ��� �湮 üũ.
        PriorityQueue<Pair> q= new PriorityQueue<>();
        
        q.add(new Pair(start_v,0));
        
        while(!q.isEmpty()) {
        	Pair now = q.poll();
        	for(Pair next : graph[now.end]) {
        		if(distance[next.end] > distance[now.end] + next.weight) {
        			distance[next.end] = distance[now.end] + next.weight;
        			q.add(new Pair(next.end, distance[next.end])); //�����Ͱ� ������Ʈ�Ǹ� �� �ٲ��ְ�  q�� �� ���� ������ �־��ֱ�.
        		}
        	}
        }//��������������.
      	//���⸦ ť�� �ۼ��ϱ�.
        
        for(int i=1; i<=V; i++) {
        	if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
        	else System.out.println(distance[i]);
        }//���.
	}//end main.
}
