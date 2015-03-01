package models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Classe Representa uma anotação do usuário.
 * 
 * @author José Aparecido Silvano de Albuquerque
 * @version 1.0
 */
@Entity
@Table(name = "TABELA_ANOTACAO")
public class Anotacao {
	
	@Id
	@GeneratedValue
	@Column(name = "ID_NOTA")
	private Long id;

	@Column(name = "CORPO_NOTA")
	private String corpo;
	
	@Column(name = "ESTAGIO")
	private int ESTAGIO;
	
	public Anotacao() {
		super();
		corpo = "";
		ESTAGIO = 0;
	}
	
	public Anotacao(final String corpo) {
		this.corpo = corpo;
		ESTAGIO = 0;
	}
	
	public Anotacao(final String corpo, int estagio) {
		this.corpo = corpo;
		ESTAGIO = estagio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public int getEstagio() {
		return ESTAGIO;
	}

	public void setEstagio(int estagio) {
		ESTAGIO = estagio;
	}
	
	public int getTipoPrimeiroEstagio(){
		return ESTAGIO;
	}
	
	public Estagio getTipoSegundoEstagio(){
		return Estagio.SEGUNDO;
	}
	
	public Estagio getTipoTerceiroEstagio(){
		return Estagio.TERCEIRO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corpo == null) ? 0 : corpo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anotacao other = (Anotacao) obj;
		if (corpo == null) {
			if (other.corpo != null)
				return false;
		} else if (!corpo.equals(other.corpo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Anotacao [id=" + id + ", corpo=" + corpo + "]";
	}

}
