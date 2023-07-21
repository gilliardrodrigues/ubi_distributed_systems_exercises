package alunosMultithread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServidor extends Thread {

	private ServerSocket servidor;
	private Socket conexao;
	private Servico servico;
	private String msg;
	private int opcaoEscolhida;
	private String opcoesMenu = "<Servidor> Insira um n�mero de acordo com a opera��o que deseja realizar:\n"
			+ "1 - Registrar aluno\n" + "2 - Consultar alunos registrados\n"
			+ "3 - Consultar n�mero de acessos ao servidor at� o momento\n" + "4 - Obter dados de um aluno\n"
			+ "Outro valor - Sair\n";
	
	
	public ThreadServidor(ServerSocket servidor, Servico servico) {
		super();
		this.servidor = servidor;
		this.servico = servico;
		start();
	}

	public void run() {

		try {
			while (true) {
				// O m�todo accept() bloqueia a conex�o at� que o servidor receba um pedido de
				// conex�o:
				conexao = servidor.accept();
				System.out.println("<Servidor> Cliente conectado: " + conexao.getInetAddress().getHostAddress());
				servico.incrementarNumeroDeAcessos();
				System.out.println("N�mero de threads ativas: " + Thread.activeCount());
				// Obtendo os fluxos de entrada e sa�da:
				var saida = new ObjectOutputStream(conexao.getOutputStream());
				var entrada = new ObjectInputStream(conexao.getInputStream());

				saida.writeObject("Conex�o estabelecida na porta " + conexao.getLocalPort() + "!\n" + opcoesMenu);
				saida.flush();
				while (true) {
					opcaoEscolhida = Integer.parseInt((String) entrada.readObject());
					System.out.println("<Cliente> Op��o escolhida: " + opcaoEscolhida);
					if (opcaoEscolhida == 1) {
						saida.writeObject(
								"Ok. Insira, respectivamente, o n�mero, nome, curso e contato do aluno a ser registrado, separados por v�rgula: ");
						saida.flush();
						msg = (String) entrada.readObject();
						String[] dadosAluno = msg.split(",");
						Aluno aluno = new Aluno(dadosAluno[0].trim(), dadosAluno[1].trim(), dadosAluno[2].trim(), dadosAluno[3].trim());
						int numAlunosRegistrados = servico.registrarAluno(aluno);
						saida.writeObject(numAlunosRegistrados);
						saida.flush();
						System.out.println("<Servidor> Aluno registrado: " + aluno.toString() + "\nN�mero atual de registros: "
								+ numAlunosRegistrados);
					} else if (opcaoEscolhida == 2) {
						String alunosRegistrados = servico.consultarAlunosRegistrados().toString();
						saida.writeObject(alunosRegistrados);
						saida.flush();
						System.out.println("<Servidor> Alunos registrados: \n" + alunosRegistrados);
					} else if (opcaoEscolhida == 3) {
						saida.writeObject(servico.consultarNumeroDeAcessos());
						saida.flush();
						System.out.println("<Servidor> N�mero de acessos ao servidor: " + servico.consultarNumeroDeAcessos());
					} else if (opcaoEscolhida == 4) {
						saida.writeObject("Ok. Insira o nome do aluno a ser consultado: ");
						saida.flush();
						String nome = (String) entrada.readObject();
						String resultado = servico.consultarDadosPeloNome(nome).toString();
						saida.writeObject(resultado);
						saida.flush();
						System.out.println("<Servidor> Resultado(s) para o nome '" + nome + "':\n" + resultado);
					} else {
						saida.writeObject("Conex�o encerrada!");
						entrada.close();
						saida.close();
						conexao.close();
						System.out.println("<Servidor> Conex�o encerrada! Aguardando um novo cliente...");
						break;
					}
				}
			}
		} catch (IOException | NumberFormatException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
