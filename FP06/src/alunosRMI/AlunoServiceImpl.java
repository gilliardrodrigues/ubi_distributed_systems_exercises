package alunosRMI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoServiceImpl extends UnicastRemoteObject implements AlunoService {

	private static final long serialVersionUID = 1541872535733207305L;

	private List<Aluno> alunos;
	private int numAcessos;

	public AlunoServiceImpl() throws RemoteException {
		super();
		if (this.alunos == null)
			this.alunos = new ArrayList<Aluno>();
	}

	@Override
	public synchronized int registrar(Aluno novoAluno) throws RemoteException {

		String diretorio = "C:\\Users\\User\\Desktop\\Mobilidade Internacional\\SD\\FP06\\src\\alunosRMI\\ficheiro.dat";

		if (this.alunos.contains(novoAluno)) {
			throw new AlunoJaRegistradoException("Este aluno já se encontra registrado no sistema!");
		} else {
			this.alunos.add(novoAluno);

			try {
				var ficheiro = new File(diretorio);
				ficheiro.delete();
				ficheiro.createNewFile();

				var saida = new ObjectOutputStream(new FileOutputStream(ficheiro));
				saida.writeObject(this.alunos);
				saida.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return alunos.size();
	}

	@Override
	public List<Aluno> consultarLista() throws RemoteException {

		return this.alunos;
	}

	@Override
	public int consultarNumAcessos() throws RemoteException {

		return this.numAcessos;
	}

	@Override
	public synchronized void incrementarNumAcessos() {

		this.numAcessos++;
	}

	@Override
	public List<String> consultarNumeroAndContatoPeloNome(String nome) throws RemoteException {

		return this.alunos.stream()
				.filter(aluno -> aluno.getNome().toUpperCase().startsWith(nome.toUpperCase()))
				.map(aluno -> aluno.getNumeroAndContato())
				.collect(Collectors.toList());
	}

}
