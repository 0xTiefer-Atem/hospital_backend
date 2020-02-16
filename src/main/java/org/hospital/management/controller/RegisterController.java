package org.hospital.management.controller;

import org.hospital.management.dao.RegisterDao;
import org.hospital.management.pojo.RegisterPojo;
import org.hospital.management.util.GetUUID;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.hospital.management.util.TimeOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/home/register")
@CrossOrigin
public class RegisterController {
    @Autowired
    RegisterDao registerDao;

    @RequestMapping(value = "/registerById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 registerById(HttpServletRequest request){
        String appointmentId = request.getParameter("appointmentId");
        String staffId = request.getParameter("staffId");
        String userId = request.getParameter("userId");
        RegisterPojo registerPojo = new RegisterPojo();

        registerPojo.setRegisterId(GetUUID.getUUID());
        registerPojo.setAppointmentId(appointmentId);
        registerPojo.setStaffId(staffId);
        registerPojo.setUserId(userId);
        registerPojo.setCreateTime(TimeOpt.getCurrentTime());

        try{
            registerDao.insertRegister(registerPojo);
        }catch (Exception e){
            System.out.println(e);
            return ResponseHelper.create(500, "挂号操作失败");
        }
        return ResponseHelper.create(200,"挂号操作失败");
    }
}
