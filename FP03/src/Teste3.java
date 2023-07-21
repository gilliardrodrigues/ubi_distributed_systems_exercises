
public class Teste3 {

	public static void main(String[] str) throws InterruptedException {
		
		final int MAX_TAM = 100000000;
		int[] A = new int[MAX_TAM];
		int[] B = new int[MAX_TAM];
		int[] C = new int[MAX_TAM];
		
		for(int i = 0; i < MAX_TAM; i++) {
			
			A[i] = (int) (Math.random() * 100);
			B[i] = (int) (Math.random() * 100);
		}
		
		long startTime = System.nanoTime();
		
		var threadA = new ThreadSoma(A, B, C, 0, MAX_TAM/2);
		var threadB = new ThreadSoma(A, B, C, MAX_TAM/2, MAX_TAM);
		
		threadA.join();
		threadB.join();
		
		long endTime = System.nanoTime();
		
		long timeElapsed = endTime - startTime;
		System.out.println("Time elapsed in nanoseconds: " + timeElapsed);
		
		System.out.println("Current value at last position of array C: " + C[MAX_TAM-1]);
		int somaEmC = 0;
		for(int i = 0; i < MAX_TAM; i++) {
			somaEmC += C[i];
		}
		System.out.println("Sum of all values of array C: " + somaEmC);
	}
}
