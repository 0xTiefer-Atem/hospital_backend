package org.hospital.management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.AppointmentPojo;

import java.util.List;

@Mapper
public interface AppointmentDao {
    @Select("")
    List<AppointmentPojo> appointmentListInit();
}
