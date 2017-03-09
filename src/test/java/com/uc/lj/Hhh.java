package com.uc.lj;

import com.uc.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangzhen on 17/3/6.
 */
public class Hhh {
    public static void main(String[] args) {
        {
            String runDate = DateUtils.getCurrentDateStr();
            System.out.println(runDate);
            String[] arr = StringUtils.split("通和戈雅公寓 | 联排别墅 | 2室2厅 | 97平米 | 南 北 | 简装", "|");
            System.out.println(arr[4]);
            String a = "磨房北里 | 1室1厅 | 43.81平米 | 南 | 精装^|顶层(共6层)1987年建板楼";
            String er = "[0-9.]*平米";
            Pattern p = Pattern.compile(er);
            Matcher m = p.matcher(a);
            if(m.find()){
                System.out.println(m.group());
            }

            er = "[0-9]室[0-9]厅";
            p = Pattern.compile(er);
            m = p.matcher(a);
            if(m.find()){
                System.out.println(m.group());
            }
        }
    }
}
