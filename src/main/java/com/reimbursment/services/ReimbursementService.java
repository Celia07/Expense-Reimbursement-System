package com.reimbursment.services;

import com.reimbursment.Daos.ReimbursementDao;
import com.reimbursment.Daos.ReimbursementDaoImpl;
import com.reimbursment.Daos.UserDao;
import com.reimbursment.Daos.UserDaoImpl;
import com.reimbursment.model.Reimbursement;
import com.reimbursment.model.ReimbursementStatus;
import com.reimbursment.model.ReimbursementType;
import com.reimbursment.model.User;


import java.util.List;
import java.util.Random;

public class ReimbursementService {

    private final ReimbursementDao rd =new ReimbursementDaoImpl();
    private final UserDao ud = new UserDaoImpl();

    public boolean createReimbursement(float amount, String description, String username, int reimbType){
        User employee = ud.getUserByUsername(username);

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        ReimbursementType[] types = ReimbursementType.values();

        Reimbursement reimbursement = new Reimbursement(amount,date, description, employee, types[reimbType]);

        return rd.createReimbursement(reimbursement);
    }

    public List<Reimbursement> getAllReimbursements(){return rd.getAllReimbursements();}

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
        Reimbursement reimb = rd.getReimbursementById(id);

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        reimb.setReimbResolved(date);

        Random rand = new Random();
        int randReceiptNumber = rand.nextInt(89999999)+10000000;
        reimb.setReimbReciept(randReceiptNumber);

        reimb.setReimbResolver(ud.getUserByUsername(username));

        reimb.setReimbStatus(rs);

        return rd.updateReimbursement(reimb);
    }

}
