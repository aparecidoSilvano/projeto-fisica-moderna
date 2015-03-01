package controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

import models.Anotacao;
import models.Estagio;
import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class AulasController extends Controller {
	private static GenericDAO dao = new GenericDAOImpl();
	private static final Form<Nota> FORM_ANOTACAO = Form.form(Nota.class);
	private static Usuario usuario;

	@Transactional
	public static Result relatividade(String email) {
		usuario = getUser(email);
		if (usuario == null) {
			LoginController.index();
		}
		return ok(views.html.relatividade.render(usuario));
	}

	@Transactional
	public static Result teoriaquantica(String email) {
		usuario = getUser(email);
		if (usuario == null) {
			LoginController.index();
		}
		return ok(views.html.teoriaquantica.render(usuario));
	}

	@Transactional
	public static Result mecanicaquantica(String email) {
		usuario = getUser(email);
		if (usuario == null) {
			LoginController.index();
		}
		return ok(views.html.mecanicaquantica.render(usuario));
	}

	@Transactional
	public static Result savarNotasPrimeiroEstagio(String email) {
		Form<Nota> notasForm = FORM_ANOTACAO.bindFromRequest();
		List<String> notas = notasForm.get().getNotas();
		if (notas != null && usuario != null) {
			salvarAnotacoes(notas, 1);
		}
		return ok(views.html.relatividade.render(usuario));
	}
	
	@Transactional
	public static Result savarNotasSegundoEstagio(String email) {
		Form<Nota> notasForm = FORM_ANOTACAO.bindFromRequest();
		List<String> notas = notasForm.get().getNotas();
		if (notas != null && usuario != null) {
			salvarAnotacoes(notas, 2);
		}
		return ok(views.html.teoriaquantica.render(usuario));
	}
	
	@Transactional
	public static Result savarNotasTerceiroEstagio(String email) {
		Form<Nota> notasForm = FORM_ANOTACAO.bindFromRequest();
		List<String> notas = notasForm.get().getNotas();
		if (notas != null && usuario != null) {
			salvarAnotacoes(notas, 3);
		}
		return ok(views.html.mecanicaquantica.render(usuario));
	}

	@Transactional
	private static void salvarAnotacoes(List<String> notas, int estagio) {
		for (String anotacao : notas) {
			if (!anotacao.isEmpty()) {
				usuario.addAnotacao(new Anotacao(anotacao, estagio), dao);
			}
		}

		dao.merge(usuario);
		dao.flush();
	}

	@Transactional
	private static Usuario getUser(String email) {
		return dao.findByEntityId(Usuario.class, email);
	}

	/**
	 * Classe Login
	 */
	public static class Nota {
		private List<String> notas;

		public Nota() {
			notas = new ArrayList<String>();
		}

		public List<String> getNotas() {
			return notas;
		}

		public void setEmail(List<String> notas) {
			this.notas = notas;
		}
	}
}
