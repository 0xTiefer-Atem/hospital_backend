package org.hospital.management.pojo;


//病例模板
//{"registerId":"234089","userId":"002","userIllness":"发热头痛","staffId":"123001","medicList":[{"medicId":1,"medicName":"阿莫西林","medicPrice":12,"medicNum":1},{"medicId":6,"medicName":"消炎药","medicPrice":12,"medicNum":2}], "totalPrice":36}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CasePojo {
    private String caseId;
    private String registerId;
    private String userId;
    private String userIllness;
    private String staffId;
    private String medicList;
    private double totalPrice;
    private String createTime;
}
