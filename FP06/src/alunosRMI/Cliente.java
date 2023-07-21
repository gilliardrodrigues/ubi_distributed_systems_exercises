package alunosRMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {

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

	public static int lerInteiro() {

		while (true) {
			try {
				return Integer.parseInt(lerString().trim());
			} catch (NumberFormatException e) {
				System.out.println("Não é um inteiro válido!!!");
			}
		}
	}

	public static void main(String[] argv) {

		String opcoesMenu = "<Servidor> Insira um número de acordo com a operação que deseja realizar:\n" + "1 - Registrar aluno\n"
				+ "2 - Consultar alunos registrados\n" + "3 - Consultar número de acessos ao servidor até o momento\n"
				+ "4 - Obter dados de um aluno\n" + "Outro número - Sair";
		int opcaoEscolhida;

		try {
			var alunoService = (AlunoService) Naming.lookup("AlunoService");
			alunoService.incrementarNumAcessos(); 
			while(true) {
				System.out.println(opcoesMenu);
				opcaoEscolhida = lerInteiro();
				if(opcaoEscolhida == 1) {
					System.out.println("<Servidor> Ok. Insira, respectivamente, o número, nome, curso e contato do aluno a ser registrado, separados por vírgula: ");
					String entrada = lerString();
					String[] dadosAluno = entrada.split(","); 
					Aluno novoAluno = new Aluno(dadosAluno[0].trim(), dadosAluno[1].trim(), dadosAluno[2].trim(), dadosAluno[3].trim());
					int numAlunosRegistrados = alunoService.registrar(novoAluno);
					System.out.println("<Servidor> Aluno registrado: " + novoAluno.toString() + "Número atual de registros: " + numAlunosRegistrados + "\n");
				} else if(opcaoEscolhida == 2) {
					String alunosRegistrados = alunoService.consultarLista().toString();
					System.out.println("<Servidor> Alunos registrados: \n" + alunosRegistrados + "\n");
				} else if(opcaoEscolhida == 3) {
					int numAcessos = alunoService.consultarNumAcessos();
					System.out.println("<Servidor> Número de acessos ao servidor: " + numAcessos + "\n");
				} else if(opcaoEscolhida == 4) {
					System.out.println("<Servidor> Ok. Insira o nome do aluno a ser consultado: ");
					String nome = lerString();
					String resultado = alunoService.consultarNumeroAndContatoPeloNome(nome).toString();
					System.out.println("<Servidor> Resultado(s) para o nome '" + nome + "':\n" + resultado + "\n");
				} else {
					System.out.println("Conexão encerrada!");
					break;
				}
			}
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println("Exceção recebida: " + e.getMessage());
			System.exit(0);
		}
	}
}
