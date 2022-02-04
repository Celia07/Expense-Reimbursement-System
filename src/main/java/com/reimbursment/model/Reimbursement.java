package com.reimbursment.model;

import java.util.Date;

public class Reimbursement {
    private int reimbId ;
    private float amount;
    private Date reimbSubmitted;
    private Date reimbResolved;
    private String reimbDescription;
    private int reimbReciept;
    private User reimbAuthor;
    private User reimbResolver;
    private ReimbursementStatus reimbStatus;
    private ReimbursementType reimbType;

    public Reimbursement(){
    }

    public Reimbursement(int reimbId, float amount, Date reimbSubmitted, Date reimbResolved, String reimbDescription, int reimbReciept, User reimbAuthor, User reimbResolver, ReimbursementStatus reimbStatus, ReimbursementType reimbType) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescription = reimbDescription;
        this.reimbReciept = reimbReciept;
        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
        this.reimbStatus = reimbStatus;
        this.reimbType = reimbType;
    }

    public Reimbursement(float amount, Date reimbSubmitted, Date reimbResolved, String reimbDescription, int reimbReciept, User reimbAuthor, User reimbResolver, ReimbursementStatus reimbStatus, ReimbursementType reimbType) {
        this.amount = amount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescription = reimbDescription;
        this.reimbReciept = reimbReciept;
        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
        this.reimbStatus = reimbStatus;
        this.reimbType = reimbType;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(Date reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    public Date getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(Date reimbResolved) {
        this.reimbResolved = reimbResolved;
    }

    public String getReimbDescription() {
        return reimbDescription;
    }

    public void setReimbDescription(String reimbDescription) {
        this.reimbDescription = reimbDescription;
    }

    public int getReimbReciept() {
        return reimbReciept;
    }

    public void setReimbReciept(int reimbReciept) {
        this.reimbReciept = reimbReciept;
    }

    public User getReimbAuthor() {
        return reimbAuthor;
    }

    public void setReimbAuthor(User reimbAuthor) {
        this.reimbAuthor = reimbAuthor;
    }

    public User getReimbResolver() {
        return reimbResolver;
    }

    public void setReimbResolver(User reimbResolver) {
        this.reimbResolver = reimbResolver;
    }

    public ReimbursementStatus getReimbStatus() {
        return reimbStatus;
    }

    public void setReimbStatus(ReimbursementStatus reimbStatus) {
        this.reimbStatus = reimbStatus;
    }

    public ReimbursementType getReimbType() {
        return reimbType;
    }

    public void setReimbType(ReimbursementType reimbType) {
        this.reimbType = reimbType;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", amount=" + amount +
                ", reimbSubmitted=" + reimbSubmitted +
                ", reimbResolved=" + reimbResolved +
                ", reimbDescription='" + reimbDescription + '\'' +
                ", reimbReciept=" + reimbReciept +
                ", reimbAuthor=" + reimbAuthor +
                ", reimbResolver=" + reimbResolver +
                ", reimbStatus=" + reimbStatus +
                ", reimbType=" + reimbType +
                '}';
    }
}
