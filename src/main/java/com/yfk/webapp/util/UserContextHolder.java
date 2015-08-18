package com.yfk.webapp.util;

public class UserContextHolder {
	private static ThreadLocal<String> userContext = new ThreadLocal<String>();
	
	 public static String Get()
     {
         return userContext.get();
     }

     public static void Set(String user)
     {
    	 userContext.set(user);
     }
}
