package org.hospital.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreatmentQueuePojo {
    private String registerId;
    private String userId;
    private String userName;
    private String staffId;
    private String staffName;
    private String createTime;
}
