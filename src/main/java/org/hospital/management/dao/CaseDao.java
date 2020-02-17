package org.hospital.management.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hospital.management.pojo.CasePojo;

import java.util.List;

@Mapper
public interface CaseDao {

    //从挂号表中选出状态为SUCCESS的 staffId 的就诊信息列表
    @Select("select distinct  rI.registerId, rI.userId, uI.userName," +
            " rI.staffId, sI.staffName, rI.createTime" +
            " from registerInfo rI," +
            " userInfo uI," +
            " staffInfo sI," +
            " clinicInfo cI" +
            " where rI.userId = uI.userId" +
            "  and rI.staffId = sI.staffId" +
            "  and rI.status = 'SUCCESS'" +
            "  and rI.staffId = #{staffId}" +
            "  and rI.staffId like concat(cI.cliId,'%')" +
            "  and rI.createTime between #{startTime} and #{endTime}" +
            "  order by rI.createTime asc")
    List<CasePojo> caseQueueInfoListInit(String staffId,String startTime, String endTime);
}
