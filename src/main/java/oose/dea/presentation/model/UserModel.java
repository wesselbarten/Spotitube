package oose.dea.presentation.model;

import oose.dea.domain.User;
import oose.dea.service.UserService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class UserModel {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private UserService userService;

    public User getUserByEmailAndPassword(String email, String password) {
        return userService.getUserByEmailAndPassword(email, password);
    }
}
