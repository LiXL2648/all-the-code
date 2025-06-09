package com.li.responsibility;

import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) {
        // 创建请求
        ApproveRequest approveRequest = new ApproveRequest("1", "1", new BigDecimal(1000));
        // 创建相关审批人
        DepartmentApprover departmentApprover = new DepartmentApprover("系主任");
        CollegeApprover collegeApprover = new CollegeApprover("院长");
        ViceSchoolMasterApprover viceSchoolMasterApprover = new ViceSchoolMasterApprover("副校长");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("校长");

        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setApprover(schoolMasterApprover);
        schoolMasterApprover.setApprover(departmentApprover);

        collegeApprover.processRequest(approveRequest);
    }
}
