
public class ThreadSoma extends Thread {
	
	int[] A;
	int[] B;
	int[] C;
	int p;
	int u;
	
	public ThreadSoma(int[] A, int[] B, int[] C, int p, int u) {
		
		super();
		this.A = A;
		this.B = B;
		this.C = C;
		this.p = p;
		this.u = u;
		start();
	}
	
	public void run() {
		
		for(int i = this.p; i < this.u; i++) {
			this.C[i] = this.A[i] + this.B[i];
		}
	}
	
}
