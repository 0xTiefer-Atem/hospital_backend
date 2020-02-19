package org.hospital.management.controller;


import org.hospital.management.dao.StaffDao;
import org.hospital.management.pojo.StaffPojo;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/home/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    StaffDao staffDao;

    @RequestMapping(value = "/staffListInit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseV2 staffListInit() {
        try {
            List<StaffPojo> staffPojoList = staffDao.staffListInit();
            return ResponseHelper.create(staffPojoList, 200, "职员信息队列初始化成功!");
        }catch (Exception e){
            return ResponseHelper.create(500, "职员信息队列初始化失败!");
        }
    }
}
