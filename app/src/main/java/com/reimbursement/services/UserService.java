package com.reimbursement.services;

import com.reimbursement.Daos.UserDao;
import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.User;

import java.util.List;

public class UserService {

    private final UserDao ud;

    public UserService(UserDao ud) {
        this.ud = ud;
    }

    public List<User> getAllUsers(){return ud.getAllUsers();}

    public List<User> getAllEmployees(){return ud.getAllUsersByUserRole(0);}

    public User getUserByUsername(String username){return ud.getUserByUsername(username);}

    public User getUserById(int id){return ud.getUserByID(id);}

    public boolean updatePassword(String username, String oldPass, String newPass){
        try{
            User updateUser = ud.getUserByUsername(username);
            if (oldPass.equals(updateUser.getPassword())){
                updateUser.setPassword(newPass);
                return ud.updateUser(updateUser);
            }
            return false;
        } catch (Exception e){
            return false;
        }

    }

    public boolean updateOtherInformation(User user, String first, String last, String username, String email){
        try {
            user.setFirstName(first);
            user.setLastName(last);
            user.setEmail(email);
            user.setUsername(username);
            return ud.updateUser(user);
        } catch (Exception e){
            return false;
        }
    }



}
