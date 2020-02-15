package org.hospital.management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.AppointmentPojo;

import java.util.List;

@Mapper
public interface AppointmentDao {
    @Select("select distinct ap.appointmentId,uI.userName,sI.staffName,ap.appointmentTime" +
            ",cI.cliName" +
            " from appointmentInfo ap join userInfo uI on ap.userId = uI.userId" +
            " join staffInfo sI on ap.staffId = sI.staffId" +
            " join clinicInfo cI")
    List<AppointmentPojo> appointmentListInit();


    @Select("select distinct ap.appointmentId," +
            "ap.userId," +
            "uI.userName," +
            "sI.staffName," +
            "ap.appointmentTime," +
            "cI.cliName" +
            " from appointmentInfo ap," +
            "userInfo uI," +
            "staffInfo sI," +
            "clinicInfo cI" +
            " where ap.userId = uI.userId" +
            " and ap.staffId = sI.staffId" +
            " and appointmentId = #{id}" +
            " and ap.staffId like concat(cI.cliId,'%')")
    List<AppointmentPojo> searchAppointmentById(String id);
}
