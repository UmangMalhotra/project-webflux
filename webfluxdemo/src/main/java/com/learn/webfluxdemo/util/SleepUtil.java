package com.learn.webfluxdemo.util;

public class SleepUtil {

	public static void sleep(int sleepInSec) {
		try {
			Thread.sleep(sleepInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
