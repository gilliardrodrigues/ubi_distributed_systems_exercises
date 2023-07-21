package exercicio5;

public class Aluno {

	private String numero;
	private String nome;
	private String curso;
	private String contato;

	
	
	public Aluno() {
		super();
	}

	public Aluno(String numero, String nome, String curso, String contato) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.curso = curso;
		this.contato = contato;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getNumeroAndContato() {
		return "Aluno [numero=" + numero + ", contato=" + contato + "]";
	}
	
	@Override
	public String toString() {
		return "Aluno [numero=" + numero + ", nome=" + nome + ", curso=" + curso + ", contato=" + contato + "]";
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
}
