package com.uc.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileTest {

    public static void main(String[] args) throws IOException {
        String name = "/Users/yangxinyan/Documents/all.yxy.txt";
        Path path = Paths.get(name);
        List<String> list = Files.readAllLines(path);

        Map<String, User> map = Maps.newHashMap();
        for(String str : list) {
            String[] arr = StringUtils.split(str," ");
            User u = map.get(arr[2]);
            if(u==null) {
                u = new User();
                u.setUid(arr[2]);
                map.put(arr[2],u);
            }
            if(arr[1].equalsIgnoreCase("Y")) {
                u.setYcount(u.getYcount() + Integer.parseInt(arr[0]));
            } else if (arr[1].equalsIgnoreCase("N")) {
                u.setNcount(u.getNcount() + Integer.parseInt(arr[0]));
            }
        }
        for(Map.Entry<String,User> entry : map.entrySet()) {
            User u = map.get(entry.getKey());
            System.out.println(u.getUid() + " " + u.getNcount() + " " + u.getYcount());
        }

    }

    static  class User {

        private String uid;

        private int ncount;

        private int ycount;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public int getNcount() {
            return ncount;
        }

        public void setNcount(int ncount) {
            this.ncount = ncount;
        }

        public int getYcount() {
            return ycount;
        }

        public void setYcount(int ycount) {
            this.ycount = ycount;
        }
    }
}
