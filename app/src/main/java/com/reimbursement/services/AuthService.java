package com.reimbursement.services;

import com.reimbursement.Daos.UserDao;
import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.User;


public class AuthService {

    private final UserDao ud;

    public AuthService(UserDao ud) {
        this.ud = ud;
    }

    public boolean loginUser(String username, String password){

        User login = ud.getUserByUsername(username);

        if(login == null || !login.getPassword().equals(password)){
            return false;
        }
        return true;

    }

}