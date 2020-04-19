package org.hospital.management.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.hospital.management.pojo.CasePojo;
import org.hospital.management.pojo.TreatmentQueuePojo;

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
    List<TreatmentQueuePojo> treatmentQueueInfoListInit(String staffId, String startTime, String endTime);

    @Insert("insert into caseInfo(caseId, registerId, userId, userIllness, " +
            "staffId, medicList, totalPrice, createTime) " +
            "values(#{caseId}, #{registerId}, #{userId}, #{userIllness}, " +
            "#{staffId}, #{medicList}, #{totalPrice}, #{createTime})")
    void insertCaseInfo(CasePojo casePojo);

    @Select("select cI.medicMenusList from clinicInfo cI where #{staffId} like concat(cI.cliId,'%')")
    String selectMedicMenusList(String staffId);


    @Update("update registerInfo set status = 'FINISH' where registerId = #{registerId}")
    void updateRegisterStatus(String registerId);

    @Update("update appointmentInfo set status = 'FINISH'" +
            " where appointmentId = (select appointmentId" +
            " from registerInfo where registerId = #{registerId})")
    void updateAppointmentStatus(String registerId);
}
