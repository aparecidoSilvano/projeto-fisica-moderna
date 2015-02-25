package controllers;

import com.google.common.base.Objects;

import models.Usuario;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class AulasController extends Controller {
    public static Result index() {
        return ok(views.html.aulas.render("dsds"));
    }
    public static Result relatividade() {
        return ok(views.html.relatividade.render("dsds"));
    }
    public static Result teoriaquantica() {
        return ok(views.html.teoriaquantica.render("dsds"));
    }
    public static Result mecanicaquantica() {
        return ok(views.html.mecanicaquantica.render("dsds"));
    }
}
