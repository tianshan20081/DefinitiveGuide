/**
 * 
 */
package com.aoeng.degu.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mar 23, 2014 11:32:19 AM
 * 
 */
public class ThreadPoolManager {
	private ExecutorService service;
	private static ThreadPoolManager manager = new ThreadPoolManager();

	private ThreadPoolManager() {
		int num = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num * 4);
	}

	/**
	 * @return
	 */
	public static ThreadPoolManager getInstance() {
		// TODO Auto-generated method stub
		return manager;
	}
	public void addTask(Runnable runnable) {
		service.execute(runnable);
	}
}
