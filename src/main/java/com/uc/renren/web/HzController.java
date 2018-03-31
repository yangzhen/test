package com.uc.renren.web;

import com.alibaba.fastjson.JSONObject;
import com.uc.renren.bean.StatBean;
import com.uc.renren.dao.TestDao;
import com.uc.renren.util.RES_STATUS;
import com.uc.renren.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yangzhen on 16/12/26.
 */
@RestController
@RequestMapping("hz")
public class HzController {

    @Autowired
    private TestDao dao;

    @RequestMapping("get")
    public Result<JSONObject> getUcreditAccount() {
        Result<JSONObject> result = new Result<>(RES_STATUS.SUCCESS);

        JSONObject json = new JSONObject();
        json.put("userCreditId", "kounan2812");
        json.put("userCreditPwd", "kounan2812");

        result.setData(json);
        
        return result;
    }

    @RequestMapping("stat")
    public Result<List<StatBean>> getStat(String start,String end) {
        return null;
    }

}

