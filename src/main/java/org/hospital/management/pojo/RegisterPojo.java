package org.hospital.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPojo {
    String registerId;
    String appointmentId;
    String userId;
    String userName;
    String staffId;
    String staffName;
    String createTime;//创建时间也是挂号时间
}
