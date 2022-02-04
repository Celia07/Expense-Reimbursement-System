package com.reimbursment.Daos;

import com.reimbursment.model.Reimbursement;
import com.reimbursment.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao{


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
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByType(int id) {
        return null;
    }


    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        return false;
    }
}
