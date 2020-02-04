import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BOJ_16637_��ȣ�߰��ϱ�_200204 {
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException, NumberFormatException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String[] expression = new String[len];
		List<String> res = new LinkedList<String>();
		int[] check = new int[len / 2];
		
		for(int i = 0; i < expression.length; i++) {
			expression[i] = str.substring(i, i + 1);
		} // �迭�� ���� ����
		
		if(expression.length == 1) { // ���̰� 1�϶�
			max = Integer.parseInt(expression[0]);
		} else if(expression.length == 3) { // ���̰� 3�϶�
			max = Integer.parseInt(cal(expression[0], expression[1], expression[2]));
		} else { // ���̰� 5�̻�
			subset(expression, res, check, 0); // ��ȣ�� ����� �� �κ��������� ���ϱ�
		}
		
		System.out.println(max);
	}
	
	public static void subset(String[] expression, List<String> res, int[] check, int pivot) {
		if(pivot == check.length) { // ��ȣ�� ������ �� �ִ� �κ����� ���ϸ� ��� ����
			addBranket(expression, res, check);
		} else {
			if(pivot > 0 && check[pivot - 1] == 1) { // ��ȣ�� ��ø�� �� ����
				check[pivot] = 0;
				subset(expression, res, check, pivot + 1);
			} else {
				check[pivot] = 0;
				subset(expression, res, check, pivot + 1);
				check[pivot] = 1;
				subset(expression, res, check, pivot + 1);
			}
		}
	}
	
	public static void addBranket(String[] expression, List<String> res, int[] check) { // ����� �� �ֵ��� list�� ���� �� ���
		res = new LinkedList<>(); // ��� ������ list �ʱ�ȭ
		for(int i = 0; i < check.length; i++) {
			if(check[i] == 1) { // ���� ��ȣ�� ������ ���� ����, �� �������� üũ�ϰ� ��� ������� �߰�����
				if(i > 0) {
					res.remove(res.size() - 1);
				}
				res.add(cal(expression[i * 2], expression[i * 2 + 1], expression[i * 2 + 2]));
		
			} else {
				if(i > 0) { // �� ���� �ƴϸ� �����ڿ� ���� ���ڸ� �߰�
					res.add(expression[i * 2 + 1]);
					res.add(expression[i * 2 + 2]);
				} else { // �� ���̸� ����, ������, ���� ��� �߰�
					res.add(expression[i * 2]);
					res.add(expression[i * 2 + 1]);
					res.add(expression[i * 2 + 2]);
				}
			}
		}
		
		int tmp = Integer.parseInt(cal(res.get(0), res.get(1), res.get(2)));
		for(int i = 2; i <= res.size() / 2; i++) { // �������� ���
			tmp = Integer.parseInt(cal(Integer.toString(tmp), res.get(i * 2 - 1), res.get(i * 2)));
		}
		if(tmp > max) max = tmp; // �ִ밪���� Ȯ��
	}
	
	public static String cal(String num1, String operator, String num2) { // ��� method
		int a = Integer.parseInt(num1);
		int b = Integer.parseInt(num2);
		
		if(operator.equals("+")) {
			return Integer.toString(a + b);
		} else if (operator.equals("-")) {
			return Integer.toString(a - b);
		} else {
			return Integer.toString(a * b);
		}
	}
}
