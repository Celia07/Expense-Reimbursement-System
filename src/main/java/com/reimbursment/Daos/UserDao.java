package com.reimbursment.Daos;

import com.reimbursment.model.Reimbursement;
import com.reimbursment.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();
    public List<User> getAllUsersByUserRole(int id);
    public User getUserByUsername(String username);
    public boolean updateUser(User user);


}
