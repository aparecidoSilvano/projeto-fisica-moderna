package models;

public enum Estagio {
	PRIMEIRO(1), SEGUNDO(2), TERCEIRO(3), DEFAULT(0);
	public int valor;
	
	Estagio(int estagio) {
		this.valor = estagio;
	}
	
	public int getValor(){
		return valor;
	}
}