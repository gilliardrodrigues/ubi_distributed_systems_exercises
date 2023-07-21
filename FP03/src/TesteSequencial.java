
public class TesteSequencial {

	public static void main(String[] str) {
		
		final int MAX_TAM = 100000000;
		int[] A = new int[MAX_TAM];
		int[] B = new int[MAX_TAM];
		int[] C = new int[MAX_TAM];
		
		for(int i = 0; i < MAX_TAM; i++) {
			
			A[i] = (int) (Math.random() * 100);
			B[i] = (int) (Math.random() * 100);
		}
		long startTime = System.nanoTime();
		for(int i = 0; i < MAX_TAM; i++) {
			C[i] = A[i] + B[i];
		}
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Time elapsed in nanoseconds: " + timeElapsed);
	}
}
