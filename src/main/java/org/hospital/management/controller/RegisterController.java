package org.hospital.management.controller;

import org.hospital.management.dao.RegisterDao;
import org.hospital.management.pojo.RegisterPojo;
import org.hospital.management.util.GetUUID;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.hospital.management.util.TimeOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/home/queue")
@CrossOrigin
public class RegisterController {
    @Resource
    RegisterDao registerDao;


    //预约->挂号处理
    @RequestMapping(value = "/registerById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 registerById(@RequestBody Map paraMap){
        System.out.println(paraMap);
        String appointmentId = (String) paraMap.get("appointmentId");
        String staffId = (String) paraMap.get("staffId");
        String userId = (String) paraMap.get("userId");
        RegisterPojo registerPojo = new RegisterPojo();
        registerPojo.setRegisterId(GetUUID.getUUID());
        registerPojo.setAppointmentId(appointmentId);
        registerPojo.setStaffId(staffId);
        registerPojo.setUserId(userId);
        registerPojo.setCreateTime(TimeOpt.getCurrentTime());
        System.out.println(registerPojo.toString());

        try{
            registerDao.insertRegister(registerPojo);
            registerDao.upDateAppointmentStatus(registerPojo.getAppointmentId());
        }catch (Exception e){
            System.out.println(e);
            return ResponseHelper.create(500, "挂号操作失败");
        }
        return ResponseHelper.create(200,"挂号操作成功");
    }



    //初始化挂号队列
    @RequestMapping(value = "/registerListInit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 registerListInit(@RequestBody Map paraMap){
        String staffId = (String) paraMap.get("staffId");
        String startTime = TimeOpt.getCurrentTime().split(" ")[0];
        String endTime = TimeOpt.getFetureDate(1).split(" ")[0];
        System.out.println(staffId + "   " + startTime + "   " + endTime);
        try {
            List<RegisterPojo> registerPojoList = registerDao.registerListInit(staffId, startTime, endTime);
            return ResponseHelper.create(registerPojoList,200,"挂号队列初始化查询成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseHelper.create(500,"挂号队列初始化查询失败");
        }
    }

    //挂号成功 -> 修改状态 -> 等待就诊
    @RequestMapping(value = "/treatmentById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 treatmentById(@RequestBody Map paraMap) {
        String registerId = (String) paraMap.get("registerId");
        System.out.println(registerId);
        try {
            registerDao.upDateRegisterStatus(registerId);
            return ResponseHelper.create(200,"就诊排队成功");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseHelper.create(500,"就诊排队失败");
        }
    }


}
