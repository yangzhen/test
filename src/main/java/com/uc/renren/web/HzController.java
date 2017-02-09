package com.uc.renren.web;

import com.uc.renren.bean.StatBean;
import com.uc.renren.dao.TestDao;
import com.uc.renren.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by yangzhen on 16/12/26.
 */
@Controller
@RequestMapping("hz")
public class HzController {

    @Autowired
    private TestDao dao;

    public Result<List<StatBean>> getStat(String start,String end) {
        return null;
    }
}

