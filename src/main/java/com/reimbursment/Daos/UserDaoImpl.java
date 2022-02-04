package com.reimbursment.Daos;

import com.reimbursment.model.User;
import com.reimbursment.model.UserRoles;
import com.reimbursment.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "Select * from ERS_USERS";

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setUserRole(UserRoles.values()[rs.getInt(7)]);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;


    }

    @Override
    public List<User> getAllUsersByUserRole(int id) {
        List<User> users = new ArrayList<>();
        String sql = "Select * from ERS_USERS where user_role_id = ?";

        try (Connection c = ConnectionUtil.getConnection();){
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setUserRole(UserRoles.values()[rs.getInt(7)]);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }


}
