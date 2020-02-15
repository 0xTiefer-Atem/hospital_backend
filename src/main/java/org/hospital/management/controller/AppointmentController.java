package org.hospital.management.controller;

import org.hospital.management.dao.AppointmentDao;
import org.hospital.management.pojo.AppointmentPojo;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/home/appointment")
@CrossOrigin
public class AppointmentController {
    @Autowired
    AppointmentDao appointmentDao;


    @RequestMapping(value = "/appointmentListInit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseV2 appointmentListInit() {
        List<AppointmentPojo> appointmentList = appointmentDao.appointmentListInit();
        return ResponseHelper.create(appointmentList, 200, "预约队列查询成功");
    }


    @RequestMapping(value = "/searchByAppointmentId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 searchByAppointmentId(HttpServletRequest request) {
        String appointmentId = request.getParameter("appointmentId");
        System.out.println(appointmentId);
        List<AppointmentPojo> appointmentPojoList = appointmentDao.searchAppointmentById(appointmentId);
        return ResponseHelper.create(appointmentPojoList, 200, "查询预约号成功");
    }
}
