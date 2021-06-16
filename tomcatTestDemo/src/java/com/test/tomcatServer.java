package com.test;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class tomcatServer implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化");
        //获取当前请求域
        ServletContext servletContext = servletConfig.getServletContext();
        //获取当前的路径
        System.out.println(servletContext.getContextPath());
        System.out.println(servletContext);
        //获取请求的参数值
        /*String username = servletConfig.getInitParameter("username");
        System.out.println(username);
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()){
            System.out.println(initParameterNames.nextElement());
        }*/
        servletContext.setAttribute("sss","fuckyou");
        servletContext.setAttribute("nono","pig");
        //获取服务名
        String servletName = servletConfig.getServletName();
        System.out.println(servletName);
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("获取配置");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletContext servletContext = servletRequest.getServletContext();
        Object sss = servletContext.getAttribute("sss");
        System.out.println(sss);
        Enumeration<String> attributeNames = servletContext.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            System.out.println(attributeNames.nextElement());
        }
        System.out.println("调用时触发服务");
    }

    @Override
    public String getServletInfo() {
        System.out.println("开启servlet");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("销毁此方法调用");
    }
}
