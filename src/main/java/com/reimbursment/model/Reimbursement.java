package com.reimbursment.model;

import com.reimbursment.Reimbursement_type;
import java.util.Date;

public class Reimbursement {
    private int reimb_id ;
    private float amount;
    private Date reimb_submitted;
    private Date reimb_resolve;
    private String reimb_descrip;
    private int reimb_reciept;
    private User reimb_author;
    private User reimb_resolver;
    private int status_id;
    private Reimbursement_type reimb_type_id;

    public Reimbursement(){
    }

    public Reimbursement(int reimb_id, float amount, Date reimb_submitted, Date reimb_resolve, String reimb_descrip, int reimb_reciept, User reimb_author, User reimb_resolver, int status_id, Reimbursement_type reimb_type_id) {
        this.reimb_id = reimb_id;
        this.amount = amount;
        this.reimb_submitted = reimb_submitted;
        this.reimb_resolve = reimb_resolve;
        this.reimb_descrip = reimb_descrip;
        this.reimb_reciept = reimb_reciept;
        this.reimb_author = reimb_author;
        this.reimb_resolver = reimb_resolver;
        this.status_id = status_id;
        this.reimb_type_id = reimb_type_id;
    }

    public int getReimb_id() { return reimb_id; }

    public void setReimb_id(int reimb_id) { this.reimb_id = reimb_id; }

    public float getAmount() { return amount; }

    public void setAmount(float amount) {this.amount = amount; }

    public Date getReimb_submitted() { return reimb_submitted;  }

    public void setReimb_submitted(Date reimb_submitted) { this.reimb_submitted = reimb_submitted; }

    public Date getReimb_resolve() { return reimb_resolve;  }

    public void setReimb_resolve(Date reimb_resolve) {  this.reimb_resolve = reimb_resolve; }

    public String getReimb_descrip() { return reimb_descrip; }

    public void setReimb_descrip(String reimb_descrip) { this.reimb_descrip = reimb_descrip;  }

    public int getReimb_reciept() { return reimb_reciept; }

    public void setReimb_reciept(int reimb_reciept) { this.reimb_reciept = reimb_reciept;  }

    public User getReimb_author() { return reimb_author; }

    public void setReimb_author(User reimb_author) { this.reimb_author = reimb_author;  }

    public User getReimb_resolver() { return reimb_resolver; }

    public void setReimb_resolver(User reimb_resolver) { this.reimb_resolver = reimb_resolver; }

    public int getStatus_id() { return status_id; }

    public void setStatus_id(int status_id) { this.status_id = status_id; }

    public Reimbursement_type getReimb_type_id() { return reimb_type_id; }

    public void setReimb_type_id(Reimbursement_type reimb_type_id) {this.reimb_type_id = reimb_type_id; }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id=" + reimb_id +
                ", amount=" + amount +
                ", reimb_submitted=" + reimb_submitted +
                ", reimb_resolve=" + reimb_resolve +
                ", reimb_descrip='" + reimb_descrip + '\'' +
                ", reimb_reciept=" + reimb_reciept +
                ", reimb_author=" + reimb_author +
                ", reimb_resolver=" + reimb_resolver +
                ", status_id=" + status_id +
                ", reimb_type_id=" + reimb_type_id +
                '}';
    }
}
