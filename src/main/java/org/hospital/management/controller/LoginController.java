package org.hospital.management.controller;


import org.hospital.management.dao.LoginDao;
import org.hospital.management.pojo.LoginPojo;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/home")
public class LoginController {

    @Autowired
    LoginDao loginDao;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody

    //paraMap是可以存多对kv值的特殊变量
    public ResponseV2 login(@RequestBody Map paraMap) {
        String staffId = (String) paraMap.get("staffId");
        //String name = stu.getName()
        String password = (String) paraMap.get("password");
        System.out.println(staffId);
        LoginPojo loginPojo = loginDao.selectStaffId(staffId);//调用函数并把结果赋值给loginPojo变量
        if(loginPojo != null && "123456".equals(password)){
            //ResponseHelper.create() 网上返回请求的模板，我自己照着打出来的
            return ResponseHelper.create(loginPojo, 200, "");//封装登录响应
        }
        return ResponseHelper.create(500, "账户或密码错误！");
    }
}
