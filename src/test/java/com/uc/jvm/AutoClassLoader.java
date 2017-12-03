package com.uc.jvm;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;


public class AutoClassLoader extends ClassLoader {

    //定义文件所在目录
    private static final String DEAFAULTDIR="/Users/yangzhen/data/uc-workspace/test/target/test-classes/";
    //定义文件绝对路径
    private static String FILEPATH="";
    
    /*
    * 重写ClassLoader类的findClass方法，将一个字节数组转换为 Class 类的实例
    */
    public Class<?> findClass(String name) throws ClassNotFoundException {
    	System.out.println("----------findClass" +
    			"");
    	 //Class c2=findLoadedClass("com.test.one.tow");
//         if(c2==null){
//         	System.out.println(/Users/yangzhen/data/uc-workspace/test/target/classes" ...null");
//         
//         }
        byte[] b = null;
        try {
            b = loadClassData(AutoClassLoader.FormatClassName(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("findclass-----over");
        return defineClass("com.uc.jvm.Goodest", b, 0, b.length);

    }
    private byte[] loadClassData(String filepath) throws Exception {
        int n =0;
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
    @Override
    public Class loadClass(String name){
    	System.out.println("loadClass===========" + name);//输出要加载的类！！！
    	 System.out.println(name.indexOf("java."));
//    	 if(name.indexOf("java.")<5&&name.indexOf("java.")>-1){
//             try {
//                 return super.loadClass(name);
//             } catch (ClassNotFoundException e) {
//                 e.printStackTrace();
//             }
//             ;}
        if(name.indexOf("java.")<5&&name.indexOf("java.")>-1){
            return null;
        }
		 Class c2=findLoadedClass("com.uc.jvm.Goodest");
		
		 String path=AutoClassLoader.FormatClassName(name);
		 System.out.println("loadclass"+path);
		// 
		byte[] b = null;
		try {
		    b = loadClassData(path);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		System.out.println("loadclass-----over"+path);
		return defineClass("com.test.one.tow", b, 0, b.length);
    }
    /*
    * 格式化文件所对应的路径
    */
    public static String FormatClassName(String name){
        
        FILEPATH= DEAFAULTDIR+name+".class";
        return FILEPATH;
    }
        
    /*
    * main方法测试
    */
    public static void main(String[] args) throws Exception {
        
        AutoClassLoader acl = new AutoClassLoader();
        //stem.out.println("-----2-----");
        Class c = acl.findClass("com/uc/jvm/Goodest");
       
        //Object obj = c.newInstance();
        //Method m = c.getMethod("getName",new Class[]{String.class ,int.class});
        //m.invoke(obj,"你好",123);
        System.out.println(c.getName());
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(AutoClassLoader.class.getClassLoader());
    }
}
