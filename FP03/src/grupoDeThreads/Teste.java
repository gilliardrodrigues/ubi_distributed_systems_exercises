package grupoDeThreads;

public class Teste {

	public static void main (String[] arg){
		MyThread Ta, Tb, Tc;
		ThreadGroup this_group;
		this_group = Thread.currentThread().getThreadGroup();
		System.out.println("O nome do grupo é: " + this_group.getName());
		System.out.println("O nº de Threads activas no grupo é " + this_group.activeCount());
		Ta=new MyThread ("Thread A");
		Tb=new MyThread ("Thread B");
		Tc=new MyThread ("Thread C");
		
		Ta.start();
		Tb.start();
		Tc.start();
		
		System.out.println("O nome do grupo é: " + this_group.getName());
		System.out.println("O nº de Threads activas no grupo é " + this_group.activeCount());
		
		ThreadGroup tg = new ThreadGroup("O meu grupo");
		MyThread Td = new MyThread(tg, "Thread D");
		Td.start();
		System.out.println("O nome do grupo é: " + tg.getName());
		System.out.println("O nº de Threads activas no grupo é " + tg.activeCount());
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		this_group.interrupt();
	}
}
