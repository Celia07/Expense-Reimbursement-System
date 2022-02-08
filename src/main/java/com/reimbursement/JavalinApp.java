package com.reimbursement;

import com.reimbursement.Controllers.AuthController;
import com.reimbursement.Controllers.ReimbursementController;
import com.reimbursement.Controllers.UserController;
import io.javalin.Javalin;


import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {


    private static AuthController ac = new AuthController();
    private static UserController uc = new UserController();
    private static ReimbursementController rc = new ReimbursementController();


    private Javalin app = Javalin.create().routes(()->{


        before("logout", ac::verifyLogin);
        path("logout", ()->{
            get(ac::logout);
        });

        before("people", ac::verifyLogin);
        path("people", ()->{
            get(uc::getAllEmployees);
        });

        before("home", ac::verifyLogin);
        path("home", ()->{
//            get(uc::getAllEmployees);
        });



        before("user", ac::verifyLogin);
        path("user", ()->{
            path("user-pending", ()->{
                get(rc::getAllPendingRequestsByUser);
            });
            path("user-resolved", ()->{
                get(rc::getAllResolvedRequestsByUser);
            });
        });



        before("requests", ac::verifyLogin);
        path("requests", ()->{
            path("requests-pending", ()->{
                get(rc::getAllPendingRequests);
                put(rc::updateReimbursement);
            });
            path("requests-resolved", ()->{
                get(rc::getAllResolvedRequests);
            });
        });

        path("login", ()->{
            post(ac::authenticateLogin);
        });

        before("pending", ac::verifyLogin);
        before("resolved", ac::verifyLogin);
        path("{username}", ()->{
            path("pending", ()->{
                get(rc::getAllPendingRequestsByUserManager);
                put(rc::updateReimbursement);
            });
            path("resolved", ()->{
                get(rc::getAllResolvedRequestsByUserManager);
            });
        });

    });

    public void start(int port){
        app.start(port);
    }
}
