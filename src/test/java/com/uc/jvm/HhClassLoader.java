package com.uc.jvm;

import java.io.*;

/**
 * Created by yangzhen on 17/8/26.
 */
public class HhClassLoader extends  ClassLoader {

    private static final String DEAFAULTDIR="/Users/yangzhen/data/uc-workspace/test/target/test-classes/";

    public HhClassLoader() {

    }

    public HhClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("find----" + name);
        try {
            byte[] bt = loadClassData(name);
            return defineClass(name, bt, 0, bt.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("load----" + name);
        return super.loadClass(name);
        //return null;
    }


    private static byte[] loadClassData(String name) throws Exception {
        int n =0;
        String filepath = DEAFAULTDIR + name.replace(".","/")+".class";
        BufferedInputStream br = new BufferedInputStream(
                new FileInputStream(
                        new File(filepath)));
        ByteArrayOutputStream bos= new ByteArrayOutputStream();
        while((n=br.read())!=-1){
            bos.write(n);
        }
        br.close();
        return bos.toByteArray();
    }

    private InputStream getClassInputStream(String name) {
        String filename = name.replace('.', '/')+".class";
        InputStream is = getClass().getResourceAsStream(filename);
        return is;
    }

}
