package model;

public class Carro {

	private int id;
	private String placa;
	private String renavam;
	private boolean premium;
	private String modelo;
	private String cor;
	
	public Carro() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getRenavam() {
		return renavam;
	}
	
	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
}
