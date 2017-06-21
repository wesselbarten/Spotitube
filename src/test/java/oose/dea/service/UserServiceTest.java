package oose.dea.service;

import oose.dea.domain.User;
import oose.dea.persistence.dao.IUserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test voor de UserService
 * Requirement T1 wordt aangetoond d.m.v. deze test
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private User user;

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserByEmailAndPassword() {
        when(userDAO.findByEmailAndPassword("wessel.barten@hotmail.com", "mypass")).thenReturn(user);
        User res = userService.getUserByEmailAndPassword("wessel.barten@hotmail.com", "mypass");
        verify(userDAO, times(1)).findByEmailAndPassword("wessel.barten@hotmail.com", "mypass");
        assertEquals(res, user);
    }
}
