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
	 * 判断针对训练数据x 估测的模型与实际数据是否有误差
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
					System.out.println(i + "调整次数：" + (++count));
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
		// data数组中包括 正实例点和负实例点，其中数组中最后一位元素代表其为何种实例点（1代表正实例，-1代表负实例）
		// 训练数据一共包括三组，前两组是正实例
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
