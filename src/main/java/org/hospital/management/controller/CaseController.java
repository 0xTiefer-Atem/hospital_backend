package org.hospital.management.controller;

import org.hospital.management.dao.CaseDao;
import org.hospital.management.pojo.TreatmentQueuePojo;
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
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/home/case")
public class CaseController {

    @Autowired
    CaseDao caseDao;

    @RequestMapping(value = "/treatmentQueueInfoListInit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 treatmentQueueInfoListInit(HttpServletRequest request) {
        String staffId = request.getParameter("staffId");
        String startTime = TimeOpt.getCurrentTime().split(" ")[0];
        String endTime = TimeOpt.getFetureDate(1).split(" ")[0];
        System.out.println(staffId + "   " + startTime + "   " + endTime);
        try {
            List<TreatmentQueuePojo> caseQueueInfoList = caseDao.treatmentQueueInfoListInit(staffId, startTime, endTime);
            return ResponseHelper.create(caseQueueInfoList, 200, "就诊排队列表信息查询成功!");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseHelper.create(500, "就诊排队列表信息查询失败!");
        }
    }
}
