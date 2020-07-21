package com.concurrency.threadlocal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ThreadLocal in Java is another way to achieve thread-safety apart from
 * writing immutable classes. If you have been writing multi-threaded or
 * concurrent code in Java then you must be familiar with cost of
 * synchronization or locking which can greatly affect Scalability of
 * application, but there is no choice other than synchronize if you are sharing
 * objects between multiple threads. ThreadLocal in Java is a different way to
 * achieve thread-safety, it doesn't address synchronization requirement,
 * instead it eliminates sharing by providing explicitly copy of Object to each
 * thread. Since Object is no more shared there is no requirement of
 * Synchronization which can improve scalability and performance of application.
 * 
 * On basic level ThreadLocal provides Thread Confinement which is extension of
 * local variable. while local variable only accessible on block they are
 * declared, ThreadLocal are visible only in Single Thread. No two Thread can
 * see each others ThreadLocal variable. Real Life example of ThreadLocal are in
 * J2EE application servers which uses java ThreadLocal variable to keep track
 * of transaction and security Context. It makes lot of sense to share heavy
 * object like Database Connection as ThreadLocal in order to avoid excessive
 * creation and cost of locking in case of sharing global instance.
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class ThreadLocalTest {

	public static void main(String args[]) throws IOException {
		Thread t1 = new Thread(new Task());
		Thread t2 = new Thread(new Task());

		t1.start();
		t2.start();

	}

	/*
	 * Thread safe format method because every thread will use its own
	 * DateFormat
	 */
	public static String threadSafeFormat(Date date) {
		DateFormat formatter = PerThreadFormatter.getDateFormatter();
		return formatter.format(date);
	}

}

/*
 * Thread Safe implementation of SimpleDateFormat Each Thread will get its own
 * instance of SimpleDateFormat which will not be shared between other threads.
 * *
 */
class PerThreadFormatter {

	private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>() {

		/*
		 * initialValue() is called
		 */
		@Override
		protected SimpleDateFormat initialValue() {
			System.out.println("Creating SimpleDateFormat for Thread : "
					+ Thread.currentThread().getName());
			return new SimpleDateFormat("dd/MM/yyyy");
		}
	};

	/*
	 * Every time there is a call for DateFormat, ThreadLocal will return
	 * calling Thread's copy of SimpleDateFormat
	 */
	public static DateFormat getDateFormatter() {
		return dateFormatHolder.get();
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println("Thread: " + Thread.currentThread().getName()
					+ " Formatted Date: "
					+ ThreadLocalTest.threadSafeFormat(new Date()));
		}
	}
}
