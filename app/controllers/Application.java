package controllers;

import models.Usuario;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(new Usuario()));
    }
    
    public static Result comoEstudar() {
    	return ok(comoEstudar.render());
    }
    
    public static Result sobre() {
    	return ok(sobre.render());
    }
}
