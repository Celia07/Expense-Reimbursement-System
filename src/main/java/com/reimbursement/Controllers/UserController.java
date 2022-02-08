package com.reimbursement.Controllers;

import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.User;
import com.reimbursement.services.UserService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

import java.util.List;

public class UserController {

    private final UserService userService = new UserService();

    public void getAllEmployees(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            List<User> employees = userService.getAllEmployees();
            ctx.json(employees);
        }
    }

}
