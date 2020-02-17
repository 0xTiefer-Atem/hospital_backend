package org.hospital.management.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hospital.management.pojo.RegisterPojo;

import java.util.List;

@Mapper
public interface RegisterDao {
    @Insert("insert into registerInfo(registerId, appointmentId, staffId, " +
            "userId, createTime) values(#{registerId}, #{appointmentId}," +
            "#{staffId}, #{userId}, #{createTime})")
    void insertRegister(RegisterPojo r);

    @Update("update appointmentInfo set status = 'SUCCESS' where appointmentId = #{id}")
    void upDateAppointmentStatus(String id);

    @Select("select distinct  rI.appointmentId, rI.registerId, rI.userId, uI.userName," +
            "                rI.staffId, sI.staffName, cI.cliName, rI.createTime" +
            " from registerInfo rI," +
            "     userInfo uI," +
            "     staffInfo sI," +
            "     clinicInfo cI" +
            " where rI.userId = uI.userId" +
            "  and rI.staffId = sI.staffId" +
            "  and rI.staffId = #{staffId}" +
            "  and rI.staffId like concat(cI.cliId,'%')" +
            "  and rI.createTime between #{startTime} and #{endTime}")
    List<RegisterPojo> registerListInit(String staffId, String startTime, String endTime);
}
