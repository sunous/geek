package com.wlw.servlet;

import com.wlw.annotation.WlwAutowired;
import com.wlw.annotation.WlwController;
import com.wlw.annotation.WlwRequestMapping;
import com.wlw.annotation.WlwService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class WlwServlet extends HttpServlet{

    private Properties properties = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String,Object> IOC = new HashMap<>();

    private Map<String,Method> haddlerMapping = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            //读取配置,加载配置文件中的参数
            initConfig(config.getInitParameter("contextConfigLocation"));

            //扫描配置的包
            initScanner(properties.getProperty("scanner.package"));
            
            //初始化扫描到的类，并将其存入IOC容器中
            doInstance();

            //自动注入，依赖注入
            doAutowired();

            //初始化HadderMapping
            initHandlerMapping();

            //
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initHandlerMapping() {
        //如果IOC容器为空，直接返回
        if(IOC.isEmpty()) return;
        for(Map.Entry<String,Object> entry:IOC.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(WlwController.class))continue;
            if (clazz.isAnnotationPresent(WlwRequestMapping.class)){
                WlwRequestMapping wlwRequestMapping = clazz.getAnnotation(WlwRequestMapping.class);
                String url = wlwRequestMapping.value();//Controller的RequestMapping前缀
                Method[] methods = clazz.getMethods();
                for(Method method:methods){
                    WlwRequestMapping methodAnnotation = method.getAnnotation(WlwRequestMapping.class);
                    String urlSubfix = methodAnnotation.value();
                    url = "/"+url+"/"+urlSubfix;
                    url = url.replaceAll("\\/+","/");
                    haddlerMapping.put(url,method);
                }


            }


        }

    }

    private void doAutowired() {
        //如果IOC容器为空，直接返回
        if(IOC.isEmpty()) return;
        //自动注入属性值
        for(Map.Entry<String,Object> entry:IOC.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            Field[] fields = clazz.getDeclaredFields();
                for (Field field :fields) {
                    if (field.isAnnotationPresent(WlwAutowired.class)){//证明是被注入的属性
                        WlwAutowired wlwAutowired = field.getAnnotation(WlwAutowired.class);
                        String beanName = field.getType().getName();
                        String value = wlwAutowired.value();
                        if (!"".equals(value)){
                            beanName = value;
                        }
                        field.setAccessible(true);
//                        IOC.put(beanName,IOC.get(beanName));
                        try {
                            //将class中的属性从IOC容器中获取到并赋值
                            field.set(entry.getValue(), IOC.get(beanName));
                        }catch (Exception e){

                        }


                    }
                }
            }



    }

    private void doInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (classNames.isEmpty()){ return;}
        for(String className :classNames){
            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(WlwController.class)){

                Object instance = clazz.newInstance();
                String beanName = lowerFirstCase(clazz.getSimpleName());
                IOC.put(beanName, instance);
            }else if (clazz.isAnnotationPresent(WlwService.class)){
                WlwService wlwService = clazz.getAnnotation(WlwService.class);
                String beanName = clazz.getSimpleName();
                String value = wlwService.value();
                if (!"".equals(value)){
                    //1、自定义beanName，优先是用定义的
                    //如果注解中存在value值，则beanName为当前的value值
                    beanName=value;
                }
                //2、默认首字母小写
                beanName = lowerFirstCase(beanName);
                //3、自动注入实现类
                Object instance = clazz.newInstance();
                IOC.put(beanName,instance);
                //插入interface
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> i:interfaces) {
                    IOC.put(i.getName(),instance);
                }
            }
        }

    }

    private void initScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File fileDir = new File(url.getFile());
        for(File classFile : fileDir.listFiles()){
            if(classFile.isDirectory()) {
                initScanner(scanPackage+"."+classFile.getName());
            }else {
                String className = scanPackage+"."+classFile.getName().replace(".class","");
                classNames.add(className);
            }
        }
    }

    private void initConfig(String contextConfigLocation) throws IOException {
        InputStream is =  WlwServlet.class.getClassLoader().getResourceAsStream(contextConfigLocation.replace("classpath:",""));
        properties.load(is);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String requestURI = req.getRequestURI();
        Method method = haddlerMapping.get(requestURI);
        if (method == null){
            resp.getWriter().write("404 not found");
            return;
        }
        //获取实参列表
        Map<String, String[]> parameterMap = req.getParameterMap();
        //获取形参列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        for(int i = 0;i<parameterTypes.length;i++){
            //按顺序获取形参列表
            Class<?> clazz = parameterTypes[i];
            if (clazz == HttpServletRequest.class){
//                parameterMap.get(i)=
            }
        }

        String  beanName = lowerFirstCase(method.getDeclaringClass().getSimpleName());
        try {
            String[] names = parameterMap.get("name");
            StringBuffer sb = new StringBuffer();
            for (String s:names){
                sb.append(",").append(s);
            }
            method.invoke(IOC.get(beanName),new Object[]{req,resp,sb.toString().replaceFirst(",","")});
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    private String lowerFirstCase(String simpleName){

        char[] chars = simpleName.toCharArray();
        if (chars[0]<97){
            chars[0]+=32;//如果小于97.则保证首字母不是小写，可以在这个基础上加上32，变为大写字母
        }
        return String.valueOf(chars);

    }

}
