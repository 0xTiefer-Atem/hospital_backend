package org.hospital.management.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentPojo {
    String appointmentId;
    String userName;
    String cliName;
    String staffName;
    String appointmentTime;
}
