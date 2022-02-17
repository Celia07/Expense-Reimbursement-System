package reimbursement;

import com.reimbursement.Daos.UserDao;
import com.reimbursement.model.User;
import com.reimbursement.model.UserRoles;
import com.reimbursement.services.AuthService;
import com.reimbursement.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class AuthServiceTest {


    @Spy
    static UserDao ud;

    @InjectMocks
    private static AuthService as;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void goodLoginShouldLogin() {

        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);

        doReturn(user).when(ud).getUserByUsername(anyString());

        boolean login = as.loginUser("testusername","testpassword");

        Mockito.verify(ud).getUserByUsername(anyString());

        assertTrue(login);

    }

    @Test
    public void badPasswordShouldNotLogin() {

        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);

        doReturn(user).when(ud).getUserByUsername(anyString());

        boolean login = as.loginUser("testusername","nottestpassword");

        Mockito.verify(ud).getUserByUsername(anyString());

        assertFalse(login);

    }
}
