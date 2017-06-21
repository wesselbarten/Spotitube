package oose.dea.service;

import oose.dea.domain.User;
import oose.dea.persistence.dao.IUserDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * Rest service voor het ophalen van users
 * Requirement IO2 en IN1 worden aangetoond, elke methode is Restful aan te roepen
 */
@Path("/showUsers")
public class UserService {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private IUserDAO userDAO;

    @GET
    @Path("{email}/{password}")
    @Produces("application/json")
    public User getUserByEmailAndPassword(@PathParam("email") String email, @PathParam("password") String password) {
        return userDAO.findByEmailAndPassword(email, password);
    }
}
