package org.hospital.management.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hospital.management.dao.StaffDao;
import org.hospital.management.pojo.StaffPojo;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.hospital.management.util.TimeOpt;
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


    @RequestMapping(value = "/addStaff", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 addStaff(@RequestBody Map paraMap) {
        System.out.println(paraMap);
        String staffInfo = (String) paraMap.get("staffInfo");
        JSONObject jsonObject = JSON.parseObject(staffInfo);

        String staffId = jsonObject.getString("staffId");
        String staffName = jsonObject.getString("staffName");
        String staffSex = jsonObject.getString("staffSex");
        String staffPos = jsonObject.getString("staffPos");
        String staffTel = jsonObject.getString("staffTel");
        String staffEntry = jsonObject.getString("staffEntry");
        String createTime = TimeOpt.getCurrentTime().split(" ")[0];

        StaffPojo staffPojo = new StaffPojo();
        staffPojo.setStaffId(staffId);
        staffPojo.setStaffName(staffName);
        staffPojo.setStaffSex(staffSex);
        staffPojo.setStaffPos(staffPos);
        staffPojo.setStaffTel(staffTel);
        staffPojo.setStaffEntry(staffEntry);
        staffPojo.setCreateTime(createTime);

        try {
            staffDao.addStaff(staffPojo);
            return ResponseHelper.create(200, "新职员添加成功!");
        }catch (Exception e) {
            return ResponseHelper.create(500, "新职员添加失败!");
        }
    }
}
