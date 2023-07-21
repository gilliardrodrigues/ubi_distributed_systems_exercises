package exercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Servidor {

	private ArrayList<Aluno> alunosRegistrados;
	private int numeroAcessos;

	public static String lerString() {
		String s = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			s = in.readLine();
		} catch (IOException e) {
			System.out.println("Erro ao ler fluxo de entrada.");
		}
		return s;
	}

	public int registrarAluno(Aluno novoAluno) {

		if (alunosRegistrados.contains(novoAluno)) {
			throw new AlunoJaRegistradoException("Este aluno já se encontra registrado no sistema!");
		} else {
			this.alunosRegistrados.add(novoAluno);
		}
		return alunosRegistrados.size();
	}

	public List<Aluno> consultarAlunosRegistrados() {

		return this.alunosRegistrados;
	}

	public int consultarNumeroDeAcessos() {

		return this.numeroAcessos;
	}

	public List<Aluno> consultarDadosPeloNome(String nome) {

		return this.alunosRegistrados.stream()
				.filter(aluno -> aluno.getNome().toUpperCase().startsWith(nome.toUpperCase()))
				.collect(Collectors.toList());
	}

	public Servidor() {

		if(this.alunosRegistrados == null) {
			this.alunosRegistrados = new ArrayList<Aluno>();
		}
		ServerSocket servidor = null;
		ObjectInputStream entrada;
		ObjectOutputStream saida;
		Socket conexao;
		int porta = 1616;
		int opcaoEscolhida;
		String msg;
		String opcoesMenu = "<Servidor> Insira um número de acordo com a operação que deseja realizar:\n"
				+ "1 - Registrar aluno\n" + "2 - Consultar alunos registrados\n"
				+ "3 - Consultar número de acessos ao servidor até o momento\n" 
				+ "4 - Obter dados de um aluno\n" + "Outro valor - Sair\n";

		try {
			// O ServerSocket vai esperar por um pedido de ligação na porta definida:
			servidor = new ServerSocket(porta);
			System.out.println("<Servidor> À escuta na porta: " + porta);

			while (true) {
				// O método accept() bloqueia a conexão até que o servidor receba um pedido de
				// conexão:
				conexao = servidor.accept();
				System.out.println("<Servidor> Cliente conectado: " + conexao.getInetAddress().getHostAddress());
				this.numeroAcessos += 1;
				// Obtendo os fluxos de entrada e saída:
				saida = new ObjectOutputStream(conexao.getOutputStream());
				entrada = new ObjectInputStream(conexao.getInputStream());

				saida.writeObject("Conexão estabelecida na porta " + porta + "!\n" + opcoesMenu);
				saida.flush();
				while (true) {
					opcaoEscolhida = Integer.parseInt((String) entrada.readObject());
					System.out.println("<Cliente> Opção escolhida: " + opcaoEscolhida);
					if(opcaoEscolhida == 1) {
						saida.writeObject(
								"Ok. Insira, respectivamente, o número, nome, curso e contato do aluno a ser registrado, separados por vírgula: ");
						saida.flush();
						msg = (String) entrada.readObject();
						String[] dadosAluno = msg.split(",");
						Aluno aluno = new Aluno(dadosAluno[0].trim(), dadosAluno[1].trim(), dadosAluno[2].trim(), dadosAluno[3].trim());
						int numAlunosRegistrados = registrarAluno(aluno);
						saida.writeObject(numAlunosRegistrados);
						saida.flush();
						System.out.println("<Servidor> Aluno registrado: " + aluno.toString() + "\nNúmero atual de registros: " + numAlunosRegistrados);
					}
					else if(opcaoEscolhida == 2) {
						String alunosRegistrados = consultarAlunosRegistrados().toString();
						saida.writeObject(alunosRegistrados);
						saida.flush();
						System.out.println("<Servidor> Alunos registrados: \n" + alunosRegistrados);
					}
					else if(opcaoEscolhida == 3) {
						saida.writeObject(consultarNumeroDeAcessos());
						saida.flush();
						System.out.println("<Servidor> Número de acessos ao servidor: " + consultarNumeroDeAcessos());
					}
					else if(opcaoEscolhida == 4) {
						saida.writeObject("Ok. Insira o nome do aluno a ser consultado: ");
						saida.flush();
						String nome = (String) entrada.readObject();
						String resultado = consultarDadosPeloNome(nome).stream()
											.map(elemento -> elemento.getNumeroAndContato())
											.collect(Collectors.toList())
											.toString();
						saida.writeObject(resultado);
						saida.flush();
						System.out.println("<Servidor> Resultado(s) para o nome '" + nome + "':\n" + resultado);
					}
					else {
						saida.writeObject("Conexão encerrada!");
						entrada.close();
						saida.close();
						conexao.close();
						System.out.println("<Servidor> Conexão encerrada! Aguardando um novo cliente...");
						break;
					}
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String args[]) {

		var servidor = new Servidor();
	}
}
