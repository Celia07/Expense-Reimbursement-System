package com.reimbursement.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.Daos.UserDao;
import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.User;
import com.reimbursement.model.UserRoles;
import com.reimbursement.services.AuthService;
import com.reimbursement.services.UserService;
import com.reimbursement.util.LoggingSingleton;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;


public class AuthController {

    private UserDao ud = new UserDaoImpl();

    private UserService us = new UserService(ud);
    private AuthService as = new AuthService(ud);
    private UserRoles ur;
    private ObjectMapper mapper = new ObjectMapper();
    private LoggingSingleton logger = LoggingSingleton.getLogger();

    public void authenticateLogin(Context ctx) {

        LoginObject lo = null;
        try {
            lo = mapper.readValue(ctx.body(), LoginObject.class);


            System.out.println(lo.username + " , " + lo.password);

            if(!as.loginUser(lo.username, lo.password)){
                ctx.status(400);
                ctx.result("Username or password is incorrect");
            }else {

                User user = us.getUserByUsername(lo.username);


                ctx.req.getSession().setAttribute("id", "" + user.getUserId());
                ctx.req.getSession().setAttribute("loggedIn", user.getUsername());
                ctx.req.getSession().setAttribute("userRole", user.getUserRole().ordinal());

                ctx.header("id", "" + user.getUserId());
                ctx.header("loggedIn", user.getUsername());
                ctx.header("userRole", String.valueOf(user.getUserRole().ordinal()));


                ctx.result(mapper.writeValueAsString(user));

                logger.info(user.getUserRole()+ " " + user.getFirstName() + " " + user.getLastName() +
                        " has signed in");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void verifyLogin(Context ctx){
        ctx.header("Access-Control-Expose-Headers", "*");

        System.out.println(ctx.req.getSession().getAttribute("id"));

        if(ctx.req.getSession().getAttribute("id") == null) {

            throw new ForbiddenResponse("User not logged in");
        }
        else {
            ctx.header("pid", ""+ctx.req.getSession().getAttribute("id"));
            return;
        }

    }

//    public void verifyManager(Context ctx){
//
//        ctx.header("Access-Control-Expose-Headers", "*");
//
//        System.out.println(ctx.req.getSession().getAttribute("userRole"));
//
//
//        if (ctx.req.getSession().getAttribute("userRole").equals(1)) {
//            return;
//        } else if (ctx.req.getSession().getAttribute("userRole").equals(0)) {
//            throw new ForbiddenResponse("Must be a Manager to view this page");
//        } else {
//            ctx.header("pid", "" + ctx.req.getSession().getAttribute("id"));
//            throw new ForbiddenResponse("Must log in to view this page");
//        }
//
//
//
//    }

    public void logout(Context ctx){
        ctx.req.getSession().invalidate();
        ctx.status(200);
        ctx.result("User logged out");

    }


}

class LoginObject{
    public String username;
    public String password;
}
