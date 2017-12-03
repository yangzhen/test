package com.uc.jvm;

import java.io.InputStream;

 //自定义类加载器
 class MyClassLoader extends ClassLoader {

	 @Override
	public Class<?> loadClass(String name) throws ClassNotFoundException{
		 System.out.println("------------");

		 try {
			String fileName = name.replace('.', '\\')+".class";
			InputStream is = getClass().getResourceAsStream(fileName);
			if (null == is) {
				return super.loadClass(name);
			}

			byte[] b = new byte[is.available()];
			is.read(b);
			return defineClass(name, b, 0, b.length);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ClassNotFoundException(name);
		}
	}

	 @Override
	 protected Class<?> findClass(String name) throws ClassNotFoundException {
		 System.out.println("xxxxxxxx");
		 return super.findClass(name);
	 }

	 @Override
	protected void finalize() throws Throwable {
		System.out.println("我被抛弃了！");
	}

}


public class Doit {   
	
	public static void main(String[] args){
		
		//是有自定义类加载器两次加载，static两次被调用，类加载器无引用后被回收
		MyClassLoader my = new MyClassLoader();
		//Thread.currentThread().setContextClassLoader(my);
		Object a = null;
		try {
			Class<?> clazz =my.loadClass("com.uc.jvm.Goodest");
			//Class<?> clazz = Class.forName("com.uc.jvm.Goodest");
			System.out.println("Class' HashCode! ="+clazz.hashCode());
			a = clazz.newInstance();
			clazz = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.gc();
		
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		MyClassLoader your = new MyClassLoader();
		try {
			Class<?> clazz2 = your.findClass("java.lang.String");
			System.out.println("Class' HashCode! =" + clazz2.hashCode());
			Object a2 = clazz2.newInstance();
			clazz2 = null;
			System.out.println(a.getClass().isAssignableFrom(a2.getClass()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		your = null;
		System.gc();
		
		
		System.out.println("完！");
		
	}

	

}
