package org.hospital.management.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.StaffPojo;

import java.util.List;

@Mapper
public interface StaffDao {
    @Select("select * from staffInfo")
    List<StaffPojo> staffListInit();

    @Delete("delete from staffInfo where staffId = #{ID}")
    void deleteStaffById(String id);

    @Insert("insert into staffInfo (staffId, staffName, staffSex, staffTel, " +
            "staffPos, staffEntry, createTime) values(#{staffId}, " +
            "#{staffName}, #{staffSex}, #{staffTel}, #{staffPos}, " +
            "#{staffEntry}, #{createTime})")
    void addStaff(StaffPojo staffPojo);
}
