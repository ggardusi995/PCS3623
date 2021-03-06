package model;

import java.sql.Date;

public class Viagem {

	private int id;
	private Date data;
	private double valor;
	
	private Carro carro;
	private Percurso percurso;
	private Motorista motorista;
	private Cliente cliente;
	
	public Viagem() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Percurso getPercurso() {
		return percurso;
	}

	public void setPercurso(Percurso percurso) {
		this.percurso = percurso;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
