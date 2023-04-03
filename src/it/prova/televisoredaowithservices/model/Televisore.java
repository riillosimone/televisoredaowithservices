package it.prova.televisoredaowithservices.model;

import java.time.LocalDate;

public class Televisore {

	private Long id;
	private String marca;
	private String modello;
	private int pollici;
	private LocalDate dataProduzione;
	
	
	//costruttori
	public Televisore() {
	}


	public Televisore(String marca, String modello) {
		this.marca = marca;
		this.modello = modello;
	}
	


	public Televisore(String marca, String modello, int pollici, LocalDate dataProduzione) {
		this.marca = marca;
		this.modello = modello;
		this.pollici = pollici;
		this.dataProduzione = dataProduzione;
	}


	public Televisore(Long id, String marca, String modello, int pollici, LocalDate dataProduzione) {
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.pollici = pollici;
		this.dataProduzione = dataProduzione;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModello() {
		return modello;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}


	public int getPollici() {
		return pollici;
	}


	public void setPollici(int pollici) {
		this.pollici = pollici;
	}


	public LocalDate getDataProduzione() {
		return dataProduzione;
	}


	public void setDataProduzione(LocalDate dataProduzione) {
		this.dataProduzione = dataProduzione;
	}


	@Override
	public String toString() {
		return "Televisore [id=" + id + ", marca=" + marca + ", modello=" + modello + ", pollici=" + pollici
				+ ", dataProduzione=" + dataProduzione + "]";
	}
	
	
	
}
