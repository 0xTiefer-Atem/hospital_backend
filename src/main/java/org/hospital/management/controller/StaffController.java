package org.hospital.management.controller;


import org.hospital.management.dao.StaffDao;
import org.hospital.management.pojo.StaffPojo;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/deleteStaffById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 deleteStaffById(@RequestBody Map paraMap) {
        String staffId = (String) paraMap.get("staffId");
        try {
            staffDao.deleteStaffById(staffId);
            return ResponseHelper.create(200, "职员删除成功!");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseHelper.create(500, "职员删除失败!");
        }
    }
}
