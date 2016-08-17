package com.uc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VoliateTest {
	
	private volatile A a = new A();
	public String tv (String name, int sleep) throws InterruptedException {
		Thread.sleep(sleep);
		if(!a.isFlag()) {
			a.setFlag(true);
			a.setName(name);
			System.out.println("thread:" + Thread.currentThread().getId()+",param:" + name+",a.name:" + a.getName());
		}  else {
			System.out.println("thread:" + Thread.currentThread().getId()+",param:" + name+",a.name:" + a.getName());
		}
		return a.getName();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final VoliateTest test = new VoliateTest();
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		Future<String> future = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return test.tv("aaa",5000);
			}
		});

		Future<String> future2 = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return test.tv("bbb",1000);
			}
		});
		System.out.println("future:" + future.get()+",future2:" + future2.get());
	}
	
}
class A {
	private boolean flag = false;
	private String name;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}