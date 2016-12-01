package model;

import java.sql.Time;

public class Percurso {

	private int id;
	private String origem;
	private String destino;
	private double distancia;
	private Time tempoInicial;
	private Time tempoFinal;
	
	public Percurso() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Time getTempoInicial() {
		return tempoInicial;
	}

	public void setTempoInicial(Time tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	public Time getTempoFinal() {
		return tempoFinal;
	}

	public void setTempoFinal(Time tempoFinal) {
		this.tempoFinal = tempoFinal;
	}
}
