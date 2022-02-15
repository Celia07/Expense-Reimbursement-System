package com.reimbursement.Daos;

import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();

    public List<User> getAllUsersByUserRole(int id);

    public User getUserByUsername(String username);

    public User getUserByID(int id);

    public boolean updateUser(User user);

}
