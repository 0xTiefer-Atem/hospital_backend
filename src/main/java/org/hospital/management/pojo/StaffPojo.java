package org.hospital.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StaffPojo {
    private String staffId;
    private String password;
    private String staffName;
    private String staffSex;
    private String staffPos;
    private String staffTel;
    private String staffEntry;
    private Date createTime;
    private String staffCover;
    private String describe;

}
