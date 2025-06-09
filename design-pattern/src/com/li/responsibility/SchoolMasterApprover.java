package com.li.responsibility;

import java.math.BigDecimal;

public class SchoolMasterApprover extends Approver {

    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(ApproveRequest approveRequest) {
        if (approveRequest.getPrice().compareTo(new BigDecimal(20000)) > 0) {
            System.out.println("请求编号：" + approveRequest.getId() + "被" + this.getName() + "处理");
        } else {
            this.getApprover().processRequest(approveRequest);
        }
    }
}
