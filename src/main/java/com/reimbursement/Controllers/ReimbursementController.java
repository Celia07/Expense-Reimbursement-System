package com.reimbursement.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.ReimbursementStatus;
import com.reimbursement.model.User;
import com.reimbursement.services.ReimbursementService;
import com.reimbursement.services.UserService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

import java.util.List;

public class ReimbursementController {

    private final ReimbursementService rs = new ReimbursementService();
    private final UserService us = new UserService();
    private ObjectMapper mapper = new ObjectMapper();

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

                int id = Integer.parseInt(uro.ReimbursementId);

                ReimbursementStatus rst = ReimbursementStatus.PENDING;

                if(uro.ReimbursementStatus.equals("APPROVED")){
                    rst = ReimbursementStatus.APPROVED;
                } else if (uro.ReimbursementStatus.equals("DENIED")){
                    rst = ReimbursementStatus.DENIED;
                } else {
                    ctx.status(400);
                }

                rs.updateReimbursement(id, rst, userParam);


            } catch (Exception e) {
                ctx.status(400);
                e.printStackTrace();
            }

        }

    }
}
class updateReimbursementObject{
    public String ReimbursementId;
    public String ReimbursementStatus;
}
