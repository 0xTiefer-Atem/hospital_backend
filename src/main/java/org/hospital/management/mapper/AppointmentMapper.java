package org.hospital.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.AppointmentPojo;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    @Select("select distinct ap.appointmentId," +
            "ap.userId," +
            "uI.userName," +
            "sI.staffId," +
            "sI.staffName," +
            "ap.appointmentTime," +
            "cI.cliName " +
            "from appointmentInfo ap," +
            "userInfo uI," +
            "staffInfo sI," +
            "clinicInfo cI" +
            " where ap.userId = uI.userId" +
            " and ap.staffId = #{id}" +
            " and ap.staffId = sI.staffId" +
            " and ap.staffId like concat(cI.cliId,'%')" +
            " and ap.appointmentTime between #{startTime} and #{endTime}" +
            " and ap.status = 'WAIT' order by ap.appointmentTime asc")
    List<AppointmentPojo> appointmentListInit(@Param("id") String id, @Param("startTime") String startTime, @Param("endTime") String endTime);


    @Select("select distinct ap.appointmentId," +
            "ap.userId," +
            "uI.userName," +
            "sI.staffId," +
            "sI.staffName," +
            "ap.appointmentTime," +
            "cI.cliName " +
            "from appointmentInfo ap," +
            "userInfo uI," +
            "staffInfo sI," +
            "clinicInfo cI" +
            " where ap.userId = uI.userId" +
            " and ap.staffId = sI.staffId" +
            " and appointmentId = #{id}" +
            " and ap.staffId like concat(cI.cliId,'%')" +
            " and ap.status = 'WAIT'")
    List<AppointmentPojo> searchAppointmentById(String id);
}
