package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.data.format.Formats.NonEmpty;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

import com.google.common.base.Objects;

/**
 * Classe Representa um Usuario do Sistema.
 * 
 * @author José Aparecido de Silvano Albuquerque
 * @version 1.0
 */
@Entity
@Table(name = "TABELA_USUARIO")
public class Usuario {

	@Id
	@Email
	@NonEmpty
	@Required
	@Column(name = "ID_USUARIO")
	private String email;

	@Transient
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Transient
	private static final Integer TAMANHO_MINIMO_SENHA = 6;
	
	@Transient
	private static final Integer TAMANHO_MAXIMO_SENHA = 8;
	
	@Transient
	private static final long MAX_LENGTH = 70L;

	@NonEmpty
	@Required
	@Column(name = "NOME")
	@MaxLength(value = MAX_LENGTH)
	private String nome;

	@NonEmpty
	@Required
	@Column(name = "SENHA")
	@MaxLength(value = MAX_LENGTH)
	private String senha;

	@OneToMany
	private List<Anotacao> anotacoes = new ArrayList<Anotacao>();
	
	/**
	 * Construtor default de um usuário.
	 */
	public Usuario() {
		super();
		anotacoes = new ArrayList<Anotacao>();
	}

	/**
	 * Contrutor da Classe
	 * @param nome - nome do usuario
	 * @param email - email do usuario
	 * @param senha - senha do usuario
	 * @throws Exception
	 */
	public Usuario(String nome, String email, String senha) throws Exception {
		super();
		isSetEmail(email);
		isSetNome(nome);
		isSetSenha(senha);
		anotacoes = new ArrayList<Anotacao>();
	}
	
	/**
	 * Contrutor da Classe
	 * @param nome - nome do usuario
	 * @param email - email do usuario
	 * @param senha - senha do usuario
	 * @throws Exception
	 */
	public Usuario(String nome, String email, String senha, List<Anotacao> viagens) throws Exception {
		super();
		isSetEmail(email);
		isSetNome(nome);
		isSetSenha(senha);
		this.anotacoes = viagens;
	}

	/**
	 * Metodo define uma lista de viagens para o usuario.
	 * @param viagens
	 */
	public void setAnotacoes(List<Anotacao> viagens) {
		this.anotacoes = viagens;
	}

	/**
	 * Metodo Retorna o Nome do Usuario
	 * @return String - nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Altera o Nome do Usuario
	 * @param nome
	 * @throws Exception - se nome for null ou tamanho maior que MAX_LENGTH
	 */
	public void setNome(String nome) throws Exception {
		isSetNome(nome);
	}

	private void isSetNome(String nome) throws Exception {
		if (nome == null) {
			throw new Exception("Nome nulo!");
		}
		if (nome.length() > MAX_LENGTH) {
			throw new Exception("Nome muito longo!");
		}
		this.nome = nome;
	}
	
	/**
	 * Metodo Retorna o E-mail do Usuario
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Altera o E-mail do Usuario
	 * @param email
	 * @throws Exception - se o email nao for valido, null ou tamanho maior que MAX_LENGTH
	 */
	public void setEmail(String email) throws Exception {
		isSetEmail(email);
	}

	private void isSetEmail(String email) throws Exception {
		if (email == null) {
			throw new Exception("E-mail nulo!");
		}
		if (!email.matches(EMAIL_PATTERN)) {
			throw new Exception("E-mail não é válido!");
		}
		if (email.length() > MAX_LENGTH) {
			throw new Exception("E-mail muito longo!");
		}
		this.email = email;
	}
	
	/**
	 * Metodo retorna a Senha do Usuario
	 * @return senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Altera a Senha do Usuario
	 * @param senha
	 * @throws Exception 
	 */
	public void setSenha(String senha) throws Exception {
		isSetSenha(senha);
	}
	
	private void isSetSenha(String senha) throws Exception{
		if (senha == null) {
			throw new Exception("Senha nula!");
		}
		
		if (senha.trim().isEmpty()) {
			throw new Exception("Senha vazia!");
		}
		
		if (senha.length() < TAMANHO_MINIMO_SENHA || senha.length() > TAMANHO_MAXIMO_SENHA ) {
			throw new Exception("Senha deve ter 6 à 8 caracteres!");
		}
		
		this.senha = senha;
		this.senha = String.valueOf(this.hashCode());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(email, senha);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equalsIgnoreCase(other.email)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("|%-40s|%-40s|", nome, email);
	}
}
