package model;

public class Motorista {
	
	private int id;
	private String CPF;
	private String RG;
	private String CNH;
	private String nome;
	private int avaliacao;
	
	public Motorista() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCNH() {
		return CNH;
	}

	public void setCNH(String cNH) {
		CNH = cNH;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
}
