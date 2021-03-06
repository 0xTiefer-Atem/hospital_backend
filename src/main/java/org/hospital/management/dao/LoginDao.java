package org.hospital.management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.LoginPojo;


@Mapper
public interface LoginDao {
    @Select("select staffId, password from staffInfo where staffId = #{staffId}")
    LoginPojo selectStaffId(String staffId);//定义方法
}
