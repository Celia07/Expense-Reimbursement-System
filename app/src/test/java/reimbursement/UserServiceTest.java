package reimbursement;

import com.reimbursement.Daos.UserDao;
import com.reimbursement.model.User;
import com.reimbursement.model.UserRoles;
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

public class UserServiceTest {


    @Spy
    static UserDao ud;

    @InjectMocks
    private static UserService us;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsers(){

        List<User> userList = us.getAllUsers();

        assertNotNull(userList);

    }

    @Test
    public void getAllEmployees(){

        List<User> employeeList = us.getAllEmployees();

        assertNotNull(employeeList);
    }

    @Test
    public void getUserByUsername(){

        List<User> userList = new ArrayList<>();

        User user = us.getUserByUsername("nbelin5");

        userList.add(user);

        assertNotNull(userList);
    }

    @Test
    public void getUserByUsernameWrong(){

        User user = us.getUserByUsername("nbeli");

        assertNull(user);
    }

    @Test
    public void getUserById(){

        List<User> userList = new ArrayList<>();

        User user = us.getUserById(16);

        userList.add(user);

        assertNotNull(userList);
    }

    @Test
    public void getUserByIdWrong(){

        User user = us.getUserById(166);

        assertNull(user);
    }

    @Test
    public void updateOtherInfoShouldUpdateOtherInfo(){


        doReturn(true).when(ud).updateUser(any());

        User user = new User();

        boolean update = us.updateOtherInformation(user, "Nicoletta", "Belinn",
                "nbelin5", "example@work.com");

        Mockito.verify(ud).updateUser(any());

        assertTrue(update);

    }

    @Test
    public void updatePasswordShouldUpdateUser(){
        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);

//        when(ud.updateUser(any())).thenReturn(true);
        doReturn(true).when(ud).updateUser(any());
//        when(ud.getUserByUsername(anyString())).thenReturn(user);
        doReturn(user).when(ud).getUserByUsername(anyString());

        boolean update = us.updatePassword("testusername","testpassword", "Password");


        Mockito.verify(ud).updateUser(any());

        assertTrue(update);

    }



}

