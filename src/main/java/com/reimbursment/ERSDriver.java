package com.reimbursment;

import com.reimbursment.Daos.ReimbursementDao;
import com.reimbursment.Daos.ReimbursementDaoImpl;
import com.reimbursment.Daos.UserDao;
import com.reimbursment.Daos.UserDaoImpl;
import com.reimbursment.model.Reimbursement;
import com.reimbursment.model.ReimbursementStatus;
import com.reimbursment.model.ReimbursementType;
import com.reimbursment.model.User;

import java.sql.Date;

public class ERSDriver {

    public static void main(String[] args){

        UserDao ud = new UserDaoImpl();
        ReimbursementDao rd = new ReimbursementDaoImpl();

        System.out.println(ud.getAllUsers());
        System.out.println(ud.getAllUsersByUserRole(1));

        User user = new User();

        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);

        System.out.println(reimbursement.toString());

        System.out.println(ud.getUserByUsername("nbelin5"));

        User testuser = ud.getUserByUsername("nbelin5");
        testuser.setPassword("password");
        ud.updateUser(testuser);

//        Reimbursement testReimbursement= new Reimbursement(197.37f, Date.valueOf("2021-11-19"), "Description", testuser, ReimbursementType.FOOD);
//        rd.createReimbursement(testReimbursement);

        User newGuy = ud.getUserByID(11);
        System.out.println(newGuy.toString());

//        System.out.println(rd.getAllReimbursements());

    }
}
