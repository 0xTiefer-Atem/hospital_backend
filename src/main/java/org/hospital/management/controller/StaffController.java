package org.hospital.management.controller;


import org.hospital.management.dao.StaffDao;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/home/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    StaffDao staffDao;
    @RequestMapping(value = "/staffListInit", method = RequestMethod.GET)
    public ResponseV2 staffListInit() {

        return ResponseHelper.create(200, "");
    }
}
