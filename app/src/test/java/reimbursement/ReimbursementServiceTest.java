package com.example.assignment;


import com.reimbursement.Daos.ReimbursementDao;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.ReimbursementStatus;
import com.reimbursement.model.ReimbursementType;
import com.reimbursement.model.User;
import com.reimbursement.services.ReimbursementService;
import com.reimbursement.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ReimbursementServiceTest {

    @Mock
    static ReimbursementDao rd;

    @InjectMocks
    private static ReimbursementService rs;

    @Before
    public void initMocks(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void createAnAssignmentShouldReturnANewAssignment(){

        //When we call createAssignment from the AssignmentService make sure we do not actually make a call to the db
        doNothing().when(rd).createReimbursement(any());
        UserService us = new UserService();
        User user = us.getUserById(16);

        boolean creation = rs.createReimbursement(10.0f, "Test", user.getUsername(), 2);
        //Verify that the dao was called, but we intercepted the call to the db
        Mockito.verify(rd).createReimbursement(any());

        assertTrue(creation);
    }



}
