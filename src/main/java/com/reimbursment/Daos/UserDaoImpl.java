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

    @Override
    public User getUserByUsername(String username) {
        String sql = "Select * from ERS_USERS where ers_username = ?";
        User user = new User();

        try (Connection c = ConnectionUtil.getConnection();) {
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, username);
            ResultSet rs = s.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));

                int typeOrdinal = rs.getInt(7);
                UserRoles[] types = UserRoles.values();
                user.setUserRole(types[typeOrdinal]);

                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    return null;
    }

    @Override
    public User getUserByID(int id) {
        String sql = "Select * from ERS_USERS where ers_users_id = ?";
        User user = new User();

        try (Connection c = ConnectionUtil.getConnection();) {
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();

            if (rs.next()) {
                user.setUserId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));

                int typeOrdinal = rs.getInt(7);
                UserRoles[] types = UserRoles.values();
                user.setUserRole(types[typeOrdinal]);

                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update ERS_USERS set ers_password = ?, user_first_name = ?, " +
                "user_last_name = ?, user_email = ?, ers_username = ? where ers_users_id = ?";

        try (Connection c = ConnectionUtil.getConnection();) {
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, user.getPassword());
            s.setString(2, user.getFirstName());
            s.setString(3, user.getLastName());
            s.setString(4, user.getEmail());
            s.setString(5, user.getUsername());
            s.setInt(6, user.getUserId());

            int rowsAffected = s.executeUpdate();

            if(rowsAffected == 1){
                return true;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


}
