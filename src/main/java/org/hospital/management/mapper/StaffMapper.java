package org.hospital.management.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.LoginPojo;
import org.hospital.management.pojo.StaffPojo;

import java.util.List;

@Mapper
public interface StaffMapper {
    @Select("select * from staffInfo")
    List<StaffPojo> staffListInit();

    @Delete("delete from staffInfo where staffId = #{ID}")
    void deleteStaffById(String id);

    @Insert("insert into staffInfo(staffId, password, staffName, staffSex, staffTel, " +
            "staffPos, staffEntry, createTime) values(#{staffId}, #{password}, " +
            "#{staffName}, #{staffSex}, #{staffTel}, #{staffPos}, " +
            "#{staffEntry}, #{createTime})")
    void addStaff(StaffPojo staffPojo);


    @Select("select staffId, password from staffInfo where staffId = #{staffId}")
    LoginPojo selectStaffId(String staffId);//定义方法
}
