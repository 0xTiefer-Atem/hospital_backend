package org.hospital.management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.StaffPojo;

import java.util.List;

@Mapper
public interface StaffDao {
    @Select("select * from staffInfo")
    List<StaffPojo> staffListInit();
}
