
public class Heap {

	int[] arr = new int[] { 4, 10, 3, 5, 1 };
	int size = arr.length;

	public void minHeap(int p) {
		if (p == -1)
			return;
		int l = p * 2 + 1;
		int r = p * 2 + 2;
		if (r == size) {
			if (arr[p] > arr[l])
				swap(p, l);
		} else {
			if (arr[l] < arr[r]) {
				if (arr[p] > arr[l])
					swap(p, l);
			} else {
				if (arr[p] > arr[r])
					swap(p, r);
			}
		}
		minHeap(p - 1);
	}

	public void maxHeap(int p) {
		if (p == -1)
			return;
		int l = p * 2 + 1;
		int r = p * 2 + 2;
		if (r == size) {
			if (arr[p] < arr[l])
				swap(p, l);
		} else {
			if (arr[l] > arr[r]) {
				if (arr[p] < arr[l])
					swap(p, l);
			} else {
				if (arr[p] < arr[r])
					swap(p, r);
			}
		}
		maxHeap(p - 1);
	}

	public void heapSort() {
		if (size == 1)
			return;
		maxHeap(size / 2 - 1);
		swap(0, size - 1);
		size--;
		heapSort();
	}

	public void swap(int pos1, int pos2) {
		int temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}

	public static void main(String[] args) {
		Heap h = new Heap();
		h.heapSort();
		for (int i : h.arr)
			System.out.print(i + " ");
	}

}
