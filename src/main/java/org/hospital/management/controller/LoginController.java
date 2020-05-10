package org.hospital.management.controller;


import org.hospital.management.dao.LoginDao;
import org.hospital.management.pojo.LoginPojo;
import org.hospital.management.util.ResponseHelper;
import org.hospital.management.util.ResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping(value = "/api/home")
public class LoginController {

    @Resource
    LoginDao loginDao;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody

    //paraMap是可以存多对kv值的特殊变量
    public ResponseV2 login(@RequestBody Map paraMap) {
        String staffId = (String) paraMap.get("staffId");
        //String name = stu.getName()
        String password = (String) paraMap.get("password");
        System.out.println(staffId);
        try {
            LoginPojo loginPojo = loginDao.selectStaffId(staffId);//调用函数并把结果赋值给loginPojo变量
            if(loginPojo != null && loginPojo.getPassword().equals(password)){
                //ResponseHelper.create() 网上返回请求的模板，我自己照着打出来的
                return ResponseHelper.create(loginPojo, 200, "");//封装登录响应
            } else {
                return ResponseHelper.create(500, "账户或密码错误！");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseHelper.create(500, "登录查询失败！");
        }

    }
}
