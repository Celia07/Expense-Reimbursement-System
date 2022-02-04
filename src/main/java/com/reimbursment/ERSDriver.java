package com.reimbursment;

import com.reimbursment.Daos.UserDao;
import com.reimbursment.Daos.UserDaoImpl;

public class ERSDriver {

    public static void main(String[] args){

        UserDao ud = new UserDaoImpl();

//        System.out.println(ud.getAllUsers());
        System.out.println(ud.getAllUsersByUserRole(1));

    }
}
