package org.hospital.management.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hospital.management.dao.CaseDao;
import org.hospital.management.dao.RegisterDao;
import org.hospital.management.pojo.CasePojo;
import org.hospital.management.pojo.TreatmentQueuePojo;
import org.hospital.management.util.GetUUID;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.hospital.management.util.TimeOpt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/home/case")
public class CaseController {

    @Resource
    CaseDao caseDao;

    @RequestMapping(value = "/treatmentQueueInfoListInit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 treatmentQueueInfoListInit(@RequestBody Map paraMap) {
        String staffId = (String) paraMap.get("staffId");
        String startTime = TimeOpt.getCurrentTime().split(" ")[0];
        String endTime = TimeOpt.getFetureDate(1).split(" ")[0];
        System.out.println(staffId + "   " + startTime + "   " + endTime);
        try {
            List<TreatmentQueuePojo> caseQueueInfoList = caseDao.treatmentQueueInfoListInit(staffId, startTime, endTime);
            String medicMenuList = caseDao.selectMedicMenusList(staffId);
            JSONArray medicMenuListArray = JSON.parseArray(medicMenuList);
            Map<String, Object> map = new HashMap<>();
            map.put("caseQueueInfoList", caseQueueInfoList);
            map.put("medicMenuList", medicMenuListArray);
            return ResponseHelper.create(map, 200, "就诊排队列表信息查询成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHelper.create(500, "就诊排队列表信息查询失败!");
        }
    }

    @RequestMapping(value = "/insertCaseInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 insertCaseInfo(@RequestBody Map paraMap) {
        String caseData = (String) paraMap.get("caseData");
        JSONObject jsonObject = JSON.parseObject(caseData);
        System.out.println(jsonObject.toJSONString());
        CasePojo casePojo = new CasePojo();
        String caseId = GetUUID.getUUID();
        String createTime = TimeOpt.getCurrentTime();
        casePojo.setCaseId(caseId);
        casePojo.setRegisterId(jsonObject.getString("registerId"));
        casePojo.setUserId(jsonObject.getString("userId"));
        casePojo.setUserIllness(jsonObject.getString("userIllness"));
        casePojo.setStaffId(jsonObject.getString("staffId"));
        casePojo.setMedicList(jsonObject.getString("medicList"));
        casePojo.setTotalPrice(jsonObject.getDouble("totalPrice"));
        casePojo.setCreateTime(createTime);
        try {
            caseDao.insertCaseInfo(casePojo);
            caseDao.updateRegisterStatus(casePojo.getRegisterId());
            caseDao.updateAppointmentStatus(casePojo.getRegisterId());
            return ResponseHelper.create(200, "病例信息插入成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseHelper.create(500, "病例信息插入失败");
        }
    }
}
