package org.hospital.management.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.hospital.management.pojo.RegisterPojo;

@Mapper
public interface RegisterDao {
    @Insert("insert into register(registerId, appointmentId, staffId, " +
            "userId, createdTime) values(#{registerId}, #{appointmentId}," +
            "#{staffId}, #{userId}, #{createdTime})")
    void insertRegister(RegisterPojo r);

    @Update("update appointmentInfo set status = 'SUCCESS' where appointmentId = #{id}")
    void upDateAppointmentStatus(String id);
}
