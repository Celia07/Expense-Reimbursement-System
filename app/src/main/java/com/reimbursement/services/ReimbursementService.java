package com.reimbursement.services;

import com.reimbursement.Daos.ReimbursementDao;
import com.reimbursement.Daos.ReimbursementDaoImpl;
import com.reimbursement.Daos.UserDao;
import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.ReimbursementStatus;
import com.reimbursement.model.ReimbursementType;
import com.reimbursement.model.User;


import java.util.List;
import java.util.Random;

public class ReimbursementService {

    private final ReimbursementDao rd;
    private final UserDao ud;

    public ReimbursementService(ReimbursementDao rd, UserDao ud) {
        this.rd = rd;
        this.ud = ud;
    }

    public boolean createReimbursement(float amount, String description, String username, int reimbType){
        User employee = ud.getUserByUsername(username);

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        ReimbursementType[] types = ReimbursementType.values();

        Reimbursement reimbursement = new Reimbursement(amount,date, description, employee, types[reimbType]);

        return rd.createReimbursement(reimbursement);
    }

//    public List<Reimbursement> getAllReimbursements(){return rd.getAllReimbursements();}

//    public Reimbursement getReimbursementById(int id){return rd.getReimbursementById(id);}

    public List<Reimbursement> getAllPendingReimbursements(){return rd.getAllReimbursementsByStatus(0);}

    public List<Reimbursement> getAllApprovedReimbursements(){return rd.getAllReimbursementsByStatus(1);}

    public List<Reimbursement> getAllDeniedReimbursements(){return rd.getAllReimbursementsByStatus(2);}

    public List<Reimbursement> getAllPendingReimbursementsByUser(String username){
        return rd.getAllReimbursementsByUsernameAndStatus(username, 0);
    }

    public List<Reimbursement> getAllApprovedReimbursementsByUser(String username){
        return rd.getAllReimbursementsByUsernameAndStatus(username, 1);
    }

    public List<Reimbursement> getAllDeniedReimbursementsByUser(String username){
        return rd.getAllReimbursementsByUsernameAndStatus(username, 2);
    }

    public boolean updateReimbursement(int id, ReimbursementStatus rs, String username){
        try {
            Reimbursement reimb = rd.getReimbursementById(id);
            User user = ud.getUserByUsername(username);

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            reimb.setReimbResolved(date);

            Random rand = new Random();
            int randReceiptNumber = rand.nextInt(89999999) + 10000000;
            reimb.setReimbReciept(randReceiptNumber);

            reimb.setReimbResolver(user);

            reimb.setReimbStatus(rs);

            return rd.updateReimbursement(reimb);

        } catch (NullPointerException e){
            return false;
        }



    }

}
