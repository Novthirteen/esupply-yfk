package com.yfk.webapp.util;

import javax.servlet.ServletContext;

public class AppContextUtil {
    private static ServletContext context;
    /* Called by Listener */
    public static void setServletContext(ServletContext context){
    	AppContextUtil.context = context;
    }
    /* Use this method to access context from any location */
    public static ServletContext getServletContext(){
        return AppContextUtil.context;
    }
}
