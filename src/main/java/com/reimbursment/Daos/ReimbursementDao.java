package com.reimbursment.Daos;

import com.reimbursment.model.Reimbursement;

import java.util.List;

public interface ReimbursementDao {

    public boolean createReimbursement(Reimbursement reimbursement);
    public List<Reimbursement> getAllReimbursements();
    public List<Reimbursement> getAllReimbursementsByType(int id);
    public boolean updateReimbursement(Reimbursement reimbursement);

}
