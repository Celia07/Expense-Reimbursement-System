package com.reimbursement;


import com.reimbursement.util.LoggingSingleton;

public class ERSDriver {

    public static void main(String[] args){

        JavalinApp app = new JavalinApp();
        app.start(7000);
//instantiate our logging class
        LoggingSingleton logger = LoggingSingleton.getLogger();

        logger.setWriteToFile(true);
        logger.setWriteToConsole(false);


//        UserDao ud = new UserDaoImpl();
//        ReimbursementDao rd = new ReimbursementDaoImpl();
//        ReimbursementService rs = new ReimbursementService();
//
//        System.out.println(ud.getAllUsers());
//        System.out.println(ud.getAllUsersByUserRole(1));
//
//        User user = new User();
//
//        Reimbursement reimbursement = new Reimbursement(10.0f, Date.valueOf("2021-12-12"), null, null, 0, user, null, ReimbursementStatus.PENDING, ReimbursementType.FOOD);
//
//        System.out.println(reimbursement.toString());
//
//        System.out.println(ud.getUserByUsername("nbelin5"));
//
//        User testuser = ud.getUserByUsername("nbelin5");
//        testuser.setPassword("password");
//        ud.updateUser(testuser);
//
////        Reimbursement testReimbursement= new Reimbursement(197.37f, Date.valueOf("2021-11-19"), "Description", testuser, ReimbursementType.FOOD);
////        rd.createReimbursement(testReimbursement);
//
//        User newGuy = ud.getUserByID(11);
//        System.out.println(newGuy.toString());
//
////        System.out.println(rd.getAllReimbursements());
//
//        Reimbursement updateReimbursement = rd.getReimbursementById(8);
//
//        long millis=System.currentTimeMillis();
//        java.sql.Date date=new java.sql.Date(millis);
//
//
//        updateReimbursement.setReimbResolved(date);
//        updateReimbursement.setReimbReciept(12345678);
//        updateReimbursement.setReimbResolver(testuser);
//        updateReimbursement.setReimbStatus(ReimbursementStatus.APPROVED);
//        System.out.println(updateReimbursement);
//        System.out.println(rd.updateReimbursement(updateReimbursement));
//
//        System.out.println(rs.getAllPendingReimbursements());



    }
}
