import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 1753. �ִܰ�� Gold 5 https://www.acmicpc.net/problem/1753
 * 
 * 
 */
public class BOJ_1753_�ִܰ��_200212 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// input
		int V = sc.nextInt(), E = sc.nextInt();
		short K = sc.nextShort();
		ArrayList<HashMap<Short, Byte>> list = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			list.add(new HashMap<>());
		}
		short u, v;
		byte w;
		for (; E > 0; E--) {
			u = sc.nextShort();
			v = sc.nextShort();
			w = sc.nextByte();
			if (list.get(u).get(v) == null)
				list.get(u).put(v, w); // ������ key, ����ġ�� value
			else if (list.get(u).get(v) > w)
				list.get(u).replace(v, w); // ���� ���� ������ ����ġ�� ���� ��� ����
		}

		// solve
		boolean[] visited = new boolean[V + 1];
		Priority_Queue pq = new Priority_Queue();
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		pq.push(new Pair(K, 0));
		// initialize end
		while (!pq.isEmpty()) {

			Pair p = pq.pop();
			if (visited[p.v])
				continue;
			visited[p.v] = true;
//			for (u = 1; u <= V; u++) {
//				if (!list.get(p.v).containsKey(u))
//					continue;
//				if (!visited[u] && dist[u] > (list.get(p.v).get(u) + dist[p.v])) { // �湮���� �ʾҰ�, u ���������� �Ÿ���
//																					// (list.get(p.v).get(u) +
//																					// dist[p.v]���� Ŭ ���
//																					// (p.v�� ��ġ�� �Ÿ����� Ŭ ���)
//					dist[u] = list.get(p.v).get(u) + dist[p.v]; // �Ÿ� ����
//					pq.push(new Pair(u, dist[u])); // pq�� �ֱ�
//				}
//			}
			list.get(p.v).forEach((key, value) -> {
				if(!visited[key] && dist[key] > value + dist[p.v]) {
					dist[key] = value + dist[p.v];
					pq.push(new Pair(key, dist[key]));
				}
			});
		}

		// output
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			sb.append((dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]) + "\n"); // ��� ������ INF
		}
		System.out.println(sb);
	}// end of main
} // end of class

class Priority_Queue {
	Pair[] q = new Pair[300000];
	int end = 0;

	boolean isEmpty() {
		if (end == 0)
			return true;
		return false;
	}

	Pair pop() {
		Pair p = q[1];
		q[1] = q[end--]; // ������ ���Ҹ� top���� �ø���
		int i = 1;
		while (i < end) { // �������鼭 ��
			int k = min(i, i * 2, i * 2 + 1);
			if (k == i) { // i�� �� ���� -> ���� ��
				break;
			}
			swap(i, k);
			i = k;
		}

		return p;
	}

	void push(Pair a) {
		q[++end] = a;
		int i = end;
		while (i > 1) {// �ö󰡸鼭 ��
			if (q[i].dist > q[i / 2].dist)
				break; // ���� �θ𺸴� ŭ -> ���� ��
			swap(i, i / 2);
			i /= 2;
		}
	}

	void swap(int i1, int i2) { // i1��°�� i2��° ���� ����
		Pair tmp = q[i1];
		q[i1] = q[i2];
		q[i2] = tmp;
	}

	int min(int i1, int i2, int i3) { // dist�� ���� ���� �ε��� ��ȯ
		if (i2 > end)
			return i1;
		if (i3 > end) {
			if (q[i1].dist < q[i2].dist)
				return i1;
			return i2;
		}
		if (q[i1].dist < q[i2].dist) {
			if (q[i1].dist < q[i3].dist)
				return i1;
			return i3;
		}
		if (q[i2].dist < q[i3].dist)
			return i2;
		return i3;
	}
}

class Pair {
	short v;
	int dist;

	Pair(short v, int dist) {
		this.v = v;
		this.dist = dist;
	}
}