package org.hospital.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentPojo {
    String appointmentId;
    String userId;
    String userName;
    String cliName;
    String staffId;
    String staffName;
    String appointmentTime;
}
