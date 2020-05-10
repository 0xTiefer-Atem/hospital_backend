package org.hospital.management.controller;

import org.hospital.management.dao.AppointmentDao;
import org.hospital.management.pojo.AppointmentPojo;
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
@RequestMapping(value = "/api/home/appointment")
@CrossOrigin
public class AppointmentController {
    @Resource
    AppointmentDao appointmentDao;


    @RequestMapping(value = "/appointmentListInit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 appointmentListInit(@RequestBody Map paraMap) {
        String staffId = (String) paraMap.get("staffId");
        String startTime = TimeOpt.getCurrentTime().split(" ")[0];
        String endTime = TimeOpt.getFetureDate(1).split(" ")[0];
        try {
            List<AppointmentPojo> appointmentList = appointmentDao.appointmentListInit(staffId, startTime, endTime);
            return ResponseHelper.create(appointmentList, 200, "预约队列查询成功!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHelper.create(500, "预约队列查询失败!");

        }
    }


    @RequestMapping(value = "/searchByAppointmentId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 searchByAppointmentId(@RequestBody Map paraMap) {
        String appointmentId = (String) paraMap.get("appointmentId");
        System.out.println(appointmentId);
        List<AppointmentPojo> appointmentPojoList = appointmentDao.searchAppointmentById(appointmentId);
        return ResponseHelper.create(appointmentPojoList, 200, "查询预约号成功");
    }
}
