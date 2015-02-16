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
}
