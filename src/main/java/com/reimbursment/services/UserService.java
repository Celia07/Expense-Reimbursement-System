package com.reimbursment.services;

import com.reimbursment.Daos.UserDao;
import com.reimbursment.Daos.UserDaoImpl;
import com.reimbursment.model.User;

import java.util.List;

public class UserService {

    private final UserDao ud = new UserDaoImpl();

    public List<User> getAllUsers(){return ud.getAllUsers();}

    public List<User> getAllEmployees(){return ud.getAllUsersByUserRole(0);}

    public boolean updatePassword(String username, String oldPass, String newPass){
        User updateUser = ud.getUserByUsername(username);
        if (oldPass == updateUser.getPassword()){
            updateUser.setPassword(newPass);
            return ud.updateUser(updateUser);
        }
        return false;
    }

    public boolean updateOtherInformation(User user, String first, String last, String username, String email){
        user.setFirstName(first);
        user.setLastName(last);
        user.setEmail(email);
        user.setUsername(username);
        return ud.updateUser(user);
    }


}
