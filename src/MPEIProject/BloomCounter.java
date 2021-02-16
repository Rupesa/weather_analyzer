package MPEIProject;

public class BloomCounter {
	
	private int k;
	private int[] array;
	private int[] a;
	private int[] b;
	
	public BloomCounter(int len, int m) {
		k = (int)Math.ceil(((len/m) * Math.log(2)));
		array = new int[len];
		a = new int[k];
		b = new int[k];
		int i = 0;
		while (i < k) {
			a[i] = (int) (Math.random() * Math.pow(2, 31) + 1);
			b[i] = (int) (Math.random() * Math.pow(2, 31) + 1);
			i++;
		}
	}
	
	public void insert(String str) {
		int indice;
		for(int i = 0; i < k; i++) {
			indice = (int) Hashing.hashOf(str, a[i], b[i]);
			indice = Math.abs(indice % array.length);
			array[indice]++;
		}
	}
	
	public void remove(String str) {
		int indice;
		for(int i = 0; i < k; i++) {
			indice = (int) Hashing.hashOf(str, a[i], b[i]);
			indice = Math.abs(indice % array.length);
			if (array[indice] > 0) array[indice]--;
		}
	}
	
	public boolean verify(String str) {
		int indice;
		boolean ver = true;
		for(int i = 0; i < k; i++) {
			indice = (int) Hashing.hashOf(str, a[i], b[i]);
			indice = Math.abs(indice % array.length);
			if (array[indice] == 0) ver = false;
		}
		return ver;
	}
	
	public int howMany(String str) {
		int indice = (int) Hashing.hashOf(str, a[0], b[0]);
 		int min = array[Math.abs(indice % array.length)];
		for(int i = 0; i < k; i++) {
			indice = (int) Hashing.hashOf(str, a[i], b[i]);
			indice = Math.abs(indice % array.length);
			if (array[indice] < min) min = array[indice];
		}
		return min;
	}

}
