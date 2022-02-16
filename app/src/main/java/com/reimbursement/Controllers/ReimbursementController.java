package com.reimbursement.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.Daos.ReimbursementDao;
import com.reimbursement.Daos.ReimbursementDaoImpl;
import com.reimbursement.Daos.UserDao;
import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.ReimbursementStatus;
import com.reimbursement.model.ReimbursementType;
import com.reimbursement.model.User;
import com.reimbursement.services.ReimbursementService;
import com.reimbursement.services.UserService;
import com.reimbursement.util.LoggingSingleton;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

import java.util.List;

public class ReimbursementController {

    private ReimbursementDao rd = new ReimbursementDaoImpl();
    private UserDao ud = new UserDaoImpl();

    private final ReimbursementService rs = new ReimbursementService(rd, ud);
    private final UserService us = new UserService(ud);
    private ObjectMapper mapper = new ObjectMapper();
    private LoggingSingleton logger = LoggingSingleton.getLogger();

    public void getRequestById(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            String idParam = ctx.cookie("reimbId");
            int id = Integer.parseInt(idParam);

            Reimbursement pending = rs.getReimbursementById(id);
            ctx.json(pending);
        }
    }

    public void getAllPendingRequests(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            List<Reimbursement> pending = rs.getAllPendingReimbursements();
            ctx.json(pending);
        }
    }

    public void getAllResolvedRequests(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            List<Reimbursement> approvedReimbursements = rs.getAllApprovedReimbursements();
            List<Reimbursement> deniedReimbursements = rs.getAllDeniedReimbursements();
            approvedReimbursements.addAll(deniedReimbursements);
            ctx.json(approvedReimbursements);
        }
    }

    public void getAllPendingRequestsByUserManager(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            String userParam = ctx.pathParam("username");
            List<Reimbursement> pending = rs.getAllPendingReimbursementsByUser(userParam);
            ctx.json(pending);
        }
    }

    public void getAllResolvedRequestsByUserManager(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            String userParam = ctx.pathParam("username");
            List<Reimbursement> approvedReimbursements = rs.getAllApprovedReimbursementsByUser(userParam);
            List<Reimbursement> deniedReimbursements = rs.getAllDeniedReimbursementsByUser(userParam);
            approvedReimbursements.addAll(deniedReimbursements);
            ctx.json(approvedReimbursements);
        }
    }

    public void getAllPendingRequestsByUser(Context ctx) {
        ctx.header("Access-Control-Expose-Headers", "*");

        String userParam = String.valueOf(ctx.req.getSession().getAttribute("loggedIn"));

        List<Reimbursement> pending = rs.getAllPendingReimbursementsByUser(userParam);
        ctx.json(pending);
    }

    public void getAllResolvedRequestsByUser(Context ctx) {
        ctx.header("Access-Control-Expose-Headers", "*");

        String userParam = String.valueOf(ctx.req.getSession().getAttribute("loggedIn"));

        List<Reimbursement> approvedReimbursements = rs.getAllApprovedReimbursementsByUser(userParam);
        List<Reimbursement> deniedReimbursements = rs.getAllDeniedReimbursementsByUser(userParam);
        approvedReimbursements.addAll(deniedReimbursements);
        ctx.json(approvedReimbursements);
    }

    public void updateReimbursement(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            String userParam = String.valueOf(ctx.req.getSession().getAttribute("loggedIn"));
            updateReimbursementObject uro = null;
            try {
                uro = mapper.readValue(ctx.body(), updateReimbursementObject.class);

                int id = Integer.parseInt(uro.reimbId);
                int rst= Integer.parseInt(uro.reimbStatus);

//                ReimbursementStatus rst = ReimbursementStatus.PENDING;

//                if(uro.ReimbursementStatus.equals("APPROVED")){
//                    rst = ReimbursementStatus.APPROVED;
//                } else if (uro.ReimbursementStatus.equals("DENIED")){
//                    rst = ReimbursementStatus.DENIED;
//                } else {
//                    ctx.status(400);
//                }

                ReimbursementStatus[] status = ReimbursementStatus.values();

                rs.updateReimbursement(id, status[rst], userParam);

                User user = us.getUserByUsername(userParam);

                logger.info(user.getUserRole()+ " " + user.getFirstName() + " " + user.getLastName() +
                        " has " + rst + " reimbursement " + id);


            } catch (Exception e) {
                ctx.status(400);
                e.printStackTrace();
            }

        }


    }

    public void submitReimbursementRequest(Context ctx) {
        String userParam = String.valueOf(ctx.req.getSession().getAttribute("loggedIn"));
        createReimbursementObject cro = null;
        try {
//            ctx.result("This method is running");
            cro = mapper.readValue(ctx.body(),createReimbursementObject.class);

            float amount = Float.parseFloat(cro.amount);
            int reimbType= Integer.parseInt(cro.reimbType);

            String description;

            if (cro.description == ""){
                description = null;
            } else{
                description = cro.description;
            }
//ligne 123 & 124 will convert the string to the data variable of each entity in our class Reimb

            rs.createReimbursement(amount, description, userParam,reimbType);

            User user = us.getUserByUsername(userParam);

            logger.info(user.getUserRole()+ " " + user.getFirstName() + " " + user.getLastName() +
                    " has requested a reimbursement of " + amount );



        } catch (Exception e) {
            ctx.status(400);
            e.printStackTrace();
        }

    }
}

class updateReimbursementObject{
    public String reimbId;
    public String reimbStatus;
}

class createReimbursementObject{
    public String amount;
    public String description;
    public String reimbType;
}