package reimbursement;


import com.reimbursement.Daos.ReimbursementDao;
import com.reimbursement.Daos.ReimbursementDaoImpl;
import com.reimbursement.Daos.UserDao;
import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.*;
import com.reimbursement.services.ReimbursementService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ReimbursementServiceTest {

    @Spy
    static ReimbursementDao rd = new ReimbursementDaoImpl();

    @Spy
    static UserDao ud = new UserDaoImpl();

    @InjectMocks
    private static ReimbursementService rs;

    @Before
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

//    @Before
//    public void setUp() {
//        rd = new ReimbursementDaoImpl();
//        ud = new UserDaoImpl();
//    }
//
//    @After
//    public void tearDown() {
//        rd = null;
//        ud = null;
//    }

    @Test
    public void createAReimbursementShouldCreateReimbursement(){
        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);
//        when(rd.createReimbursement(any())).thenReturn(true);
//
        doReturn(true).when(rd).createReimbursement(any());
        doReturn(user).when(ud).getUserByUsername(anyString());



//        Reimbursement reimbursement = new Reimbursement()

        boolean creation = rs.createReimbursement(10.0f, "Test", "test", 2);

        Mockito.verify(rd).createReimbursement(any());

        assertTrue(creation);


    }

    @Test
    public void createABadReimbursementShouldNotCreateReimbursement(){

        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);
//        when(rd.createReimbursement(any())).thenReturn(false);
//        when(ud.getUserByUsername(anyString())).thenReturn(user);
        doReturn(false).when(rd).createReimbursement(any());
        doReturn(user).when(ud).getUserByUsername(anyString());


        boolean creation = rs.createReimbursement(10.0f, "Test", "test", 2);

        Mockito.verify(rd).createReimbursement(any());

        assertFalse(creation);

    }

    @Test
    public void getAllPendingShouldNotBeNull(){
        List<Reimbursement> reimbs = new ArrayList<>();

        User user = new User();

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        reimbs.add(reimbursement);

        doReturn(reimbs).when(rd).getAllReimbursementsByStatus(0);

        List<Reimbursement> reimbursementList = rs.getAllPendingReimbursements();

        assertNotNull(reimbursementList);

    }

    @Test
    public void getAllApprovedShouldNotBeNull(){

        List<Reimbursement> reimbs = new ArrayList<>();

        User user = new User();

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        reimbs.add(reimbursement);

        doReturn(reimbs).when(rd).getAllReimbursementsByStatus(1);

        List<Reimbursement> reimbursementList = rs.getAllApprovedReimbursements();

        assertNotNull(reimbursementList);

    }

    @Test
    public void getAllDeniedShouldNotBeNull(){

        List<Reimbursement> reimbs = new ArrayList<>();

        User user = new User();

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        reimbs.add(reimbursement);

        doReturn(reimbs).when(rd).getAllReimbursementsByStatus(2);

        List<Reimbursement> reimbursementList = rs.getAllDeniedReimbursements();

        assertNotNull(reimbursementList);

    }

    @Test
    public void getAllPendingByUserShouldNotBeNull(){

        List<Reimbursement> reimbs = new ArrayList<>();

        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        reimbs.add(reimbursement);

        doReturn(reimbs).when(rd).getAllReimbursementsByUsernameAndStatus(anyString(),eq(0));
        doReturn(user).when(ud).getUserByUsername(anyString());

        List<Reimbursement> reimbursementList = rs.getAllPendingReimbursementsByUser("testusername");

        assertNotNull(reimbursementList);

    }

    @Test
    public void getAllApprovedByUserShouldNotBeNull(){

        List<Reimbursement> reimbs = new ArrayList<>();

        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        reimbs.add(reimbursement);

        doReturn(reimbs).when(rd).getAllReimbursementsByUsernameAndStatus(anyString(),eq(1));
        doReturn(user).when(ud).getUserByUsername(anyString());

        List<Reimbursement> reimbursementList = rs.getAllApprovedReimbursementsByUser("testusername");

        assertNotNull(reimbursementList);

    }

    @Test
    public void getAllDeniedByUserShouldNotBeNull(){

        List<Reimbursement> reimbs = new ArrayList<>();

        User user = new User(25, "testusername", "testpassword", "test", "person",
                "test@test.com", UserRoles.EMPLOYEE);

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        reimbs.add(reimbursement);

        doReturn(reimbs).when(rd).getAllReimbursementsByUsernameAndStatus(anyString(),eq(2));
        doReturn(user).when(ud).getUserByUsername(anyString());

        List<Reimbursement> reimbursementList = rs.getAllDeniedReimbursementsByUser("testusername");

        assertNotNull(reimbursementList);

    }

    @Test
    public void badUpdateToReimbursementShouldNotUpdateReimbursement(){

        boolean update = rs.updateReimbursement(16,ReimbursementStatus.APPROVED,"bell5");

        assertFalse(update);

    }

    @Test
    public void updateAReimbursementShouldUpdateReimbursement(){

        User user = new User();

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        doReturn(true).when(rd).updateReimbursement(any());
        doReturn(reimbursement).when(rd).getReimbursementById(anyInt());

        boolean update = rs.updateReimbursement(14,ReimbursementStatus.APPROVED,"nbelin5");

        Mockito.verify(rd).updateReimbursement(any());

        assertTrue(update);

    }




}
