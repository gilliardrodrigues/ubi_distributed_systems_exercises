package alunosMultithread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Servico {

	private ArrayList<Aluno> alunosRegistrados;
	private int numeroAcessos;
	private String diretorio = "C:\\Users\\User\\Desktop\\Mobilidade Internacional\\SD\\FP05\\src\\alunosMultithread\\ficheiro.dat";

	public Servico() {
		super();
		if (this.alunosRegistrados == null) {
			this.alunosRegistrados = new ArrayList<Aluno>();
		}
	}

	public synchronized int registrarAluno(Aluno novoAluno) {

		if (alunosRegistrados.contains(novoAluno)) {
			throw new AlunoJaRegistradoException("Este aluno já se encontra registrado no sistema!");
		} else {
			this.alunosRegistrados.add(novoAluno);
			try {
				var ficheiro = new File(diretorio);
				ficheiro.delete();
				ficheiro.createNewFile();

				var saida = new ObjectOutputStream(new FileOutputStream(ficheiro));
				saida.writeObject(this.alunosRegistrados);
				saida.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
		return alunosRegistrados.size();
	}

	public List<Aluno> consultarAlunosRegistrados() {

		return this.alunosRegistrados;
	}

	public int consultarNumeroDeAcessos() {

		return this.numeroAcessos;
	}

	public synchronized void incrementarNumeroDeAcessos() {

		this.numeroAcessos += 1;
	}

	public List<String> consultarDadosPeloNome(String nome) {

		return this.alunosRegistrados.stream()
				.filter(aluno -> aluno.getNome().toUpperCase().startsWith(nome.toUpperCase()))
				.map(aluno -> aluno.getNumeroAndContato())
				.collect(Collectors.toList());
	}

}
