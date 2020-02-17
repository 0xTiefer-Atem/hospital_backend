package org.hospital.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasePojo {
    private String registerId;
    private String userId;
    private String userName;
    private String staffId;
    private String staffName;
    private String createTime;
}
