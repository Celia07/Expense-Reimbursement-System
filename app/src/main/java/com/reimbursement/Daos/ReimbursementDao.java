package com.reimbursement.Daos;

import com.reimbursement.model.Reimbursement;

import java.util.List;

public interface ReimbursementDao {

    public boolean createReimbursement(Reimbursement reimbursement);
    public List<Reimbursement> getAllReimbursements();
    public List<Reimbursement> getAllReimbursementsByStatus(int id);
    public List<Reimbursement> getAllReimbursementsByUsernameAndStatus(String username, int id);
    public Reimbursement getReimbursementById(int id);
    public boolean updateReimbursement(Reimbursement reimbursement);

}