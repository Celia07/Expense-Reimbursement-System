package com.reimbursement.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.Daos.UserDao;
import com.reimbursement.Daos.UserDaoImpl;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.ReimbursementStatus;
import com.reimbursement.model.User;
import com.reimbursement.services.UserService;
import com.reimbursement.util.LoggingSingleton;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;

import java.util.List;

public class UserController {

    private UserDao ud = new UserDaoImpl();


    private final UserService us = new UserService(ud);
    private ObjectMapper mapper = new ObjectMapper();
    private final LoggingSingleton logger = LoggingSingleton.getLogger();

    public void getAllEmployees(Context ctx) {
        if (!ctx.req.getSession().getAttribute("userRole").equals(1)) {
            throw new ForbiddenResponse("Must be a Manager to view this page");
        } else {
            List<User> employees = us.getAllEmployees();
            ctx.json(employees);
        }
    }
    public void viewAccountInformationByUser(Context ctx){
        ctx.header("Access-Control-Expose-Headers", "*");

        String userParam = String.valueOf(ctx.req.getSession().getAttribute("loggedIn"));

        User employee = us.getUserByUsername(userParam);
        ctx.json(employee);
    }

    public void updateAccountInformation(Context ctx) {
        String userParam = String.valueOf(ctx.req.getSession().getAttribute("loggedIn"));
        updateInformationObject uio = null;
        try {
            uio = mapper.readValue(ctx.body(), updateInformationObject.class);

            User employee = us.getUserByUsername(userParam);

            us.updateOtherInformation(employee,uio.FirstName, uio.LastName, uio.Username,uio.email);

            logger.info(employee.getUserRole()+ " " + employee.getFirstName() + " " + employee.getLastName() +
                    " has updated their personal information");

        } catch (Exception e) {
            ctx.status(400);
            e.printStackTrace();
        }

    }
    public void updateAccountInformationPassword(Context ctx) {
        String userParam = String.valueOf(ctx.req.getSession().getAttribute("loggedIn"));
        updateInformationPasswordObject uip = null;
        try {
            uip = mapper.readValue(ctx.body(), updateInformationPasswordObject.class);

            us.updatePassword(userParam,uip.oldPass,uip.newPass);

            User employee = us.getUserByUsername(userParam);

            logger.info(employee.getUserRole()+ " " + employee.getFirstName() + " " + employee.getLastName() +
                    " has updated their password");

        } catch (Exception e) {
            ctx.status(400);
            e.printStackTrace();
        }

    }


}

class updateInformationObject{
    public String FirstName;
    public String LastName;
    public String Username;
    public String email;
}

class updateInformationPasswordObject {
    public String oldPass;
    public String newPass;

}
