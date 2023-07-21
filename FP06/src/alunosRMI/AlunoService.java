package alunosRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AlunoService extends Remote {

	public int registrar(Aluno novoAluno) throws RemoteException;
	public List<Aluno> consultarLista() throws RemoteException;
	public int consultarNumAcessos() throws RemoteException;
	public List<String> consultarNumeroAndContatoPeloNome(String nome) throws RemoteException;
	void incrementarNumAcessos() throws RemoteException;
}
