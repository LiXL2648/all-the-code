package com.li.responsibility;

public abstract class Approver {

    private final String name;

    private Approver approver;

    public Approver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void processRequest(ApproveRequest approveRequest);
}
