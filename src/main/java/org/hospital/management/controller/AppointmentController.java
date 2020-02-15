package org.hospital.management.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/home/appointment")
public class AppointmentController {

    @RequestMapping(value = "/appointmentListInit", method = RequestMethod.GET)
    @ResponseBody
    public String response() {
        return "哈哈哈哈";
    }
}
