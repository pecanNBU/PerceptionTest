public class Perception {
	int learnRate;// learn rate
	int d;// demension of the data
	int[] w; // weight vector
	int b; // offset

	public Perception(int learnRate, int d, int[] w, int b) {
		this.learnRate = learnRate;
		this.d = d;
		this.w = w;
		this.b = b;
	}

	/*
	 * �ж����ѵ������x �����ģ����ʵ�������Ƿ������
	 */
	private boolean judgeHasError(int[] x) {
		int y=0;
		for (int i = 0; i < d - 1; i++) {
			y += w[i] * x[i];
		}
		y = x[d - 1] * (y + b);
		if (y <= 0)
			return false;
		else {
			return true;
		}

	}

	private void adjustParam(int[] x) {
		for (int i = 0; i < d - 1; i++) {
			w[i] += learnRate * x[i] * x[d - 1];
		}
		b += learnRate * x[d - 1];
		return;
	}

	public void TrainData(int data[][], int num) throws InterruptedException {
		int count = 0;
		boolean isOver = false;
		while (!isOver) {
			System.out.println("b:" + b);
			for (int i = 0; i < data[0].length - 1; i++) {
				System.out.println(w[i]);
			}
			for (int i = 0; i < num; ++i) {
				if (!judgeHasError(data[i])) {
					System.out.println(i + "����������" + (++count));
					adjustParam(data[i]);
					isOver = false;
					break;
				} else
					isOver = true;
			}
		}
		//
		System.err.println(count);
		System.out.println("b:" + b);
		for (int i = 0; i < data[0].length - 1; i++) {
			System.out.println(w[i]);
		}

	}

	public static void main(String args[]) {
		// data�����а��� ��ʵ����͸�ʵ���㣬�������������һλԪ�ش�����Ϊ����ʵ���㣨1������ʵ����-1����ʵ����
		// ѵ������һ���������飬ǰ��������ʵ��
		int data[][] = { { 3, 3, -1 }, { 4, 3, 1 }, { 1, 1, -1 }, { 1, 3, -1 } };
		int[] w = { 0, 0 };
		Perception p = new Perception(1, 3, w, 1);
		try {
			p.TrainData(data, data.length);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
