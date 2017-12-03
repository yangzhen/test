package com.uc.jvm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义加载器的工具类
 * 临时写的，暂时不做过多控制
 * @author Ran
 *
 */
public class ClassLoaderUtils extends ClassLoader{
	// 获取二进制字节流
	private byte[] getData(InputStream in){
		byte[] data = null;
		try {
			data = new byte[in.available()];
			in.read(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}
	
	// 获得文件流
	private InputStream getInputStream(String name){
		name = name.replace(".", "/")+".class";
		return getClass().getClassLoader().getResourceAsStream(name);
	}
    
	
	// 这个源码是直接抛出异常，并且是protected，好让我们重写findClass
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		InputStream in = getInputStream(name);
		if(in == null){
			System.out.println("自定义加载失败！");
			return super.loadClass(name);
		}
		byte[] data = this.getData(in);  
		return super.defineClass(name,data, 0, data.length);
	}
	
	//  测试
	public static void main(String[] args) throws Exception, 
        IllegalAccessException, ClassNotFoundException {
                // 
		//String name = "com.uc.jvm.Goodest";
		String name = "java.lang.test.LangTest";
		ClassLoaderUtils cu = new ClassLoaderUtils();
		System.out.println(cu.findClass(name).newInstance());
	}
}
