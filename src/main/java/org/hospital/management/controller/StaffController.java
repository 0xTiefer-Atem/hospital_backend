package org.hospital.management.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hospital.management.mapper.StaffMapper;
import org.hospital.management.pojo.StaffPojo;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.hospital.management.util.TimeOpt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/home/staff")
@CrossOrigin
public class StaffController {

    @Resource
    private StaffMapper staffMapper;

    @RequestMapping(value = "/staffListInit", method = RequestMethod.GET)
    public ResponseV2 staffListInit() {
        try {
            List<StaffPojo> staffPojoList = staffMapper.staffListInit();
            return ResponseHelper.create(staffPojoList, 200, "职员信息队列初始化成功!");
        } catch (Exception e) {
            return ResponseHelper.create(500, "职员信息队列初始化失败!");
        }
    }

    @RequestMapping(value = "/deleteStaffById", method = RequestMethod.POST)
    public ResponseV2 deleteStaffById(@RequestBody Map paraMap) {
        String staffId = (String) paraMap.get("staffId");
        try {
            staffMapper.deleteStaffById(staffId);
            return ResponseHelper.create(200, "职员删除成功!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseHelper.create(500, "职员删除失败!");
        }
    }


    @RequestMapping(value = "/addStaff", method = RequestMethod.POST)
    public ResponseV2 addStaff(@RequestBody Map paraMap) {
        StaffPojo staffPojo = optRequestData(paraMap, "staffInfo");
        staffPojo.setPassword("123456");
        System.out.println(staffPojo.toString());
        try {
            staffMapper.addStaff(staffPojo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHelper.create(500, "新职员添加失败!");
        }
        return ResponseHelper.create(200, "新职员添加成功!");
    }


    @RequestMapping(value = "/editStaff")
    @ResponseBody
    public ResponseV2 editStaff(@RequestBody Map paraMap) {

        //将json字符串转成java对象
        StaffPojo staffPojo = optRequestData(paraMap, "editStaff");

        System.out.println(staffPojo.toString());

        try {

            //先删除再插入达到更新效果
            staffMapper.deleteStaffById(staffPojo.getStaffId());
            staffMapper.addStaff(staffPojo);
            return ResponseHelper.create(200, "修改职员信息成功!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseHelper.create(500, "修改职员信息失败!");
        }
    }


    private StaffPojo optRequestData(Map paraMap, String type) {

        String staffInfo = (String) paraMap.get(type);


        JSONObject jsonObject = JSON.parseObject(staffInfo);

        String staffId = jsonObject.getString("staffId");
        String staffName = jsonObject.getString("staffName");
        String password = jsonObject.getString("password");
        String staffSex = jsonObject.getString("staffSex");
        String staffPos = jsonObject.getString("staffPos");
        String staffTel = jsonObject.getString("staffTel");
        String staffEntry = jsonObject.getString("staffEntry");
        String describe = jsonObject.getString("describe");
        String staffCover = "http://47.107.64.157/blog/20210606/35341622967770291.jpg";

        StaffPojo staffPojo = new StaffPojo();
        staffPojo.setStaffId(staffId);
        staffPojo.setStaffName(staffName);
        staffPojo.setStaffSex(staffSex);
        staffPojo.setPassword(password);
        staffPojo.setStaffPos(staffPos);
        staffPojo.setStaffTel(staffTel);
        staffPojo.setStaffEntry(staffEntry);
        staffPojo.setDescribe(describe);
        staffPojo.setStaffCover(staffCover);
        staffPojo.setCreateTime(new Date());

        return staffPojo;
    }
}
