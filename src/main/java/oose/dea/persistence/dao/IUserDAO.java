package oose.dea.persistence.dao;

import oose.dea.domain.User;

/**
 * Interface voor de userDAO
 * Requirement IM1 wordt aangetoond, deze interface maakt het mogelijk om makkelijk van relationele database te wisselen
 */
public interface IUserDAO {

    User findByEmailAndPassword(String email, String password);

}
