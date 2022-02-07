package com.reimbursment.Daos;

import com.reimbursment.model.*;
import com.reimbursment.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao{

    private final UserDao ud = new UserDaoImpl();


    @Override
    public boolean createReimbursement(Reimbursement reimbursement) {
        String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_description, " +
                "reimb_author, reimb_status_id, reimb_type_id) values (?,?,?,?,?,?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            ps.setFloat(1, reimbursement.getAmount());
            ps.setDate(2, reimbursement.getReimbSubmitted());
            ps.setString(3, reimbursement.getReimbDescription());
            ps.setInt(4, reimbursement.getReimbAuthor().getUserId());
            ps.setInt(5, 0);
            ps.setInt(6, reimbursement.getReimbType().ordinal());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String sql = "Select * from ERS_REIMBURSEMENT";

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();
                reimb.setReimbId(rs.getInt(1));
                reimb.setAmount(rs.getFloat(2));
                reimb.setReimbSubmitted(rs.getDate(3));
                reimb.setReimbResolved(rs.getDate(4));
                reimb.setReimbDescription(rs.getString(5));
                reimb.setReimbReciept(rs.getInt(6));
                reimb.setReimbAuthor(ud.getUserByID(rs.getInt(7)));
                reimb.setReimbResolver(ud.getUserByID(rs.getInt(8)));
                reimb.setReimbStatus(ReimbursementStatus.values()[rs.getInt(9)]);
                reimb.setReimbType(ReimbursementType.values()[rs.getInt(10)]);
                reimbursements.add(reimb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByType(int id) {
        String sql = "Select * from ERS_REIMBURSEMENT where reimb_type_id = ?";
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();) {
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();
                reimb.setReimbId(rs.getInt(1));
                reimb.setAmount(rs.getFloat(2));
                reimb.setReimbSubmitted(rs.getDate(3));
                reimb.setReimbResolved(rs.getDate(4));
                reimb.setReimbDescription(rs.getString(5));
                reimb.setReimbReciept(rs.getInt(6));
                reimb.setReimbAuthor(ud.getUserByID(rs.getInt(7)));
                reimb.setReimbResolver(ud.getUserByID(rs.getInt(8)));
                reimb.setReimbStatus(ReimbursementStatus.values()[rs.getInt(9)]);
                reimb.setReimbType(ReimbursementType.values()[rs.getInt(10)]);
                reimbursements.add(reimb);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reimbursements;

    }

    @Override
    public List<Reimbursement> getAllReimbursementsByUsernameAndType(String username, int id) {
        String sql = "Select * from ERS_REIMBURSEMENT where reimb_type_id = ? and reimb_author = ?";
        User employee = ud.getUserByUsername(username);
        List<Reimbursement> reimbursements = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();) {
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.setInt(2,employee.getUserId());
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                Reimbursement reimb = new Reimbursement();
                reimb.setReimbId(rs.getInt(1));
                reimb.setAmount(rs.getFloat(2));
                reimb.setReimbSubmitted(rs.getDate(3));
                reimb.setReimbResolved(rs.getDate(4));
                reimb.setReimbDescription(rs.getString(5));
                reimb.setReimbReciept(rs.getInt(6));
                reimb.setReimbAuthor(ud.getUserByID(rs.getInt(7)));
                reimb.setReimbResolver(ud.getUserByID(rs.getInt(8)));
                reimb.setReimbStatus(ReimbursementStatus.values()[rs.getInt(9)]);
                reimb.setReimbType(ReimbursementType.values()[rs.getInt(10)]);
                reimbursements.add(reimb);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return reimbursements;

    }


    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        String sql = "update ERS_REIMBURSEMENT set reimb_resolved = ?, reimb_receipt = ?, " +
                "reimb_resolver = ?, reimb_status_id = ? where reimb_id = ?";

        try (Connection c = ConnectionUtil.getConnection();) {
            PreparedStatement s = c.prepareStatement(sql);
            s.setDate(1, reimbursement.getReimbResolved());
            s.setInt(2, reimbursement.getReimbReciept());
            s.setInt(3, reimbursement.getReimbResolver().getUserId());
            s.setInt(4, reimbursement.getReimbStatus().ordinal());
            s.setInt(5, reimbursement.getReimbId());

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
